package battleship;

/**
 * Subclass of Ship to represent a Destroyer.
 */
public class Destroyer extends Ship {

	/**
	 * Integer variable to represent the length of the destroyer, which is 2.
	 */
	static final int LENGTH_DESTROYER = 2;
	
	/**
	 * Final string variable to represent the type of this ship, a "destroyer".
	 */
	static final String SHIP_TYPE = "destroyer";
	
	/**
	 * Zero-argument constructor that sets the length of the ship to 2 by calling the super class constructor.
	 */
	public Destroyer() {
		
		super(LENGTH_DESTROYER);
	}
	
	/**
	 * Overrides the super class abstract method that returns the ship type. 
	 * @return the string "destroyer" as instructed.
	 */
	@Override
	public String getShipType() {
		
		return Destroyer.SHIP_TYPE;
	}
}
