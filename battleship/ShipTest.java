package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit test class for all the methods in Ship.
 */
class ShipTest {

	/**
	 * Creating an ocean variable that will be used during the test cases.
	 */
	Ocean ocean;
	
	/**
	 * Creating a ship variable that will be used during the test cases.
	 */
	Ship ship;
	
	/**
	 * Constructor method to create a new instance of ocean.
	 * @throws Exception if the constructor fails.
	 */
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	/**
	 * Test method to check if the getter method to return the ship length works properly as intended.
	 */
	@Test
	void testGetLength() {
		
		/*Testing the getter method to return the ship length across all ship subclasses.
    Checks the getter method for a battleship.*/
		ship = new Battleship();
		assertEquals(4, ship.getLength());
		
    //Checks the getter method for a cruiser.
		ship = new Cruiser();
		assertEquals(3, ship.getLength());
		
    //Checks the getter method for a destroyer.
		ship = new Destroyer();
		assertEquals(2, ship.getLength());
		
    //Checks the getter method for a submarine.
		ship = new Submarine();
		assertEquals(1, ship.getLength());
		
    //Checks the getter method for an empty sea.
		ship = new EmptySea();
		assertEquals(1, ship.getLength());
	}

	/**
	 * Test method to check if the getter method to return the row number of the ship bow works properly as intended.
	 */
	@Test
	void testGetBowRow() {
		
		/*Testing the getter method to return the row number of the ship bow across all ship subclasses.
    Places a battleship and checks the row number for its ship bow.*/
		Ship battleship = new Battleship();
		int rowBattleship = 0;
		int columnBattleship = 4;
		boolean horizontalBattleship = true;
		battleship.placeShipAt(rowBattleship, columnBattleship, horizontalBattleship, ocean);
		assertEquals(rowBattleship, battleship.getBowRow());
		
    //Places a cruiser and checks the row number for its ship bow.
		Ship cruiser = new Cruiser();
		int rowCruiser = 4;
		int columnCruiser = 9;
		boolean horizontalCruiser = false;
		cruiser.placeShipAt(rowCruiser, columnCruiser, horizontalCruiser, ocean);
		assertEquals(rowCruiser, cruiser.getBowRow());
		
    //Places a destroyer and checks the row number for its ship bow.
		Ship destroyer = new Destroyer();
		int rowDestroyer = 5;
		int columnDestroyer = 5;
		boolean horizontalDestroyer = true;
		destroyer.placeShipAt(rowDestroyer, columnDestroyer, horizontalDestroyer, ocean);
		assertEquals(rowDestroyer, destroyer.getBowRow());
		
    //Places a submarine and checks the row number for its ship bow.
		Ship submarine = new Submarine();
		int rowSubmarine = 9;
		int columnSubmarine = 9;
		boolean horizontalSubmarine = true;
		submarine.placeShipAt(rowSubmarine, columnSubmarine, horizontalSubmarine, ocean);
		assertEquals(rowSubmarine, submarine.getBowRow());
		
    //Places an emptysea and checks the row number for its ship bow.
		Ship empty = new EmptySea();
		int rowEmpty= 9;
		int columnEmpty = 9;
		boolean horizontalEmpty = true;
		empty.placeShipAt(rowEmpty, columnEmpty, horizontalEmpty, ocean);
		assertEquals(rowEmpty, empty.getBowRow());
	}

