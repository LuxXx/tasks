/**
 * 
 * @author LuxXx
 * https://github.com/LuxXx
 */
public class Regression {
	
	private double[] x; // independent variable, regressand
	private double[] y; // dependent variable, regressor
	private double slope;
	private double intercept;
	
	public Regression(double[] x, double[] y) throws Exception {
		
		if (x.length != y.length) throw new Exception("Data set does not match");
		
		this.x = x;
		this.y = y;
		
		slope = slope();
		intercept = intercept();
		
	}
	
	private double slope() {
		return sampleCoVariance(x, y)/sampleVariance(x);
	}
	
	private double intercept() {
		return sampleMean(y) - slope()*sampleMean(x);
	}
	
	public double getSlope() {
		return slope;
	}

	public double getIntercept() {
		return intercept;
	}

	public static double sampleCoVariance(double[] x, double[] y) {
		double sxy = 0;
		double meanx = sampleMean(x);
		double meany = sampleMean(y);
		for (int i = 0; i < x.length; i++) {
			sxy += (x[i] - meanx)*(y[i] - meany);
		}
		return sxy/x.length;
	}
	
	public static double sampleVariance(double... d) {
		double s2 = 0;
		double mean = sampleMean(d);
		
		for (int i = 0; i < d.length; i++) {
			s2 += (d[i] - mean)*(d[i] - mean);
		}
		return s2/d.length;
	}
	
	public static double sampleMean(double... d) {
		double sampleMean = 0;
		for (int i = 0; i < d.length; i++) {
			sampleMean+=d[i];
		}
		return sampleMean/d.length;
	}
}
