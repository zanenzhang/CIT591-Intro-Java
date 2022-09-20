package hangman;


import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Class that contains the main method and starts by creating a new game of Hangman.
 * The computer will randomly select the version of Hangman, either original or the evil version.
 * The human player will enter in letters to guess the word, with a total of six incorrect guesses
 * allowed before he or she loses the game. In the evil version, the computer can swap in words
 * belonging in the same word family, however, the human player will not know which version is being
 * played until the game is over. Best of luck and have fun!
 */
public class HangmanGame {

	/**
	 * Integer variable to track the number of lines in a text file (or the number of words with one word per line).
	 */
	private static int numberOfLines;
	
	/**
	 * Boolean variable to track if the game is still ongoing (true) or not (false).
	 */
	private static boolean gameOngoing = true;
	
	/**
	 * Boolean variable to track whether the game is still getting user from a human player or not.
	 */
	private static boolean gettingUserInput = true;
	
	/**
	 * Initializes the game of Hangman and ends when the human player loses (six incorrect guesses) or wins (guesses all the letters in the hidden word).
	 * @param args standard for the main method.
	 */
	public static void main(String[] args) {

		//Initializes random to be used by the computer in selecting a word.
		Random random = new Random();
		
		/*Filters the file of words according to the conditions in the assignment instructions. For example, no upper case letters,
		no abbreviations, no apostrophes, no hyphens, no compound words, and no digits.*/
		Hangman.setLines(dictionary.MyFileReader.filterLines("src/dictionary/words.txt"));
		dictionary.MyFileWriter.writeToFile(Hangman.getLines(), "src/dictionary/filtered_words.txt");
	
		//Gets the number of words in the new filtered list of words.
		numberOfLines = Hangman.getLines().size();
	
		//Randomly selects a word from the filtered list.
		int randWordNumber = random.nextInt(numberOfLines);
		String selectedWord = Hangman.getLines().get(randWordNumber);
		
		//Begins the game with a welcome message.
		System.out.println("Welcome to Hangman!");
		System.out.println("You are allowed 6 incorrect guesses. Good luck!");
		System.out.println("");
		
		//Computer will randomly select between original Hangman and evil Hangman.
		int evilDecision = random.nextInt(2);
		
		//Creates a new game of Hangman with the determined version.
		Hangman hangman;
		
		if (evilDecision == 1) {
			
			hangman = new HangmanEvil(selectedWord);
			hangman.setGameMode(true);
			
		} else {
			
			hangman = new HangmanOriginal(selectedWord);
			hangman.setGameMode(false);			
		}
		
		//Creates a scanner to take input in from the human player.
		Scanner scanner = new Scanner(System.in);
		
		//If the game is ongoing, the Hangman display will print and the human player will guess its letters.
		while (gameOngoing) {
						
			hangman.print();
			
			//If there are prior incorrect guesses, the display will show the letters that the human player has incorrectly guessed.
			ArrayList<Character> incorrectGuesses = hangman.getIncorrectGuesses();
			
			if (incorrectGuesses.size() > 0) {
				
				System.out.println("Incorrect guesses: " + incorrectGuesses);
			} 
			
			//Displays the total number of guesses from the human player.
			System.out.println("Total guesses: " + hangman.getGuessAttemps());
			System.out.println("Guess a letter!");
			
			//If the user guess is not a lower case letter, the game will keep asking the human player for input until the input is acceptable.
			char userGuess = 0;
			gettingUserInput = true;
			
			while (gettingUserInput) {
				
				userGuess = takeUserInput(scanner);
				
				if (Character.isLowerCase(userGuess)) {
					
					gettingUserInput = false;
					
				} else {
					
					gettingUserInput = true;
				}
			}
			
			//Checks to see if the guessed letter is in the Hangman word.
			int guessCheck = hangman.letterGuessCheck(userGuess);
			
			System.out.println("");
			
			//Displays a message if the letter is in the Hangman word.
			if (guessCheck == 0) {
				
				System.out.println("Awesome! Great guess, the word has your letter.");
				System.out.println("");
				
			//Displays a message if the letter was already guessed.	
			} else if (guessCheck == 1) {
				
				System.out.println("You already guessed that letter!");
				System.out.println("");
			
			//Displays a message if the letter is not in the Hangman word.
			} else {
				
				System.out.println("Unfortunately, the word does not contain your letter.");
				System.out.println("");
				
			}
			
			/*Checks if any of the conditions have been met to end the game, for example if the human player
			has guessed all the letters to win the game or has incorrectly guessed 6 times to lose the game.*/
			if (hangman.isGameOver() == true) {
				
				gameOngoing = false;
				
			}
		}
		
		//Closes the scanner.
		scanner.close();
	}

	/**
	 * Method to take input from the human player.
	 * @param scanner to scan for keyboard input.
	 * @return character representing the human player's guessed letter.
	 */
	private static char takeUserInput(Scanner scanner) {
		
		//Placeholder for the human player's input.
		char userLetter = 0;
		
		//The human player is required enter a lower case letter as a guess, otherwise the game will continue to ask for input.
		while (gettingUserInput) {
		
			try {
				
				String userInput = scanner.nextLine();
				
				if (userInput != "") {
					
					userLetter = userInput.charAt(0);
									
				} else {
					
					System.out.println("Please enter a valid letter! (No capital letters or numbers).");
				}
				
			} catch (Exception e) {
		    	
		        System.out.println("Please enter a valid letter! (No capital letters or numbers).");
		    
			} finally {
		    	
				//If the input is a lower case letter, the while loop will exit.
				if (Character.isLowerCase(userLetter) == true) {
					
					gettingUserInput = false;
				}
		    }
		}
		
		//Returns the human player's guess.
		return userLetter;
	}
}
