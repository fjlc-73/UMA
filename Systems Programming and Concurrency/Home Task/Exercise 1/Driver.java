package ex1;


import java.lang.reflect.InvocationTargetException;

import javax.swing.*;

public class Driver {

	public static void createGUI(JFrame window){
		Panel panel = new Panel();
		Controller ctr = new Controller(panel);
		panel.setController(ctr);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(panel);
		window.pack();
		window.setVisible(true);
	}
	
	
	
	public static void main(String[] args) {
		final JFrame window = new JFrame("Bubble sort VS Selection sort");
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				createGUI(window);
			}
		});
		
		
	}
	
	

}
