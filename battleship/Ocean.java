package battleship;

import java.util.Random;

/**
 * This class contains the 10 x 10 array of ship objects to represent the ocean, or the underlying map for the game.
 * This class also tracks the number of shots fired during the game, as well as the number of hits and ships sunk.
 * The class has a number of methods to manipulate the 10 x 10 array including placing ships and shooting at given coordinates.
 */
public class Ocean {
	
	/**
	 * Integer variable to represent the number of rows for the ocean, will be set in stone and unchangeable afterwards.
	 */
	static final int OCEAN_ROWS = 10;
	
	/**
	 * Integer variable to represent the number of columns for the ocean, will be final and not able to changed afterwards.
	 */
	static final int OCEAN_COLUMNS = 10;	
	
	/**
	 * Integer variable to represent the number of battleships in the game, will only be 1 as per the given instructions.
	 */
	static final int NUMBER_BATTLESHIPS = 1;
	
	/**
	 * Integer variable to represent the number of cruisers in the game, will be 2 as per the given instructions.
	 */
	static final int NUMBER_CRUISERS = 2;
	
	/**
	 * Integer variable to represent the number of destroyers in the game, will be 3 as per the given instructions.
	 */
	static final int NUMBER_DESTROYERS = 3;
	
	/**
	 * Integer variable to represent the number of submarines in the game, will be 4 as per the given instructions.
	 */
	static final int NUMBER_SUBMARINES = 4;
	
	/**
	 * Integer variable to represent the total number of ships in the game, will be 10 as per the given instructions.
	 */
	static final int NUMBER_SHIPS = 10;
	
	/**
	 * Integer variable to track the number of shots fired during the game.
	 */
	private int shotsFired;
	
	/**
	 * Integer variable to track the number of hits fired during the game.
	 */
	private int hitCount;
	
	/**
	 * Integer variable to track the number of ships sunk during the game.
	 */
	private int shipsSunk;
	
	/**
	 * The 10 x 10 array of ships representing the ocean.
	 */
	private Ship[][] ships;
	
	/**
	 * "Getter" function to return the number of shots fired during the game.
	 * @return the number of shots fired in the game thus far.
	 */
	int getShotsFired() {
		return this.shotsFired;
	}
	
	/**
	 * "Getter" function to return the number of hits during the game.
	 * @return the number of hits registered in the game thus far.
	 */
	int getHitCount() {
		return this.hitCount;
	}
	
	/**
	 * "Getter" function to return the number of ships sunk during the game.
	 * @return the number of ships sunk in the game thus far.
	 */
	int getShipsSunk() {
		return this.shipsSunk;
	}	
	
	/**
	 * "Getter" function to return the 10 x 10 array of ships representing the ocean.
	 * @return the 10 x 10 ship array representing the ocean.
	 */
	Ship[][] getShipArray(){
		return this.ships;
	}
	
	/**
	 * Constructor method that creates an ocean and fills it with "Emptysea" ships to represent an empty ocean.
	 * Initializes variables for the class and their starting values.
	 */
	public Ocean() {
		
		//Sets the shots fired, hit count, and ships sunk to 0.
		this.shotsFired = 0;
		this.hitCount = 0;
		this.shipsSunk = 0;
		
		//Creates a new 10 x 10 array of ships.
		this.ships = new Ship[Ocean.OCEAN_ROWS][Ocean.OCEAN_COLUMNS]; 
		
		//For loop to fill each element of the array with EmptySea ships.
		for (int i = 0; i < OCEAN_ROWS; i++) {
			
			for (int j = 0; j < OCEAN_COLUMNS; j++) {
				
				//Creates a new EmptySea ship for each element at every index position.
				Ship emptySea = new EmptySea(); 
				
				/*Passes on the parameters for row (i), column (j), true for horizontal although this does not matter 
				because EmptySea has a length of 1, and this ocean instance.*/
				emptySea.placeShipAt(i, j, true, this); 
			}
		}
	}
	
