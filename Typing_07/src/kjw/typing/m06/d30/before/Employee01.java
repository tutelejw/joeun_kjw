public class Employee01{    // Bean class
	String part;
	String name;
	int age;
	int baseSalary;

	public Employee01(){
		System.out.println("Emp01의 default Constructor");
	}
	
	public Employee01(String name){
		this.name=name;
		System.out.println("Emp01의 name을 받는 Constructor");
	}

	public Employee01(String name, String part){
		this(name);
		this.part=part;
		System.out.println("Emp01의 name, part를 받는 Constructor");
	}

	public Employee01(String name, String part, int age){
		this(name, part);
		this.age=age;
		System.out.println("Emp01의 name, part,age 를 받는 Constructor");
	}

	public int salary(){
		System.out.println("Emp01의 salay() method");
		baseSalary = 100;
		return baseSalary;
	}

}