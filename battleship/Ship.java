package battleship;

/**
 * Abstract ship class describing the characteristics common to all ships.
 * This class has five subclasses with different lengths and characteristics.
 */
public abstract class Ship {
	
	/**
	 * Integer variable to represent the length of the ship.
	 */
	private int length;
	
	/**
	 * Integer variable to represent the row number of the ship's bow (or front of the ship).
	 */
	private int bowRow;
	
	/**
	 * Integer variable to represent the column number of the ship's bow (or front of the ship).
	 */
	private int bowColumn;
	
	/**
	 * Boolean variable to represent whether the ship is horizontal (true) or vertical (false) in the ocean array.
	 */
	private boolean horizontal;
	
	/**
	 * An array of boolean values to track whether a part of the ship has been hit (true) or not hit (false).
	 */
	private boolean[] hit;
	
	/**
	 * Boolean variable to track whether a ship was previously shot at already (true if shot at).
	 */
	private boolean wasShotAt;
	
	/**
	 * Boolean variable to track whether a ship is sunk (true) or not (false).
	 */
	private boolean isSunk;
	
	/**
	 * "Getter" method that returns the ship's length.
	 * @return the length of the ship instance.
	 */
	public int getLength() {
		return this.length;
	}	
	
	/**
	 * "Getter" method that returns the row of the ship's bow (front of the ship).
	 * @return the row number of the ship's bow.
	 */
	public int getBowRow() {
		return this.bowRow;
	}
	
	/**
	 * "Getter" method that returns the column of the ship's bow (front of the ship).
	 * @return the column number of the ship's bow.
	 */
	public int getBowColumn() {
		return this.bowColumn;
	}	
	
	/**
	 * "Getter" method that returns the ship's hit array, showing if a section of the ship has been hit (true) or not (false).
	 * @return the ship's hit array, with a true value indicating any sections where the ship has been hit and a false for sections not hit.
	 */
	public boolean[] getHit() {
		return this.hit;
	}

	/**
	 * "Getter" method that returns whether the ship is sunk (true) or not (false).
	 * @return true if the ship has been sunk and false if the ship is not sunk.
	 */
	public boolean getIsSunk() {
		return this.isSunk;
	}	

	/**
	 * "Getter" method that returns whether the ship was previously shot at (true) or not (false).
	 * @return true if the ship has been shot at previously and false if not.
	 */
	public boolean getWasShotAt()  {
		return this.wasShotAt;
	}
	
	/**
	 * "Getter" method that returns whether the ship is placed horizontally (true) or not (false) in the ocean array.
	 * @return true if the ship is aligned horizontally, and false if the ship is vertical.
	 */
	public boolean isHorizontal() {
		return this.horizontal;
	}
	
	/**
	 * Abstract "getter" method that is not defined yet in this class, but to indicate there will be methods in each subclass to get the ship's type.
	 * @return a string indicating the ship's type.
	 */	
	public abstract String getShipType();
	
	/**
	 * "Setter" method that sets the row of the ship's bow (or front of the ship).
	 * @param row number for the ship's bow
	 */
	public void setBowRow(int row) {
		this.bowRow = row;
	}	
		
	/**
	 * "Setter" method that sets the column of the ship's bow (or front of the ship).
	 * @param column number for the ship's bow
	 */
	public void setBowColumn(int column) {
		this.bowColumn = column;
	}
	
	/**
	 * "Setter" method that sets whether the ship is sunk (true) or not (false).
	 * @param isSunk a boolean value to represent if the ship is sunk (true) or still afloat (false).
	 */
	public void setIsSunk(boolean isSunk) {
		this.isSunk = isSunk;
	}
	