	/**
	 * Creates the 10 ships that will be used in the game and places them randomly in the ocean (the 10 x 10 ships array).
	 * The ships will be placed in a manner that follows the given guidelines, e.g. ships are not adjacent to one another.
	 * This method places the larger ships first before smaller ones so that space does not run out.
	 */
	void placeAllShipsRandomly() {
		
		//Creates a new random value generator.
		Random random = new Random();
		
		/*Initializes variables to hold the randomized row number, randomized column number, and random boolean (true/false) number
		These variables will be passed onto the ship's placement testing method to see if the positioning matches all given conditions.*/
		int randRow = 0;
		int randColumn = 0;
		boolean randHorizontal = true;
		
		/*Loop to save time initializing each ship of the same type, there is only one battleship so it only runs once but the same
		structure was maintained as the other ship types to maintain consistency and also if the ship numbers change in the future.*/
		for (int i = 0; i < Ocean.NUMBER_BATTLESHIPS; i++) {
			
			//Creates a new battleship.
			Battleship battleship = new Battleship();
			
			//Boolean switch variable for the while loop.
			boolean placementPass = false;
			
			//While loop to keep trying different coordinate combinations until the given conditions are met.
			while (!placementPass) {
				
				/*Generates a random integer from 0 to 9 inclusive for the randomized row and columns variables, 
				and a random boolean for the horizontal variable.*/
				randRow = random.nextInt(10);
				randColumn = random.nextInt(10);
				randHorizontal = random.nextBoolean();
				
				/*Attempts to place the ship with the random coordinates by using the ship's testing method, returns a true (passes) or 
				false (coordinates do not pass, and the loop will try another set of random coordinates.*/
				boolean shipPlacementTest = battleship.okToPlaceShipAt(randRow, randColumn, randHorizontal, this);
				
				/*If the coordinates pass the required conditions, the battleship is placed into the ocean at those coordinates
				and the while loop is exited.*/
				if (shipPlacementTest) {
					battleship.placeShipAt(randRow, randColumn, randHorizontal, this);
					break;
				}
			}	
		}
		
		//Will loop through twice to place the two cruisers into the ocean.
		for (int i = 0; i < Ocean.NUMBER_CRUISERS; i++) {
			
			//Starts by creating a new cruiser.
			Cruiser cruiser = new Cruiser();
			
			//Boolean switch variable for the while loop.
			boolean placementPass = false;
			
			//While loop to keep trying different coordinate combinations until the given conditions are met.
			while (!placementPass) {
				
				/*Generates a random integer from 0 to 9 inclusive for the randomized row and columns variables, 
				and a random boolean for the horizontal variable.*/
				randRow = random.nextInt(10);
				randColumn = random.nextInt(10);
				randHorizontal = random.nextBoolean();
				
				/*Attempts to place the ship with the random coordinates by using the ship's testing method, returns a true (passes) or 
				false (coordinates do not pass, and the loop will try another set of random coordinates.*/
				boolean shipPlacementTest = cruiser.okToPlaceShipAt(randRow, randColumn, randHorizontal, this);
				
				/*If the coordinates pass the required conditions, the cruiser is placed into the ocean at those coordinates, which will
				happen twice (to place both cruiser ships).*/
				if (shipPlacementTest) {
					cruiser.placeShipAt(randRow, randColumn, randHorizontal, this);
					break;
				}
			}
		}
		
		//Will loop through three times to place the three destroyers into the ocean.
		for (int i = 0; i < Ocean.NUMBER_DESTROYERS; i++) {
			
			//Starts by creating a new destroyer.
			Destroyer destroyer = new Destroyer();
			
			//Boolean switch variable for the while loop.
			boolean placementPass = false;
			
			//While loop to keep trying different coordinate combinations until the given conditions are met.
			while (!placementPass) {
				
				/*Generates a random integer from 0 to 9 inclusive for the randomized row and columns variables, 
				and a random boolean for the horizontal variable.*/
				randRow = random.nextInt(10);
				randColumn = random.nextInt(10);
				randHorizontal = random.nextBoolean();
				
				/*Attempts to place the ship with the random coordinates by using the ship's testing method, returns a true (passes) or 
				false (coordinates do not pass, and the loop will try another set of random coordinates.*/
				boolean shipPlacementTest = destroyer.okToPlaceShipAt(randRow, randColumn, randHorizontal, this);
				
				/*If the coordinates pass the required conditions, the destroyer is placed into the ocean at those coordinates, which will
				happen twice (to place both cruiser ships).*/
				if (shipPlacementTest) {
					destroyer.placeShipAt(randRow, randColumn, randHorizontal, this);
					break;
				}
			}
		}
		
		//Will loop through four times to place the four submarines into the ocean.
		for (int i = 0; i < NUMBER_SUBMARINES; i++) {
			
			//Starts by creating a new submarine.
			Submarine submarine = new Submarine();
			
			//Boolean switch variable for the while loop.
			boolean placementPass = false;
			
			//While loop to keep trying different coordinate combinations until the given conditions are met.
			while (!placementPass) {
				
				/*Generates a random integer from 0 to 9 inclusive for the randomized row and columns variables, 
				and a random boolean for the horizontal variable.*/
				randRow = random.nextInt(10);
				randColumn = random.nextInt(10);
				randHorizontal = random.nextBoolean();
				
				/*Attempts to place the ship with the random coordinates by using the ship's testing method, returns a true (passes) or 
				false (coordinates do not pass, and the loop will try another set of random coordinates.*/
				boolean shipPlacementTest = submarine.okToPlaceShipAt(randRow, randColumn, randHorizontal, this);
				
				/*If the coordinates pass the required conditions, the destroyer is placed into the ocean at those coordinates, which will
				happen twice (to place both cruiser ships).*/
				if (shipPlacementTest) {
					submarine.placeShipAt(randRow, randColumn, randHorizontal, this);
					break;
				}
			}
		}
	}
	
