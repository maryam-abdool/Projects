import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.function.Function;

/**
 * This class is in charge of delegating what gets piped.
 * 
 * @author Maryam Abdool 
 *
 */
public class TSVPipeline {

	/**
	 * This is a constructor that constructs a TSVPipeline and initializes the
	 * tsvFilter object.
	 * 
	 * @param inTSVFilter a tsvFilter object.
	 */
	public TSVPipeline(TSVFilter inTSVFilter) {
		tsvFilter = inTSVFilter;
	}

	/**
	 * This method delegates to the other method but is made to protect the 
	 * privacy of the checkFile() method.
	 */
	public void doit() {
		checkFile();
	}

	/**
	 * This method reads from a *.tsv file one line at a time, and delegates to
	 * another method to check the proper formatting of each line. It then 
	 * prints if the operation is successful.
	 */
	private void checkFile() {
		String lineRead;
		CheckLines checklines = new CheckLines();

		File file = new File(tsvFilter.getFileNameWithExtension());
		try {
			Scanner input = new Scanner(file).useDelimiter("(?<=@)");
			int lineNumber = 0;
			while (input.hasNext()) {
				lineRead = input.next();
				checklines.checkLine(lineRead, lineNumber);
				lineNumber++;
			}
			input.close();

		} catch (FileNotFoundException e) {
			System.out.println("File does not exist.");
			System.exit(0);
		}
		
		printTerminalComputations();
		System.out.println("");
		System.out.println("Operation is successful.");
	}
	
	/**
	 * This method prints the output of the terminal computation to the user.
	 */
	private void printTerminalComputations() {
		if(tsvFilter.getTerminalComputation() != null) {
			tsvFilter.getTerminalComputation().print();
		}
	}
	
	public static TSVFilter tsvFilter;

}