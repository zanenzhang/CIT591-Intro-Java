package hangman;

/**
 * Class representing the original game of Hangman. The computer does not get to utilize word families like in the evil version.
 */
public class HangmanOriginal extends Hangman {

	/**
	 * Constructor method to create a new game of original Hangman using its parent class.
	 * @param word representing the Hangman word that the user will try to guess.
	 */
	public HangmanOriginal(String word) {
		super(word);
	}

	/**
	 * Implements the abstract method in its parent class to compare the human player's given letter input against characters in the Hangman word.
	 * @param letter representing the character guess provided by the human player.
	 */
	@Override
	public int letterGuessCheck(char letter) {
		
		//Simple call since there are no special variations in the original game compared to the evil version.
		int checkResult = this.letterMatching(letter);
			
		return checkResult;
	}		
}	
	
