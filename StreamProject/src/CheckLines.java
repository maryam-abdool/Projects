/**
 * This class is a delegate for checking the first two lines as well as the
 * rest of the lines in the input stream.
 * 
 * @author Maryam Abdool
 *
 */
public class CheckLines {
	/**
	 * This is a constructor that creates a CheckLines object and initializes 
	 * an InputStreamValidation and PerformOperations object.
	 */
	CheckLines() {
		inputStreamValidation = new InputStreamValidation();
		performOperations = new PerformOperations();
	}
	
	/**
	 * This method delegates to other methods that validate the formatting of 
	 * each line depending on the line number.
	 * 
	 * @param lineRead   Current line being read.
	 * @param lineNumber The line number in file.
	 */
	public void checkLine(String lineRead, int lineNumber) {
		if (lineNumber == 0 || lineNumber == 1) {
			checkFirstTwoLines(lineRead, lineNumber);
		} else {
			checkRestLines(lineRead, lineNumber);
		}
	}

	/**
	 * This method validates the formatting of the first two lines as well as 
	 * the formatting of the user's selections.
	 * 
	 * @param lineRead   Current line being read.
	 * @param lineNumber The line number in file.
	 */
	private void checkFirstTwoLines(String lineRead, int lineNumber) {
		int numberOfFieldsCurrentLine = 0;
		SelectionValidation selectValidation = new SelectionValidation();
		TerminalValidation terminalValidation = new TerminalValidation();
		
		if (inputStreamValidation.isLineFound(lineRead, lineNumber) == true) {
			numberOfFieldsCurrentLine = inputStreamValidation
					.countNumberOfFields(lineRead, lineNumber);
			inputStreamValidation.isTabAndSpaceFormatted(lineRead, lineNumber);
			if (lineNumber == 0) {
				inputStreamValidation.containsEmptyField(lineRead);
				selectValidation.checkSelectionHeader
				(TSVPipeline.tsvFilter.getSelectName(), lineRead);
				terminalValidation.checkTerminalHeader
				(TSVPipeline.tsvFilter.getTerminateName(), lineRead);
				performOperations.writeToFile(lineRead, lineNumber);
			} else {
				inputStreamValidation.containsAppropriateTypes(lineRead);
				inputStreamValidation.checkLineAgreement
				(numberOfFieldsCurrentLine);
				selectedValue = selectValidation.determineTypeOfSelection();
				selectValidation.checkSelectionType(lineRead, selectedValue);
				terminalValidation.checkTerminalType(lineRead);
				performOperations.writeToFile(lineRead, lineNumber);
			}
		} else {
			Terminate.terminate();
		}
	}

	/**
	 * This method validates the formatting of all the lines after the first two
	 * lines, as well as delegates to another method for making selections based 
	 * on the user's request.
	 * 
	 * @param lineRead   Current line being read.
	 * @param lineNumber The line number in file.
	 */
	private void checkRestLines(String lineRead, int lineNumber) {
		int numberOfFieldsCurrentLine = 0;

		if (inputStreamValidation.isLineFound(lineRead, lineNumber) == true) {
			numberOfFieldsCurrentLine = 
					inputStreamValidation.countNumberOfFields(lineRead, 
					lineNumber);
			inputStreamValidation.checkLineAgreement(numberOfFieldsCurrentLine);
			inputStreamValidation.isTabAndSpaceFormatted(lineRead, lineNumber);
			inputStreamValidation.containsEmptyField(lineRead);
			inputStreamValidation.typeValidation(lineRead);
			performOperations.selectRecord(lineRead, lineNumber, selectedValue);

		} else {
			Terminate.terminate();
		}
	}
	
	InputStreamValidation inputStreamValidation;
	PerformOperations performOperations;
	private Object selectedValue;

}
