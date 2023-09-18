package ex1;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.*;


public class Controller implements ActionListener{

	private WorkerSelection ws= null;
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
			if(n>=1 && n<=60000) {
				Random r = new Random();
				l = new ArrayList<>(n);
				for(int i = 0; i < n; i++) {
					l.add(r.nextInt(100000));
				}
				panel.writeTextArea(l);
				panel.clearAreaBubble();
				panel.clearAreaSelection();
				panel.messageArea("List created");
				panel.comment("Number accepted");
			} else {
				panel.clearSize();
				panel.clearAreaBubble();
				panel.clearAreaSelection();
				panel.comment("Number entered is INVALID");
				panel.messageArea("");
			}	
		}
		if(e.getActionCommand().equals("SORT")) {
			panel.clearAreaBubble();
			panel.clearAreaSelection();
			ws = new WorkerSelection(panel, l);
			wb = new WorkerBubble(panel, l);
			ws.execute();
			wb.execute();
			
		}

	}


}
