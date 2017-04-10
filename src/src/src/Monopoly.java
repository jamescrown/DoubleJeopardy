import java.util.ArrayList;


public class Monopoly {

	private static final int START_MONEY = 1500;
	private static final int GO_MONEY = 200;
	
	private Players players = new Players();
	private Player currPlayer;
	private Dice dice = new Dice();
	private Cards cards = new Cards();
	private Board board = new Board(dice);
	private UI ui = new UI(players, board);
	private boolean gameOver = false;
	private boolean onlyOneNotBankrupt = false;
	private boolean turnFinished;
	private boolean rollDone;
	private boolean rentOwed;
	private boolean taxOwed;
	private boolean rentPaid;
	private boolean taxPaid;
	private boolean cardMovement;
	private int doubles;
	
	Monopoly () {
		ui.display();
		return;
	}
		
	public void inputNames () {
		int playerId = 0;
		do {
			ui.inputName(playerId);
			if (!ui.isDone()) {
				boolean duplicate = false;
				for (Player p : players.get()) {
					if (ui.getString().toLowerCase().equals(p.getName().toLowerCase())) {
						duplicate = true;
					}
				}
				if (!duplicate) {
					players.add(new Player(ui.getString(),ui.getTokenName(playerId),playerId));
					playerId++;
				} else {
					ui.displayError(UI.ERR_DUPLICATE);
				}
			}
		} while (!ui.isDone() && players.canAddPlayer());
		return;
	}
	
	public void giveStartMoney () {
		for (Player p : players.get()) {
			p.doTransaction (START_MONEY);
			ui.displayBankTransaction (p);
		}
		return;
	}
	
	public void decideStarter () {
		Players inPlayers = new Players(players), selectedPlayers = new Players();
		boolean tie = false;
		do {
			int highestTotal = 0;
			for (Player p : inPlayers.get()) {
				dice.roll();
				ui.displayDice(p,dice);
				if (dice.getTotal() > highestTotal) {
					tie = false;
					highestTotal = dice.getTotal();
					selectedPlayers.clear();
					selectedPlayers.add(p);
				} else if (dice.getTotal() == highestTotal) {
					tie = true;
					selectedPlayers.add(p);
				}
			}
			if (tie) {
				ui.displayRollDraw();
				inPlayers = new Players(selectedPlayers);
				selectedPlayers.clear();
			}
		} while (tie);
		currPlayer = selectedPlayers.get(0);
		ui.displayRollWinner(currPlayer);
		ui.display();
		return;
	}
	
	private void processRoll (boolean cardMovement) {
		if (currPlayer.getBalance()>=0){
			if (!rollDone) {
				if(!taxOwed){
					if (!rentOwed) {
						
						if(!cardMovement){//don't roll if you are moving as the result of a card
							dice.roll();
							ui.displayDice(currPlayer, dice);
							
						}
						
						
						if (dice.isDouble()){
							doubles++;
						}	
						
							if(doubles != 3){
								if(!cardMovement){//don't move again if you have moved as the result of a card
									currPlayer.move(dice.getTotal());
									//currPlayer.move(30); //testing
								}
								
								ui.display();
								if (currPlayer.passedGo()) {
									currPlayer.doTransaction(+GO_MONEY);
									ui.displayPassedGo(currPlayer);
									ui.displayBankTransaction(currPlayer);
								}
								ui.displaySquare(currPlayer, board, dice);
								if (board.getSquare(currPlayer.getPosition()).getName() == "Income Tax" || board.getSquare(currPlayer.getPosition()).getName() == "Super Tax" ){
									taxPaid = false;
									processTax(board.getSquare(currPlayer.getPosition()).getName());
								}
								
								if(board.getSquare(currPlayer.getPosition()).getName() == "Go To Jail"){
									currPlayer.goToJail(); //jail is square 10
									ui.displayString("You have been sent to jail");
									ui.displayJail();
									doubles = 0;
									rollDone=true;
									//in jail
								}
								
								if(board.getSquare(currPlayer.getPosition()).getName() == "Community Chest" || board.getSquare(currPlayer.getPosition()).getName() == "Chance"){
									processCard(board.getSquare(currPlayer.getPosition()).getName());
								}
								if (board.getSquare(currPlayer.getPosition()) instanceof Property && 
										((Property) board.getSquare(currPlayer.getPosition())).isOwned() &&
										!((Property) board.getSquare(currPlayer.getPosition())).getOwner().equals(currPlayer) ) {
											processPayRent();
//											rentOwed = true;
//											rentPaid = false;
								} else {
									rentOwed = false;
								}
								if (!dice.isDouble()) {
									rollDone = true;
								}
							}else{
								
								currPlayer.goToJail();
								ui.display();
								ui.displayString("You rolled doubles 3 times and have been sent to jail");
								ui.displayJail();
								doubles = 0;
								rollDone=true;

							}
					} else {
						ui.displayError(UI.ERR_RENT_OWED);	
					}
				} else{
					ui.displayError(UI.ERR_TAX_OWED);
				}
			} else {
				ui.displayError(UI.ERR_DOUBLE_ROLL);
			}
		} else{
			ui.displayError(UI.ERR_NEG_BALANCE);
		}
		return;
	}
	
