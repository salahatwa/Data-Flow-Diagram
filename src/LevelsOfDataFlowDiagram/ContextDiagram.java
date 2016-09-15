/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package LevelsOfDataFlowDiagram;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author salah atwa
 */
public class ContextDiagram extends JFrame {
   
    
    
    JLabel dataFlow=new JLabel("  Data Flow Diagram Design");
     
  static public  int numberOfEntits=0;
  static public ArrayList<String>  inpuntsource=new ArrayList<String>();
  static public ArrayList<String>  outputsource=new ArrayList<String>();
    
  public static String processName="";
    
 public static ArrayList<String>  enitisName=new ArrayList<String>();
 
 
    //Menu Item
    JButton save=new JButton("Save");
    JButton  open=new JButton("Open");
    

    public ContextDiagram() 
    {
        initUI();
    }

    public final void initUI() 
    {   
        
        enitisName.add("enitity 1");
        enitisName.add("enitity 2");
        enitisName.add("enitity 3");
        
    
        
        DrawPanel rectsPanel = new DrawPanel(numberOfEntits,enitisName,processName,inpuntsource,outputsource);
        
        rectsPanel.setBackground(Color.WHITE);
        
        JPanel southPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        southPanel.add(save);
        southPanel.add(open);
        
        JPanel allPanel=new JPanel(new BorderLayout());
        allPanel.add(dataFlow,BorderLayout.NORTH);
        allPanel.add(rectsPanel,BorderLayout.CENTER);
        allPanel.add(southPanel,BorderLayout.SOUTH);
        
        allPanel.setBackground(Color.RED);
        dataFlow.setForeground(Color.WHITE);
        
        add(allPanel);
       

        setSize(450, 400);
        setTitle("Painter");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);
        
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
            try {
                FileDialog fileDialog=new FileDialog(new Frame(),"Save Image",FileDialog.SAVE);
                
                fileDialog.show();
             
                
                String fileName=fileDialog.getDirectory()+fileDialog.getFile()+".png";
             
                
                System.out.println(fileName);
                
                    makePanelImage(rectsPanel,fileName);
                } catch (IOException ex) {
                    Logger.getLogger(ContextDiagram.class.getName()).log(Level.SEVERE, null, ex);
                } catch (AWTException ex) {
                    Logger.getLogger(ContextDiagram.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        open.addActionListener(new ActionListener() {
           
             private BufferedImage image=null;
             
            @Override
            public void actionPerformed(ActionEvent e) {
              FileDialog fileDialog=new FileDialog(new Frame(),"Save Image",FileDialog.LOAD);
                
              fileDialog.show();  
              String fileName=fileDialog.getDirectory()+fileDialog.getFile();
              
              new DrawPanel(rectsPanel,allPanel,fileName);
     
            }
        });
    }

      private void makePanelImage(Component panel,String fileName) throws IOException, AWTException
    {
        BufferedImage bi = ScreenImage.createImage(panel);
        ScreenImage.writeImage(bi,fileName);
    }
}
