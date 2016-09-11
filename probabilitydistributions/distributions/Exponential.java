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

	@Override
	public double mean() {
		return 1/lambda;
	}

	@Override
	public double variance() {
		return 1/(lambda*lambda);
	}

}