	private void processPayRent () {
		if (board.getSquare(currPlayer.getPosition()) instanceof Property) {
			Property property = (Property) board.getSquare(currPlayer.getPosition());
			if (property.isOwned()) {
				if (!property.getOwner().equals(currPlayer)) {
					if (!rentPaid) {
						int rent = property.getRent();
						//if (currPlayer.getBalance()>=rent) {
							Player owner = property.getOwner();
							currPlayer.doTransaction(-rent);
							owner.doTransaction(+rent);
							ui.displayTransaction(currPlayer, owner);
							rentPaid = true;	
							rentOwed = false;
//						} else {
//							ui.displayError(UI.ERR_INSUFFICIENT_FUNDS);										
//						} 
					} else {
						ui.displayError(UI.ERR_RENT_ALREADY_PAID);									
					}
				} else {
					ui.displayError(UI.ERR_SELF_OWNED);								
				}
			} else {
				ui.displayError(UI.ERR_NOT_OWNED);							
			}
		} else {
			ui.displayError(UI.ERR_NOT_A_PROPERTY);
		}
		return;
	}
	private void processPayTax(){
		if (board.getSquare(currPlayer.getPosition()).getName() == "Income Tax" || board.getSquare(currPlayer.getPosition()).getName() == "Super Tax" ){
				
					if (!taxPaid) {
						processTax(board.getSquare(currPlayer.getPosition()).getName()); 
					} else {
						ui.displayError(UI.ERR_TAX_ALREADY_PAID);									
					}
				
			
		} else {
			ui.displayError(UI.ERR_NOT_A_TAX);
		}
		return;
	}
	
	private void processCard(String TypeCard){
		ui.displayString("You drew the card:");
		Card card;
		int whichCard = (int)(Math.random() * 16);
		//int whichCard = 15; //to test each of the cards
		if(TypeCard == "Community Chest"){
			card = cards.getCommunityChestCard(whichCard);
		}
		else{
			card = cards.getChanceCard(whichCard);
		}
		
		ui.displayString(card.getName());
		
		if (card.getType()== "fine" || card.getType()== "inherit"){
			currPlayer.doTransaction(card.getFigure());
			ui.displayBankTransaction(currPlayer);
			ui.displayBalance(currPlayer);
		}
		
		else if (card.getType()== "move"){
			currPlayer.setPosition(card.getFigure());
			processRoll(true);
		}
		
		else if (card.getType()== "jail"){
			currPlayer.goToJail();
			ui.displayString("You have been sent to jail");
			ui.displayJail();
			doubles = 0;
			rollDone=true;

		}
		
		else if (card.getType()== "moveSpaces"){
			currPlayer.setPosition(currPlayer.getPosition() + card.getFigure());
			currPlayer.passedGo = false;
			processRoll(true);
		}
		
		else if (card.getType()=="houses"){
			
			int numHouses = 0;
			int housesTotal = 0;
			int numHotels = 0;
			int hotelsTotal = 0;
			currPlayer.getProperties();
			for (Property p : currPlayer.getProperties()){
				if(p instanceof Site){
					if (((Site) p).hasBuildings()){
						if (((Site) p).getNumBuildings()<5){
							numHouses += ((Site) p).getNumBuildings();
						}
						else{ //5 builds -> 1 hotel
							numHotels += (((Site) p).getNumBuildings()-4);
						}
					}
				}
			}
			housesTotal = (card.getFigure()*numHouses);
			hotelsTotal = ((card.getFigure()+75)*numHotels);
			ui.displayString("You own "+ numHouses +" houses. £"+ card.getFigure()+ " each: £" + housesTotal);
			ui.displayString("You own "+ numHotels +" hotels. £"+ (card.getFigure()+75)+ " each: £" + hotelsTotal);
			if((housesTotal+hotelsTotal) != 0){
				currPlayer.doTransaction(-(housesTotal+hotelsTotal));
				ui.displayBankTransaction(currPlayer);
				ui.displayBalance(currPlayer);
			}
			
		}
		
		else if (card.getType()=="eachPlayer"){
			//Collect £10 from each player
			int collection = card.getFigure();
			for (Player p : players.get()) {
				if (p != currPlayer){
					p.doTransaction (-collection);
					currPlayer.doTransaction(+collection);
					ui.displayTransaction (p, currPlayer);
				}
			}
			
			ui.displayBalance(currPlayer);
		}
		
		else if (card.getType()=="jailCard"){
			currPlayer.GainAGetOutOfJailFreeCard();
		}

		else if (card.getType()=="fineOrChance"){
			
			if (ui.fineOrChance(currPlayer)=="fine"){
				currPlayer.doTransaction(-card.getFigure());
				ui.displayBankTransaction(currPlayer);
				ui.displayBalance(currPlayer);
			}
			else{
				processCard("Chance");
			}
			
		}
	}

