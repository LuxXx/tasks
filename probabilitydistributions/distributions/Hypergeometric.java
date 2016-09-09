package distributions;

public class Hypergeometric implements Distribution {
	private int n;
	private int N;
	private int M;
	/**
	 * 
	 * @param n Stichprobenumfang
	 * @param N Umfang der Grundgesamtheit
	 * @param M Elemente mit Eigenschaft
	 */
	public Hypergeometric(int n, int N, int M) {
		super();
		this.n = n;
		this.N = N;
		this.M = M;
	}

	@Override
	public double prob(int x) {
		return (double) (Utility.choose(M, x)*Utility.choose(N-M, n-x))/Utility.choose(N, n);
	}

	@Override
	public double cprob(int x) {
		double sum = 0;
		for (int i = 0; i <= x; i++) {
			sum += prob(i);
		}
		return sum;
	}

}