	/**
	 * "Setter" method that sets whether the ship is horizontally placed in the ocean array (true) or not (false).
	 * @param horizontal boolean value to represent if the ship is horizontal (true) or not (false).
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	
	/**
	 * "Setter" method that sets whether the ship was shot at previously (true) or not (false).
	 *  @param shotBefore boolean value to represent if the ship was previously shot (true) or not (false).
	 */
	public void setWasShotAt(boolean shotBefore) {
		this.wasShotAt = shotBefore;
	}
	
	
	/**
	 * Constructor method for the ship, will set up the length, hit array, wasShotAt boolean and isSunk boolean.
	 * @param length of the ship that will set the length property of the ship.
	 */
	public Ship (int length) {
		
		//Sets the length of the ship.
		this.length = length;
		
		//Populates the hit array with false boolean values to indicate no section of the ship has been hit yet.
		this.hit = new boolean[length];
		for (int i = 0; i < length; i++) {
			this.hit[i] = false;
		}

		//Sets the wasShotAt and isSunk boolean variables to false, i.e. the ship is not yet sunk and has not been shot at.
		setWasShotAt(false);
		setIsSunk(false);
	}
	
	
	/**
	 * Method to check whether it is permissible to place the current ship in the ocean array in a particular way.
	 * @param row of the bow of the ship.
	 * @param column of the bow of the ship.
	 * @param direction a boolean to show whether the ship is placed horizontally (true) or vertically (false).
	 * @param ocean with the 10 x 10 array in which the ship will be placed.
	 * @return boolean value, true to indicate if the potential placement met all the required conditions, and false otherwise.
	 */
	boolean okToPlaceShipAt(int row, int column, boolean direction, Ocean ocean) {
		
		//Puts the ship length into a shorter local variable name for convenience.
		int length = this.length;
		//Sets the potential direction of the ship to be placed.
		this.setHorizontal(direction);		
		
		/*Sets initial integer variables to represent the surrounding positions for a particular set of coordinates to be looped through. For example,
		the positions to the left or west of the given coordinates is -1, east or right is +1 (column count ascends to the right, up or north
		is -1 and down or south is +1 (row count ascends downwards in the ocean array).*/
		int westColumnLimit = -1;
		int eastColumnLimit = 1;
		int northRowLimit = - 1;
		int southRowLimit = 1;
		int overlapCounter = 0;
		
		//Initializes the placement pass variable to track if the placement is acceptable or not.
		boolean placementPass = false;
		
		//This section is to ensure that the ship stays within the parameters of the ocean, i.e. does not hang off the edge.
		if (this.isHorizontal() == true) {
			
			//If the ship is horizontal, the row will be fine if the bow of the ship is between rows 0 and 9 inclusive, and the method returns true.
			if (0 <= row && row <= 9) {
				
				placementPass = true;
				
			} else {
				
				//Otherwise the proposed position is not ok and the method returns false.
				return false;
			}
				
			/*If the ship is horizontal, the column has to take into account the length of the ship.
			Since the ships stretches west or to the left from the bow, the end of the ship cannot be to the left of column 0.*/
			if ( 0 <= (column - length) && column <= 9) {
				
				//Returns true if the position is ok and meets this condition
				placementPass = true;
								
			} else {
				
				//Otherwise, the proposed placement fails this test and the method returns false.
				return false;
			}
		}	
			
		//If the ship is to be placed vertically, the logic is similar and the ship must not hang off the edge of the ocean vertically.		
		if (this.isHorizontal() == false) {
			
			//Checks that the row of the bow, less the length of the ship is not above row 0, or spans beyond the ocean array.
			if ( 0 <= (row - length) && row <= 9) {
				
				placementPass = true;
				
			} else {
				
				//Otherwise, the proposed placement fails and the method returns false.
				return false;
			}
			
			//As long as the column of the vertical ship is between 0 and 9, inclusive, the placement will be ok and the method returns true.
			if (0 <= column && column <= 9) {
				
				placementPass = true;
								
			} else {
				
				return false;
			}
		}
		
		
		/*This section is to check whether the proposed placement overlaps with any other ships already in the ocean array.
		Starts with whether the ship is horizontal, will affect which positions will be checked.*/
		if (this.isHorizontal() == true) {
			
			/*First checks if the ship is on the right or east edge of the ocean array, i.e. if the bow column is on column 9.
			If it is, the square to the east (or right) of the ship will not be checked for overlap because it falls off the ocean.*/
			if (Ocean.OCEAN_COLUMNS - column == 1) {
				eastColumnLimit = 0;
			}
			
			/*Then it will check if the ship is on the left or west edge of the ocean array (if the ship is on column 0).
			If it is, the west limit variable will be set to 0, i.e. when the loop runs later, it will not check the position
			to the left or west of the ship because it will be off the ocean array.*/
			if (Ocean.OCEAN_COLUMNS - (column - length + 1) == Ocean.OCEAN_COLUMNS) {
				westColumnLimit = 0;
			}
			
			/*Will check if the ship is on the bottom row (row 9), if so, positions below the ship will not be checked for overlap
			because it will be off the ocean array.*/
			if (Ocean.OCEAN_ROWS - row == 1) {
				southRowLimit = 0;
			}
			
			/*Will check if the ship is on the top row (row 0), if so, positions above the ship will not be checked for overlap
			because it will be off the ocean array.*/
			if (Ocean.OCEAN_ROWS - row == Ocean.OCEAN_ROWS) {
				northRowLimit = 0;
			}
			
			//Initializes an integer variable to track if any positions overlap with other ships.
			overlapCounter = 0;
			
			/*Loops through the positions adjacent or surrounding the ship, including diagonally, but will be adjusted if any part
			of the ship is on an edge of the ocean array. If the ship is on a particular edge, the position beyond that side will
			not be checked (loop will add a 0 to the position on that side and will not check any adjacent positions on that side).*/
			for (int i = northRowLimit; i <= southRowLimit; i++) {
				
				for (int j = westColumnLimit - length + 1; j <= eastColumnLimit; j++) {
					
					/*Checks if the position is occupied already by another ship that is not an "EmptySea". If the position is empty,
					the loop continues to check through other positions surrounding the given coordinate.*/
					if (ocean.isOccupied(row + i, column + j) == false) {
						
						continue;
					
					//If the position is already occupied by another ship that is not an "EmptySea" the counter is incremented by one.
					} else if (ocean.isOccupied(row + i, column + j) == true) {
						
						overlapCounter += 1;
					}
				}
			}	
		
		//Applies a similar process as the one above, with an adjustment with a vertical ship placement.
		} else if (this.isHorizontal() == false) {
				
			/*Checks if the ship is on the right or east edge of the ocean array, i.e. if the bow column is on column 9. If it is,
			the square to the east (or right) of the ship will not be checked later for overlap because it falls off the ocean.*/
			if (Ocean.OCEAN_COLUMNS - column == 1) {
					eastColumnLimit = 0;
			}
				
			/*Checks if the ship is on the left or west edge of the ocean array, i.e. if the bow column is on column 0. If it is,
			the square to the west (or left) of the ship will not be checked later for overlap because it falls off the ocean.*/
			if (Ocean.OCEAN_COLUMNS - column == Ocean.OCEAN_COLUMNS) {
				westColumnLimit = 0;
			}
			
			/*Will check if the ship is on the bottom row (row 9), if so, positions below the ship will not be checked for overlap
			because it will be off the ocean array.*/
			if (Ocean.OCEAN_ROWS - row == 1) {
				southRowLimit = 0;
			}
			
			/*Will check if the ship is on the top row (row 0), if so, positions above the ship will not be checked for overlap
			because it will be off the ocean array.*/
			if (Ocean.OCEAN_ROWS - (row - length + 1) == Ocean.OCEAN_ROWS) {
				northRowLimit = 0;
			}
			
			//Initializes an integer variable to track if any positions overlap with other ships.
			overlapCounter = 0;
			
			/*Loops through the positions adjacent or surrounding the ship, including diagonally, but will be adjusted if any part
			of the ship is on an edge of the ocean array. If the ship is on a particular edge, the position beyond that side will
			not be checked (loop will add a 0 to the position on that side and will not check any adjacent positions on that side).*/
			for (int i = northRowLimit - length + 1; i <= southRowLimit; i++) {
				
				for (int j = westColumnLimit; j <= eastColumnLimit; j++) {
					
					/*Checks if the position is occupied already by another ship that is not an "EmptySea". If the position is empty,
					the loop continues to check through other positions surrounding the given coordinate.*/
					if (ocean.isOccupied(row + i, column + j) == false) {
						
						continue;
					
					//If the position is already occupied by another ship that is not an "EmptySea" the counter is incremented by one.
					} else if (ocean.isOccupied(row + i, column + j) == true) {
						
						overlapCounter += 1;	
					}
				}
			}
		}
		
		
		/*The proposed placement only passes and the method returns true if there is no overlap (i.e. the overlap counter remains at 0)
		and the placementPass boolean remains true.*/
		if (overlapCounter == 0 && placementPass == true) {
			
			return true;
			
		} else {
		
			//Otherwise, the placement does not pass and the method returns false.
			return false;
		}
	}
	
	
	/**
	 * Method to place the ship in the ocean array at the given coordinates.
	 * @param row of the bow of the ship.
	 * @param column of the bow of the ship.
	 * @param horizontal a boolean to show whether the ship is placed horizontally (true) or vertically (false), i.e. the direction of the ship.
	 * @param ocean with the 10 x 10 array in which the ship will be placed.
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		
		//Sets the position of the ship by assigning row and column values to the bow, and sets whether the ship is horizontal or vertical.
		this.setBowRow(row);
		this.setBowColumn(column);
		this.setHorizontal(horizontal);
		
		//Places the ship length in a shorter variable name for convenience.
		int length = this.length;
		
		//Variables to represent the stern (back of the ship), depending on whether the ship is horizontal or vertical.
		int sternRow;
		int sternColumn;
		 
		//Gets the ocean array, the ship will be placed into this array.
		Ship[][] shipArray = ocean.getShipArray();
		
		//If the ship is vertical, positions above the ship will be filled by the current ship (if the length is greater than 1).
		if (this.isHorizontal() == false) {
			
			sternRow = row - length + 1;
			
			//Loops through the positions from the row of the bow up the length of the ship and places the ship in the ocean array.
			for (int i = row; i >= sternRow; i--) {
				
				shipArray[i][column] = this; 
			}
		
		//If the ship is vertical, positions to the left of the ship will be filled by the current ship (if the length is greater than 1).
		} else if (this.isHorizontal() == true) {
			
			sternColumn = column - length + 1;
			
			//Loops through the positions from the column of the bow to the left stern of ship and places the ship in the ocean array.
			for (int i = column; i >= sternColumn; i--) {
												
				shipArray[row][i] = this;
			}
		}
	}
	
	/**
	 * Method to represent the ship being shot at with the given coordinates, and will update the hit array to record which section
	 * of the ship got hit.
	 * @param row of the position that is being shot at.
	 * @param column of the position that is being shot at.
	 * @return boolean value, will return true because the ship is calling this method to record being shot at and hit.
	 */
	boolean shootAt(int row, int column) {
		
		//Updates the boolean value to record that the ship was shot at, for display printing purposes.
		setWasShotAt(true);
		
		length = this.getLength();
		
		if ( (this.bowRow - row >= length) || (this.bowColumn - column >= length)) {
			
			return false;
			
		} else {
		
			//Updates the hit array for a vertically placed ship if the given column aligns with the column of the ship bow.
			if (this.isHorizontal() == false && column == this.getBowColumn()) {
				
				//Index position will be the difference between the given coordinate and the ship bow
				int arrayPosition = this.bowRow - row;
				
				//Places a true value at the index position to record which part of the ship was hit.
				this.hit[arrayPosition] = true;
								
				return true;	
			
			//Updates the hit array for a horizontally placed ship if the given row aligns with the row of the ship bow.
			} else if (this.isHorizontal() == true && row == this.getBowRow()) {
				
				//Index position will be the difference between the given coordinate and the ship bow
				int arrayPosition = this.bowColumn - column;
				
				//Places a true value at the index position to record which part of the ship was hit.
				this.hit[arrayPosition] = true;
								
				return true;	
			
			//Otherwise returns false
			} else {
				
				return false;
				
			}
		} 
	}
	
