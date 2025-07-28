public class Manager0728  extends Employee0728{
	
	int bonus;
	
	//default Constructor
	public Manager0728 () {
	}
	
	//부모생성자 호출
	public Manager0728(String name, int salary, int bonus) {
		super(name,salary);
		this.bonus=bonus;
	}
	
	public int getPay() {
		return super.salary + bonus; 
	}
}
