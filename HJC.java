import java.util.ArrayList;


public class HJC implements Bot {
	
	// The public API of YourTeamName must not change
	// You cannot change any other classes
	// YourTeamName may not alter the state of the board or the player objects
	// It may only inspect the state of the board and the player objects
  //build houses in three , build seven steps ahead of opponents, best areas are orange, red, pink
  
  
  BoardAPI board;
	PlayerAPI player;
	DiceAPI dice;
	boolean rollDone;
	boolean doubles;
	int doubleCount = 0;
	int jailCount = 0;
	boolean MortgageNeeded = false;
	boolean redeemCheck = true;
	
	HJC (BoardAPI board, PlayerAPI player, DiceAPI dice) {
		this.board = board;
		this.player = player;
		this.dice = dice;
		rollDone = false;
		doubles = false;
		return;
	}
	
	public String getName () {
		return "HJC";
	}
	
	public String getCommand () {
		doubles = false;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
		if(dice.isDouble() && doubleCount<3){//only add doubles if the player has less than 3 double counts, preventing the bot from rolling again while in jail
			doubles = true;
			doubleCount++;
		}
		if(!player.isInJail()){//reset the jail turn counter
			jailCount =0;
		}
		if(player.isInJail()){//if the player is in jail set doubles to false so he doesnt roll again
			doubles = true;
			rollDone = false;
			if(player.hasGetOutOfJailCard()){//if bot has a get out of jail card , use it
				return "card";
			}
			else if(player.getBalance() > 50){//or else if he has a decent amount of money pay the bail
				return "pay";
			}
			else if(player.getBalance() <= 50){
				return checkToMortgage();
			}
			else if(!rollDone){
				return"roll";
			}
			else{//end your turn and increase the jailcount by one if youre still in jail
				jailCount++;
				return "done";
			}
		}

	else if(!player.isInJail()){	
		Square square = board.getSquare(player.getPosition());
		
		if (square instanceof Property && ((Property) square).isOwned() && !((Property) square).getOwner().equals(player) ) {
			//int rent = ((Property) square).getRent();
			if(player.getBalance()< 0 && !MortgageNeeded){
				MortgageNeeded = true;
				return "balance";