	/**
	 * Test method to check if the getter method to return the column number of the ship bow works properly as intended.
	 */
	@Test
	void testGetBowColumn() {
		
		/*Testing the getter method to return the column number of the ship bow across all ship subclasses.
    Places a battleship and checks the column number for its ship bow.*/
		Ship battleship = new Battleship();
		int rowBattleship = 0;
		int columnBattleship = 4;
		boolean horizontalBattleship = true;
		battleship.placeShipAt(rowBattleship, columnBattleship, horizontalBattleship, ocean);
		assertEquals(columnBattleship, battleship.getBowColumn());	
		
    //Updates a column number for the ship bow and checks to see if method returns the new number.
		int newColumnBattleship = 4;
		battleship.setBowColumn(newColumnBattleship);
		assertEquals(newColumnBattleship, battleship.getBowColumn());	
		
    //Places a cruiser and checks the column number for its ship bow.
		Ship cruiser = new Cruiser();
		int rowCruiser = 4;
		int columnCruiser = 9;
		boolean horizontalCruiser = false;
		cruiser.placeShipAt(rowCruiser, columnCruiser, horizontalCruiser, ocean);
		assertEquals(columnCruiser, cruiser.getBowColumn());
		
    //Places a destroyer and checks the column number for its ship bow.
		Ship destroyer = new Destroyer();
		int rowDestroyer = 5;
		int columnDestroyer = 5;
		boolean horizontalDestroyer = true;
		destroyer.placeShipAt(rowDestroyer, columnDestroyer, horizontalDestroyer, ocean);
		assertEquals(columnDestroyer, destroyer.getBowColumn());
		
    //Places a submarine and checks the column number for its ship bow.
		Ship submarine = new Submarine();
		int rowSubmarine = 9;
		int columnSubmarine = 9;
		boolean horizontalSubmarine = true;
		submarine.placeShipAt(rowSubmarine, columnSubmarine, horizontalSubmarine, ocean);
		assertEquals(columnSubmarine, submarine.getBowColumn());
		
    //Places an empty sea and checks the column number for its ship bow.
		Ship empty = new EmptySea();
		int rowEmpty = 9;
		int columnEmpty = 9;
		boolean horizontalEmpty = true;
		empty.placeShipAt(rowEmpty, columnEmpty, horizontalEmpty, ocean);
		assertEquals(columnEmpty, empty.getBowColumn());
	}

	/**
	 * Test method to check if the getter method to return the ship's hit array works properly as intended.
	 */
	@Test
	void testGetHit() {
		
		/*Testing the getter method to return the hit array with the correct index elements across different ship subclasses.
    Checks if the hit array for a new battleship has 4 elements that are all false.*/
		Ship battleship = new Battleship();
		boolean[] hitsBattleship = new boolean[4];
		assertArrayEquals(hitsBattleship, battleship.getHit());
		assertFalse(battleship.getHit()[0]);
		assertFalse(battleship.getHit()[1]);
		assertFalse(battleship.getHit()[2]);
		assertFalse(battleship.getHit()[3]);
		
    //Checks if the hit array for a new cruiser has 3 elements that are all false.
		Ship cruiser = new Cruiser();
		boolean[] hitsCruiser = new boolean[3];
		assertArrayEquals(hitsCruiser, cruiser.getHit());
		assertFalse(cruiser.getHit()[0]);
		assertFalse(cruiser.getHit()[1]);
		assertFalse(cruiser.getHit()[2]);

    //Checks if the hit array for a new destroyer has 2 elements that are all false.
		Ship destroyer = new Destroyer();
		boolean[] hitsDestroyer = new boolean[2];
		assertArrayEquals(hitsDestroyer, destroyer.getHit());
		assertFalse(destroyer.getHit()[0]);
		assertFalse(destroyer.getHit()[1]);
		
    //Checks if the hit array for a new submarine has one element that is false.
		Ship submarine = new Submarine();
		boolean[] hitsSubmarine = new boolean[1];
		assertArrayEquals(hitsSubmarine, submarine.getHit());
		assertFalse(submarine.getHit()[0]);
		
	}

