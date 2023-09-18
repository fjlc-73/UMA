package ex2;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class Panel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private JLabel label = new JLabel("List size (1--60000 elems):");

	private JTextField size = new JTextField(8);
	private JButton createButton = new JButton("Create");
	private JButton sortButton = new JButton("Sort");
	private JButton cancelButton = new JButton("Cancel");
	
	private JTextArea area = new JTextArea(20,35);
	private JTextArea areaSelection = new JTextArea(20,35);
	private JTextArea areaBubble = new JTextArea(20,35);
	private JProgressBar progress = new JProgressBar(0,100);
	private JProgressBar progressSelection = new JProgressBar(0,100);
	private JProgressBar progressBubble = new JProgressBar(0,100);

	private JLabel messageArea = new JLabel("");
	private JLabel messageAreaSelection = new JLabel("");
	private JLabel messageAreaBubble = new JLabel("");
	private JLabel comment = new JLabel("GUI created");
	
	public Panel(){
		this.setLayout(new BorderLayout());
		comment.setForeground(Color.RED);
		setCreateButton();
		setSortButton();
		setCancelButton();
		disableSortButton();
		disableCancelButton();
		JPanel topRow=new JPanel();
		topRow.add(label);
		topRow.add(size);
		topRow.add(createButton);
		topRow.add(sortButton);
		topRow.add(cancelButton);
		
		
		this.add(topRow,BorderLayout.NORTH);
		
		JPanel center = new JPanel();
		JPanel c1=new JPanel();
		c1.setLayout(new BorderLayout());
		JScrollPane scrol1 = new JScrollPane(area);
		scrol1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrol1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		c1.add(scrol1,BorderLayout.CENTER);
		JPanel c11 = new JPanel();
		c11.setLayout(new GridLayout(3,1));
		c11.add(new JLabel("Array to be sorted"));
		c11.add(progress);
		c11.add(messageArea);
		c1.add(c11,BorderLayout.SOUTH);
		
		JPanel c2=new JPanel();
		c2.setLayout(new BorderLayout());
		JScrollPane scrol2 = new JScrollPane(this.areaSelection);
		scrol2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrol2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		c2.add(scrol2,BorderLayout.NORTH);
		JPanel c21 = new JPanel();
		c21.setLayout(new GridLayout(3,1) );
		c21.add((new JLabel("Selection sort")));
		c21.add(progressSelection);
		c21.add(messageAreaSelection);
		c2.add(c21,BorderLayout.SOUTH);
		
		
		JPanel c3=new JPanel();
		c3.setLayout(new BorderLayout());
		JScrollPane scrol3 = new JScrollPane(this.areaBubble);
		scrol3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrol3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		c3.add(scrol3,BorderLayout.NORTH);
		JPanel c31 = new JPanel();
		c31.setLayout(new GridLayout(3,1) );
		c31.add((new JLabel("Bubble sort")));
		c31.add(progressBubble);
		c31.add(messageAreaBubble);
		c3.add(c31,BorderLayout.SOUTH);

		
		center.add(c1);
		center.add(c2);
		center.add(c3);
		this.add(center,BorderLayout.CENTER);
		this.add(comment,BorderLayout.SOUTH);
		
		area.setWrapStyleWord(true);
		area.setLineWrap(true);
		areaSelection.setWrapStyleWord(true);
		areaSelection.setLineWrap(true);
		areaBubble.setWrapStyleWord(true);
		areaBubble.setLineWrap(true);
		progressSelection.setStringPainted(true);
		progressBubble.setStringPainted(true);

	}


	public void disableCreateButton() {
	}
	public void disableSortButton() {
	}
	public void disableCancelButton() {
	}
	public void enableCreateButton() {
	}
	public void enableSortButton() {
	}
	public void enableCancelButton() {
	}
	public void setCreateButton() {
		createButton.setActionCommand("CREATE");		
	}

	public void setSortButton() {
		sortButton.setActionCommand("SORT");
	}
	
	public void setCancelButton() {
	}
	
	public void setController(ActionListener ctr){
		createButton.addActionListener(ctr);
		sortButton.addActionListener(ctr);
	}
	
	public int getArrayLength(){
		//return the number introduced in the textfield
		//must be between 1 and 60000
		return Integer.parseInt(size.getText());
	}
	
	
	public void clearSize(){
		size.setText("");
	
	}
	public void clearArea(){
		area.setText("");
	
	}
	public void clearAreaSelection(){
		areaSelection.setText("");
	
	}
	public void clearAreaBubble(){
		this.areaBubble.setText("");
	
	}

	public void writeTextArea(java.util.List<Integer> lista) {
		for(Integer i : lista) {
			area.setText(area.getText() + " " + i.toString());
		}
	}
	public void writeTextAreaSelection(java.util.List<Integer> lista) {
		for(Integer i : lista) {
			areaSelection.setText(areaSelection.getText() + " " + i.toString());
		}
		
	}
	public void writeTextAreaBubble(java.util.List<Integer> lista) {
		for(Integer i : lista) {
			areaBubble.setText(areaBubble.getText() + " " + i.toString());
		}
	}
	
	public void messageArea(String texto) {
		this.messageArea.setText(texto);
	}
	public void messageAreaSelection(String texto) {
		this.messageAreaSelection.setText(texto);
	}
	public void messageAreaBubble(String texto) {
		this.messageAreaBubble.setText(texto);
	}

	public void comment(String texto) {
		comment.setText(texto);
	}

	public void setProgressSelection(int n) {
		progressSelection.setValue(n);	
	}
	
	public void setProgressbubble(int n) {
		progressBubble.setValue(n);
		
	}
	


	
}
