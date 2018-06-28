package br.ufc.crateus.matematicacomputacional;

public class Decomposicaolu {
	// Função Imprime
	public static void print(double m[][], int size) {
		int i, j;
		for (i = 0; i < size; i++) {
			for (j = 0; j < size; j++) {
				System.out.println(m[i][j]);
			}
			System.out.println();
		}
	}

	// Função Pivoteamento
	public static int pivot(double m[][], int size) {
		int i, j, k, t = 0;
		double aux;
		for (j = 0; j < (size + 1); j++) {
			for (i = j; i < size; i++) {
				if (m[i][i] < m[i][j]) {
					for (k = 0; k < (size + 1); k++) {
						aux = m[i][j];
						m[i][k] = m[j][k];
						m[j][k] = aux;
					}
					t++;
					i++;
				}
			}
		}
		System.out.println("\n\n matriz com pivoteamento:\n");
		Decomposicaolu.print(m, size);
		return t;
	}

	// Função de Triangularização
	public static void zera(double m[][], int size) {
		int i, j, k;
		double num;

		for (j = 0; j < (size + 1); j++) {
			for (i = 0; i < size; i++) {
				if (i > j) {
					num = -(m[i][j] / m[j][j]);
					for (k = 0; k < (size + 1); k++) {
						m[i][k] = num * m[j][k] + m[i][k];
					}
				}
			}
		}
		System.out.println("\n\n matriz Triangular Superior:\n");
		Decomposicaolu.print(m, size);
	}

	// Função para fazer a substituição reversa
	public static void solucao(double m[][], int size) {
		double[] x = new double[size];
		double soma = 0.0;
		int i, j;
		x[size - 1] = m[size - 1][(size + 1) - 1] / m[size - 1][(size + 1) - 2];

		for (i = size - 2; i >= 0; i--) {
			soma = 0;
			for (j = i + 1; j < size; j++) {
				soma = soma + m[i][j] * x[j];
			}
			x[i] = (m[i][size] - soma) / m[i][i];
		}

		System.out.println("\n A solução do sistema será:\n");
		for (i = 0; i < size; i++) {
			System.out.print("x" + (i + 1));
			System.out.println(":" + x[i]);
		}
	}

	// Função para o cálculo do Determinante
	public static void determinante(double m[][], int size, int n) {
		double v, prod = 1.0, soma = 0.0;
		int i, j;
		for (i = 0; i < size; i++) {
			for (j = 0; j < (size + 1); j++) {
				if (i == j) {
					// se número de troca positivo
					if (n % 2 == 0) {
						v = m[i][j];
						prod *= v;
					} else {// se número de troca negativo
						v = -m[i][j];
						prod *= v;
					}
				}
			}
		}
		System.out.println("O valor do determinante é: " + prod);
	}

	// Decomposição lu

	public static void dlu(double m[][], int size) {
		int i, j, k, cont = 0;
		double[][] l = new double[size][size];
		double[][] u = new double[size][size];
		double s1 = 0.0, s2 = 0.0;
		// preenchendo a primeira linha de l da primeira coluna
		for (i = 0; i < size; i++)
			l[i][0] = m[i][0];

		// preenchendo a matriz u
		i = 0;
		for (j = 0; j < size; j++) {
			if (i == j) {
				u[i][j] = 1.0;
				i++;
			}
			// cada coluna com a linha fixa recebe esse valor:
			u[0][j] = m[0][j] / l[0][0];
			cont++;
		}
		// começando da segunda linha e segunda coluna, já que a primeira ja
		// está preenchida.
		for (i = 1; i < size; i++) {
			for (j = 1; j < size; j++) {

				for (k = 0; k < j - 1; k++) {

					s1 += u[k][i] * l[i][k];
					cont += 2;
				}

				l[i][j] = m[i][j] - s1;
				cont++;
			}
			for (j = i + 1; j < size - 1; j++) {
				for (k = 0; k < i - 1; i++) {
					s2 += u[k][j] * l[i][k];
					cont = +2;

				}

				u[i][j] = (m[i][j] - s2) / l[i][i];
				cont++;
			}
		}

		System.out.println("Matriz L");
		Decomposicaolu.print(l, size);

		System.out.println("Matriz U");
		Decomposicaolu.print(u, size);

		System.out.println("Númoro de iterações para dimensão" + size + " : " + cont);

	}

	public static void chamada(double m[][], int size) {
		System.out.println("Matriz");
		Decomposicaolu.print(m, size);
		int t = Decomposicaolu.pivot(m, size);
		System.out.println("Número de trocas: " + t);
		Decomposicaolu.zera(m, size);
		Decomposicaolu.solucao(m, size);
		Decomposicaolu.determinante(m, size, t);
		Decomposicaolu.dlu(m, size);
	}
}
