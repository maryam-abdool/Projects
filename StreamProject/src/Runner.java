/**
 * Summary of the system:
 * The system runs terminal operations (computations) on the records — if they
 * are correctly formatted — the user selected to get piped to the output 
 * stream.
 * 
 * Broad overview of class connections: 
 * The runner class stores the user's requests in the TSVFilter class, which
 * is essentially a data structure that utilizes the builder pattern. The
 * TSVPipeline class then utilizes the data to fulfill the purposes of the
 * system (Pipes and Filter Pattern). The other classes in the system are 
 * helper classes. 
 * 
 * Understanding test results:
 * There are five examples in the file that show the output of running the 
 * system:
 * Example 1: No Select, No Terminate.
 * Example 2: Select Only, No Terminate.
 * Example 3: Terminate only, No Select.
 * Example 4: Select and Terminate
 * Example 5: Corner Case: Mismatch between the type of terminal computation and
 * the field chosen
 * 
 * Test results details:
 * To be specific, each example has a tsv file (input stream) and output 
 * tsv file (output stream). Each input and output file have the same name,
 * with the output file having the word "Output" appended to it to distinguish 
 * it from the input file. Furthermore, each example has an analogous console 
 * output that matches the pair of input and output file, which is collectively 
 * saved in a text file. As for the corner cases, only one example was provided
 * in the folder; however, all corner-cases and errors have been handled and
 * documented. Only one corner case was provided as a sample, as the there are
 * numerous and countless corner cases and errors. Other than the documentation
 * of the corner cases and errors, they can also be proven when running the
 * system. 
 * 
 * 
 * @author Maryam Abdool
 *
 */
public class Runner {

	public static void main(String[] args) {
		TSVFilter myTSVFilter = new TSVFilter.WhichFile("SelectAndTerminate.tsv")
				.select("Zip Code", 10027)
				.terminate("Name", TerminalComputation.STDLENGTH)
				.done();
		System.out.println(myTSVFilter);
		
		new TSVPipeline(myTSVFilter).doit();
	}
}
