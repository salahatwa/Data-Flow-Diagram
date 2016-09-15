package LevelsOfDataFlowDiagram;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.jgraph.JGraph;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphModel;


/**
 * This example is an evolution of the hello world example. This time,
 * we demonstrate the use of a custom graph cell view factory able to
 * provide different view indirections for various cells. Then all is about
 * using Java2D features in the view renderer to get draw the shape you want.
 * 
 * @author Ahmed shalash && salah atwa
 */
public class LevelOne extends JFrame{
    
   public static JLabel dataFlow=new JLabel("  Data Flow Diagram Design");
     
     //Menu Item
   public  static  JButton save=new JButton("Save");
   public static  JButton  open=new JButton("Open");
   

    
   public LevelOne(JGraph panel,JPanel allPanels,String fileName)
    {
      //  panel.setBackground(Color.red);
        
        //repaint();
        //panel.setOpaque(t);
        //allPanels.setOpaque(true);
        allPanels.remove(panel);
        allPanels.revalidate();
        
        JLabel label=new JLabel(new ImageIcon(fileName));
       // label.set
        JScrollPane scrollPane=new JScrollPane(label);
        allPanels.add(scrollPane);
    }
   

   public LevelOne(ArrayList<String> allProcess,ArrayList<String> allEntits,ArrayList<String> processToEntity,ArrayList<String> entityToProcess,ArrayList<String> all_datastore,ArrayList<String> inputDataStore,ArrayList<String> processToDataStore,ArrayList<String> dataStoreToProcess,ArrayList<String> processToprocess,String selectedSubProcessToSubset,ArrayList<String> all_sub_process,
           ArrayList<String> linkEntityToSubProcess,ArrayList<String> linkSubProcessToEntity,ArrayList<String>processToDataStore1,ArrayList<String> dataStoreToProcess1,ArrayList<String> processToprocess1)
   {  
       
       JGraphRoundRectView.ActivityRenderer.setI(1.1);
      
       //########################################################################
		// Construct Model and Graph
		GraphModel model = new DefaultGraphModel();
		JGraph graph = new JGraph(model);
		
		graph.getGraphLayoutCache().setFactory(new GPCellViewFactory());

		// Control-drag should clone selection
		graph.setCloneable(true);

		// Enable edit without final RETURN keystroke
		graph.setInvokesStopCellEditing(true);

		// When over a cell, jump to its default port (we only have one, anyway)
		graph.setJumpToDefaultPort(true);
                //SupProcess
             
                 //Data Storage
               ArrayList<DefaultGraphCell> dataStorage =new ArrayList<>();
               
                 
                  if(all_datastore.size()==1)
                 {
                 dataStorage.add(createVertex(10, 10, 80, 30, Color.RED, false, new DefaultGraphCell("D1:"+all_datastore.get(0)), null));   
                 }
                 else if(all_datastore.size()==2)
                 {
                 dataStorage.add(createVertex(10, 10, 80, 30, Color.RED, false, new DefaultGraphCell("D1:"+all_datastore.get(0)), null));
                 dataStorage.add(createVertex(365,10, 80, 30, Color.RED, false, new DefaultGraphCell("D2:"+all_datastore.get(1)), null));
                 }
                 

	
                //entity Drawn
               ArrayList<DefaultGraphCell> entityCells  =new ArrayList<>();
                
                if(allEntits.size()==1)
                {
                   entityCells.add(createVertex(180, 280, 120, 40, Color.BLUE, false, new DefaultGraphCell(allEntits.get(0)), null));
                }
                else if(allEntits.size()==2)
                {
                    entityCells.add(createVertex(10, 150, 120, 40, Color.BLUE, false, new DefaultGraphCell(allEntits.get(0)), null));
                    entityCells.add(createVertex(355, 150, 120, 40, Color.BLUE, false, new DefaultGraphCell(allEntits.get(1)), null));
                }
                else if(allEntits.size()==3)
                {
                    entityCells.add(createVertex(10, 150, 120, 40, Color.BLUE, false, new DefaultGraphCell(allEntits.get(0)), null));
                    entityCells.add(createVertex(355, 150, 120, 40, Color.BLUE, false, new DefaultGraphCell(allEntits.get(1)), null));
                    entityCells.add(createVertex(180, 280, 120, 40, Color.BLUE, false, new DefaultGraphCell(allEntits.get(2)), null));
                }
                 else if(allEntits.size()==4)
                {
                    entityCells.add(createVertex(10, 150, 120, 40, Color.BLUE, false, new DefaultGraphCell(allEntits.get(0)), null));
                    entityCells.add(createVertex(355, 150, 120, 40, Color.BLUE, false, new DefaultGraphCell(allEntits.get(1)), null));
                    entityCells.add(createVertex(10, 280, 120, 40, Color.BLUE, false, new DefaultGraphCell(allEntits.get(2)), null));
                    entityCells.add(createVertex(355,280, 120, 40, Color.BLUE, false, new DefaultGraphCell(allEntits.get(3)), null));                   
                }
                
                	// Insert all three cells in one call, so we need an array to store them
		//DefaultGraphCell[] processCells = new DefaultGraphCell[allProcess.size()+outputsFromProcess.size()+processToprocess.size()+all_sub_process.size()-1];

                ArrayList<DefaultGraphCell> processCells=new ArrayList<>();
                
                ArrayList<DefaultGraphCell> subProcessCells =new ArrayList<>();
               
		
                if(allProcess.size()==2)
                {
                  
                  processCells.add(createVertex(180,150, 120, 80, Color.ORANGE, false, new DefaultGraphCell(allProcess.get(0)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                 
                  processCells.add(createVertex(180, 40, 120, 80, Color.ORANGE, false, new DefaultGraphCell(allProcess.get(1)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
               
              //System.out.println(processCells.get(0).toString()+"%%");
             //#######################################################################
             //##################################################################
           //SubProcess Algorithm
               if(!all_sub_process.isEmpty())
               {
                   for(int i=0;i<processCells.size();i++)
                   {
                       if(processCells.get(i).toString().equals(selectedSubProcessToSubset))
                       {
                           processCells.remove(processCells.get(i));
                          
                           //this is First Process for x and Y
                           if(i==0)
                           {
                             if(all_sub_process.size()==2)
                             {
                               subProcessCells.add(createVertex(180,150, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(0)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                               subProcessCells.add(createVertex(250,150, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(1)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                             }
                             else if(all_sub_process.size()==3)
                             {
                               subProcessCells.add(createVertex(180,150, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(0)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                               subProcessCells.add(createVertex(240,150, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(1)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                               subProcessCells.add(createVertex(210,180, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(2)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                             }
                           }
                           else if(i==1)
                           {
                               if(all_sub_process.size()==2)
                             {
                               subProcessCells.add(createVertex(180,40, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(0)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                               subProcessCells.add(createVertex(250,40, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(1)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                             }
                             else if(all_sub_process.size()==3)
                             {
                               subProcessCells.add(createVertex(180,40, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(0)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                               subProcessCells.add(createVertex(240,40, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(1)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                               subProcessCells.add(createVertex(210,90, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(2)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                             }
                           }
                       }
                   }
               }
              //#######################################################################                   
              //#######################################################################
      }
                else if(allProcess.size()==3)
                {
                  System.out.println("all Process 3");
                  //System.out.println("Process Lenght:"+processCells.length);
                  processCells.add(createVertex(200,250, 90, 80, Color.ORANGE, false, new DefaultGraphCell(allProcess.get(0)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                  processCells.add(createVertex(200,150, 90, 80, Color.ORANGE, false, new DefaultGraphCell(allProcess.get(1)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                  processCells.add(createVertex(200,50, 90, 80, Color.ORANGE, false, new DefaultGraphCell(allProcess.get(2)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                
                  
                  //#######################################################################
             //##################################################################
           //SubProcess Algorithm
               if(!all_sub_process.isEmpty())
               {
                   for(int i=0;i<processCells.size();i++)
                   {
                       if(processCells.get(i).toString().equals(selectedSubProcessToSubset))
                       {
                           processCells.remove(processCells.get(i));
                          
                           //this is First Process for x and Y
                           if(i==0)
                           {
                             if(all_sub_process.size()==2)
                             {
                               subProcessCells.add(createVertex(200,250, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(0)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                               subProcessCells.add(createVertex(270,250, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(1)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                             }
                             else if(all_sub_process.size()==3)
                             {
                               subProcessCells.add(createVertex(200,250, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(0)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                               subProcessCells.add(createVertex(270,250, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(1)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                               subProcessCells.add(createVertex(235,280, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(2)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                             }
                           }
                           else if(i==1)
                           {
                               if(all_sub_process.size()==2)
                             {
                               subProcessCells.add(createVertex(200,150, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(0)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                               subProcessCells.add(createVertex(270,150, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(1)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                             }
                             else if(all_sub_process.size()==3)
                             {
                               subProcessCells.add(createVertex(200,150, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(0)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                               subProcessCells.add(createVertex(270,150, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(1)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                               subProcessCells.add(createVertex(235,180, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(2)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                             }
                           }
                           else if(i==2)
                           {
                               if(all_sub_process.size()==2)
                             {
                               subProcessCells.add(createVertex(200,50, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(0)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                               subProcessCells.add(createVertex(270,50, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(1)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                             }
                             else if(all_sub_process.size()==3)
                             {
                               subProcessCells.add(createVertex(200,50, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(0)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                               subProcessCells.add(createVertex(270,50, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(1)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                               subProcessCells.add(createVertex(235,80, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(2)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                             }
                           }
                           
                       }
                   }
               }
              //#######################################################################                   
              //#######################################################################
                  
                  
               
                //#########################################################################################
                
                }
                else if(allProcess.size()==4)
                {
                  processCells.add(createVertex(200,250, 90, 80, Color.ORANGE, false, new DefaultGraphCell(allProcess.get(0)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                  processCells.add(createVertex(200,150, 90, 80, Color.ORANGE, false, new DefaultGraphCell(allProcess.get(1)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                  processCells.add(createVertex(200,50, 90, 80, Color.ORANGE, false, new DefaultGraphCell(allProcess.get(2)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                  processCells.add( createVertex(10,60, 90, 60, Color.ORANGE, false, new DefaultGraphCell(allProcess.get(3)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
               
                
              //#######################################################################
             //##################################################################
           //SubProcess Algorithm
               if(!all_sub_process.isEmpty())
               {
                   for(int i=0;i<processCells.size();i++)
                   {
                       if(processCells.get(i).toString().equals(selectedSubProcessToSubset))
                       {
                           processCells.remove(processCells.get(i));
                          
                           //this is First Process for x and Y
                           if(i==0)
                           {
                             if(all_sub_process.size()==2)
                             {
                               subProcessCells.add(createVertex(200,250, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(0)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                               subProcessCells.add(createVertex(270,250, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(1)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                             }
                             else if(all_sub_process.size()==3)
                             {
                               subProcessCells.add(createVertex(200,250, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(0)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                               subProcessCells.add(createVertex(270,250, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(1)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                               subProcessCells.add(createVertex(235,280, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(2)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                             }
                           }
                           else if(i==1)
                           {
                               if(all_sub_process.size()==2)
                             {
                               subProcessCells.add(createVertex(200,150, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(0)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                               subProcessCells.add(createVertex(270,150, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(1)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                             }
                             else if(all_sub_process.size()==3)
                             {
                               subProcessCells.add(createVertex(200,150, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(0)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                               subProcessCells.add(createVertex(270,150, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(1)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                               subProcessCells.add(createVertex(235,180, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(2)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                             }
                           }
                           else if(i==2)
                           {
                               if(all_sub_process.size()==2)
                             {
                               subProcessCells.add(createVertex(200,50, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(0)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                               subProcessCells.add(createVertex(270,50, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(1)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                             }
                             else if(all_sub_process.size()==3)
                             {
                               subProcessCells.add(createVertex(200,50, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(0)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                               subProcessCells.add(createVertex(270,50, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(1)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                               subProcessCells.add(createVertex(235,80, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(2)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                             }
                           }
                           else if(i==3)
                           {
                             if(all_sub_process.size()==2)
                             {
                               subProcessCells.add(createVertex(10,60, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(0)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                               subProcessCells.add(createVertex(80,60, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(1)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                             }
                             else if(all_sub_process.size()==3)
                             {
                               subProcessCells.add(createVertex(10,60, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(0)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                               subProcessCells.add(createVertex(80,60, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(1)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                               subProcessCells.add(createVertex(45,82, 50, 25, Color.ORANGE, false, new DefaultGraphCell(all_sub_process.get(2)), "LevelsOfDataFlowDiagram.JGraphRoundRectView"));
                             }
                           }
                           
                       }
                   }
               }
              //#######################################################################                   
              //#######################################################################
                  
              }
       
                //output from Parent process to data Storage
              
                 if(!processToDataStore.isEmpty())
                 {
               
                 for (int i = 0; i < processToDataStore.size(); i++)
                  {
                     String t=processToDataStore.get(i).substring(0,processToDataStore.get(i).indexOf("="));
                     String t2=processToDataStore.get(i).substring(processToDataStore.get(i).indexOf(">")+1,processToDataStore.get(i).lastIndexOf("with source")-4);
                    
                      for (int j = 0; j < allProcess.size(); j++) 
                        {
                             if(t.equals(allProcess.get(j)))
                            {
                                for(int k=0;k<all_datastore.size();k++)
                                {
                                    if(t2.equals(all_datastore.get(k)))
                                    {
                                        System.out.println("YES ITS EXCUTE");
                                       // System.out.println("dc"+dataStorage.length+"count"+countD);
                                     // Create Edges
		                     DefaultEdge edge0 = new DefaultEdge();
		                     edge0.setSource(processCells.get(j).getChildAt(0));
		                     edge0.setTarget(dataStorage.get(k).getChildAt(0));
                                     GraphConstants.setLineEnd(edge0.getAttributes(), GraphConstants.ARROW_TECHNICAL);
                                     GraphConstants.setEndFill(edge0.getAttributes(), true);
                                   
                                     dataStorage.add(edge0); 
                                  
                                     break;
                                    }  
                                }
                         
                          }     
                        }
                  }                     
                 }
                
         //output from sub process to data Storage
              
                 if(!processToDataStore1.isEmpty())
                 {
               
                 for (int i = 0; i < processToDataStore1.size(); i++)
                  {
                     String t=processToDataStore1.get(i).substring(0,processToDataStore1.get(i).indexOf("="));
                     String t2=processToDataStore1.get(i).substring(processToDataStore1.get(i).indexOf(">")+1,processToDataStore1.get(i).lastIndexOf("with source")-4);
                    
                      for (int j = 0; j < all_sub_process.size(); j++) 
                        {
                             if(t.equals(all_sub_process.get(j)))
                            {
                                for(int k=0;k<all_datastore.size();k++)
                                {
                                    if(t2.equals(all_datastore.get(k)))
                                    {
                                       
		                     DefaultEdge edge0 = new DefaultEdge();
		                     edge0.setSource(subProcessCells.get(j).getChildAt(0));
		                     edge0.setTarget(dataStorage.get(k).getChildAt(0));
                                     GraphConstants.setLineEnd(edge0.getAttributes(), GraphConstants.ARROW_TECHNICAL);
                                     GraphConstants.setEndFill(edge0.getAttributes(), true);
                                     subProcessCells.add(edge0); 
                                     break;
                                    }  
                                }
                         
                          }     
                        }
                  }                     
                 }
                
                
                 //Output from data Storage To Process
                 //dataStoreToProcess.get(i)+"@@@"
                if(!dataStoreToProcess.isEmpty())
                {
                 //int countP=dataStorage.length-processToDataStore.size()-1;
                 
                 for (int i = 0; i < dataStoreToProcess.size(); i++)
                  {
                     //dataStore
                     String t=dataStoreToProcess.get(i).substring(0,dataStoreToProcess.get(i).indexOf("="));
                     //process
                     String t2=dataStoreToProcess.get(i).substring(dataStoreToProcess.get(i).indexOf(">")+1,dataStoreToProcess.get(i).lastIndexOf("with source")-4);
                      System.out.println(t+" :salah :"+t2);
                      for (int j = 0; j < all_datastore.size(); j++) 
                        {
                             if(t.equals(all_datastore.get(j)))
                            {
                                for(int k=0;k<allProcess.size();k++)
                                {
                                    if(t2.equals(allProcess.get(k)))
                                    {
                                        System.out.println("No I DO IT");
                                       // System.out.println("dc"+dataStorage.length+" count"+countP);
                                     // Create Edges
		                     DefaultEdge edge0 = new DefaultEdge();
		                     edge0.setSource(dataStorage.get(j).getChildAt(0));
		                     edge0.setTarget(processCells.get(k).getChildAt(0));
                                     GraphConstants.setLineEnd(edge0.getAttributes(), GraphConstants.ARROW_TECHNICAL);
                                     GraphConstants.setEndFill(edge0.getAttributes(), true);
                                     
                                         dataStorage.add(edge0);
                                     break;
                                    }  
                                }
                         
                          }     
                        }
                  }
                 
                }
                
                //out put From Data Store To Sub Process
                
                if(!dataStoreToProcess1.isEmpty())
                {
                  for (int i = 0; i < dataStoreToProcess1.size(); i++)
                  {
                     //dataStore
                     String t=dataStoreToProcess1.get(i).substring(0,dataStoreToProcess1.get(i).indexOf("="));
                     //process
                     String t2=dataStoreToProcess1.get(i).substring(dataStoreToProcess1.get(i).indexOf(">")+1,dataStoreToProcess1.get(i).lastIndexOf("with source")-4);
                      for (int j = 0; j < all_datastore.size(); j++) 
                        {
                             if(t.equals(all_datastore.get(j)))
                            {
                                for(int k=0;k<all_sub_process.size();k++)
                                {
                                    if(t2.equals(all_sub_process.get(k)))
                                    {
		                     DefaultEdge edge0 = new DefaultEdge();
		                     edge0.setSource(dataStorage.get(j).getChildAt(0));
		                     edge0.setTarget(subProcessCells.get(k).getChildAt(0));
                                     GraphConstants.setLineEnd(edge0.getAttributes(), GraphConstants.ARROW_TECHNICAL);
                                     GraphConstants.setEndFill(edge0.getAttributes(), true);
                                     dataStorage.add(edge0); 
                                     break;
                                    }  
                                }
                
                          }     
                        }
                  }
                 
                }
                
                //input from entity to SUB  process
                if(!linkEntityToSubProcess.isEmpty())
                {
                for (int i = 0; i <linkEntityToSubProcess.size(); i++) 
                    {
                String t=linkEntityToSubProcess.get(i).substring(0,linkEntityToSubProcess.get(i).indexOf("="));
                String t2=linkEntityToSubProcess.get(i).substring(linkEntityToSubProcess.get(i).indexOf(">")+1,linkEntityToSubProcess.get(i).lastIndexOf("with source")-4);
                      
                        for (int j = 0; j < allEntits.size(); j++) 
                        {
                             if(t.equals(allEntits.get(j)))
                            {
                                for (int k = 0; k < all_sub_process.size(); k++)
                                {
                                    if(t2.equals(all_sub_process.get(k)))
                                    {
                          DefaultEdge edge0 = new DefaultEdge();
                          edge0.setSource(entityCells.get(j).getChildAt(0));
		          edge0.setTarget(subProcessCells.get(k).getChildAt(0));
                          GraphConstants.setLineEnd(edge0.getAttributes(), GraphConstants.ARROW_TECHNICAL);
                          GraphConstants.setEndFill(edge0.getAttributes(), true);
                          entityCells.add(edge0); 
                          break;
                                    }
                                }
		   
                          }     
                        }
                    }
                 }
                
                //output from SUB process To entity
                if(!linkSubProcessToEntity.isEmpty())
                {
                for (int i = 0; i <linkSubProcessToEntity.size(); i++) 
                    {
                        //Sub Process
                String t=linkSubProcessToEntity.get(i).substring(0,linkSubProcessToEntity.get(i).indexOf("="));
                  //entity
                String t2=linkSubProcessToEntity.get(i).substring(linkSubProcessToEntity.get(i).indexOf(">")+1,linkSubProcessToEntity.get(i).lastIndexOf("with source")-4);
                      
                        for (int j = 0; j < all_sub_process.size(); j++) 
                        {
                             if(t.equals(all_sub_process.get(j)))
                            {
                                for (int k = 0; k < allEntits.size(); k++)
                                {
                                    if(t2.equals(allEntits.get(k)))
                                    {
                          DefaultEdge edge0 = new DefaultEdge();
                          edge0.setSource(subProcessCells.get(j).getChildAt(0));
		          edge0.setTarget(entityCells.get(k).getChildAt(0));
                          GraphConstants.setLineEnd(edge0.getAttributes(), GraphConstants.ARROW_TECHNICAL);
                          GraphConstants.setEndFill(edge0.getAttributes(), true);
                          subProcessCells.add(edge0); 
                          break;
                                    }
                                }
		   
                          }     
                        }
                    }
                 }
                
                 //Link Process To Another Process processToprocess
                if(!processToprocess.isEmpty())
                {
                    for(int i=0;i<processToprocess.size();i++)
                    {
                    //process 1
                     String p1=processToprocess.get(i).substring(0,processToprocess.get(i).indexOf("=")).trim();
                     //process 2
                     String p2=processToprocess.get(i).substring(processToprocess.get(i).indexOf(">")+1,processToprocess.get(i).lastIndexOf("with source")-4).trim();
                    
                     for(int k=0;k<allProcess.size();k++)
                     {
                      if(p1.equals(allProcess.get(k)))
                       {
                        for (int j = 0; j < allProcess.size(); j++)
                        {
                          
                            if(p2.equals(allProcess.get(j)))
                            {
                           //System.out.println("Linking Process Now "+processCells.length+" "+procCount);
                           DefaultEdge edge0 = new DefaultEdge();
		           edge0.setSource(processCells.get(k).getChildAt(0));
		           edge0.setTarget(processCells.get(j).getChildAt(0));
                           GraphConstants.setLineEnd(edge0.getAttributes(), GraphConstants.ARROW_TECHNICAL);
                           GraphConstants.setEndFill(edge0.getAttributes(), true);
                           processCells.add( edge0); 
                          
                           break;
                          }
                         }
                            
                        }
                     
                    }
                    
                 }
                }                
                 //Link sub Process To Another sub Process processToprocess
                if(!processToprocess1.isEmpty())
                {
                    for(int i=0;i<processToprocess1.size();i++)
                    {
                    //process 1
                     String p1=processToprocess1.get(i).substring(0,processToprocess1.get(i).indexOf("=")).trim();
                     //process 2
                     String p2=processToprocess1.get(i).substring(processToprocess1.get(i).indexOf(">")+1,processToprocess1.get(i).lastIndexOf("with source")-4).trim();
                    
                     for(int k=0;k<all_sub_process.size();k++)
                     {
                      if(p1.equals(all_sub_process.get(k)))
                       {
                        for (int j = 0; j < all_sub_process.size(); j++)
                        {
                          
                            if(p2.equals(all_sub_process.get(j)))
                            {
                           //System.out.println("Linking Process Now "+processCells.length+" "+procCount);
                           DefaultEdge edge0 = new DefaultEdge();
		           edge0.setSource(subProcessCells.get(k).getChildAt(0));
		           edge0.setTarget(subProcessCells.get(j).getChildAt(0));
                           GraphConstants.setLineEnd(edge0.getAttributes(), GraphConstants.ARROW_TECHNICAL);
                           GraphConstants.setEndFill(edge0.getAttributes(), true);
                           subProcessCells.add( edge0); 
                           break;
                          }
                         }
                            
                        }
                     
                    }
                    
                 }
                }          
                
                
                //input from entity to  Parent process
                if(!entityToProcess.isEmpty())
                {
                for (int i = 0; i <entityToProcess.size(); i++) 
                    {
                String t=entityToProcess.get(i).substring(0,entityToProcess.get(i).indexOf("="));
                String t2=entityToProcess.get(i).substring(entityToProcess.get(i).indexOf(">")+1,entityToProcess.get(i).lastIndexOf("with source")-4);
                      
                        for (int j = 0; j < allEntits.size(); j++) 
                        {
                             if(t.equals(allEntits.get(j)))
                            {
                                for (int k = 0; k < allProcess.size(); k++)
                                {
                                    if(t2.equals(allProcess.get(k)))
                                    {
                          DefaultEdge edge0 = new DefaultEdge();
                          edge0.setSource(entityCells.get(j).getChildAt(0));
		          edge0.setTarget(processCells.get(k).getChildAt(0));
                          GraphConstants.setLineEnd(edge0.getAttributes(), GraphConstants.ARROW_TECHNICAL);
                          GraphConstants.setEndFill(edge0.getAttributes(), true);
                          entityCells.add(edge0); 
                          break;
                                    }
                                }
		   
                          }     
                        }
                    }
                 }
                
                //output from  Parent process To entity
                if(!processToEntity.isEmpty())
                {
                for (int i = 0; i <processToEntity.size(); i++) 
                    {
                        //Process
                String t=processToEntity.get(i).substring(0,processToEntity.get(i).indexOf("="));
                  //entity
                String t2=processToEntity.get(i).substring(processToEntity.get(i).indexOf(">")+1,processToEntity.get(i).lastIndexOf("with source")-4);
                      
                        for (int j = 0; j < allProcess.size(); j++) 
                        {
                             if(t.equals(allProcess.get(j)))
                            {
                                for (int k = 0; k < allEntits.size(); k++)
                                {
                                    if(t2.equals(allEntits.get(k)))
                                    {
                          DefaultEdge edge0 = new DefaultEdge();
                          edge0.setSource(processCells.get(j).getChildAt(0));
		          edge0.setTarget(entityCells.get(k).getChildAt(0));
                          GraphConstants.setLineEnd(edge0.getAttributes(), GraphConstants.ARROW_TECHNICAL);
                          GraphConstants.setEndFill(edge0.getAttributes(), true);
                          processCells.add(edge0); 
                          break;
                                    }
                                }
                          }     
                        }
                    }
                 }
                
		// Insert the cells via the cache, so they get selected
                graph.getGraphLayoutCache().insert(dataStorage.toArray());
		graph.getGraphLayoutCache().insert(processCells.toArray());
                graph.getGraphLayoutCache().insert(entityCells.toArray());
                graph.getGraphLayoutCache().insert(subProcessCells.toArray());
                
                JPanel southPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
                
                 southPanel.add(save);
                 southPanel.add(open);
        
                JPanel allPanel=new JPanel(new BorderLayout());
                allPanel.add(dataFlow,BorderLayout.NORTH);
                allPanel.add(new JScrollPane(graph),BorderLayout.CENTER);
                allPanel.add(southPanel,BorderLayout.SOUTH);
        
                allPanel.setBackground(Color.RED);
                dataFlow.setForeground(Color.WHITE);
        
      

		// Show in Frame
		
		getContentPane().add(allPanel);
                setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		pack();
                setSize(500,500);
		setVisible(true);
		
		graph.setSelectionCell(processCells.get(0));//deselecting the cells emphasis makes it easier to see their shape
         
                open.addActionListener(new ActionListener() {
           
             private BufferedImage image=null;
             
            @Override
            public void actionPerformed(ActionEvent e) {
              FileDialog fileDialog=new FileDialog(new Frame(),"Save Image",FileDialog.LOAD);
                
              fileDialog.show();  
              String fileName=fileDialog.getDirectory()+fileDialog.getFile();
              
              new LevelZero(graph,allPanel,fileName);
     
            }
        });
                
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

	public static DefaultGraphCell createVertex(double x,
			double y, double w, double h, Color bg, boolean raised, DefaultGraphCell cell, String viewClass) {
		
		// set the view class (indirection for the renderer and the editor)
		if (viewClass != null)
			GPCellViewFactory.setViewClass(cell.getAttributes(), viewClass);

		// Set bounds
		GraphConstants.setBounds(cell.getAttributes(), new Rectangle2D.Double(
				x, y, w, h));

		// Set fill color
		if (bg != null) {
			GraphConstants.setGradientColor(cell.getAttributes(), bg);
			GraphConstants.setOpaque(cell.getAttributes(), true);
		}

		// Set raised border
		if (raised)
			GraphConstants.setBorder(cell.getAttributes(), BorderFactory
					.createRaisedBevelBorder());
		else
			// Set black border
			GraphConstants.setBorderColor(cell.getAttributes(), Color.black);

		// Add a Floating Port
		cell.addPort();

		return cell;
	}

}