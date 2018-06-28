package br.ufc.crateus.matematicacomputacional;

public class GaussLegendre {
				
	
	public static double a(int n) {
		if (n == 0) {
			return 1;
		} else {
			return (a(n - 1) + b(n - 1)) / 2.0;
		}
	}

	public static double b(int n) {
		if (n == 0) {
			return 1.0 / Math.sqrt(2.0);
		} else {
			return Math.sqrt(a(n - 1) * b(n - 1));
		}
	}

	public static double t(int n) {
		if (n == 0) {
			return 1.0 / 4.0;
		} else {
			return t(n - 1) - p(n - 1) * Math.pow(a(n - 1) - a(n), 2);
		}
	}

	public static double p(int n) {
		if (n == 0) {
			return 1;
		} else {
			return 2 * p(n - 1);
		}
	}

	public static double pi(int n) {
		return Math.pow(a(n) + b(n), 2) / (4 * t(n));
	}
	
	public static void main(String[] args) {
		System.out.println(pi(3));
	}
}
