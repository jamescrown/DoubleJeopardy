
public class Utilities extends Property{

private boolean isOwned;
private int value;
private int rent[];
private Player owner;
private static String color;

Utilities (String name, int value, int[] rent) {
	super(name, value, rent, color);
	this.value = value;
	this.rent = rent;
	isOwned = false;
	color = "utilities";
	return;
}

public int getValue () {
	return value;
}

public int getRent () {
	//return rent[0]*roll;
	return rent[0];
}

public boolean isOwned () {
	return isOwned;
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

}



