
public class Cards {

	public static final int NUM_CARDS = 32;
	private Card[] community_chest,chance = new Card[NUM_CARDS];
	
	
	Cards() {
		
		community_chest[0] = new Card("Advanced to Go."); 
		         chance[1] = new Card("Advanced to Go.");
		community_chest[2] = new Card("Go back to Old Kent Road.");
				 chance[3] = new Card("Go to Jail. Move directly to Jail. Do not pass go. ");
		community_chest[4] = new Card("Go to jail. Move directly to jail. Do not pass Go. Do not collect £200.");
		         chance[5] = new Card("Advance to Pall Mall. If you pass Go collect £200.");
		community_chest[6] = new Card("Pay hospital £100.");
			     chance[7] = new Card("Take a trip to Marylebone Station and if you pass Go collect £200.");
		community_chest[8] = new Card("Doctor's fee. Pay £50.");
 				 chance[9] = new Card("Advance to Trafalgar Square. If you pass Go collect £200.");
 		community_chest[10] = new Card("Pay your insurance premium £50.");
 				 chance[11] = new Card("Advance to Mayfair."); 
 		community_chest[12] = new Card("Bank error in your favour. Collect £200.");
 			     chance[13] = new Card("Go back three spaces.");
 		community_chest[14] = new Card("Annuity matures. Collect £100.");
 				 chance[15] = new Card("Make general repairs on all of your houses. For each house pay £25. For each hotel pay £100.");
 		community_chest[16] = new Card("You inherit £100.");
 				 chance[17] = new Card("You are assessed for street repairs: £40 per house, £115 per hotel.");
 		community_chest[18] = new Card("From sale of stock you get £50.");
 			     chance[19] = new Card("Pay school fees of £150.");
 		community_chest[20] = new Card("Receive interest on 7% preference shares: £25.");
 			     chance[21] = new Card("Drunk in charge fine £20.");
 		community_chest[22] = new Card("Income tax refund. Collect £20.");
 			     chance[23] = new Card("Speeding fine £15.");
 		community_chest[24] = new Card("You have won second prize in a beauty contest. Collect £10.");
 			     chance[25] = new Card("Your building loan matures. Receive £150.");
 		community_chest[26] = new Card("It is your birthday. Collect £10 from each player.");
 			     chance[27] = new Card("You have won a crossword competition. Collect £100.");
 		community_chest[28] = new Card("Get out of jail free. This card may be kept until needed or sold.");
 			     chance[29] = new Card("Bank pays you dividend of £50.");
 		community_chest[30] = new Card("Pay a £10 fine or take a Chance.");
 		         chance[31] = new Card("Get out of jail free. This card may be kept until needed or sold");
 
	}
	
}
