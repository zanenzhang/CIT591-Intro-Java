package hangman;

import java.util.ArrayList;
import java.util.List;

/**
 * The abstract class representing a game of Hangman and describes the methods available to both the original
 * game of Hangman and the evil version of Hangman.
 */
public abstract class Hangman {

	/**
	 * Arraylist to hold strings of text that are read in from a file or to be written into a file.
	 */
	private static ArrayList<String> lines = new ArrayList<>();
	
	/**
	 * A list of boolean values to track which letters of the Hangman word have been guessed correctly and should be unveiled (true) 
	 * or have not been guessed yet and still remain to be unveiled (false).
	 */
	protected List<Boolean> wordLettersMatchedBools = new ArrayList<>();
	
	/**
	 * A list of characters from the Hangman word.
	 */
	protected List<Character> wordLetters = new ArrayList<>();
	
	/**
	 * A string of the Hangman word.
	 */
	private String hangmanWord;
	
	/**
	 * An integer variable to store the value of a word's length, or how many letters the word contains.	
	 */
	private int wordLength;
	
	/**
	 * An integer variable to track the total number of guesses from the human player.
	 */
	private int guessAttempts;
	
	/**
	 * An arraylist of characters to track the letters that were incorrectly guessed by the human player.
	 */
	private ArrayList<Character> incorrectGuesses;
	
	/**
	 * An arraylist of characters to track all the characters that were guessed by the human player.
	 */
	private ArrayList<Character> totalGuesses;
	
	/**
	 * A boolean value to track whether the Hangman game being played is in evil mode or not.
	 */
	private boolean gameModeEvil;
	
	/**
	 * Constructor method to create a new instance of the Hangman game. Initializes variables in order to play the game, including the word
	 * length, the number of total guesses, which letters were incorrectly guessed, all the letters that were already guessed, and also sets the 
	 * arraylists for characters in the Hangman word and to track which letters of the Hangman word have been guessed correctly.
	 * @param word representing the Hangman word that the human player will attempt to guess.
	 */
	public Hangman(String word) {
		
		//Initializes variables needed for the game.
		setHangmanWord(word);
		this.wordLength = word.length();
		this.guessAttempts = 0;
		this.incorrectGuesses = new ArrayList<Character>();
		this.totalGuesses = new ArrayList<Character>();
				
		setWordLettersList(word, false);
		setWordLettersMatchedBools();
	}
		
	/**
	 * Method to update the list of incorrect guesses with the provided character in the declaration.
	 * @param letter from the user representing an incorrect guess to be added to the arraylist of characters.
	 */
	public void addToIncorrectGuesses(char letter) {
		
		this.incorrectGuesses.add(letter);	
	}
	
	/**
	 * Method to update the list of total guessed characters with the provided character in the declaration.
	 * @param letter from the user representing the letter guessed to be added to the arraylist of characters.
	 */
	public void addToTotalGuesses(char letter) {
		
		this.totalGuesses.add(letter);
	}
	
	/**
	 * Getter method for the length of the Hangman word.
	 * @return integer representing the number of letters in the Hangman word.
	 */
	public int getWordLength() {
		
		return this.wordLength;
	}
	
	/**
	 * Setter method to track which version of Hangman is being played, original or evil.
	 * @param bool representing whether the evil version or original version of Hangman is being played.
	 */
	public void setGameMode(boolean bool) {
		
		this.gameModeEvil = bool;
	}
	
	/**
	 * Getter method that returns which version of Hangman is being played.
	 * @return boolean value indicating if the evil version is being played.
	 */
	public boolean getGameMode() {
		
		return this.gameModeEvil;
	}

	/**
	 * Setter method for the arraylist of lines that are read from files or to be written to files.
	 * @param text representing the arraylist of strings.
	 */
	public static void setLines(ArrayList<String> text) {
				
		Hangman.lines = text;
	}
	
	/**
	 * Getter method for the arraylist of lines that are read from files or to be written to files. 
	 * @return the arraylist of lines.
	 */
	public static ArrayList<String> getLines() {
			
		return Hangman.lines;
	}
	
