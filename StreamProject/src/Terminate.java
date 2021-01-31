/**
 * This class holds the print error messages, and terminates accordingly.
 * 
 * @author Maryam Abdool 
 *
 */
public class Terminate {
	/**
	 * This method terminates the program and displays that the file is not 
	 * properly formatted to the user.
	 */
	public static void terminate() {
		System.out.println(" ");
		System.out.println("File does not have proper form: input file does not" 
		+ "equal to output file.");
		System.exit(0);
	}

	/**
	 * This method terminates the program and displays that the selection is 
	 * invalid in addition to the fact that the file is not properly formatted 
	 * to the user.
	 */
	public static void terminateSelection() {
		System.out.println(" ");
		System.out.println("Selection is invalid.");
		System.out.println("File does not have proper form: input file does not" 
		+ "equal to output file.");
		System.exit(0);
	}
	
	/**
	 * This method terminates the program and displays that the terminal is 
	 * invalid in addition to the fact that the file is not properly formatted 
	 * to the user.
	 */
	public static void terminateTermination() {
		System.out.println(" ");
		System.out.println("Terminal is invalid.");
		System.out.println("File does not have proper form: input file does not" 
		+ "equal to output file.");
		System.exit(0);
	}
}
