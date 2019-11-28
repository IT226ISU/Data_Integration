package it226;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Run implements ActionListener{
	JButton addButton,saveRButton,saveTButton,exportRButton,exportTButton;
	JPanel mainPanel,buttonPanel,viewerPanel;
	JScrollPane viewerScrollPane;
	JTextArea viewer;
	JTextField lookupTR;
	ButtonGroup lookupButtonGroup;
	JRadioButton lookupT,lookupR;
	GridBagLayout layout1;
	private Run() {
		createAndShowGUI();
	}
	
	private void createAndShowGUI() {
		JFrame frame = new JFrame("Data Integration");
		layout1=new GridBagLayout();
		JPanel pane=new JPanel(layout1);
		addButton=buttonMaker("Add Data from File","This button allows you to add data from a csv file");
		saveRButton=buttonMaker("Add New Relationship","This button allows you to input a new relationship");
		saveTButton=buttonMaker("Add New Term","This button allows you to input a new Term");
		exportRButton=buttonMaker("Export Relationship Data","This button allows you to export data pertaining to a Relationship");
		exportTButton=buttonMaker("Export Term Data","This button allows you to export data pertaining to a Term");
		
		frame.add(pane);
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
