public class MonteCarloPi {
	private static long attempts = 30000000;
	
	private static double pi() {
		
		long attemptsInCircle = 0;
		
		for (long i = 0; i < attempts; i++) {
			double x = Math.random();
			double y = Math.random();
			
			if (Math.sqrt(x*x+y*y) < 1)
				attemptsInCircle++;
		}
		
		return (double) 4*attemptsInCircle / attempts;
	}
	public static void main(String[] args) {
		double pi = pi();
		System.out.println(pi);
		System.out.println("Error: " + (Math.PI - pi));
	}
}
