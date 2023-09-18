package ex2;

import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.*;

public class Controller2 implements ActionListener, PropertyChangeListener {

	private WorkerSelection ws = null;
	private Panel panel;
	private List<Integer> l = null;

	public Controller2(Panel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("CREATE")) {
			int n;
			n = panel.getArrayLength();
			if (n >= 1 && n <= 60000) {
				Random r = new Random();
				l = new ArrayList<>(n);
				for (int i = 0; i < n; i++) {
					l.add(r.nextInt(100000));
				}
			} 
		}
		if (e.getActionCommand().equals("SORT")) {
			ws = new WorkerSelection(panel, l);
			ws.addPropertyChangeListener(this);
			ws.execute();
		}

	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		if (e.getPropertyName().equals("progress")) {
			int progress = (Integer) e.getNewValue();
			panel.setProgressSelection(progress);
			


		}

	}

}
