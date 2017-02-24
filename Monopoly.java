import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class Monopoly {

	public static final int MAX_NUM_PLAYERS = 3;
	public static final int NUM_SQUARES = 40;
	
	static Properties pr = new Properties();
	
	private  ArrayList<Player> players = new ArrayList<Player>();
	private UI ui = new UI(players);
	
	Monopoly () {
		for (int p=0; p<MAX_NUM_PLAYERS; p++) {
			players.add(new Player());
			players.get(p).setName(StartUp.Player1Name());
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
			
			//go through the players
			//problem: only does this once
			
			for (int p=0; p<MAX_NUM_PLAYERS; p++) {
				ui.displayString("Your turn, " + players.get(p).getName());
				
				do {
					ui.displayString("What would you like to do?");
					
					text = ui.getCommand();
					ui.displayString("You chose : " + text);
				
					if(text.equalsIgnoreCase("Roll")) {
						int jailTest = 0; //tests how many times in a row you have rolled doubles
						roll(jailTest, p);
					}
					else if (text.equalsIgnoreCase("Buy")){
						//buy();
					}
					else if (text.equalsIgnoreCase("Balance")){
						//displayBalance();
					}
					else if (text.equalsIgnoreCase("Sell")){
						//sell();
					}
					else if (text.equalsIgnoreCase("Help")){
						queryList();
					}
					else if (text.equalsIgnoreCase("End Roll")){
						break;
					}
					else{
						ui.displayString("Invalid Command. Enter Help for a query list.");
					}
					
					//displays info on the property you are on
					ui.displayString("You landed on:\n" + Properties.GetPropertyName(players.get(p).getPosition()));
					ui.displayString("Price = " + Properties.GetPropertyPrice(players.get(p).getPosition()));
					
			} while (!text.equals("quit"));
			
				//break;	
	  }
	  }
	
	  public void queryList(){
		  ui.displayString("Valid commands:\nRoll\nBuy\nSell\nPayRent\nBalance\nEndRoll");
	  }
	  
	  public void buy(){
		  ui.displayString("Would you like to buy this property? ");
		  //add in James code...
	  }
	  
	  public void roll(int jailTest, int p){
		  	
		  	Random rand = new Random();
			int roll1 = rand.nextInt(5) + 1;
			int roll2 = rand.nextInt(5) + 1;
			int sum = roll1 + roll2;
			
			//fix so that it stops before rolling again when you roll doubles
			if (roll1 == roll2 && jailTest < 3){
				jailTest++;
				ui.displayString("You rolled a " + roll1 + " and a " + roll2 + " = " + sum);
				
				ui.displayString("Doubles! \nRolling again!");
				players.get(p).move(sum);
				ui.display();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					System.out.println("Sleep exeception.");
				} 
				roll(jailTest, p);
			}
			else if (roll1 == roll2 && jailTest == 3){
				ui.displayString("\nUh Oh, You rolled doubles three times in a row!\nOff to Jail!");
				//set position as jail
			}
			else{
				ui.displayString("You rolled a " + roll1 + " and a " + roll2 + " = " + sum);
				players.get(p).move(sum);
				ui.display();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					System.out.println("Sleep exeception.");
				} 
			}
	  }
	
	public static void main (String args[]) throws InterruptedException  {	
		
		
		
		StartUp  StartUp = new StartUp();      
		      StartUp.showDialogDemo();
		while (true)
        {
            
			Thread.sleep(500);
            if (StartUp.getDone()==2){ 
                BankAccount Player1 = new BankAccount(1500);
				Player1.setName(StartUp.Player1Name());
				//players.get(0).setName(StartUp.Player1Name());
				BankAccount Player2 = new BankAccount(1500);
				Player2.setName(StartUp.Player2Name());
				//players.get(1).setName(StartUp.Player2Name());
				BankAccount Player3 = new BankAccount(1500);
				Player3.setName(StartUp.Player3Name());
				//players.get(2).setName(StartUp.Player3Name());
				
	  		JOptionPane.showMessageDialog(null,Player1.getName() + " contains :  $" + Player1.getBalance());
	  			Monopoly game = new Monopoly();		
	  			
	  			
	      		//game.tour();
	      		//game.echo();
	  			game.input();
	      		
	      		
            }	
        
		
        }
	}
}