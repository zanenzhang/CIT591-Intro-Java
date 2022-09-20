package dictionary;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * JUnit test class for methods in MyFileReader to get input of text lines from an existing file.
 */
class MyFileReaderTest {
	
		
	/**
	 * Testing the method that filters down a list of words by the number of letters in each word. The method should create a new
	 * file that only contains words of the same length.
	 */
	@Test
	void testWordsSameLength() {
		
		//Filtering the words_clean text file for three letter words.
		ArrayList<String> actualThreeLetters = dictionary.MyFileReader.wordsSameLength(3, "src/dictionary/words_clean.txt");
		ArrayList<String> expectedThreeLetters = new ArrayList<String>();
				
		expectedThreeLetters.add("one");
		expectedThreeLetters.add("sea");
		expectedThreeLetters.add("dog");
		expectedThreeLetters.add("pug");
						
		for (int i = 1; i < expectedThreeLetters.size(); i++) {
			assertEquals(expectedThreeLetters.get(i), actualThreeLetters.get(i));
		}
		
		//Filtering the words_clean text file to only get the four letter words.
		ArrayList<String> actualFourLetters = dictionary.MyFileReader.wordsSameLength(4, "src/dictionary/words_clean.txt");
		ArrayList<String> expectedFourLetters = new ArrayList<String>();
		
		expectedFourLetters.add("coil");
		expectedFourLetters.add("bite");
		expectedFourLetters.add("mint");
		expectedFourLetters.add("jump");
		expectedFourLetters.add("roil");
		expectedFourLetters.add("five");
		expectedFourLetters.add("four");
		expectedFourLetters.add("sexy");
		expectedFourLetters.add("told");
		expectedFourLetters.add("with");
		expectedFourLetters.add("wild");
		expectedFourLetters.add("make");		
						
		for (int i = 1; i < expectedFourLetters.size(); i++) {
			assertEquals(expectedFourLetters.get(i), actualFourLetters.get(i));
		}
		
		//Filtering the words_clean text file to only get the five letter words.		
		ArrayList<String> actualFiveLetters = dictionary.MyFileReader.wordsSameLength(5, "src/dictionary/words_clean.txt");
		ArrayList<String> expectedFiveLetters = new ArrayList<String>();
				
		expectedFiveLetters.add("woozy");
		expectedFiveLetters.add("ultra");
		expectedFiveLetters.add("train");
		expectedFiveLetters.add("queue");
		expectedFiveLetters.add("level");
		expectedFiveLetters.add("goofy");
		expectedFiveLetters.add("books");
		expectedFiveLetters.add("blood");
		expectedFiveLetters.add("brood");
		expectedFiveLetters.add("woman");
		expectedFiveLetters.add("forty");
		expectedFiveLetters.add("point");	
		expectedFiveLetters.add("house");
		expectedFiveLetters.add("along");
		expectedFiveLetters.add("bench");
						
		for (int i = 1; i < expectedFiveLetters.size(); i++) {
			assertEquals(expectedFiveLetters.get(i), actualFiveLetters.get(i));
		}

	}
	
