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
import java.io.FileReader;
import java.io.FileWriter;
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
    Controller controller;
    MainPanel main;
    
    
    public SPanel(MainPanel m){
        
        super();
        main = m;
        setLayout(new GridLayout(size,size));
        map = drawMap();
        addButtons();
        controller = new Controller(this);
    }  // end constructor
    
    
    
    
   private void addButtons(){
             for(int i = 0; i < size; i++){
////           
         for(int j = 0; j < size; j++){
////           
          grid[i][j] = new JButton();//i + "" + j);
          /*
          if(i%2 == 0 && j%2 == 0){
            grid[i][j].setBackground(java.awt.Color.black);
             grid[i][j].setForeground(java.awt.Color.white); 
          }else if(i%2 == 1 && j%2 == 1){
            grid[i][j].setBackground(java.awt.Color.black);
             grid[i][j].setForeground(java.awt.Color.white); 
          }else{
             grid[i][j].setBackground(java.awt.Color.white);
             grid[i][j].setForeground(java.awt.Color.black);   
          }
            
           */
              if(map[i][j]==0){
                 grid[i][j].setBackground(java.awt.Color.white);
                 grid[i][j].setForeground(java.awt.Color.black);   
              }   else{
                   grid[i][j].setBackground(java.awt.Color.black);
                    grid[i][j].setForeground(java.awt.Color.white);  
              }   
            // grid[i][j].setBackground(java.awt.Color.white);
            // grid[i][j].setForeground(java.awt.Color.black); 
             
            grid[i][j].setBorderPainted(false);
           
            grid[i][j].setOpaque(true);
          
          
          add(grid[i][j]);
           
       }
           
       }
       
       
       
       
   }  // end add buttons 
    
 
private int[][] drawMap(){
    int[][] temp = new int[size][size];
    try {
			
			BufferedReader br = new BufferedReader(new FileReader("maps/testmap.txt"));
			
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

public int[][] writeMap(){
    int[][] temp = new int[size][size];
    try {
			
			//BufferedWriter br = new BufferedWriter(new FileReader("maps/testmap.txt"));
			FileWriter fw = new FileWriter("maps/testmap.txt");
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
    
    
    
    return temp;
}// end draw map
   
    
} // end class
