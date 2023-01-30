package prSimpleFilesWordCounting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class WordCounterSig extends WordCounter {
	
	private static final int INITIAL_SIZE_NSW = 10;
	private int nonSigWordsNum;
	private String[] nonSignificant;
	
	public WordCounterSig(int i, String[] nosig) {
		super(i);
		nonSignificant = toUpperCase(nosig);
		nonSigWordsNum = nosig.length;
	}
	
	public WordCounterSig(String[] s) {
		super();
		nonSignificant = toUpperCase(s);
		nonSigWordsNum = s.length;
	}
	
	public WordCounterSig(String filNoSig, String del) throws FileNotFoundException {
		nonSignificant = new String[INITIAL_SIZE_NSW];
		nonSigWordsNum = 0;
		readNonSigFile(filNoSig, del);
	}
	
	public WordCounterSig(int n, String filNoSig, String del) throws FileNotFoundException {
		nonSignificant = new String[n];
		nonSigWordsNum = 0;
		readNonSigFile(filNoSig, del);
	}
	
	private void readNonSigFile(String filNoSig, String del) throws FileNotFoundException {
		try(Scanner sc = new Scanner(new File(filNoSig))){
			readNonSigWords(sc, del);
		}
		
	}

	private void readNonSigWords(Scanner sc, String del) {
		while(sc.hasNext()) {
			includeNoSig(sc.next());
		}
		
	}


	
	private String[] toUpperCase(String[] s) {
		for(int i = 0; i < s.length; i++) {
			s[i]=s[i].toUpperCase();
		}
		return s;
	}
	
	private void includeNoSig(String s) {
		int pos = findNoSig(s.toUpperCase());
		if(pos == -1) {
			if(nonSigWordsNum == nonSignificant.length) {
				nonSignificant = Arrays.copyOf(nonSignificant, nonSignificant.length*2);
			}
			nonSignificant[nonSigWordsNum++]=s.toUpperCase();
		}
	}
	
	private int findNoSig(String s) {
		int i = 0;
		while(i < nonSigWordsNum && !s.equals(nonSignificant[i])) {
			i++;
		}
		return (i==nonSigWordsNum) ? -1 : i;
	}
	
	protected void include(String s) {
		int pos = findNoSig(s.toUpperCase());
		if(pos == -1) {
			super.include(s);
		}
	}

}
