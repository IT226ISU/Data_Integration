package it226;

public class Concept {
	private String CUI;
	private String STR;
	
	public Concept(String CUI, String STR) {
		this.CUI=CUI;
		this.STR=STR;
	}
	public String getCUI() {
		return CUI;
	}
	public String getSTR() {
		return STR;
	}
	public void setSTR(String s) {
		STR=s;
	}
	@Override
	public String toString() {
		return CUI+": "+STR;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CUI == null) ? 0 : CUI.hashCode());
		result = prime * result + ((STR == null) ? 0 : STR.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Concept other = (Concept) obj;
		if (CUI == null) {
			if (other.CUI != null) {
				return false;
			}
		} else if (!CUI.equals(other.CUI)) {
			return false;
		}
		if (STR == null) {
			if (other.STR != null) {
				return false;
			}
		} else if (!STR.equals(other.STR)) {
			return false;
		}
		return true;
	}
}
