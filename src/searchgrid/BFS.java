/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchgrid;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author markhancharik
 */
public class BFS implements ActionListener {

    SPanel panel;
    Timer timer;

    
    
    
    
    int startx;// = panel.size-3;
    int starty;// = 3;
    int endx;// = 3;
    int endy;// = panel.size-3;
    int sx;
    int sy;
    int ex;
    int ey;

    boolean goingLeft;
    boolean goingRight;
    boolean goingUp;
    boolean goingDown;

    boolean clockwise;
    
    
    // these are for adding gaps in the path - safeCorners reduces distance to one around corners, 
    //  modHolder adjusts the period of skips (2 is every other one, three is every third)
    boolean safeCorners = false;
    int modHolder = 3; // 1%2==modHolder

    
    
    
    boolean gettingSmaller = false;

    ArrayList<Block> path = new ArrayList();
    ArrayList<Block> smoothPoints = new ArrayList();

    ArrayList<Block> open = new ArrayList();
    ArrayList<Block> closed = new ArrayList();
    
    
    
    int nextSmoothPoint = 0;

    boolean lenny = true; // otherwise, squiggy... lenny finds a path, squiggy smooths the path
    boolean pathBuilt = false;
    boolean pathReady = false;
    boolean nextStep2 = false;
    int counter = 0;
    int timerSetting = 60;
    
    Graph graph;
    
    double hMultiplier = 6.0;
    int finalPathSize; // this is to store the length of the path, to compare unsmoothed vs smoothed path lengths

    public BFS(SPanel s) throws FileNotFoundException {

        panel = s;
        
        timer = new Timer(timerSetting, this);
        setup();

    }

    private void setup() throws FileNotFoundException {
        
        startx = 3;//panel.size - 3;
        starty = 3;
        endx = panel.size - 3;
        endy = panel.size - 3;
        setStart(startx, starty);//setStart(start,start);//
        setEnd(endx, endy);//setEnd(panel.size-start,panel.size - start);//
        //createWalls();
        hookUpButtons();
        panel.main.makeFileArray();
        graph = new Graph(this);
       // timer.start();
        // System.out.println("there are " + smoothPoints.size() + " smooth points");
    }

    public void setStart(int a, int b) {
        panel.grid[a][b].setBackground(java.awt.Color.green);
        panel.grid[a][b].setForeground(java.awt.Color.black);
        sx = a;
        sy = b;
        
        path.add(new Block(a, b));
    }

    public void setEnd(int a, int b) {
        panel.grid[a][b].setBackground(java.awt.Color.red);
        panel.grid[a][b].setForeground(java.awt.Color.black);
        ex = a;
        ey = b;
    }

   public void colorPath() {

      
        
        
        // we should probably throw this into a stack soon...
       
        for (int i = 1; i < path.size(); i++) {

            if (i <  path.size()-2) {
                // this is to check if the points are in a straight line - NEED TO FIX!! better choice is to check for obstacles if in a corner
                // i think we can figure out a subtraction problem which produces the correct cell to check for obstacle
                // until then, this is a quick fix:
                if(safeCorners){
                  
                         if(i%2==modHolder  && ((path.get(i).x == path.get(i+1).x || path.get(i).y == path.get(i+1).y  )&&(path.get(i).x == path.get(i-1).x || path.get(i).y == path.get(i-1).y  ))){
                     // i'm leaving this here to show myself how I was wrong in my logic, this was the original, which is logically incorrect
               // if(i%2==0 && path.get(i).x == path.get(i+1).x && path.get(i).y == path.get(i+1).y ){
                panel.grid[path.get(i).x][path.get(i).y].setBackground(java.awt.Color.white);
                panel.grid[path.get(i).x][path.get(i).y].setForeground(java.awt.Color.white);
            } else {
              
                panel.grid[path.get(i).x][path.get(i).y].setBackground(java.awt.Color.green);
                panel.grid[path.get(i).x][path.get(i).y].setForeground(java.awt.Color.green);
               

            }  // end if/else
                    
                    
                    
                }else{
                   
                    
                    
                        if(i%2==modHolder ){
               panel.grid[path.get(i).x][path.get(i).y].setBackground(java.awt.Color.white);
                panel.grid[path.get(i).x][path.get(i).y].setForeground(java.awt.Color.white);
            } else {
              
                panel.grid[path.get(i).x][path.get(i).y].setBackground(java.awt.Color.green);
                panel.grid[path.get(i).x][path.get(i).y].setForeground(java.awt.Color.green);
               

            }  // end if/else 
                    
                    
                    
                    
                    
                }
            
                 
                 
            }
        }

    }



