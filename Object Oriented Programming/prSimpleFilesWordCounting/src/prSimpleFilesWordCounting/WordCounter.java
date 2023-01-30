package prSimpleFilesWordCounting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringJoiner;

public class WordCounter {
	
	private static final int INITIAL_SIZE = 10;
	private int wordsNumber;
	private WordInText[] words;
	
	public WordCounter() {
		this(INITIAL_SIZE);
	}
	
	public WordCounter(int n) {
		words = new WordInText[n];
	}
	
	private int position(String p) {
		WordInText w = new WordInText(p);
		int i = 0;
		while(i < wordsNumber && !w.equals(words[i])) {
			i++;
		}
		return (i == wordsNumber) ? -1 : i;
	}
	
	protected void include(String w) {
		int pos = position(w);
		if(pos >= 0) {
			words[pos].increment();
		} else {
			if (wordsNumber == words.length) {
				words = Arrays.copyOf(words, 2*words.length);
			}
			words[wordsNumber++]=new WordInText(w);
		}
	}
	
	private void includeAll(String l, String d) {
		try (Scanner sc = new Scanner(l)) {
			sc.useDelimiter(d);
			while(sc.hasNext()) {
				include(sc.next());
			}
		}
	}
	
	public void includeAll(String[] t, String d) {
		for(String l: t) {
			includeAll(l,d);
		}
	}
	
	public void includeAllFromFile(String filename, String del) throws FileNotFoundException {
		try(Scanner sc = new Scanner(new File(filename))){
			readFile(sc, del);
		}
	}

	private void readFile(Scanner sc, String del) {
		while(sc.hasNextLine()) {
			includeAll(sc.nextLine(), del);
		}
		
	}
	
	public WordInText find(String w) {
		int p = position(w);
		if(p < 0) {
			throw new NoSuchElementException("Not found" + w);
		}
		return words[p];
	}
	
	public String toString() {
		StringJoiner sj = new StringJoiner(", " , "[" , "]");
		for(int i = 0; i < wordsNumber; i++) {
			sj.add(words[i].toString());
		}
		return sj.toString();
	}
	
	public void presentWords(String f) throws FileNotFoundException {
		try(PrintWriter pw = new PrintWriter(f)){
			presentWords(pw);
		}
	}

	public void presentWords(PrintWriter pw) {
		for(int i = 0; i < wordsNumber; i++) {
			pw.println(words[i]);
		}
	}
	
	
	
}
