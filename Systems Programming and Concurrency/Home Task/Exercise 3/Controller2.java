package ex3;

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

	public Controller2(Panel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("SORT")) {
			ws = new WorkerSelection(panel, Controller.l);
			ws.addPropertyChangeListener(this);
			ws.execute();
		}
		if(e.getActionCommand().equals("CANCEL")) {
			ws.cancel(true);
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

