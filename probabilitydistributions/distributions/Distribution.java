package distributions;

public interface Distribution {
	/* probability function */
	public double prob(int x);
	/* cumulative probability function */
	public double cprob(int x);
}