	private void processBuy () {
		if (board.getSquare(currPlayer.getPosition()) instanceof Property) {
			Property property = (Property) board.getSquare(currPlayer.getPosition());
			if (!property.isOwned()) {
				if (currPlayer.getBalance() >= property.getPrice()) {				
					currPlayer.doTransaction(-property.getPrice());
					ui.displayBankTransaction(currPlayer);
					currPlayer.addProperty(property);
					ui.displayLatestProperty(currPlayer);
				} else {
					ui.displayError(UI.ERR_INSUFFICIENT_FUNDS);
				}
			} else {
				ui.displayError(UI.ERR_IS_OWNED);
			}
		} else {
			ui.displayError(UI.ERR_NOT_A_PROPERTY);
		}
		return;
	}
	
	
	private void processBuild () {
		Property property = ui.getInputProperty();
		if (property.isOwned() && property.getOwner().equals(currPlayer)) {
			if (property instanceof Site) {
				Site site = (Site) property;
				if (currPlayer.isGroupOwner(site)) {
					if (!site.isMortgaged()) {
						int numBuildings = ui.getInputNumber();
						if (numBuildings>0) {
							if (site.canBuild(numBuildings)) {
								int debit = numBuildings*site.getBuildingPrice();
								if (currPlayer.getBalance()>debit) {
									site.build(numBuildings);
									currPlayer.doTransaction(-debit);
									ui.displayBuild(currPlayer,site,numBuildings);
								} else {
									ui.displayError(UI.ERR_INSUFFICIENT_FUNDS);
								}
							} else {
								ui.displayError(UI.ERR_TOO_MANY_BUILDINGS);
							}
						} else {
							ui.displayError(UI.ERR_TOO_FEW_BUILDINGS);
						}
					} else {
						ui.displayError(UI.SITE_IS_MORTGAGED);
					}
				} else {
					ui.displayError(UI.ERR_DOES_NOT_HAVE_GROUP);
				}
			} else {
				ui.displayError(UI.ERR_NOT_A_SITE);
			}
		} else {
			ui.displayError(UI.ERR_NOT_YOURS);
		}
		return;
	}
	
	private void processDemolish () {
		Property property = ui.getInputProperty();
		if (property.isOwned() && property.getOwner().equals(currPlayer)) {
			if (property instanceof Site) {
				Site site = (Site) property;
				int numBuildings = ui.getInputNumber();
				if (numBuildings>0) {
					if (site.canDemolish(numBuildings)) {
						site.demolish(numBuildings);
						int credit = numBuildings * site.getBuildingPrice()/2;
						currPlayer.doTransaction(+credit);
						ui.displayDemolish(currPlayer,site,numBuildings);
					} else {
						ui.displayError(UI.ERR_TOO_MANY_BUILDINGS);
					}
				} else {
					ui.displayError(UI.ERR_TOO_FEW_BUILDINGS);
				}
			} else {
				ui.displayError(UI.ERR_NOT_A_SITE);
			}
		} else {
			ui.displayError(UI.ERR_NOT_YOURS);
		}
		return;		
	}
	
	public void processCheat () {
		switch (ui.getInputNumber()) {
			case 1 :       // acquire colour group
				Property property = board.getProperty("kent");
				currPlayer.addProperty(property);		
				property = board.getProperty("whitechapel");
				currPlayer.addProperty(property);
				break;
			case 2 :	   // make zero balance
				currPlayer.doTransaction(-currPlayer.getBalance());
				break;
		}
		return;
	}
	
