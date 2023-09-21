package friendsc;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.CancellationException;
public class Worker extends SwingWorker<java.util.List<Friends>,Friends>{

	private int n;
	private Panel panel;
	public Worker(int n,Panel panel){
		this.n = n; 
		this.panel = panel;
	}
	private boolean sumDivisors(long a, long b){
		long sum = 0;
		int i = 1;
		while (i<a && sum <= b){
			if (a % i == 0) sum+=i;
			i++;
		}
		return sum == b && i == a;
	}
	
	private long sumDivisors(long a){
		long sum = 1;
		int i = 2;
		while (i<a){
			if (a % i == 0) sum+=i;
			i++;
		}
		return sum;
	}
	@Override
	protected java.util.List<Friends> doInBackground() throws Exception {
		int b = 2;
		java.util.List<Friends> ret = new ArrayList<>();
		int friendCount = 0;
		boolean finished = false;
		while(!finished && !isCancelled()) {
			int a = 1;
			while(a<b && !finished && !isCancelled()) {
				if(sumDivisors(a,b) && sumDivisors(b,a)) {
					Friends f = new Friends(a,b,friendCount);
					publish(f);
					ret.add(f);
					friendCount++;
					this.setProgress(Math.min(100, 100*friendCount/this.n));
					finished = (friendCount == this.n);
				}
				a++;
			}
			b++;
		}		
		return ret;
	}
	
	// tratar la excepcion CancellationException
	public void done(){
		try {
			panel.cleanArea();
			panel.writeFriends(get());
			panel.setMessage("Operation Finished");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	protected void process(List<Friends> list) {
		panel.writeFriends(list);
	}

}
