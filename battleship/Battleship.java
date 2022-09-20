package battleship;

/**
 * Subclass of Ship to represent a Battleship.
 */
public class Battleship extends Ship {
		
	/**
	 * Integer variable to represent the length of the battleship, which is 4.
	 */
	static final int LENGTH_BATTLESHIP = 4;
	
	/**
	 * Final string variable to represent the type of this ship, a "battleship".
	 */
	static final String SHIP_TYPE = "battleship";
	
	/**
	 * Zero-argument constructor that sets the length of the ship to 4 by calling the super class constructor.
	 */
	public Battleship() {
		
		super(LENGTH_BATTLESHIP);
	}

	/**
	 * Overrides the super class abstract method that returns the ship type. 
	 * @return the string "battleship" as instructed.
	 */
	@Override
	public String getShipType() {
		
		return Battleship.SHIP_TYPE;
	}
}