	public void processBankrupt () {
		ui.displayBankrupt(currPlayer);
		Player tempPlayer = players.getNextPlayer(currPlayer);
		players.remove(currPlayer);
		currPlayer = tempPlayer;
		if (players.numPlayers()==1) {
			gameOver = true;
			onlyOneNotBankrupt = true;
		}
		ui.display();
		return;
	}
	
	public void processMortgage () {
		Property property = ui.getInputProperty();
		if (property.isOwned() && property.getOwner().equals(currPlayer)) {
			if ((property instanceof Site) && !((Site) property).hasBuildings() || (property instanceof Station) || (property instanceof Utility)) {
				if (!property.isMortgaged()) {
					property.setMortgaged();
					currPlayer.doTransaction(+property.getMortgageValue());
					ui.displayMortgage(currPlayer,property);
				} else {
					ui.displayError(UI.ERR_IS_MORTGAGED);
				}
			} else {
				ui.displayError(UI.ERR_HAS_BUILDINGS);
			}
		} else {
			ui.displayError(UI.ERR_NOT_YOURS);
		}
		return;		
	}
	
	public void processRedeem () {
		Property property = ui.getInputProperty();
		if (property.isOwned() && property.getOwner().equals(currPlayer)) {
			if (property.isMortgaged()) {
				int price = property.getMortgageRemptionPrice();
				if (currPlayer.getBalance() >= price) {
					property.setNotMortgaged();
					currPlayer.doTransaction(-price);
					ui.displayMortgageRedemption(currPlayer,property);
				} else {
					ui.displayError(UI.ERR_INSUFFICIENT_FUNDS);
				}
			} else {
				ui.displayError(UI.ERR_IS_NOT_MORTGAGED);
			}
		} else {
			ui.displayError(UI.ERR_NOT_YOURS);
		}
		return;			
	}

	private void processDone () {
		if (currPlayer.getBalance()>=0){
			if (rollDone) {
				if (!rentOwed || (rentOwed && rentPaid)) {
					if (!taxOwed || (taxOwed && taxPaid)){
					
						turnFinished = true;
						
						ui.clearPanel();
					}
					else{
						ui.displayError(UI.ERR_TAX_OWED);
					}	
				} else {
					ui.displayError(UI.ERR_RENT_OWED);
				}
			} else {
				ui.displayError(UI.ERR_NO_ROLL);
			}
		} else {
			ui.displayError(UI.ERR_NEG_BALANCE);
		}
		return;
	}
	
	public void processTax(String typeTax){
		int taxprice;
		if (typeTax == "Income Tax"){
			taxprice = 200;
		}
		else{
			taxprice = 100;
		}
		if (currPlayer.getBalance() >= taxprice) {				
			currPlayer.doTransaction(-taxprice);
			ui.displayBankTransaction(currPlayer);
			ui.displayBalance(currPlayer);
			taxOwed = false;
		} else {
			ui.displayError(UI.ERR_INSUFFICIENT_FUNDS); //must sell things or declare bankrupt
			taxOwed = true;
		}
		return;
	}
	
	public void processTurn () {
		turnFinished = false;
		rollDone = false;
		rentOwed = false;
		rentPaid = false;
		doubles = 0;
		cardMovement = false;
		if(currPlayer.inJail){
			ui.displayString("IN JAIL");
			ui.displayRemainingNightsInJail(currPlayer);
		}
		do {
			ui.inputCommand(currPlayer);
			switch (ui.getCommandId()) {
				case UI.CMD_ROLL :
					processRoll(cardMovement);
					break;
//				case UI.CMD_PAY_RENT :
//					processPayRent();
//					break;
				case UI.CMD_TAX :
					processPayTax();
					break;
				case UI.CMD_BUY :
					processBuy();
					break;
				case UI.CMD_BALANCE :
					ui.displayBalance(currPlayer);
					break;
				case UI.CMD_PROPERTY :
					ui.displayProperty(currPlayer);
					break;
				case UI.CMD_BANKRUPT :
					processBankrupt();
					turnFinished = true;
					break;
				case UI.CMD_BUILD :
					processBuild();
					break;
				case UI.CMD_DEMOLISH :
					processDemolish();
					break;
				case UI.CMD_REDEEM :
					processRedeem();
					break;
				case UI.CMD_MORTGAGE :
					processMortgage();
					break;
				case UI.CMD_CHEAT :
					processCheat();
					break;
				case UI.CMD_HELP :
					ui.displayCommandHelp();
					break;
				case UI.CMD_DONE :
					processDone();
					break;
				case UI.CMD_QUIT : 
					turnFinished = true;
					gameOver = true;
					break;
				case UI.CMD_ROLLDOUBLE :
					processRollForDoubles();
					break;
				case UI.CMD_PAYBAIL :
					payBail();
					break;
				case UI.CMD_HELPJAIL :
					displayJailHelp();
					break;
				case UI.CMD_JAILCARD :
					processJailCard(currPlayer);
					break;
					
			}
		} while (!turnFinished);
		return;
		
	}
	
