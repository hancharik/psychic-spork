/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchgrid;


import java.awt.BorderLayout;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class SFrame extends JFrame{
    
  SPanel panel = new SPanel();

    public SFrame(){
        
    
        
        super("ZStar search algorithm - written at Shingletown Gap 04/12/16");
        
       setLayout(new BorderLayout());
        getContentPane().add(panel);
        
        setSize(1000, 1000);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //String instructions = "-this will show up on the pop-up";
        
        //JOptionPane.showMessageDialog(this, instructions, "Instructions", JOptionPane.INFORMATION_MESSAGE);
    }

   // MatchingGame matchingGame = new MatchingGame();
    //Scoreboard scoreBoard = new Scoreboard();
  

    
    
    
    
    
}


