/* Name: Zan En Zhang
Penn ID: 65095743
Statement Of Work: I, Zan En Zhang, worked on this submission alone and without help. */

package battleship;

//Imports the scanner class
import java.util.Scanner;

/**
 * The main class that contains the main method and starts by creating an instance of Ocean.
 * One-player game of battleship! Battleship is a classic game for the venturing human being!
 * The computer will place the ships and the human player will try to sink them.
 * Remember that ships cannot be directly adjacent to each other, including diagonally. 
 */
public class BattleshipGame {

	/**
	 * Variable to track if the game is over (all 10 ships have been sunk), does not need to be instantiated by a class hence is static.
	 */
	private static boolean gameOver = false;
  
  /**
	 * Variable to track if the player wants to play again, does not need to be instantiated by a class hence is static.
	 */
	private static boolean playerReplayEnd = false;
	
	/**
	 * Initializes the game of battleship and runs until all ships are sunk.
	 * Shows and updates the main ocean display for shots fired, hits, and ships sunk.
	 */
	public static void main(String args[]) {
		
	    //Creates a new scanner and assigns to a variable, will be used to take user input later.
	    Scanner scanner = new Scanner(System.in);
	    
	    while (!playerReplayEnd){
	      
		    //Prints a welcome message.
			System.out.println("");
			System.out.println("Welcome to Battleship!");
			System.out.println("The computer will place 10 ships for you to sink.");
			System.out.println("The game is over when you sink all 10 ship. Have fun!");
			System.out.println("");
	      
			//Creates a new instance of ocean.
			Ocean ocean = new Ocean(); 
			
			//PLaces the 10 ships randomly that follows the given guidelines.
			ocean.placeAllShipsRandomly(); 
			
			//Prints the ocean, will only display an "empty" ocean at the start.
			ocean.print(); 
			
			//If the game is still ongoing, e.g. while the game is not over, the user will continue to shoot at coordinates.
			while (!gameOver) {
		
		        //Displays totals for shots fired, how many hits were made, and how many ships were sunk.
		        System.out.println("");
		        System.out.println("Shots Fired: " + ocean.getShotsFired());
		        System.out.println("Hit Count: " + ocean.getHitCount());
		        System.out.println("Ships Sunk: " + ocean.getShipsSunk());
		        System.out.println("");
		
		        //Decided to split up the row and column inputs for preference and to wrap in try and catch blocks in the methods.
		        int row = getRowInput(scanner);
		        int column = getColumnInput(scanner);
		        System.out.println("");
		
		        //Will shoot at the coordinates provided by the user, if there is a physical ship at the location, a hit will be registered
		        ocean.shootAt(row, column);
		
		        /*Shows the new and updated ocean, with a "-" to show an empty ocean that was fired upon, a "x" to show a hit on a ship
		        and a "s" to show a sunk ship as given in the instructions.*/
		        ocean.print();
		
		        //Checks if the game is over, i.e. if all 10 ships have been sunk. If so, the while loop exits and the game ends.
		        gameOver = ocean.isGameOver();
			} 
		      
			//Checks if the player wants to play again.
			boolean receivedResponse = false;
			String playerResponse = "";
	      
			//Loop to ask player for a response until the input meets certain conditions to exit.
			while (! receivedResponse){
	      
		        System.out.println("");
		        System.out.println("Would you like to play again?");
		        playerResponse = scanner.next();
	        
	        //If the response is empty, the loop while will continue. Otherwise, if the response starts with a 'Y', or a 'y', or 'N' or 'n', the loop will exit.
		        if (! playerResponse.isEmpty()) {
		          
		            if (Character.toUpperCase(playerResponse.charAt(0)) == 'Y' || Character.toUpperCase(playerResponse.charAt(0)) == 'N'){
		
		            	receivedResponse = true;
		            }
		 
		        } else {
		
		          receivedResponse = false;
		        }
			}
		        
			//If the player wants to play again, i.e. entered in a 'Y' or 'y', a new game will start.
			if (Character.toUpperCase(playerResponse.charAt(0)) == 'Y'){
			    
				gameOver = false;
				playerReplayEnd = false;
			  
			//Otherwise, if the player entered in a 'N' or 'n', the game ends.
			} else if (Character.toUpperCase(playerResponse.charAt(0)) == 'N') {
			    
				System.out.println("");
				System.out.println("Thanks for playing!");
				playerReplayEnd = true;
			}     
		}
	    						
		//Closes the scanner.
		scanner.close();
	}
	
  
	/**
	 * Method to get user input for the row.
	 * @param scanner to read the integer that is inputed from the user.
	 * @return the row integer.
	 */
	static int getRowInput(Scanner scanner) {
		
		boolean rowResponseError = false; //To track if the input is valid.
		int rowNumber = 0; //To store the row number.
		
		do {
			
		    try {
		    	
		    	System.out.println("Enter the row: ");
	
		    	//Gets the next integer and stores that as the row number.
		    	rowNumber = scanner.nextInt(); 

		    	  //If the given row number is outside of the ocean limits, the while loop continues.
		        if (rowNumber < 0 || rowNumber >= Ocean.OCEAN_ROWS) {
		        	
		        	System.out.println("That is beyond the boundaries of the ocean! Please try again!");
		        	System.out.println("");
		        	scanner.nextLine();
		            
		        } else {
		        	
		        	System.out.println("");
		        	
		        	//Otherwise if no exceptions are thrown, the while loop exits.
		        	rowResponseError = true; 
		        }
		   		    
		    //For other exceptions the loop continues on.
		    } catch (Exception e) {
		    	
		        System.out.println("Invalid row number! Please try again!");
		        System.out.println("");
		        scanner.nextLine();
		    }
		    
		} while (!rowResponseError);
		
		//When the while loop exists, the row number is returned.
		return rowNumber;
	}
	
	/**
	 * Method to get user input for the column.
	 * @param scanner to read the integer that is inputed from the user.
	 * @return the column integer.
	 */
	static int getColumnInput(Scanner scanner) {
	
		//To track if the input is valid.
		boolean columnResponseError = false; 
		//To store the column value.
		int columnNumber = 0;
		
		do {
			
		    try {
		    	
		    	System.out.println("Enter the column: ");
	
		    	//Gets the next integer and stores that as the column number.
		    	columnNumber = scanner.nextInt(); 

		    	  //If the given column number is outside of the ocean limits, the while loop continues.
		        if (columnNumber < 0 || columnNumber >= Ocean.OCEAN_COLUMNS) {
		        	
		        	System.out.println("");
		        	System.out.println("That is beyond the boundaries of the ocean! Please try again!");
		        	System.out.println("");
		        	scanner.nextLine();
		            
		        } else {
		        
		        	//Otherwise if no exceptions are thrown, the while loop exits.
		        	columnResponseError = true; 
		        }
		   		    
		    //For other exceptions including if the column input is out of the ocean range, the loop continues on.
		    } catch (Exception e) {
		    	
		    	System.out.println("");
		        System.out.println("Invalid column number! Please try again!");
		        System.out.println("");
		        scanner.nextLine();
		    }
		    
		} while (!columnResponseError);
		
		//When the while loop exists, the column number is returned.
		return columnNumber;
	}
}
