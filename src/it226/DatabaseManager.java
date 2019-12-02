package it226;

import java.util.LinkedHashMap;
import java.util.Map;

public class DatabaseManager {
	Map<String,String> Concepts=new LinkedHashMap<String,String>();
	/**
	 * @param CUI The Concept Identifier
	 * @param STR The name or value of the CUI
	 */
	public void addTerm(String CUI, String STR){
		
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
	
	/**
	 * @param CUI1
	 * @param RELA
	 * @param CUI2
	 */
	public void addRelationship(String CUI1,String RELA,String CUI2) {
		
	}
	
	public String getREL(String CUI1,String CUI2) {
		return null;
	}
	public String[] getRELS(String CUI1) {
		return null;
	}
	public String[]
	public void resetDatabase() {}

}
