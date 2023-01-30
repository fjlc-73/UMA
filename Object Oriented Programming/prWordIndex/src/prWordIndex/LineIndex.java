package prWordIndex;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class LineIndex extends AbstractIndex {
	
	private SortedMap<String,SortedSet<Integer>> index;

	public LineIndex() {
		index = new TreeMap<>();
	}

	@Override
	public void solve(String s) {
		int numLine = 1;
		index.clear();
		Iterator<String> iter = sentences.iterator();
		while(iter.hasNext()) {
			try (Scanner sc = new Scanner(iter.next())){
				sc.useDelimiter(s);
				while(sc.hasNext()) {
					String ss = sc.next().toLowerCase();
					SortedSet<Integer> set = index.getOrDefault(ss, new TreeSet<Integer>());
					set.add(numLine);
					index.putIfAbsent(ss, set);
			} 
			numLine++;
		}

	}

	}

	@Override
	public void presentIndex(PrintWriter pw) {
		for(String s: index.keySet()) {
			System.out.println(String.format("%s \t %s", s, index.get(s).toString()));
		}

	}

}
