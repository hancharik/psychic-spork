/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchgrid;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *
 * @author mark
 */
public class MainPanel extends JPanel{
    
    SPanel gameBoard;;// = new SPanel(this);
 
 JLabel label;// = new JLabel("finding path..."); 
 JButton start;// = new JButton("start");
 JButton zeke;
    public MainPanel(){
    
        super();
        
        setLayout(null);
        
        
        label = new JLabel("finding path..."); 
        start = new JButton("pause");
        zeke = new JButton("zeke");
        gameBoard = new SPanel(this);
        
        gameBoard.setBounds(0,0,800,800);
        label.setBounds(340,840,260,40);
        start.setBounds(620,840,80,40);
        
        
        
        zeke.setBounds(740,820, 90,92);
        String zekename = "images/bigzeke2.gif"; 
        ImageIcon monkeyPic = new ImageIcon(zekename);
        zeke.setIcon(monkeyPic);
       // zeke.addActionListener(this);  // moved to controller class
        //add(zeke);
        
        
        
        
        add(start);
        add(gameBoard);
        add(label);
   // gameBoard.setFocusable(true);
    //    repaint();
    } // end constructor

    public void runSearch(){
        
        remove(gameBoard);
        gameBoard = new SPanel(this);
        add(gameBoard);
        gameBoard.repaint();
        repaint();
    }
    
} // end class
