class Employee{
	 protected String name; //이름
	 protected int salary; //기본급
	 
	 public Employee() {
	 }
	 
	 public Employee(String name, int salary) {
		 this.name=name;
		 this.salary=salary;
	 }
	
	public int getPay() {
		return salary;
	}
	public void printInfo() {
		System.out.println("이름" + name + " 급여: " + getPay());
	}
}

class Manager extends Employee{
	private int bonus; //보너스
	
	public Manager() {
	}
	public Manager(String name, int salary, int bonus) {
		super(name, salary);
		this.bonus=bonus;
	}
	public int getPay() {
		salary += bonus;
		return salary;
	}
}
public class CompanyApp {

}
