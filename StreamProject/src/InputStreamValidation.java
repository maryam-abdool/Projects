/**
 * This class handles the validations for the input stream.
 * 
 * @author Mayram Abdool
 *
 */
public class InputStreamValidation {
	/**
	 * This method has two purposes – to check if a new line has been found, and
	 * to check if the line is properly formatted through checking if it ends 
	 * with '@'.
	 * 
	 * @param lineRead   Current line being read.
	 * @param lineNumber The line number in file.
	 * @return If the line is found and properly formatted.
	 */
	public boolean isLineFound(String lineRead, int lineNumber) {
		boolean isLineFound;

		if (!lineRead.substring(lineRead.length() - 1).equals("@")) {
			isLineFound = false;
			System.out.println("Line " + (lineNumber + 1) 
					+ " cannot be found.");
		} else {
			isLineFound = true;
			System.out.println("Line " + (lineNumber + 1) + " is found.");
		}

		return isLineFound;
	}

	/**
	 * This method counts the number of fields in each line.
	 * 
	 * @param lineRead   Current line being read.
	 * @param lineNumber The line number in file.
	 * @return Number of fields in the line being read.
	 */
	public int countNumberOfFields(String lineRead, int lineNumber) {
		int numberOfFieldsCurrentLine = 0;

		if (lineNumber == 0) {
			requiredNumberOfFields = lineRead.length() 
					- lineRead.replaceAll("\\*", "").length();
			numberOfFieldsCurrentLine = requiredNumberOfFields;

		} else {
			numberOfFieldsCurrentLine = lineRead.length() 
					- lineRead.replaceAll("\\*", "").length();
		}

		System.out.println(
				"Number of fields in line " + (lineNumber + 1) + " is " 
		+ (numberOfFieldsCurrentLine + 1) + ".");

		return numberOfFieldsCurrentLine;
	}

	/**
	 * This method checks whether the current line being read has the same 
	 * number of fields as the first line (because the first line contains the 
	 * headers).
	 * 
	 * @param numberOfFieldsCurrentLine The number of fields in the line being 
	 * read.
	 */
	public void checkLineAgreement(int numberOfFieldsCurrentLine) {
		if (numberOfFieldsCurrentLine != requiredNumberOfFields) {
			System.out.println("Lines are not in agreement.");
			Terminate.terminate();
		} else {
			System.out.println("Lines are in agreement.");
		}
	}

	/**
	 * This method checks that the line does not contain '\t' or '\n'.
	 * 
	 * @param lineRead   Current line being read.
	 * @param lineNumber The line number in file.
	 * @return If the line contains '\t' or '\n'.
	 */
	public boolean isTabAndSpaceFormatted(String lineRead, int lineNumber) {
		boolean isTabAndSpaceFormatted = true;

		if (lineRead.contains("\\t") || lineRead.contains("\\n")) {
			isTabAndSpaceFormatted = false;
			System.out.println("Line improperly formatted: " + "it contains " 
			+ "\\t or \\n ");
			Terminate.terminate();
		}

		return isTabAndSpaceFormatted;
	}

	/**
	 * This method checks that each field is not empty or just a white space, 
	 * but an actual record.
	 * 
	 * @param lineRead Current line being read.
	 */
	public void containsEmptyField(String lineRead) {
		boolean isHeaderFormatted = true;

		String[] fieldNames = lineRead.split("(\\*|@)");

		for (String fieldName : fieldNames) {
			if (fieldName.isEmpty()) {
				System.out.println(fieldName);
				isHeaderFormatted = false;
			}
		}

		if (isHeaderFormatted == false) {
			System.out.println("One or more fields are empty.");
			Terminate.terminate();
		}
	}

	/**
	 * This method checks that the second line contains valid types, and for the
	 * purpose of this system, either a String or long.
	 * 
	 * @param lineRead Current line being read.
	 */
	public void containsAppropriateTypes(String lineRead) {
		boolean isTypeFormatted = true;

		types = lineRead.split("(\\*|@)");

		for (String type : types) {
			if (!type.toUpperCase().equals("STRING") && !type.toUpperCase().equals("LONG")) {
				isTypeFormatted = false;

			}
		}

		if (isTypeFormatted == false) {
			System.out.println("Types improperly formatted: " + "System accepts String or long only.");
			Terminate.terminate();
		}
	}

	/**
	 * This method ensures that each record field matches its corresponding type
	 * specified in the second line.
	 * 
	 * @param lineRead Current line being read.
	 */
	public void typeValidation(String lineRead) {
		String[] fieldNames = lineRead.split("(\\*|@)");

		for (int i = 0; i < fieldNames.length; i++) {
			switch (types[i].toUpperCase()) {
			case "STRING":
				if (fieldNames[i].matches("^[a-zA-Z]+$") == false) {
					System.out.println("Field must be of type String (only " 
				+ "characters are allowed).");
					Terminate.terminate();
				}
				break;
			case "LONG":
				try {
					Long.parseLong(fieldNames[i]);
				} catch (Exception ex) {
					System.out.println("Field must be of type long "
							+ "(Only numbers" + " are allowed).");
					Terminate.terminate();
				}
				break;
			}
		}
	}
	
	private int requiredNumberOfFields;
	private String[] types;

}