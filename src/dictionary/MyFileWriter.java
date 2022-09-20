package dictionary;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Class that contains the method for writing an arraylist of strings to a file.
 */
public class MyFileWriter {

	/**
	 * Static method to write an arraylist of text lines into a file.
	 * @param lines of text to be written, organized in an arraylist.
	 * @param filename of the file that will be written into.
	 */
	public static void writeToFile(ArrayList<String> lines, String filename) {

		/**
		 * Initializes a filewriter.
		 */
		FileWriter fileWriter = null;
		
		/**
		 * Initializes a bufferedwriter to pair with the filewriter.
		 */
		BufferedWriter bufferedWriter = null;
		
		//Tries to loop through all the lines of text to write into the new file unless there is an exception.
		try {
			
			fileWriter = new FileWriter(filename);
			bufferedWriter = new BufferedWriter(fileWriter);
			
			for (String line : lines) {
				
				bufferedWriter.write(line);
				bufferedWriter.write("\n");
			}
			
		//Prints the stack trace if there is an IOException. 	
		} catch (IOException e) {
			
			e.printStackTrace();
		
		} finally {
			
			try {
				
				//Flushes the filewriter and bufferedwriter
				fileWriter.flush();
				bufferedWriter.flush();
				
				//Closes both the filewriter and buffered writer.
				fileWriter.close();
				bufferedWriter.close();
			
			//Prints the stack trace if there is an IOException. 
			} catch (IOException e) {
				
				e.printStackTrace();
			}
				
		}
	}
}
	
