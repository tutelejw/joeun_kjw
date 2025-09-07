package kjw.typing.m06.d30.before;
public class Iam extends Human{
	///Field
	String job;

	public Iam(){
	System.out.println("lam class default Constructor");
	}

	///Method
	//setter method
	public void setJob(String str){
		job = str;
	}
	//getter method
	public String getJob(){
		return job;
	}

	///main method
	public static void main(String[] args){
	Iam iam = new Iam();


	iam.setName("홍길동");
	iam.setAge(100);
	iam.setJob("개발자");
	
	System.out.println("이름 : " + iam.getName());
	System.out.println("나이 : " + iam.getAge());
	System.out.println("직업 : " + iam.getJob());


	}//end of main
}// end of class