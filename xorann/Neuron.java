public class Neuron {
	
	/** the weights of each input. weights[0] is the bias **/
	private double[] weights;
	
	/** the learning rate **/
	private double delta;
	
	/**
	 * the constructor
	 * @param inputs the amount of inputs this neuron has
	 * @param delta the learning rate this neuron learns
	 */
	public Neuron(int inputs, double delta) {
		weights = new double[inputs + 1];
		for (int i = 0; i < weights.length; i++) {
			weights[i] = 1;
		}
		this.delta = delta;
	}
	
	/**
	 * evaluates the input
	 * @param inputs the inputs
	 * @return the output the neuron calculates
	 */
	public double evaluate(double... inputs) {
		if (inputs.length + 1 != weights.length) {
			// TODO: do some exception handling
			System.err.println("the number of given inputs does not match with the number of neuron inputs");
			return Double.NaN;
		}
		
		double sum = -weights[0];
		
		for (int i = 0; i < inputs.length; i++) {
			sum += weights[i + 1] * inputs[i];
		}
		return sig(sum);
		
	}
	
	/**
	 * trains the neuron with a set of given examples
	 * @param examples the given examples
	 */
	public void train(Example[] examples) {
		for (Example example : examples) {
			if (evaluate(example.getInput()) != example.getOutput()) { // perceptron learning rule
				
				weights[0] = weights[0] - delta * example.getOutput();
				
				for (int i = 1; i < weights.length; i++) {
					weights[i] = weights[i] + delta * example.getOutput() * example.getInput()[i - 1];
				}
			}
		}
	}
	
	/**
	 * the signum function
	 * @param d an arbitrary double
	 * @return -1.0 if d below zero, else 1.0
	 */
	public static double sig(double d) {
		return (d > 0 ? 1.0 : -1.0);
	}
}
