package dictionary;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * Class that contains methods for reading and manipulating text from files.
 */
public class MyFileReader {

	/**
	 * Creates an arraylist variable of strings that will be used to store text across a number of methods.
	 */
	static ArrayList<String> lines; 
	
	/**
	 * Creates a hashmap that will use lists of boolean sequences as keys and the number of each respective boolean sequence as values.
	 */
	private static HashMap<List<Boolean>, Integer> sequenceCount;
	
	/**
	 * Method that filters a file of text according to the length of each word (the number of letters in each word) and returns the 
	 * new text in an arraylist of strings.
	 * @param lengthFamily denoting how many letters words for the new file should contain (size for the new family of words).
	 * @param filename for the file of text that will be read.
	 * @return an arraylist of strings that contains words meeting the new length requirement.
	 */
	public static ArrayList<String> wordsSameLength(int lengthFamily, String filename) {

		//Variables to store the final arraylist of strings as well as a string variable to store single lines of text.
		lines = new ArrayList<String>();
		String nextLine;
		
		//Initializes the filereader and bufferedreader.
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
				
		//Will try to loop through each line of text, and if the line meets the length requirement, will be added to the arraylist of strings.
		try {
			
			fileReader = new FileReader(filename);
			bufferedReader = new BufferedReader(fileReader);
			
			nextLine = bufferedReader.readLine();
			
			while (nextLine != null) {
							
				boolean linePass = true;
				
				
				if (nextLine.length() != lengthFamily) {
					
					linePass = false;
				}
				
				if (linePass == true) {
					
					lines.add(nextLine);
				}
				
				nextLine = bufferedReader.readLine();
			}	
			
		//If there is a FileNotFoundException or an IOException, will print the stack trace.
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} finally {
			
			//Afterwards will try to close the filereader and bufferedreader.
			try {
				
				fileReader.close();
				bufferedReader.close();
			
			//Unless there is an IOException, under which will print the stack trace.
			} catch (IOException e) {
				
				e.printStackTrace();
			}	
		}
				
		return lines;
	}
	
	
	/**
	 * Method that returns a hashmap using lists of booleans as keys, and the respective number of each list as values. The method will create 
	 * lists of booleans by comparing whether letters in a string match a provided letter character.
	 * @param letter character that will be used to compare against characters in lines of text, to create boolean lists.
	 * @param length of each word to make sure each sequence of booleans stored in lists are of the same size.
	 * @param filename of the file of text that will be read from.
	 * @return HashMap with the list of boolean sequences as keys, and the number of each list as values.
	 */
	public static HashMap<List<Boolean>, Integer> sequenceMapCounter(char letter, int length, String filename) {

		/*Initializes variables for the arraylist of strings that will store the final text, a string to store each line of text, the list of 
		 booleans that will track whether a given letter appears in a word and in which position, and the hashmap that uses the lists of
		 booleans as keys and the number of each boolean sequence as values. */
		lines = new ArrayList<String>();
		String nextLine;
		List<Boolean> wordLettersMatched = null;
		sequenceCount = new HashMap<List<Boolean>, Integer>();
		
		//Initializes the filereader and bufferedreader
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
				
		/*Will try to loop through each of line of text in the file, and if the character in the line matches with the given letter
		in the method declaration, will mark the respective position in a list of booleans as true. If the character does not match
		the given letter, then the position in the boolean list will be marked with false. */
		try {
			
			fileReader = new FileReader(filename);
			bufferedReader = new BufferedReader(fileReader);
			
			nextLine = bufferedReader.readLine();
			
			while (nextLine != null) {
			
				//Will also check that the length of each word matches the method declaration requirement, so that boolean lists are the same size.
				if (nextLine.length() == length) {
															
					wordLettersMatched = new ArrayList<>();
					
					for (int i = 0; i < nextLine.length(); i++) {
						
						char letterChar = nextLine.charAt(i);
																
						//If the character in the line of text matches the given letter in the method declaration, then the boolean list will be marked with a true.
						if (letterChar == letter) {
													
							wordLettersMatched.add(i, true); 
							
						} else {
							
							//Otherwise the boolean list will be marked with a false.
							wordLettersMatched.add(i, false);
						}
					}
					
					/*Will then place each list of booleans into the hashmap as keys, with a starting value of 1 and will increment the value for each  
					additional boolean list that has the same sequence of boolean values.*/
					if (sequenceCount.containsKey(wordLettersMatched) == false) {
						
						sequenceCount.put(wordLettersMatched, 1);
						
					} else {
						
						sequenceCount.replace(wordLettersMatched, sequenceCount.get(wordLettersMatched) + 1);
					}
										
					nextLine = bufferedReader.readLine();
					
				//If the length of the line of text does not match the given requirement, it will skip to the next line.
				}	else {
					
					nextLine = bufferedReader.readLine();
				}
			}
		
		//If there is a FileNotFoundException or an IOException, will print the stack trace.
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				//Will try to close the filereader and buffered reader.
				fileReader.close();
				bufferedReader.close();
			
			//Unless there is an IOException, in which it will print the stack trace.
			} catch (IOException e) {
				
				e.printStackTrace();
			}	
		}
				