	/**
	 * Method to check if a particular set of coordinates is occupied by a ship, i.e. the position is not an EmptySea.
	 * @param row number for the ship coordinate to be checked.
	 * @param column number for the ship coordinate to be checked.
	 * @return false if the position is occupied by an "EmptySea ship", or true otherwise.
	 */
	boolean isOccupied(int row, int column) {
		
		//Gets the ship from the 10x10 array at the given coordinates
		Ship[][] ship = this.getShipArray();
		Ship shipCell = ship[row][column];
		
		//If the coordinates contain an EmptySea ship, the method returns false, i.e. the position is not occupied
		if (shipCell.getShipType() == "empty") {
			
			return false;
			
		} else {
			
			//Otherwise, the position is occupied by a ship that is not an "EmptySea" and the method returns true
			return true;
		}
	}
	
	/**
	 * This is to represent the shooting process towards the set of coordinates provided by the user.
	 * @param row number for the coordinate that will be fired upon.
	 * @param column number for the coordinate that will be fired upon.
	 * @return true if a ship is hit, otherwise false if the ship is already sunk or if it is an "EmptySea".
	 */
	boolean shootAt(int row, int column) {
		
		//Gets the ship at the provided row and column coordinates.
		Ship[][] oceanGrid = this.getShipArray();
		Ship gridPosition = oceanGrid[row][column];
		
		/*Will first check if the ship at the position is already sunk. If so, the method returns false as 
		nothing was hit. As instructed, an "EmptySea" position cannot be sunk and will return false.*/	
		if (gridPosition.isSunk() == true ){
			
			//Increments the shots fired by 1, but not the hits count or ships sunk.
			shotsFired += 1;
			
			//Returns false to indicate that nothing was hit.
			return false;
		
		//If the position is an "EmptySea", nothing is hit and the method returns false.
		} else if (gridPosition.getShipType() == "empty") {
								
			/*Activates the EmptySea's shootAt method that will indicate that the ship was shot at, and will
			update the display marking when the position is printed in the ocean. However, as instructed,
			the EmptySea shootAt method will always return false to indicate that nothing was hit.*/
			gridPosition.shootAt(row, column);
			
			//Even though nothing was hit, the shots fired counter will increment by 1.
			shotsFired += 1;
			
			//Returns false to indicate nothing was hit.
			return false;
				
		} else {
			
			/*Otherwise, if the position is not empty or sunk, the position must be a floating ship that is hit
			The shots fired counter and hits counter will be updated incrementally by 1.*/
			shotsFired += 1;
			
			//As instructed, every hit is counted regardless of where the location was hit for the first time.
			hitCount +=1;
			
			/*The ship's shootAt method is executed to update the hit array for the ship, tracking where the shit
			was hit. As instructed, returns true every time the user shoots at the ship location.*/
			gridPosition.shootAt(row, column);
			
			/*After the ship is hit, will check if the ship is sunk. If every position of the ship is hit,
			the method will return true and the ship sunk counter will increase by 1.
			The user will also be notified of which type of ship was sunk.*/
			if (gridPosition.isSunk() == true) {
				
				shipsSunk += 1;
				System.out.println("You just sank a " + gridPosition.getShipType() + ".");
				System.out.println("");
			}
			
			//Returns true to indicate that a ship was hit.
			return true;
		}
	}
		
