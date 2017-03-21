
public class Property extends Square {

	private boolean isOwned;
	private int value;
	private int rent[];
	private Player owner;
	private String color;
	int i = 0;
	
	Property (String name, int value, int[] rent, String color) {
		super(name);
		this.value = value;
		this.rent = rent;
		isOwned = false;
		this.color = color;
		return;
	}
	
	public int getValue () {
		return value;
	}
	
	public int getRent () {
		return rent[i];
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
	
	public Player getOwner () {
		return owner;
	}
	public String getColor (){
		return color;
	}
	public void removeOwner (){// remove the owner of said property . for example when bankrupt
		isOwned = false;
	}
}

