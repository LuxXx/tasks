package distributions;

public class Utility {
	public static long choose(int n, int k) {
		long r = 1;
		if (k > n) {
			return 0;
		}
		for (int i = 1; i <= k; i++) {
			r *= n--;
			r /= i;
		}
		return r;
	}
	public static long fact(final int n) {
		if (n < 0) {
			System.err.println("No negative numbers");
			return 0;
		}
		long ans = 1;
		for (int i = 1; i <= n; i++) {
			ans *= i;
		}
		return ans;
	}
	public static double erf(double z) {
		double t = 1.0 / (1.0 + 0.47047 * Math.abs(z));
		double poly = t * (0.3480242 + t * (-0.0958798 + t * (0.7478556)));
		double ans = 1.0 - poly * Math.exp(-z*z);
		if (z >= 0) return  ans;
		else        return -ans;
	}
	public static double erfc(double z) {
		return 1-erf(z);
	}
}
