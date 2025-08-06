package kjw.hw.m07.d07;

import kjw.hw.m07.d07.Prob02;
//2차로 모델링 하게 

class Person_Prob02 {
	String name;
	int age;
	
	public Person_Prob02(String name, int age) {
		this.name = name;
		this.age = age;
	}
}

class Student_Prob02 extends Person_Prob02 {
	
	int id;
	
	public Student_Prob02 (String name, int age, int id) {
		super(name, age);
		this.id = id;
	}
	
	public void print() {
		System.out.println("이름 : " + this.name + "  / 나이 : " + age + "  /   학번 : " + id);
	}
}

class Teacher_Prob02 extends Person_Prob02 {
	
	String subject;
	
	public Teacher_Prob02(String name, int age, String subject) {
		super(name, age);
		this.subject = subject;
	}
		
	public void print() {
		System.out.println("이름 : " + name + "  / 나이 : " + age + "  /   학번 : " + subject);
	}
}

class Employee_Prob02 extends Person_Prob02 {
	
	String dept;
	
	public Employee_Prob02(String name, int age, String dept) {
		super(name, age);
		this.dept = dept;
	}
	
	public void print() {
		System.out.println("이름 : " + name + "  / 나이 : " + age + "  /   학번 : " + dept);
	}
}

public class Prob02 {
	public static void main(String[] args) {
		Student s = new Student("홍길동",20,200201);
		Teacher t = new Teacher("이순신",30,"JAVA");
		Employee e = new Employee("유관순",40,"교무관");
		
		s.print();
		t.print();
		e.print();
	}
}
