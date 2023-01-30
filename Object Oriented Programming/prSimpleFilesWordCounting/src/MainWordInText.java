import prSimpleFilesWordCounting.WordInText;

public class MainWordInText {

	public static void main(String[] args) {
		WordInText p1 = new WordInText("gorra");
		WordInText p2 = new WordInText("Gorra");
		p1.increment();
		System.out.println("Word 1 = " + p1);
		System.out.println("Word 2 = " + p2);
		System.out.println("The words are " + ((p1.equals(p2)) ? "" : "not" ) + "equal");
		

	}

}
