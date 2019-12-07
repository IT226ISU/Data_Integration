package it226;



import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Run implements ActionListener{
	//JButton addButton,addRButton,addTButton,exportRButton,exportTButton,exportXMLButton;//ButtonBox
	JButton addButton,lookupButton,exportButton;
	JButton okButton,cancelButton;
	JPanel cards, mainPanel, addPanel, exportPanel;
	JScrollPane viewerScrollPane;
	JTextArea viewer; 
	JTextField lookupTRC,exportTRC;
	ButtonGroup lookupButtonGroup,exportButtonGroup;
	JRadioButton lookupT,lookupR,lookupC;
	JRadioButton exportT,exportR,exportC;
	JFileChooser fc;
	DatabaseManager d;
	
	private Run() {
		createAndShowGUI();
	}
	
	private void createAndShowGUI() {
		d=new DatabaseManager();
		JFrame frame = new JFrame("Data Integration");
		fc=new JFileChooser();
		FileFilter f=new FileNameExtensionFilter("CSV File","csv");
		//FileFilter f2=new FileNameExtensionFilter("XML","xml");
		fc.setFileFilter(f);
		//fc.addChoosableFileFilter(f2);
		fc.setAcceptAllFileFilterUsed(false);
		mainPanel=createMainPanel();
		//addPanel=createAddPanel();
		exportPanel=createExportPanel();
		cards=new JPanel(new CardLayout());
		cards.add(mainPanel,"main");
		//cards.add(addPanel,"add");
		cards.add(exportPanel,"export");
		
		frame.add(cards);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	private JPanel createMainPanel() {
		
		//button Panel;
		addButton=buttonMaker("Add Data from File","This button allows you to add data from a csv file");
		//addRButton=buttonMaker("Add New Relationship","This button allows you to input a new relationship");
		//addTButton=buttonMaker("Add New Term","This button allows you to input a new Term");
		//exportXMLButton=buttonMaker("Export Database","This button exports the database as an xml file");
		//exportRButton=buttonMaker("Export Relationship Data","This button allows you to export data pertaining to a Relationship");
		//exportTButton=buttonMaker("Export Term Data","This button allows you to export data pertaining to a Term");
		lookupButton=buttonMaker("Search", "Search the database");
		exportButton=buttonMaker("Export","Export the lookup data");
		
		//GridLayout buttonGrid=new GridLayout(2,3);
		GridLayout buttonGrid=new GridLayout(1,2);
		JPanel buttonPanel=new JPanel(buttonGrid);
		buttonPanel.add(addButton);
		buttonPanel.add(exportButton);
		//buttonPanel.add(addTButton);
		//buttonPanel.add(addRButton);
		//exportXMLButton.setEnabled(false);//not actually using this button, but I need it for the layout
		//exportXMLButton.setVisible(false);//hide the button so it doesn't show up.
		//buttonPanel.add(exportXMLButton);//add it so the alignment of the GridLayout stays as desired
		//buttonPanel.add(exportTButton);
		//buttonPanel.add(exportRButton);
		//Radio Buttons
		lookupT=new JRadioButton("STR");
		lookupT.setSelected(true);
		lookupR=new JRadioButton("RELA");
		lookupC=new JRadioButton("CUI");
		//lookupT.addActionListener(this);
		//lookupR.addActionListener(this);
		//lookupC.addActionListener(this);
		lookupButtonGroup=new ButtonGroup();
		lookupButtonGroup.add(lookupT);
		lookupButtonGroup.add(lookupR);
		lookupButtonGroup.add(lookupC);
		JPanel radioPanel=new JPanel(new FlowLayout());
		radioPanel.add(lookupT);
		radioPanel.add(lookupR);
		radioPanel.add(lookupC);
		//Other Components
		JPanel lookup=new JPanel(new FlowLayout());
		JLabel label=new JLabel("Lookup: ");
		JLabel label2=new JLabel("By: ");
		lookupTRC=new JTextField(15);
		lookupTRC.requestFocusInWindow();
		
		lookup.add(label);
		lookup.add(lookupTRC);
		lookup.add(label2);
		lookup.add(radioPanel);
		lookup.add(lookupButton);
		//Results box
		viewer=new JTextArea(25,25);
		viewer.setEditable(false);
		viewerScrollPane=new JScrollPane(viewer);
		
		JPanel main=new JPanel(new BorderLayout());
		main.add(lookup,BorderLayout.PAGE_START);
		main.add(viewerScrollPane,BorderLayout.CENTER);
		main.add(buttonPanel,BorderLayout.PAGE_END);
		return main;
	}
	private JPanel createExportPanel() {
		
		cancelButton=buttonMaker("Cancel", "Return to Main Page");
		okButton=buttonMaker("Export", "Save the data to the specified file");
		GridLayout buttonGrid=new GridLayout(1,2);
		JPanel buttonPanel=new JPanel(buttonGrid);
		buttonPanel.add(cancelButton);
		buttonPanel.add(okButton);
		
		
		exportT=new JRadioButton("STR");
		exportT.setSelected(true);
		exportR=new JRadioButton("RELA");
		exportC=new JRadioButton("CUI");
		//lookupT.addActionListener(this);
		//lookupR.addActionListener(this);
		//lookupC.addActionListener(this);
		exportButtonGroup=new ButtonGroup();
		exportButtonGroup.add(exportT);
		exportButtonGroup.add(exportR);
		exportButtonGroup.add(exportC);
		JPanel radioPanel=new JPanel(new FlowLayout());
		exportTRC=new JTextField(15);
		exportTRC.requestFocusInWindow();
		
		radioPanel.add(exportT);
		radioPanel.add(exportR);
		radioPanel.add(exportC);
		JLabel label=new JLabel("Export by: ");
		JPanel headPanel=new JPanel(new FlowLayout());
		headPanel.add(label);
		headPanel.add(radioPanel);
		
		
		//JComboBox<String> exportItemT=new JComboBox<String>(d.getVector(DatabaseManager.STR));
		JPanel export = new JPanel(new BorderLayout());
		export.add(headPanel,BorderLayout.PAGE_START);
		export.add(buttonPanel,BorderLayout.SOUTH);
		return export;
	}
	

	private JButton buttonMaker(String displayText,String toolTip) {
		JButton button=new JButton(displayText);
		button.setToolTipText(toolTip);
		button.addActionListener(this);
		return button;
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		CardLayout c = (CardLayout)(cards.getLayout());
		if (e.getSource().equals(addButton)){
			int returnVal=fc.showOpenDialog(null);
			if (returnVal==JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				String readStatus=d.readFile(file);
				viewer.setText(readStatus);
			} else {
				viewer.setText("Open command cancelled by user.");
			}
			viewer.setCaretPosition(viewer.getDocument().getLength());
		}
		else if (e.getSource().equals(exportButton)){
			String x="";
			int returnVal=fc.showSaveDialog(null);
			if (returnVal==JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				if (lookupTRC.getText()!=null) {
					if (lookupT.isSelected()) {
						x=d.writeCSV(DatabaseManager.STR,lookupTRC.getText(), file);
					}
					else if (lookupR.isSelected()) {
						x=d.writeCSV(DatabaseManager.RELA,lookupTRC.getText(), file);
					}
					else if (lookupC.isSelected()) {
						x=d.writeCSV(DatabaseManager.RELA,lookupTRC.getText(), file);
					}
				}
				viewer.setText(x);
			} else {
				viewer.setText("Open command cancelled by user.");
			}
			viewer.setCaretPosition(viewer.getDocument().getLength());
		}
		else if(e.getSource().equals(cancelButton)) {
			c.show(cards, "main");
		}
		//else if (e.getSource().equals(exportXMLButton)) {}
//		else if (e.getSource().equals(lookupT)) {
//			viewer.setText("t");
//		}
//		else if(e.getSource().equals(lookupR)){
//			viewer.setText("r");
//		}
//		else if(e.getSource().equals(lookupC)){
//			viewer.setText("c");
//		}
		else if(e.getSource().equals(lookupButton)) {
			if (lookupT.isSelected()) {
				if (lookupTRC.getText()!=null){
					viewer.setText(d.lookupT(lookupTRC.getText()).replace(",", " "));
				}
			}
			if (lookupR.isSelected()) {
				if (lookupTRC.getText()!=null){
					viewer.setText(d.lookupR(lookupTRC.getText()).replace(",", " "));
				}
			}else if (lookupC.isSelected()) {
				if (lookupTRC.getText()!=null){
					viewer.setText(d.lookupC(lookupTRC.getText()).replace(",", " "));
				}
			}
		}
			
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