	/**
	 * This method is to check whether is game is over, i.e. if all 10 ships in the ocean have been sunk.
	 * @return boolean value to represent whether the game is over (true) or not over (false).
	 */
	boolean isGameOver() {
		
		/*If all the ships in the game have been sunk, a celebratory message will be printed and the method
		will return a true value. Will be used in a loop for the main game method to check if the game is ongoing.*/
		if (shipsSunk == NUMBER_SHIPS) {
			
			//Displays totals for shots fired, how many hits were made, and how many ships were sunk
			System.out.println("");
			System.out.println("Game Summary: ");
			System.out.println("Shots Fired: " + this.getShotsFired());
			System.out.println("Hit Count: " + this.getHitCount());
			System.out.println("Ships Sunk: " + this.getShipsSunk());
			
			System.out.println("");
			System.out.println("You sank all 10 ships! Congratulations, the game is over!");
			
			return true;
		
		//Otherwise, returns false if the game is ongoing.
		} else {
			
			return false;
		}
	}
	
	/**
	 * This is to print the ocean array of 10 x 10 but as instructed, only locations that have been shot at.
	 * Positions that have not been shot at by the user will be represented with a "."
	 */
	void print() {
		
		//Gets the ocean array of ships to be printed.
		Ship[][] shipsArray = this.getShipArray();
		
		//Prints some spacing for formatting purposes.
		System.out.print("  ");
		
		//*Prints number markers for the first row to aid the user.
		for (int i = 0; i < Ocean.OCEAN_COLUMNS; i++) {
			
			System.out.print(i);
			System.out.print(" ");
		}
		
		System.out.println();
		
		//Loops through the ocean 10 x 10 array to get the row and column coordinates, starting with the rows.
		for (int i = 0; i < Ocean.OCEAN_ROWS; i++) {
			
			//Prints number markers for the columns alongside the left side of the ocean array.
			System.out.print(i);
			System.out.print(" ");			
			
			//Loops through the columns.
			for (int j = 0; j < Ocean.OCEAN_COLUMNS; j++) {
				
				//Gets each ship in the ocean array.
				Ship selectedShip = shipsArray[i][j];
				
				/*Checks if the ship was shot at previously at the particular set of coordinates.
				The ship will call on its own shotAtDisplay method to check if the position in the hit array
				translates to the ship already being hit at the given row and column.*/
				if (selectedShip.shotAtDisplay(i, j) == true) {
					
					/*If the ship was already hit by a shot before, the method will call the ship's toString method. 
					If the ship was hit and sunk, will return a "s", otherwise, will return an "x" to be printed.*/
					String hitDisplay = selectedShip.toString();
					System.out.print(hitDisplay);
					System.out.print(" ");
				
				/*If the position is empty and was previously shot at before, the method will return a "-" as 
				instructed to be printed. The shootAt method cannot be called because it will always return false
				as instructed for an EmptySea ship.*/
				} else if (selectedShip.getShipType() == "empty" && selectedShip.getWasShotAt() == true) {
					
					//Prints "-" if the empty position was fired upon previously.
					String hitDisplay = selectedShip.toString();
					System.out.print(hitDisplay);
					System.out.print(" ");
				
				/*Otherwise, if there are no hit ships, sunk ships, and previously fired upon ocean positions, the 
				method will print a ".' for that position.*/
				} else {
					
					System.out.print(".");
					System.out.print(" ");
				}
			}
			
			System.out.println();
		}
	}
	
}
