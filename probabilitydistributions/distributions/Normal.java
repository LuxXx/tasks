package distributions;

public class Normal implements Distribution {
	private double ssq;
	private double m;
	
	public Normal(double ssq, double m) {
		super();
		this.ssq = ssq;
		this.m = m;
	}

	@Override
	public double prob(int x) {
		return 0;
	}

	@Override
	public double cprob(int x) {
		return 0.5*Utility.erfc((m-x)/Math.sqrt(2*ssq));
	}

}
