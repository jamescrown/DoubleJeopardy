import java.util.ArrayList;
import javax.swing.*;

public class Monopoly {

	public static final int MAX_NUM_PLAYERS = 6;
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
	
	private void echo () {
		String command;
		ui.display();
		ui.displayString("ECHO MODE");
		do {
			command = ui.getCommand();
			ui.displayString(command);
		} while (!command.equals("quit"));
		return;
	}
	
	public static void main (String args[]) {	
		//bank account code to open accounts and get starting money/print
				BankAccount Player1 = new BankAccount(1500);
				double balance = Player1.getBalance();
				BankAccount Player2 = new BankAccount(1500);
				double balance2 = Player2.getBalance();
				BankAccount Player3 = new BankAccount(1500);
				double balance3 = Player3.getBalance();
				JOptionPane.showMessageDialog(null,"player1 contains :  $" +balance);
				JOptionPane.showMessageDialog(null,"player2 contains :  $" +balance2);
				JOptionPane.showMessageDialog(null,"player3 contains :  $" +balance3);
				//myFirstAccount.withdraw(200);
				
		Monopoly game = new Monopoly();		
		//game.tour();
		game.echo();
		return;
	}
}