	/**
	 * Method to check if a ship was hit at a particular set of coordinates when printing the ocean.
	 * @param row of the position that is being shot at.
	 * @param column of the position that is being shot at.
	 * @return boolean value, will return true if a portion of a ship at the particular coordinates was hit.
	 */
	boolean shotAtDisplay(int row, int column) {
		
		/*If the ship is vertically placed and was shot at before, will translate the given coordinates to the
		ship's hit array and check if that particular section of the ship was hit.*/
		if (this.isHorizontal() == false && this.getWasShotAt() == true) {
			
			int arrayPosition = this.bowRow - row;
		
			//If the hit array index representing the given coordinates was hit, will return a true. 
			if (this.hit[arrayPosition] == true) {
				
				return true;
			
			//Otherwise will return a false.
			} else {
				
				return false;
			}
		
		/*If the ship is horizontally placed and was shot at before, will translate the given coordinates to the
		ship's hit array and check if that particular section of the ship was hit.*/
		} else if (this.isHorizontal() == true && this.getWasShotAt() == true) {
			
			int arrayPosition = this.bowColumn - column;
			
			//If the hit array index representing the given coordinates was hit, will return a true.
			if (this.hit[arrayPosition] == true) {
				
				return true;
			
			//Otherwise will return a false.
			} else {
				
				return false;
			}
		
		//If the ship was not shot at before, no part of the ship could have been hit already, and the method returns a false.
		} else {
			
			return false;
			
		}
		
	}
	
