import es.uma.ada.problem.combinatorial.sequence.rearrangement.Reversal;
import es.uma.ada.problem.combinatorial.sequence.rearrangement.Permutation;
import es.uma.ada.problem.combinatorial.sequence.rearrangement.SequenceRearrangementAlgorithm;

/**
 * Implementation of Prefix sorting as a rearrangement method on unsigned
 * permutations
 * 
 * @author ccottap
 *
 */
public class PrefixSort extends SequenceRearrangementAlgorithm {

	/**
	 * Default constructor
	 */
	public PrefixSort() {
	}

	@Override
	protected void _run(Permutation l) {
		int n = l.size();
		
		for(int i = 0; i<n; i++) {
			if(l.get(i)!=i+1) {
				int j = l.indexOf(i+1);
				
				Reversal r = new Reversal(i,j);
				r.apply(l);
				
				numOperations++;
			}
			
		}
		
		// This method implements PrefixSort.
		// See the slides for a description.
		// Remember to increase the counter numOperations
		// each time a reversal is made.
		
		//------------- TO COMPLETE ------------- 
		
		//--------------------------------------- 


	}

	@Override
	public String getName() {
		return "PrefixSort";
	}

	@Override
	public boolean isSignedCompatible() {
		return false;
	}

}
