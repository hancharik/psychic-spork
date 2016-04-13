/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchgrid;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Queue;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author markhancharik
 */
public class Controller implements ActionListener{
    
    SPanel panel;
    Timer timer;
    
    int start = 3;
    
    
    int sx;
    int sy;
    int ex;
    int ey;
    
    boolean goingLeft;
    boolean goingRight;
    boolean goingUp;
    boolean goingDown;
    
    boolean clockwise;
    
    boolean gettingSmaller = false;
    
    ArrayList<Block> path = new ArrayList();
    ArrayList<Block> smoothPoints = new ArrayList();
    
    int nextSmoothPoint = 0;
    
    boolean lenny = true; // otherwise, squiggy... lenny finds a path, squiggy smooths the path
    
    
    int counter = 0;
    
    
    
    public Controller(SPanel s){
        
       panel = s;
       
       
       setup();
       
       
       
       
       
    }
    
    private void setup(){
        
        setStart(start,start);
        setEnd(panel.size-start,panel.size - start);
        //createWalls();
        hookUpButtons();
        timer = new Timer(100, this);
        timer.start();
       // System.out.println("there are " + smoothPoints.size() + " smooth points");
    }
    
    
    
   private void setStart(int a, int b){
            panel.grid[a][b].setBackground(java.awt.Color.green);
            panel.grid[a][b].setForeground(java.awt.Color.black); 
            sx = a;
            sy = b;
            path.add(new Block(a,b));
   } 
   
      private void setEnd(int a, int b){
            panel.grid[a][b].setBackground(java.awt.Color.red);
            panel.grid[a][b].setForeground(java.awt.Color.black); 
            ex = a;
            ey = b;
   } 

      
      
      
      
      
      
      
      private void checkX(){
          
          // go down
            if(sx < ex){
                if(!checkForWall(sx+1,sy)){
                    sx++; 
                    //goingLeft = true;  
                }
                
                 // special condition for when there is a wall below you and the taget is directly beneath you, otherwise you will stop moving
        if(checkForWall(sx+1,sy) && sy == ey){
                   goingLeft = true;  
                }  
                
                
            }  // end go down
            
        // go up    
       if(sx > ex){
          if(!checkForWall(sx-1,sy)){
               sx--;  
           }
       }  // end go up
       
    
       
      } // end check x
      
      
      private void checkY(){
        if(goingLeft && checkForWall(sx+1,sy)){
          
            if(!checkForWall(sx,sy-1)){
               sy--;  
            }
            
        }else{
            
            
            
         // go right   
       if(sy < ey){
            if(!checkForWall(sx,sy+1)){
               sy++;  
           }else{
                if(checkForWall(sx,sy+1) && checkForWall(sx+1,sy)){
               sy--;  
               goingLeft = true;
                }else{
                goingLeft = false;
                }
           } 
       }// end go right
       
       
       
       
       // go left
       if(sy > ey){
           if(!checkForWall(sx,sy-1)){
               sy--;  
           }
         
       }
       
       
       
       
        }// end if else
         
      }  // end check y
      
      
   private void nextStep(int a, int b){
       
     checkX();
     checkY();
       
       
       
            panel.grid[sx][sy].setBackground(java.awt.Color.blue);
            panel.grid[sx][sy].setForeground(java.awt.Color.blue); 
            path.add(new Block(sx,sy));
            if(sx==ex && sy==ey){
               // timer.stop();
               // printPath();
              //  panel.grid[sx][sy].setBackground(java.awt.Color.yellow);
            //panel.grid[sx][sy].setForeground(java.awt.Color.yellow); 
            smooth(0);
            if(nextSmoothPoint!=0){
                smooth(nextSmoothPoint);
            }
            
            setStart(3,3);
        setEnd(smoothPoints.get(0).j,smoothPoints.get(0).g);
            lenny = false;
            path.clear();
            }
       
   } // end next step  
  
   
   