	/**
	 * Method to check if a ship is sunk or not.
	 * @return boolean value, will return true if the ship is sunk, false if the ship is still afloat.
	 */
	boolean isSunk() {
		
		//Integer counter to track how many sections of the ship have been hit already.
		int counter = 0;
		
		//For every true value in the hit array, the counter increments by 1
		for (int i = 0; i < this.length; i++) {
			
			if (hit[i] == true) {
				
				counter += 1;
			} 
		}
		
		/*If the number of hits is equal to the length of the ship, i.e. all sections of the ship have been hit,
		the ship is effectively sunk and the method returns a true value.*/
		if (counter == this.length) {
			
			setIsSunk(true);
			
			return true;
		
		//Otherwise the ship is still afloat and the method returns a false
		} else {
			
			return false;
		}
		
	}
	
	
	/**
	 * Method to return a single-character String to use in the ocean's print method, only for locations that have been shot at.
	 * This method is overriden by the EmptySea subclass.
	 * @return a single-character String representing if the location is occupied by a portion of a sunk ship or a ship that is hit.
	 */
	@Override
	public String toString() {
		
		//If the ship is already sunk, the ocean print method will display a "s".
		if (this.isSunk() == true) { 
			
			String finalString = "s";
			
			return finalString;
		
		//Otherwise if the section of the ship was hit, will display an "x".
		} else {
			
			String finalString = "x";
			
			return finalString;
		}
	}

}
	