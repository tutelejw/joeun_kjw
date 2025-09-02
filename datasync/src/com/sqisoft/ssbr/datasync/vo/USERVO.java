package com.sqisoft.ssbr.datasync.vo;

import com.mitzz.frame.vo.ValueObject;

public class USERVO extends ValueObject {
	private static final long serialVersionUID = 1L;
	private String userId;
	private String userName;
	private String userPswd;
	private String deptId;
	private String userDeptRank;
	private String deptName;
	private String dutyName;
	private String EMail;
	private String Tel;
	private String Phone;
	private String Seq;
	private String userNo;
	private String userStat;
	private Integer idx;
	
	private String TeamManager;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserPswd() {
		return userPswd;
	}

	public void setUserPswd(String userPswd) {
		this.userPswd = userPswd;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDutyName() {
		return dutyName;
	}

	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}

	public String getEMail() {
		return EMail;
	}

	public void setEMail(String eMail) {
		EMail = eMail;
	}

	public String getTel() {
		return Tel;
	}

	public void setTel(String tel) {
		Tel = tel;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getSeq() {
		return Seq;
	}

	public void setSeq(String seq) {
		Seq = seq;
	}

	public String getUserStat() {
		return userStat;
	}

	public void setUserStat(String userStat) {
		this.userStat = userStat;
	}

	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public String toString() {
		StringBuffer strbuf = new StringBuffer();
		strbuf.append(" userId :" + this.userId);
		strbuf.append(" name :" + this.userName);
		strbuf.append(" pswd :" + this.userPswd);
		strbuf.append(" deptId    :" + this.deptId);
		strbuf.append(" deptName   :" + this.deptName);
		strbuf.append(" dutyName     :" + this.dutyName);
		strbuf.append(" EMail     :" + this.EMail);
		strbuf.append(" Tel     :" + this.Tel);
		strbuf.append(" Phone     :" + this.Phone);
		strbuf.append(" Seq     :" + this.Seq);
		strbuf.append(" userStat     :" + this.userStat);

		return strbuf.toString();
	}

	public String getUserDeptRank() {
		return userDeptRank;
	}

	public void setUserDeptRank(String userDeptRank) {
		this.userDeptRank = userDeptRank;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getTeamManager() {
		return TeamManager;
	}

	public void setTeamManager(String teamManager) {
		TeamManager = teamManager;
	}
}