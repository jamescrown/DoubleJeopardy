
public class Player {
	
	private int position;
	public String name;
	private double balance; 
	
	Player (double initialBalance) {
		position = 0;
		name = "";
		balance = initialBalance;
		return;
	}
	
	public void move (int squares) {
		position = position + squares;
		if (position < 0) {
			position = position + Monopoly.NUM_SQUARES;
		} else if (position >= Monopoly.NUM_SQUARES) {
			position = position % Monopoly.NUM_SQUARES;
		}
		return;
	}
	
	public int getPosition () {
		return position;
	}
	
	public void deposit(double amount) {  
		   balance = balance + amount;
	}
	
	public void withdraw(double amount) {  
		   balance = balance - amount;
	}
	
	public double getBalance(){  
		   return balance; 
	}
	
	public void setName(String playername){
		name = playername;
	}
	
	public String getName(){
		return name;
	}
	
	
	
}