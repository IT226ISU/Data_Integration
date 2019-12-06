package it226;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Relationship {
	private Concept c1,c2;
	private String RELA;
	private static Map<String,List<Concept[]>> map=new LinkedHashMap<String, List<Concept[]>>();
	public Relationship(Concept c1,String RELA, Concept c2) {
		this.c1=c1;
		this.RELA=RELA;
		this.c2=c2;
		Concept[] c=new Concept[] {c1,c2};
		if (map.containsKey(RELA)){
			map.get(RELA).add(c);
		}else {
			List<Concept[]> temp=new ArrayList<Concept[]>();
			temp.add(c);
			map.put(RELA,temp);
		}
	}
	public String getRELA() {
		return RELA;
	}
	public Concept getCUI() {
		return c1;
	}
	public Concept getCUI2() {
		return c2;
	}
	@Override
	public String toString() {
		return c1.getSTR()+" "+RELA+" "+c2.getSTR();
	}
	public static List<Concept[]> getAll(String relationship){
		return map.get(relationship);
	}
	public static boolean containsRelationship(String RELA,Concept CUI1,Concept CUI2) {
		Concept[] c=new Concept[] {CUI1,CUI2};
		List<Concept[]> concept=map.get(RELA);
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
}
