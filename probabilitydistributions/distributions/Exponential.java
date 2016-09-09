package distributions;

public class Exponential implements Distribution {

	private double lambda;
	
	public Exponential(double lambda) {
		super();
		this.lambda = lambda;
	}

	@Override
	public double prob(int x) {
		return 0;
	}

	@Override
	public double cprob(int x) {
		return 1-Math.exp(-lambda*x);
	}

}
