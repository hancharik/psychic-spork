/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchgrid;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


/**
 *
 * @author mark
 */
public class MainPanel extends JPanel{
    
    SPanel gameBoard;;// = new SPanel(this);
 
 JLabel label;// = new JLabel("finding path..."); 
 JButton start;// = new JButton("start");
 JButton save;// = new JButton("start");
 JButton zeke;
 JButton bfirstButton;
 JTextArea textArea;
 
 JPanel mapButtonPanel;
 
 ArrayList<JButton> mapButtons = new ArrayList();
 ArrayList<String> maps = new ArrayList();
 
                 int from;// = line1.indexOf("h");
                 int to;// = line1.lastIndexOf("f");
                 int from2;// = line1.indexOf("f");
                 int to2;// = line1.lastIndexOf("\t");
                  String line1;
                 /* HungryLikeTheWolf:    "wereWolf.Wolf";////"lee0814.Wolf";//"ribau.Wolf";//
                    I ate granny: //"joe.Wolf";//"vella.Wolf";//"thesnake.Wolf";//
                 */
                 
                 String line2 = ".txt";//"zeke.Wolf";//"rickyBobby.Wolf";//"hancharik.Wolf";//
                 String line3 = line2.substring(0);
 
 
 
 
 
 
 
 
 
 
 
 
    public MainPanel(){
    
        super();
        
        setLayout(null);
        
        
        label = new JLabel("finding path..."); 
        start = new JButton("pause");
         save = new JButton("save map");
        zeke = new JButton("z star");
        bfirstButton = new JButton("breadth-first");
        textArea = new JTextArea();
        gameBoard = new SPanel(this);
        //mapButtonPanel = categoryPanel();
       // mapButtonPanel.setBounds(840,20,120,400);
       // mapButtonPanel.setBackground(Color.yellow);
        gameBoard.setBounds(0,0,800,800);
        label.setBounds(340,840,260,40);
        start.setBounds(620,840,80,40);
        save.setBounds(720,860,120,40);
        textArea.setBounds(720,810,120,30);
        textArea.setVisible(false);
        zeke.setBounds(840,520, 120,40);
        bfirstButton.setBounds(840,580, 120,40);
        String zekename = "images/bigzeke2.gif"; 
        ImageIcon monkeyPic = new ImageIcon(zekename);
        //zeke.setIcon(monkeyPic);
       // zeke.addActionListener(this);  // moved to controller class
        add(zeke);
        add(bfirstButton);
        
        add(textArea);
        add(save);
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
  
  public void makeFileArray() throws FileNotFoundException{
       
       
               // Construct Scanner objects for input files
    
        ArrayList<String> fileNames = new ArrayList();
        ArrayList<Double> numbers = new ArrayList();
        
        File folder = new File("maps/");
      File[] listOfFiles = folder.listFiles();

    for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile()) {
          String temp = listOfFiles[i].getName();
          fileNames.add(temp);
        System.out.println(listOfFiles[i].getName());
        
      } else if (listOfFiles[i].isDirectory()) {
        System.out.println("Directory " + listOfFiles[i].getName());
      }
    }
        
        
        
        
        
        
     for(int i = 0; i < fileNames.size(); i++){   
        String temp = fileNames.get(i).toString();
         System.out.println();
        System.out.println("adding " + temp + " to map array");
        maps.add(temp);
        Scanner in1 = new Scanner(new File("maps/"+temp));

        // Construct PrintWriter for the output file
       // PrintWriter out = new PrintWriter("world_pop_density.txt");
        //System.out.println();
        // Read lines from each file

        while (in1.hasNextLine()) {
            line1 = in1.nextLine();
            if (line1.contains(line2)) {
                 from = line1.indexOf(line3);//from = line1.indexOf("z");
                 to = line1.lastIndexOf("t");//to = line1.lastIndexOf(line2.length()-1);//
                 //from2 = line1.indexOf("f");
                 //to2 = line1.lastIndexOf("\t");
                 //extractName(line1.substring(from, to + 11));
                //numbers.add(extractValue(line1.substring(to + 6, to + 11)));
                  System.out.println("adding to map array: " + line1.substring(from, to - 5));//System.out.println(extractName(line1.substring(from, to + 11)));
                  maps.add(line1.substring(from, to - 5));
               // System.out.println(line1.substring(to + 6, to + 11));
            }

            // System.out.println(line1);
            //extractName(line1);
            //System.out.println(line1.substring(from, to));
        }

        in1.close();
        if(numbers.size()>0){
          System.out.println("score/time: " + (int)(numbers.get(0) / numbers.get(1)));  
        }
   
   numbers.clear();
    }
        //out.close();
       
       mapButtonPanel = categoryPanel();
        mapButtonPanel.setBounds(840,20,120,400);
        mapButtonPanel.setBackground(Color.yellow);
        add(mapButtonPanel);
       
       
   } 
    
    
        public static String extractName(String line) {
        int i = 0; // Locate the start of the first digit
        while (!Character.isWhitespace(line.charAt(i))) {
            i++;
        }
        return line.substring(0, i).trim(); // Extract the country name
    }

    /**
     * Extracts the value from an input line.
     *
     * @param line a line containing a country name, followed by a value
     * @return the value associated with the country
     */
    public static double extractValue(String line) {
        int i = 0; // Locate the start of the first digit
        while (!Character.isDigit(line.charAt(i))) {
            i++;
        }
        // Extract and convert the value
        return Double.parseDouble(line.substring(i).trim());
    }
    
    
    
    
    private JPanel categoryPanel() {

        JPanel mercPanel = new JPanel();
        int buttons = maps.size();
        int rows = 10;
        int columns = 1;//buttons/rows;

        mercPanel.setLayout(new GridLayout(rows, columns + 1));
        // mercPanel.setLayout(new GridLayout(columns+1, rows));

        //  merchantButtons = monkeyTown.merchants();
        // colorMerchantsWithTransactions();
        int quickTemp = buttons;
        if (quickTemp > 10) {
            quickTemp = 10;
        }

        for (int i = 0; i < quickTemp; i++) {
            //   for(int i = 0; i < rows; i++){   
            JButton jimmy = new JButton();
            jimmy.setSize(160, 40);
            // if(user.getContacts().size()< i){
            // jimmy.setText("" + i);  
            jimmy.setText(maps.get(i));
            //  }

            // jimmy.setBackground(randomColor(user.getTheme()));
            jimmy.setOpaque(true);
            jimmy.setBorderPainted(false);

            // jimmy.addActionListener(this);
            mapButtons.add(jimmy);
            mercPanel.add(jimmy);

        }
        return mercPanel;
    }  // end cat panel

    
    
    
    
} // end class
