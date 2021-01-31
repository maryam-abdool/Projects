/**
 * This class handles the validation for the select operation.
 * 
 * @author Maryam Abdool 
 *
 */
public class SelectionValidation {
	/**
	 * This method checks that the header name the user specified is valid, and 
	 * a part of the headers in the .tsv file, that is if the user specified a
	 * selection from the selection() method.
	 * 
	 * @param selectedName The name of the header selected by the user.
	 * @param lineRead     Current line being read.
	 */
	public void checkSelectionHeader(String selectedName, String lineRead) {
		boolean selectionFound = false;

		if (selectedName != null) {
			String[] fieldsInLineRead = lineRead.split("(\\*|@)");
			for (int i = 0; i < fieldsInLineRead.length; i++) {
				if (fieldsInLineRead[i].equals(selectedName)) {
					fieldSelectedIndex = i;
					lineRead = fieldsInLineRead[i];
					selectionFound = true;
					break;
				}
			}

			if (selectionFound == false) {
				Terminate.terminateSelection();
			}
		}
	}
	
	/**
	 * This method determines the type of the selected value by the user, and
	 * returns the value accordingly.
	 * 
	 * @return The value selected by the user.
	 */
	public Object determineTypeOfSelection() {
		if (TSVPipeline.tsvFilter.getSelectStringValue() != null) {
			return TSVPipeline.tsvFilter.getSelectStringValue();
		}
		if (TSVPipeline.tsvFilter.getSelectLongValue() != null) {
			return TSVPipeline.tsvFilter.getSelectLongValue();
		}
		return null;
	}
	
	/**
	 * This method checks that the type of the value specified by the user 
	 * matches the type of the name selected, as per the specification in 
	 * the .tsv file.
	 * 
	 * @param lineRead Current line being read.
	 * @param selectedValue The selected value.
	 */
	public void checkSelectionType(String lineRead, Object selectedValue) {
		if (selectedValue != null) {
			String[] fieldsInLineRead = lineRead.split("(\\*|@)");
			String actualTypeOfSelectedValue = fieldsInLineRead
					[fieldSelectedIndex];

			if (!actualTypeOfSelectedValue.toUpperCase()
					.equals(selectedValue.getClass().getSimpleName().toString()
							.toUpperCase())) {
				Terminate.terminateSelection();
			}
		}
	}
	
	public static int fieldSelectedIndex = -1;

}