package br.ufc.crateus.matematicacomputacional;

public class EliminacaoGaussPivoParcial {
	private int size; // Ordem da matriz...
	private double MatCoeficientes[][]; // Matriz dos coeficientes...
	private double vectConstantes[]; // Vetor das constantes...
	private double vectorResultados[]; // Vetor dos resultados...

	public EliminacaoGaussPivoParcial(double paramet_a[][], double paramet_b[], double paramet_x[], int paramet_n) {
		MatCoeficientes = paramet_a;
		vectConstantes = paramet_b;
		vectorResultados = paramet_x;
		size = paramet_n;
	}

	// Este método mostra a matriz MatCoeficientes e o vetor vectConstantes ao
	// mesmo tempo!
	public boolean MostrarAB() {
		System.out.println("---------------------------------------------------------");

		for (int linha = 0; linha < size; linha++) {
			for (int coluna = 0; coluna < size; coluna++) {
				System.out.printf("\t%.2f", MatCoeficientes[linha][coluna]);
			}
			System.out.printf("\t=\t%.2f", vectConstantes[linha]);
			System.out.print("\n");
		}
		System.out.println("---------------------------------------------------------");

		return true;
	}

	// Este método faz a eliminação para transformar a matriz em uma matriz
	// triangular!
	public boolean EliminacaoGauss() {
		// Percorre linhas... Repete até número de linhas - 1
		for (int k = 0; k < (size - 1); k++) {
			// Percorre linhas secundárias... Até o fim da matriz ou seja... até
			// n
			for (int i = (k + 1); i < size; i++) {
				// Escolher como pivô o elemento de maior múdulo entre os
				// coeficientes...
				if (Math.abs(MatCoeficientes[k][k]) < Math.abs(MatCoeficientes[i][k])) {
					// Neste casó é necessário trocar as linhas k e i
					System.out.printf("Trocando Linhas: %d por %d\n", k + 1, i + 1);
					TrocarLinhas(k, i);
					System.out.printf("Nova Matriz com linhas trocadas\n");
					MostrarAB();
				}
				// Continua com algorítimo normal do Gauss!

				double m = MatCoeficientes[i][k] / MatCoeficientes[k][k];
				MatCoeficientes[i][k] = 0;

				// Calcula as próxima linhas com base nos m encontrados...
				for (int j = (k + 1); j < size; j++) {
					MatCoeficientes[i][j] = MatCoeficientes[i][j] - m * MatCoeficientes[k][j];
				}

				// Cálculo do vetor... dos resutados!!!
				vectConstantes[i] = vectConstantes[i] - m * vectConstantes[k];
			}
		}

		return true;
	}

	// Este método faz a eliminação para transformar a matriz em uma matriz
	// triangular!
	public boolean ResolucaoDoSistema() {
		size--; // para facilitar os cáculos já que o indece de matrizes em java
				// começam em zero!
		vectorResultados[size] = vectConstantes[size] / MatCoeficientes[size][size];

		for (int k = size; k > -1; k--) {
			double s = 0;
			for (int j = (k + 1); j < (size + 1); j++) {
				s = s + MatCoeficientes[k][j] * vectorResultados[j];
				vectorResultados[k] = (vectConstantes[k] - s) / MatCoeficientes[k][k];
			}
		}

		size++; // para acertar o valor de n...
		return true;
	}

	// Este método mostra os resultados ou seja os x's.
	public boolean MostrarResultados() {
		for (int i = 0; i < size; i++) {
			System.out.printf("x[%d] = %.2f\n", i + 1, vectorResultados[i]);
		}

		return true;
	}

	// Este método traca duas linhas informadas!
	private boolean TrocarLinhas(int linha1, int linha2) {
		double aux = 0.00;

		// Troca elementos das linhas na matriz: a
		for (int i = 0; i < size; i++) {
			aux = MatCoeficientes[linha1][i];
			MatCoeficientes[linha1][i] = MatCoeficientes[linha2][i];
			MatCoeficientes[linha2][i] = aux;
		}

		// Troca os elementos do vetor: b
		aux = vectConstantes[linha1];
		vectConstantes[linha1] = vectConstantes[linha2];
		vectConstantes[linha2] = aux;

		return true;
	}

}
