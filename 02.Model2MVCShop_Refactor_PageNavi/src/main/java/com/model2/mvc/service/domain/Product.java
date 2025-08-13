package com.model2.mvc.service.domain;

import java.sql.Date;


public class Product {
	
	///Field
	private String fileName; 		// IMAGE_FILE
	private String manuDate;    // MANUFACTURE_DAY
	private int price;					// PRICE
	private String prodDetail;   // PROD_DETAIL
	private String prodName;    //PROD_NAME
	private int prodNo;					// PROD_NO
	private Date regDate;				// REG_DATE
	private String proTranCode;
	
	///Constructor
	public Product(){
	}
	
	///Method 
	public String getProTranCode() {
		return proTranCode;
	}
	public void setProTranCode(String proTranCode) {
		this.proTranCode = proTranCode;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getManuDate() {
		return manuDate;
	}
	public void setManuDate(String manuDate) {
		this.manuDate = manuDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getProdDetail() {
		return prodDetail;
	}
	public void setProdDetail(String prodDetail) {
		this.prodDetail = prodDetail;
	}
	public String getProdName() {
		return prodName; 
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getProdNo() {
		return prodNo;
	}
	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@Override
//	public String toString() {
//		return "UserVO : [userId] "+userId+" [userName] "+userName+" [password] "+password+" [role] "+ role
//			+" [ssn] "+ssn+" [phone] "+phone+" [email] "+email+" [regDate] "+regDate;
//	}
	public String toString() {
		return "Product : [fileName]" + fileName	+ "[manuDate]" + manuDate+ "[price]" + price + "[prodDetail]" + prodDetail
				+ "[prodName]" + prodName + "[prodNo]" + prodNo;
	}	
}