

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;
 
public class StartUp {
   public String PlayerOne;
   public String PlayerTwo;
   public String PlayerThree;
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;
   private int roll1 = 0, roll2 = 0, roll3 = 0;
   private int n1 = 0, n2 = 0, n3 = 0;
   private int starting = 0;
   
   public int getDone(){
	   return starting;
   }
   
   
   public StartUp(){
      prepareGUI();
   }
//   public static void main(String[] args){
//      StartUp  StartUp = new StartUp();      
//      StartUp.showDialogDemo();
//   }
   private void prepareGUI(){
      mainFrame = new JFrame("Player Names");
      mainFrame.setSize(400,400);
      mainFrame.setLayout(new GridLayout(3, 1));
      
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      headerLabel = new JLabel("", JLabel.CENTER);        
      statusLabel = new JLabel("",JLabel.CENTER);    
      statusLabel.setSize(350,100);

      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);  
   }
   public void showDialogDemo(){                                       
      headerLabel.setText("Please Enter Player Names"); 
      
      final String first = JOptionPane.showInputDialog(controlPanel, "What's your name? (1/3)");
      final String second = JOptionPane.showInputDialog(controlPanel, "What's your name? (2/3)");
      final String third = JOptionPane.showInputDialog(controlPanel, "What's your name? (3/3)");


      
      JButton firstroll = new JButton(first + ": Roll");        
      JButton secondroll = new JButton(second + ": Roll");
      JButton thirdroll = new JButton(third + ": Roll");
      JButton EveryoneHasRolled = new JButton("Everyone has rolled.");
      final JButton StartGame = new JButton("Start Game");

      firstroll.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	if(n1 == 0){
        		Random rand = new Random();
        		roll1 = rand.nextInt(5) + 1;
        		statusLabel.setText(first + " rolled a " + roll1);
        		n1++;
        	}
        	else{
        		statusLabel.setText(first + " you've already rolled!");
        	}
         }          
      });
      
      secondroll.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	if(n2 == 0){
        		Random rand = new Random();
      			roll2 = rand.nextInt(5) + 1;
      			statusLabel.setText(second + " rolled a " + roll2);
      			n2++;
        	}
        	else{
        		statusLabel.setText(second + " you've already rolled!");
        	}
          }          
       });
      
      thirdroll.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	 if(n3 == 0){
        	  	Random rand = new Random();
        	  	roll3 = rand.nextInt(5) + 1;
        	  	statusLabel.setText(third + " rolled a " + roll3);
      			n3++;
        	 }
        	 else{
        		statusLabel.setText(third + " you've already rolled!");
        	 }
          }          
       });
      
      EveryoneHasRolled.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  
        	  
        	  if(n1 == 1 && n2 == 1 && n3 == 1){
        		  
        		  //System.out.println("test");
            	  if ((roll1 > roll2 && roll1 > roll3))
                  {
                      if(roll2 > roll3)
                      {
                          PlayerOne = first;
                          PlayerTwo = second;
                          PlayerThree = third;
                      }
                      else
                    	PlayerOne = first;
                      	PlayerTwo = third;
                      	PlayerThree = second;
                  }
                  else if ((roll2 > roll1 && roll2 > roll3))
                  {
                      if(roll1 > roll3)
                      {
                    	  PlayerOne = second;
                          PlayerTwo = first;
                          PlayerThree = third;
                      }
                      else
                      {
                    	  PlayerOne = second;
                          PlayerTwo = third;
                          PlayerThree = first;
                      }
                  }
                  else if ((roll3 > roll1 && roll3 > roll2))
                  {
                      if(roll1 > roll2)
                      {
                    	  PlayerOne = third;
                          PlayerTwo = first;
                          PlayerThree = second;
                      }
                      else
                    	  PlayerOne = third;
                      	  PlayerTwo = second;
                      	  PlayerThree = first;
                  }
                  else if (roll1 == roll2 ){
                	  if (roll1 > roll3){
                		  PlayerOne = first;
                          PlayerTwo = second;
                          PlayerThree = third;
                	  }
                	  else{
                		  PlayerOne = third;
                          PlayerTwo = first;
                          PlayerThree = second;
                	  }
                  }
                  else if (roll1 == roll3){
                	  if (roll1 > roll2){
                		  PlayerOne = first;
                          PlayerTwo = third;
                          PlayerThree = second;
                	  }
                	  else{
                		  PlayerOne = second;
                          PlayerTwo = first;
                          PlayerThree = third;
                	  }
                  }
                  else if (roll2 == roll3){
                	  if(roll2 > roll1){
                		  PlayerOne = second;
                          PlayerTwo = third;
                          PlayerThree = first;
                	  }
                	  else{
                		  PlayerOne = first;
                          PlayerTwo = second;
                          PlayerThree = third;
                	  }
                  }
                  else{
                	  PlayerOne = first;
                  	  PlayerTwo = second;
                  	  PlayerThree = third;
                  }
        		  
        		  statusLabel.setText(PlayerOne + " is Player 1. " + PlayerTwo + " is Player 2. " + PlayerThree + " is Player 3.");
        		  controlPanel.add(StartGame);
        	      mainFrame.setVisible(true); 
        	      
        	    
        	  }
        	  else{
        		  statusLabel.setText("Not Everyone Has Rolled");
        	  }
          }
       });
      
      StartGame.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  starting = 2;
        	  mainFrame.setVisible(false);
          }
          });
      
      
      if (roll1 == 0 || roll2 == 0 || roll3 == 0){
    		statusLabel.setText("Roll to see who goes first");
      }
      else{
    	 
    	  
    	 }

      controlPanel.add(firstroll);
      controlPanel.add(secondroll);
      controlPanel.add(thirdroll);
      controlPanel.add(EveryoneHasRolled);
      mainFrame.setVisible(true); 
   }

public String Player1Name(){
		return PlayerOne;
	}

public String Player2Name(){
	return PlayerTwo;
}

public String Player3Name(){
	return PlayerThree;
}
}
