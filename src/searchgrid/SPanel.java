/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchgrid;

import com.sun.prism.paint.Color;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author markhancharik
 */
public class SPanel extends JPanel{
    
    int size = 40;
    int[][] map = new int[size][size];
    JButton[][] grid = new JButton[size][size];
    //Zeke controller;
    BFS controller;
    MainPanel main;
    String currentMap;// = "maps/testmap.txt";
    
    public SPanel(MainPanel m){
        
        super();
        main = m;
        setLayout(new GridLayout(size,size));
        currentMap = "maps/testmap.txt";
        map = drawMap();
        addButtons();
        try {
           controller = new BFS(this);//controller = new Zeke(this);// 
             
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  // end constructor
    
    
    
    
   private void addButtons(){
             for(int i = 0; i < size; i++){
////           
         for(int j = 0; j < size; j++){
////           
          grid[i][j] = new JButton();//i + "" + j);
   
            grid[i][j].setBorderPainted(false);
           
            grid[i][j].setOpaque(true);
          
          
          add(grid[i][j]);
           
       }
           
       }
       
       
       colorButtons();
       
   }  // end add buttons 

   
   
  public void colorButtons(){
       
       
             for(int i = 0; i < size; i++){
////           
         for(int j = 0; j < size; j++){
////           

              if(map[i][j]==0){
                 grid[i][j].setBackground(java.awt.Color.white);
                 grid[i][j].setForeground(java.awt.Color.black);   
              }   else{
                   grid[i][j].setBackground(java.awt.Color.black);
                    grid[i][j].setForeground(java.awt.Color.white);  
              }   
 
             
         
          
     
           
       }
           
       }
       
       
       
   }
 
public int[][] drawMap(){
    int[][] temp = new int[size][size];
    try {
			
			BufferedReader br = new BufferedReader(new FileReader(currentMap));
			
			int mapWidth = Integer.parseInt(br.readLine());
			int mapHeight = Integer.parseInt(br.readLine());
			map = new int[mapHeight][mapWidth];
			
			//minx = GamePanel.WIDTH - mapWidth * tileSize;
			//miny = GamePanel.HEIGHT - mapHeight * tileSize;
			
			String delimiters = "\\s+";
			for(int row = 0; row < mapHeight; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delimiters);
				for(int col = 0; col < mapWidth; col++) {
					temp[row][col] = Integer.parseInt(tokens[col]);
				}
			}
				br.close();
		}
		catch(Exception e) {}
    
    
    
    return temp;
}// end draw map






public void writeMap(String s){
    int[][] temp = new int[size][size];
    try {
			File thisFile = new File(s);
                        if(!thisFile.exists()) {
                            thisFile.createNewFile();
                           // System.out.println("new file created: " + s);
                        } 
			//BufferedWriter br = new BufferedWriter(new FileReader("maps/testmap.txt"));
			FileWriter fw = new FileWriter(thisFile);
			BufferedWriter bw = new BufferedWriter(fw);
		
		   bw.write(""+size+"\n");
		    bw.write(""+size+"\n");
			for(int row = 0; row < size; row++) {
				for(int col = 0; col < size; col++) {
					bw.write(String.valueOf(map[row][col]));
                                        bw.write(" ");
				}
                                bw.write("\n");
			}
			bw.close();	
		}
		catch(Exception e) {}
    
    
    
   // return temp;
}// end draw map
   
    
} // end class
