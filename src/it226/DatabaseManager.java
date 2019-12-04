package it226;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DatabaseManager {
	private Map<String,String> concepts=new LinkedHashMap<String,String>();
	private List<String[]> relationships=new ArrayList<String[]>();
	
	
	/**
	 * @param CUI The Concept Identifier
	 * @param STR The name or value of the CUI
	 */
	public void addTerm(String CUI, String STR){
		if(concepts.containsKey(CUI)) {
			if (concepts.get(CUI).equals(STR)){
				//Duplicate;
			}else {
				 
			}
		}else {
			concepts.put(CUI, STR);
			DataItem term= new DataItem(CUI,STR);
			
		}
	}
	
	/**
	 * @param CUI1 Concept Identifier 1
	 * @param RELA The relationship between the Strings  
	 * @param CUI2 Concept Identifier 2
	 */
	public void addRelationship(String CUI1,String RELA,String CUI2) {
		
	}
	
	/**
	 * Lookup Concept String by Identifier
	 * @param CUI The Concept Identifier
	 * @return The Concept's name/value
	 */
	public String getSTR(String CUI) {
		return null;
	}
	/**
	 * Lookup CUI by Name of Term
	 * @param STR
	 * @return
	 */
	public String getCUI(String STR) {
		return null;
	}
	
	
	
	public String getREL(String CUI1,String CUI2) {
		return null;
	}
	public String[] getRELS(String CUI1) {
		return null;
	}
	public void readFile(File f) throws FileNotFoundException,AccessDeniedException {
		if (!f.isFile()) {
			throw new FileNotFoundException("This file cannot be found.");
		}else if (!f.canRead()){
			throw new AccessDeniedException(f.getAbsolutePath().toString());
		}else {
			try {
				BufferedReader br = new BufferedReader(new FileReader(f));
				String x=br.readLine();
				x.split(",");
				br.close();
			} catch(Exception e) {
				
			}
		}
	}
	
	
	public void resetDatabase() {}

}