	/**
	 * Test method to check if the getter method to return whether the ship is horizontal or not works properly.
	 */
	@Test
	void testIsHorizontal() {
		
		//Testing the setter method for the horizontal boolean across four ship subclasses (does not matter is EmptySea is horizontal or not).
		Ship battleship = new Battleship();
		int rowBattleship = 0;
		int columnBattleship = 4;
		boolean horizontalBattleship = true;
		battleship.placeShipAt(rowBattleship, columnBattleship, horizontalBattleship, ocean);
		assertTrue(battleship.isHorizontal());
		
		//Testing a switch of the boolean value after it was previously set.
		boolean newHorizontalBattleship = false;
		battleship.setHorizontal(newHorizontalBattleship);
		assertFalse(battleship.isHorizontal());
		
    //Checks the method for a new cruiser.
		Ship cruiser = new Cruiser();
		int rowCruiser = 4;
		int columnCruiser = 9;
		boolean horizontalCruiser = false;
		cruiser.placeShipAt(rowCruiser, columnCruiser, horizontalCruiser, ocean);
		assertFalse(cruiser.isHorizontal());
		
    //Checks the method for a new destroyer.
		Ship destroyer = new Destroyer();
		int rowDestroyer = 5;
		int columnDestroyer = 5;
		boolean horizontalDestroyer = true;
		destroyer.placeShipAt(rowDestroyer, columnDestroyer, horizontalDestroyer, ocean);
		assertTrue(destroyer.isHorizontal());
		
    //Checks the method for a new submarine.
		Ship submarine = new Submarine();
		int rowSubmarine = 9;
		int columnSubmarine = 9;
		boolean horizontalSubmarine = true;
		submarine.placeShipAt(rowSubmarine, columnSubmarine, horizontalSubmarine, ocean);
		assertTrue(submarine.isHorizontal());
		
	}

	/**
	 * Test method to check if the getter method to return the ship's type works properly.
	 */
	@Test
	void testGetShipType() {
		
		/*Testing the getter method to return the ship type across all ship subclasses.
    Checks for a new battleship.*/
		Battleship battleship = new Battleship();
		assertEquals("battleship", battleship.getShipType());
		
    //Checks for a new cruiser.
		Cruiser cruiser = new Cruiser();
		assertEquals("cruiser", cruiser.getShipType());
		
    //Checks for a new destroyer.
		Destroyer destroyer = new Destroyer();
		assertEquals("destroyer", destroyer.getShipType());
		
    //Checks for a new submarine.
		Submarine submarine = new Submarine();
		assertEquals("submarine", submarine.getShipType());
		
    //Checks for an empty sea.
		EmptySea empty = new EmptySea();
		assertEquals("empty", empty.getShipType());
	}

	/**
	 * Test method to check if the setter method to set the row number for the ship's bow works properly.
	 */
	@Test
	void testSetBowRow() {
		
		/*Testing the setter method for the row number of the ship bow, across all ship subclasses.
    Creates a new battleship and checks if the row column of the ship bow is set properly with the method.*/
		Ship battleship = new Battleship();
		int rowBattleship = 0;
		battleship.setBowRow(rowBattleship);
		assertEquals(rowBattleship, battleship.getBowRow());
		
    //Creates a new cruiser and checks if the row column of the ship bow is set properly with the method.
		Ship cruiser = new Cruiser();
		int rowCruiser = 5;
		cruiser.setBowRow(rowCruiser);
		assertEquals(rowCruiser, cruiser.getBowRow());
		
    //Creates a new destroyer and checks if the row column of the ship bow is set properly with the method.
		Ship destroyer = new Destroyer();
		int rowDestroyer = 4;
		destroyer.setBowRow(rowDestroyer);
		assertEquals(rowDestroyer, destroyer.getBowRow());
		
    //Creates a new submarine and checks if the row column of the ship bow can be set properly.
		Ship submarine = new Submarine();
		int rowSubmarine = 8;
		submarine.setBowRow(rowSubmarine);
		assertEquals(rowSubmarine, submarine.getBowRow());
		
    //Creates a new empty sea and checks if the row column of the ship bow is set properly with the method.
		Ship empty = new EmptySea();
		int rowEmpty = 7;
		empty.setBowRow(rowEmpty);
		assertEquals(rowEmpty, empty.getBowRow());
	}

