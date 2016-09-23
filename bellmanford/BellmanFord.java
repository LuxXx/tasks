/**
 * finding for every vertex to any other vertex a shortest path in a graph using the bellman ford algorithm
 * @author LuxXx
 * https://github.com/LuxXx
 */
public class BellmanFord {
	private static final double INF = Double.POSITIVE_INFINITY;	
	
	/**
	 * This is an implementation of the bellman multiplication
	 * @param a the first matrix
	 * @param b the first matrix
	 * @return a new array that is the result of the multiplication
	 */
	public static double[][] bellmanMult(double[][] a, double[][] b) {
		double[][] r = copy(a);
		
		for (int i = 0; i < r.length; i++) {
			for (int j = 0; j < r[i].length; j++) {
				double[] x = new double[a.length];
				for (int k = 0; k < x.length; k++) {
					x[k] = a[i][k] + b [k][j];
				}
				r[i][j] = min(x);
			}
		}
		return r;
	}
	/**
	 * executes the bellman algorithm to find a shortest path in a graph
	 * make sure this has matrix is quadratic
	 * @param b a matrix with the weights from vertex i to vertex j if there is no edge between those vertices use Double.POSITIVE_INFINITY
	 * @return a new array that contains b.length-1 matrices for each bellman multiplication step
	 */
	public static double[][][] belmannFord(double[][] b) {
		double[][][] u = new double[b.length - 1][][];
		u[0] = copy(b);
		
		for (int i = 1; i < u.length; i++) {
			u[i] = bellmanMult(u[i-1], u[0]);
		}
		
		return u;
	}
	
	public static void main(String[] args) {
		double[][] b = {
				
				{0	,	-1,		2,		INF,	INF},
				{INF,	0,		2,		3,		INF},
				{INF,	INF,	0,		INF,	1},
				{-1	,	INF,	0,		0,		INF},
				{INF,	INF,	INF,	1,		0}
		};
		double[][][] sol = belmannFord(b);
		print(sol);
	}
	
	// down here are some helper functions: min, print and deep copy
	
	public static void print(double[][][] a) {
		for (int i = 0; i < a.length; i++) {
			print(a[i]);
			System.out.println();
		}
	}
	
	public static void print(double[][] a) {
		for (int i = 0; i < a.length; i++) {
			print(a[i]);
		}
	}
	
	public static void print(double[] a) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < a.length; i++) {
			sb.append(a[i]);
			if (i != a.length - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		System.out.println(sb.toString());
	}
	
	/**
	 * creates a deepcopy of a
	 * @param a a double array
	 * @return deep copy of a
	 */
	public static double[] copy(double[] a) {
		double[] r = new double[a.length];
		for (int i = 0; i < r.length; i++) {
			r[i] = a[i];
		}
		return r;
	}

	/**
	 * creates a deepcopy of a
	 * @param a a double matrix
	 * @return deep copy of a
	 */
	public static double[][] copy(double[][] a) {
		double[][] r = new double[a.length][];
		for (int i = 0; i < r.length; i++) {
			r[i] = copy(a[i]);
		}
		return r;
	}
	/**
	 * returns the smallest value of the input array
	 * @param d an varags array
	 * @return the smallest value of the input array
	 */
	public static double min(double... d) {
		double min = Double.POSITIVE_INFINITY;
		for (int i = 0; i < d.length; i++) {
			if (d[i] < min) {
				min = d[i];
			}
		}
		return min;
	}
	
}
