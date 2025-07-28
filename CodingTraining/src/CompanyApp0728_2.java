class Employee0728_2{
	String name; //이름
	int salary; // 기본급
	
	//기본 생성자 Constructor 
	public Employee0728_2() {
	}
	public Employee0728_2(String name, int salary) {
		this.name=name;
		this.salary=salary;
	}
	public int getPay() {
		return salary;
	}
	public void printInfo() {
		System.out.println("이름 : " + name + " / 급여 : " + getPay());
	}
}

class Manager0728_2 extends Employee0728_2{
	int bonus;
	
	//default Constructor
	public Manager0728_2() {
	}
	public Manager0728_2(String name, int salary, int bonus) {
		super(name, salary);
		this.bonus=bonus;
	}
	public int getPay() {
		return salary + bonus;
	}
}

public class CompanyApp0728_2 {
	public static void main(String[] args) {
		Employee0728_2 ep = new Employee0728_2("홍길동", 4000);
		ep.printInfo();
		Manager0728_2 mg = new Manager0728_2("김유신", 4000,4000);
		mg.printInfo();
		
	}// out of main
} // out of class CompanyApp0728_2
