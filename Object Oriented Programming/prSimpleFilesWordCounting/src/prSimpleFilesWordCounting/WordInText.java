package prSimpleFilesWordCounting;

public class WordInText {
	
	private String word;
	private int times;
	
	public WordInText(String s) {
		word = s.toUpperCase();
		times = 1;
	}
	
	public boolean equals(Object o) {
		return (o instanceof WordInText) && word.equals(((WordInText)o).word);
	}
	
	public int hashCode() {
		return word.hashCode();
	}
	
	public void increment() {
		times++;
	}
	
	public String toString() {
		return String.format("%s: %d", word, times);
	}

}
