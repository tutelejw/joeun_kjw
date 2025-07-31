package jw04;

public class UserVO {
    private String id;
    private String pwd;
    private boolean active;

    public UserVO() {}

    public UserVO(String id, String pwd, boolean active) {
        this.id = id;
        this.pwd = pwd;
        this.active = active;
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
}