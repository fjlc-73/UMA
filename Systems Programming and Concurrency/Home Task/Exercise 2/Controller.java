package ex2;

import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.*;

public class Controller implements ActionListener, PropertyChangeListener {

	private WorkerBubble wb = null;
	private Panel panel;
	private List<Integer> l = null;

	public Controller(Panel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("CREATE")) {
			int n;
			panel.clearArea();
			n = panel.getArrayLength();
			if (n >= 1 && n <= 60000) {
				Random r = new Random();
				l = new ArrayList<>(n);
				for (int i = 0; i < n; i++) {
					l.add(r.nextInt(100000));
				}
				panel.writeTextArea(l);
				panel.clearAreaBubble();
				panel.clearAreaSelection();
				panel.messageAreaBubble("");
				panel.messageAreaSelection("");
				panel.setProgressbubble(0);
				panel.setProgressSelection(0);
				panel.messageArea("List created");
				panel.comment("Number accepted");

			} else {
				panel.clearSize();
				panel.clearAreaBubble();
				panel.clearAreaSelection();
				panel.messageAreaBubble("");
				panel.messageAreaSelection("");
				panel.setProgressbubble(0);
				panel.setProgressSelection(0);
				panel.comment("Number entered is INVALID");
				panel.messageArea("");
			}
		}
		if (e.getActionCommand().equals("SORT")) {
			panel.clearAreaBubble();
			panel.clearAreaSelection();
			wb = new WorkerBubble(panel, l);
			wb.addPropertyChangeListener(this);
			wb.execute();
		}

	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		if (e.getPropertyName().equals("progress")) {
			int progress = (Integer) e.getNewValue();
			panel.setProgressbubble(progress);
			

		}

	}

}
