import java.awt.BorderLayout;

import javax.swing.JFrame;

import java.util.ArrayList;

public class UI {

	private static final int FRAME_WIDTH = 1200;
	private static final int FRAME_HEIGHT = 800;
	private static final String CURRENCY = " pounds";
	
	public static final int CMD_QUIT = 0;
	public static final int CMD_DONE = 1;
	public static final int CMD_ROLL = 2;
	public static final int CMD_BUY = 3;
	public static final int CMD_PAY_RENT = 4;
	public static final int CMD_AUCTION = 5;
	public static final int CMD_PROPERTY = 6;
	public static final int CMD_BALANCE = 7;
	public static final int CMD_BANKRUPT = 8;
	public static final int CMD_HELP = 9;
	//build
	public static final int CMD_BUILD = 10;
	
	public static final int ERR_SYNTAX = 0;
	public static final int ERR_DOUBLE_ROLL = 1;
	public static final int ERR_NO_ROLL = 2;
	public static final int ERR_INSUFFICIENT_FUNDS = 3;
	public static final int ERR_NOT_A_PROPERTY = 4;
	public static final int ERR_RENT_ALREADY_PAID = 5;
	public static final int ERR_NOT_OWNED = 6;
	public static final int ERR_IS_OWNED = 7;
	public static final int ERR_SELF_OWNED = 8;
	public static final int ERR_RENT_OWED= 9;
	//color error
	public static final int ERR_NOT_ALL_COLORS = 10;
	//build error
	public static final int ERR_CANNOT_BUILD_ON = 11;
	//max num buildings
	public static final int ERR_MAX_BUILDINGS = 12;
	
	private final String[] errorMessages = {
		"Error: Not a valid command.",
		"Error: Too many rolls this turn.",
		"Error: You have a roll to play.",
		"Error: You don't have enough money.",
		"Error: This square is not a property.",
		"Error: You have already paid the rent.",
		"Error: The property is not owned.",
		"Error: The property is already owned.",
		"Error: You own the property.",
		"Error: You owe rent.",
		//errors for building
		"Error: You don't own all the properties in this color.",
		"Error: You cannot build on this property type.",
		"Error: This property already holds the max number of buildings."
		
	};
	
	private JFrame frame = new JFrame();
	private BoardPanel boardPanel;	
	private InfoPanel infoPanel = new InfoPanel();
	private CommandPanel commandPanel = new CommandPanel();
	private String string;
	private boolean done;
	private int commandId;
	private int propertyId;

	UI (ArrayList<Player> players) {
		boardPanel = new BoardPanel(players);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setTitle("Monopoly");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(boardPanel, BorderLayout.LINE_START);
		frame.add(infoPanel, BorderLayout.LINE_END);
		infoPanel.add(commandPanel,BorderLayout.NORTH);
		frame.setResizable(false);
		frame.setVisible(true);
		return;
	}

// INPUT METHODS
	
	public void inputName (int numPlayers) {
		if (numPlayers == 0) {
			infoPanel.displayString("Enter new player name (" + boardPanel.getTokenName(numPlayers) + "):");			
		} else {
			infoPanel.displayString("Enter new player name (" + boardPanel.getTokenName(numPlayers)  +  ") or done:");
		}
		commandPanel.inputString();
		string = commandPanel.getString();
		if ( (numPlayers > 0) && (string.trim().toLowerCase().equals("done")) ) {
			done = true;
		} else {
			done = false;
		}
		infoPanel.displayString("> " + string);
		return;
	}
	
	public void inputCommand (Player player) {
		boolean inputValid = false;
		do {
			infoPanel.displayString(player + " type your command:");
			commandPanel.inputString();
			string = commandPanel.getString();
			infoPanel.displayString("> " + string);
			string = commandPanel.getString();
			string = string.toLowerCase();
			string = string.trim();
			string = string.replaceAll("( )+", " ");
			switch (string) {
				case "quit" :
					commandId = CMD_QUIT;
					inputValid = true;
					break;
				case "done" :
					commandId = CMD_DONE;
					inputValid = true;
					break;
				case "roll" :
					commandId = CMD_ROLL;
					inputValid = true;
					break;
				case "buy" :
					commandId = CMD_BUY;				
					inputValid = true;
					break;
				case "pay rent" :
					commandId = CMD_PAY_RENT;
					inputValid = true;
					break;
				case "auction" :
					commandId = CMD_AUCTION;
					inputValid = true;
					break;
				case "property" :
					commandId = CMD_PROPERTY;
					inputValid = true;
					break;
				case "balance" :
					commandId = CMD_BALANCE;
					inputValid = true;
					break;
				case "bankrupt" :
					commandId = CMD_BANKRUPT;
					inputValid = true;
					break;
				case "help" :
					commandId = CMD_HELP;
					inputValid = true;
					break;
				//build option
				case "build" :
					commandId = CMD_BUILD;
					inputValid = true;
					break;
				default:
					inputValid = false;
				}
			if (!inputValid) {
				displayError(ERR_SYNTAX);
			}
		} while (!inputValid);
		if (commandId == CMD_DONE) {
			done = true;
		} else {
			done = false;
		}		
		return;
	}
	
