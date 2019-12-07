package it226;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class DatabaseManager {
	private Map<Concept,List<Relationship>> cMap=new LinkedHashMap<Concept, List<Relationship>>();//Concept, List of Relationships
	private Map<String,Concept> map=new LinkedHashMap<String, Concept>();//CUI,Concept
	private Map<String,List<Concept[]>> relMap=new LinkedHashMap<String, List<Concept[]>>();//RELA,List of pairs of Concepts
	private Map<String,String> lookupMap=new LinkedHashMap<String,String>();//STR,CUI
	public static int STR=0;//In hindsight, this three lines
	public static int RELA=1;//might have been better to implement
	public static int CUI=2;//as an enumerator.
	/**
	 * @param CUI The Concept Identifier
	 * @param STR The name or value of the CUI
	 */
	private Concept addTerm(String CUI, String STR){
		
		Concept c=new Concept(CUI,STR);
		lookupMap.put(c.getSTR(),c.getCUI());
		map.put(c.getCUI(),c);
		if(!cMap.containsKey(c)) {
			List<Relationship> relatedTo=new ArrayList<Relationship>();
			cMap.put(c,relatedTo);
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
		cMap.get(CUI1).add(r);
		if(!CUI1.equals(CUI2)) {//avoids including self referential relationships twice.
			cMap.get(CUI2).add(r);
		}
		Concept[] c=new Concept[] {CUI1,CUI2};
		if (relMap.containsKey(RELA)){
			relMap.get(RELA).add(c);
		}else {
			List<Concept[]> temp=new ArrayList<Concept[]>();
			temp.add(c);
			relMap.put(RELA,temp);
		}
		return r;
	}
	public boolean hasCUI(String CUI) {
		return map.containsKey(CUI);
	}
	public Vector<String> getVector(int i) {
		if(i==STR) {
			return new Vector<String>(lookupMap.keySet());
		}
		else if(i==RELA) {
			return new Vector<String>(relMap.keySet());
		}
		else if(i==CUI) {
			return new Vector<String>(map.keySet());
		}
		else{
			return null;
		}
	}
	
	public String lookupR(String r) {
		String x="";
		List<Concept[]> list=relMap.get(r);
		if (list!=null) {
			for (Concept[] i:list) {
				x+=i[0].getSTR()+","+r+","+i[1].getSTR()+"\n";
			}
		}
		return x;
	}
	public String lookupC(String c) {
		String x="";
		List<Relationship> r=cMap.get(map.get(c));
		if (r!=null) {
			for(Relationship i:r) {
				x+=i.toString()+"\n";
			}
		}
		return x;
	}
	public String lookupT(String c) {
		String x="";
		List<Relationship> r=cMap.get(map.get(lookupMap.get(c)));//and the prize for most map lookups goes to...
		if (r!=null) {
			for(Relationship i:r) {
				x+=i.toString()+"\n";
			}
		}
		return x;
	}
	public Concept getConcept(Concept x) {
		for(Concept i:map.values()) {
			if (i.equals(x)){
				return i;
			}
		}
		return null;
		
	}
	public String writeCSV(int type,String val, File f) {
		try {
			f.createNewFile();
			FileWriter csvWriter = new FileWriter(f);
			csvWriter.append("STR1");
			csvWriter.append(",");
			csvWriter.append("RELA");
			csvWriter.append(",");
			csvWriter.append("STR2");
			csvWriter.append("\n");
			if (type==STR) {
				csvWriter.append(lookupT(val));
			}
			if (type==RELA) {
				csvWriter.append(lookupR(val));
			}
			if (type==CUI) {
				csvWriter.append(lookupC(val));
			}
			csvWriter.flush();
			csvWriter.close();
			return f.toString();
			
		}catch(Exception e){
			return "Export failed.";
		}
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
	private boolean containsRelationship(String RELA,Concept CUI1,Concept CUI2) {
		Concept[] c=new Concept[] {CUI1,CUI2};
		List<Concept[]> concept=relMap.get(RELA);
		if (concept==null) {
			return false;
		}
		for(Concept[] i:concept) {
			if (Arrays.deepEquals(c, i)){
				return true;
			}
		}
		return false;
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
						if (!containsRelationship(lineC[1], c0, c1)) {
							Relationship r=addRelationship(c0, lineC[1], c1);
							ctr++;
							x+=r.toString()+"\n";
						}
					}
					else {
						Concept c=addTerm(lineC[0],lineC[0]);
						Concept c1=getConcept(lineC[2]);
						Relationship r=addRelationship(c, lineC[1], c1);
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

}
