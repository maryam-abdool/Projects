/**
 * This is an enum that represents the terminal computations.
 * 
 * @author Maryam Abdool
 *
 */

public enum TerminalComputation {
	MEANVAL {
		@Override
		public void print() {
			System.out.println("The mean of the chosen values is " + meanVal 
					+ ".");
		}
	}, STDVAL {
		@Override
		public void print() {
			System.out.println("The standard deviation of the chosen values is " 
					+ stdVal + ".");
		
		}
	}, MEANLENGTH {
		@Override
		public void print() {
			System.out.println("The mean of the chosen String (length) is " 
					+ meanLength + ".");
			
		}
	}, STDLENGTH {
		@Override
		public void print() {
		System.out.println("The standard deviation of the chosen String "
				+ "(length) is " + stdLength + ".");
			
		}
	};
	
	double meanVal;
	double stdVal;
	double meanLength;
	double stdLength;
    public abstract void print();

}