import java.util.ArrayList;

public class Perceptron {
	private double input_0 = -1.0;
	
	private double weight_0 = 1.0;
	private double weight_1 = 1.0;
	private double weight_2 = 1.0;
	
	private ArrayList<Example> examples;
	
	private double delta = 0.2; // learning rate
	
	public Perceptron() {
		this.examples = new ArrayList<>();
		// Get 10000 examples for the training set
		for (int i = 0; i < 10000; i++) {
			examples.add(Example.values()[(int) (Math.random()*Example.values().length)]);
		}
		train();
	}
	
	private void train() {
		for (Example example : examples) {
			if (evaluate(example.getInput_1(), example.getInput_2()) != example.getOutput() ) {
				weight_0 = weight_0 + delta * example.getOutput() * input_0;
				weight_1 = weight_1 + delta * example.getOutput() * example.getInput_1();
				weight_2 = weight_2 + delta * example.getOutput() * example.getInput_2();
			}
		}
	}
	
	public double evaluate(double input_1, double input_2) {
		return sig(weight_0 * input_0 + weight_1 * input_1 + weight_2 * input_2);
	}
	
	public static void main(String[] args) {
		Perceptron n = new Perceptron();
		
		// do a test
		double input_1 = -1.0;
		double input_2 = 1.0;
		double result = n.evaluate(input_1, input_2);
		System.out.println("Input: " + input_1 + " " + input_2 + " Neural Network says: " + result);
	}
	
	/**
	 * An enum for all examples, true - true, true - false, false - true, false, false
	 */
	private enum Example {
		T_T(1.0, 1.0, 1.0), T_F(1.0, -1.0, 1.0), F_T(-1.0, 1.0, 1.0), F_F(-1.0, -1.0, -1.0); // or
		//T_T(1.0, 1.0, 1.0), T_F(1.0, -1.0, -1.0), F_T(-1.0, 1.0, -1.0), F_F(-1.0, -1.0, -1.0); // and
		private double input_1;
		private double input_2;
		private double output;
		private Example(double input_1, double input_2, double output) {
			this.input_1 = input_1;
			this.input_2 = input_2;
			this.output = output;
		}
		public double getInput_1() {
			return input_1;
		}
		public double getInput_2() {
			return input_2;
		}
		public double getOutput() {
			return output;
		}
	}
	public static double sig(double d) {
		return (d > 0 ? 1.0 : -1.0);
	}
}
