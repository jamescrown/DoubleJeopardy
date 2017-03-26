
public class Property extends Square {

	private boolean isOwned;
	private int value;
	private boolean mortgaged;
	private int rent[];
	private int mortgage[];
	private Player owner;
	private String color;
	private int housePrice;
	int i = 0;
	
	Property (String name, int value, int[] rent, String color, int housePrice) {
		super(name);
		this.value = value;
		this.rent = rent;
		isOwned = false;
		this.color = color;
		this.housePrice = housePrice;
		return;
	}
	
	public int getValue () {
		return value;
	}
	
	public int getRent () {
		return rent[i];
	}
	
	public int getMortgage() {
		return mortgage[i];
	}
	
	
	public boolean isOwned () {
		return isOwned;
	}
	
	
	public void setBuilding(){
		i++;
		return ;
	}
	
	public int numberBuildings(){
		return i;
	}
	
	public void setOwner (Player inPlayer) {
		owner = inPlayer;
		isOwned = true;
		return;
	}
	
	public boolean isMortgaged(Player inPlayer) { // gets the mortgaged status of the property.
		return mortgaged;
	}
	
	public void setMortgage(boolean bool) { //un-mortgages the property.. will use later on.
		mortgaged = bool;
	}
	
	public Player getOwner () {
		return owner;
	}
	public String getColor (){
		return color;
	}
	public void removeOwner (){// remove the owner of said property . for example when bankrupt
		isOwned = false;
	}
	public void suspendRent() { // suspend rent when mortgaged.
		isOwned = false;
	}
	public int getPrice(){
		return housePrice;
	}
}

