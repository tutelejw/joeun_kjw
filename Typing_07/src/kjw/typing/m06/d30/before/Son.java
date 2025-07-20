public class Son extends Iam{
	///Field
	String school;

	public Son(){
	System.out.println("Son class default Constructor");
	}

	///Method
	//setter method
	public void setSchool(String str){
		school = str;
	}
	//getter method
	public String getSchool(){
		return school;
	}

	///main method
	public static void main(String[] args){
	Son son = new Son();


	son.setName("홍길동아들");
	son.setAge(50);
	son.setJob("개발자준비중");
	son.setSchool("아이티교육센터");
	
	System.out.println("이름 : " + son.getName());
	System.out.println("나이 : " + son.getAge());
	System.out.println("직업 : " + son.getJob());
	System.out.println("직업 : " + son.getSchool());


	}//end of main
}// end of class