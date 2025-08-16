package model;

import javax.print.event.PrintJobAttributeEvent;

public class User {
	private String id;
	private String password;
	private String name;
	private int age;
	private String role;
	
	// Constructor
	public User(String id, String password, String name, int age, String role) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.age = age;
		this.role=role;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id=id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age=age;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role=role;
	}
	
} // end of class
