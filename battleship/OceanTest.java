package battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit test class for Ocean. Will test all the method with the exception of print().
 */
class OceanTest {

	/**
	 * Creating an ocean variable that will be used during the test cases.
	 */
	Ocean ocean;
	
	/**
	 * Setting the number of battleships to 1 as stated in the instructions.
	 */
	static int NUM_BATTLESHIPS = 1;
	
	/**
	 * Setting the number of cruisers to 2 as stated in the instructions.
	 */
	static int NUM_CRUISERS = 2;
	
	/**
	 * Setting the number of destroyers to 3 as stated in the instructions.
	 */
	static int NUM_DESTROYERS = 3;
	
	/**
	 * Setting the number of submarines to 4 as stated in the instructions.
	 */
	static int NUM_SUBMARINES = 4;
	
	/**
	 * Setting the size of the ocean to 10, which represents the number of rows and the number of columns in the ocean ships array.
	 */
	static int OCEAN_SIZE = 10;
	
	/**
	 * Constructor method to create a new instance of ocean.
	 * @throws Exception if the constructor fails.
	 */
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}
	
	/**
	 * Test method to check if a new ocean is filled with EmptySea ships.
	 */
	@Test
	void testEmptyOcean() {
		
		//Tests that all locations in the ocean are "empty"
		
		Ship[][] ships = ocean.getShipArray();
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				
				assertEquals("empty", ship.getShipType());
			}
		}
		
		assertEquals(0, ships[0][0].getBowRow());
		assertEquals(0, ships[0][0].getBowColumn());
		
		assertEquals(5, ships[5][5].getBowRow());
		assertEquals(5, ships[5][5].getBowColumn());
		
		assertEquals(9, ships[9][0].getBowRow());
		assertEquals(0, ships[9][0].getBowColumn());
	}
	
	/**
	 * Test method to check if placeAllShipsRandomly works as intended and that each ship is placed correctly in the ocean.
	 */
	@Test
	void testPlaceAllShipsRandomly() {
		
		//Tests that the correct number of each ship type is placed in the ocean.
		
		ocean.placeAllShipsRandomly();

		Ship[][] ships = ocean.getShipArray();
		ArrayList<Ship> shipsFound = new ArrayList<Ship>();
		
		int numBattlehips = 0;
		int numCruisers = 0;
		int numDestroyers = 0;
		int numSubmarines = 0;
		int numEmptySeas = 0;
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				if (!shipsFound.contains(ship)) {
					shipsFound.add(ship);
				}
			}
		}
		
    //Tests to see if the ship type is correct for each ship in the arraylist.
		for (Ship ship : shipsFound) {
			if ("battleship".equals(ship.getShipType())) {		
				numBattlehips++;
			} else if ("cruiser".equals(ship.getShipType())) {
				numCruisers++;
			} else if ("destroyer".equals(ship.getShipType())) {
				numDestroyers++;
			} else if ("submarine".equals(ship.getShipType())) {
				numSubmarines++;
			} else if ("empty".equals(ship.getShipType())) {
				numEmptySeas++;
			}
		}
		
		assertEquals(NUM_BATTLESHIPS, numBattlehips);
		assertEquals(NUM_CRUISERS, numCruisers);
		assertEquals(NUM_DESTROYERS, numDestroyers);
		assertEquals(NUM_SUBMARINES, numSubmarines);
		
		//Calculate total number of available spaces and occupied spaces.
		int totalSpaces = OCEAN_SIZE * OCEAN_SIZE; 
		int occupiedSpaces = (NUM_BATTLESHIPS * 4)
				+ (NUM_CRUISERS * 3)
				+ (NUM_DESTROYERS * 2)
				+ (NUM_SUBMARINES * 1);
		
		//test number of empty seas, each with length of 1.
		assertEquals(totalSpaces - occupiedSpaces, numEmptySeas);
	}

	/**
	 * Test method to check if a location is occupied by a certain ship (not an EmptySea) after the ship is placed at that particular location.
	 */
	@Test
	void testIsOccupied() {

		//Testing the isOccupied method across four ship subclasses.
    //Places a destroyer in the ocean and then checks if locations are occupied (returns true) or not (returns false).
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.isOccupied(1, 5));
		assertTrue(ocean.isOccupied(0, 5));
		
    //Places a submarine in the ocean and then checks if locations are is occupied (returns true) or not (returns false).
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
				
		assertTrue(ocean.isOccupied(0, 0));
		
		//Places a battleship in the ocean and then checks if locations are occupied (returns true) or not (returns false).
		Ship battleship = new Battleship();
		row = 5;
		column = 9;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.isOccupied(5, 9));
		assertTrue(ocean.isOccupied(5, 8));
		assertTrue(ocean.isOccupied(5, 7));
		assertTrue(ocean.isOccupied(5, 6));
		
		//Places a cruiser in the ocean and then checks if locations are occupied (returns true) or not (returns false).
		Ship cruiser = new Cruiser();
		row = 8;
		column = 2;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.isOccupied(8, 2));
		assertTrue(ocean.isOccupied(7, 2));
		assertTrue(ocean.isOccupied(6, 2));
		
    //Places an emptysea in the ocean and then checks the location returns a false as expected.
		Ship empty = new EmptySea();
		
		row = 6;
		column = 6;
		horizontal = false;
		empty.placeShipAt(row, column, horizontal, ocean);
		assertFalse(ocean.isOccupied(6, 6));
		
	}

	/**
	 * This test method is to check that the ocean shootAt method works as intended, e.g. ship shootAt methods are called and hits or misses are registered.
	 */
	@Test
	void testShootAt() {
	
		//Testing the ocean shootAt method and whether hits are registered for different ship subclasses.
		assertFalse(ocean.shootAt(0, 1));
		
    //Places a destroyer in the ocean and then shoots at its location.
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(destroyer.getWasShotAt());
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		
		//Places a submarine in the ocean and then shoots at its location.
		Submarine submarine = new Submarine();
		row = 9;
		column = 9;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(submarine.getWasShotAt());
		assertTrue(ocean.shootAt(9, 9));
		assertTrue(submarine.getWasShotAt());
		assertTrue(submarine.isSunk());
		//Since the submarine is now sunk, shooting at the location again should return false.
		assertFalse(ocean.shootAt(9, 9));
		
    //Places a cruiser in the ocean and then shoots at its location.
		Cruiser cruiser = new Cruiser();
		row = 8;
		column = 4;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(cruiser.getWasShotAt());
		assertTrue(ocean.shootAt(8, 3));
		assertTrue(submarine.getWasShotAt());
		
		//Places a battleship in the ocean and then shoots at its location.
		Battleship battleship = new Battleship();
		row = 0;
		column = 9;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(battleship.getWasShotAt());
		assertTrue(ocean.shootAt(0, 7));
		assertTrue(battleship.getWasShotAt());
	}

	/**
	 * This test method is to check if the shots fired counter and getter method for shots fired works properly and as intended.
	 */
	@Test
	void testGetShotsFired() {
		
		//should be all false - no ships added yet.
		assertFalse(ocean.shootAt(0, 1));
		assertFalse(ocean.shootAt(1, 0));
		assertFalse(ocean.shootAt(3, 3));
		assertFalse(ocean.shootAt(9, 9));
		assertEquals(4, ocean.getShotsFired());
		
		//Testing the getter method to return shots fired across different ship subclasses.
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
    //Places a submarine in the ocean.
		Submarine submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
    //Places a cruiser in the ocean.
		Cruiser cruiser = new Cruiser();
		row = 5;
		column = 9;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
    //Places a battleship in the ocean.
		Battleship battleship = new Battleship();
		row = 7;
		column = 8;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);	
		
		//Will shoot at the ships that were placed and checks if the shots were counted.
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(6, ocean.getShotsFired());
		
		assertFalse(ocean.shootAt(0, 1));
		assertTrue(ocean.shootAt(0, 0));
		assertTrue(submarine.isSunk());
		assertEquals(8, ocean.getShotsFired());
		
		//Will test shooting at a sunk ship, should return a false value.
		assertFalse(ocean.shootAt(0, 0));
		
		assertTrue(ocean.shootAt(5, 9));
		assertFalse(cruiser.isSunk());
		
		assertFalse(ocean.shootAt(6, 8));
		assertTrue(ocean.shootAt(7, 8));
		assertTrue(battleship.getWasShotAt());
		//Will test hitting the same location twice.
		assertTrue(ocean.shootAt(7, 8));
		
		assertEquals(13, ocean.getShotsFired());
	}

	/**
	 * This test method checks if the getter method and the counter for total hits in the game works properly as intended.
	 * Will shoot at different locations, the same location on a floating ship, and a sunk ship location.
	 */
	@Test
	void testGetHitCount() {
		
		//Tests the getter method to return the hit count. Also testing across different subclasses.
    //Places a destroyer in the ocean.
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
    //Places a submarine in the ocean.
		Submarine submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
    //Places a cruiser in the ocean.
		Cruiser cruiser = new Cruiser();
		row = 5;
		column = 9;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
    //Places a battleship in the ocean.
		Battleship battleship = new Battleship();
		row = 7;
		column = 8;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);	
		
    //Will shoot at the destroyer, should not be sunk yet.
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		
		//Shoots again at the destroyer, this time it should be sunk.
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(2, ocean.getHitCount());
		
		//Will test shooting a sunk ship location, should return false and should not register another hit.
		assertFalse(ocean.shootAt(0, 5));
		assertEquals(2, ocean.getHitCount());
		
    //Will shoot at and sink the submarine.
		assertFalse(ocean.shootAt(0, 1));
		assertTrue(ocean.shootAt(0, 0));
		assertTrue(submarine.isSunk());
		assertEquals(3, ocean.getHitCount());
		
		//Will test hitting the same section of a ship repeatedly, expecting the hit count to increase as per the instructions
		assertTrue(ocean.shootAt(5, 9));
		assertTrue(cruiser.getWasShotAt());
		assertTrue(ocean.shootAt(5, 9));
		assertTrue(ocean.shootAt(5, 9));		
		assertEquals(6, ocean.getHitCount());
		
    //Will shoot at the battleship and should register a hit.
		assertTrue(ocean.shootAt(7, 7));
		assertTrue(battleship.getWasShotAt());
		assertEquals(7, ocean.getHitCount());
		
	}
	
	/**
	 * This test method checks if the getter method and the counter for ships sunk in the game works properly as intended.
	 */
	@Test
	void testGetShipsSunk() {
		
		//Testing getter method to return the number of ships sunk across different ship subclasses
    //Places a destroyer in the ocean.
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
    //Places a submarine in the ocean.
		Submarine submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
    
		submarine.placeShipAt(row, column, horizontal, ocean);
		
    //Places a cruiser in the ocean.
		Cruiser cruiser = new Cruiser();
		row = 5;
		column = 9;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
    //Places a battleship in the ocean.
		Battleship battleship = new Battleship();
		row = 7;
		column = 8;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
    //Checks that the ships sunk count is still 0 after a hit.
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		
    //Checks that the ships sunk count increases by 1 after a destroyer is sunk.
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(2, ocean.getHitCount());
		assertEquals(1, ocean.getShipsSunk());
		
    //Checks that the ships sunk count increases by 1 after a submarine is sunk.
		assertTrue(ocean.shootAt(0, 0));
		assertTrue(submarine.isSunk());
		assertEquals(3, ocean.getHitCount());
		assertEquals(2, ocean.getShipsSunk());
		
    //Checks that hitting a ship location that is already sunk does not increase the sunk count.
		assertFalse(ocean.shootAt(0, 0));
		assertEquals(3, ocean.getHitCount());
		assertEquals(2, ocean.getShipsSunk());
		
		assertTrue(ocean.shootAt(5, 9));
		assertTrue(ocean.shootAt(4, 9));
		assertTrue(cruiser.getWasShotAt());
		assertTrue(ocean.shootAt(4, 9));
		assertEquals(6, ocean.getHitCount());
		assertEquals(2, ocean.getShipsSunk());
		
    //Checks that the ships sunk count increases by 1 after a cruiser is sunk.
		assertTrue(ocean.shootAt(3, 9));
		assertTrue(cruiser.isSunk());
		assertEquals(7, ocean.getHitCount());
		assertEquals(3, ocean.getShipsSunk());
		
    //Checks that the ships sunk count increases by 1 after a battleship is sunk.
		assertTrue(ocean.shootAt(7, 5));
		assertTrue(ocean.shootAt(7, 6));
		assertTrue(ocean.shootAt(7, 7));
		assertTrue(ocean.shootAt(7, 8));
		assertTrue(battleship.isSunk());
		assertEquals(11, ocean.getHitCount());
		assertEquals(4, ocean.getShipsSunk());
	}

	/**
	 * This test method is to check if the getter method for returning the ship array in ocean works properly.
	 */
	@Test
	void testGetShipArray() {
		
		//Testing the ship array before and after placement of different subclasses of ships
		Ship[][] shipArray = ocean.getShipArray();
		assertEquals(OCEAN_SIZE, shipArray.length);
		assertEquals(OCEAN_SIZE, shipArray[0].length);
		
		assertEquals("empty", shipArray[0][0].getShipType());
		
		//Places a destroyer in the ocean and checks that the ships array returns the correct ship for the location.
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertEquals("destroyer", shipArray[0][5].getShipType());
		
    //Places a submarine in the ocean and checks that the ships array returns the correct ship for the location.
		Submarine submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertEquals("submarine", shipArray[0][0].getShipType());
		
    //Places a cruiser in the ocean and checks that the ships array returns the correct ship for the location.
		Cruiser cruiser = new Cruiser();
		row = 5;
		column = 9;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		assertEquals("cruiser", shipArray[3][9].getShipType());
		
    //Places a battleship in the ocean and checks that the ships array returns the correct ship for the location.
		Battleship battleship = new Battleship();
		row = 7;
		column = 8;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertEquals("battleship", shipArray[7][5].getShipType());
	}
	
	/**
	 * Test method for isGameOver, which checks if all 10 ships have been sunk. If so, the user finishes the game.
	 */
	@Test
	void testIsGameOver() {
		
		//Testing if the game finishes after 10 ships are sunk.
    //Will first place the 10 ships and will subsequently shoot at them until they are sunk.
		Battleship battleship1 = new Battleship();
		battleship1.placeShipAt(0, 4, true, ocean);
		
		Cruiser cruiser1 = new Cruiser();
		cruiser1.placeShipAt(3, 3, true, ocean);
		Cruiser cruiser2 = new Cruiser();
		cruiser2.placeShipAt(3, 8, true, ocean);
		
		Destroyer destroyer1 = new Destroyer();
		destroyer1.placeShipAt(5, 3, true, ocean);
		Destroyer destroyer2 = new Destroyer();
		destroyer2.placeShipAt(5, 6, true, ocean);
		Destroyer destroyer3 = new Destroyer();
		destroyer3.placeShipAt(5, 9, true, ocean);
		
		Submarine submarine1 = new Submarine();
		submarine1.placeShipAt(7, 2, true, ocean);
		Submarine submarine2 = new Submarine();
		submarine2.placeShipAt(7, 4, true, ocean);
		Submarine submarine3 = new Submarine();
		submarine3.placeShipAt(7, 6, true, ocean);
		Submarine submarine4 = new Submarine();
		submarine4.placeShipAt(7, 8, true, ocean);
		
		
		assertTrue(ocean.shootAt(0, 4));
		assertTrue(ocean.shootAt(0, 3));
		assertTrue(ocean.shootAt(0, 2));
		assertTrue(ocean.shootAt(0, 1));
		assertTrue(battleship1.isSunk());
		
		
		assertTrue(ocean.shootAt(3, 3));
		assertTrue(ocean.shootAt(3, 2));
		assertTrue(ocean.shootAt(3, 1));
		assertTrue(cruiser1.isSunk());
		
		assertTrue(ocean.shootAt(3, 8));
		assertTrue(ocean.shootAt(3, 7));
		assertTrue(ocean.shootAt(3, 6));
		assertTrue(cruiser2.isSunk());
		
		
		assertTrue(ocean.shootAt(5, 3));
		assertTrue(ocean.shootAt(5, 2));
		assertTrue(destroyer1.isSunk());
		
		assertTrue(ocean.shootAt(5, 6));
		assertTrue(ocean.shootAt(5, 5));
		assertTrue(destroyer2.isSunk());
		
		assertTrue(ocean.shootAt(5, 9));
		assertTrue(ocean.shootAt(5, 8));
		assertTrue(destroyer3.isSunk());
		
		
		assertTrue(ocean.shootAt(7, 2));
		assertTrue(submarine1.isSunk());
		
		assertTrue(ocean.shootAt(7, 4));
		assertTrue(submarine2.isSunk());
		
		assertTrue(ocean.shootAt(7, 6));
		assertTrue(submarine3.isSunk());
		
		assertTrue(ocean.shootAt(7, 8));
		assertTrue(submarine4.isSunk());
		
		//Checks if the game is over after all 10 ships are sunk, should return true.
		assertEquals(10, ocean.getShipsSunk());
		assertTrue(ocean.isGameOver());
	}

}
