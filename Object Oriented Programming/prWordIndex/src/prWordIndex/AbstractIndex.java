package prWordIndex;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractIndex implements Index {
	
	protected List<String> sentences;

	public AbstractIndex() {
		sentences = new LinkedList<>();
	}

	
	public void addSentence(String s) {
		sentences.add(s);

	}


	public abstract void solve(String s);
	


	public void presentIndex() {
	 try (PrintWriter pw = new PrintWriter(System.out,true)) {
			presentIndex(pw);
	 } 
 }


	public abstract void presentIndex(PrintWriter pw);
	

}
