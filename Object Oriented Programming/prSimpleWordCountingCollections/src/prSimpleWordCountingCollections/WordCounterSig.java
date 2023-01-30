package prSimpleWordCountingCollections;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;


public class WordCounterSig extends WordCounter {
	
	private Set<String> notSignificant;
	
	public WordCounterSig(Collection<String> c) {
		super();
		notSignificant = new HashSet<String>();
		capitalise(c);	
	}
	
	public WordCounterSig(String nonSigFile, String del) throws FileNotFoundException {
		super();
		notSignificant = new HashSet<String>();
		readNonSigFile(nonSigFile, del);
	}
	
	protected void include(String s) {
		if(!notSignificant.contains(s.toUpperCase())) {
			super.include(s);
		}
	}
	
	

	private void readNonSigFile(String nonSigFile, String del) throws FileNotFoundException {
		Scanner sc = new Scanner (new File (nonSigFile));
		readNonSigWords(sc, del);
		
	}

	private void readNonSigWords(Scanner sc, String del) {
		while(sc.hasNext()) {
			notSignificant.add(sc.next().toUpperCase());
			
		}
		
	}

	private void capitalise(Collection<String> c) {
		Iterator<String> iter = c.iterator();
		while(iter.hasNext()) {
			notSignificant.add(iter.next().toUpperCase());
		}
		
	}

}
