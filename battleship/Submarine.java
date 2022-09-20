package battleship;

/**
 * Subclass of Ship to represent a Submarine.
 */
public class Submarine extends Ship {
	
   /**
	 * Integer variable to represent the length of the submarine, which is 1.
	 */
	static final int LENGTH_SUBMARINE = 1;
	
	/**
	 * Final string variable to represent the type of this ship, a "submarine".
	 */
	static final String SHIP_TYPE = "submarine";
	
	/**
	 * Zero-argument constructor that sets the length of the ship to 1 by calling the super class constructor.
	 */
	public Submarine() {
		
		super(LENGTH_SUBMARINE);
  }

	/**
	 * Overrides the super class abstract method that returns the ship type. 
	 * @return the string "submarine" as instructed.
	 */
	@Override
	public String getShipType() {
		
		return Submarine.SHIP_TYPE;
	}
}
