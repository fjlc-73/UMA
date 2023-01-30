
import java.util.LinkedList;
import java.util.List;

import es.uma.ada.problem.factorization.IntegerFactorization;

/**
 * Factorizes an integer n by exhaustively checking all prime
 * numbers less than or equal to sqrt(n).
 * @author ccottap
 *
 */
public class BruteForceFactorization extends IntegerFactorization {

	/**
	 * Default constructor
	 */
	public BruteForceFactorization () {
		super();
	}
	


	@Override
	public List<Long> _factorize(long n) {
		List<Long> factors = new LinkedList<Long>();
		long i = 3;
		double maxdiv; 
		
		while(n%2 == 0) {
			factors.add((long) 2);
			n = n/2;
		}
		
		maxdiv = Math.sqrt(n);
		while(i <= maxdiv) {
			if(n%i == 0) {
				factors.add(i);
				n = n/i;
				maxdiv = Math.sqrt(n);
			} else {
			i = i+2;
			}
		}
		if(n > 1) {
			factors.add(n);
		}
	
		return factors;
	}


	@Override
	public String getName() {
		return "Brute-force factorization";
	}

}
