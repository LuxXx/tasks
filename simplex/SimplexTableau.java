import java.text.DecimalFormat;
/**
 * This file was written for learning purposes
 * Algorithm was written completely out of my head only using knowledge I remeber from university class
 * @author LuxXx
 * https://github.com/LuxXx
 */
public class SimplexTableau {
	
	public static void main(String[] args) {
		
		double[][] canoncialForm = {
				// x1 and x2 are variables
				// x3, x4 and x5 are slack variables
				// bi variables contain the restriction values
				
				// x1, x2, x3, x4, x5, bi
				/*B*/ {1, 1, 1, 0, 0, 6},
				/*A*/ {3, 0, 0, 1, 0, 9},
				/*S*/ {1, -4, 0, 0, 1, 1},
				/*E*/
				/*z*/ {-3, -2, 0, 0, 0, 0},
		};
		
		SimplexTableau tab = new SimplexTableau(canoncialForm);
		System.out.println(tab);
		
		try {
			System.out.println("Trying to solve it!");
			tab.solve();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(tab);
	}
	
	/** the array that contains our tableau**/
	double[][] tableau;
	
	/**
	 * @param tableau the initial tableau. During object creation a deep copy will be created.
	 */
	public SimplexTableau(double[][] tableau) {
		
		this.tableau = new double[tableau.length][tableau[0].length];
		
		for (int i = 0; i < tableau.length; i++) {
			for (int j = 0; j < tableau[i].length; j++) {
				this.tableau[i][j] = tableau[i][j];
			}
		}
	}
	/**
	 * solves this tableau
	 * @throws Exception will be thrown if tableau can't be solved
	 */
	public void solve() throws Exception {
		while(!isSolved()) {
			nextIteration();
		}
	}
	/**
	 * 
	 * @return true if tableau is already optimal, false if not
	 */
	public boolean isSolved() {
		double[] z = tableau[tableau.length-1];
		for (int i = 0; i < z.length - 1; i++) { // -1 because we want to skip the bi entry
			if (z[i]  < 0) return false;
		}
		return true;
	}
	
	/**
	 * do an iteration of the simplex algorithm
	 * @throws Exception
	 */
	private void nextIteration() throws Exception {
		int pivotRow = findPivotRowIndex(tableau[tableau.length-1]);
		int pivotColumn = 0;
		
		// find the pivot column
		// TODO: Consider case where all the entries are zero for the pivot column search
		for (int i = 0; i < tableau.length; i++) {
			if (tableau[i][pivotRow] <= 0) continue;
			if (tableau[i][tableau[i].length - 1] <= 0) {
				throw new Exception("This is a dual problem");
			}
			double lowest = Double.MAX_VALUE;
			if (tableau[i][tableau[i].length - 1]/tableau[i][pivotRow] < lowest) {
				lowest = tableau[i][tableau[i].length - 1]/tableau[i][pivotRow];
				pivotColumn = i;
			}
		}
		
		// new blank tableau
		double[][] nTableau = new double[tableau.length][tableau[0].length];

		// this stores all the factors that lead to the unit vector
		double[] factor = new double[tableau.length];
		
		// find the factors for each column that lead to the unit vector
		for (int i = 0; i < factor.length; i++) {
			int unitVectorValue = 0;
			
			// one in unit vector
			if (i == pivotColumn) unitVectorValue = 1;
			
			factor[i] = (unitVectorValue - tableau[i][pivotRow]) / tableau[pivotColumn][pivotRow];

		}
		
		// set new values
		for (int i = 0; i < nTableau.length; i++) {
			for (int j = 0; j < nTableau[i].length; j++) {
				nTableau[i][j] = tableau[i][j] + factor[i] * tableau[pivotColumn][j];
			}
		}
		tableau = nTableau;
	}
	/**
	 * Get the id of the smallest value in this array
	 * @return the id of the smallest value in this array
	 */
	private static int findPivotRowIndex(double[] a) {
		double lowest = Double.MAX_VALUE;
		int lowestIndex = 0;
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i] < lowest) {
				lowest = a[i];
				lowestIndex = i;
			}
		}
		return lowestIndex;
	}
	
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("000.000");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tableau.length; i++) {
			for (int j = 0; j < tableau[i].length; j++) {
				sb.append("|");
				if (tableau[i][j] >= 0) sb.append(" ");
				sb.append(df.format(tableau[i][j]).replace(",", "."));
			}
			if (i != tableau.length-1) sb.append("\n");
		}

		return sb.toString();
	}
}
