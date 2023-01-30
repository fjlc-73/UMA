import java.util.NoSuchElementException;

import prSimpleFilesWordCounting.*;

import java.io.*;

public class Main {
	public static void main(String[] args) {
		String[] datos = { "Guerra tenía una jarra y Parra tenía una perra, ",
				"pero la perra de Parra rompió la jarra de Guerra.", "Guerra pegó con la porra a la perra de Parra. ",
				"¡Oiga usted buen hombre de Parra! ", "Por qué ha pegado con la porra a la perra de Parra.",
				"Porque si la perra de Parra no hubiera roto la jarra de Guerra,",
				"Guerra no hubiera pegado con la porra a la perra de Parra." };
		String delimitadores = "[ .,:;\\-\\!\\¡\\¿\\?]+"; // " .,:;-!¡¿?" una o varias apariciones
		String[] noSig = { "Con", "La", "A", "De", "NO", "SI", "y", "una" };
		WordCounter contador = null, contadorSig = null;
		// Si no se incluye un argumento numérico, se crea por defecto.
		try {
			int n = Integer.parseInt(args[0]);
			System.out.println("With main's argument..." + n);
			contador = new WordCounter(n);
			contadorSig = new WordCounterSig(n, noSig);
		} catch (RuntimeException e) {
			System.out.println("With no main's argument...");
			contador = new WordCounter();
			contadorSig = new WordCounterSig(noSig);
		}
		// We include all the words taking into account the delimiters
		contador.includeAll(datos, delimitadores);
		contadorSig.includeAll(datos, delimitadores);
		System.out.println(contador + "\n");
		System.out.println(contadorSig + "\n");
		try {
			System.out.println(contador.find("parra"));
			System.out.println(contador.find("Gorra"));
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
		// We repeat the execution taking reading/writing from/to file
		System.out.println("We repeat the execution taking reading/writing from/to file");
		WordCounter contadorSigFich = null;
		// If a numeric argument is not included, it is created by default.
		try {
			int n = Integer.parseInt(args[0]);
			System.out.println("With main's argument..." + n);
			contador = new WordCounter(n);
			contadorSigFich = new WordCounterSig(n, "fichNoSig.txt", delimitadores);
		} catch (RuntimeException e) {
			System.out.println("With no main's argument...");
			contador = new WordCounter();
			try {
				contadorSigFich = new WordCounterSig("fichNoSig.txt", delimitadores);
			} catch (IOException e1) {
				//
				System.out.println("ERROR:" + e1.getMessage());
			}
		} catch (IOException e) {
			System.out.println("ERROR:" + e.getMessage());
		}
		// We include all the words that are in datos.txt taking into account the separators
		try {
			contador.includeAllFromFile("datos.txt", delimitadores);
			contadorSigFich.includeAllFromFile("datos.txt", delimitadores);
			System.out.println(contador + "\n");
			System.out.println(contadorSigFich + "\n");
			// display methods
			PrintWriter pw = new PrintWriter(System.out, true);
			contador.presentWords(pw);
			// output to file
			contador.presentWords("salida-datos.txt");
			// Methods to present on screen for Non Significant
			System.out.println();
			contadorSigFich.presentWords(pw);
			// output to file
		} catch (IOException e) {
			System.out.println("ERROR:" + e.getMessage());
		}
	}
}
