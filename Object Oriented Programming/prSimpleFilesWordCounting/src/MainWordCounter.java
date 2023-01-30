import prSimpleFilesWordCounting.WordCounter;

public class MainWordCounter {

	public static void main(String[] args) {
		WordCounter w = new WordCounter(5);
		
		String [] datos = {
				"Esta es la primera frase del ejemplo", "y esta es la segunda frase"
				};
		
		w.includeAll(datos, "[ ]");
		
		System.out.println(w);

	}

}
