import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class Monopoly {

	public static final int MAX_NUM_PLAYERS = 3;
	public static final int NUM_SQUARES = 40;
	
	private ArrayList<Player> players = new ArrayList<Player>();
	private UI ui = new UI(players);
	
	Monopoly () {
		for (int p=0; p<MAX_NUM_PLAYERS; p++) {
			players.add(new Player());
		}		
		ui.display();
		return;
	}
	
	private void tour () {
		ui.displayString("TOUR MODE");
		for (int p=0; p<MAX_NUM_PLAYERS; p++) {
			for (int i=0; i<NUM_SQUARES; i++) {
				players.get(p).move(+1);
				ui.display();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					System.out.println("Sleep exeception.");
				} 
			}
		}
		return;
	}
	
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
	  
	  public void input() {
	
		  	String text;
			ui.display(); 
			ui.displayString("TEST");
			
			do {
				text = ui.getCommand();
				ui.displayString(text);
				
				//int jailCheck = 0;
				
				if(text.equals("Roll")) {
					
					
					Random rand = new Random();
					int roll1 = rand.nextInt(5) + 1;
					int roll2 = rand.nextInt(5) + 1;
					int sum = roll1 + roll2;
					if (roll1 == roll2){
						ui.displayString("You rolled doubles! \n ");
						//jailCheck++;

						ui.displayString("You rolled a " + roll1 + " and a " + roll2 + " = " + sum);
						
					}
					else{
					ui.displayString("You rolled a " + roll1 + " and a " + roll2 + " = " + sum);
					}
				
					
				}
				else if(text.equalsIgnoreCase("Buy")){
					ui.displayString("You selected buy \n ");
					buy();
				}
				
				
				else if(text.equalsIgnoreCase("Balance")) {
					ui.displayString(" You selected balance " );
					//Balance();
				}
				
				else if(text.equalsIgnoreCase("Sell")) {
					ui.displayString("You selected Sell ");
					//Sell();
					
				}
				
				else if(text.equalsIgnoreCase("Help")){
					ui.displayString("You selected Help ");
					
					queryList();
					
					
				}
				
				else {
					 ui.displayString("Invalid Command");// if user enters invalid query.
				}
				
				
				
			} while (!text.equalsIgnoreCase("quit"));
			
			
	  }
	  
	  public void queryList(){
		  
		  ui.displayString(" Buy ");
		  ui.displayString(" Sell ");
		  ui.displayString(" Balance ");
		  
	  }
	  
	  
	  public void buy() {
		  
		  ui.displayString("Would you like to buy this property? ");
		  
		  // Add in James's code.. 
		  
	  }
	  
	  
	  
			 
			
			
				// System.out.println("ROLL"); // person has moved 2 spots.
			//textArea.setText(" \n  ROLL  "+input); 
//				
//			if (text.equals("Buy")) {
//				
//				textArea.setText(" \n BUY " +input); 
//				
//			}
//			
//			
//			 if (text.equals("Sell")) {
//				
//				
//				textArea.setText(" \n SELL "+input); 
//			}
//			
//			
//			if(text.equals("Balance")) {
//				
//				textArea.setText(" \n BALANCE "+input); 
//			}
//			
//			if(text.equals("Help")) {
//				
//				
//				
//				textArea.setText(" \n HELP "+input); 
//			}
//			
//			
//			return;
//			
//		}
//	  }
	
	public static void main (String args[]) throws InterruptedException  {	
		
		
		//get rid of main in start up and call start up from here
		
		StartUp  StartUp = new StartUp();      
		      StartUp.showDialogDemo();
//		      System.out.println(StartUp.getDone());
		while (true)
        {
            
			Thread.sleep(500);
            if (StartUp.getDone()==2){ 
                BankAccount Player1 = new BankAccount(1500);
				Player1.setName(StartUp.Player1Name());
				BankAccount Player2 = new BankAccount(1500);
				Player2.setName(StartUp.Player2Name());
				BankAccount Player3 = new BankAccount(1500);
				Player3.setName(StartUp.Player3Name());
	  		JOptionPane.showMessageDialog(null,Player1.getName() + " contains :  $" + Player1.getBalance());
	  			Monopoly game = new Monopoly();		
	  			
	  			
	      		//game.tour();
	      		//game.echo();
	  			game.input();
	      		
	      		
            }	
        
		
        }
	}
}