package it226;


public class Relationship {
	private Concept c1,c2;
	private String RELA;
	public Relationship(Concept c1,String RELA, Concept c2) {
		this.c1=c1;
		this.RELA=RELA;
		this.c2=c2;
		
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
		return c1.getSTR()+","+RELA+","+c2.getSTR();
	}
}
