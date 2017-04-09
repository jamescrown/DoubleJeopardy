
public class Cards {

	public static final int NUM_CARDS = 32;
	private Card[] chance = new Card[NUM_CARDS/2];
	private Card[] community_chest = new Card[NUM_CARDS/2];
	
	
	Cards() {
		
		community_chest[0] = new Card("Advanced to Go.", "move", 0); 
		         chance[0] = new Card("Advanced to Go.", "move", 0);
		community_chest[1] = new Card("Go back to Old Kent Road.", "move", 1);
				 chance[1] = new Card("Go to Jail. Move directly to Jail. Do not pass go. ", "jail", 10);
		community_chest[2] = new Card("Go to jail. Move directly to jail. Do not pass Go. Do not collect £200.", "jail", 10);
		         chance[2] = new Card("Advance to Pall Mall. If you pass Go collect £200.", "move", 11);
		community_chest[3] = new Card("Pay hospital £100.","fine",-100);
			     chance[3] = new Card("Take a trip to Marylebone Station and if you pass Go collect £200.","move",15);
		community_chest[4] = new Card("Doctor's fee. Pay £50.","fine",-50);
 				 chance[4] = new Card("Advance to Trafalgar Square. If you pass Go collect £200.","move",24);
 		community_chest[5] = new Card("Pay your insurance premium £50.","fine",-50);
 				 chance[5] = new Card("Advance to Mayfair.","move",39); 
 		community_chest[6] = new Card("Bank error in your favour. Collect £200.","inherit",200);
 			     chance[6] = new Card("Go back three spaces.","moveSpaces",-3);
 		community_chest[7] = new Card("Annuity matures. Collect £100.","inherit",100);
 				 chance[7] = new Card("Make general repairs on all of your houses. For each house pay £25. For each hotel pay £100.","houses", 25);
 		community_chest[8] = new Card("You inherit £100.","inherit",100);
 				 chance[8] = new Card("You are assessed for street repairs: £40 per house, £115 per hotel.","houses", 40);
 		community_chest[9] = new Card("From sale of stock you get £50.","inherit",50);
 			     chance[9] = new Card("Pay school fees of £150.","fine",-150);
 		community_chest[10] = new Card("Receive interest on 7% preference shares: £25.","inherit",25);
 			     chance[10] = new Card("Drunk in charge fine £20.","fine",-20);
 		community_chest[11] = new Card("Income tax refund. Collect £20.","inherit",20);
 			     chance[11] = new Card("Speeding fine £15.","fine",-15);
 		community_chest[12] = new Card("You have won second prize in a beauty contest. Collect £10.","inherit",10);
 			     chance[12] = new Card("Your building loan matures. Receive £150.","inherit",150);
 		community_chest[13] = new Card("It is your birthday. Collect £10 from each player.","eachPlayer",10);
 			     chance[13] = new Card("You have won a crossword competition. Collect £100.","inherit",100);
 		community_chest[14] = new Card("Get out of jail free. This card may be kept until needed or sold.","jailCard",0);
 			     chance[14] = new Card("Bank pays you dividend of £50.","inherit",50);
 		community_chest[15] = new Card("Pay a £10 fine or take a Chance.","fineOrChance", 10);
 		         chance[15] = new Card("Get out of jail free. This card may be kept until needed or sold","jailCard", 0);
 
	}
	
	public Card getCommunityChestCard (int index) {
		return community_chest[index];
	}
	
	public Card getChanceCard(int index){
		return chance[index];
	}
}
