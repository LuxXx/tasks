package distributions;

public class Geometric implements Distribution {
	
	private double pi;
	
	public Geometric(double pi) {
		super();
		this.pi = pi;
	}

	@Override
	public double prob(int x) {
		return Math.pow(1-pi, x-1)*pi;
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
		return 1/pi;
	}

	@Override
	public double variance() {
		return (1-pi)/(pi*pi);
	}

}
