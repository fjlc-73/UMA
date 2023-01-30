package prWordIndex;

import java.io.PrintWriter;

public interface Index {
	
	public void addSentence(String s);
	
	public void solve(String s);
	
	public void presentIndex();
	
	public void presentIndex(PrintWriter pw);

}
