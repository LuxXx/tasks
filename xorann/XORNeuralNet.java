import java.util.ArrayList;

public class XORNeuralNet {
	
	private Neuron or;
	private Neuron and;
	private Neuron not;
	
	public static void main(String[] args) {
		XORNeuralNet nn = new XORNeuralNet();
		System.out.println(nn.evaluate(-1, -1));
	}
	
	public XORNeuralNet() {
		or = new Neuron(2, 0.2);
		and = new Neuron(2, 0.2);
		not = new Neuron(1, 0.2);
		
		ArrayList<Example> ors = new ArrayList<>();
		ArrayList<Example> ands = new ArrayList<>();
		ArrayList<Example> nots = new ArrayList<>();
		
		for (int i = 0; i < 10000; i++) {
		
			double r1 = rand();
			double r2 = rand();
			
			Example orEx = new Example(or(r1, r2), r1, r2);
			ors.add(orEx);
			
			Example andEx = new Example(and(r1, r2), r1, r2);
			ands.add(andEx);
			
			Example notEx = new Example(not(r1), r1);
			
			nots.add(notEx);
		}
		
		or.train(ors.toArray(new Example[ors.size()]));
		and.train(ands.toArray(new Example[ands.size()]));
		not.train(nots.toArray(new Example[nots.size()]));	
	}
	
	public double evaluate(double i1, double i2) {
		double a_or_b = or.evaluate(i1, i2);
		double a_and_b = and.evaluate(i1, i2);
		double not_a_and_b = not.evaluate(a_and_b);
		double xor = and.evaluate(a_or_b, not_a_and_b);
		
		return xor;
	}
	
	
	public static double xor(double i, double i2) {
		if (i == 1.0 ^ i2 == 1.0) return 1.0;
		return -1.0;
	}	
	public static double or(double i, double i2) {
		if (i == 1.0 || i2 == 1.0) return 1.0;
		return -1.0;
	}
	public static double and(double i, double i2) {
		if (i == 1.0 && i2 == 1.0) return 1.0;
		return -1.0;
	}
	public static double not(double i) {
		return -i;
	}
	
	public static double rand() {
		return (Math.random() < 0.5 ? -1.0 : 1.0);
	}
}
