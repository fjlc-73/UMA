package prSimpleWordCountingCollections;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeSet;

public class WordCounter {
	
	private Set<WordInText> words;
	
	public WordCounter() {
		words = new TreeSet<WordInText>();
	}
	
	protected void include(String s) {
		WordInText w = new WordInText(s);
		try {
		find(s).increment();
		} catch (NoSuchElementException e) {
			words.add(w);
		}
	}
	
	
	public WordInText find(String s) {
		WordInText w = new WordInText(s);
		WordInText wt = new WordInText("");
		boolean found = false;
		Iterator<WordInText> iter = words.iterator();
		while (iter.hasNext() && !found) {
			 wt = iter.next();
			if(wt.equals(w)) {
				found = true;
			}
		}
		if(!found) {
			throw new NoSuchElementException();
		}
		return wt;
	}
	
	public void includeAll(String[] text, String del) {
		for(String s: text) {
			includeAll(s, del);
		}
	}
	
	public void includeAllFromFile(String filename, String del) throws FileNotFoundException {
		try(Scanner sc = new Scanner(new File (filename))){
			readFile(sc, del);
		}
	}
	
	public String toString() {
		StringJoiner sj = new StringJoiner(", " , "[" , "]");
		
		Iterator<WordInText> iter = words.iterator();
		while (iter.hasNext()) {
			 sj.add(iter.next().toString());
		}
		return sj.toString();
	}
	
	public void presentWords(String f) throws FileNotFoundException {
		try(PrintWriter pw = new PrintWriter(f)){
			presentWords(pw);
		}
	}

	public void presentWords(PrintWriter pw) {
		Iterator<WordInText> iter = words.iterator();
		while (iter.hasNext()) {
			pw.println(iter.next());
		}
	}
	

	
	private void readFile(Scanner sc, String del) {
		while(sc.hasNextLine()) {
			includeAll(sc.nextLine(), del);
		}
	}
	
	
	
	private void includeAll(String s, String del)
	{
		try(Scanner sc = new Scanner(s)){
			sc.useDelimiter(del);
			while (sc.hasNext()) {
				include(sc.next());
			}
		}
	}
	
	
}
