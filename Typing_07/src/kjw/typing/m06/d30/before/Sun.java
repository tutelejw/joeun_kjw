public class Son extends Iam{
	///Field
	String school;

	public Son(){
	System.out.println("Son class default Constructor");
	}

	///Method
	//setter method
	public void setSchool(String str){
		School = str;
	}
	//getter method
	public String getSchool(){
		return school;
	}

	///main method
	public static void main(String[] args){
	Son son = new Son();


	iam.setName("홍길동아들");
	iam.setAge(50);
	iam.setJob("개발자준비중");
	iam.setSchool("아이티교육센터");
	
	System.out.println("이름 : " + iam.getName());
	System.out.println("나이 : " + iam.getAge());
	System.out.println("직업 : " + iam.getJob());
	System.out.println("직업 : " + iam.getSchool());


	}//end of main
}// end of class