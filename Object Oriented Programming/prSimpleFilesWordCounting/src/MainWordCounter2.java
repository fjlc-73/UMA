import java.io.FileNotFoundException;

import prSimpleFilesWordCounting.WordCounter;

public class MainWordCounter2 {

	public static void main(String[] args) {
		WordCounter w = new WordCounter();
		
		try {
			w.includeAllFromFile("quijote.txt", "[ .,;:]+");
			w.presentWords("salida-quijote.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


	}

}
