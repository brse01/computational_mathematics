package br.ufc.crateus.matematicacomputacional;

public class App {

	private static double h = 0.0000001;

	// x^3 - 9x + 3
	public static double funcaoSecantNewton(double x) {
		return Math.pow(x, 3) - 9 * x + 3;
	}
	public static float funcaoTrapezio(double x) {
		return (float) Math.pow(Math.E, x);
	}
	// e^x
	public static float funcao(double x) {
		return (float) Math.pow(Math.E, x);
	}

	public static double function(double x) {
		return Math.pow(2.718281828459045, x) - x - 2.0;
	}

	public static int get_max_inte(double e_0, double e) {
		return (int) (Math.abs(Math.log10(e_0 / e) / Math.log10(2.0)) + 1);
	}

	public static double function2(double x) {
		return x * x + 2 * x + 1;
	}

	public static double funcion3(double x) {
		return x - Math.sqrt(7);
	}

	// x^2 - 9
	public static double derivada(double x) {
		return 3 * Math.pow(x, 2) - 9;
	}

	public static double d_derivada(double x) {
		return 2 * x + 2;
	}

	public static double d_d_derivada(double x) {
		return (function2(x + h) - function2(x)) / h;
	}

	// CALCULAR A FUNÇÃO QUE ESTEJA TENTANDO APROXIMAR.
	/**
	 * 
	 * @param x
	 *            valor do x que deseja aplicar a função f
	 * @return retorna o valor do x aplicado na função f
	 */
	public static float f(float x) {
		return (float) (Math.pow(x, 3) - x - 4);
	}

	public static double f(double x) {
		return (double) (Math.pow(x, 3) - x - 4);
	}

	public static double false_position(double a, double b, double episilon_1, double episilon_2) {
		int K_false_position = 0;

		while (b - a >= episilon_1) {
			double f_a = funcion3(a);
			double f_b = funcion3(b);

			if (Math.abs(f_a) < episilon_2)
				return a;
			if (Math.abs(f_b) < episilon_2)
				return b;

			double x = (a * f_b - b * f_a) / (f_b - f_a);
			double f_x = funcion3(x);

			if ((f_a * f_x) > 0)
				a = x;
			else
				b = x;

			K_false_position++;
		}

		return a - (b - a) / 2;
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

		if (Math.abs(funcaoSecantNewton(x0)) < e1)
			System.out.println("faça y = " + x0);

		else {
			int k = 0;

			double x1 = x0 - (funcaoSecantNewton(x0) / derivada(x0));

			while (Math.abs(funcaoSecantNewton(x1)) > e1 || Math.abs(x1 - x0) > e2) {

				x1 = x0 - (funcaoSecantNewton(x0) / derivada(x0));
				x0 = x1;
				k++;
			}
			System.out.println("faça y = " + x1 + " - " + f(x1));
		}
	}

	public static void secant(double x0, double x1, double e1, double e2) {
		if (Math.abs(funcao(x0)) < e1)
			System.out.println("faça y = " + x0);
		else if (Math.abs(funcaoSecantNewton(x1)) < e1 || Math.abs(x1 - x0) < e2)
			System.out.println("faça y = " + x1);
		else {
			int k = 1;

			double x2 = 0.0;

			do {

				x2 = x1 - (funcaoSecantNewton(x1) / (funcaoSecantNewton(x1) - funcaoSecantNewton(x0))) * (x1 - x0);

				x0 = x1;
				x1 = x2;

				k++;
			} while (Math.abs(funcaoSecantNewton(x2)) > e1 || Math.abs(x2 - x1) > e2);

			System.out.println("então faça y = " + x2);
		}
	}

	public static void trapezio(float b, float a, int n) {

		float h = (b - a) / n;

		float soma = funcaoTrapezio(a);

		int i;
		for (i = 1; i < n; i++)
			soma += 2 * funcaoTrapezio(h * i);

		soma += funcaoTrapezio(b);

		System.out.println("valor: " + (h * soma) / 2);
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
		float a_next = 0, b_next = 0, t_next = 0, p_next;
		while (maxInteration > 0) {
			a_next = (a0 + b0) / 2;
			b_next = (float) Math.sqrt((a0 * b0));
			t_next = (float) (t0 - p0 * Math.pow((a0 - a_next), 2));
			p_next = 2 * p0;

			a0 = a_next;
			b0 = b_next;
			t0 = t_next;
			p0 = p_next;
			
			maxInteration -- ;

		}
		
		float re = (float) ((Math.pow((a_next + b_next),2)) / 4* t_next);
		System.out.println(re);
	}

	public static void eliminacaoGauusPivoTiamentoParcial(double MatCoeficientes[][], double vectConstantes[],
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

	public static void eliminacaoGaussPivoTiamentoTotal() {

	}

	public static void decomposicaoLU() {
		double m[][] = null;
		int size = 0;
		Decomposicaolu.chamada(m, size);
	}

	public static void simplex() {

	}

	public static void main(String[] args) {
		// trapezio(2, 1,4 );ok
		// simpson13(0.8f, 0, 4);ok
		//System.out.println(bisection(1, 2, 0.001)); OK 
		//System.out.println(false_position(0, 1, 0.0005, 0.0005)); ok
		 //metodoNewton(0.5, 0.0001, 0.0001);
		//secant(0, 1, 0.0005, 0.0005);
		//System.out.println(GaussLegendre.pi(10));

		/*
		int n = 2; // Ordem da matriz A

		// Matriz dos coeficientes:
		double a[][] = { { 0.0002, 2.00 }, { 2.00, 2.00 } };

		// Vetor das constantes...:
		double b[] = { 5.00, 6.00 };

		// Vetor das soluções: os x's do Sistema!
		double x[] = new double[n];
		eliminacaoGauusPivoTiamentoParcial(a, b, x, n);
		*/
	}
}