	/**
	 * Test method to check if the setter method to set the column number for the ship's bow works properly.
	 */
	@Test
	void testSetBowColumn() {
		
		/*Testing the setter method for the column number of the ship bow, across all ship subclasses.
    Creates a new battleship and checks if the row column of the ship bow is set properly with the method.*/
		Ship battleship = new Battleship();
		int columnBattleship = 4;
		battleship.setBowColumn(columnBattleship);
		assertEquals(columnBattleship, battleship.getBowColumn());
		
    //Creates a new cruiser and checks if the row column of the ship bow is set properly with the method.
		Ship cruiser = new Cruiser();
		int columnCruiser = 6;
		cruiser.setBowColumn(columnCruiser);
		assertEquals(columnCruiser, cruiser.getBowColumn());
		
    //Creates a new destroyer and checks if the row column of the ship bow is set properly with the method.
		Ship destroyer = new Destroyer();
		int columnDestroyer = 9;
		destroyer.setBowColumn(columnDestroyer);
		assertEquals(columnDestroyer, destroyer.getBowColumn());
		
    //Creates a new submarine and checks if the row column of the ship bow is set properly with the method.
		Ship submarine = new Submarine();
		int columnSubmarine= 9;
		submarine.setBowColumn(columnSubmarine);
		assertEquals(columnSubmarine, submarine.getBowColumn());
		
    //Creates a new empty sea and checks if the row column of the bow is set properly with the method.
		Ship empty = new EmptySea();
		int columnEmpty = 9;
		empty.setBowColumn(columnEmpty);
		assertEquals(columnEmpty, empty.getBowColumn());
	}

	/**
	 * Test method to check if the setter method for whether the ship is horizontal or vertical works properly.
	 */
	@Test
	void testSetHorizontal() {
		
		/*Testing the setter method for the horizontal boolean value of the ship, across four ship subclasses (does not matter for EmptySea).
		Checks the horizontal boolean setter method for a new battleship.*/
    Ship battleship = new Battleship();
		boolean horizontalBattleship = true;
		battleship.setHorizontal(horizontalBattleship);
		assertTrue(battleship.isHorizontal());
		
    //Checks the horizontal boolean setter method for a new cruiser.
		Ship cruiser = new Cruiser();
		boolean horizontalCruiser = true;
		cruiser.setHorizontal(horizontalCruiser);
		assertTrue(cruiser.isHorizontal());
		
    //Checks the horizontal boolean setter method for a new destroyer.
		Ship destroyer = new Destroyer();
		boolean horizontalDestroyer = false;
		cruiser.setHorizontal(horizontalDestroyer);
		assertFalse(destroyer.isHorizontal());
		
    //Checks the horizontal boolean setter method for a new submarine.
		Ship submarine = new Submarine();
		boolean horizontalSubmarine = false;
		cruiser.setHorizontal(horizontalSubmarine);
		assertFalse(submarine.isHorizontal());
	}

	/**
	 * Test method for the okToPlaceShipAt method. Will first check whether a ship can be placed in an empty ocean.
	 */
	@Test
	void testOkToPlaceShipAt() {
		
		//Test when other ships are not in the ocean, checks to see if a battleship can be placed in the ocean. would expect to return a true value.
		Ship battleship = new Battleship();
		int rowBattleship = 0;
		int columnBattleship = 4;
		boolean horizontalBattleship = true;
		boolean okBattleship = battleship.okToPlaceShipAt(rowBattleship, columnBattleship, horizontalBattleship, ocean);
		assertTrue(okBattleship, "OK to place ship here.");
				
		//Checks to see if a destroyer can be placed off the left edge of the ocean, would expect to return a false value.
		Ship destroyer = new Destroyer();
		int rowDestroyer = 8;
		int columnDestroyer = 0;
		boolean horizontalDestroyer = true;
		boolean okDestroyer = destroyer.okToPlaceShipAt(rowDestroyer, columnDestroyer, horizontalDestroyer, ocean);
		assertFalse(okDestroyer);
		
		//Checks to see if a destroyer can be placed off the top edge of the ocean, would expect to return a false value.
		Ship cruiser = new Cruiser();
		int rowCruiser = 0;
		int columnCruiser = 5;
		boolean horizontalCruiser = false;
		boolean okCruiser = cruiser.okToPlaceShipAt(rowCruiser, columnCruiser, horizontalCruiser, ocean);
		assertFalse(okCruiser);
		
		//Checks to see if a submarine can be placed off the ocean boundaries, would expect to return a false value.
		Ship submarine = new Submarine();
		int rowSubmarine = 15;
		int columnSubmarine = 15;
		boolean horizontalSubmarine = true;
		boolean okSubmarine = submarine.okToPlaceShipAt(rowSubmarine, columnSubmarine, horizontalSubmarine, ocean);
		assertFalse(okSubmarine);
	}
	
