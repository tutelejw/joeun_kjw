package com.sqisoft.ssbr.datasync.vo;

import com.mitzz.frame.vo.ValueObject;

public class APRVUSERGROUPVO
extends ValueObject {
	private static final long serialVersionUID = 1L;
	private String seqNo;
	private String userId;
	private String userName;
	private String dept_id;
	private String phone;
	private String loc;
	private String email;
	private String aprvId;
	private String aprvName;
	private String insDt;
	private String useFlag;
	private String comnlRegFlag;
	
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
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
	public String getAprvId() {
		return aprvId;
	}
	public void setAprvId(String aprvId) {
		this.aprvId = aprvId;
	}
	public String getAprvName() {
		return aprvName;
	}
	public void setAprvName(String aprvName) {
		this.aprvName = aprvName;
	}
	public String getInsDt() {
		return insDt;
	}
	public void setInsDt(String insDt) {
		this.insDt = insDt;
	}
	public String getUseFlag() {
		return useFlag;
	}
	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}
	public String getComnlRegFlag() {
		return comnlRegFlag;
	}
	public void setComnlRegFlag(String comnlRegFlag) {
		this.comnlRegFlag = comnlRegFlag;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getDept_id() {
		return dept_id;
	}
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}