	public void payTax(int taxPrice){
		if (currPlayer.getBalance() >= taxPrice) {				
			currPlayer.doTransaction(-taxPrice);
			ui.displayBankTransaction(currPlayer);
			ui.displayBalance(currPlayer);
			taxPaid = true;
		} else {
			ui.displayError(UI.ERR_INSUFFICIENT_FUNDS);
			//if not enough money, must sell things or declare bankrupt
		}
	}
	
	public void nextPlayer () {
		currPlayer = players.getNextPlayer(currPlayer);
		return;
	}
	
	public void decideWinner () {
		if (onlyOneNotBankrupt) {
			ui.displayWinner(currPlayer);			
		} else {
			ArrayList<Player> playersWithMostAssets = new ArrayList<Player>();
			int mostAssets = players.get(0).getAssets();
			for (Player player : players.get()) {
				ui.displayAssets(player);
				if (player.getAssets() > mostAssets) {
					playersWithMostAssets.clear(); 
					playersWithMostAssets.add(player);
				} else if (player.getAssets() == mostAssets) {
					playersWithMostAssets.add(player);
				}
			}
			if (playersWithMostAssets.size() == 1) {
				ui.displayWinner(playersWithMostAssets.get(0));
			} else {
				ui.displayDraw(playersWithMostAssets);
			}
		}
		return;
	}
	
	public void displayGameOver () {
		ui.displayGameOver ();
		return;
	}
	
	public boolean isGameOver () {
		return gameOver;
	}
	
	public void payBail(){// pays 50 to the bank as bail and leaves jail , only if you can afford it
		if(currPlayer.inJail=true){
			if(currPlayer.jailCount>0){
		  if (currPlayer.getBalance() >= 50) {				
			currPlayer.doTransaction(-50);
			ui.displayBankTransaction(currPlayer);
			ui.displayJailLeave();
			ui.displayBalance(currPlayer);
			currPlayer.inJail = false;
			rollDone= true;
		  } else {
			ui.displayError(UI.ERR_INSUFFICIENT_FUNDS);
		  }
			}else{
				ui.displayError(UI.ERR_JAILFIRST);
			}
	    }else{
			ui.displayError(UI.ERR_NOTJAIL);
		}
	}
	public void displayJailHelp(){//displays the help for jail
		ui.displayJailCommandHelp();
	}
	private void processRollForDoubles () {//rolls for doubles and will leave jail if it succeeds
		if(currPlayer.inJail=true){
			if(currPlayer.jailCount>0){
			if (!rollDone) {
						dice.roll();
						ui.displayDice(currPlayer, dice);
						ui.display();

						if (dice.isDouble()) {
						    currPlayer.leaveJail();
						    ui.displayJailLeave();
						    
						}
						else{
						ui.displayJailDoubleFail();
						}
						    rollDone = true;
                    		return;
                      	}
			else{
				ui.displayError(UI.ERR_DOUBLE_ROLL);					
			}
			
		   }else{
				ui.displayError(UI.ERR_JAILFIRST);
			}
		}else{
			ui.displayError(UI.ERR_NOTJAIL);
		}
          }
	public void processJailCard(Player player){//checks if the player has a get out of jail card and then uses it or else returns errors
		if(currPlayer.inJail=true){
			if(currPlayer.jailCount>0){
		         if(player.JailCard()){
			        currPlayer.leaveJail();
			        ui.displayJailLeave();
			        rollDone= true;
		           }
		          else{
		          	ui.displayError(UI.ERR_NOJAILCARD);
		              }
			 }else{
					ui.displayError(UI.ERR_JAILFIRST);
				}
	    }else{
			ui.displayError(UI.ERR_NOTJAIL);
		}
	}
	
}
