import java.util.HashMap;
import java.util.Map;

public class Mutant {
	private static String[] pat = { "AAAA", "TTTT", "CCCC", "GGGG" };

	public static void main(String[] args) {
		System.out.println("Caso true=>");
		String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
		System.out.println(isMutant(dna));

		System.out.println("Caso true 2 match en vertical=>");
		String[] dna1 = { "ATGCGA", "CCGTGC", "CTATTA", "CCAATG", "CTACTA", "TCACTG" };
		System.out.println(isMutant(dna1));

		System.out.println("Caso true 2 match en diagonal=>");
		String[] dna2 = { "ATGCGA", "CAGTGC", "CTATGT", "ACAACG", "CTCCTA", "TCACTG" };
		System.out.println(isMutant(dna2));

		System.out.println("Caso true 2 match en diagonal contraria=>");
		String[] dna3 = { "ATGCGA", "CCGTAC", "CTAAGT", "AGAATG", "CTCTTA", "TCTCTG" };
		System.out.println(isMutant(dna3));
	}

	/**
	 * Metodo que permite validar si un humano es mutante o no a partir de la
	 * secuencia de ADN recibida como parametro en el arreglo de Strings.
	 * 
	 * @param String[] dna
	 * @return String
	 */
	public static boolean isMutant(String[] dna) {
		String[] dnaVer = new String[dna[0].length()];
		dnaVer = toDnaVer(dna);

		String[][] dnaBi = new String[dna[0].length()][dna[0].length()];

		String[] dnaDia = toDnaDia(dna, dnaBi);
		String[] dnaObl = toDnaObl(dna, dnaBi);

		int aux = 0;

		for (int i = 0; i < dna.length; i++) {

			if (findBoyerMoore(pat[0].toCharArray(), dna[i].toCharArray()) != -1) {
				aux = aux + 1;
			}

			if (findBoyerMoore(pat[1].toCharArray(), dna[i].toCharArray()) != -1) {
				aux = aux + 1;
			}

			if (findBoyerMoore(pat[2].toCharArray(), dna[i].toCharArray()) != -1) {
				aux = aux + 1;
			}

			if (findBoyerMoore(pat[3].toCharArray(), dna[i].toCharArray()) != -1) {
				aux = aux + 1;
			}

			if (findBoyerMoore(pat[0].toCharArray(), dnaVer[i].toCharArray()) != -1) {
				aux = aux + 1;
			}

			if (findBoyerMoore(pat[1].toCharArray(), dnaVer[i].toCharArray()) != -1) {
				aux = aux + 1;
			}

			if (findBoyerMoore(pat[2].toCharArray(), dnaVer[i].toCharArray()) != -1) {
				aux = aux + 1;
			}

			if (findBoyerMoore(pat[3].toCharArray(), dnaVer[i].toCharArray()) != -1) {
				aux = aux + 1;
			}
		}

		for (int j = 0; j < dnaDia.length - 1; j++) {
			if (findBoyerMoore(pat[0].toCharArray(), dnaDia[j].toCharArray()) != -1) {
				aux = aux + 1;
			}
		}

		for (int j = 0; j < dnaDia.length - 1; j++) {
			if (findBoyerMoore(pat[1].toCharArray(), dnaDia[j].toCharArray()) != -1) {
				aux = aux + 1;
			}
		}

		for (int j = 0; j < dnaDia.length - 1; j++) {
			if (findBoyerMoore(pat[2].toCharArray(), dnaDia[j].toCharArray()) != -1) {
				aux = aux + 1;
			}
		}

		for (int j = 0; j < dnaDia.length - 1; j++) {
			if (findBoyerMoore(pat[3].toCharArray(), dnaDia[j].toCharArray()) != -1) {
				aux = aux + 1;
			}
		}

		for (int j = 0; j < dnaDia.length - 1; j++) {
			if (findBoyerMoore(pat[0].toCharArray(), dnaObl[j].toCharArray()) != -1) {
				aux = aux + 1;
			}
		}

		for (int j = 0; j < dnaDia.length - 1; j++) {
			if (findBoyerMoore(pat[1].toCharArray(), dnaObl[j].toCharArray()) != -1) {
				aux = aux + 1;
			}
		}

		for (int j = 0; j < dnaDia.length - 1; j++) {
			if (findBoyerMoore(pat[2].toCharArray(), dnaObl[j].toCharArray()) != -1) {
				aux = aux + 1;
			}
		}

		for (int j = 0; j < dnaDia.length - 1; j++) {
			if (findBoyerMoore(pat[3].toCharArray(), dnaObl[j].toCharArray()) != -1) {
				aux = aux + 1;
			}
		}

		if (aux > 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Metodo que permite organizar de forma vertical las cadenas recibidas en la
	 * peticion.
	 * 
	 * @param String[] dna
	 * @return String[]
	 */
	public static String[] toDnaVer(String[] dna) {

		String[] dnaVer = new String[dna[0].length()];
		for (int i = 0; i < dna.length; i++) {
			dnaVer[i] = "";
			for (int j = 0; j < dna.length; j++) {
				dnaVer[i] = dnaVer[i] + (String.valueOf(dna[j].charAt(i)));
			}
		}
		return dnaVer;

	}

	/**
	 * Metodo que permite organizar de forma diagonal las cadenas recibidas en la
	 * peticion original.
	 * 
	 * @param String[] dna
	 * @return String[]
	 */
	public static String[] toDnaDia(String[] dna, String[][] dnaBi) {
		String[] dnaDiaAux = new String[dna[0].length() * 2];
		for (int i = 0; i < dna.length; i++) {
			for (int j = 0; j < dna.length; j++) {
				dnaBi[i][j] = String.valueOf(dna[i].charAt(j));
			}
		}

		// Calcula la altura y la anchura de la matriz introducida.
		Integer altura = dnaBi.length, anchura = dnaBi[0].length;

		for (
				// Recorre los inicios de cada diagonal en los bordes de la matriz.
				Integer diagonal = 1 - anchura; // Comienza con un número negativo.
				diagonal <= altura - 1; // Mientras no llegue a la última diagonal.
				diagonal += 1 // Avanza hasta el comienzo de la siguiente diagonal.
				) {
			dnaDiaAux[diagonal + anchura - 1] = "";
			for (
					// Recorre cada una de las diagonales a partir del extremo superior derecho.
					Integer vertical = Math.max(0, diagonal), horizontal = -Math.min(0, diagonal); vertical < altura
							&& horizontal < anchura; // Mientras no excedan los límites.
					vertical += 1, horizontal += 1 // Avanza en diagonal incrementando ambos ejes.
					) {
				// agrega los datos al arreglo ordenadamente
				dnaDiaAux[diagonal + anchura - 1] = dnaDiaAux[diagonal + anchura - 1] + dnaBi[vertical][horizontal];
			}
		}
		return dnaDiaAux;

	}

	/**
	 * Metodo que permite organizar de forma diagonal las cadenas recibidas en la
	 * peticion y que se organizan para poder ser agregadas al arreglo en la forma
	 * que senecesita para poder cubrir las diagonales oblicuas.
	 * 
	 * @param String[] dna
	 * @return String[]
	 */
	public static String[] toDnaObl(String[] dna, String[][] dnaBi) {
		String[] dnaDiaAux = new String[dna[0].length() * 2];
		for (int i = 0; i < dna.length; i++) {
			int aux = 0;
			for (int j = dna.length - 1; j >= 0; j--) {
				dnaBi[i][aux] = String.valueOf(dna[i].charAt(j));
				aux++;
			}
		}

		// Calcula la altura y la anchura de la matriz introducida.
		Integer altura = dnaBi.length, anchura = dnaBi[0].length;

		for (
				// Recorre los inicios de cada diagonal en los bordes de la matriz.
				Integer diagonal = 1 - anchura; // Comienza con un número negativo.
				diagonal <= altura - 1; // Mientras no llegue a la última diagonal.
				diagonal += 1 // Avanza hasta el comienzo de la siguiente diagonal.
				) {
			dnaDiaAux[diagonal + anchura - 1] = "";
			for (
					// Recorre cada una de las diagonales a partir del extremo superior derecho.
					Integer vertical = Math.max(0, diagonal), horizontal = -Math.min(0, diagonal); vertical < altura
							&& horizontal < anchura; // Mientras no excedan los límites.
					vertical += 1, horizontal += 1 // Avanza en diagonal incrementando ambos ejes.
					) {
				// agrega los datos al arreglo ordenadamente
				dnaDiaAux[diagonal + anchura - 1] = dnaDiaAux[diagonal + anchura - 1] + dnaBi[vertical][horizontal];
			}
		}
		return dnaDiaAux;

	}

	/**
	 * Boyer-Moore busqueda por patron dentro de una cadena.
	 * 
	 * @param char[] text -- search this text to see if it contains pattern
	 * @param char[] pattern -- look for this text inside text parameter
	 * @return int -- return index of first match or -1 if not found
	 */
	public static int findBoyerMoore(char[] pattern, char[] text) {

		int n = text.length;
		int m = pattern.length;

		// Primero se evalua si llega un string vacio
		if (m == 0)
			return 0;

		// Inicializacion, se crea Map de la ultima posicion para cada caracter = O(n)
		Map<Character, Integer> last = new HashMap<>();
		for (int i = 0; i < n; i++) {
			// asignacion de todos los caracteres, por defecto en -1
			last.put(text[i], -1);
		}
		for (int i = 0; i < m; i++) {
			// update last seen positions
			last.put(pattern[i], i);
		}

		// Inicia con el final del patron alineado en el indice m-0 en el texto
		// indice en el texto
		int i = m - 1;
		// indice en el patron
		int k = m - 1;
		while (i < n) {
			// match! retorna i se es match completo; si no, sigue verificando.
			if (text[i] == pattern[k]) {
				if (k == 0) {
					// encontrado
					return i;
				}
				i--;
				k--;
			} else { // salto, reinicia al final del patron
				// mover en el texto
				i += m - Math.min(k, 1 + last.get(text[i]));
				// mover al final del patron
				k = m - 1;
			}
		}
		return -1;
	}
}