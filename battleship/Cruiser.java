package battleship;

/**
 * Subclass of Ship to represent a Cruiser.
 */
public class Cruiser extends Ship {

	/**
	 * Integer variable to represent the length of the cruiser, which is 3.
	 */
	static final int LENGTH_CRUISER = 3;
	
	/**
	 * Final string variable to represent the type of this ship, a "cruiser".
	 */
	static final String SHIP_TYPE = "cruiser";
	
	/**
	 * Zero-argument constructor that sets the length of the ship to 3 by calling the super class constructor.
	 */
	public Cruiser() {
		
		super(LENGTH_CRUISER);
	}

	/**
	 * Overrides the super class abstract method that returns the ship type. 
	 * @return the string "cruiser" as instructed.
	 */
	@Override
	public String getShipType() {
		
		return Cruiser.SHIP_TYPE;
	}
}
