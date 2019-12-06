package it226;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DatabaseManager {
	private Map<Concept,List<Relationship>> relMap=new LinkedHashMap<Concept, List<Relationship>>();
	private Map<String,Concept> map=new LinkedHashMap<String, Concept>();
	//private List<List<String>> string=new ArrayList<List<String>>();
	
	
	/**
	 * @param CUI The Concept Identifier
	 * @param STR The name or value of the CUI
	 */
	private Concept addTerm(String CUI, String STR){
		Concept c=new Concept(CUI,STR);
		map.put(c.getCUI(),c);
		if(!relMap.containsKey(c)) {
			List<Relationship> relatedTo=new ArrayList<Relationship>();
			relMap.put(c,relatedTo);
		}
		return c;
	}
	/**
	 * @param CUI1 Concept Identifier 1
	 * @param RELA The relationship between the Strings  
	 * @param CUI2 Concept Identifier 2
	 */
	private Relationship addRelationship(Concept CUI1,String RELA,Concept CUI2){
		Relationship r=new Relationship(CUI1, RELA, CUI2);
		relMap.get(CUI1).add(r);
		if(CUI1!=CUI2) {//avoids including self referential relationships twice.
			relMap.get(CUI2).add(r);
		}
		return r;
	}
	public boolean hasCUI(String CUI) {
		return map.containsKey(CUI);
	}
	
	public Concept getConcept(Concept x) {
		for(Concept i:map.values()) {
			if (i.equals(x)){
				return i;
			}
		}
		return null;
		
	}
	public Concept getConcept(String CUI) {
		return map.get(CUI);
	}	
	/**
	 * Lookup Concept String by Identifier
	 * @param CUI The Concept Identifier
	 * @return The Concept's name/value
	 */
	public String getSTR(String CUI) {
		return map.get(CUI).getSTR();
	}
	
	public String readFile(File f) {
		if (!f.isFile()) {
			return f.toString()+" cannot be opened or is not a file.";
		}else if (!f.canRead()) {
			return "File: "+f.toString()+" cannot be read.";
		}
		try {
			int ctr=0;
			int ctr2=0;
			int ctr3=0;
			String x="Added: \n";
			String z="\nModified: \n";
			String w="";//warning String;
			String line;
			BufferedReader br = new BufferedReader(new FileReader(f));
			while ((line=br.readLine())!=null) {
				String[] lineC=line.split(",");
				if (lineC.length==2) {
					if (hasCUI(lineC[0])) {
						if (!getSTR(lineC[0]).equals(lineC[1])) {
							Concept concept=addTerm(lineC[0],lineC[1]);
							ctr2++;
							z+=concept.toString()+"\n";
						}
					}else {
						Concept concept=addTerm(lineC[0],lineC[1]);
						ctr++;
						x+=concept.toString()+"\n";
					}
				}
				if (lineC.length==4) {
					if (hasCUI(lineC[2])) {
						if (!getSTR(lineC[2]).equals(lineC[3])) {
							Concept concept=addTerm(lineC[2],lineC[3]);
							ctr2++;
							z+=concept.toString()+"\n";
						}
					}else {
						Concept concept=addTerm(lineC[2],lineC[3]);
						ctr++;
						x+=concept.toString()+"\n";
					}
					if (hasCUI(lineC[0])) {
						Concept c0=getConcept(lineC[0]);
						Concept c1=getConcept(lineC[2]);
						if (!Relationship.containsRelationship(lineC[1], c0, c1)) {
							Relationship r=addRelationship(c0, lineC[1], c1);
							ctr++;
							x+=r.toString()+"\n";
						}
					}
					else {
						Concept c=addTerm(lineC[0],lineC[0]);
						Concept c1=getConcept(lineC[2]);
						Relationship r=addRelationship(c, lineC[1], c1);
						ctr3++;
						ctr++;
						w+="Warning "+ ++ctr3+"\t"+"Concept "+c.getCUI()+" could not be found. Created Temporary Concept.\n";
						x+=r.toString()+"\n";
					}
					
				}
			}
			br.close();

			String y="Added a total of "+ctr+" Terms/Relationships\n";
			if (ctr2!=0) {
				y+="Modified a total of "+ctr2+" Terms/Relationships\n";
				x+=z;
			}
			if (ctr3!=0) {
				y+="Warnings: "+ctr3+"\n";
				x+=w;
			}
			return y+"\n"+x;
		} catch (Exception e) {
			System.out.println(e.getCause());
			System.out.println(e.getStackTrace());
			return "Problem Parsing File";
		}
		
	}
	
	
	public void resetDatabase() {}

}