	/**
	 * Test method for the okToPlaceShipAt method. Will check whether a ship can be placed in the ocean after other ships have been placed.
	 */
	@Test
	void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {
		
		//Tests when other ships are in the ocean
		
		//Place first ship
		Battleship battleship1 = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		battleship1.placeShipAt(row, column, horizontal, ocean);

		//Test second ship
		Battleship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");
		
		//Checks to see if a new cruiser can be placed with the same bow location as the battleship, would expect to return a false value.
		Ship cruiser = new Cruiser();
		int rowCruiser = 0;
		int columnCruiser = 4;
		boolean horizontalCruiser = true;
		boolean okCruiser = cruiser.okToPlaceShipAt(rowCruiser, columnCruiser, horizontalCruiser, ocean);
		assertFalse(okCruiser);
		
		//Checks to see if a submarine can be placed directly adjacent to the right of the battleship above, would expect to return a false value.
		Ship submarine = new Submarine();
		int rowSubmarine = 0;
		int columnSubmarine = 5;
		boolean horizontalSubmarine = true;
		boolean okSubmarine = submarine.okToPlaceShipAt(rowSubmarine, columnSubmarine, horizontalSubmarine, ocean);
		assertFalse(okSubmarine);
		
		//Checks to see if a submarine can be placed two rows below the battleship above, would expect to return a true value.
		rowSubmarine = 2;
		columnSubmarine = 4;
		horizontalSubmarine = true;
		okSubmarine = submarine.okToPlaceShipAt(rowSubmarine, columnSubmarine, horizontalSubmarine, ocean);
		assertTrue(okSubmarine);
		
		//Checks to see if a submarine can be placed diagonally adjacent to the battleship above, would expect to return a true value.
		rowSubmarine = 1;
		columnSubmarine = 5;
		horizontalSubmarine = true;
		okSubmarine = submarine.okToPlaceShipAt(rowSubmarine, columnSubmarine, horizontalSubmarine, ocean);
		assertFalse(okSubmarine);	
	}
	
