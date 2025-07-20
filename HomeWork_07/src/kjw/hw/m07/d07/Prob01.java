//2차로 모델링 하게 
class Person {
	private String name;
	private int age;
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public Person() {
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
}
	
class Student extends Person{
	
	private int id;
	
	public Student() {
		super();
	}
	
	public Student(String name, int age, int id) {
		super(name, age);
		this.id = id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void print() {
		System.out.println("이름 : " + getName() + "  / 나이 : " + getAge() + "  /   학번 : " + getId());
	}
}
//
class Teacher extends Person {
 private String subject;

 public Teacher(String name, int age, String subject) {
     super(name, age);
     this.subject = subject;
 }

 public Teacher() {
 }

 public String getSubject() {
     return subject;
 }

 public void setSubject(String subject) {
     this.subject = subject;
 }

 public void print() {
     System.out.println("이름 : " + getName() + "  / 나이 : " + getAge() + "  /   과목 : " + getSubject());
 }
}

class Employee extends Person {
 private String dept;

 public Employee(String name, int age, String dept) {
     super(name, age);
     this.dept = dept;
 }
 
 public Employee() {
 }

 public String getDept() {
     return dept;
 }

 public void setDept(String dept) {
     this.dept = dept;
 }

 public void print() {
     System.out.println("이름 : " + getName() + "  / 나이 : " + getAge() + "  /   부서 : " + getDept());
 }
}

public class Prob01 {

	public static void main(String[] args) {
		Student s = new Student("홍길동",20,200201);
		Teacher t = new Teacher("이순신",30,"JAVA");
		Employee e = new Employee("유관순",40,"교무관");
		
		s.print();
		t.print();
		e.print();
	}
}