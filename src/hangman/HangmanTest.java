package hangman;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * JUnit test class for the methods in the Hangman class.
 */
class HangmanTest {
	
	/**
	 * List of booleans to show to the human player which letters have been correctly guessed.
	 */
	protected List<Boolean> wordLettersMatched = new ArrayList<>();
	
	/**
	 * List of characters for each letter in the selected word for Hangman.
	 */
	protected List<Character> wordLetters = new ArrayList<>();
	
	/**
	 * String variable to hold the selected word for Hangman.
	 */
	String selectedWord;
	
	/**
	 * Creating a holder for an original Hangman game.
	 */
	Hangman hangmanOriginalGame;
	
	/**
	 * Creating a holder for a game of evil Hangman.
	 */
	Hangman hangmanEvilGame;
	
	/**
	 * To test whether the method updating the arraylist of characters representing total guesses is working properly.
	 */
	@Test
	void testCheckTotalGuesses() {
		
		hangmanOriginalGame = new HangmanOriginal("library");
		hangmanEvilGame = new HangmanEvil("library");
		
		//Adding a number of letters to the arraylist holding characters that have been guessed already.
		hangmanOriginalGame.addToTotalGuesses('a');
		hangmanOriginalGame.addToTotalGuesses('b');
		hangmanOriginalGame.addToTotalGuesses('c');
		hangmanEvilGame.addToTotalGuesses('d');
		
		//Since these characters have been already guessed, checking for them should return true.
		assertTrue(hangmanOriginalGame.checkTotalGuesses('a'));
		assertTrue(hangmanOriginalGame.checkTotalGuesses('b'));
		assertTrue(hangmanOriginalGame.checkTotalGuesses('c'));
		assertTrue(hangmanEvilGame.checkTotalGuesses('d'));
	}
	
	/**
	 * To test whether the method to replace letters in the list containing Hangman characters works properly.
	 */
	@Test
	void testReplaceWordLettersList() {
		
		//First checks that setting the Hangman word works properly.
		hangmanOriginalGame = new HangmanOriginal("library");
		
		List<Character> wordLettersComp = new ArrayList<>();
		wordLettersComp = hangmanOriginalGame.getWordLettersList();
		
		List<Character> wordLetters = new ArrayList<>();
		
		String test = "library";
		
		for (int i = 0; i < test.length(); i++ ) {
			
			char letterToAdd = test.charAt(i);
			
			wordLetters.add(i, letterToAdd);
		}
		
		assertEquals(wordLetters, wordLettersComp);
		
		//Will try to replace the Hangman word in the original game and see if it updated properly.
		hangmanOriginalGame.setWordLettersList("testing", true);
		hangmanEvilGame = new HangmanEvil("testing");
		
		wordLetters = hangmanOriginalGame.getWordLettersList();
		wordLettersComp = hangmanEvilGame.getWordLettersList();
		assertEquals(wordLetters, wordLettersComp);
		
		//Will try to replace the Hangman word in the evil game to see if it updated properly.
		hangmanEvilGame.setWordLettersList("failure", true);
		wordLettersComp = hangmanEvilGame.getWordLettersList();
		assertFalse(wordLetters.equals(wordLettersComp));		
	}
	
	/**
	 * To test the functionality of the method to check whether a user provided character matches at least one character
	 * in the Hangman word.	The method returns 0 to represent that the letter is in the word, and returns 1 if the letter 
	 * was already guessed, and returns 2 to indicate that the letter is not in the word.
	 */
	@Test
	void testLettersMatching() {
		
		//Sets the Hangman word in a new original game to "library"
		hangmanOriginalGame = new HangmanOriginal("library");
		
		//These letters should not match against anything.
		assertEquals(2, hangmanOriginalGame.letterMatching('e'));
		assertEquals(2, hangmanOriginalGame.letterMatching('o'));
		
		//These letters are in the Hangman word so they should match.
		assertEquals(0, hangmanOriginalGame.letterMatching('r'));
		assertEquals(0, hangmanOriginalGame.letterMatching('l'));
		
		//Checking if the proper value is returned for letters that have already been guessed previously.
		hangmanOriginalGame.addToTotalGuesses('t');
		
		assertEquals(1, hangmanOriginalGame.letterMatching('e'));
		assertEquals(1, hangmanOriginalGame.letterMatching('t'));
	}
	
	/**
	 * To test if the method works for checking that the game is over.
	 */
	@Test
	void testIsGameOver() {		
		
		//Setting up the letters matching list of booleans to indicate that all letters have been guessed correctly.
		String word = "library";
		HangmanOriginal hangmanOriginalGame = new HangmanOriginal(word);
		
		for (int i = 0; i < word.length(); i++ ) {
			
			hangmanOriginalGame.wordLettersMatchedBools.set(i, true);
		}
		
		//If all letters have been guessed, this method should return true.
		assertTrue(hangmanOriginalGame.isGameOver());
				
		//If the letters have not all been guessed yet, should return false.
		word = "anotherrun";
		hangmanOriginalGame = new HangmanOriginal(word);
		assertFalse(hangmanOriginalGame.isGameOver());
				
		//If there have been 6 incorrect guesses, the game should end because the human player loses.
		word = "losing";
		hangmanOriginalGame = new HangmanOriginal(word);
		hangmanOriginalGame.addToIncorrectGuesses('a');
		hangmanOriginalGame.addToIncorrectGuesses('b');
		hangmanOriginalGame.addToIncorrectGuesses('c');
		hangmanOriginalGame.addToIncorrectGuesses('d');
		hangmanOriginalGame.addToIncorrectGuesses('e');
		hangmanOriginalGame.addToIncorrectGuesses('f');
		assertTrue(hangmanOriginalGame.isGameOver());		
	}
	
