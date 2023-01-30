import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;

import prSimpleWordCountingCollections.*;

import java.io.PrintWriter;
import java.io.IOException;

public class Main2 {
	public static void main(String[] args) {
		String[] data = { "Guerra tenía una jarra y Parra tenía una perra, ",
				"pero la perra de Parra rompió la jarra de Guerra.", "Guerra pegó con la porra a la perra de Parra. ",
				"¡Oiga usted buen hombre de Parra! ", "Por qué ha pegado con la porra a la perra de Parra.",
				"Porque si la perra de Parra no hubiera roto la jarra de Guerra,",
				"Guerra no hubiera pegado con la porra a la perra de Parra." };
		String delimiters = "[ .,:;\\-\\!\\¡\\¿\\?]+";
		System.out.println("We create a word counter");
		WordCounter counter = new WordCounter();
		// We include all words in data taking into account the delimiters 
		counter.includeAll(data, delimiters);
		System.out.println(counter + "\n");
		try {
			System.out.println(counter.find("parra"));
			System.out.println(counter.find("Gorra"));
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage() + "\n");
		}
		// Repetimos la salida con entrada desde fichero ................
		System.out.println("We repeat the execution taking the input from file");
		counter = new WordCounter();
		// Incluimos todas las palabras que hay en datos.txt teniendo en cuenta los
		// separadores
		try {
			counter.includeAllFromFile("datos.txt", delimiters);
			System.out.println(counter + "\n");
			// métodos para presentar por pantalla
			System.out.println("Output to terminal: ");
			PrintWriter pw = new PrintWriter(System.out, true);
			counter.presentWords(pw);
			// salida a fichero
			System.out.println("\nOutput to file: salida-datos.txt\n");
			counter.presentWords("salida-datos.txt");
		} catch (IOException e) {
			System.out.println("ERROR:" + e.getMessage());
		}
		// Creamos un contador de palabras significativas
		// .....................................
		String[] noSig = { "A", "Con", "De", "La", "NO", "SI", "una", "y" };
		Collection<String> palNS = new HashSet<String>();
		for (String p : noSig) {
			palNS.add(p);
		}
		System.out.println("We create a file of significant words: ");
		WordCounterSig contadorSig = new WordCounterSig(palNS);
		contadorSig.includeAll(data, delimiters);
		System.out.println(contadorSig + "\n");
		// Repetimos la salida con entrada desde fichero ................
		System.out.println("We repeat the execution taking the input from file");
		// Incluimos todas las palabras que hay en datos.txt y las no significativas de
		// fichNoSig
		try {
			contadorSig = new WordCounterSig("fichNoSig.txt", delimiters);
			contadorSig.includeAllFromFile("datos.txt", delimiters);
			System.out.println(contadorSig + "\n");
			// métodos para presentar por pantalla
			System.out.println("Output to terminal:");
			PrintWriter pw = new PrintWriter(System.out, true);
			contadorSig.presentWords(pw);
			// salida a fichero
			System.out.println("\nOutput to file: salidaSig.txt");
			contadorSig.presentWords("salidaSig.txt");
		} catch (IOException e) {
			System.out.println("ERROR:" + e.getMessage());
		}
	}
}
