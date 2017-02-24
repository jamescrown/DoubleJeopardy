
public class Player {
	
	private int position;
	public String name;
	
	Player () {
		position = 0;
		name = "";
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
	
	public void setName(String playername){
		name = playername;
	}
	
	public String getName(){
		return name;
	}
	
	
	
}