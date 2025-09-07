package kjw.typing.m06.d30.before;
public class Manager01 extends Employee01{

	int salary;

	public Manager01(){
		System.out.println("Manager01의 default Constructor");
	}
	
	public Manager01(String name){
		super(name);
		System.out.println("Manager01의 name을 받는 Constructor");
	}

	public Manager01(String name, String part){
		this(name);
		this.part=part;
		System.out.println("Manager01의 name, part를 받는 Constructor");
	}

	public Manager01(String name, String part, int age){
		super(name, part);
		this.age=age;
		System.out.println("Manager01의 name, part,age 를 받는 Constructor");
	}

	public Manager01(String name, String part, int age, int salary){
		this(name, part,age);
		this.salary=salary;
		System.out.println("Manager01의 name, part,age,salary 를 받는 Constructor");
	}

	public int Salary(){
		System.out.println("Employee의 salay method");
		baseSalary = 200;
		return baseSalary;
	}

	public void callSalary(){
		System.out.println("Employee의 기본급은 : " + super.salary());
		System.out.println("Manager01의 기본급은 : " + this.salary());
		System.out.println("Manager01의 기본급은 : " + salary());
	}
	public void printSalary() {
		System.out.println("Manager01의 salary: " + this.salary);
	}

	public static void main(String[] args){
	Manager01 m1 = new Manager01("홍길동","aaa",12,500);
//	Manager01 m1 = new Manager01();
	System.out.print("\n");
	
	System.out.println("overriding 된 salary 호출 : " + m1.salary());
	m1.callSalary();
	m1.printSalary();

	/*Manager01 m2 = new Manager01("홍길동");
	Manager01 m3 = new Manager01("홍길동");
	Manager01 m4 = new Manager01("홍길동");
	System.out.print("\n");
	
	System.out.println("overriding 된 salary 호출 : " + m4.salary());
	m4.callSalary();
	*/

	}

}

