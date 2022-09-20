package battleship;

/**
 * Subclass of Ship to represent an empty location in the ocean.
 */
public class EmptySea extends Ship {

	/**
	 * Integer variable to represent the length of the EmptySea location, which is 1.
	 */
	static final int LENGTH_EMPTYSEA = 1;
	
	/**
	 * Final string variable to represent the type of this ship, which is "empty".
	 */
	static final String SHIP_TYPE = "empty";
	
	/**
	 * Zero-argument constructor that sets the length of the ship to 1 by calling the super class constructor.
	 */
	public EmptySea() {
	
		super(LENGTH_EMPTYSEA);
	}
	
	/**
	 * Method to represent being shot at, overrides the super class method because an empty ocean location cannot be hit.
	 * @param row of the emptysea location.
	 * @param column of the emptysea location.
	 * @return false because an emptysea location cannot be hit.
	 */
	@Override
	boolean shootAt(int row, int column) {
		
		/*Sets this instance's boolean value for wasShotAt to true, which will be used when printing the ocean.
		I.e. an emptysea that was shot at before should display "-", vs "." if not shot at before.*/
		setWasShotAt(true);
		
		//Always returns false as instructed to indicate that nothing was hit.
		return false;
	}
	
	/**
	 * Overrides the super class getter method that returns whether a ship is sunk. 
	 * @return false because an empty ocean location cannot be sunk.
	 */
	@Override
	boolean isSunk() {
		
		return false;
	}
	
	/**
	 * Overrides the super class getter method that returns whether a ship was previously shot at. 
	 * @return true if the location was shot at previously, otherwise, return false.
	 */
	@Override
	boolean shotAtDisplay(int row, int column) {
		
		if (this.getBowRow() == row && this.getBowColumn() == column && this.getWasShotAt() == true) {
			
			return true;
			
		} else {
			
			return false;
		}
	}
	
	/**
	 * Overrides the super class toString that returns a single-character string. This method is only for locations that have been shot at. 
	 * @return a "—" to represent an emptysea location that has been shot at previously.
	 */
	@Override
	public String toString() {
		
		String finalString = "—";
		
		return finalString;
	}
	
	/**
	 * Overrides the super class abstract method that returns the ship type. 
	 * @return the string "empty" as instructed.
	 */
	@Override
	public String getShipType() {
				
		return EmptySea.SHIP_TYPE;
	}
}
