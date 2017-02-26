import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Monopoly {

	public static final int MAX_NUM_PLAYERS = 3;
	public static final int NUM_SQUARES = 40;
	static int p ;
	
	
	
	static Properties pr = new Properties();
	
	private  ArrayList<Player> players = new ArrayList<Player>();
	private UI ui = new UI(players);
	
	
	
	Monopoly () {
		for (int p=0; p<MAX_NUM_PLAYERS; p++) {
			players.add(new Player(1500));
		}
		players.get(0).setName(StartUp.Player1Name());
		players.get(1).setName(StartUp.Player2Name());
		players.get(2).setName(StartUp.Player3Name());
		
		
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
			int jailTest = 0;
			boolean rolled = false;
			int youOwn = 0;
			int theyOwn = -1;
			int noOneOwns = 0;
			
			//go through the players
			
			if(p == MAX_NUM_PLAYERS){
				p = 0;
			}
			
			ui.displayString("Your turn, " + players.get(p).getName());
				
				do {
					ui.displayString("What would you like to do?");
					
					text = ui.getCommand();
					ui.displayString("You chose : " + text);
				
					//if statements for inputs
					
					if(text.equalsIgnoreCase("Roll")) {
						//have a look at this clo problems with doubles
						if (rolled == true && jailTest == 0){
							ui.displayString("Sorry, You've already Rolled!");
						}
						else{
						
						//tests how many times in a row you have rolled doubles
						jailTest = roll(jailTest, p);
						
						if (jailTest > 0 && jailTest < 3){
							ui.displayString("Doubles! You can roll again!");
						}
						
						else if (jailTest == 3){
							ui.displayString("\nUh Oh, You rolled doubles three times in a row!\nOff to Jail!");
							//set position as jail
							break;
						}
						//displays info on the property you are on
						if(players.get(p).getPosition() == 0){
							ui.displayString("You've landed on GO!\n Collect $200");
							players.get(p).deposit(200);
							ui.displayString("Your new bank balance is $" + players.get(p).getBalance());
						}
						else{
							ui.displayString("You landed on:\n" + Properties.GetPropertyName(players.get(p).getPosition()));
						}
						
						//check if owned if owned say you must pay rent, otherwise display price
						
						for(int pls = 0; pls < MAX_NUM_PLAYERS; pls++){
							//owned by any player
							if(players.get(pls).owned(players.get(p).getPosition())){
								if(pls == p){
									ui.displayString("You already own this property!");
									youOwn++;
								}
								else{
									ui.displayString(players.get(pls).getName() + " owns this property.\nYou must pay them rent.");
									theyOwn = pls;
								}
							}
							else{
								ui.displayString("No one owns this property");
								noOneOwns++;
							}
						}
						
//						ui.displayString(" " + noOneOwns);
//						ui.displayString("Owned? " + players.get(p).owned(players.get(p).getPosition()));
//						ui.displayString("Price = " + Properties.GetPropertyPrice(players.get(p).getPosition()));
						rolled = true;
						}	
					}
					
					else if (text.equalsIgnoreCase("Buy")){
						if (noOneOwns != 0 && (players.get(p).owned(players.get(p).getPosition()) == false)){
							buy(p);
						}
						else{
							ui.displayString("You Can't Buy This Property");
						}
					}
					
					else if (text.equalsIgnoreCase("Balance")){
						ui.displayString("$" + players.get(p).getBalance());
					}
					
					else if (text.equalsIgnoreCase("Sell")){
						if (youOwn == 1 || players.get(p).owned(players.get(p).getPosition())){
							sell(p);
						}
						else{
							ui.displayString("You Can't sell a property you don't own");
						}
					}
					
					else if (text.equalsIgnoreCase("Pay Rent")){
						if (theyOwn == 1){
							payRent(p, theyOwn);
						}
						else{
							ui.displayString("You don't need to pay rent!");
						}
					}
					
					else if (text.equalsIgnoreCase("Help")){
						queryList();
						//displays info on the property you are on
						ui.displayString("You landed on:\n" + Properties.GetPropertyName(players.get(p).getPosition()));
						ui.displayString("Price = " + Properties.GetPropertyPrice(players.get(p).getPosition()));
						
					}
					
					else if (text.equalsIgnoreCase("End Turn")){
						ui.displayString("Turn over");
					}
					
					else if (text.equalsIgnoreCase("Quit")){
						quit();
					}
					
					else{
						ui.displayString("Invalid Command. Enter Help for a query list.");
					}
					
				
			} while (!text.equals("End Turn"));
			
		p++;
		input(p);
				
	  }
	  
	
	  public void queryList(){
		  ui.displayString("Valid commands:\nRoll\nBuy\nSell\nPayRent\nBalance\nEndTurn\nQuit\n");
	  }
	  
	  public void buy(int player){
		  ui.displayString("Congratulations! You now own this property.");
		  int price = Properties.GetPropertyPrice(players.get(player).getPosition());
		  players.get(player).withdraw(price);
		  ui.displayString("Your new bank balance is $" + players.get(player).getBalance());
		  players.get(player).addToProperties(players.get(player).getPosition());
		  
	  }
	  
	  public void sell(int player){
		  //can only sell if you own it
		  int price = Properties.GetPropertyPrice(players.get(player).getPosition());
		  ui.displayString("You Sold "+ Properties.GetPropertyName(players.get(p).getPosition()) + " for $" + price);
		  players.get(player).deposit(price);
		  ui.displayString("Your new bank balance is $" + players.get(player).getBalance());
		  players.get(player).sellProperty(players.get(player).getPosition());
		  
	  }
	  
	  public void payRent(int player, int theyOwn){
		  int rent = Properties.GetPropertyRent(players.get(player).getPosition());
		  ui.displayString("You have paid $" + rent + " rent to " + players.get(theyOwn).getName());
		  //increase his account
		  players.get(theyOwn).deposit(rent);
		  //decrease your account
		  players.get(player).withdraw(rent);
		  ui.displayString("Your new bank balance is $" + players.get(player).getBalance());
	  }
	  
	  public int roll(int jailTest, int p){
		  	
		  	Random rand = new Random();
			int roll1 = rand.nextInt(5) + 1;
			int roll2 = rand.nextInt(5) + 1;
			int sum = roll1 + roll2;
			
			ui.displayString("You rolled a " + roll1 + " and a " + roll2 + " = " + sum);
			
				players.get(p).move(sum);
				ui.display();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					System.out.println("Sleep exeception.");
				} 
				
			if(roll1 == roll2){//doubles
				jailTest++;
			}
			
				return jailTest;
			
	  }
	  
	  public void quit(){
		  JFrame frame = new JFrame();
		  String[] options = new String[2];
		  options[0] = new String("Yes");
		  options[1] = new String("No");
		  int choice = JOptionPane.showOptionDialog(frame.getContentPane(), "Are you sure you want to quit?", "Quit", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
		  if(choice == 0){
			  System.exit(0);
		  }
		  else{
			  return;
		  }
	  
	  }
	
	public static void main (String args[]) throws InterruptedException  {	
		
		
		
		StartUp  StartUp = new StartUp();      
		      StartUp.showDialogDemo();
		while (true)
        {
            
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