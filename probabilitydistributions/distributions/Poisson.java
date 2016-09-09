package distributions;

public class Poisson implements Distribution {
	private double lambda;
	
	public Poisson(double lambda) {
		super();
		this.lambda = lambda;
	}

	@Override
	public double prob(int x) {
		return (Math.pow(lambda, x)/Utility.fact(x))*Math.exp(-lambda);
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
