import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Monopoly {

	public static final int MAX_NUM_PLAYERS = 3; // we could get it to have 2-6 players but shouldve changed it early on, ran out of time as we would have to change the entire code which we will do next sprint
		
	public static final int NUM_SQUARES = 40;
	static int p ;
	
	static Properties pr = new Properties();
	
	private  ArrayList<Player> players = new ArrayList<Player>(); // array list. 
	
	private UI ui = new UI(players); // object called from UI class.
	
	
	
	Monopoly () {
		for (int p=0; p<MAX_NUM_PLAYERS; p++) {
		//	players.add(new Player(10));
			players.add(new Player(1500));
		}
		players.get(0).setName(StartUp.Player1Name());
		players.get(1).setName(StartUp.Player2Name());
	//	players.get(2).setName(StartUp.Player3Name());
		
		
		if(MAX_NUM_PLAYERS == 3){
			players.get(2).setName(StartUp.Player3Name());
		}
//		if(MAX_NUM_PLAYERS == 4){
//			players.get(3).setName(StartUp.Player4Name());
//		}
//		if(MAX_NUM_PLAYERS == 5){
//			players.get(4).setName(StartUp.Player5Name());
//		}
//		if(MAX_NUM_PLAYERS == 6){
//			players.get(5).setName(StartUp.Player6Name());
//		}
		
		
		ui.display();
		return;
	}
	
//	private void tour() {
//		ui.displayString("TOUR MODE");
//		for (int p=0; p<MAX_NUM_PLAYERS; p++) {
//			for (int i=0; i<NUM_SQUARES; i++) {
//				players.get(p).move(+1);
//				ui.display();
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					System.out.println("Sleep exeception.");
//				} 
//			}
//		}
//		return;
//	}
	
	  public void echo () {
		String command;
		ui.display();
		ui.displayString("ECHO MODE");
		do {
			command = ui.getCommand();
			ui.displayString(command);
		} while (!command.equals("quit"));
		return;
	}
	  
	  public void input(int p) {
	
		  	String text;
			ui.display(); 
			int doubles = 0;
			int rolled = 0;
			int youOwn = 0;
			int theyOwn = -1;
			int noOneOwns = 0;
			
			//loop through the players
			
			if(p == MAX_NUM_PLAYERS){
				p = 0;
			}
			
			ui.displayString("Your turn, " + players.get(p).getName());
				
				do {
					ui.displayString("What would you like to do?");
					
					text = ui.getCommand(); // get command from whatever is type in text by user.
					ui.displayString("You chose : " + text);
				
					//if statements for inputs
					
					if(text.equalsIgnoreCase("Roll")) { // Roll if Statement when user types Roll
						
						if ((rolled != 0 && doubles == 0) || rolled > doubles){
							ui.displayString("Sorry, You've already Rolled!");
						}
						else{
						
						//tests how many times in a row you have rolled doubles
						doubles = roll(doubles, p);
						//ui.displayString("doubles value " + doubles);
							
						
						if (doubles > rolled && doubles > 0){
							ui.displayString("Doubles! You can roll again!");
						}
						
						//displays info on the property you are on
					
						
							ui.displayString("You landed on:\n" + Properties.GetPropertyName(players.get(p).getPosition()));
							ui.displayString("Price = " + Properties.GetPropertyPrice(players.get(p).getPosition()));
							
						
						
						//check if owned if owned say you must pay rent, otherwise display price
						
						for(int pls = 0; pls < MAX_NUM_PLAYERS; pls++){
							//owned by any player
							if(players.get(pls).owned(players.get(p).getPosition())){
								if(pls == p){
									ui.displayString("You already own this property!");
									youOwn++;
								}
								
								else
								{
									ui.displayString(players.get(pls).getName() + " owns this property.\nYou must pay them rent.");
									theyOwn = pls;
								}
							}
							else{
								noOneOwns++;
							}
						}
						
						if (noOneOwns > 0){
							ui.displayString("No one owns this property");
						}
						rolled++;
						}	
					}
					
					else if (text.equalsIgnoreCase("Buy")){ // Buy if Statement when user types Buy
						//can only buy the property if it is eligible and is not already owned
						if (noOneOwns != 0 && theyOwn==-1 && (players.get(p).owned(players.get(p).getPosition()) == false) && ((Properties.GetPropertyPrice(players.get(p).getPosition()) != 0))){
							buy(p);  
						}
						else{
							ui.displayString("You Can't Buy This Property");
						}
					}
					
					else if (text.equalsIgnoreCase("Balance")){  // Balance if Statement when user types Balance
						ui.displayString("$" + players.get(p).getBalance()); // gets and displays the money of player.
					}
					
					else if (text.equalsIgnoreCase("Sell")){ // Sell if Statement when user types Sell.
						if (youOwn == 1 || players.get(p).owned(players.get(p).getPosition())){ // if a player owns property, then they can sell it.
							sell(p);
						}
						else{ 
							ui.displayString("You Can't sell a property you don't own");
						}
					}
					
					else if (text.equalsIgnoreCase("Pay Rent")){ // Roll if statement user types in Pay Rent, if they land on someone's property.
						if (theyOwn != -1){
							payRent(p, theyOwn); // if land on someone's property,  then pay rent
						}
						else{ // otherwise, don't need to pay.
							ui.displayString("You don't need to pay rent!");
						}
					}
					
					else if (text.equalsIgnoreCase("Help")){ // help If statement. 
		
						queryList();//displays the list of possible inputs
						//displays info on the property you are on
						ui.displayString("You landed on:\n" + Properties.GetPropertyName(players.get(p).getPosition()));
						ui.displayString("Price = " + Properties.GetPropertyPrice(players.get(p).getPosition()));
						
					}
					else if (text.equalsIgnoreCase("Check Property")){  // check Property if statement.
						ui.displayString("You own the following properties: ");
						//returning an integer array = property positions owned
						int[] positionsOwned = players.get(p).allPropertiesOwned();
						for(int i = 0; i <positionsOwned.length; i++){
							ui.displayString("" + Properties.GetPropertyName(positionsOwned[i])); // displays properties players own by finding the names of the position numbers owned
						}
						
					}
					
					else if (text.equalsIgnoreCase("Done")){ // when player is done with their turn, then it's the next player's turn.
						ui.displayString("Turn over");
					}
					
					else if (text.equalsIgnoreCase("Quit")){
						quit(); 
					}
					
					else{ // displays if player types a command that is not recognized by the program.
						
						ui.displayString("Invalid Command. Enter Help for a query list.");
					}
					
				
			} while (!text.equalsIgnoreCase("Done"));//once player types done we stop taking input from that player
			
		checkWinner(); //checks if there is a winner
		p++; 
		input(p); //next player
				
	  }
	  
	
	  public void queryList() { //displays the following commands the user only is valid to input.
		  ui.displayString("Valid commands:\nRoll\nBuy\nSell\nPay Rent\nBalance\nCheck Property\nDone\nQuit\n");
	  }
	  
	  public void buy(int player){ // buy method. 
		  ui.displayString("Congratulations! You now own this property.");
		  int price = Properties.GetPropertyPrice(players.get(player).getPosition()); // gets the price of the property the player has landed on.
		  players.get(player).withdraw(price); //player pays, so balance decreases
		  ui.displayString("Your new bank balance is $" + players.get(player).getBalance()); // displays player's new balance after buying the property.
		  players.get(player).addToProperties(players.get(player).getPosition()); //adds to array of properties owned
		  
	  }
	  
	  public void sell(int player){ // sell method 
		  //can only sell if you own it
		  int price = Properties.GetPropertyPrice(players.get(player).getPosition());
		  ui.displayString("You Sold "+ Properties.GetPropertyName(players.get(p).getPosition()) + " for $" + price); // displays the property the player has sold.
		  players.get(player).deposit(price); // increase account of player.
		  ui.displayString("Your new bank balance is $" + players.get(player).getBalance()); // displays new balance of player.
		  players.get(player).sellProperty(players.get(player).getPosition()); //remove from properties owned
		  
	  }
	  
	  public void payRent(int player, int theyOwn){ // payRent method.
		  int rent = Properties.GetPropertyRent(players.get(player).getPosition()); // gets the rent of the property.
		  ui.displayString("You have paid $" + rent + " rent to " + players.get(theyOwn).getName());
		  players.get(theyOwn).deposit(rent);   //increase his/her account
		  players.get(player).withdraw(rent); //decrease your account
		  ui.displayString("Your new bank balance is $" + players.get(player).getBalance());
	  }
	  
	  public int roll(int doubles, int p){
		  	
		  	Random rand = new Random();
			int roll1 = rand.nextInt(5) + 1; // 2 dice rolls. 
			int roll2 = rand.nextInt(5) + 1;
			int sum = roll1 + roll2; // gets the sum of the 2 dice.
			
			ui.displayString("You rolled a " + roll1 + " and a " + roll2 + " = " + sum);
			
				players.get(p).move(sum);
			//code that checks if you've looped the board and gives cash as reward
		  if (players.get(p).getPosition() > Monopoly.NUM_SQUARES) { 
					players.get(p).resetPosition(); // so here the code will call the reset function which will remove 40 from their position count so we can keep track of where they are and still use the arrays properties
					ui.displayString("You've passed GO!\n Collect $200");
					players.get(p).deposit(200); // this increase the player's balance (200$ given by the Banker)
					ui.displayString("Your new bank balance is $" + players.get(p).getBalance());
			}
		  else if(players.get(p).getPosition() == Monopoly.NUM_SQUARES){
				
					players.get(p).resetPosition();
					ui.displayString("You've landed on GO!\n Collect $200");
					players.get(p).deposit(200);
					ui.displayString("Your new bank balance is $" + players.get(p).getBalance());
				}
				ui.display();
				try {  
					Thread.sleep(500);   // this doesn't show up until player is ready.
				} catch (InterruptedException e) {
					System.out.println("Sleep exeception.");
				} 
				
			if(roll1 == roll2){//doubles
				doubles++;
			}
			
				return doubles;
			
	  }
	  
	  // Winner algorithm to check if two players have negative bank balances, resulting in the other player winning.
	//does not take into account properties owned
	  public void checkWinner(){
		  if((players.get(0).getBalance() < 0) && (players.get(1).getBalance()) < 0 && (players.get(2).getBalance() > 0)){
			  ui.displayString("\n\n\nWinner!\n\n");
			  	
			  	//jframe that displays the name of the winner and a quit button
			  	JFrame frame = new JFrame();
			  	String[] options = new String[1];
			  	options[0] = new String("Quit");
			  	int choice = JOptionPane.showOptionDialog(frame.getContentPane(), players.get(2).getName() + " is the Winner!", "Quit", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
			  	if(choice == 0){
			  		System.exit(0);
			  	}
		  }
		  if((players.get(0).getBalance() < 0) && (players.get(2).getBalance()) < 0 && (players.get(1).getBalance() > 0)){
			  ui.displayString("\n\n\nWinner!\n\n");
			  	JFrame frame = new JFrame();
			  	String[] options = new String[1];
			  	options[0] = new String("Quit");
			  	int choice = JOptionPane.showOptionDialog(frame.getContentPane(), players.get(1).getName() + " is the Winner!", "Quit", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
			  	if(choice == 0){
			  		System.exit(0);
			  	}
		  }
		  if((players.get(2).getBalance() < 0) && (players.get(1).getBalance()) < 0 && (players.get(0).getBalance() > 0)){
			  ui.displayString("\n\n\nWinner!\n\n");
			  	JFrame frame = new JFrame();
			  	String[] options = new String[1];
			  	options[0] = new String("Quit");
			  	int choice = JOptionPane.showOptionDialog(frame.getContentPane(), players.get(0).getName() + " is the Winner!", "Quit", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
			  	if(choice == 0){
			  		System.exit(0);
			  	}
		  }
	  }
	 
	public void quit(){// displays a JFrame when player inputs the command "Quit"
		  
		
			//jframe that checks if the player is sure they want to quit. if yes it finds winner, if no it simply returns to input
		  JFrame frame = new JFrame(); 
		  String[] options = new String[2];
		  options[0] = new String("Yes");
		  options[1] = new String("No");
		  int choice = JOptionPane.showOptionDialog(frame.getContentPane(), "Are you sure you want to quit?", "Quit", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
		  if(choice == 0){
			  	
			  
			  // Shows which player has the most assets and cash together , they are the winner.
			  JFrame frame2 = new JFrame("Winner");
			  GridLayout experimentLayout = new GridLayout(0,MAX_NUM_PLAYERS+1);
			  frame2.setLayout(experimentLayout);
			  	
			  String[] info = new String[MAX_NUM_PLAYERS];
			  int[] propertyValue = new int[MAX_NUM_PLAYERS];
			  
			  	for(int p = 0; p<MAX_NUM_PLAYERS; p++){
			  		int[] positionsOwned = players.get(p).allPropertiesOwned();
			  		String[] properties = new String[positionsOwned.length];
			  		info[p] = "";
			  		propertyValue[p] = 0;
			  		for(int i = 0; i <positionsOwned.length; i++){
			  			properties[i] = (Properties.GetPropertyName(positionsOwned[i])+ " $" + Properties.GetPropertyPrice(positionsOwned[i]));
			  			info[p] += ("<br>" + properties[i]);
			  			propertyValue[p] += Properties.GetPropertyPrice(positionsOwned[i]);
			  		}
			  		
			  	}
			   
			  	// create labels for displaying property values and cash of each player.
			   JLabel label = new JLabel(("<html>PLAYER 1: " + players.get(0).getName() + "<br><br>Bank Balance = $" + players.get(0).getBalance() + "<br><br>" + info[0] + 
			    		"<br>" + "Total Property Value = $" + propertyValue[0] + "<br><br>Total Balance = $" + (players.get(0).getBalance() + propertyValue[0]) + "</html>"), JLabel.CENTER);
			    JLabel label2 = new JLabel(("<html>PLAYER 2:" + players.get(1).getName() + "<br><br>Bank Balance = $" + players.get(1).getBalance() + "<br><br>" + info[1] + 
			    		"<br>" + "Total Property Value = $" + propertyValue[1] + "<br><br>Total Balance = $" + (players.get(1).getBalance() + propertyValue[1]) + "</html>"), JLabel.CENTER);
			    JLabel label3 = new JLabel(("<html>PLAYER 3:" + players.get(2).getName() + "<br><br>Bank Balance = $" + players.get(2).getBalance() + "<br><br>" + info[2] + 
			    		"<br>" + "Total Property Value = $" + propertyValue[2] + "<br><br>Total Balance = $" + (players.get(2).getBalance() + propertyValue[2]) + "</html>"), JLabel.CENTER);
			    //labels for the winner , they are just stated here but not printed
			    JLabel label4 = new JLabel(("The Winner is " + players.get(0).getName() ), JLabel.CENTER);
			    JLabel label5 = new JLabel(("The Winner is " + players.get(1).getName() ), JLabel.CENTER);
			    JLabel label6 = new JLabel(("The Winner is " + players.get(2).getName() ), JLabel.CENTER);
			    JLabel label7 = new JLabel(("NO WINNER"), JLabel.CENTER);
			    // the labels above will be printed if its player has the most money and property value
			    if((players.get(0).getBalance() + propertyValue[0])>(players.get(1).getBalance() + propertyValue[1]) && (players.get(0).getBalance() + propertyValue[0]) > (players.get(2).getBalance() + propertyValue[2])){
			    	frame2.add(label4);
			    
			    }
			    else if((players.get(1).getBalance() + propertyValue[1])>(players.get(0).getBalance() + propertyValue[0]) && (players.get(1).getBalance() + propertyValue[1]) > (players.get(2).getBalance() + propertyValue[2])){
			    	frame2.add(label5);
			    }
			    else if((players.get(2).getBalance() + propertyValue[2])>(players.get(1).getBalance() + propertyValue[1]) && (players.get(0).getBalance() + propertyValue[0]) < (players.get(2).getBalance() + propertyValue[2])){
			    	frame2.add(label6);
			    }
			    else{
			    	frame2.add(label7);
			    }
			    
			    
			    frame2.add(label);
			    frame2.add(label2);
			    frame2.add(label3);
			    
			    frame2.setSize(600, 500); 
			    final JButton Quit = new JButton("Quit");
			    
			    
			    Quit.addActionListener(new ActionListener() {
				    //ends the program when the quit button is pressed
			          public void actionPerformed(ActionEvent e) {
			        	  System.exit(0);
			          }
			          });
			    
			    frame2.add(Quit);
			    frame2.setVisible(true); // Display the frame
			  
		  }
		  else{
			  return;
		  }
	  
	  }
	  
	
	public static void main (String args[]) throws InterruptedException  {	
		
		
		//runs the startup cde
		StartUp  StartUp = new StartUp();      
		StartUp.showDialogDemo();
		while (true)
        {
            //multithreading allows the startup frame and monopoly frame not to be run simultaneously but instead wait
			Thread.sleep(500); 
            if (StartUp.getDone()==2){ 
                
            	Monopoly game = new Monopoly();		
	  			
	  			
	      		//game.tour();
	      		//game.echo();
	  			game.input(p);
	      		
	      		
            }	
        
		
        }
	}
}
