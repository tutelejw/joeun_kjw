package jw.services.user.vo;

/*
 *  FileName : UserVO.java
 *  1. Client의 Data를 Object Modeling한 Normal class
 *  2. Data만 갖는 객체로 ==> Value Object라 한다.  (Value Object Pattern)
 */
public class UserVO {
	///Field
	private String id;
	private String pwd;
	private boolean active;
	private int no;
	///Constructor
	public UserVO() {
	}
	///Method
	//getter/setter Method
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	//==> toString Method OverRding
	public String toString() {
		return "UserVO [active=" + active + ", id=" + id + ", no=" + no
				+ ", pwd=" + pwd + "]";
	}
}//end of class