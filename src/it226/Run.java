package it226;



import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Run implements ActionListener{
	JButton addButton,addRButton,addTButton,exportRButton,exportTButton,resetButton;
	
	JPanel mainPanel,buttonPanel,viewerPanel;
	JScrollPane viewerScrollPane;
	//JTextArea viewer; Will add if there is time.
	//JTextField lookupTR;
	//ButtonGroup lookupButtonGroup;
	//JRadioButton lookupT,lookupR;
	
	private Run() {
		createAndShowGUI();
	}
	
	private void createAndShowGUI() {
		JFrame frame = new JFrame("Data Integration");
		
		addButton=buttonMaker("Add Data from File","This button allows you to add data from a csv file");
		addRButton=buttonMaker("Add New Relationship","This button allows you to input a new relationship");
		addTButton=buttonMaker("Add New Term","This button allows you to input a new Term");
		exportRButton=buttonMaker("Export Relationship Data","This button allows you to export data pertaining to a Relationship");
		exportTButton=buttonMaker("Export Term Data","This button allows you to export data pertaining to a Term");
		
		resetButton=buttonMaker("Reset Database","This will wipe the database and cannot be undone");
		resetButton.setEnabled(false);
		resetButton.setVisible(false);
		
		
		GridLayout buttonGrid=new GridLayout(2,3);
		buttonPanel=new JPanel(buttonGrid);
		
		buttonPanel.add(addButton);
		buttonPanel.add(addTButton);
		buttonPanel.add(addRButton);
		buttonPanel.add(resetButton);
		buttonPanel.add(exportTButton);
		buttonPanel.add(exportRButton);
		
		frame.add(buttonPanel,BorderLayout.PAGE_END);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	
	JButton buttonMaker(String displayText,String toolTip) {
		JButton button=new JButton(displayText);
		button.setToolTipText(toolTip);
		button.addActionListener(this);
		return button;
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
		
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	new Run();
            }
        });
    }
}