	public String getString () {
		return string; 
	}
	
	public String getTokenName (int tokenId) {
		return boardPanel.getTokenName(tokenId);
	}
	
	public int getCommandId () {
		return commandId;
	}
	
	public boolean isDone () {
		return done;
	}
	
	
// OUTPUT METHODS
	
	public void display () {
		boardPanel.refresh();
		return;
	}
	
	public void displayString (String string) {
		infoPanel.displayString(string);
		return;
	}
	
	public void displayBankTransaction (Player player) {
		if (player.getTransaction() >= 0) {
			infoPanel.displayString(player + " receives " + player.getTransaction() + CURRENCY + " from the bank.");
		} else {
			infoPanel.displayString(player + " pays " + (-player.getTransaction()) + CURRENCY + " to the bank.");			
		}
		return;
	}
	
	public void displayTransaction (Player fromPlayer, Player toPlayer) {
		infoPanel.displayString(fromPlayer + " pays " + toPlayer.getTransaction() + CURRENCY + " to " + toPlayer);
		return;
	}
	
	public void displayDice (Player player, Dice dice) {
		infoPanel.displayString(player + " rolls " + dice + ".");
		return;
	}
	
	public void displayRollDraw () {
		infoPanel.displayString("Draw");
		return;
	}
	
	public void displayRollWinner (Player player) {
		infoPanel.displayString(player + " wins the roll.");
		return;
	}
	
	public void displayGameOver () {
		infoPanel.displayString("GAME OVER");
		return;
	}
	
	public void displayCommandHelp () {
		infoPanel.displayString("Available commands: roll, pay rent, buy, property, build, balance, done, quit. ");
		return;
	}
	
	public void build(Player player, Board board, int propertyId){
		
		Property property = board.getProperty(propertyId);
		//builds property
		property.setBuilding();
		if(property.numberBuildings() < 5){
			infoPanel.displayString("This property now has " + property.numberBuildings() + " houses.");
			infoPanel.displayString("Rent is now £" + property.getRent());
		}
		else if(property.numberBuildings() == 5){
			infoPanel.displayString("This property now has 1 hotel.");
			infoPanel.displayString("Rent is now £" + property.getRent());
		}
		
		return;
	}
	
	public void displayBalance (Player player) {
		infoPanel.displayString(player + "'s balance is " + player.getBalance() + CURRENCY);
		return;
	}
	
	public void displayError (int errorId) {
		infoPanel.displayString(errorMessages[errorId]);
		return;
	}
	
	public void displayPassedGo (Player player) {
		infoPanel.displayString(player + " passed Go.");
		return;
	}
	
	public void displayLatestProperty (Player player) {
		infoPanel.displayString(player + " bought " + player.getLatestProperty());
		return;
	}
	
	public void displayProperty (Player player) {
		ArrayList<Property> propertyList = player.getProperties();
		ArrayList<String> allColor = new ArrayList<>();
		
		if (propertyList.size() == 0) {
			infoPanel.displayString(player + " owns no property.");
		} else {
			infoPanel.displayString(player + " owns the following property...");
			for (Property p : propertyList) {
				if (p.getColor()=="transport" || p.getColor()=="utilities"){
					infoPanel.displayString(p.getName() + ", rent " + p.getRent() + ", " + p.getColor());
					
				}
				else{
					infoPanel.displayString(p.getName() + ", rent " + p.getRent() + ", " + p.getColor() + ", Number of houses/hotels: " + p.numberBuildings());
				
				}
				
			}
			
			allColor = checkAllColor(player);
				//displays which of the properties you own all the colors of
			if(!allColor.isEmpty()){	
				infoPanel.displayString("You own all the properties of the following colors: " + allColor);
			}
			
		}
	}
	
