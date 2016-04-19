/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchgrid;


import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class SFrame extends JFrame{
    
    //SPanel panel = new SPanel();
    MainPanel main;// = new MainPanel();
    public SFrame() throws FileNotFoundException{
        
   
        
        super("PathFinding Visualizer");//"ZStar search algorithm - written at Shingletown Gap 04/12/16"
         main = new MainPanel();
       setLayout(new BorderLayout());
        getContentPane().add(main);
        
        setSize(1000, 1000);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //String instructions = "-this will show up on the pop-up";
        
        //JOptionPane.showMessageDialog(this, instructions, "Instructions", JOptionPane.INFORMATION_MESSAGE);
    }

   // MatchingGame matchingGame = new MatchingGame();
    //Scoreboard scoreBoard = new Scoreboard();
  

    
    
    
    
    
}


