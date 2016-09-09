package distributions;

public class Binomial implements Distribution {
	private int n;
	private double pi;
	
	public Binomial(int n, double pi) {
		super();
		this.n = n;
		this.pi = pi;
	}

	@Override
	public double prob(int x) {
		return Utility.choose(n,x)*Math.pow(pi, x)*Math.pow(1-pi, n-x);
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