	/**
	 * Test method to check if the placeShipAt method works properly as intended.
	 */
	@Test
	void testPlaceShipAt() {
		
		//Tests the placement of a battleship.
		Ship battleship = new Battleship();
		int rowBattleship = 0;
		int columnBattleship = 4;
		boolean horizontalBattleship = true;
		battleship.placeShipAt(rowBattleship, columnBattleship, horizontalBattleship, ocean);
		assertEquals(rowBattleship, battleship.getBowRow());
		assertEquals(columnBattleship, battleship.getBowColumn());
		assertTrue(battleship.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals(battleship, ocean.getShipArray()[0][1]);
		
		//Tests the placement of a cruiser.
		Ship cruiser = new Cruiser();
		int rowCruiser = 5;
		int columnCruiser = 5;
		boolean horizontalCruiser = true;
		cruiser.placeShipAt(rowCruiser, columnCruiser, horizontalCruiser, ocean);
		assertEquals(rowCruiser, cruiser.getBowRow());
		assertEquals(columnCruiser, cruiser.getBowColumn());
		assertTrue(cruiser.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[5][2].getShipType());
		assertEquals(cruiser, ocean.getShipArray()[5][3]);
		
		//Tests the placement of a destroyer.
		Ship destroyer = new Destroyer();
		int rowDestroyer = 6;
		int columnDestroyer = 9;
		boolean horizontalDestroyer = false;
		destroyer.placeShipAt(rowDestroyer, columnDestroyer, horizontalDestroyer, ocean);
		assertEquals(rowDestroyer, destroyer.getBowRow());
		assertEquals(columnDestroyer, destroyer.getBowColumn());
		assertFalse(destroyer.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[4][9].getShipType());
		assertEquals(destroyer, ocean.getShipArray()[5][9]);
		
		//Tests the placement of a submarine.
		Ship submarine = new Submarine();
		int rowSubmarine = 9;
		int columnSubmarine = 9;
		boolean horizontalSubmarine = false;
		submarine.placeShipAt(rowSubmarine, columnSubmarine, horizontalSubmarine, ocean);
		assertEquals(rowSubmarine, submarine.getBowRow());
		assertEquals(columnSubmarine, submarine.getBowColumn());
		assertFalse(submarine.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[8][9].getShipType());
		assertEquals(submarine, ocean.getShipArray()[9][9]);
	}

	/**
	 * Test method to check if the shootAt method works properly as intended, i.e. if a ship and its hit array can register being hit.
	 */
	@Test
	void testShootAt() {
		
		//Tests the shootAt method for a battleship and tests the getter method to return the Hit array.
		Ship battleship = new Battleship();
		int rowBattleship = 0;
		int columnBattleship = 9;
		boolean horizontalBattleship = true;
		battleship.placeShipAt(rowBattleship, columnBattleship, horizontalBattleship, ocean);
		
		assertFalse(battleship.shootAt(1, 9));
		boolean[] hitArray0 = {false, false, false, false};
		assertArrayEquals(hitArray0, battleship.getHit());
		
		assertTrue(battleship.shootAt(0, 9));
		boolean[] hitArray1 = {true, false, false, false};
		assertArrayEquals(hitArray1, battleship.getHit());
		
		//Tests the shootAt method for a cruiser and tests the getter method to return the Hit array.
		Ship cruiser = new Cruiser();
		int rowCruiser = 5;
		int columnCruiser = 5;
		boolean horizontalCruiser = true;
		cruiser.placeShipAt(rowCruiser, columnCruiser, horizontalCruiser, ocean);
		
		assertTrue(cruiser.shootAt(5, 4));
		boolean[] hitArray2 = {false, true, false};
		assertArrayEquals(hitArray2, cruiser.getHit());
		
		//Tests the shootAt method for a destroyer and tests the getter method to return the Hit array.
		Ship destroyer = new Destroyer();
		int rowDestroyer = 8;
		int columnDestroyer = 2;
		boolean horizontalDestroyer = false;
		destroyer.placeShipAt(rowDestroyer, columnDestroyer, horizontalDestroyer, ocean);
		
		assertTrue(destroyer.shootAt(7, 2));
		boolean[] hitArray3 = {false, true};
		assertArrayEquals(hitArray3, destroyer.getHit());
		
		//Tests the shootAt method for a submarine and tests the getter method to return the Hit array.
		Ship submarine = new Submarine();
		int rowSubmarine = 9;
		int columnSubmarine = 9;
		boolean horizontalSubmarine = false;
		submarine.placeShipAt(rowSubmarine, columnSubmarine, horizontalSubmarine, ocean);
		
		assertTrue(submarine.shootAt(9, 9));
		boolean[] hitArray4 = {true};
		assertArrayEquals(hitArray4, submarine.getHit());
		
		//Tests the shootAt method for an EmptySea location, which is expected to always return a false value.
		Ship empty = new EmptySea();
		int rowEmpty = 9;
		int columnEmpty = 9;
		boolean horizontalEmpty = false;
		empty.placeShipAt(rowEmpty, columnEmpty, horizontalEmpty, ocean);
		
		assertFalse(empty.shootAt(9, 9));
	}
	
	/**
	 * Test method to check if the shotAtDisplay method works properly, e.g. if a ship can return true after being fired upon previously.
	 */
	@Test
	void testShotAtDisplay() {
		
    //Fires at a new submarine and tests if the method returns true to indicate it was previously fired upon.
		Ship submarine = new Submarine();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);

		assertTrue(submarine.shootAt(3, 3));
		assertTrue(submarine.shotAtDisplay(3, 3));
				
		//Fires at a new destroyer and tests if the method returns true to indicate it was previously fired upon.
		Ship destroyer = new Destroyer();
		int rowDestroyer = 8;
		int columnDestroyer = 3;
		boolean horizontalDestroyer = false;
		destroyer.placeShipAt(rowDestroyer, columnDestroyer, horizontalDestroyer, ocean);
		
		assertTrue(destroyer.shootAt(7, 3));
		assertTrue(destroyer.shotAtDisplay(7, 3));
		
		//Fires at a new cruiser and tests if the method returns true to indicate it was previously fired upon.
		Ship cruiser = new Cruiser();
		int rowCruiser = 5;
		int columnCruiser = 5;
		boolean horizontalCruiser = true;
		cruiser.placeShipAt(rowCruiser, columnCruiser, horizontalCruiser, ocean);
		
		assertTrue(cruiser.shootAt(5, 4));
		assertTrue(cruiser.shotAtDisplay(5, 4));
		
		//Fires at a new battleship and tests if the method returns true to indicate it was previously fired upon.
		Ship battleship = new Battleship();
		int rowBattleship = 0;
		int columnBattleship = 9;
		boolean horizontalBattleship = true;
		battleship.placeShipAt(rowBattleship, columnBattleship, horizontalBattleship, ocean);
		
		assertTrue(battleship.shootAt(0, 6));
		assertTrue(battleship.shotAtDisplay(0, 6));
		
		//Fires at a new empty sea and tests if the method returns true to indicate it was previously fired upon.
		Ship empty = new EmptySea();
		int rowEmpty = 8;
		int columnEmpty = 8;
		boolean horizontalEmpty = true;
		empty.placeShipAt(rowEmpty, columnEmpty, horizontalEmpty, ocean);
		
		assertFalse(empty.shootAt(8, 8));
		assertTrue(empty.shotAtDisplay(8, 8));
		
	}
	
