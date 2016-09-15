/*
 * To change null license header, choose License Headers in Project Properties.
 * To change null template file, choose Tools | Templates
 * and open the template in the editor.
 */

package LevelsOfDataFlowDiagram;

/**
 *
 * @author salah atwa
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

class DrawPanel extends JPanel 
{
    int numOfEntits=0;
    
   ArrayList<String>  inpuntsource=new ArrayList<String>();
   ArrayList<String>  outputSource=new ArrayList<String>();
    
   ArrayList<String>  enitisName=new ArrayList<String>();
    
    String processName="";
    
    ArrayList<String> processNames=new ArrayList<String>();
    ArrayList<String> dataStorage=new ArrayList<String>();
    ArrayList<String> dataStorageInputs=new ArrayList<String>();
    ArrayList<String> dataStorageOutputs=new ArrayList<String>();
    final int CONTEXT=0;
    final int LEVEL0=1;
    final int LEVEL1=2;
    int tybeOfLevel=0;
    
    int determinedProcess=1;
    
    BufferedImage image=null;
    
 
    public DrawPanel(int numOfEntits,ArrayList<String> enitisName,String processName,ArrayList<String> inputSource,ArrayList<String> outputSource)
    {
        this.numOfEntits=numOfEntits;
        this.enitisName=enitisName;
        this.processName=processName;
        this.inpuntsource=inputSource;
        this.outputSource=outputSource;    
        tybeOfLevel=0;
     
    }
    
    public DrawPanel(JPanel panel,JPanel allPanels,String fileName)
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

    private void doDrawing(Graphics g) 
    {
        
        
        Graphics2D g2d = (Graphics2D) g;
      
        if(numOfEntits==1)
        {
         
               g2d.drawRect(170, 255, 90, 60);
               g2d.drawString(enitisName.get(0), 170+15, 255+20);
               
                for (int i = 0; i < inpuntsource.size(); i++)
               {
                   if(enitisName.get(0).equals(inpuntsource.get(i).substring(0,inpuntsource.get(i).indexOf("="))))
                    {
               
                      g2d.drawLine(190, 255, 190, 200);
                      g2d.drawString(inpuntsource.get(i).substring(inpuntsource.get(i).indexOf(">")+1),170, 240);
                 ImageIcon imageIcon5= new ImageIcon("src/image/op2.png");
               Image image5 = imageIcon5.getImage();
               g2d.drawImage(image5,183,200, null);
               break;
              
                    }
    
               }
                 for (int j = 0; j<outputSource.size(); j++)
                  {
                   if(enitisName.get(0).equals(outputSource.get(j).substring(0,outputSource.get(j).indexOf("="))))
                    {
                        g2d.drawLine(240, 255, 240, 200);
                          g2d.drawString(outputSource.get(j).substring(outputSource.get(j).indexOf(">")+1),240+3, 240-5);
                        ImageIcon imageIcon7= new ImageIcon("src/image/op3.png");
                        Image image7= imageIcon7.getImage();
                        g2d.drawImage(image7,233,239, null);
                        break;
                    }
                   }

         
         //Drawing Process
                 if(tybeOfLevel==0)
                 {
                  g2d.drawRoundRect(170, 135, 90, 60,30,30);
                  ImageIcon imageIcon55 = new ImageIcon("src/image/context.png");
                  Image image35 = imageIcon55.getImage();
                  g2d.drawImage(image35,170,130, null);
                  g2d.drawString(processName, 170+10, 135+25);
                 }
                 else if(tybeOfLevel==1)
                 {
                    processNames.add("salah");
                    processNames.add("shalash");
                     //bottom Process
                  g2d.drawRoundRect(170, 135, 90, 60,30,30);
                  ImageIcon imageIcon55 = new ImageIcon("src/image/process1.png");
                  Image image35 = imageIcon55.getImage();
                  g2d.drawImage(image35,170,130, null);
                  g2d.drawString(processNames.get(0), 170+10, 135+25);
                  
                  //top Process
                  g2d.drawRoundRect(170,40, 90, 60,30,30);
                  ImageIcon imageIcon552 = new ImageIcon("src/image/process2.png");
                  Image image352 = imageIcon552.getImage();
                  g2d.drawImage(image352,170,35, null);
                  g2d.drawString(processNames.get(1), 170+10, 40+25);
                  
                  //Input output from process to another process
                 // Don't Forget Process Input AND Output
                     //from process 1  to process 2
                     ImageIcon imageIcon5152 = new ImageIcon("src/image/processUpRow.png");
                     Image image3152 = imageIcon5152.getImage();
                     g2d.drawImage(image3152,208,104, null);
                     
                      //from process 2  to process 1
                     ImageIcon imageIcon51523 = new ImageIcon("src/image/processDownRow.png");
                     Image image31523 = imageIcon51523.getImage();
                     g2d.drawImage(image31523,208,104, null);
                  
                  
                  
                  
                  dataStorage.add("asdad");
                  dataStorage.add("asdad2");
                  dataStorageInputs.add("asdad");
                 // dataStorageInputs.add("asdad2");
                  dataStorageOutputs.add("1");
                 // dataStorageOutputs.add("2");
                  //dataStorage.add("hjkl");
                  
                  if(!dataStorage.isEmpty())
                  {
                      if(dataStorage.size()==1)
                      {
                          //left data Storage
                       ImageIcon imageIcon55222= new ImageIcon("src/image/storage1.png");
                       Image image35222 = imageIcon55222.getImage();
                       g2d.drawImage(image35222,10,40, null);
                       g2d.drawString(dataStorage.get(0), 44, 57);
                       
                       //dataStorage Sources Input Output
                          for (int i = 0; i < dataStorageInputs.size(); i++)
                          {
                             //Process1 To Data Storage LeftRow
                             if(dataStorage.get(0).equals("asd1ad")&&determinedProcess==2)
                             {
                                ImageIcon imageIcon55272= new ImageIcon("src/image/leftRow.png");
                                 Image image35282 = imageIcon55272.getImage();
                                 g2d.drawImage(image35282,118,45, null);  
                                 break;
                             }
                             //Process2 To Data Storage upRow
                             else if(dataStorage.get(0).equals("asd1ad")&&determinedProcess==1)
                             {
                                 ImageIcon imageIcon55272= new ImageIcon("src/image/upRow.png");
                                 Image image35282 = imageIcon55272.getImage();
                                 g2d.drawImage(image35282,68,67, null);
                                 break;
                             }
                          }
                          
                          //data storage outputs
                          for (int i = 0; i < dataStorageOutputs.size(); i++)
                          {
                               //Data Storage To Process Right Row
                             if(dataStorage.get(0).equals("asd1ad")&&determinedProcess==2)
                             {
                                 ImageIcon imageIcon55272= new ImageIcon("src/image/rightRow.png");
                                 Image image35282 = imageIcon55272.getImage();
                                 g2d.drawImage(image35282,118,45, null);
                                 break;
                             }
                             else if(dataStorage.get(0).equals("asdad")&&determinedProcess==1)
                             {
                                ImageIcon imageIcon55272= new ImageIcon("src/image/leftDownRow.png");
                                 Image image35282 = imageIcon55272.getImage();
                                 g2d.drawImage(image35282,55,67, null);   
                                 break;
                             }
                          }       
                      }
                      else if(dataStorage.size()==2)
                      {
                         
                       //left data storage
                       ImageIcon imageIcon5572= new ImageIcon("src/image/storage1.png");
                       Image image3572 = imageIcon5572.getImage();
                       g2d.drawImage(image3572,10,40, null);
                       g2d.drawString(dataStorage.get(0), 44, 57);
                       
                            
                        //right data storage
                       ImageIcon imageIcon5522= new ImageIcon("src/image/storage2.png");
                       Image image3522 = imageIcon5522.getImage();
                       g2d.drawImage(image3522,315,40, null);
                       g2d.drawString(dataStorage.get(1), 350, 57);
                       
                       //dataStorage Sources Input Output
                          for (int i = 0; i < dataStorageInputs.size(); i++)
                          {
                             
                             //Process To Data Storage LeftRow Left DataStorage
                             if(dataStorage.get(i).equals("a1sdad")&&determinedProcess==1)
                             {
                                ImageIcon imageIcon55272= new ImageIcon("src/image/leftRow.png");
                                 Image image35282 = imageIcon55272.getImage();
                                 g2d.drawImage(image35282,118,45, null);  
                                 break;
                             }
                             //Process To Data Storage LeftRow  Right DataStorage
                             else if(dataStorage.get(i).equals("asdad2")&&determinedProcess==2)
                             {  System.out.println("asfa");
                                ImageIcon imageIcon55272= new ImageIcon("src/image/rightRow.png");
                                 Image image35282 = imageIcon55272.getImage();
                                 g2d.drawImage(image35282,264,45, null);  
                                 break;
                             }
                             
                             else if(dataStorage.get(i).equals("asd2ad")&&determinedProcess==1)
                             {
                                ImageIcon imageIcon55272= new ImageIcon("src/image/upRow.png");
                                 Image image35282 = imageIcon55272.getImage();
                                 g2d.drawImage(image35282,68,67, null); 
                                 break;
                             }
                             //Process To Data Storage LeftRow  Right DataStorage
                             else if(dataStorage.get(i).equals("asdad1")&&determinedProcess==1)
                             {  //System.out.println("asfa");
                                ImageIcon imageIcon55272= new ImageIcon("src/image/leftUpRow.png");
                                 Image image35282 = imageIcon55272.getImage();
                                 g2d.drawImage(image35282,264,70, null);  
                                break;
                             }
                          }
                       
                          
                    
                       
                       
                       //dataStorage Sources  Outputs
                          for (int i = 0; i < dataStorageOutputs.size(); i++)
                          {
                             //Data Storage To Process Right Row
                            if(dataStorage.get(i).equals("asdad")&&determinedProcess==2)
                             {
                                 ImageIcon imageIcon55272= new ImageIcon("src/image/rightRow.png");
                                 Image image35282 = imageIcon55272.getImage();
                                 g2d.drawImage(image35282,118,45, null);
                                 break;
                             }
                              //Data Storage To Process Right Row
                            else if(dataStorage.get(i).equals("asdad2")&&determinedProcess==2)
                             {
                                 ImageIcon imageIcon55272= new ImageIcon("src/image/leftRow.png");
                                 Image image35282 = imageIcon55272.getImage();
                                 g2d.drawImage(image35282,264,45, null);
                                 break;
                             }
                             else if(dataStorage.get(i).equals("asdad1")&&determinedProcess==1)
                             {
                                ImageIcon imageIcon55272= new ImageIcon("src/image/leftDownRow.png");
                                 Image image35282 = imageIcon55272.getImage();
                                 g2d.drawImage(image35282,55,67, null);   
                                 break;
                             }
                             else if(dataStorage.get(i).equals("asdad")&&determinedProcess==1)
                             {
                                ImageIcon imageIcon55272= new ImageIcon("src/image/rightDownRow.png");
                                 Image image35282 = imageIcon55272.getImage();
                                 g2d.drawImage(image35282,270,67, null);  
                                 break;
                             }
                         
                             
                          }
                       
                       
                      }
                  }
                  
                  
                     
                 }
        }
        else if(numOfEntits==2)
        {
           
           g2d.drawRect(10, 15, 90, 60);
              g2d.drawString(enitisName.get(0), 10+15, 15+20);
              
               for (int i = 0; i < inpuntsource.size(); i++)
               {
                   if(enitisName.get(0).equals(inpuntsource.get(i).substring(0,inpuntsource.get(i).indexOf("="))))
                    {
                        
              g2d.drawLine(10+90-10,15+60,10+90-10, 135+60-50);
              g2d.drawLine(10+90-10,135+60-50,170, 135+10);
              g2d.drawString(inpuntsource.get(i).substring(inpuntsource.get(i).indexOf(">")+1),10+90-10+5,135+60-50);
              
              
              ImageIcon imageIcon = new ImageIcon("src/image/op.png");
              Image image = imageIcon.getImage();
              g2d.drawImage(image,155,  140, null);
              break;
                    }
              else
              {
//              ImageIcon imageIcon2= new ImageIcon("src/image/op2.png");
//              Image image2 = imageIcon2.getImage();
//              g2d.drawImage(image2,10+73, 15+61, null);
              }  
            }
                   
//                   if(h=="en<p")
//              {
                  for (int j = 0; j<outputSource.size(); j++)
                  {
                   if(enitisName.get(0).equals(outputSource.get(j).substring(0,outputSource.get(j).indexOf("="))))
                    {
                      g2d.drawLine(10+40-10,15+60,10+40-10, 135+100-50);
                      g2d.drawLine(10+40-10,135+100-50,160, 135+50);
                      g2d.drawString(outputSource.get(j).substring(outputSource.get(j).indexOf(">")+1),10+40-10+5,135+100-50);
                      ImageIcon imageIcon3 = new ImageIcon("src/image/op2.png");
                      Image image3 = imageIcon3.getImage();
                      g2d.drawImage(image3,10+23,  15+61, null);
                    }
                  }
//              g2d.drawLine(10+40-10,15+60,10+40-10, 135+100-50);
//              g2d.drawLine(10+40-10,135+100-50,160, 135+50);
//              
//               if(h=="en<p")
//              {
//               ImageIcon imageIcon3 = new ImageIcon("D:\\image\\op2.png");
//              Image image3 = imageIcon3.getImage();
//              g2d.drawImage(image3,10+23,  15+61, null);
//              }
//               else
//               {
//              ImageIcon imageIcon4= new ImageIcon("D:\\image\\op.png");
//              Image image4 = imageIcon4.getImage();
//              g2d.drawImage(image4,155, 135+45, null);
//               }
//              }
                    
                 
             
              
              
              
              
              
              //##########################################################
               
               g2d.drawRect(330, 15, 90, 60);
               g2d.drawString(enitisName.get(1), 330+15, 15+20);
               
                for (int i = 0; i < inpuntsource.size(); i++)
               {
                   if(enitisName.get(1).equals(inpuntsource.get(i).substring(0,inpuntsource.get(i).indexOf("="))))
                    {
               
                g2d.drawLine(330+90-70,15+60,330+90-70,135+60-50);
                g2d.drawLine(330+90-70,135+60-50,270, 135+100-90);
                
                g2d.drawString(inpuntsource.get(i).substring(inpuntsource.get(i).indexOf(">")+1),270+9, 135+100-90);
                ImageIcon imageIcon44= new ImageIcon("src/image/op4.png");
                Image image44 = imageIcon44.getImage();
                g2d.drawImage(image44,262, 135+100-97, null);
                break;
                    }
                   else
                   {
// ImageIcon imageIcon33 = new ImageIcon("src/image/op2.png");
//              Image image33 = imageIcon33.getImage();
//              g2d.drawImage(image33,330+90-77,15+61, null);
                   }
               }
                
                 for (int j = 0; j<outputSource.size(); j++)
                  {
                   if(enitisName.get(1).equals(outputSource.get(j).substring(0,outputSource.get(j).indexOf("="))))
                    {
                        g2d.drawLine(330+90-10,15+60,330+90-10,135+50);
                        g2d.drawLine(330+90-10,135+50,270, 135+100-90+40);
                        g2d.drawString(outputSource.get(j).substring(outputSource.get(j).indexOf(">")+1),270+9, 135+100-90+40);
                        ImageIcon imageIcon333 = new ImageIcon("src/image/op2.png");
                        Image image333 = imageIcon333.getImage();
                        g2d.drawImage(image333,330+90-16,15+61, null);
                        break;
                    }
                  }
//                g2d.drawLine(330+90-10,15+60,330+90-10,135+50);
//                g2d.drawLine(330+90-10,135+50,270, 135+100-90+40);
//                
//                
//                ImageIcon imageIcon333 = new ImageIcon("D:\\image\\op2.png");
//              Image image333 = imageIcon333.getImage();
//              g2d.drawImage(image333,330+90-16,15+61, null);
//              
//              ImageIcon imageIcon444= new ImageIcon("D:\\image\\op4.png");
//              Image image444= imageIcon444.getImage();
//              g2d.drawImage(image444,263, 135+100-90+34, null);
              
               
               
            
            //Drawing Process
            
         g2d.drawRoundRect(170, 135, 90, 60,30,30);
         ImageIcon imageIcon55 = new ImageIcon("src/image/context.png");
         Image image35 = imageIcon55.getImage();
         g2d.drawImage(image35,170,130, null);
         g2d.drawString(processName, 170+10, 135+25);
        }
        else if(numOfEntits==3)
        {
              
              g2d.drawRect(10, 15, 90, 60);
              g2d.drawString(enitisName.get(0), 10+15, 15+20);
              
               for (int i = 0; i < inpuntsource.size(); i++)
               {
                   if(enitisName.get(0).equals(inpuntsource.get(i).substring(0,inpuntsource.get(i).indexOf("="))))
                    {
                        
              g2d.drawLine(10+90-10,15+60,10+90-10, 135+60-50);
              g2d.drawLine(10+90-10,135+60-50,170, 135+10);
              g2d.drawString(inpuntsource.get(i).substring(inpuntsource.get(i).indexOf(">")+1),10+90-10+5,135+60-50);
              
              
              ImageIcon imageIcon = new ImageIcon("src/image/op.png");
              Image image = imageIcon.getImage();
              g2d.drawImage(image,155,  140, null);
              break;
                    }
              else
              {
//              ImageIcon imageIcon2= new ImageIcon("src/image/op2.png");
//              Image image2 = imageIcon2.getImage();
//              g2d.drawImage(image2,10+73, 15+61, null);
              }  
            }
                   
//                   if(h=="en<p")
//              {
                  for (int j = 0; j<outputSource.size(); j++)
                  {
                   if(enitisName.get(0).equals(outputSource.get(j).substring(0,outputSource.get(j).indexOf("="))))
                    {
                      g2d.drawLine(10+40-10,15+60,10+40-10, 135+100-50);
                      g2d.drawLine(10+40-10,135+100-50,160, 135+50);
                      g2d.drawString(outputSource.get(j).substring(outputSource.get(j).indexOf(">")+1),10+40-10+5,135+100-50);
                      ImageIcon imageIcon3 = new ImageIcon("src/image/op2.png");
                      Image image3 = imageIcon3.getImage();
                      g2d.drawImage(image3,10+23,  15+61, null);
                    }
                  }
//              g2d.drawLine(10+40-10,15+60,10+40-10, 135+100-50);
//              g2d.drawLine(10+40-10,135+100-50,160, 135+50);
//              
//               if(h=="en<p")
//              {
//               ImageIcon imageIcon3 = new ImageIcon("D:\\image\\op2.png");
//              Image image3 = imageIcon3.getImage();
//              g2d.drawImage(image3,10+23,  15+61, null);
//              }
//               else
//               {
//              ImageIcon imageIcon4= new ImageIcon("D:\\image\\op.png");
//              Image image4 = imageIcon4.getImage();
//              g2d.drawImage(image4,155, 135+45, null);
//               }
//              }
                    
                 
             
              
              
              
              
              
              //##########################################################
               
               g2d.drawRect(330, 15, 90, 60);
               g2d.drawString(enitisName.get(1), 330+15, 15+20);
               
                for (int i = 0; i < inpuntsource.size(); i++)
               {
                   if(enitisName.get(1).equals(inpuntsource.get(i).substring(0,inpuntsource.get(i).indexOf("="))))
                    {
               
                g2d.drawLine(330+90-70,15+60,330+90-70,135+60-50);
                g2d.drawLine(330+90-70,135+60-50,270, 135+100-90);
                
                g2d.drawString(inpuntsource.get(i).substring(inpuntsource.get(i).indexOf(">")+1),270+9, 135+100-90);
                ImageIcon imageIcon44= new ImageIcon("src/image/op4.png");
                Image image44 = imageIcon44.getImage();
                g2d.drawImage(image44,262, 135+100-97, null);
                break;
                    }
                   else
                   {
// ImageIcon imageIcon33 = new ImageIcon("src/image/op2.png");
//              Image image33 = imageIcon33.getImage();
//              g2d.drawImage(image33,330+90-77,15+61, null);
                   }
               }
                
                 for (int j = 0; j<outputSource.size(); j++)
                  {
                   if(enitisName.get(1).equals(outputSource.get(j).substring(0,outputSource.get(j).indexOf("="))))
                    {
                        g2d.drawLine(330+90-10,15+60,330+90-10,135+50);
                        g2d.drawLine(330+90-10,135+50,270, 135+100-90+40);
                        g2d.drawString(outputSource.get(j).substring(outputSource.get(j).indexOf(">")+1),270+9, 135+100-90+40);
                        ImageIcon imageIcon333 = new ImageIcon("src/image/op2.png");
                        Image image333 = imageIcon333.getImage();
                        g2d.drawImage(image333,330+90-16,15+61, null);
                        break;
                    }
                  }
//                g2d.drawLine(330+90-10,15+60,330+90-10,135+50);
//                g2d.drawLine(330+90-10,135+50,270, 135+100-90+40);
//                
//                
//                ImageIcon imageIcon333 = new ImageIcon("D:\\image\\op2.png");
//              Image image333 = imageIcon333.getImage();
//              g2d.drawImage(image333,330+90-16,15+61, null);
//              
//              ImageIcon imageIcon444= new ImageIcon("D:\\image\\op4.png");
//              Image image444= imageIcon444.getImage();
//              g2d.drawImage(image444,263, 135+100-90+34, null);
              
               
               
              //###########################################################
               g2d.drawRect(170, 255, 90, 60);
               g2d.drawString(enitisName.get(2), 170+15, 255+20);
               
                for (int i = 0; i < inpuntsource.size(); i++)
               {
                   if(enitisName.get(2).equals(inpuntsource.get(i).substring(0,inpuntsource.get(i).indexOf("="))))
                    {
               
               g2d.drawLine(190, 255, 190, 200);
               g2d.drawString(inpuntsource.get(i).substring(inpuntsource.get(i).indexOf(">")+1),170, 240);
               ImageIcon imageIcon5= new ImageIcon("src/image/op2.png");
               Image image5 = imageIcon5.getImage();
               g2d.drawImage(image5,183,200, null);
               break;
              
                    }
                   else
                   {
//                        ImageIcon imageIcon6= new ImageIcon("src/image/op3.png");
//                        Image image6 = imageIcon6.getImage();
//                        g2d.drawImage(image6,182,239, null);
               
                   }
               }
                 for (int j = 0; j<outputSource.size(); j++)
                  {
                   if(enitisName.get(2).equals(outputSource.get(j).substring(0,outputSource.get(j).indexOf("="))))
                    {
                        g2d.drawLine(240, 255, 240, 200);
                          g2d.drawString(outputSource.get(j).substring(outputSource.get(j).indexOf(">")+1),240+3, 240-5);
                        ImageIcon imageIcon7= new ImageIcon("src/image/op3.png");
                        Image image7= imageIcon7.getImage();
                        g2d.drawImage(image7,233,239, null);
                        break;
                    }
                   }
//               g2d.drawLine(240, 255, 240, 200);
//               
//               ImageIcon imageIcon8= new ImageIcon("D:\\image\\op2.png");
//               Image image8 = imageIcon8.getImage();
//               g2d.drawImage(image8,233,200, null);
//               
//               ImageIcon imageIcon7= new ImageIcon("D:\\image\\op3.png");
//               Image image7= imageIcon7.getImage();
//               g2d.drawImage(image7,233,239, null);
               
               
               
               
                        //Drawing Process
         
          g2d.drawRoundRect(170, 135, 90, 60,30,30);
         ImageIcon imageIcon55 = new ImageIcon("src/image/context.png");
         Image image35 = imageIcon55.getImage();
         g2d.drawImage(image35,170,130, null);
         g2d.drawString(processName, 170+10, 135+25);
        }
        else if(numOfEntits==4)
        {
               
               g2d.drawRect(10, 15, 90, 60);
              g2d.drawString(enitisName.get(0), 10+15, 15+20);
              
               for (int i = 0; i < inpuntsource.size(); i++)
               {
                   if(enitisName.get(0).equals(inpuntsource.get(i).substring(0,inpuntsource.get(i).indexOf("="))))
                    {
                        
              g2d.drawLine(10+90-10,15+60,10+90-10, 135+60-50);
              g2d.drawLine(10+90-10,135+60-50,170, 135+10);
              g2d.drawString(inpuntsource.get(i).substring(inpuntsource.get(i).indexOf(">")+1),10+90-10+5,135+60-50);
              
              
              ImageIcon imageIcon = new ImageIcon("src/image/op.png");
              Image image = imageIcon.getImage();
              g2d.drawImage(image,155,  140, null);
              break;
                    }
              else
              {
//              ImageIcon imageIcon2= new ImageIcon("src/image/op2.png");
//              Image image2 = imageIcon2.getImage();
//              g2d.drawImage(image2,10+73, 15+61, null);
              }  
            }
                   
//                   if(h=="en<p")
//              {
                  for (int j = 0; j<outputSource.size(); j++)
                  {
                   if(enitisName.get(0).equals(outputSource.get(j).substring(0,outputSource.get(j).indexOf("="))))
                    {
                      g2d.drawLine(10+40-10,15+60,10+40-10, 135+100-50);
                      g2d.drawLine(10+40-10,135+100-50,160, 135+50);
                      g2d.drawString(outputSource.get(j).substring(outputSource.get(j).indexOf(">")+1),10+40-10+5,135+100-50);
                      ImageIcon imageIcon3 = new ImageIcon("src/image/op2.png");
                      Image image3 = imageIcon3.getImage();
                      g2d.drawImage(image3,10+23,  15+61, null);
                    }
                  }
//              g2d.drawLine(10+40-10,15+60,10+40-10, 135+100-50);
//              g2d.drawLine(10+40-10,135+100-50,160, 135+50);
//              
//               if(h=="en<p")
//              {
//               ImageIcon imageIcon3 = new ImageIcon("D:\\image\\op2.png");
//              Image image3 = imageIcon3.getImage();
//              g2d.drawImage(image3,10+23,  15+61, null);
//              }
//               else
//               {
//              ImageIcon imageIcon4= new ImageIcon("D:\\image\\op.png");
//              Image image4 = imageIcon4.getImage();
//              g2d.drawImage(image4,155, 135+45, null);
//               }
//              }
                    
                 
             
              
              
              
              
              
              //##########################################################
               
               g2d.drawRect(330, 15, 90, 60);
               g2d.drawString(enitisName.get(1), 330+15, 15+20);
               
                for (int i = 0; i < inpuntsource.size(); i++)
               {
                   if(enitisName.get(1).equals(inpuntsource.get(i).substring(0,inpuntsource.get(i).indexOf("="))))
                    {
               
                g2d.drawLine(330+90-70,15+60,330+90-70,135+60-50);
                g2d.drawLine(330+90-70,135+60-50,270, 135+100-90);
                
                g2d.drawString(inpuntsource.get(i).substring(inpuntsource.get(i).indexOf(">")+1),270+9, 135+100-90);
                ImageIcon imageIcon44= new ImageIcon("src/image/op4.png");
                Image image44 = imageIcon44.getImage();
                g2d.drawImage(image44,262, 135+100-97, null);
                break;
                    }
                   else
                   {
// ImageIcon imageIcon33 = new ImageIcon("src/image/op2.png");
//              Image image33 = imageIcon33.getImage();
//              g2d.drawImage(image33,330+90-77,15+61, null);
                   }
               }
                
                 for (int j = 0; j<outputSource.size(); j++)
                  {
                   if(enitisName.get(1).equals(outputSource.get(j).substring(0,outputSource.get(j).indexOf("="))))
                    {
                        g2d.drawLine(330+90-10,15+60,330+90-10,135+50);
                        g2d.drawLine(330+90-10,135+50,270, 135+100-90+40);
                        g2d.drawString(outputSource.get(j).substring(outputSource.get(j).indexOf(">")+1),270+9, 135+100-90+40);
                        ImageIcon imageIcon333 = new ImageIcon("src/image/op2.png");
                        Image image333 = imageIcon333.getImage();
                        g2d.drawImage(image333,330+90-16,15+61, null);
                        break;
                    }
                  }
//                g2d.drawLine(330+90-10,15+60,330+90-10,135+50);
//                g2d.drawLine(330+90-10,135+50,270, 135+100-90+40);
//                
//                
//                ImageIcon imageIcon333 = new ImageIcon("D:\\image\\op2.png");
//              Image image333 = imageIcon333.getImage();
//              g2d.drawImage(image333,330+90-16,15+61, null);
//              
//              ImageIcon imageIcon444= new ImageIcon("D:\\image\\op4.png");
//              Image image444= imageIcon444.getImage();
//              g2d.drawImage(image444,263, 135+100-90+34, null);
              
               
            //###############################################################  
               g2d.drawRect(10, 255, 90, 60);
               g2d.drawString(enitisName.get(2), 10+15, 255+20);
               
               for (int i = 0; i < inpuntsource.size(); i++)
               {
                   if(enitisName.get(2).equals(inpuntsource.get(i).substring(0,inpuntsource.get(i).indexOf("="))))
                    {
               g2d.drawLine(100, 260, 178,260);
               g2d.drawLine(178,260, 178,200);
               
               ImageIcon imageIcon333 = new ImageIcon("src/image/op2.png");
               Image image333 = imageIcon333.getImage();
               g2d.drawImage(image333,172,200, null);
                    break;
                    }
               }
               
                 for (int j = 0; j<outputSource.size(); j++)
                  {
                   if(enitisName.get(2).equals(outputSource.get(j).substring(0,outputSource.get(j).indexOf("="))))
                    {
               g2d.drawLine(200,200, 200,310);
               g2d.drawLine(200,310, 100,310);
               
               ImageIcon imageIcon3333 = new ImageIcon("src/image/op4.png");
               Image image3333 = imageIcon3333.getImage();
               g2d.drawImage(image3333,100+1,303, null);
               break;
                    }
                  }
               
     //#########################################################################3          
               
               g2d.drawRect(330, 255, 90, 60);
               g2d.drawString(enitisName.get(3), 330+15, 255+20);
               
               for (int i = 0; i < inpuntsource.size(); i++)
               {
                   if(enitisName.get(3).equals(inpuntsource.get(i).substring(0,inpuntsource.get(i).indexOf("="))))
                    {
               g2d.drawLine(330,265,252,265);
               g2d.drawLine(252,265,252,200);
               
               ImageIcon imageIcon3 = new ImageIcon("src/image/op2.png");
               Image image32 = imageIcon3.getImage();
               g2d.drawImage(image32,245,200, null);
               
               break;
                    }
               }
               
                for (int j = 0; j<outputSource.size(); j++)
                  {
                   if(enitisName.get(3).equals(outputSource.get(j).substring(0,outputSource.get(j).indexOf("="))))
                    {
               g2d.drawLine(225,200, 225,310);
               g2d.drawLine(225,310,330,310);
               
               ImageIcon imageIcon55 = new ImageIcon("src/image/op.png");
               Image image35 = imageIcon55.getImage();
               g2d.drawImage(image35,314,303, null);
               
               break;
                    }
                  }
                        //Drawing Process
        
         g2d.drawRoundRect(170, 135, 90, 60,30,30);
         ImageIcon imageIcon55 = new ImageIcon("src/image/context.png");
         Image image35 = imageIcon55.getImage();
         g2d.drawImage(image35,170,130, null);
         g2d.drawString(processName, 170+10, 135+25);
            
        }
        else if(numOfEntits==5)
        {
        
        g2d.drawRect(10, 15, 90, 60);
        g2d.drawString(enitisName.get(0), 10+15, 15+20);

        
        g2d.drawRect(170, 15, 90, 60);
        g2d.drawString(enitisName.get(1), 170+15, 15+20);

       
        g2d.drawRect(330, 15, 90, 60);
        g2d.drawString(enitisName.get(2), 330+15, 15+20);

        
        g2d.drawRect(10, 135, 90, 60);
        g2d.drawString(enitisName.get(3), 10+15, 135+20);
        
        
        g2d.drawRect(330, 135, 90, 60);
        g2d.drawString(enitisName.get(4), 330+15, 135+20);
        
        
                 //Drawing Process
         
         g2d.drawRoundRect(170, 135, 90, 60,30,30);
         g2d.drawString(processName, 170+15, 135+20);
            
        }
        
        else if(numOfEntits==6)
        {
        
        g2d.drawRect(10, 15, 90, 60);
        g2d.drawString(enitisName.get(0), 10+15, 15+20);

      
        g2d.drawRect(170, 15, 90, 60);
        g2d.drawString(enitisName.get(1), 170+15, 15+20);

       
        g2d.drawRect(330, 15, 90, 60);
        g2d.drawString(enitisName.get(2), 330+15, 15+20);
        
        
        
        g2d.drawRect(10, 255, 90, 60);
        g2d.drawString(enitisName.get(3), 10+15, 255+20);

       
        g2d.drawRect(170, 255, 90, 60);
        g2d.drawString(enitisName.get(4), 170+15, 255+20);

       
        g2d.drawRect(330, 255, 90, 60);
        g2d.drawString(enitisName.get(5), 330+15, 255+20);
        
        
                 //Drawing Process
      
         g2d.drawRoundRect(170, 135, 90, 60,30,30);
        g2d.drawString(processName, 170+15, 135+20);
            
        }
        else if(numOfEntits==7)
        {
         
        g2d.drawRect(10, 15, 90, 60);
        g2d.drawString(enitisName.get(0), 10+15, 15+20);

       
        g2d.drawRect(170, 15, 90, 60);
        g2d.drawString(enitisName.get(1), 170+15, 15+20);

       
        g2d.drawRect(330, 15, 90, 60);
        g2d.drawString(enitisName.get(2), 330+15, 15+20);

      
        g2d.drawRect(10, 135, 90, 60);
        g2d.drawString(enitisName.get(3), 10+15, 135+20);
        
       
        g2d.drawRect(10, 255, 90, 60);
        g2d.drawString(enitisName.get(4), 10+15, 255+20);

      
        g2d.drawRect(170, 255, 90, 60);
        g2d.drawString(enitisName.get(5), 170+15, 255+20);
        
        g2d.drawRect(330, 255, 90, 60);
        g2d.drawString(enitisName.get(6), 330+15, 255+20);
        
        
                 //Drawing Process
        
         g2d.drawRoundRect(170, 135, 90, 60,30,30);
         g2d.drawString(processName, 170+15, 135+20);
            
        }
        else if(numOfEntits==8)
        {
       
        g2d.drawRect(10, 15, 90, 60);
        g2d.drawString(enitisName.get(1), 10+15, 15+20);

        
        g2d.drawRect(170, 15, 90, 60);
        g2d.drawString(enitisName.get(1), 170+15, 15+20);

        
        g2d.drawRect(330, 15, 90, 60);
        g2d.drawString(enitisName.get(1), 330+15, 15+20);

       
        g2d.drawRect(10, 135, 90, 60);
        g2d.drawString(enitisName.get(1), 10+15, 135+20);
        
         
        g2d.drawRect(330, 135, 90, 60);
        g2d.drawString(enitisName.get(1), 330+15, 135+20);

        g2d.drawRect(10, 255, 90, 60);
        g2d.drawString(enitisName.get(1), 10+15, 255+20);

        
        g2d.drawRect(170, 255, 90, 60);
        g2d.drawString(enitisName.get(1), 170+15, 255+20);

        
        g2d.drawRect(330, 255, 90, 60);
        g2d.drawString(enitisName.get(1), 330+15, 255+20);
                 //Drawing Process
        
         g2d.drawRoundRect(170, 135, 90, 60,30,30);
         g2d.drawString(processName, 170+15, 135+20);
            
        }

//        g2d.setColor(new Color(125, 167, 116));
//        g2d.drawRect(10, 15, 90, 60);
//
//        g2d.setColor(new Color(42, 179, 231));
//        g2d.drawRect(130, 15, 90, 60);
//
//        g2d.setColor(new Color(70, 67, 123));
//        g2d.drawRect(250, 15, 90, 60);
//
//        g2d.setColor(new Color(130, 100, 84));
//        g2d.drawRect(10, 105, 90, 60);
//
//        g2d.setColor(new Color(252, 211, 61));
//        g2d.drawRect(130, 105, 90, 60);
//
//        g2d.setColor(new Color(241, 98, 69));
//        g2d.drawRect(250, 105, 90, 60);
//
//        g2d.setColor(new Color(217, 146, 54));
//        g2d.drawRect(10, 195, 90, 60);
//
//        g2d.setColor(new Color(63, 121, 186));
//        g2d.drawRect(130, 195, 90, 60);
//
//        g2d.setColor(new Color(31, 21, 1));
//        g2d.drawRect(250, 195, 90, 60);
       // g2d.dispose();
    }

    @Override
    public void paintComponent(Graphics g) 
    {
        
        super.paintComponent(g);
     
        doDrawing(g);
      
        
    }
}