		return sequenceCount;
	}
	
		
	/**
	 * Method that returns an arraylist of strings depending on where a given letter appears in the strings of text. The method will filter
	 * out strings that do not match a given sequence of letters, denoted by a list of booleans indicating if a certain letter should be at
	 * a specific location in the string or not.
	 * @param letter that will be used in conjunction with the list of booleans to determine its required position in a string.
	 * @param boolSequence or a list of booleans to determine where the given letter should appear in a string.
	 * @param filename of the file that contains lines of text and will be read.
	 * @return an arraylist of strings that contains words that meet a certain sequence of letters.
	 */
	public static ArrayList<String> filterWordsBySequence(char letter, List<Boolean> boolSequence, String filename) {

		//Initializes variables for the final arraylist of strings and the string variable to store individual lines of text.
		lines = new ArrayList<String>();
		String nextLine;
		
		//Initializes the filereader, bufferedreader, and list of boolean values.
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		List<Boolean> comparisonList = null;
		
		/*Will try to loop through each line of text, and if the word aligns with the boolean sequence in the list of booleans,
		 with the given letter either appearing or not appearing in specific locations, the line will be added to the final
		 arraylist of strings.*/
		try {
			
			fileReader = new FileReader(filename);
			bufferedReader = new BufferedReader(fileReader);
			
			nextLine = bufferedReader.readLine();
						
			while (nextLine != null) {
				
				comparisonList = new ArrayList<>();
				
				boolean linePass = true;
				
				//Will create a comparison list of boolean values that will be matched against the blueprint boolean sequence.																
				for (int i = 0; i < nextLine.length(); i++) {
					
					char letterChar = nextLine.charAt(i);
															
					if (letterChar == letter) {
						
						comparisonList.add(i, true);
													
					} else {
						
						comparisonList.add(i, false);
					}
				}
				
				//If the sequence of letters aligns with the boolean sequence provided in the method declaration, the line will be added to the arraylist of strings.
				if (! comparisonList.equals(boolSequence)) {
					
					linePass = false;
				}
								
				if (linePass == true) {
					
					lines.add(nextLine);
				}
				
				nextLine = bufferedReader.readLine();
			}	
			
		//If there is a FileNotFoundException or an IOException, it will print the stack trace.
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} finally {
			
			//Will try to close the filereader and bufferedreader.
			try {
				
				fileReader.close();
				bufferedReader.close();
			
			//Unless there is an IOException, when it will print the stack trace.
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
				
		return lines;
	}
	
	
	/**
	 * This method Will filter a file of text according to the conditions provided in the instructions. For example, the text cannot have upper
	 * case letters, abbreviations, an apostrophe, a hyphen, a compound word, or a digit.
	 * @param filename or the file containing lines of text that will be read.
	 * @return arraylist of strings representing the new filtered list of words.
	 */
	public static ArrayList<String> filterLines(String filename) {

		//Initializes the final arraylist of strings that will be returned and a string variable to store individual lines of text.
		lines = new ArrayList<String>();
		String nextLine;
		
		//Initializes the filereader and bufferedreader
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
			
		//Will try to loop through each line and will cycle through each letter to see if the instructed requirements are met.
		try {
			
			fileReader = new FileReader(filename);
			bufferedReader = new BufferedReader(fileReader);
			
			nextLine = bufferedReader.readLine();
				
			
			while (nextLine != null) {
			
				//Will first trim each line for excess whitespace.
				nextLine = nextLine.trim();
				
				boolean linePass = true;
				
				//Will skip over the line if it is an empty line.
				if (nextLine.isEmpty() == true) {
					
					linePass = false;	
				}
				
				
				for (int i = 0; i < nextLine.length(); i++) {
					
					char letterChar = nextLine.charAt(i);
					
					//Will skip over the line if it contains an upper case character.
					if (Character.isUpperCase(letterChar) == true) {
						
						linePass = false;	
					}
					
					//Will skip over the line if it contains a period denoting an abbreviation.
					if (letterChar == '.') {
						
						linePass = false;
					}
					
					//Will skip over the line if it contains a slash which could fit under certain abbreviations.
					if (letterChar == '\'') {
						
						linePass = false;
					}
					
					//Will skip over the line if it contains a hyphen.
					if (letterChar == '-') {
						
						linePass = false;
					}
					
					//Will skip over the line if it contains an empty space.
					if (letterChar == ' ') {
						
						linePass = false;
					}
					
					//Will skip over the line if it contains a digit.
					if (Character.isDigit(letterChar) == true) {
						
						linePass = false;	
					}
				}	
				
				//If the line passes all the required conditions, it will be added to the arraylist of lines.
				if (linePass == true) {
					
					lines.add(nextLine);
				}
				
				nextLine = bufferedReader.readLine();
			}	
			
			//If there is a FileNotFoundException or an IOException, will print the stack trace.
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
				
			} catch (IOException e) {
				
				e.printStackTrace();
				
			} finally {
				
				//Will try to close the filereader and bufferedreader.
				try {
					
					fileReader.close();
					bufferedReader.close();
				
				//Unless there is an IOException, when it will print the stack trace.
				} catch (IOException e) {
					
					e.printStackTrace();
				}			
			}
				
		return lines;
	}
}
