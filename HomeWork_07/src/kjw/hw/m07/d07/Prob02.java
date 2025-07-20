//2차로 모델링 하게 

class Person {
	String name;
	int age;
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
}

class Student extends Person {
	
	int id;
	
	public Student(String name, int age, int id) {
		super(name, age);
		this.id = id;
	}
	
	public void print() {
		System.out.println("이름 : " + name + "  / 나이 : " + age + "  /   학번 : " + id);
	}
}

class Teacher extends Person {
	
	String subject;
	
	public Teacher(String name, int age, String subject) {
		super(name, age);
		this.subject = subject;
	}
		
	public void print() {
		System.out.println("이름 : " + name + "  / 나이 : " + age + "  /   학번 : " + subject);
	}
}

class Employee extends Person {
	
	String dept;
	
	public Employee(String name, int age, String dept) {
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