	/**
	 * Test method to check if a ship is sunk after all the sections of the ship have been hit.
	 */
	@Test
	void testIsSunk() {
		
		/*Tests the getter method to check the isSunk boolean for all ship subclasses (and would expect the EmptySea location to always return false).
		Places a new submarine, then shoots the ship and checks if it was sunk after being hit.*/
    Ship submarine = new Submarine();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(submarine.isSunk());
		assertFalse(submarine.shootAt(5, 2));
		assertFalse(submarine.isSunk());
		
		assertTrue(submarine.shootAt(3, 3));
		assertTrue(submarine.isSunk());
				
		//Places a new destroyer, then shoots the ship and checks if it was sunk after being hit twice.
		Ship destroyer = new Destroyer();
		int rowDestroyer = 8;
		int columnDestroyer = 2;
		boolean horizontalDestroyer = false;
		destroyer.placeShipAt(rowDestroyer, columnDestroyer, horizontalDestroyer, ocean);
		
		assertTrue(destroyer.shootAt(7, 2));
		assertTrue(destroyer.shootAt(8, 2));
		assertTrue(destroyer.isSunk());
		
		//Places a new cruiser, then shoots the ship and checks if it was sunk after being hit three times.
		Ship cruiser = new Cruiser();
		int rowCruiser = 5;
		int columnCruiser = 5;
		boolean horizontalCruiser = true;
		cruiser.placeShipAt(rowCruiser, columnCruiser, horizontalCruiser, ocean);
		
		assertTrue(cruiser.shootAt(5, 3));
		assertTrue(cruiser.shootAt(5, 4));
		assertTrue(cruiser.shootAt(5, 5));
		assertTrue(cruiser.isSunk());
		
		//Places a new battleship, then shoots the ship and checks if it was sunk after being hit four times.
		Ship battleship = new Battleship();
		int rowBattleship = 0;
		int columnBattleship = 9;
		boolean horizontalBattleship = true;
		battleship.placeShipAt(rowBattleship, columnBattleship, horizontalBattleship, ocean);
		
		assertTrue(battleship.shootAt(0, 9));
		assertTrue(battleship.shootAt(0, 8));
		assertTrue(battleship.shootAt(0, 7));
		assertTrue(battleship.shootAt(0, 6));
		assertTrue(battleship.isSunk());
		
		//Places a empty sea and checks if it returns false when shot at, and also checks that the isSunk method always returns false.
		Ship empty = new EmptySea();
		int rowEmpty = 3;
		int columnEmpty = 3;
		boolean horizontalEmpty = true;
		empty.placeShipAt(rowEmpty, columnEmpty, horizontalEmpty, ocean);
		
		assertFalse(empty.shootAt(3, 3));
		assertFalse(empty.isSunk());
	}

