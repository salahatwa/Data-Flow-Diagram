/*
 * $Id: UserDrawDataFlow.java,v 1.2 2006/01/03 11:12:04 david Exp $
 * 
 * Copyright (c) 2001-2005, Gaudenz Alder
 * 
 * See LICENSE file in distribution for licensing details of this source file
 */
package com.jgraph.example.adapter;

import LevelsOfDataFlowDiagram.ContextDiagram;
import LevelsOfDataFlowDiagram.ScreenImage;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.event.UndoableEditEvent;
import static LevelsOfDataFlowDiagram.LevelZero.save;

import org.jgraph.event.GraphSelectionEvent;
import org.jgraph.example.GraphEd;
import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.GraphUndoManager;

/**
 * NOTE: To connect a database to this example please go to the main method and
 * read to comments in the try block. If you want to use HSQLDB you can simply
 * add the driver to the classpath and uncomment the two commented-out lines.
 * For other databases you must change the name of the driver class.
 */
public class UserDrawDataFlow extends GraphEd {

	// Actions which Change State
	protected Action hide, collapse, expand, expandAll;

	// Add a console
	protected static JTextArea console = new JTextArea();

	protected static JGraphSQLBackend backend = null;

	protected static Connection conn = null;
        
        JPanel south=new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JButton save=new JButton("Save");
        

	public UserDrawDataFlow(JGraphAdapterBackend backend) {
		println("Connect this example to a database (see JGraphAdapterExample)");
		// Use Border Layout
		getContentPane().setLayout(new BorderLayout());

		JGraphAdapterModel bm = new JGraphSQLBusinessModel();
		bm.setBackend(backend);

		// Construct the Graph
		graph = new MyGraph(bm);
		graph.setGraphLayoutCache(new GraphLayoutCache(graph.getModel(),
				new DefaultCellViewFactory(), true));
		// Use a Custom Marquee Handler
		graph.setMarqueeHandler(new MyMarqueeHandler());
		
		// Construct Command History
		//
		// Create a GraphUndoManager which also Updates the ToolBar
		undoManager = new GraphUndoManager() {
			// Override Superclass
			public void undoableEditHappened(UndoableEditEvent e) {
				// First Invoke Superclass
				super.undoableEditHappened(e);
				// Then Update Undo/Redo Buttons
				updateHistoryButtons();
			}
		};

		// Add Listeners to Graph
		//
		// Register UndoManager with the Model
		graph.getModel().addUndoableEditListener(undoManager);
		// Update ToolBar based on Selection Changes
		graph.getSelectionModel().addGraphSelectionListener(this);
		// Listen for Delete Keystroke when the Graph has Focus
		graph.addKeyListener(this);

		// Construct Panel
		//
		// Add a ToolBar
                south.setBackground(Color.BLACK);
		getContentPane().add(createToolBar(), BorderLayout.NORTH);
		// Add the Graph as Center Component
		//JGraphSQLQueryPane queryPane = new JGraphSQLQueryPane(this, graph);
		// queryPane.setPreferredSize(new Dimension(100, 40));
		south.add(save);
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				graph,south);
		splitPane.setResizeWeight(1.0);
		getContentPane().add(splitPane, BorderLayout.CENTER);
                
                 save.addActionListener(new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent e) {
              try {
                FileDialog fileDialog=new FileDialog(new Frame(),"Save Image",FileDialog.SAVE);
                
                fileDialog.show();
             
                
                String fileName=fileDialog.getDirectory()+fileDialog.getFile()+".png";
             
                
                System.out.println(fileName);
                
                    makePanelImage(graph,fileName);
                } catch (IOException ex) {
                    Logger.getLogger(ContextDiagram.class.getName()).log(Level.SEVERE, null, ex);
                } catch (AWTException ex) {
                    Logger.getLogger(ContextDiagram.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
       });
                 
	}

        private void makePanelImage(Component panel,String fileName) throws IOException, AWTException
       {
        BufferedImage bi = ScreenImage.createImage(panel);
        ScreenImage.writeImage(bi,fileName);
       }
	public Map createCellAttributes(Point2D point) {
		Map map = super.createCellAttributes(point);
		GraphConstants.setInset(map, 5);
		return map;
	}

	public Map createEdgeAttributes() {
		Map map = super.createEdgeAttributes();
		// Adds a parallel edge router
		GraphConstants
				.setRouting(map, JGraphParallelRouter.getSharedInstance());
		return map;
	}

