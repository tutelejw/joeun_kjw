package model;

/**
 * User - 사용자 정보를 담는 모델(또는 DTO)
 */
public class User {
	//Field
	private String id;
	private String password;
	private String name;
	private String role;
	
	//Default Constructor
	public User() {
	}
	
	public User(String id, String password, String name, String role) {
		this.id=id;
		this.password=password;
		this.name=name;
		this.role=role;
	}
	
	//getter/setter
	public String getId() {		return id;	}
	public void setId(String id) {this.id = id;}
	
	public String getPassword() { return password;}
	public void setPassword(String password) { this.password = password;}
	
	public String getName () { return name;}
	public void setName(String name) {	this.name = name;}
	
	public String getRole() { return role;}
	public void setRole(String role) { this.role = role;}
	
}// end of class
