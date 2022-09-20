package dictionary;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * JUnit test class for methods in MyFileWriter to write lines of text into a file.
 */
class MyFileWriterTest {

	/**
	 * Testing the method for writing lines of text to a new file. Calling using static methods for more convenience.
	 */
	@Test
	void testWriteToFile() {
				
		//Testing with a file that contains capitalized letters and periods to see if the process will filter these out as instructed.
		ArrayList<String> expectedLines = new ArrayList<String>();
		expectedLines = dictionary.MyFileReader.filterLines("src/dictionary/words_tofilter.txt");	
		dictionary.MyFileWriter.writeToFile(expectedLines, "src/dictionary/words_filtertest.txt");
		
		ArrayList<String> expectedLinesComp = new ArrayList<String>();
		expectedLinesComp = dictionary.MyFileReader.filterLines("src/dictionary/words_filtertest.txt");	
				
		ArrayList<String> expectedLinesClean = new ArrayList<String>();
		expectedLinesClean.add("coil");
		expectedLinesClean.add("inexpensive");
		expectedLinesClean.add("woozy");
		expectedLinesClean.add("loving");
		expectedLinesClean.add("general");
		expectedLinesClean.add("bite");
		expectedLinesClean.add("ultra");
		expectedLinesClean.add("disastrous");
		expectedLinesClean.add("library");
		expectedLinesClean.add("mint");
		
		for (int i = 0; i < 10; i++) {
			assertEquals(expectedLinesComp.get(i), expectedLinesClean.get(i));
		}
		
		
		//Testing with a file that includes empty lines and digits to see if the process will filter these out as instructed.
		ArrayList<String> expectedLinesFiltered = new ArrayList<String>();
		expectedLinesFiltered = dictionary.MyFileReader.filterLines("src/dictionary/words_clean.txt");
		
		ArrayList<String> expectedLinesDigitsSpaces = new ArrayList<String>();
		expectedLinesDigitsSpaces = dictionary.MyFileReader.filterLines("src/dictionary/words_digits_spaces.txt");	
		dictionary.MyFileWriter.writeToFile(expectedLinesDigitsSpaces, "src/dictionary/words_filtertest.txt");
		
		expectedLinesComp = new ArrayList<String>();
		expectedLinesComp = dictionary.MyFileReader.filterLines("src/dictionary/words_filtertest.txt");
		
		assertEquals(expectedLinesComp, expectedLinesFiltered);
		
		
		//Testing with a file that includes dashes and gaps in words to see if the process will filter these out as instructed.
		expectedLinesFiltered = new ArrayList<String>();
		expectedLinesFiltered = dictionary.MyFileReader.filterLines("src/dictionary/words_clean.txt");
		
		ArrayList<String> expectedLinesDashes = new ArrayList<String>();
		expectedLinesDashes = dictionary.MyFileReader.filterLines("src/dictionary/words_dashes_gaps.txt");	
		dictionary.MyFileWriter.writeToFile(expectedLinesDashes, "src/dictionary/words_filtertest.txt");
		
		expectedLinesComp = new ArrayList<String>();
		expectedLinesComp = dictionary.MyFileReader.filterLines("src/dictionary/words_filtertest.txt");
		
		assertEquals(expectedLinesComp, expectedLinesFiltered);
	}
}
