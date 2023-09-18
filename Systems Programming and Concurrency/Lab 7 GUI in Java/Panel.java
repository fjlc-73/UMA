package friendsc;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


import java.util.*;
import java.util.List;
public class Panel extends JPanel{
	
	private JLabel label = new JLabel("How many friends do you want?");
	private JLabel msg = new JLabel("GUI Created");
	private JTextField number = new JTextField(3);
	
	private JTextArea listOfFriends = new JTextArea(10,40);
	private JScrollPane scroll = new JScrollPane(listOfFriends);
	private JButton end = new JButton("Cancel");
	private JProgressBar progress = new JProgressBar(0,100);
	
	
	public Panel(){
		this.setLayout(new BorderLayout());
		JPanel north = new JPanel();
		north.add(label);north.add(number);
		north.add(end);
		end.setActionCommand("END");
		this.add(BorderLayout.NORTH, north);
	    this.add(BorderLayout.CENTER,  scroll);
		JPanel south = new JPanel();
		south.setLayout(new BorderLayout());
		progress.setStringPainted(true);
	    this.add(BorderLayout.SOUTH,  south);
	    south.add(BorderLayout.SOUTH, msg);
	    south.add(BorderLayout.NORTH, progress);
	}
	
	public void setController(ActionListener ctr){
		number.addActionListener(ctr);
		end.addActionListener(ctr);
	}
	public int getNumber() {
		return Integer.parseInt(number.getText());
	}

	public void writeFriends(List<Friends> list){
		for(Friends f : list) {
			listOfFriends.setText(listOfFriends.getText() + " " + f.toString());
		}
	}
	public void cleanArea(){
		listOfFriends.setText("");
	}
	public void setMessage(String str){
		msg.setText(str);
	}

	public void setMyProgress(int n) {
		progress.setValue(n);
	}
	
}