	/**
	 * Test method to check if the toString method for each subclass of ship works properly.
	 */
	@Test
	void testToString() {
		
		/*Tests the toString method across all ship subclasses, only for ships that have been previously shot at (as instructed).
    Checks the toString method for a new battleship, before and after it is hit.*/
		Ship battleship = new Battleship();
		assertEquals("x", battleship.toString());
		
		int rowBattleship = 9;
		int columnBattleship = 1;
		boolean horizontalBattleship = false;
		battleship.placeShipAt(rowBattleship, columnBattleship, horizontalBattleship, ocean);
		battleship.shootAt(9, 1);
		assertEquals("x", battleship.toString());
		
		battleship.shootAt(8, 1);
		battleship.shootAt(7, 1);
		battleship.shootAt(6, 1);
		assertEquals("s", battleship.toString());
		
		//Checks the toString method for a new submarine, before and after it is hit.
		Ship submarine = new Submarine();
		int rowSubmarine = 3;
		int columnSubmarine = 3;
		boolean horizontalSubmarine = true;
		submarine.placeShipAt(rowSubmarine, columnSubmarine, horizontalSubmarine, ocean);
		assertEquals("x", submarine.toString());
		
		assertTrue(submarine.shootAt(3, 3));
		assertEquals("s", submarine.toString());
		
		//Checks the toString method for a new cruiser, before and after it is hit.
		Ship cruiser = new Cruiser();
		int rowCruiser = 5;
		int columnCruiser = 5;
		boolean horizontalCruiser = true;
		cruiser.placeShipAt(rowCruiser, columnCruiser, horizontalCruiser, ocean);
		assertEquals("x", cruiser.toString());
		
		assertTrue(cruiser.shootAt(5, 3));
		assertTrue(cruiser.shootAt(5, 4));
		assertTrue(cruiser.shootAt(5, 5));
		assertEquals("s", cruiser.toString());
		
		//Checks the toString method for a new destroyer, before and after it is hit and sunk.
		Ship destroyer = new Destroyer();
		int rowDestroyer = 8;
		int columnDestroyer = 2;
		boolean horizontalDestroyer = false;
		destroyer.placeShipAt(rowDestroyer, columnDestroyer, horizontalDestroyer, ocean);
		assertEquals("x", destroyer.toString());
		
		assertTrue(destroyer.shootAt(7, 2));
		assertTrue(destroyer.shootAt(8, 2));
		assertEquals("s", destroyer.toString());
		
		//Checks the toString method for a new empty sea, after it has been fired upon.
		Ship empty = new EmptySea();
		int rowEmpty = 7;
		int columnEmpty = 7;
		boolean horizontalEmpty = true;
		empty.placeShipAt(rowEmpty, columnEmpty, horizontalEmpty, ocean);
				
		assertFalse(empty.shootAt(7, 7));
		assertEquals("—", empty.toString());
	}

}
