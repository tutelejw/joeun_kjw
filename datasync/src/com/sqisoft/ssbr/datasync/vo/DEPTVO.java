package com.sqisoft.ssbr.datasync.vo;

import com.mitzz.frame.vo.ValueObject;

public class DEPTVO
extends ValueObject {
	private static final long serialVersionUID = 1L;
	private String ORG_CD; //부서아이디
	private String ORG_NM; //부서명
	private String SUPER_ORG_CD; //상위부서
	private String SEQ;

	public String getORG_CD() {
		return ORG_CD;
	}

	public void setORG_CD(String oRG_CD) {
		ORG_CD = oRG_CD;
	}

	public String getORG_NM() {
		return ORG_NM;
	}

	public void setORG_NM(String oRG_NM) {
		ORG_NM = oRG_NM;
	}

	public String getSUPER_ORG_CD() {
		return SUPER_ORG_CD;
	}

	public void setSUPER_ORG_CD(String sUPER_ORG_CD) {
		SUPER_ORG_CD = sUPER_ORG_CD;
	}

	public String getSEQ() {
		return SEQ;
	}

	public void setSEQ(String sEQ) {
		SEQ = sEQ;
	}

	public String toString() {
		StringBuffer strbuf = new StringBuffer();
		strbuf.append(" ORG_CD :" + this.ORG_CD);
		strbuf.append(" ORG_NM :" + this.ORG_NM);
		strbuf.append(" SUPER_ORG_CD :" + this.SUPER_ORG_CD);
		strbuf.append(" SEQ :" + this.SEQ);

		return strbuf.toString();
	}
}