	/**
	 * Setter method for the Hangman string.
	 * @param word representing the Hangman word in string format.
	 */
	public void setHangmanWord(String word) {
		
		this.hangmanWord = word;
	}
	
	/**
	 * Getter method for the Hangman string.
	 * @return the Hangman word as a string.
	 */
	public String getHangmanWord() {
		
		return this.hangmanWord;
	}
	
	/**
	 * Getter method for the arraylist of characters that were guessed by the user throughout the game thus far.
	 * @return arraylist of characters representing all the characters that were guessed thus far by the user.
	 */
	public ArrayList<Character> getTotalGuesses() {
		
		return this.totalGuesses;
	}
	
	/**
	 * Method to check whether a letter was already guessed by the human player.
	 * @param letter representing a guess from the human player.
	 * @return boolean to indicate whether the character letter was already guessed or not.
	 */
	public boolean checkTotalGuesses(char letter) {
		
		boolean alreadyGuessed = false;
		
		for (int i = 0; i < this.totalGuesses.size(); i++) {
			
			if (totalGuesses.get(i) == letter) {
				
				alreadyGuessed = true;
			}
		}
		
		if (alreadyGuessed == false) {
			
			return false;
			
		} else {
			
			return true;
		}
	}
	
	/**
	 * Getter method for the arraylist of characters representing letters that were missed guesses.
	 * @return arraylist of characters representing the letters that were incorrectly guessed by the human player.
	 */
	public ArrayList<Character> getIncorrectGuesses() {
		
		return this.incorrectGuesses;
	}
	
	/**
	 * Getter method for the number of guess attempts by the human player.
	 * @return integer representing how many times the human player has guessed so far.
	 */
	public int getGuessAttemps() {
		
		return this.guessAttempts;
	}
	
	/**
	 * Method to increment the number of guesses each time the human player enters a new guess (letter that was not already guessed).
	 */
	public void incrementGuessAttempts() {
		
		this.guessAttempts += 1;
	}
	
	/**
	 * Setter method for the list of characters for the Hangman word.
	 * @param word representing the Hangman word.
	 * @param replace boolean value indicating whether the method should add or set values into the list.
	 */
	public void setWordLettersList(String word, boolean replace) {
		
		for (int i = 0; i < this.wordLength; i++ ) {
			
			char letterToAdd = word.charAt(i);
			
			if (replace == true) {
				
				wordLetters.set(i, letterToAdd);
			
			} else {
				
				wordLetters.add(i, letterToAdd);
			}
		}
	}
	
	
	/**
	 * Getter method for the list of characters for the Hangman word.
	 * @return list of characters for the Hangman word.
	 */
	public List<Character> getWordLettersList() {
		
		return this.wordLetters;
	}

	
	/**
	 * Setter method to first place false values into the list of booleans to indicate that the user has not correctly guessed any letter positions.
	 */
	public void setWordLettersMatchedBools() {
		
		for (int i = 0; i < this.wordLength; i++ ) {
			
			wordLettersMatchedBools.add(i, false);
		}	
	}
	
	/**
	 * Getter method for the list of booleans representing which letters of the Hangman word were guessed correctly.
	 * @return list of booleans with true indicating that the letter location was guessed correctly by the user, and false otherwise.
	 */
	public List<Boolean> getWordLettersMatchedBools() {
		
		return this.wordLettersMatchedBools;		
	}
	
	/**
	 * Abstract method to check whether the human player's character guess is in the Hangman word.
	 * @param letter representing the human player's character guess.
	 * @return integer representing whether the character was already guessed, a correct guess, or an incorrect guess.
	 */
	public abstract int letterGuessCheck(char letter);
	
