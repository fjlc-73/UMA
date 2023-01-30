
import es.uma.ada.backtracking.Backtracking;
import es.uma.ada.datastructures.tuple.Pair;
import es.uma.ada.problem.combinatorial.puzzle.latinsquare.LatinSquare;

/**
 * Backtracking for latinSquare
 * 
 * @author ccottap
 *
 */
public class LatinSquareBacktracking extends Backtracking {
	/**
	 * the Latin square to solve
	 */
	protected LatinSquare latinSquare;

	/**
	 * Creates the solver
	 */
	public LatinSquareBacktracking() {
		super();
		latinSquare = null;
	}

	/**
	 * Creates the solver with a specific puzzle
	 * 
	 * @param latinSquare a Latin square puzzle
	 */
	public LatinSquareBacktracking(LatinSquare latinSquare) {
		super();
		setPuzzle(latinSquare);
	}

	@Override
	protected Object initialState() {
		return new Pair<Integer,Integer>(0,0);
		// The initial state is a pair with the coordinates of the 
		// first position, namely the upper left corner (0,0)
		
		//------------- TODO ------------- 
		
		//-------------------------------- 
	}
	
	private Pair<Integer, Integer> previous(Pair<Integer, Integer> p){
		int i = p.getFirst();
		int j = p.getSecond();
		
		if(j==0) {
			i--;
			j = latinSquare.getSize()-1;
		} else {
			j--;
		}
		
		return new Pair<Integer, Integer>(i,j);
	}

	@Override
	protected boolean backtracking(Object state) {
		@SuppressWarnings("unchecked")
		Pair<Integer, Integer> p = (Pair<Integer, Integer>) state;
		int i = p.getFirst();
		int j = p.getSecond();
		boolean ok;
		int n = latinSquare.getSize();
		
		ok = true;
		
		int k = i;
		int l = j;
		
		while(k<n) {
			while (l<n) {
				if(!latinSquare.isFixed(k,l)) {
					int v = latinSquare.get(k, l);
					if(v == -1) {
						v=1;
					}
					while(v <= n && !latinSquare.test(k, l, v)) {
						v++;
					}
					if(v<=n) {
						ok = true;
						latinSquare.set(k, l, v);
					} else {
						latinSquare.set(k, l, -1);
						Pair<Integer, Integer> pp = previous(new Pair<Integer, Integer>(k,l));
						if(pp.getFirst() == -1) {
							return false;
						}
						ok = false;
						k = pp.getFirst();
						l = pp.getSecond()-1;
					}
					
				} else {
					int v = latinSquare.get(k, l);
					ok = !latinSquare.test(k, l, v) && ok;
					if(!ok) {
						Pair<Integer, Integer> pp = previous(new Pair<Integer, Integer>(k,l));
						if(pp.getFirst() == -1) {
							return false;
						}
						k = pp.getFirst();
						l = pp.getSecond()-1;
					}
				
				}
				this.nodes++;
				l++;
			}
			l=0;
			k++;
		}
		
		// The algorithm must try to fill the table row by 
		// row, from left to right.
		// If a position is unspecified, the algorithm must check which
		// values are feasible for that position and continue recursively.
		// If a position is fixed, the algorithm must still check if that
		// value is feasible, because the initial instance might be unsolvable.

		//------------- TODO ------------- 
		
		
		
		//-------------------------------- 

		
		return ok;

	}

	/**
	 * Returns the internal Latin square
	 * 
	 * @return the internal Latin square
	 */
	public LatinSquare getPuzzle() {
		return latinSquare;
	}

	/**
	 * Sets the Latin square. The parameter is internally cloned, so it remains
	 * independent from the solver.
	 * 
	 * @param latinSquare the Latin square
	 */
	public void setPuzzle(LatinSquare latinSquare) {
		this.latinSquare = latinSquare.clone();
		if (verbosity) {
			System.out.println(this.latinSquare);
		}
	}

	@Override
	public String getName() {
		return "Latin-square backtracking";
	}

}
