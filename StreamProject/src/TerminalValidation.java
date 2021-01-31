/**
 * This class handles the validations for the terminal operation.
 * 
 * @author Maryam Abdool 
 *
 */
public class TerminalValidation {
	/**
	 * This method checks that the header name the user specified is valid, and 
	 * a part of the headers in the .tsv file, that is if the user specified a
	 * selection from the terminal() method.
	 * 
	 * @param selectedName The name of the header selected by the user.
	 * @param lineRead     Current line being read.
	 */
	public void checkTerminalHeader(String selectedName, String lineRead) {
		boolean selectionFound = false;

		if (selectedName != null) {
			String[] fieldsInLineRead = lineRead.split("(\\*|@)");
			for (int i = 0; i < fieldsInLineRead.length; i++) {
				if (fieldsInLineRead[i].equals(selectedName)) {
					fieldTerminatedIndex = i;
					lineRead = fieldsInLineRead[i];
					selectionFound = true;
					break;
				}
			}

			if (selectionFound == false) {
				Terminate.terminateTermination();
			}
		}
	}
	
	/**
	 * This method checks that the type of the terminal computation selected by 
	 * the user matches the type of the selected value. 
	 * 
	 * @param lineRead Current line being read.
	 */
	public void checkTerminalType(String lineRead) {
		TerminalComputation terminalComputation = TSVPipeline.tsvFilter
				.getTerminalComputation();
		
		if(terminalComputation != null) {
			String[] fieldsInLineRead = lineRead.split("(\\*|@)");
			String actualTypeOfSelectedValue = fieldsInLineRead
					[fieldTerminatedIndex];
			String chosenTypeOfSelectedValue = null; 
					
			if(terminalComputation == TerminalComputation.MEANVAL || 
					terminalComputation == TerminalComputation.STDVAL) {
				chosenTypeOfSelectedValue = "LONG";
			}
			if(terminalComputation == TerminalComputation.MEANLENGTH || 
					terminalComputation == TerminalComputation.STDLENGTH) {
				chosenTypeOfSelectedValue = "STRING";
			}
			
			if(!actualTypeOfSelectedValue.toUpperCase().equals
					(chosenTypeOfSelectedValue)) {
				Terminate.terminateTermination();
			}
		}
	}
	
	public static int fieldTerminatedIndex = -1;

}
