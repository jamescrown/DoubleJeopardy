
public class Transport extends Property{

private boolean isOwned;
private int value;
private int rent[];
private Player owner;
private static String color;
int numberOf = 0;

Transport (String name, int value, int[] rent) {
	super(name, value, rent, color, value);
	this.value = value;
	this.rent = rent;
	isOwned = false;
	color = "transport";
	return;
	
}

public int getValue () {
	return value;
}

public int getRent () {
	return rent[numberOf];
}

//call this when buying
public void ownMultiples(int i){
	numberOf = i-1;
	return ;
}

public int numberOwned(){
	return numberOf+1;
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