      private void nextStep2(int a, int b){
       
          String ekename = "images/bigskull.gif"; 
        ImageIcon onkeyPic = new ImageIcon(ekename);
        panel.main.zeke.setIcon(onkeyPic);
        //smoothPoints.get(0);
          
        // System.out.println("there are " + smoothPoints.size() + " smooth points");  
          
          
          
          
     checkX();
     checkY();
       
       
       
            panel.grid[sx][sy].setBackground(java.awt.Color.yellow);
            panel.grid[sx][sy].setForeground(java.awt.Color.yellow); 
            path.add(new Block(sx,sy));
            if(sx==ex && sy==ey){
               // timer.stop();
              //  printPath();
      
                if(smoothPoints.size()>0){
                    
                        //smoothPoints.remove(0);
                    
                                if(smoothPoints.size()>1){
                                   setStart(smoothPoints.get(0).j,smoothPoints.get(0).g);
                                   setEnd(smoothPoints.get(1).j,smoothPoints.get(1).g); 
                                    smoothPoints.remove(0);
                                   // path.clear();
                                }else{
                                  setStart(smoothPoints.get(0).j,smoothPoints.get(0).g); 
                                  setEnd(panel.size-start,panel.size - start);
                                    smoothPoints.remove(0);
                                   // path.clear();
                                } 
                                
                }else{
                  
         
      
                    colorPath();
              
                    
                    
                    panel.main.label.setText("steps to find shortest path: " + counter);
                      timer.stop();
                      panel.main.start.setText("new"); 
                }
             
        
            }
       
   } // end next step  
   
 private void colorPath(){
     
                      
             for(int i = 0; i < path.size(); i++){
           
         
           if(i%2==0){
                 panel.grid[ path.get(i).j][ path.get(i).g].setBackground(java.awt.Color.green);
                    panel.grid[ path.get(i).j][ path.get(i).g].setForeground(java.awt.Color.green); 
           }else{
           String zekename = "images/zeke.gif"; 
        ImageIcon monkeyPic = new ImageIcon(zekename);
        //panel.grid[ path.get(i).j][ path.get(i).g].setIcon(monkeyPic);
          String kenam = "images/bigzeke.gif"; 
        ImageIcon onkyPic = new ImageIcon(kenam);
       // panel.main.zeke.setIcon(onkyPic);
              panel.grid[ path.get(i).j][ path.get(i).g].setBackground(java.awt.Color.white);
                    panel.grid[ path.get(i).j][ path.get(i).g].setForeground(java.awt.Color.white);
           
           }
       
                 
   
             }
      
     
     
 }  
   
   
  private boolean checkForWall(int a, int b){
      
     if(panel.grid[a][b].getBackground().equals(java.awt.Color.black)){
          return true; 
       } 
      return false;
      
      //panel.grid[a][b].getBackground().equals(java.awt.Color.black)? return true : return false;
      
      
  } 
   
  private void createWalls(){
      /*
      
      these are the original walls, leaving them here for reference.  
      
      
      addWall(10,10);
      addWall(11,10);
      addWall(12,10);
      addWall(13,10);
      addWall(14,10);
      
      
      addWall(14,5);
      addWall(14,6);
      addWall(14,7);
      addWall(14,8);
      addWall(14,9);
      
      
      addWall(19,4);
      addWall(19,5);
      addWall(19,6);
     // addWall(19,7);
    //  addWall(19,8);
     // addWall(19,9);
      
      //addWall(18,10);
     // addWall(18,10);
     // addWall(18,10);
     // addWall(18,10);
     
     
      addWall(23,20);
      addWall(24,20);
      addWall(25,20);
      addWall(26,20);
      addWall(27,20);
      
      addWall(28,6);
      addWall(29,6);
      addWall(30,6);
      addWall(31,6);
      addWall(32,6);
      addWall(33,6);
      
      addWall(28,7);
      addWall(28,8);
      addWall(28,9);
      addWall(28,10);
      addWall(28,11);
      addWall(28,12);
      addWall(28,13);
      addWall(28,14);
      addWall(28,15);
      addWall(28,16);
      addWall(28,17);
      addWall(28,18);
      addWall(28,19);
      addWall(28,20);
      
      */
  }
  
