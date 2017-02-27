
public class Player {
	
	
	//keeps track of player name, bank balance, position on the board and properties owned
	private int position;
	public String name;
	private double balance;
	private int[] properties = new int[30]; //array of property positioned owned
	
	Player (double initialBalance) {
		position = 0;//start on go
		name = "";
		balance = initialBalance;
		return;
	}
	
	//this method checks if a property at a certain position is owned by the players
	public boolean owned(int position){
		for(int x:properties){ 
			if(x == position){	//goes through the array of properties owned and checks if the current position is in that array
				return true;
			}
		}
		return false;
	}
	
	//this method returns all the property positions owned by the player
	public int[] allPropertiesOwned(){
		int numberOwned = 0;
		//this for loop calculates the number of properties the player owns
		//so we can make use of number owned instead of a possibly bigger array of size 30
		for(int i = 0; i<properties.length; i++){
			if(properties[i] != 0){
				numberOwned++;
			}
		}
		
		int[] ownedProperties = new int[numberOwned];
		int j = 0;
		for(int i = 0; i<properties.length; i++){	
				if(properties[i] != 0){
					ownedProperties[j] = properties[i]; //this adds the properties owned to our new array which will contain no zeros(the equivalent of empty spaces in the array)
					j++;
				}
		}
		
		return ownedProperties;
	}
	
	//method adds the property just bought to the array of properties owned
	public void addToProperties(int position){
		for(int i = 0; i<properties.length; i++){
			if(properties[i] == 0){
				properties[i] = position;
				return;
			}
		}
	}
	
	//removes the property from the array of those owned when sold
	public void sellProperty(int position){
		for(int i = 0; i<properties.length; i++){
			if(properties[i] == position){
				properties[i] = 0;
				return;
			}
		}
	}
	
	//moves the player piece
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
	
	//adds money to the players bank account e.g. when selling a property or receiving rent
	public void deposit(double amount) {  
		   balance = balance + amount;
	}
	
	//withdraws money when buying property or paying rent, etc.
	public void withdraw(double amount) {  
		   balance = balance - amount;
	}
	
	public double getBalance(){  
		   return balance; 
	}
	
	//assigns the names given at startup to the correct players
	public void setName(String playername){
		name = playername;
	}
	
	public String getName(){
		return name;
	}
	
	
	
}