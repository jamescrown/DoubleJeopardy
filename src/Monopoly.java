import java.util.ArrayList;

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
	      		game.echo();
	      		
	      		
            }	
        
		
        }
	}
}