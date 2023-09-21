package friendsc;

import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

public class Controller implements ActionListener, PropertyChangeListener {

	private Worker w = null;
	private Panel panel;

	public Controller(Panel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("END")) {
			if (w != null) {
				panel.setMessage("Operation cancelled");
				w.cancel(true);
				w = null;
			}
		} else {
			panel.cleanArea();
			w = new Worker(panel.getNumber(), panel);
			w.addPropertyChangeListener(this);
			w.execute();
		}

	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		if(e.getPropertyName().equals("progress")) {
			int progress = (Integer)e.getNewValue();
			panel.setMyProgress(progress);
		}
		
	}

}
