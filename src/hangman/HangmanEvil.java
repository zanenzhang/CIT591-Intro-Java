package hangman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

/**
 * Class representing the evil version that inherits methods from the Hangman class. Will also implement the letterGuessCheck method that was
 * abstract in the parent class.
 */
public class HangmanEvil extends Hangman {

	/**
	 * Boolean switch to determine if the word family process is confined to words that have the same number of letters.
	 */
	private boolean lockedWordLength;
	
	/**
	 * Creates a list of booleans to track where certain letters should be positioned in a string of text.
	 */
	List<Boolean> boolSequence = new ArrayList<>();
	
	/**
	 * Creates a hashmap with a lists of booleans as the key and the number of times each list appears as the value.
	 */
	private HashMap< List<Boolean>, Integer> sequenceCount = new HashMap<List<Boolean>, Integer>();	
	
	/**
	 * Constructor method to create an instance of the evil version of the Hangman game with the given word. Initializes the variable that tracks whether
	 * the size of each word in the word family has been locked in.
	 * @param word that will be used as the Hangman word for this game of evil Hangman.
	 */
	public HangmanEvil(String word) {
		super(word);
		this.lockedWordLength = false;
	}

	/**
	 * This method will check whether the user's given character matches any characters in the Hangman word. This method will also include
	 * the functionality by which the computer will swap in different words that are part of the word family.
	 * @param letter to represent the user's given letter, to be used to check if it matches any characters in the Hangman word.
	 */
	@Override
	public int letterGuessCheck(char letter) {
		
		//If not already, the computer will reduce the word family to those words that have the same length as the initial Hangman word.
		if (lockedWordLength == false) {
			
			setWordFamilyByLength(this.getWordLength(),"src/dictionary/filtered_words.txt", "src/dictionary/evil_hangman_wordlist.txt");
		}
		
		//Will then further reduce the word family according to the provided letter from the user.
		setWordFamilyByLetter(letter, "src/dictionary/evil_hangman_wordlist.txt", "src/dictionary/evil_hangman_wordlist.txt");
		
		//Will then check to see if the user's given letter matches any characters in the word family.
		int checkResult = this.letterMatching(letter);
		
		//Returns in the form of an integer, whether the guess was correct, if the letter was already guessed, or if it missed.
		return checkResult;	
	}
	
	/**
	 * This method is to filter the list of words in a file to form a new word family with the same length (or number of letters).
	 * @param wordLength that represents the length of the word family, which should equal the number of letters in the current Hangman word.
	 * @param filenameRead or the file of text with the initial list of words that will be filtered down.
	 * @param filenameWrite of the new file that will hold the word family with each word having the same length.
	 */
	public void setWordFamilyByLength(int wordLength, String filenameRead, String filenameWrite) {
		
		//Reads the lines of text from the given file.
		Hangman.setLines(dictionary.MyFileReader.wordsSameLength(wordLength, filenameRead));
		
		//Writes the new word family to the provided filename.
		dictionary.MyFileWriter.writeToFile(Hangman.getLines(), filenameWrite);
		
		this.lockedWordLength = true;
	}
	
	/**
	 * This method reduces the word family depending on which letter is guessed by the human player.
	 * @param letter representing the letter that is guessed by the human player.
	 * @param filenameRead or the file of text that will be read from.
	 * @param filenameWrite or the file of text that will be written to.
	 * @return an integer only for testing purposes, denoting the number of words that match the specific 
	 * letter sequence according to the user's guess.
	 */
	public int setWordFamilyByLetter(char letter, String filenameRead, String filenameWrite) {
		
		//Imports the random function, will be used to randomly select a word from the new word family.
		Random random = new Random();
		
		//The hashmap that contains the boolean sequences as keys and the number of each respective appearance as values.
		sequenceCount = dictionary.MyFileReader.sequenceMapCounter(letter, this.getWordLength(), filenameRead);
		
		/*Will loop through the hashmap to determine which boolean sequence has the highest value, which represents the order of
		letters that is the most frequent.*/
		int maxSequenceCount = 0;
		boolSequence = null;
		
		for (Entry<List<Boolean>, Integer> entry : sequenceCount.entrySet()) {
						
			for (int i = 0; i < this.getWordLength(); i++) {
			
				//Will compare an entry value with the previous high value to determine which is the final value to be used.
				if (entry.getValue() > maxSequenceCount) {
					
					maxSequenceCount = entry.getValue();
					boolSequence = entry.getKey();
				}
			}
		}
		
		//Will reduce the word family to fit the boolean sequence with the highest number of words.
		Hangman.setLines(dictionary.MyFileReader.filterWordsBySequence(letter, boolSequence, filenameRead));
		
		//Will overwrite or update the existing word family with the new list of words.
		dictionary.MyFileWriter.writeToFile(Hangman.getLines(), filenameWrite);
		
		//If there is at least one word in the word family, the computer will randomly select a word from the word family to replace the current Hangman word.
		int numberOfLines = Hangman.getLines().size();
		
		if (numberOfLines >= 1) {
			
			int randWordNumber = random.nextInt(numberOfLines);
			
			String selectedWord = Hangman.getLines().get(randWordNumber);
			
			//Will replace the current Hangman word with a new word if there are words available, and will update the list of characters representing the Hangman word.
			setWordLettersList(selectedWord, true);
			
			setHangmanWord(selectedWord);
		}
		
		//Returns the maximum number of sequences solely for testing purposes.
		return maxSequenceCount;
	}
}