	/**
	 * To test that the method calling the dictionary methods to filter words by length is working properly.
	 */
	@Test
	void testSetWordFamilyByLength() {
		
		//Compares files with 4 letter words to see if the filtering process worked properly.
		String word = "villain";
		HangmanEvil hangmanEvilGame = new HangmanEvil(word);
		
		hangmanEvilGame.setWordFamilyByLength(4, "src/dictionary/words_clean.txt", "src/dictionary/words_fourletters_test.txt");
		
		ArrayList<String> expectedLines = new ArrayList<String>();
		expectedLines = dictionary.MyFileReader.filterLines("src/dictionary/words_fourletters_test.txt");
				
		ArrayList<String> expectedLinesComp = new ArrayList<String>();
		expectedLinesComp = dictionary.MyFileReader.filterLines("src/dictionary/words_fourletters.txt");
		
		assertEquals(expectedLines, expectedLinesComp);
		
		//Compares files with 3 letter words to see if the filtering process worked properly.
		expectedLines = new ArrayList<String>();
		hangmanEvilGame.setWordFamilyByLength(3, "src/dictionary/words_clean.txt", "src/dictionary/words_threeletters_test.txt");
		expectedLines = dictionary.MyFileReader.filterLines("src/dictionary/words_threeletters_test.txt");
		
		ArrayList<String> expectedLinesFiltered = new ArrayList<String>();
		expectedLinesFiltered.add("one");
		expectedLinesFiltered.add("sea");
		expectedLinesFiltered.add("dog");
		expectedLinesFiltered.add("pug");
		
		assertEquals(expectedLines, expectedLinesFiltered);
		
		//Compares files with 10 letter words to see if the filtering process worked properly.
		expectedLines = new ArrayList<String>();
		hangmanEvilGame.setWordFamilyByLength(10, "src/dictionary/words_clean.txt", "src/dictionary/words_tenletters_test.txt");
		expectedLines = dictionary.MyFileReader.filterLines("src/dictionary/words_tenletters_test.txt");
		
		expectedLinesFiltered = new ArrayList<String>();
		expectedLinesFiltered.add("disastrous");
		expectedLinesFiltered.add("hysterical");
		expectedLinesFiltered.add("confidence");
		
		assertEquals(expectedLines, expectedLinesFiltered);
	}
	
	/**
	 * Tests to see if the method in the evil version of Hangman that selects different word families depending on the placement of certain letters 
	 * is functioning properly. The method returns the maximum number of words that meet the sequence requirement to give the computer the highest
	 * chance to dodge the user's guess. 
	 */
	@Test
	void testSetWordFamilyByLetter() {
		
		//Will test if the method works with a given letter 'o' against the current word "boil". 
		String word = "boil";
		HangmanEvil hangmanEvilGame = new HangmanEvil(word);
		
		hangmanEvilGame.setWordFamilyByLength(4, "src/dictionary/words_clean.txt", "src/dictionary/words_fourletters_test.txt");
		int maxSequence = hangmanEvilGame.setWordFamilyByLetter('o', "src/dictionary/words_fourletters_test.txt", "src/dictionary/words_fourletters_family.txt");
		
		/*Four words have 'o' as the second letter, the remaining 8 words do not have an 'o' in the word. Hence the method should return 8, i.e. the computer 
		will choose and slide in a new word from the list of 8 words that do not have an 'o' in order to maximize the chances that the human player will lose.*/
		
		assertEquals(8, maxSequence);
	
		//Will test if the method works with a given letter 's' against the current word "one".
		word = "one";
		hangmanEvilGame = new HangmanEvil(word);
		
		hangmanEvilGame.setWordFamilyByLength(3, "src/dictionary/words_clean.txt", "src/dictionary/words_threeletters_test.txt");
		maxSequence = hangmanEvilGame.setWordFamilyByLetter('s', "src/dictionary/words_threeletters_test.txt", "src/dictionary/words_threeletters_family.txt");
		
		/*Only 1 of the 4 words has an 's', therefore, the method should return 3, i.e. the computer will choose a new word from the remaining list of 
		3 words that do not have an 'n' in order to maximize the chances that the human player will lose. The computer will remove the word sea from the
		possible list of words because the human user has guessed 's' and will randomly choose one of the other three words ("one", "dog", or "pug").*/
		
		assertEquals(3, maxSequence);
			
		//Will test if the method works with a given letter 'y' against the current word "hysterical".
		word = "hysterical";
		hangmanEvilGame = new HangmanEvil(word);
		
		hangmanEvilGame.setWordFamilyByLength(10, "src/dictionary/words_clean.txt", "src/dictionary/words_tenletters_test.txt");
		maxSequence = hangmanEvilGame.setWordFamilyByLetter('y', "src/dictionary/words_tenletters_test.txt", "src/dictionary/words_tenletters_family.txt");
		
		/*Only 1 of the 3 words has an 'y', therefore, the method should return 2, i.e. the computer will choose a new word from the remaining list of 
		2 words randomly that do not have an 'y' in order to maximize the chances that the human player will lose.*/
		
		assertEquals(2, maxSequence);		
	}
}
