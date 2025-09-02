package com.sqisoft.ssbr.datasync.vo;

import com.mitzz.frame.vo.ValueObject;

public class POSTUSERVO
extends ValueObject {
	private static final long serialVersionUID = 1L;
	private String userId;
	private String mailId;
	private String userName;
	private String deptId;
	private String deptName;
	private String dutyName;
	private String updtDt;
	private String useFlag;
	private String Seq;
	private String dutyId;
	private Integer idx;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getUpdtDt() {
		return updtDt;
	}
	public void setUpdtDt(String updtDt) {
		this.updtDt = updtDt;
	}
	public String getUseFlag() {
		return useFlag;
	}
	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}
	public String getSeq() {
		return Seq;
	}
	public void setSeq(String seq) {
		Seq = seq;
	}
	public String getDutyId() {
		return dutyId;
	}
	public void setDutyId(String dutyId) {
		this.dutyId = dutyId;
	}
	public Integer getIdx() {
		return idx;
	}
	public void setIdx(Integer idx) {
		this.idx = idx;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("POSTUSERVO [\nuserId=");
		builder.append(userId);
		builder.append("\nmailId=");
		builder.append(mailId);
		builder.append("\nuserName=");
		builder.append(userName);
		builder.append("\ndeptId=");
		builder.append(deptId);
		builder.append("\ndeptName=");
		builder.append(deptName);
		builder.append("\ndutyName=");
		builder.append(dutyName);
		builder.append("\nupdtDt=");
		builder.append(updtDt);
		builder.append("\nuseFlag=");
		builder.append(useFlag);
		builder.append("\nSeq=");
		builder.append(Seq);
		builder.append("\ndutyId=");
		builder.append(dutyId);
		builder.append("\nidx=");
		builder.append(idx);
		builder.append(" \n]");
		return builder.toString();
	}
}