public class Card{
	
private String name;
private String type;
private int squaresOrMoney;
	
	Card(String name, String type, int squaresOrMoney) {
		this.name = name;
		this.type = type;
		this.squaresOrMoney = squaresOrMoney;
		return;
	}
	
	public String getName () {
		return name;
	}
	
	public String getType(){
		return type;
	}
	
	public int getFigure(){
		return squaresOrMoney;
	}
	
	public String toString () {
		return name;
	}
	
	
}
