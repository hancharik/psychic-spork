/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchgrid;


import java.util.ArrayList;

/**
 *
 * @author mark
 */
public class Graph {

     ArrayList<Node> blocks;// = new ArrayList();
     ArrayList<Node> path;// = new ArrayList();
     private ArrayList<Node> openNodes = new ArrayList();
     private ArrayList<Node> closedNodes = new ArrayList();
     int[][] mapGrid;// = new ArrayList();
     Node start;
     Node end;
     boolean endFound = false;
     int size;// = 40;
     int counter = 0;
     Node penultimate;
     double startTime;
     double endTime;
    BFS bfs;
     
    public Graph(BFS b) {
      
        bfs = b;
        size = bfs.panel.size;
        startTime = System.currentTimeMillis();
       blocks = new ArrayList();
       createGrid();
        start = new Node(bfs.startx,bfs.starty, this);
        openNodes.add(start);
        end = new Node(bfs.endx,bfs.endy, this);
        
        /*
        start.addNeighborsToOpenList();
       
       
        do {
            sortNodesByValue().addNeighborsToOpenList();
           // counter++;
        } while (!endFound);
      
        
        
        addNodesToGrid();
        printGrid();
        printNodeValues();
        buildPath();
        showPath();
         endTime = System.currentTimeMillis() - startTime;
         System.out.println("time in miliseconds to complete = " + endTime );
        */
    }
   
    /*
   public  void printGrid(){
       
         for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
       
                for(int k = 0; k < blocks.size();k++){
                    if(blocks.get(k).x == i && blocks.get(k).y == j){
                    System.out.print(blocks.get(k).fValue);
                } 
                }
               System.out.print("\t");
                
                
            }
            System.out.print("\n");
         }
       
   } // end print grid arraylist
    
    */
    
    

    
    
        public  void createGrid(){
             mapGrid = bfs.panel.map;
            /*
             mapGrid = new int[size][size];
           for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
           
                    mapGrid[i][j] = 0;
                
            }
           }
         makeWalls();
         */
      }
      public  void addNodesToGrid(){
           for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
            for(int k = 0; k < blocks.size();k++){
                    
                    if(blocks.get(k).x == i && blocks.get(k).y == j){
                    mapGrid[i][j] = blocks.get(k).fValue;
                     } 
                    
                }
            }
           }
          // set end
          mapGrid[end.x][end.y] = 777;
      }
      
      
     public  void printGrid(){
       
         for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
       
             
               System.out.print(mapGrid[i][j] + "\t");
                
                
            }
            System.out.print("\n");
         }
       
   } // end print grid array [][] 
     
     
          public  void printNodeValues() {
        System.out.println("printing "+openNodes.size()+" open nodes");
        for (int k = 0; k < openNodes.size(); k++) {

           // System.out.println(openNodes.get(k).fValue);
            System.out.println(openNodes.get(k).printInfo());
        }
        System.out.println("\n\nprinting "+closedNodes.size()+" closed nodes");
        for (int k = 0; k < closedNodes.size(); k++) {

           // System.out.println(closedNodes.get(k).fValue);
            System.out.println(closedNodes.get(k).printInfo());
        }

    } // end print block value
    
          
   public  Node sortNodesByValue(){
       
       Node temp = openNodes.get(0);
       
      for (int k = 0; k < openNodes.size(); k++) {

            if(openNodes.get(k).fValue < temp.fValue){
              //  openNodes.get(0) = openNodes.get(k); 
               temp = openNodes.get(k) ; 
               
            }

        }
       return temp;
       
   }   // end sort blocks by value    
          
   
   // later we can swith this from void top arraylist of nodes
   public  void buildPath(){
       
      path = new ArrayList(); 
      path.add(end);
      System.out.println("path step [" +end.x + "][" + end.y + "]");//System.out.println("adding end [" +end.x + "][" + end.y + "]");
      path.add(penultimate);
       System.out.println("path step [" +penultimate.x + "][" + penultimate.y + "]");// System.out.println("adding penultimate [" +penultimate.x + "][" + penultimate.y + "]");
      // System.out.println(" penultimate parent =  [" +penultimate.parent.x + "][" + penultimate.parent.y + "]");
      Node temp = penultimate.parent;
      for(int i = 0; i < closedNodes.size(); i++){
           System.out.println("path step [" +temp.x + "][" + temp.y + "]");
        path.add(temp);
        if(temp.parent!=null){
             temp = temp.parent;
        }else{
            System.out.println("path complete."); break;
        }
       
          
       }
       
       
         bfs.finalPathSize = path.size();
       
       
       
   }  // end build path
   

   
   public void colorPath(){
       
      if(path.size()>1){
         
                   searchgrid.SearchGrid.f.main.gameBoard.controller.panel.grid[path.get(path.size()-1).x][path.get(path.size()-1).y].setBackground(java.awt.Color.green);
                searchgrid.SearchGrid.f.main.gameBoard.controller.panel.grid[path.get(path.size()-1).x][path.get(path.size()-1).y].setForeground(java.awt.Color.green);
                bfs.path.add(new Block(path.get(path.size()-1).x,path.get(path.size()-1).y));
                path.remove(path.size()-1);
                
      } else if(path.size()==1){
         
                   searchgrid.SearchGrid.f.main.gameBoard.controller.panel.grid[path.get(0).x][path.get(0).y].setBackground(java.awt.Color.green);
                searchgrid.SearchGrid.f.main.gameBoard.controller.panel.grid[path.get(0).x][path.get(0).y].setForeground(java.awt.Color.green);
                bfs.path.add(new Block(path.get(0).x,path.get(0).y));
                path.remove(0);
                
      } else{
          //bfs.pathReady = true;
          bfs.panel.main.label.setText(bfs.counter + " steps to find a path with a length of " + bfs.finalPathSize);
       
          bfs.panel.main.start.setText("new");
         bfs.timer.stop();
      }
       
       
       
       
       
   }
   
   
   
   
   
  
 public void addToOpenList(Node b){
    
    
    openNodes.add(b);
    
    
}// end add to open list


 public void addToClosedList(Node b){
    
    
   closedNodes.add(b);
    
    
}// end add to open list
 public void removeFromOpenList(Node b){
    
    
    openNodes.remove(b);
    
    
}// end add to open list

 public int openListSize(){
    
    
    return openNodes.size();
    
    
}// end add to open list
     
  public boolean openContainsThisNode(int x , int y){
      
      boolean temp = false;
      
      for(int q = 0; q < openNodes.size(); q++){
          
          if(openNodes.get(q).x == x && openNodes.get(q).y == y ){
             temp = true; break; 
          }
          
      }
      
      return temp;
      
      
  }// end open contains this block       
 
   public boolean closedContainsThisNode(int x , int y){
      
      boolean temp = false;
      
      for(int q = 0; q < closedNodes.size(); q++){
          
          if(closedNodes.get(q).x == x && closedNodes.get(q).y == y ){
             temp = true; break; 
          }
          
      }
      
      return temp;
      
      
  }// end open contains this block 
  
}// end class