	public ArrayList<String> checkAllColor(Player player){
		ArrayList<Property> propertyList = player.getProperties();
		ArrayList<String> allColor = new ArrayList<>();
		
		for (Property p : propertyList) {
				if (colorPropertiesOwned(player, p.getColor())==3){
					//only add if not already there
					
						if(!allColor.contains(p.getColor())){
							allColor.add(p.getColor());
						}
					
				}
				else if((p.getColor() == "Brown" || p.getColor() == "Blue") && colorPropertiesOwned(player, p.getColor())==2){
					if(!allColor.contains(p.getColor())){
						allColor.add(p.getColor());
					}
				}
			}
		return allColor;
		
	}
	
	public void displaySquare (Player player, Board board) {
		infoPanel.displayString(player + " arrives at " + board.getSquare(player.getPosition()).getName() + ".");
		if (board.isTransport(player.getPosition())){
			Transport transport = board.getTransport(player.getPosition());
			if (transport.isOwned()) {
				infoPanel.displayString("The property is owned by " + transport.getOwner() + ". Rent is " + transport.getRent() + CURRENCY + ".");				
			} else {
				infoPanel.displayString("The property is not owned. Rent is " + transport.getRent() + CURRENCY + ".");								
				//if own another transport square - this could be used for calculating rent
				if (colorPropertiesOwned(player, "transport") > 0){
					infoPanel.displayString("You own " + colorPropertiesOwned(player, "transport") + " other properties of type transport");
				}
			
			}
		}
		else if (board.isUtilities(player.getPosition())){
			Utilities utilities = board.getUtilities(player.getPosition());
			if (utilities.isOwned()) {
				infoPanel.displayString("The property is owned by " + utilities.getOwner() + ". Rent is " + utilities.getRent() + CURRENCY + ".");				
			} else {
				infoPanel.displayString("The property is not owned. Rent is " + utilities.getRent() + CURRENCY + ".");								
				//if own another utilities square - this could be used for calculating rent
				if (colorPropertiesOwned(player, "utilities") > 0){
					infoPanel.displayString("You own " + colorPropertiesOwned(player, "utilities") + " other properties of type utilities");
				}
			}
		}
		else if (board.isProperty(player.getPosition())) {
			Property property = board.getProperty(player.getPosition());
			if (property.isOwned()) {
				infoPanel.displayString("The property is owned by " + property.getOwner() + ". Rent is " + property.getRent() + CURRENCY + ".");				
			} else {
				infoPanel.displayString("The property is not owned. Rent is " + property.getRent() + CURRENCY + "." + " Color is " + property.getColor() + ".");								
			//if you own another property in that color tell them
				if (colorPropertiesOwned(player, property.getColor()) > 0){
					infoPanel.displayString("You own " + colorPropertiesOwned(player, property.getColor()) + " other properties of this color");
				}
			}
		}
		
		return;
	}
	
	public void displayAssets (Player player) {
		infoPanel.displayString(player + " has assets of " + player.getAssets() + CURRENCY);
		return;
	}
	
	public void displayWinner (Player player) {
		infoPanel.displayString("The winner is " + player);
		return;
	}
	
	public void displayDraw (ArrayList<Player> players) {
		infoPanel.displayString("The following players drew the game " + players);
		return;
	}
	
	//bankrupt function to display bankruptcy
	 public void Bankrupt(Player player){
	 	
	 		infoPanel.displayString(player + " has declared bankruptcy because of insufficient funds");
	 	}
	
	public void clearPanel(){
		infoPanel.clearPanel();
	}
	
	//////this counts how many other properties in this color you already own
	public int colorPropertiesOwned(Player player, String type){
		ArrayList<Property> propertyList = player.getProperties();
		int i = 0;
		for (Property p : propertyList) {
			if (p.getColor() == type){
				i++;
			}
		}
		return i;
	}

