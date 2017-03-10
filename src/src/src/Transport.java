
public class Transport extends Square{

private boolean isOwned;
private int value;
private int rent[];
private Player owner;

Transport (String name, int value, int[] rent) {
	super(name);
	this.value = value;
	this.rent = rent;
	isOwned = false;
	return;
}

public int getValue () {
	return value;
}

public int getRent () {
	//if own 1 return rent[0];
	//if own 2 return rent[1];
	//etc.
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

}