  private void printPath(){
      
      for(int i = 0; i < path.size(); i++){
          
       System.out.println("path #"+i+" = [" + path.get(i).j + "][" + path.get(i).g + "]  " ); 
          
      }
      
  }
  
  private void addWall(int a, int b){
      
      
            panel.grid[a][b].setBackground(java.awt.Color.black);
            panel.grid[a][b].setForeground(java.awt.Color.black);  
      
      
      
      
  } 

    private void hookUpButtons() {

        for (int i = 0; i < panel.size; i++) {

            for (int j = 0; j < panel.size; j++) {

                panel.grid[i][j].addActionListener(this);

            }
        }

        panel.main.start.addActionListener(this);
        panel.main.zeke.addActionListener(this);
        
    }

  
  
    
    private void smooth(int start){
        // start at start, and then get distance 1-2, 1-3, etc
        nextSmoothPoint = 0;
        
        
        for(int i = start+ 1; i < path.size();i++){
           // System.out.println("\nSMOOTHING...  step #"+ i  + ", " + getDistance(path.get(start).j,path.get(i).j, path.get(start).g,path.get(i).g));
          if(getDistance(path.get(start).j,path.get(i).j, path.get(start).g,path.get(i).g)  < getDistance(path.get(start).j,path.get(i-1).j, path.get(start).g,path.get(i-1).g)){
              panel.grid[path.get(i).j][path.get(i).g].setBackground(java.awt.Color.MAGENTA);
              gettingSmaller = true;
          }else{
              
              
              if(gettingSmaller){
                  smoothPoints.add(path.get(i-1));
                //   System.out.println("there are " + smoothPoints.size() + " smooth points");//System.out.println("\n\n\n this is a SUUUUUUPER POINT!!!!!!!!!!!!   [" + path.get(i-1).j+ "][" + path.get(i-1).g + "]\n\n\n");
                nextSmoothPoint = i-1;
                gettingSmaller = false;
              return;
              }
              
              
              
              
          }  
        }
       // when distance > step, mark step as bad
        // when distance is back to normal,
        //try to draw a line to the last bad step 
        
    }
    
 private double getDistance(int a, int a2, int b, int b2){
     double temp = Math.sqrt(((a2-a)*(a2-a))+((b2-b)*(b2-b)));
  //  System.out.println("distance from = [" + a + "][" + b + "] to [" + a2 + "][" + b2+ "] is " + temp);
   return temp;
     
     
     
     
 }   // end get distance
  
 
 
  
    @Override
    public void actionPerformed(ActionEvent e) {
        
        
       if (e.getSource() == timer){
           counter++;
           
           if(lenny){
           nextStep(sx,sy);
           }else{
            nextStep2(sx,sy);   
           }
           
           
       }
       
       
             if (e.getSource() ==  panel.main.start){
      
                 if(panel.main.start.getText().endsWith("new")){
                   //panel.main.runSearch();
                 }else if(panel.main.start.getText().endsWith("start")){
                   panel.main.start.setText("pause");  
                   timer.start();
                 }else{
                    panel.main.start.setText("start"); 
                    timer.stop();
                 }
                 
                 
                 
                 
               }
       
      
       
       
       
       
        for (int i = 0; i < panel.size; i++) {

            for (int j = 0; j < panel.size; j++) {

                if (e.getSource() == panel.grid[i][j]) {

                    if (panel.grid[i][j].getBackground().equals(java.awt.Color.white)) {
                        panel.grid[i][j].setBackground(java.awt.Color.black);
                        panel.map[i][j]=1;
                        panel.writeMap();
                    } else {
                        panel.grid[i][j].setBackground(java.awt.Color.white);
                         panel.map[i][j]=0;
                         panel.writeMap();
                    }

                    //System.out.println("position = [" + i + "][" + j + "]");
                }

            }  // end for j
        } // end for i
           
       
    } // end action listener
 
 
 
 
} // end class
