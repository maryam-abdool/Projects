/**
 * The purpose of this class is to record the user’s need in a data structure
 * using the Builder pattern.
 * 
 * @author Maryam Abdool
 *
 */
public class TSVFilter {

	/**
	 * This is a constructor that constructs a TSVFilter object and initializes 
	 * the variables dealing with the file name as well as the rest of the 
	 * required and optional variables.
	 * 
	 * @param inFile File Name.
	 */
	private TSVFilter(WhichFile inFile) {
		fileNameWithExtension = inFile.fileNameWithExtension;
		fileName = fileNameWithExtension.substring(0, 
				fileNameWithExtension.lastIndexOf("."));
		fileExtension = fileNameWithExtension.substring(
				fileNameWithExtension.lastIndexOf("."));
		selectName = inFile.selectName;
		selectStringValue = inFile.selectStringValue;
		selectLongValue = inFile.selectLongValue;
		terminateName = inFile.terminateName;
		terminalComputation = inFile.terminalComputation;
	}

	/**
	 * This method is a getter for the file name with extension.
	 * 
	 * @return File name with extension.
	 */
	public String getFileNameWithExtension() {
		return fileNameWithExtension;
	}

	/**
	 * This method is a getter for the file name.
	 * 
	 * @return File name.
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * This method is a getter for the file extension.
	 * 
	 * @return File extension.
	 */
	public String getFileExtension() {
		return fileExtension;
	}

	/**
	 * This method is a getter for the selected name by the user.
	 * 
	 * @return Selected name.
	 */
	public String getSelectName() {
		return selectName;
	}

	/**
	 * This method is a getter for the selected string value.
	 * 
	 * @return Selected string value.
	 */
	public String getSelectStringValue() {
		return selectStringValue;
	}

	/**
	 * This method is a getter for the selected long value.
	 * 
	 * @return Selected long value.
	 */
	public Long getSelectLongValue() {
		return selectLongValue;
	}
	
	public String getTerminateName() {
		return terminateName;
	}
	
	public TerminalComputation getTerminalComputation() {
		return terminalComputation;
	}

	/**
	 * This method overrides the toString() method.
	 */
	@Override
	public String toString() {
		return "TSVFilter " 
				+ ": fileNameWithExtension = " + fileNameWithExtension + "\n" 
				+ ", selectName = " + selectName 
				+ ", selectStringValue = " + selectStringValue 
				+ ", selectLongValue = " + selectLongValue + "\n"
				+ ", terminateName = " + terminateName
				+ ", terminalComputation = " + terminalComputation + "\n";
				
	}

	/**
	 * This is an inner static class to set the optional values and construct 
	 * the TSVFilter class.
	 * 
	 * @author Maryam Abdool / mra2174
	 *
	 */
	public static class WhichFile {

		/**
		 * This is a constructor that acts as a builder for the required 
		 * parameters, which in this case is the file name with extension.
		 * 
		 * @param inFileNameWithExtension File name with extension.
		 */
		public WhichFile(String inFileNameWithExtension) {
			fileNameWithExtension = inFileNameWithExtension;
			fileName = fileNameWithExtension.substring(0, 
					fileNameWithExtension.lastIndexOf("."));
			fileExtension = fileNameWithExtension.substring(
					fileNameWithExtension.lastIndexOf("."));
		}

		/**
		 * This is a builder method for setting optional properties (which in 
		 * this case is the select properties that are string in nature), 
		 * and then returning an instance of the class WhichFile.
		 * 
		 * @param inSelectName        Selected name.
		 * @param inSelectStringValue Selected String value.
		 * @return Instance of the class WhichFile.
		 */
		public WhichFile select(String inSelectName, String inSelectStringValue) 
		{
			selectName = inSelectName;
			selectStringValue = inSelectStringValue;
			return this;
		}

		/**
		 * This is a builder method for setting optional properties (which in 
		 * this case is the select properties that are long in nature), and then 
		 * returning an instance of the class WhichFile.
		 * 
		 * @param inSelectName      Selected name.
		 * @param inSelectLongValue Selected long value.
		 * @return Instance of the class WhichFile.
		 */
		public WhichFile select(String inSelectName, long inSelectLongValue) {
			selectName = inSelectName;
			selectLongValue = (long) inSelectLongValue;
			return this;
		}
		
		public WhichFile terminate(String inTerminateName, TerminalComputation 
				inTerminalCompuation) {
			terminateName = inTerminateName;
			terminalComputation = inTerminalCompuation;
			return this;
		}

		/**
		 * Builder method to return the full object: makes inner object into 
		 * outer object.
		 * 
		 * @return TSVFilter object.
		 */
		public TSVFilter done() {
			return new TSVFilter(this);
		}

		private final String fileNameWithExtension;
		private final String fileName;
		private final String fileExtension;

		private String selectName;
		private String selectStringValue;
		private Long selectLongValue;
		private String terminateName;
		private TerminalComputation terminalComputation;
	}

	private final String fileNameWithExtension;
	private final String fileName;
	private final String fileExtension;

	private final String selectName;
	private final String selectStringValue;
	private final Long selectLongValue;
	private final String terminateName;
	private final TerminalComputation terminalComputation;

}