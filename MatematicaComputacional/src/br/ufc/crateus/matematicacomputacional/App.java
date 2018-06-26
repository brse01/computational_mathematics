package br.ufc.crateus.matematicacomputacional;

public class App {

	/**
	 * e^x
	 * 
	 * @param x
	 * @return
	 */

	private static float funcao(double x) {
		return (float) Math.pow(Math.E, x);
	}

	// x^2 - 9
	private static double derivada(double x) {
		return 3 * Math.pow(x, 2) - 9;
	}

	// CALCULAR A FUNÇÃO QUE ESTEJA TENTANDO APROXIMAR.
	/**
	 * 
	 * @param x
	 *            valor do x que deseja aplicar a função f
	 * @return retorna o valor do x aplicado na função f
	 */
	private static float f(float x) {
		return (float) (Math.pow(x, 3) - x - 4);
	}

	private static double f(double x) {
		return (double) (Math.pow(x, 3) - x - 4);
	}

	private static float posicaoFalsa(float a, float b, float err) {
		float fa, fb, c, fc;
		fa = f(a);
		fb = f(b);
		c = (b * f(a) - f(b) * a) / (f(a) - f(b));
		fc = f(c);
		while (Math.abs(f(c)) > err) {
			c = (b * f(a) - f(b) * a) / (f(a) - f(b));
			fc = f(c);
			if (fa * fc < 0) {
				b = c;
				fb = fc;
			} else {
				a = c;
				fa = fc;
			}
		}
		return c;
	}

	public static void metodoNewton(double x0, double e1, double e2) {

		if (Math.abs(f(x0)) < e1)
			System.out.println("faça y = " + x0);

		else {
			double x1 = x0 - (f(x0) / derivada(x0));

			while (Math.abs(f(x1)) > e1 || Math.abs(x1 - x0) > e2) {

				x1 = x0 - (f(x0) / derivada(x0));
				x0 = x1;
			}
			System.out.println("faça y = " + x1 + " - " + f(x1));
		}
	}

	public static void trapezio(float a, float b, int m) {
		float h = (b - a) / m;
		float soma = funcao(a);
		for (int i = 1; i < m; i++) {
			soma += 2 * funcao(h * i);
		}
		soma += funcao(b);
		System.out.println("Resultado: " + (h * soma) / 2);
	}

	public static void simpson13(float b, float a, int m) {
		float h = (b - a) / m;

		float soma = funcao(a);

		int i;
		for (i = 1; i < m - 2; i += 2)
			soma += 4 * funcao(h * i) + 2 * funcao(h * (i + 1));

		soma += 4 * funcao(h * i) + funcao(b);

		System.out.println("Resultado: " + (soma * h) / 3);
	}

	public static void gauss_Legendre(float a0, float b0, float t0, float p0, int maxInteration) {
		float an, bn, tn, pn, aux;
		for (int i = 0; i < maxInteration; i++) {
			an = (a0 + b0) / 2;
			bn = (float) Math.sqrt(a0 * b0);
			aux = a0 - an; // evita o uso de pow(a, 2)
			tn = t0 - p0 * aux * aux;

			// atualizarção das variáveis
			p0 = 2 * p0;
			a0 = an;
			b0 = bn;
			t0 = tn;
		}
		aux = (a0 + b0) / 2;
		System.out.println("Resultado é " + (aux * aux / t0));

	}
}