    private void printPath() {

        for (int i = 0; i < path.size(); i++) {

            System.out.println("path #" + i + " = [" + path.get(i).x + "][" + path.get(i).y + "]  ");

        }

    }

    private void addWall(int a, int b) {

        panel.grid[a][b].setBackground(java.awt.Color.black);
        panel.grid[a][b].setForeground(java.awt.Color.black);

    }

    
        private void clearWall(int a, int b) {

        panel.grid[a][b].setBackground(java.awt.Color.white);
        panel.grid[a][b].setForeground(java.awt.Color.white);

    }
        
        
    private void hookUpButtons() {
System.out.println("hooking up buttons");
        for (int i = 0; i < panel.size; i++) {

            for (int j = 0; j < panel.size; j++) {

                panel.grid[i][j].addActionListener(this);

            }
        }

        System.out.println("panel.main.mapButtons.size() = " + panel.main.mapButtons.size());
         for (int i = 0; i < panel.main.mapButtons.size(); i++) {
        panel.main.mapButtons.get(i).addActionListener(this);
         }
        
        panel.main.start.addActionListener(this);
        panel.main.save.addActionListener(this);
        panel.main.zeke.addActionListener(this);
        panel.main.bfirstButton.addActionListener(this);
    }


    private void restart() throws FileNotFoundException{
        
        if(timer.isRunning()){
            timer.stop();
        }
             path.clear();
                smoothPoints.clear();
                
                 panel.colorButtons();
                 lenny = true;
                  panel.main.start.setText("start");
                setup();//panel.main.runSearch();
        
        
        
    }
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == timer) {
            if(lenny){
                graph.start.addNeighborsToOpenList();
                lenny = false;
            }else if(!graph.endFound){
                graph.sortNodesByValue().addNeighborsToOpenList();
            }else if(!pathBuilt){
                graph.buildPath();
                pathBuilt = true;
            }else if(!pathReady){
                 graph.colorPath();
            }else if(!nextStep2){
               //  nextStep(sx, sy);
            }else{
                
               //  nextStep2(sx, sy);
               //graph.colorPath();
            }
            if(timer.isRunning()){
              panel.main.label.setText("<html><h2><font color='black'>step: </font><font color='black'>" + counter + "</font><h2></html>");
            counter++;  
            timer.setDelay(timerSetting);
            }
          

            //if (lenny) {
           //     nextStep(sx, sy);
           // } else {
           //     nextStep2(sx, sy);
          //  }

        }

        
        
        if (e.getSource() == panel.main.start) {

            if (panel.main.start.getText().equals("new")) {
           
                try {
                    restart();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } else if (panel.main.start.getText().equals("start")) {
                panel.main.start.setText("pause");
                timer.start();
            } else {
                panel.main.start.setText("start");
                timer.stop();
            }

        }

        if (e.getSource() == panel.main.bfirstButton) {

            panel.main.bfirstButton.setText("not yet!");

        }

        if (e.getSource() == panel.main.save) {

            if (panel.main.save.getText().equals("save map")) {
                panel.main.textArea.setVisible(true);
                panel.main.save.setText("save");

            } else {
                panel.main.textArea.setVisible(false);
                panel.main.save.setText("save map");
                // System.out.println("print text = " + panel.main.textArea.getText()+".txt");
                panel.writeMap("maps/" + panel.main.textArea.getText() + ".txt");
                try {
                    
                    panel.main.makeFileArray();
                    panel.main.mapButtonPanel = panel.main.categoryPanel();
                    panel.main.mapButtonPanel.repaint();
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        
          for (int i = 0; i < panel.main.mapButtons.size(); i++) {
               if (e.getSource() == panel.main.mapButtons.get(i)) {
                  //  panel.main.mapButtons.get(i).setText("not yet!");
            System.out.println("panel.main.mapButtons.get(i) = " + panel.main.mapButtons.get(i).getText());
            panel.currentMap = "maps/" + panel.main.mapButtons.get(i).getText();
            panel.map = panel.drawMap();
                   try {
                       
                      restart();
                   } catch (FileNotFoundException ex) {
                      Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                   }
        }
       
         }
        
        
        
        
        
        
        for (int i = 0; i < panel.size; i++) {

            for (int j = 0; j < panel.size; j++) {

                if (e.getSource() == panel.grid[i][j]) {

                    if (panel.grid[i][j].getBackground().equals(java.awt.Color.white)) {
                        addWall(i,j);//panel.grid[i][j].setBackground(java.awt.Color.black);
                        panel.map[i][j] = 1;
                        // we were having problems with move this to a save button
                       panel.writeMap(panel.currentMap);
                    } else {
                        clearWall(i,j);//panel.grid[i][j].setBackground(java.awt.Color.white);
                        panel.map[i][j] = 0;
                       panel.writeMap(panel.currentMap);
                    }

                    //System.out.println("position = [" + i + "][" + j + "]");
                }

            }  // end for j
        } // end for i

    } // end action listener

        
  
        ////////////////////////////////////////////////////////////////////////////////  
    ////////////////////////////////////////////////////////////////////////  
    ////////////////////////////////////////////////////////////////////////////////   
    ////////////////////////////////////////////////////////////////////////////////  
    ////////////////////////////////////////////////////////////////////////  
    ////////////////////////////////////////////////////////////////////////////////       
      ////////////////////////////////////////////////////////////////////////////////  
    ////////////////////////////////////////////////////////////////////////  
    ////////////////////////////////////////////////////////////////////////////////   
    ////////////////////////////////////////////////////////////////////////////////  
    ////////////////////////////////////////////////////////////////////////  
    ////////////////////////////////////////////////////////////////////////////////   
      ////////////////////////////////////////////////////////////////////////////////  
    ////////////////////////////////////////////////////////////////////////  
    ////////////////////////////////////////////////////////////////////////////////   
    ////////////////////////////////////////////////////////////////////////////////  
    ////////////////////////////////////////////////////////////////////////  
    ////////////////////////////////////////////////////////////////////////////////   
    private void checkX() {

      
           
            // go down
            if (sx < ex) {
            if(!checkForWall(sx + 1, sy)){
               sx++;   
            }
                  
                   
                } else {
                     if(!checkForWall(sx - 1, sy)){
               sx--;   
            }
                }

                
    } // end check x

         ////////////////////////////////////////////////////////////////////////////////  
    ////////////////////////////////////////////////////////////////////////  
    ////////////////////////////////////////////////////////////////////////////////    
    private void checkY() {
 
            if (sy < ey) {
                
                 if(!checkForWall(sx, sy + 1)){
               sy++;   
            }
          
                } else {
                         if(!checkForWall(sx, sy - 1)){
               sy--;   
            }
               
            }

    }  // end check y

    ////////////////////////////////////////////////////////////////////////////////  
    ////////////////////////////////////////////////////////////////////////  
    ////////////////////////////////////////////////////////////////////////////////   
    private void nextStep(int a, int b) {

        checkX();
        checkY();

        
        
        
        
        panel.grid[sx][sy].setBackground(java.awt.Color.blue);
        panel.grid[sx][sy].setForeground(java.awt.Color.blue);
        path.add(new Block(sx, sy));
        if (sx == ex && sy == ey) {
               // timer.stop();
            // printPath();
            //  panel.grid[sx][sy].setBackground(java.awt.Color.yellow);
            //panel.grid[sx][sy].setForeground(java.awt.Color.yellow); 
            smooth(0);
            if (nextSmoothPoint != 0) {
                smooth(nextSmoothPoint);
            }
                
          

            setStart(startx, starty);
            if (smoothPoints.size() > 0) {
                setEnd(smoothPoints.get(0).x, smoothPoints.get(0).y);
            }

            nextStep2 = true;
            path.clear();
        }

    } // end next step  

    private void nextStep2(int a, int b) {

        String ekename = "images/bigskull.gif";
        ImageIcon onkeyPic = new ImageIcon(ekename);
        //panel.main.zeke.setIcon(onkeyPic);
        //smoothPoints.get(0);

        // System.out.println("there are " + smoothPoints.size() + " smooth points");  
        checkX();
        checkY();

        panel.grid[sx][sy].setBackground(java.awt.Color.yellow);
        panel.grid[sx][sy].setForeground(java.awt.Color.yellow);
        path.add(new Block(sx, sy));
        if (sx == ex && sy == ey) {
               // timer.stop();
            //  printPath();

            if (smoothPoints.size() > 0) {

                        //smoothPoints.remove(0);
                if (smoothPoints.size() > 1) {
                    setStart(smoothPoints.get(0).x, smoothPoints.get(0).y);
                    setEnd(smoothPoints.get(1).x, smoothPoints.get(1).y);
                    smoothPoints.remove(0);
                    // path.clear();
                } else {
                    setStart(smoothPoints.get(0).x, smoothPoints.get(0).y);
                    setEnd(endx, endy);
                    smoothPoints.remove(0);
                    // path.clear();
                }

            } else {

                colorPath();

                panel.main.label.setText("steps to find shortest path: " + counter);
                timer.stop();
                panel.main.start.setText("new");
            }

        }

    } // end next step  
  
    
    
      private void smooth(int start) {
        // start at start, and then get distance 1-2, 1-3, etc
        nextSmoothPoint = 0;

        for (int i = start + 1; i < path.size(); i++) {
            // System.out.println("\nSMOOTHING...  step #"+ i  + ", " + getDistance(path.get(start).x,path.get(i).x, path.get(start).g,path.get(i).g));
            if (getDistance(path.get(start).x, path.get(i).x, path.get(start).y, path.get(i).y) < getDistance(path.get(start).x, path.get(i - 1).x, path.get(start).y, path.get(i - 1).y)) {
                panel.grid[path.get(i).x][path.get(i).y].setBackground(java.awt.Color.MAGENTA);
                gettingSmaller = true;
            } else {

                if (gettingSmaller) {
                    smoothPoints.add(path.get(i - 1));
                    //   System.out.println("there are " + smoothPoints.size() + " smooth points");//System.out.println("\n\n\n this is a SUUUUUUPER POINT!!!!!!!!!!!!   [" + path.get(i-1).x+ "][" + path.get(i-1).g + "]\n\n\n");
                    nextSmoothPoint = i - 1;
                    System.out.println("there are " + smoothPoints.size() + " smooth points. Next smooth point = " + nextSmoothPoint);
                    gettingSmaller = false;
                    return;
                }

            }
        }
       // when distance > step, mark step as bad
        // when distance is back to normal,
        //try to draw a line to the last bad step 

    }

    private double getDistance(int a, int a2, int b, int b2) {
        double temp = Math.sqrt(((a2 - a) * (a2 - a)) + ((b2 - b) * (b2 - b)));
        //  System.out.println("distance from = [" + a + "][" + b + "] to [" + a2 + "][" + b2+ "] is " + temp);
        return temp;

    }   // end get distance

      
    
   
    private boolean checkForWall(int a, int b) {

        if (panel.grid[a][b].getBackground().equals(java.awt.Color.black)) {
            return true;
        }
        return false;

      //panel.grid[a][b].getBackground().equals(java.awt.Color.black)? return true : return false;
    }

    private boolean checkUpOpens(int a, int b) {  //  b would be the real Y + 1. so you check the three above you

        for (int i = -1; i < 1; i++) {

            if (!checkForWall(a + i, b)) {
                return true;
            }

        }

        return false;
    }

    private boolean checkDownOpens(int a, int b) {  //  b would be the real Y - 1. so you check the three below you

        for (int i = -1; i < 1; i++) {

            if (!checkForWall(a + i, b)) {
                return true;
            }

        }

        return false;
    }

    private boolean checkLeftOpens(int a, int b) { //  b would be the real X - 1. so you check the three left of you

        for (int i = -1; i < 1; i++) {

            if (!checkForWall(a, b + i)) {
                return true;
            }

        }

        return false;
    }

    private boolean checkRightOpens(int a, int b) {//  b would be the real X + 1. so you check the three left of you

        for (int i = -1; i < 1; i++) {

            if (!checkForWall(a, b + i)) {
                return true;
            }

        }

        return false;
    } 
    
    
      
        ////////////////////////////////////////////////////////////////////////////////  
    ////////////////////////////////////////////////////////////////////////  
    ////////////////////////////////////////////////////////////////////////////////   
    ////////////////////////////////////////////////////////////////////////////////  
    ////////////////////////////////////////////////////////////////////////  
    ////////////////////////////////////////////////////////////////////////////////       
      ////////////////////////////////////////////////////////////////////////////////  
    ////////////////////////////////////////////////////////////////////////  
    ////////////////////////////////////////////////////////////////////////////////   
    ////////////////////////////////////////////////////////////////////////////////  
    ////////////////////////////////////////////////////////////////////////  
    ////////////////////////////////////////////////////////////////////////////////   
      ////////////////////////////////////////////////////////////////////////////////  
    ////////////////////////////////////////////////////////////////////////  
    ////////////////////////////////////////////////////////////////////////////////   
    ////////////////////////////////////////////////////////////////////////////////  
    ////////////////////////////////////////////////////////////////////////  
    ////////////////////////////////////////////////////////////////////////////////   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
} // end class