	public void whichProperty(Player player, Board board) {

		boolean inputValid = false;
		Property property = board.getProperty(player.getPosition());
		do {
			infoPanel.displayString("On what property would you like to build?");
			commandPanel.inputString();
			string = commandPanel.getString();
			infoPanel.displayString("> " + string);
			string = commandPanel.getString();
			string = string.toLowerCase();
			string = string.trim();
			string = string.replaceAll("( )+", " ");
			switch (string) {
				case "old kent road" :
					propertyId = 1;
					inputValid = true;
					
					if(checkAllColor(player).contains("Brown")){
						if(property.numberBuildings()==5){
							//max num buildings
							displayError(ERR_MAX_BUILDINGS);
						}
						else{
							build(player, board, propertyId);
						}
						
					}
					else{
						displayError(ERR_NOT_ALL_COLORS);
					}
					break;
				case "whitechapel road" :
					propertyId = 3;
					inputValid = true;
					if(checkAllColor(player).contains("Brown")){
						if(property.numberBuildings()==5){
							//max num buildings
							displayError(ERR_MAX_BUILDINGS);
						}
						else{
							build(player, board, propertyId);
						}
					}
					else{
						displayError(ERR_NOT_ALL_COLORS);
					}
					break;
				case "the angel islington" :
					propertyId = 6;
					inputValid = true;
					if(checkAllColor(player).contains("Cyan")){
						if(property.numberBuildings()==5){
							//max num buildings
							displayError(ERR_MAX_BUILDINGS);
						}
						else{
							build(player, board, propertyId);
						}
					}
					else{
						displayError(ERR_NOT_ALL_COLORS);
					}
					break;
				case "euston road" :
					propertyId = 8;
					inputValid = true;
					if(checkAllColor(player).contains("Cyan")){
						if(property.numberBuildings()==5){
							//max num buildings
							displayError(ERR_MAX_BUILDINGS);
						}
						else{
							build(player, board, propertyId);
						}
					}
					else{
						displayError(ERR_NOT_ALL_COLORS);
					}
					break;
				case "pentonville road" :
					propertyId = 9;
					inputValid = true;
					if(checkAllColor(player).contains("Cyan")){
						if(property.numberBuildings()==5){
							//max num buildings
							displayError(ERR_MAX_BUILDINGS);
						}
						else{
							build(player, board, propertyId);
						}
					}
					else{
						displayError(ERR_NOT_ALL_COLORS);
					}
					break;
				case "pall mall" :
					propertyId = 11;
					inputValid = true;
					if(checkAllColor(player).contains("Pink")){
						if(property.numberBuildings()==5){
							//max num buildings
							displayError(ERR_MAX_BUILDINGS);
						}
						else{
							build(player, board, propertyId);
						}
					}
					else{
						displayError(ERR_NOT_ALL_COLORS);
					}
					break;
				case "whitehall" :
					propertyId = 13;
					inputValid = true;
					if(checkAllColor(player).contains("Pink")){
						if(property.numberBuildings()==5){
							//max num buildings
							displayError(ERR_MAX_BUILDINGS);
						}
						else{
							build(player, board, propertyId);
						}
					}
					else{
						displayError(ERR_NOT_ALL_COLORS);
					}
					break;
				case "northumberland avenue" :
					propertyId = 14;
					inputValid = true;
					if(checkAllColor(player).contains("Pink")){
						if(property.numberBuildings()==5){
							//max num buildings
							displayError(ERR_MAX_BUILDINGS);
						}
						else{
							build(player, board, propertyId);
						}
					}
					else{
						displayError(ERR_NOT_ALL_COLORS);
					}
					break;
				case "bow street" :
					propertyId = 16;
					inputValid = true;
					if(checkAllColor(player).contains("Orange")){
						if(property.numberBuildings()==5){
							//max num buildings
							displayError(ERR_MAX_BUILDINGS);
						}
						else{
							build(player, board, propertyId);
						}
					}
					else{
						displayError(ERR_NOT_ALL_COLORS);
					}
					break;
				case "marlborough street" :
					propertyId = 18;
					inputValid = true;
					if(checkAllColor(player).contains("Orange")){
						if(property.numberBuildings()==5){
							//max num buildings
							displayError(ERR_MAX_BUILDINGS);
						}
						else{
							build(player, board, propertyId);
						}
					}
					else{
						displayError(ERR_NOT_ALL_COLORS);
					}
					break;
				case "vine street" :
					propertyId = 19;
					inputValid = true;
					if(checkAllColor(player).contains("Orange")){
						if(property.numberBuildings()==5){
							//max num buildings
							displayError(ERR_MAX_BUILDINGS);
						}
						else{
							build(player, board, propertyId);
						}
					}
					else{
						displayError(ERR_NOT_ALL_COLORS);
					}
					break;
				case "strand" :
					propertyId = 21;
					inputValid = true;
					if(checkAllColor(player).contains("Red")){
						if(property.numberBuildings()==5){
							//max num buildings
							displayError(ERR_MAX_BUILDINGS);
						}
						else{
							build(player, board, propertyId);
						}
					}
					else{
						displayError(ERR_NOT_ALL_COLORS);
					}
					break;
				case "fleet street" :
					propertyId = 23;
					inputValid = true;
					if(checkAllColor(player).contains("Red")){
						if(property.numberBuildings()==5){
							//max num buildings
							displayError(ERR_MAX_BUILDINGS);
						}
						else{
							build(player, board, propertyId);
						}
					}
					else{
						displayError(ERR_NOT_ALL_COLORS);
					}
					break;
				case "trafalgar square" :
					propertyId = 24;
					inputValid = true;
					if(checkAllColor(player).contains("Red")){
						if(property.numberBuildings()==5){
							//max num buildings
							displayError(ERR_MAX_BUILDINGS);
						}
						else{
							build(player, board, propertyId);
						}
					}
					else{
						displayError(ERR_NOT_ALL_COLORS);
					}
					break;
				case "leicester square" :
					propertyId = 26;
					inputValid = true;
					if(checkAllColor(player).contains("Yellow")){
						if(property.numberBuildings()==5){
							//max num buildings
							displayError(ERR_MAX_BUILDINGS);
						}
						else{
							build(player, board, propertyId);
						}
					}
					else{
						displayError(ERR_NOT_ALL_COLORS);
					}
					break;
				case "coventry street" :
					propertyId = 27;
					inputValid = true;
					if(checkAllColor(player).contains("Yellow")){
						if(property.numberBuildings()==5){
							//max num buildings
							displayError(ERR_MAX_BUILDINGS);
						}
						else{
							build(player, board, propertyId);
						}
					}
					else{
						displayError(ERR_NOT_ALL_COLORS);
					}
					break;
				case "piccadilly" :
					propertyId = 29;
					inputValid = true;
					if(checkAllColor(player).contains("Yellow")){
						if(property.numberBuildings()==5){
							//max num buildings
							displayError(ERR_MAX_BUILDINGS);
						}
						else{
							build(player, board, propertyId);
						}
					}
					else{
						displayError(ERR_NOT_ALL_COLORS);
					}
					break;
				case "regent street" :
					propertyId = 31;
					inputValid = true;
					if(checkAllColor(player).contains("Green")){
						if(property.numberBuildings()==5){
							//max num buildings
							displayError(ERR_MAX_BUILDINGS);
						}
						else{
							build(player, board, propertyId);
						}
					}
					else{
						displayError(ERR_NOT_ALL_COLORS);
					}
					break;
				case "oxford street" :
					propertyId = 32;
					inputValid = true;
					if(checkAllColor(player).contains("Green")){
						if(property.numberBuildings()==5){
							//max num buildings
							displayError(ERR_MAX_BUILDINGS);
						}
						else{
							build(player, board, propertyId);
						}
					}
					else{
						displayError(ERR_NOT_ALL_COLORS);
					}
					break;
				case "bond street" :
					propertyId = 34;
					inputValid = true;
					if(checkAllColor(player).contains("Green")){
						if(property.numberBuildings()==5){
							//max num buildings
							displayError(ERR_MAX_BUILDINGS);
						}
						else{
							build(player, board, propertyId);
						}
					}
					else{
						displayError(ERR_NOT_ALL_COLORS);
					}
					break;
				case "park lane" :
					propertyId = 37;
					inputValid = true;
					if(checkAllColor(player).contains("Blue")){
						if(property.numberBuildings()==5){
							//max num buildings
							displayError(ERR_MAX_BUILDINGS);
						}
						else{
							build(player, board, propertyId);
						}
					}
					else{
						displayError(ERR_NOT_ALL_COLORS);
					}
					break;
				case "mayfair" :
					propertyId = 39;
					inputValid = true;
					if(checkAllColor(player).contains("Blue")){
						if(property.numberBuildings()==5){
							//max num buildings
							displayError(ERR_MAX_BUILDINGS);
						}
						else{
							build(player, board, propertyId);
						}
					}
					else{
						displayError(ERR_NOT_ALL_COLORS);
					}
					break;
				default:
					inputValid = false;
				}
			if (!inputValid) {
				displayError(ERR_CANNOT_BUILD_ON);
			}
		} while (!inputValid);
		if (commandId == CMD_DONE) {
			done = true;
		} else {
			done = false;
		}		
		return;
	}
	
	
}

