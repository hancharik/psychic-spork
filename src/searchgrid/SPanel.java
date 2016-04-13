/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchgrid;

import com.sun.prism.paint.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author markhancharik
 */
public class SPanel extends JPanel{
    
    int size = 40;
    JButton[][] grid = new JButton[size][size];
    Controller controller;
    public SPanel(){
        
        super();
        setLayout(new GridLayout(size,size));
        
        addButtons();
        controller = new Controller(this);
    }
    
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
                    
             grid[i][j].setBackground(java.awt.Color.white);
             grid[i][j].setForeground(java.awt.Color.black); 
             
            grid[i][j].setBorderPainted(false);
           
            grid[i][j].setOpaque(true);
          
          
          add(grid[i][j]);
           
       }
           
       }
       
       
       
       
   } 
    
    
    
}
