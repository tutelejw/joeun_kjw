
public class Employee0728 {
		String name ;
		int salary;
		
		//default constructor
		public Employee0728() {
		}
		
		public Employee0728(String name, int salary) {
			this.name = name;
			this.salary=salary;
		}
		
		public int getPay() {
			return salary;
		}
		
		public void printInfo() {
		System.out.println("이름 :  " + name + "급여 : " + getPay());
		}
}// out of class
