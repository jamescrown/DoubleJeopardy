
public class Board {
	
	// Board data from http://monopoly.wikia.com/wiki/Monopoly
	// Note, a property is a site, utility or station
	
	public static final int NUM_SQUARES = 40;
		
	private Square[] squares = new Square[NUM_SQUARES];
	
	Board () {
		squares[0] = new Square("Go");
		squares[1] = new Property("Old Kent Rd", 60, new int[] {2,10,30,90,160,250}, "Brown");
		squares[2] = new Square("Comminity Chest");
		squares[3] = new Property("Whitechapel Rd", 60, new int[] {4,20,60,180,320,450}, "Brown");
		squares[4]  = new Square("Income Tax");
		squares[5] = new Transport("King's Cross Station", 200, new int[] {25,50,100,200,200,200});
		squares[6] = new Property("The Angel Islington", 100, new int[] {6,30,90,270,400,550}, "Cyan");
		squares[7] = new Square("Chance");
		squares[8] = new Property("Euston Rd", 100, new int[] {6,30,90,270,400,550}, "Cyan");
		squares[9] = new Property("Pentonville Rd", 120, new int[] {8,40,100,300,450,600}, "Cyan");
		squares[10] = new Square("Jail");
		squares[11] = new Property("Pall Mall", 140, new int[] {10,50,150,450,625,750}, "Pink");
		squares[12] = new Utilities("Electric Co", 150, new int[] {4,10,0,0,0,0});
		squares[13] = new Property("Whitehall", 140, new int[] {10,50,150,450,625,750}, "Pink");
		squares[14] = new Property("Northumberland Ave", 160, new int[] {12,60,180,500,700,900}, "Pink");
		squares[15] = new Transport("Marylebone Station", 200, new int[] {25,50,100,200,200,200});
		squares[16] = new Property("Bow St", 180, new int[] {14,70,200,550,750,950}, "Orange");
		squares[17] = new Square("Community Chest");
		squares[18] = new Property("Marlborough St", 180, new int[] {14,70,200,550,750,950}, "Orange");
		squares[19] = new Property("Vine St", 200, new int[] {16,80,220,600,800,1000}, "Orange");
		squares[20] = new Square("Free Parking");
		squares[21] = new Property("Strand", 220, new int[] {18,90,250,700,875,1050}, "Red");
		squares[22] = new Square("Chance");
		squares[23] = new Property("Fleet St", 220, new int[] {18,90,250,700,875,1050}, "Red");
		squares[24]  = new Property("Trafalgar Sq", 240, new int[] {20,100,300,750,925,1100}, "Red");
		squares[25] = new Transport("Fenchurch St Station", 200, new int[] {25,50,100,200,200,200});
		squares[26] = new Property("Leicester Sq", 260, new int[] {22,110,330,800,975,1150}, "Yellow");
		squares[27] = new Property("Coventry St", 260, new int[] {22,110,330,800,975,1150}, "Yellow");
		squares[28] = new Utilities("Water Works", 150, new int[] {4,10,0,0,0,0});
		squares[29] = new Property("Piccadilly", 280, new int[] {22,120,360,850,1025,1200}, "Yellow");
		squares[30] = new Square("Go To Jail");
		squares[31] = new Property("Regent St", 300, new int[] {26,130,390,900,1100,1275}, "Green");
		squares[32] = new Property("Oxford St", 300, new int[] {26,130,390,900,1100,1275}, "Green");
		squares[33] = new Square("Community Chest");
		squares[34] = new Property("Bond St", 320, new int[] {28,150,450,1000,1200,1400}, "Green");
		squares[35] = new Transport("Liverpool St Station", 200, new int[] {25,50,100,200,200,200});
		squares[36] = new Square("Chance");
		squares[37] = new Property("Park Lane", 350, new int[] {35,175,500,1100,1300,1500}, "Blue");
		squares[38] = new Square("Super Tax");
		squares[39 ] = new Property("Mayfair", 400, new int[] {50,200,600,1400,1700,2000}, "Blue");
		return;
	}
	
	public Square getSquare (int index) {
		return squares[index];
	}
	
	public Property getProperty (int index) {
		return (Property) squares[index];
	}
	
	public Transport getTransport (int index) {
		return (Transport) squares[index];
	}
	
	public Utilities getUtilities (int index) {
		return (Utilities) squares[index];
	}
	
	public boolean isProperty (int index) {
		return squares[index] instanceof Property;
	}
	
	public boolean isTransport (int index) {
		return squares[index] instanceof Transport;
	}
	
	public boolean isUtilities (int index) {
		return squares[index] instanceof Transport;
	}
	
}

