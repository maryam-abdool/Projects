/**
 * This class hold the functionality for the terminal operations.
 * 
 * @author Maryam Abdool 
 *
 */
public class TerminalOperations {
	/**
	 * This method computes the mean (shared functionality between
	 * meanVal and meanLength).
	 * 
	 * @param fieldToCompute The field to compute.
	 * @param lineNumber The line number in file.
	 * @return The mean.
	 */
	private double meanComputation(long fieldToCompute, int lineNumber) {
		sumOfValues += fieldToCompute;
		double mean = (sumOfValues / (double)(lineNumber - 1));
		return mean;
	}
	
	/**
	 * This method computes the standard deviation (shared functionality 
	 * between stdVaL and stdLength).
	 * @param fieldToCompute The field to compute.
	 * @param lineNumber The line number in file.
	 * @return The standard deviation. 
	 */
	private double standardDeviationComputation(long fieldToCompute, 
			int lineNumber) {
		sumOfValues += fieldToCompute;
		sumOfValuesSquared+= (fieldToCompute * fieldToCompute);
		double firstVariable = (sumOfValuesSquared / (double)(lineNumber - 1));
		double secondVariable = (sumOfValues / (double)(lineNumber - 1)) 
				* (sumOfValues / (double)(lineNumber - 1));
		double subtract = firstVariable - secondVariable;
		double standardDeviation = Math.sqrt(subtract);
		return standardDeviation;
	}

	/**
	 * This method computes the mean of type long.
	 * 
	 * @param lineRead Current line being read.
	 * @param lineNumber The line number in file.
	 */
	public void meanVal(String lineRead, int lineNumber) {
		long fieldToCompute = Long.parseLong(selectTerminalField(lineRead));
		TerminalComputation.MEANVAL.meanVal = meanComputation(fieldToCompute,
				lineNumber);
	}
	
	/**
	 * This method computes the standard deviation of type long.
	 * 
	 * @param lineRead Current line being read.
	 * @param lineNumber The line number in file.
	 */
	public void stdVaL(String lineRead, int lineNumber) {
		long fieldToCompute = Long.parseLong(selectTerminalField(lineRead));
		TerminalComputation.STDVAL.stdVal = standardDeviationComputation
				(fieldToCompute, lineNumber);
	}
	
	/**
	 * This method computes the mean of type String.
	 * 
	 * @param lineRead Current line being read.
	 * @param lineNumber The line number in file.
	 */
	public void meanLength(String lineRead, int lineNumber) {
		long fieldToCompute = selectTerminalField(lineRead).length();
		TerminalComputation.MEANLENGTH.meanLength = meanComputation
				(fieldToCompute,lineNumber);
	}
	
	/**
	 * This method computes the standard deviation of type String.
	 * 
	 * @param lineRead Current line being read.
	 * @param lineNumber The line number in file.
	 */
	public void stdLength(String lineRead, int lineNumber) {
		long fieldToCompute = selectTerminalField(lineRead).length();
		TerminalComputation.STDLENGTH.stdLength = standardDeviationComputation
				(fieldToCompute,lineNumber);
	}
	
	/**
	 * This method retrieves the field to be operated on by the terminal.
	 * 
	 * @param lineRead Current line being read.
	 * @return The field to be operated on by the terminal.
	 */
	private String selectTerminalField(String lineRead) {
		String[] fieldsInLineRead = lineRead.split("(\\*|@)");
		return fieldsInLineRead[TerminalValidation.fieldTerminatedIndex];
		
	}
	
	double sumOfValues;
	double sumOfValuesSquared;
}