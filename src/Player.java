
public class Player {
	
	private int position;
	public String name;
	private double balance;
	private int[] properties = new int[30];
	
	Player (double initialBalance) {
		position = 0;
		name = "";
		balance = initialBalance;
		return;
	}
	//send in position number; check if ownedby anyone - int is in the array? true if owned 
	public boolean owned(int position){
		for(int x:properties){
			if(x == position){
				return true;
			}
		}
		return false;
	}
	
	public int[] allPropertiesOwned(){
		int numberOwned = 0;
		for(int i = 0; i<properties.length; i++){
			if(properties[i] != 0){
				numberOwned++;
			}
		}
		
		int[] ownedProperties = new int[numberOwned];
		int j = 0;
		for(int i = 0; i<properties.length; i++){	
				if(properties[i] != 0){
					ownedProperties[j] = properties[i];
					j++;
				}
		}
		
		return ownedProperties;
	}
	
	public void addToProperties(int position){
		for(int i = 0; i<properties.length; i++){
			if(properties[i] == 0){
				properties[i] = position;
				return;
			}
		}
	}
	
	public void sellProperty(int position){
		for(int i = 0; i<properties.length; i++){
			if(properties[i] == position){
				properties[i] = 0;
				return;
			}
		}
	}
	public void move (int squares) {
		position = position + squares;
		if (position < 0) {
			position = position + Monopoly.NUM_SQUARES;
		} //else if (position >= Monopoly.NUM_SQUARES) {
		//	position = position % Monopoly.NUM_SQUARES;
		//}
		return;
	}
	
	public int getPosition () {
		return position;
	}
	public void resetPosition() {
		position = position % Monopoly.NUM_SQUARES;
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
