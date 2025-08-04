package homework3;

public class UserVO {
	private String no;
	private String id;
    private String pwd;
    private String gender;
    private String married;

    // Getter & Setter
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

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMarried() {
        return married;
    }
    public void setMarried(String married) {
        this.married = married;
    }
}
