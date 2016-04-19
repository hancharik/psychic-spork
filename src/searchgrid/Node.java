/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchgrid;

/**
 *
 * @author markhancharik
 */
public class Node {
    
    int x;
    int y;
    
     Node parent;
    // int distance;
    
     
     int hValue;  // determined by hueristic, we will be using distance
     int gValue;  // the cost to get to this step from the parent block
     int fValue;  // we get this by adding h + g
   
    // double hMultiplier;
     Graph graph;
     
    public Node(int a, int b, Graph g){
        
      x=a;
      y=b;
  
      graph = g;
      //hMultiplier = graph.bfs.hMultiplier;
      
      
    }  // end constructor
    
  
    
        
        // don't need this , using int cost instead
    private void setDistance(){
        
        // distance = (int)(Math.sqrt(( endx-x)*( endx-x)+( endy-y)*( endy-y))*10);
         hValue = (int)(Math.sqrt((  graph.bfs.graph.end.x-x)*(  graph.bfs.graph.end.x-x)+(   graph.bfs.graph.end.y-y)*(   graph.bfs.graph.end.y-y))*graph.bfs.hMultiplier);
       
    }
   
     private void setfValue(){
        
         fValue = gValue + hValue;
       
    }
     
     
     
   public void addNeighborsToOpenList(){
       
       int tempCost = 0;
       int costCalcInt = 0;
        // graph.bfs.graph.openNodes.remove(this);  
       System.out.println("adding [" + this.x + "][" + this.y + "] to the open list");
      
      
       for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
                
                Node temp = new Node(x+i,y+j, graph);
                
                if(withinMapBoundaries(x+i,y+j)){
                    
              if( graph.bfs.graph.end.x == x+i &&  graph.bfs.graph.end.y == y+j){
                 System.out.println("end found");
                  graph.bfs.graph.endFound = true; 
                  graph.bfs.graph.penultimate = this;
                 // graph.bfs.timer.stop();
                }else{
           
                if( graph.bfs.graph.mapGrid[x+i][y+j]==0){
               if(! graph.bfs.graph.openContainsThisNode(temp.x , temp.y) &&  ! graph.bfs.graph.closedContainsThisNode(temp.x , temp.y)){
                        temp.setfValue(); 
                    graph.bfs.graph.addToOpenList(temp);
                   graph.bfs.panel.grid[x+i][y+j].setBackground(java.awt.Color.blue);
                graph.bfs.panel.grid[x+i][y+j].setForeground(java.awt.Color.blue);
                // add a block to the open list
              //  graph.bfs.graph.blocks.add(new Node(x+i,y+j));
               
               // record parent
               // graph.bfs.graph.blocks.get(  graph.bfs.graph.openListSize()-1).parent = this;
               temp.parent = this;
               
               //calculate hueristic (H)
              //  graph.bfs.graph.blocks.get(  graph.bfs.graph.openListSize()-1).setDistance();
               temp.setDistance();
               
               
               // calculate the cost to move to this block from parent (G)
               // this is from policyalminac.org/games/aStarTutorial - the discussion of multiplying by ten and casting to int for speed - haven't tested the speed claim yet - Math.sqrt(2) to 14 and ten 
                if((Math.abs(i) + Math.abs(j)) == 2){
                   temp.gValue = 14 + gValue;
                }else {
                   temp.gValue = 10 + gValue;
                }
                temp.setfValue();
                // check of the block is in the open list, or null, or an obstacle
                // add a block to the open list
              //  if(i == 0 && j == 0){
                 // graph.bfs.graph.blocks.get(  graph.bfs.graph.openListSize()-1).setfValue();
               // }else{
                     }
                 
                //}
               
                }
                
                }
            } // end if within map boundaries 
       }  // end for j
       }  // end for i
        graph.bfs.graph.removeFromOpenList(this);
         graph.bfs.graph.addToClosedList(this);  
         graph.bfs.panel.grid[this.x][this.y].setBackground(java.awt.Color.red);
         graph.bfs.panel.grid[this.x][this.y].setForeground(java.awt.Color.red);
        System.out.println("adding [" + this.x + "][" + this.y + "] to the closed list");
      
   } // end add neighbors to the open list
    
    
  public boolean withinMapBoundaries(int x, int y){
      
      boolean temp = true;
      
      if(x < 0 || x >  graph.bfs.graph.size-1){
         temp = false; 
      }
      
       if(y < 0 || y >  graph.bfs.graph.size-1){
          temp = false;
      }
      
      return temp;
  }  
    
public String printInfo(){
    
   return "["+this.x+"]["+this.y+"] = "+ fValue;
    
    
}
  
  
}   // end class
