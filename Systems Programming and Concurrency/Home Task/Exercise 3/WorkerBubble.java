package ex3;

import javax.swing.*;


import java.util.*;
import java.util.concurrent.ExecutionException;

public class WorkerBubble extends SwingWorker<java.util.List<Integer>, Integer> {

	private int n;
	private Panel panel;
	private List<Integer> list;
	private long startTime;

	public WorkerBubble(Panel panel, List<Integer> l) {
		list = new ArrayList<>(l.size());
		list.addAll(l);
		this.panel = panel;
	}

	private void sort(List<Integer> l) throws Exception {
		for (int i = 0; i < l.size() - 1; i++) {
			Thread.sleep(1);
			if(isCancelled()) {
				break;
			}

			// we run the array a[i.. N-1] from position N-1 to i.
			// We compare the elements two by two and exchange them if // are unsorted
			for (int j = l.size() - 1; j > i; j--) {
				if(isCancelled()) {
					break;
				}
				if (l.get(j) < l.get(j - 1)) {
					int aux = l.get(j);
					l.set(j, l.get(j - 1));
					l.set(j - 1, aux);
				}
			}
			// Inv: a[0..i] contains the i+1 lowest elements of a[0..N-1]
			// Inv: a[0..i] is ordered
			publish(l.get(i));
			this.setProgress(Math.min(100, 100*(i+2)/l.size()));
		}
	}

	@Override
	protected java.util.List<Integer> doInBackground() throws Exception {
		panel.messageAreaBubble("Sorting the list");
		startTime = System.currentTimeMillis();
		sort(this.list);
		return this.list;
	}

	public void done() {
		try {
			panel.clearAreaBubble();
			panel.writeTextAreaBubble(get());
			panel.messageAreaBubble("List sorted in " + (System.currentTimeMillis() - startTime) + " ms");
			panel.enableCreateButton();
			panel.disableCancelButton();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void process(List<Integer> list) {
		panel.writeTextAreaBubble(list);
	}

}
