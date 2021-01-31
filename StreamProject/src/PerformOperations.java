import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * This class performs the operation of selecting a record and writing to file.
 * 
 * @author Maryam Abdool
 *
 */
public class PerformOperations {
	/**
	 * This method makes the selection of the record based on the match between 
	 * the user's selection and the corresponding value in the file. It then 
	 * writes to the file if the match is successful. This method also ensures 
	 * that the terminal operation is being run on the output stream instead
	 * of the input stream, if the user made a selection.
	 * 
	 * @param lineRead   Current line being read.
	 * @param lineNumber The line number in file.
	 * @param selectedValue The selected value.
	 */
	public void selectRecord(String lineRead, int lineNumber, 
			Object selectedValue) {
		String[] fieldsInLineRead = lineRead.split("(\\*|@)");

		if (selectedValue != null) {
			if ((selectedValue.toString())
					.equals(fieldsInLineRead
							[SelectionValidation.fieldSelectedIndex])) {
				writeToFile(lineRead, lineNumber);
				chooseTerminalOperation(lineRead, lineNumber);
				
			}
		} 
		else {
			writeToFile(lineRead, lineNumber);
			chooseTerminalOperation(lineRead, lineNumber);
		} 
	}
	
	/**
	 * This method executes the terminal computation based on the user's input.
	 * 
	 * @param lineRead   Current line being read.
	 * @param lineNumber The line number in file.
	 */
	private void chooseTerminalOperation(String lineRead, int lineNumber) {
		TerminalOperations terminalOperations = new TerminalOperations();
		
		if (TSVPipeline.tsvFilter.getTerminalComputation() == 
				TerminalComputation.MEANVAL) {
			terminalOperations.meanVal(lineRead, lineNumber);
		}
		if (TSVPipeline.tsvFilter.getTerminalComputation() == 
				TerminalComputation.STDVAL) {
			terminalOperations.stdVaL(lineRead, lineNumber);
		}
		if (TSVPipeline.tsvFilter.getTerminalComputation() == 
				TerminalComputation.MEANLENGTH) {
			terminalOperations.meanLength(lineRead, lineNumber);
		}
		if (TSVPipeline.tsvFilter.getTerminalComputation() == 
				TerminalComputation.STDLENGTH) {
			terminalOperations.stdLength(lineRead, lineNumber);
		}
	}
	
	/**
	 * This method writes to the output file each line that is properly 
	 * formatted. If the input file is being read from the first line, then a 
	 * new file is created or re-written. If the input file is being read from 
	 * the subsequent lines, then the lines are just appended to the output file 
	 * previously created.
	 * 
	 * @param lineRead   Current line being read.
	 * @param lineNumber The line number in file.
	 */
	public void writeToFile(String lineRead, int lineNumber) {
		try {
			File file = new File(TSVPipeline.tsvFilter.getFileName() 
					+ "Output" + TSVPipeline.tsvFilter.getFileExtension());
			if (lineNumber == 0) {
				PrintWriter out = new PrintWriter(file);
				out.print(lineRead);
				out.close();
				System.out.println("Line successfully appeneded to file.");
				System.out.println(" ");
			} else {
				FileWriter fileWriter = new FileWriter(file, true);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				PrintWriter out = new PrintWriter(bufferedWriter);
				out.print(lineRead);
				out.close();
				System.out.println("Line successfully appeneded to file.");
				System.out.println(" ");
			}
		} catch (Exception e) {
			System.out.println("Could not write to file.");
		}
	}
}
