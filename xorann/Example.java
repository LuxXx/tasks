
public class Example {
	
	private double[] inputs;
	private double output;
	
	public Example(double output, double... inputs) {
		
		this.inputs = inputs;
		this.output = output;
	}
	
	public double[] getInput() {
		return inputs;
	}
	
	public double getOutput() {
		return output;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < inputs.length; i++) {
			sb.append(inputs[i]);
			sb.append(" ");
		}
		sb.append("= ");
		sb.append(output);
		return sb.toString();
	}
}
