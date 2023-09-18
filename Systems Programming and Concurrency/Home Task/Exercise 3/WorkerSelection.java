package ex3;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class WorkerSelection extends SwingWorker<java.util.List<Integer>, Integer> {

	private int n;
	private Panel panel;
	private List<Integer> list;
	private long startTime;

	public WorkerSelection(Panel panel, List<Integer> l) {
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

			// We look for the smallest element of the array a[i.. N-1] // and we swap it
			// with a[i]
			int lowest = i;
			for (int j = i + 1; j < l.size(); j++) {
				if(isCancelled()) {
					break;
				}

				if (l.get(j) < l.get(lowest))
					lowest = j;
			}
			int aux = l.get(i);
			l.set(i, l.get(lowest));
			l.set(lowest, aux);

			// Inv: a[0..i] contains the i+1 lowest elements of a[0..N-1] //Inv: a[0..i] is
			// ordered
			publish(l.get(i));
			this.setProgress(Math.min(100, 100*(i+2)/l.size()));
		}
	}

	@Override
	protected java.util.List<Integer> doInBackground() throws Exception {
		panel.messageAreaSelection("Sorting the list");
		startTime = System.currentTimeMillis();
		sort(this.list);
		return this.list;
	}

	public void done() {
		try {
			panel.clearAreaSelection();
			panel.writeTextAreaSelection(get());
			panel.messageAreaSelection("List sorted in " + (System.currentTimeMillis() - startTime) + " ms");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void process(List<Integer> list) {
		panel.writeTextAreaSelection(list);
	}

}
