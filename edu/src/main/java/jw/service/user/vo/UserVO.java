package jw.service.user.vo;

public class UserVO {
    private String no;
	private String id;
    private String pwd;
    private Boolean active; 

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
    
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
    
    public String toString() {
        return "UserVO [id=" + id + ", pwd=" + pwd + ", active=" + active + "]";
    }
}