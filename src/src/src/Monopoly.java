
				case UI.CMD_ROLL :
					if (!rollDone) {
						if (!rentOwed) {
							dice.roll();
							ui.displayDice(currPlayer, dice);
							currPlayer.move(dice.getTotal());
							ui.display();
							if (currPlayer.passedGo()) {
								currPlayer.doTransaction(+GO_MONEY);
								ui.displayPassedGo(currPlayer);
								ui.displayBankTransaction(currPlayer);
							}
							ui.displaySquare(currPlayer, board, dice);
							if (board.isProperty(currPlayer.getPosition()) && 
									board.getProperty(currPlayer.getPosition()).isOwned() &&
									!board.getProperty(currPlayer.getPosition()).getOwner().equals(currPlayer) ) {
										rentOwed = true;
							} else {
								rentOwed = false;
							}
							if (!dice.isDouble()) {
								rollDone = true;
							}
						} else {
							ui.displayError(UI.ERR_RENT_OWED);	
						}
					} else {
						ui.displayError(UI.ERR_DOUBLE_ROLL);
					}
					break;
				case UI.CMD_PAY_RENT :
					if (board.isProperty(currPlayer.getPosition())) {
						Property property = board.getProperty(currPlayer.getPosition());
						if (property.isOwned()) {
							if (!property.getOwner().equals(currPlayer)) {
								if(property.isMortgaged(currPlayer) == false){
								 if (!rentPaid) {
									if (currPlayer.getBalance()>=property.getRent()) {
										//if utilities rent is mutliplied by roll
										if (board.isUtilities(currPlayer.getPosition())){
											Player owner = property.getOwner();
											int rent = (property.getRent()*dice.getTotal());
											currPlayer.doTransaction(-rent);
											owner.doTransaction(+rent);
											ui.displayTransaction(currPlayer, owner);
											rentPaid = true;	
											rentOwed = false;
										}
										else{
											Player owner = property.getOwner();
											currPlayer.doTransaction(-property.getRent());
											owner.doTransaction(+property.getRent());
											ui.displayTransaction(currPlayer, owner);
											rentPaid = true;	
											rentOwed = false;
										}
								        } else {//if they dont have enough money then run bankrupt command		
  									    for(Property pr : currPlayer.getProperties())//for each property remove the owner
  								        {
  								            pr.removeOwner();
  								        }
  									    
										ui.Bankrupt(currPlayer);//run function from UI class called Bankrupt
										currPlayer.lost();
										rentPaid = true;
										rentOwed = false;
										turnFinished = true;
										
									} 
								} else {
									ui.displayError(UI.ERR_RENT_ALREADY_PAID);									
								}
								}else{
									ui.displayError(UI.ERR_MORTG_OWNED);
									rentOwed= false;
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
					break;
				case UI.CMD_BUY :
					if (board.isTransport(currPlayer.getPosition())) {
						Transport transport = board.getTransport(currPlayer.getPosition());
						if (!transport.isOwned()) {
							if (currPlayer.getBalance() >= transport.getValue()) {				
								currPlayer.doTransaction(-transport.getValue());
								ui.displayBankTransaction(currPlayer);
								currPlayer.boughtTransport(transport);
								
								//ui.displayString(" " + ui.colorPropertiesOwned(currPlayer, transport.getColor()));
								if (ui.colorPropertiesOwned(currPlayer, transport.getColor()) > 1){
									//you own multiples
									transport.ownMultiples(ui.colorPropertiesOwned(currPlayer, transport.getColor()));
								}
								
								//if you own other transports increase rent ON ALL
								if (transport.numberOwned() > 0 ){
									
									
									ArrayList<Property> propertyList = currPlayer.getProperties();
									ArrayList<Transport> transportsOwnedList = new ArrayList<Transport>();;
									for (Property p : propertyList) {
										if (p.getColor() == "transport"){
											transportsOwnedList.add((Transport) p); //finds all the transport spaces you own and adds them to the arraylist transportsOwnedList
										}
									}
									for(Transport t : transportsOwnedList){
										//ui.displayString(t.getName() + t.getRent());
										if (t != transport){
											t.ownMultiples(ui.colorPropertiesOwned(currPlayer, transport.getColor()));
										}	
										
									}
								}
								ui.displayLatestProperty(currPlayer);
							} else {
								ui.displayError(UI.ERR_INSUFFICIENT_FUNDS);
							}
						} else {
							ui.displayError(UI.ERR_IS_OWNED);
						}
					}
					else if (board.isUtilities(currPlayer.getPosition())) {
						Utilities utility = board.getUtilities(currPlayer.getPosition());
						if (!utility.isOwned()) {
							if (currPlayer.getBalance() >= utility.getValue()) {				
								currPlayer.doTransaction(-utility.getValue());
								ui.displayBankTransaction(currPlayer);
								currPlayer.boughtUtilities(utility);
								
								//ui.displayString(" " + ui.colorPropertiesOwned(currPlayer, utility.getColor()));
								if (ui.colorPropertiesOwned(currPlayer, utility.getColor()) > 1){
									//you own multiples
									utility.ownMultiples(ui.colorPropertiesOwned(currPlayer, utility.getColor()));
								}
								//ui.displayString(" Owned: " + utility.numberOf);
								if (utility.numberOwned() > 0){
									
									
									ArrayList<Property> propertyList = currPlayer.getProperties();
									ArrayList<Utilities> utilitiesOwnedList = new ArrayList<Utilities>();;
									for (Property p : propertyList) {
										if (p.getColor() == "utilities"){
											utilitiesOwnedList.add((Utilities) p); //finds all the utility spaces you own and adds them to the arraylist transportsOwnedList
										}
									}
									for(Utilities u : utilitiesOwnedList){
										if (u != utility){
										u.ownMultiples(ui.colorPropertiesOwned(currPlayer, utility.getColor()));//this increases the rent for each of the spaces, not just the one you're on
										}
									}
								}
								ui.displayLatestProperty(currPlayer);
							} else {
								ui.displayError(UI.ERR_INSUFFICIENT_FUNDS);
							}
						} else {
							ui.displayError(UI.ERR_IS_OWNED);
						}
					}
					else if (board.isProperty(currPlayer.getPosition())) {
						Property property = board.getProperty(currPlayer.getPosition());
						if (!property.isOwned()) {
							if (currPlayer.getBalance() >= property.getValue()) {				
								currPlayer.doTransaction(-property.getValue());
								ui.displayBankTransaction(currPlayer);
								currPlayer.boughtProperty(property);
								ui.displayLatestProperty(currPlayer);
							} else {
								ui.displayError(UI.ERR_INSUFFICIENT_FUNDS);
							}
						} else {
							ui.displayError(UI.ERR_IS_OWNED);
						}
					}
					
					else {
						ui.displayError(UI.ERR_NOT_A_PROPERTY);
					}
					break;
				case UI.CMD_BALANCE :
					ui.displayBalance(currPlayer);
					break;
				case UI.CMD_PROPERTY :
					ui.displayProperty(currPlayer);
					break;
				case UI.CMD_HELP :
					ui.displayCommandHelp();
					break;
				//build option
				case UI.CMD_BUILD :
					ui.whichProperty(currPlayer, board, "build");
					ui.displayBankTransaction(currPlayer);
					break;
				case UI.CMD_DEMOLISH :
					ui.whichProperty(currPlayer, board, "demolish");
					ui.displayBankTransaction(currPlayer);
					break;
				case UI.CMD_MORTGAGE : // mortgage option.
					ui.whichProperty(currPlayer, board, "mortgage");
					ui.displayBankTransaction(currPlayer);
					break;
				case UI.CMD_DONE :
					if (rollDone) {
						if (!rentOwed || (rentOwed && rentPaid)) {
							turnFinished = true;
							ui.clearPanel();
						} else {
							ui.displayError(UI.ERR_RENT_OWED);
						}
					} else {
						ui.displayError(UI.ERR_NO_ROLL);
					}
					break;
				case UI.CMD_QUIT : 
					turnFinished = true;
					gameOver = true;
					break;
			