	/**
	 * Tests if the sequence map counter function works properly, i.e. returns a map with boolean sequences as keys and their 
	 * respective counts, depending on how many times the boolean sequence appeared in a text file. 
	 */
	@Test
	void testSequenceMapCounter() {
		
		/*For a list of words that only contains four letter words from the given words_clean text file, there should be only
		 * two possible potential boolean sequences for all words, either all false i.e. the words do not contain the letter 'a'
		 * in any location, which occurs 11 times, or [false, true, false, false] i.e. the letter 'a' is the second letter, which
		 * only occurs once in the word "make".
		 */
		Map< List<Boolean>, Integer> sequenceCount = new HashMap<List<Boolean>, Integer>();
		Map< List<Boolean>, Integer> sequenceCountComp = new HashMap<List<Boolean>, Integer>();
		
		sequenceCount = dictionary.MyFileReader.sequenceMapCounter('a', 4, "src/dictionary/words_fourletters.txt");
				
		List<Boolean> wordLettersMatched = new ArrayList<>();
		wordLettersMatched.add(false);
		wordLettersMatched.add(false);
		wordLettersMatched.add(false);
		wordLettersMatched.add(false);
		sequenceCountComp.put(wordLettersMatched, 11);
		
		wordLettersMatched = new ArrayList<>();		
		wordLettersMatched.add(false);
		wordLettersMatched.add(true);
		wordLettersMatched.add(false);
		wordLettersMatched.add(false);				
		sequenceCountComp.put(wordLettersMatched, 1);

		assertEquals(sequenceCount, sequenceCountComp);
				
		/*For a list of words that only contains three letter words from the word_spaces file, there should be only
		 * two potential boolean sequences for words that contain the letter 'e', either the 'e' is in the middle of the word,
		 * which occurs just once ("sea"), or the letter 'e' is the last letter in the word, which also only occurs once ("one").
		 * There are only two words with three letters, hence there are no boolean sequences that are all false.
		 */
		sequenceCount = dictionary.MyFileReader.sequenceMapCounter('e', 3, "src/dictionary/words_spaces.txt");
		sequenceCountComp = new HashMap<List<Boolean>, Integer>();
		
		wordLettersMatched = new ArrayList<>();
		wordLettersMatched.add(false);
		wordLettersMatched.add(true);
		wordLettersMatched.add(false);
		sequenceCountComp.put(wordLettersMatched, 1);
		
		wordLettersMatched = new ArrayList<>();		
		wordLettersMatched.add(false);
		wordLettersMatched.add(false);
		wordLettersMatched.add(true);				
		sequenceCountComp.put(wordLettersMatched, 1);

		assertEquals(sequenceCount, sequenceCountComp);		
		
		/*For a list of words that only contains three letter words from the word_clean file, there should be only
		 * one potential boolean sequence. Since no three letter word contains the letter 'i', the only possible sequence
		 * is [false, false, false], which occurs four times in the file.
		 */
		sequenceCount = dictionary.MyFileReader.sequenceMapCounter('i', 3, "src/dictionary/words_clean.txt");
		sequenceCountComp = new HashMap<List<Boolean>, Integer>();
		
		wordLettersMatched = new ArrayList<>();
		wordLettersMatched.add(false);
		wordLettersMatched.add(false);
		wordLettersMatched.add(false);
		sequenceCountComp.put(wordLettersMatched, 4);
		
		assertEquals(sequenceCount, sequenceCountComp);
	}
	
	
	/**
	 * Tests if the function to filter words by given boolean sequences works properly, i.e. returns an arraylist of strings that
	 * matches the given boolean sequence and letter. 
	 */
	@Test
	void testFilterWordsBySequence() {
		
		//For a file of four letter words from the given original words_clean file, the only word with the letter 'a' as the second letter is the word "make".
		List<Boolean> wordLettersMatched = new ArrayList<>();
		wordLettersMatched.add(false);
		wordLettersMatched.add(true);
		wordLettersMatched.add(false);
		wordLettersMatched.add(false);
		
		ArrayList<String> expectedWords = new ArrayList<String>();		
		ArrayList<String> expectedWordsComp = new ArrayList<String>();
		expectedWords = dictionary.MyFileReader.filterWordsBySequence('a', wordLettersMatched, "src/dictionary/words_fourletters.txt");		
		expectedWordsComp.add("make");
		
		assertEquals(expectedWords, expectedWordsComp);
		
		//For a file of four letter words from the given original words_clean file, words with the letter 'i' as the third letter are "coil" and "roil".
		wordLettersMatched = new ArrayList<>();
		wordLettersMatched.add(false);
		wordLettersMatched.add(false);
		wordLettersMatched.add(true);
		wordLettersMatched.add(false);
		
		expectedWords = new ArrayList<String>();		
		expectedWordsComp = new ArrayList<String>();
		expectedWords = dictionary.MyFileReader.filterWordsBySequence('i', wordLettersMatched, "src/dictionary/words_fourletters.txt");
		expectedWordsComp.add("coil");
		expectedWordsComp.add("roil");
		
		assertEquals(expectedWords, expectedWordsComp);
		
		//For a file of three letter words from the given original words_clean file, words with the letter 'g' as the last letter are "dog" and "pug".
		wordLettersMatched = new ArrayList<>();
		wordLettersMatched.add(false);
		wordLettersMatched.add(false);
		wordLettersMatched.add(true);
		
		expectedWords = new ArrayList<String>();		
		expectedWordsComp = new ArrayList<String>();
		expectedWords = dictionary.MyFileReader.filterWordsBySequence('g', wordLettersMatched, "src/dictionary/words_clean.txt");		
		expectedWordsComp.add("dog");
		expectedWordsComp.add("pug");
		
		assertEquals(expectedWords, expectedWordsComp);
	}
	
	/**
	 * Testing the method that filters down a list of words by the requirements provided in the instructions and returns this list as an arraylist of strings. 
	 * For example, there cannot be any: upper case letters, abbreviations or apostrophes, hyphens, compound words, or digits.
	 */
	@Test
	void testFilterLines() {
		
		//Testing against the provided words_clean file with another file that contains words with capital letters and periods.
		ArrayList<String> expectedLines = new ArrayList<String>();
		expectedLines = dictionary.MyFileReader.filterLines("src/dictionary/words_tofilter.txt");
				
		ArrayList<String> expectedLinesComp = new ArrayList<String>();
		expectedLinesComp = dictionary.MyFileReader.filterLines("src/dictionary/words_clean.txt");
		
		assertEquals(expectedLines, expectedLinesComp);
		
		
		//Testing against the provided words_clean file with another file that contains words with numbers, blank lines, and hyphens.
		expectedLines = new ArrayList<String>();
		expectedLines = dictionary.MyFileReader.filterLines("src/dictionary/words_fourletters_tofilter.txt");
				
		expectedLinesComp = new ArrayList<String>();
		expectedLinesComp.add("coil");
		expectedLinesComp.add("bite");
		expectedLinesComp.add("mint");
		expectedLinesComp.add("jump");
		expectedLinesComp.add("roil");
		expectedLinesComp.add("five");
		expectedLinesComp.add("four");
		expectedLinesComp.add("sexy");
		expectedLinesComp.add("told");
		expectedLinesComp.add("with");
		expectedLinesComp.add("wild");
		expectedLinesComp.add("make");
		
		assertEquals(expectedLines, expectedLinesComp);
		
		
		//Testing against the provided words_clean file with another file that contains words with digits and spaces in words.
		expectedLines = new ArrayList<String>();
		expectedLines = dictionary.MyFileReader.filterLines("src/dictionary/words_clean.txt");
				
		expectedLinesComp = new ArrayList<String>();
		expectedLinesComp = dictionary.MyFileReader.filterLines("src/dictionary/words_digits_spaces.txt");
		
		assertEquals(expectedLines, expectedLinesComp);
	}
}