	/**
	 * Method to check if a given letter matches any letters in the Hangman word, or if it was already guessed by the user. Will be called by the subclass, 
	 * with a variation in the evil version.
	 * @param letter representing the human player's guess, which will be checked against letters in the Hangman word to see if there is a match or not.
	 * @return integer representing whether the guess was correct and there was a match, or if the guess is incorrect, or if the letter was already guessed.
	 */
	public int letterMatching(char letter) {
		
		//Boolean values to track if the guess is correct or if it was already guessed.
		boolean letterFlag = false;
		boolean alreadyGuessed = false;
		
		//Checks to see if the human player's character guess is in the arraylist of characters that were already guessed.
		alreadyGuessed = this.checkTotalGuesses(letter);
		
		//If the guessed letter matches a character in the Hangman word, the guess is marked as correct and the list of booleans is updated to display the character(s).
		for (int i = 0; i < this.getWordLength(); i++ ) {			
			
			if (letter == getWordLettersList().get(i)) {
				
				this.wordLettersMatchedBools.set(i, true);
				
				letterFlag = true;
			}
		}
		
		//If the character was already guessed, a value of 1 will be returned and the human player will be asked for input again.
		if (alreadyGuessed == true)  {
			
			return 1;
			
		//If the guess was incorrect, the guess attempts is updated along with the list of characters that were incorrectly guessed, returns a value of 2.
		} else if (letterFlag == false) {
			
			this.incrementGuessAttempts();
			this.addToIncorrectGuesses(letter);
			
			//Adds the character guess to the arraylist of total guesses.
			this.addToTotalGuesses(letter);
			
			return 2;
		
		} else {
			
			//Adds the character guess to the arraylist of total guesses, returns a value of 0.
			this.addToTotalGuesses(letter);
			
			//Increments the total number of guess attempts.
			this.incrementGuessAttempts();
			
			return 0;
		}
	}
	
	/**
	 * This method checks to see if the conditions have been met to end the game, either the game continues on, or the human player has lost, or the
	 * human player has won.
	 * @return boolean value representing if the game is over.
	 */
	public boolean isGameOver() {
		
		int matchCounter = 0;
		
		//Checks to see how many of the letters in the Hangman word were guessed correctly.
		for (int i = 0; i < this.wordLength; i++ ) {
			
			if (wordLettersMatchedBools.get(i) == true) {
				
				matchCounter += 1;
			}
		}
		
		//If all letters in the Hangman word have been guessed correctly, the game is over and the human player wins.
		if (matchCounter == this.wordLength) {
			
			System.out.print("The word was: " + this.hangmanWord);
			System.out.println("");
			System.out.println("Congrats, you got all the letters! You win the game!");
			System.out.println("");
			
			//To let the human player know which version of Hangman they were playing.
			if (this.getGameMode() == true) {
				
				System.out.println("You were playing evil Hangman!");
				System.out.println("");
				
			} else {
				
				System.out.println("You were playing normal Hangman!");
				System.out.println("");
			}
			
			return true;
		
		//If the human player has guessed 6 times incorrectly, the game is over and the human player loses.	
		} else if (this.incorrectGuesses.size() == 6) {
			
			System.out.println("You reached the limit of " + this.incorrectGuesses.size() + " incorrect guesses.");
			System.out.print("The word was: " + this.hangmanWord);
			System.out.println("");
			System.out.println("Sorry, you lose the game!");
			System.out.println("");
			
			if (this.getGameMode() == true) {
				
				System.out.println("You were playing evil Hangman!");
				System.out.println("");
				
			} else {
				
				System.out.println("You were playing normal Hangman!");
				System.out.println("");
			}
			
			return true;
		
		//Otherwise, the game continues on	
		} else {
			
			return false;
		}
	}
	
	/**
	 * Method to print out the Hangman word display showing which letters have been guessed correctly and 
	 * which letters are remaining to be guessed.
	 */
	public void print() {
		
		//Will loop through the list of booleans for the Hangman word to check which letters have been guessed correctly.
		for (int i = 0; i < this.wordLength; i++ ) {
			
			if (wordLettersMatchedBools.get(i) == false) {
				
				System.out.print("_ ");
			
			} else {
				
				System.out.print(getWordLettersList().get(i) + " ");
			}
		}
		
		System.out.print("\n");
	}
}
