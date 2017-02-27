
public class Properties {
   //list of all the square names, we can use the position number to find the associated name
	private static final String[] SITE_NAMES = {
	"GO","Old Kent Rd","Community Chest","Whitechapel Rd","Income Tax","King's Cross Station","The Angel Islington","Chance",
	"Euston Rd","Pentonville Rd","Jail(just visiting)","Pall Mall","Electric Company","Whitehall",
	"Northumberland Ave","Marylebone Station","Bow St","Community Chest","Marlborough St","Vine St","Free Parking",
	"Strand","Chance","Fleet St","Trafalgar Sq","Fenchurch St Station","Leicester Sq","Coventry St","Water Works","Piccadilly","Go To Jail"
	,"Regent St","Oxford St","Community Chest","Bond St","Liverpool St Station","Chance","Park Lane","Super Tax","Mayfair"};
//function to retrieve name of position square by inputing the position and then returning that value from the array
	 public static String GetPropertyName(int PositionNumber){
		  return SITE_NAMES[PositionNumber];
	}
	
	 
	 
	
	//site prices and multiple empty values to represent chance cards community cards etc.
	private static final int[] SITE_PRICES = {
	0,40,0,60,0,200,100,0,100,120,
	0,140,150,140,160,200,180,0,180,200,
	0,220,0,220,240,200,260,260,150,280,
	0,300,300,0,320,200,0,350,0,400};
	//function for site prices, same idea as before , input position , retrieve from array except if price is zero dont bother 
	public static int GetPropertyPrice(int PositionNumber){
		//if the price is 0 it is because it is not a property therefore return 0 as we do not want it
		if(SITE_PRICES[PositionNumber]==0){
			return 0;
		}
		else{
		return SITE_PRICES[PositionNumber];
		}
	}
	
	
	
	
	private static final int[] SITE_RENTS = {
			//rents for all houses/stations/utilities are set at 50 except rent for tax and supertax are 100/200 
			0,50,0,50,100,50,50,0,50,50,
			0,50,50,50,50,50,50,0,50,50,
			0,50,0,50,50,50,50,50,50,50,
			0,50,50,0,50,50,0,50,200,50};
	//function to retrieve rent , when rent is 0 it is because it is not a property
	public static int GetPropertyRent(int PositionNumber){
		if(SITE_RENTS[PositionNumber]==0){
			return 0;
		}
		else{
			return SITE_RENTS[PositionNumber];
		}
	}
	
	

	//array of site colours and status' such as station or utility
	private static final String[] SITE_DETAIL = {
			"","brown","","brown","","Station","lightblue","",
			"lightblue","lightblue","","pink","Utility","pink",
			"pink","Station","orange","","orange","orange","",
			"red","","red","red","Station","yellow","yellow","Utility","yellow",""
			,"green","green","","green","Station","","dark blue","","dark blue"};
	
	public static String GetPropertyDetail(int PositionNumber){
		if(SITE_DETAIL[PositionNumber]==null){
			return "No Detail";
		}
		else{
			return SITE_DETAIL[PositionNumber];
		}
	}
	
	
	
	//selling prices so the owner can sell his property whenever for a fixed rate atm

	private static final int[] SITE_MORTGAGE = {
			0,100,0,100,100,100,100,0,100,100,
			0,100,100,100,100,100,100,0,100,100,
			0,100,0,100,100,100,100,100,100,100,
			0,100,100,0,100,100,0,100,100,100};
	
    public int GetPropertyMotgage(int PositionNumber){
	    //again if its not a property its price will be 0 so nothing happens
		if(SITE_MORTGAGE[PositionNumber]==0){
			return 0;
		}
		else{
			return SITE_MORTGAGE[PositionNumber];
		}
	}
}
