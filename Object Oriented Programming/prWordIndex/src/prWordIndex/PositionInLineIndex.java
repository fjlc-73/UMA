package prWordIndex;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class PositionInLineIndex extends AbstractIndex {
	
	private SortedMap<String, SortedMap<Integer,SortedSet<Integer>>> index;
	
	public PositionInLineIndex() {
		index = new TreeMap<>();
	}

	@Override
	public void solve(String s) {
		int numLine = 1;
		int numWord = 1;
		index.clear();
		Iterator<String> iter = sentences.iterator();
		while(iter.hasNext()) {
			try (Scanner sc = new Scanner(iter.next())){
				sc.useDelimiter(s);
				while(sc.hasNext()) {
					String ss = sc.next().toLowerCase();
					SortedMap<Integer,SortedSet<Integer>> map = index.getOrDefault(ss, new TreeMap<Integer,SortedSet<Integer>>());
					SortedSet<Integer> set = map.getOrDefault(numLine, new TreeSet<Integer>());
					set.add(numWord);
					map.putIfAbsent(numLine, set);
					index.putIfAbsent(ss, map);
					numWord++;
			}
				numLine++;
				numWord=1;
			
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
