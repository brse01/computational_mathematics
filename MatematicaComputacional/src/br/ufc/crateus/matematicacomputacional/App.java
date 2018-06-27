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

	private static double function(double x) {
		return Math.pow(2.718281828459045, x) - x - 2.0;
	}

	public static int get_max_inte(double e_0, double e) {
		return (int) (Math.abs(Math.log10(e_0 / e) / Math.log10(2.0)) + 1);
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

	public static double bisection(double a, double b, double e) {
		int i = 0;
		int max = get_max_inte(b - a, e);
		while (b - a >= e && i < max) {
			double f_a = function(a);
			double f_b = function(b);
			double mid = a + (b - a) / 2;
			double f_mid = function(mid);

			if (f_a * f_mid < 0)
				b = mid;
			else if (f_mid * f_b < 0)
				a = mid;
			else
				return mid;

			i++;
			System.out.println("Iteração= " + i + " mid= " + mid + " f_mid= " + f_mid + " b-a= " + (b - a));
		}
		return a + (b - a) / 2;
	}

	public static void metodoNewton(double x0, double e1, double e2) {

		if (Math.abs(f(x0)) < e1)
			System.out.println("faça y = " + x0);

		else {
			int k = 0;

			double x1 = x0 - (f(x0) / derivada(x0));

			while (Math.abs(f(x1)) > e1 || Math.abs(x1 - x0) > e2) {

				x1 = x0 - (f(x0) / derivada(x0));
				x0 = x1;
				k++;
			}
			System.out.println("faça y = " + x1 + " - " + f(x1));
		}
	}

	public static void secant(double x0, double x1, double e1, double e2){
		if (Math.abs(funcao(x0)) < e1)
			System.out.println("faça y = " + x0);
		else if (Math.abs(funcao(x1)) < e1 || Math.abs(x1 - x0) < e2)
			System.out.println("faça y = " + x1);
		else {
			int k = 1;

			double x2 = 0.0;

			do {

				x2 = x1 - (funcao(x1) / (funcao(x1) - funcao(x0))) * (x1 - x0);

				x0 = x1;
				x1 = x2;

				k++;
			} while (Math.abs(funcao(x2)) > e1 || Math.abs(x2 - x1) > e2);

			System.out.println("então faça y = " + x2);
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

	public static void eliminacaoGauusPivotiamentoParcial(double MatCoeficientes[][], double vectConstantes[],
			double vectorResultados[], int size) {

		EliminacaoGaussPivoParcial MeuSistema = new EliminacaoGaussPivoParcial(MatCoeficientes, vectConstantes,
				vectorResultados, size);

		System.out.println("Matriz A e vetor B Iniciais.");

		MeuSistema.MostrarAB(); // Mostra a matriz
		MeuSistema.EliminacaoGauss(); // Faz a eliminação
		System.out.println("Matriz A e vetor B após eliminação de Gauss.");
		MeuSistema.MostrarAB(); // Mostra a matriz novamenete agora como matriz
								// triangular superior

		MeuSistema.ResolucaoDoSistema(); // Resolve o sistema ou seja calcula os
											// X's
		MeuSistema.MostrarResultados(); // Mostra os resultados encontrados para
										// os X's
	}

}
