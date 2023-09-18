package ex1;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class WorkerBubble extends SwingWorker<java.util.List<Integer>, Void> {

	private int n;
	private Panel panel;
	private List<Integer> list;
	private long startTime;

	public WorkerBubble(Panel panel, List<Integer> l) {
		list = new ArrayList<>(l.size());
		list.addAll(l);
		this.panel = panel;
	}

	private void sort(List<Integer> l) {
		for (int i = 0; i < l.size() - 1; i++) {
			// we run the array a[i.. N-1] from position N-1 to i.
			// We compare the elements two by two and exchange them if // are unsorted
			for (int j = l.size() - 1; j > i; j--) {
				if (l.get(j) < l.get(j - 1)) {
					int aux = l.get(j);
					l.set(j, l.get(j - 1));
					l.set(j - 1, aux);
				}
			}
			// Inv: a[0..i] contains the i+1 lowest elements of a[0..N-1]
			// Inv: a[0..i] is ordered
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
			panel.writeTextAreaBubble(get());
			panel.messageAreaBubble("List sorted in " + (System.currentTimeMillis() - startTime) + " ms");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
