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
public class Block {
    
    int x;
    int y;
     Block parent;
     double distance;
     BFS bfs;
     int cost;
    
    public Block(int a, int b){
        
      x=a;
      y=b;
 
    }  // end constructor
    
        public Block(int a, int b, BFS p, int c){
        
      x=a;
      y=b;
      bfs = p;
 
    }  // end constructor
    
        
        // don't need this , using int cost instead
    private void setDistance(){
        
         distance = Math.sqrt((bfs.endx-x)*(bfs.endx-x)+(bfs.endy-y)*(bfs.endy-y));
       
    }
   
    
   private void addNeighborsToOpenList(){
       
       int tempCost = 0;
       int costCalcInt = 0;
       
       for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
           
               
                
                if((Math.abs(i) + Math.abs(j)) == 2){
                   tempCost = 14;
                }else {
                   tempCost = 10;
                }
                // check of the block is in the open list, or null, or an obstacle
                // add a block to the open list
                if(i != 0 && j != 0){
                   bfs.open.add(new Block(x+i,y+j,bfs, (cost + tempCost)));  
                }
               
                
            } 
       }
       
       
   } // end add neighbors to the open list
    
    
    
    
}
