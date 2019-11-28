package it226;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Run {
	JButton addButton,saveRButton,saveTButton;
	private void createAndShowGUI() {
		JFrame frame = new JFrame("Data Integration");
		JPanel pane=new JPanel();
		
		addButton=new JButton("Add Data");
	
		frame.add(pane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	
	
	
	public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
		
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	Run r=new Run();
                r.createAndShowGUI();
            }
        });
    }
}
