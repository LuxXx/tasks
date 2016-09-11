package distributions;

public class NegativeBinomial implements Distribution {
	
	private int r;
	private double pi;
	/**
	 * 
	 * @param r count of failures
	 * @param pi probability of the success
	 */
	public NegativeBinomial(int r, double pi) {
		super();
		this.r = r;
		this.pi = pi;
	}
	
	@Override
	public double prob(int x) {
		return Utility.choose(x+r-1, x)*Math.pow(pi, r)*Math.pow(1-pi, x);
	}

	@Override
	public double cprob(int x) {
		double sum = 0;
		for (int i = 0; i <= x; i++) {
			sum += prob(i);
		}
		return sum;
	}

	@Override
	public double mean() {
		return r/pi;
	}

	@Override
	public double variance() {
		return r*(1-pi)/(pi*pi);
	}

}
