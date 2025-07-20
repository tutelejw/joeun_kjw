abstract class BusCharge{
	///Field
	String section;

	///Constructor
	public BusCharge(){
	}
	public BusCharge(String section){
		this.section=section;
	}

	///Method
	public void information(){
		System.out.println("버스요금안내");
	}
	public abstract void charge();

}//end of class


class Student extends BusCharge{
	public Student(){
		super("학생");
	}
	
	public void charge(){
		System.out.println("300원");
	}
}//end of class

class Adult extends BusCharge{
	public Adult(){
		super("일반인");
	}
	public void charge(){
		System.out.println("500원");
	}	
}//end of class

class Old extends BusCharge{
	public Old(){
		super("어르신");
	}
	public void charge(){
		System.out.println("공짜");
	}	
}//end of class

public class Display04{
	///Main
	public static void main(String[] args){
	Student bc1 = new Student();
	Adult bc2 = new Adult();
	Old bc3 = new Old();

	bc1.information();
	System.out.println(bc1.section);
	bc1.charge();

	bc2.information();
	System.out.println(bc2.section);
	bc2.charge();


	bc3.information();
	System.out.println(bc3.section);
	bc3.charge();

	Fee[] fee = new Fee[3];
	
	}//end of main
}//end of class
