package prWordIndex;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

public class CounterIndex extends AbstractIndex {
	
	private SortedMap<String,Integer> index;

	public CounterIndex() {
		index = new TreeMap<>();
	}

	@Override
	public void solve(String s) {
		index.clear();
		Iterator<String> iter = sentences.iterator();
		while(iter.hasNext()) {
			try (Scanner sc = new Scanner(iter.next())){
				sc.useDelimiter(s);
				while(sc.hasNext()) {
					String ss = sc.next().toLowerCase();
					int i=index.getOrDefault(ss, 0);
					index.put(ss, i+1);
				}
			} 
			
		}

	}

	@Override
	public void presentIndex(PrintWriter pw) {
		for(String s: index.keySet()) {
			System.out.println(String.format("%s \t %s", s, index.get(s)));
		}
        
	}

}