	protected DefaultGraphCell createGroupCell() {
		return createAdapterVertex();
	}

	protected DefaultGraphCell createDefaultGraphCell() {
		return createAdapterVertex();
	}

	protected static DefaultGraphCell createAdapterVertex() {
		DefaultGraphCell cell = new DefaultGraphCell(new JGraphSQLEntity(
				"New Entity Or Process"));
		cell.addPort();
		return cell;
	}

	// Hook for subclassers
	protected DefaultEdge createDefaultEdge() {
		return createAdapterEdge();
	}

	protected static DefaultEdge createAdapterEdge() {
		return new DefaultEdge(new JGraphSQLRelation("New Linked Edge"));
	}

	public JToolBar createToolBar() {
		JToolBar toolbar = super.createToolBar();
		// Hide
		hide = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				graph.getGraphLayoutCache().hideCells(
						graph.getSelectionCells(), true);
			}
		};
		URL url = getClass().getClassLoader().getResource(
				"com/jgraph/example/adapter/image/hide.gif");
		hide.putValue(Action.SMALL_ICON, new ImageIcon(url));
		hide.setEnabled(false);
		toolbar.addSeparator();
		toolbar.add(hide);
		toolbar.addSeparator();

		// Collapse
		collapse = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				graph.getGraphLayoutCache().collapse(graph.getSelectionCells());
			}
		};
		url = getClass().getClassLoader().getResource(
				"com/jgraph/example/adapter/image/collapse.gif");
		collapse.putValue(Action.SMALL_ICON, new ImageIcon(url));
		collapse.setEnabled(false);
		toolbar.add(collapse);

		// Expand
		expand = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				graph.getGraphLayoutCache().expand(graph.getSelectionCells());
			}
		};
		url = getClass().getClassLoader().getResource(
				"com/jgraph/example/adapter/image/expand.gif");
		expand.putValue(Action.SMALL_ICON, new ImageIcon(url));
		expand.setEnabled(false);
		toolbar.add(expand);

		// ExpandAll
		expandAll = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				graph.getGraphLayoutCache().expand(
						graph.getDescendants(graph.getSelectionCells()));
			}
		};
		url = getClass().getClassLoader().getResource(
				"com/jgraph/example/adapter/image/expandAll.gif");
		expandAll.putValue(Action.SMALL_ICON, new ImageIcon(url));
		expandAll.setEnabled(false);
		toolbar.add(expandAll);

		return toolbar;
	}

	// From GraphSelectionListener Interface
	public void valueChanged(GraphSelectionEvent e) {
		super.valueChanged(e);
		// Group Button only Enabled if a cell is selected
		boolean enabled = !graph.isSelectionEmpty();
		hide.setEnabled(enabled);
		expand.setEnabled(enabled);
		expandAll.setEnabled(enabled);
		collapse.setEnabled(enabled);
	}

    /**
     * Main method
     */
    public UserDrawDataFlow() {
		// Initializing backend (file-based database)
		try {
			// Download the HSQLDB (org.hsqldb.jdbcDriver) or any other JDBC
			// driver and connect this example to a real database by
			// uncommenting the following statement (passing a connection to the
			// SQL backend):
			// Class.forName("org.hsqldb.jdbcDriver");
			// conn = DriverManager.getConnection(
			// "jdbc:hsqldb:" + backendFilename, "sa", "");
			backend = new JGraphSQLBackend(conn, createAdapterVertex(),
					createAdapterEdge());

			// Construct Frame
			JFrame frame = new JFrame("User Graph");
			// Set Close Operation to Exit
			frame.addWindowListener(new WindowAdapter() {
				public void windowClosed(WindowEvent e) {
					try {
						if (backend != null && conn != null) {
							backend.update("SHUTDOWN");
							conn.close();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();

					}
					System.exit(0);
				}
			});
			frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			// Add an Editor Panel
			frame.getContentPane().add(new UserDrawDataFlow(backend));
			// Fetch URL to Icon Resource
			URL jgraphUrl = UserDrawDataFlow.class.getClassLoader()
					.getResource("org/jgraph/example/resources/jgraph.gif");
			// If Valid URL
			if (jgraphUrl != null) {
				// Load Icon
				ImageIcon jgraphIcon = new ImageIcon(jgraphUrl);
				// Use in Window
				frame.setIconImage(jgraphIcon.getImage());
			}
			// Set Default Size
			frame.setSize(640, 480);
			// Show Frame
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void println(String msg) {
		console.setText(console.getText() + msg + "\n");
	}

}
