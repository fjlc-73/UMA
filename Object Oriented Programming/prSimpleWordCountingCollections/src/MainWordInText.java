import prSimpleWordCountingCollections.WordInText;

public class MainWordInText {

	public static void main(String[] args) {
		WordInText w1 = new WordInText("uno");
		WordInText w2 = new WordInText("dos");
		
		w2.increment();
		w2.increment();
		w2.increment();
		w1.increment();
		w1.increment();
		
		System.out.println(w1.compareTo(w2));
		
		 w1 = new WordInText("uno");
		 w2 = new WordInText("UNO");
		
		w2.increment();
		w2.increment();
		w2.increment();
		w1.increment();
		w1.increment();
		
		System.out.println(w1.compareTo(w2));
		
		
	}

}
