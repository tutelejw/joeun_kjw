package kjw.typing.m07.d03.before;  

/*
	FileName : Son,java
	
	1. Encapsulation(information hiding)을 지원하는 Access Modifier 이해
	2. public / protected /       / private 의 사용 및 이해 

	ㅇ Son 은 Father 와 같은 package 에 존재하며, 상속관계이다.
	ㅇ 주석을 풀고 
	      ==> Compile 시 error 를 확인하고, error 의 의미 이해
	      ==> 각 Access Modifier 의 접근범위를 확인  
*/
public class Son extends Father{
	
	///Field
	
	///Constructor
	public Son(){
		System.out.println(this.name);    	//==> public String name = "홍길동";
		System.out.println(bank);		        //==> protected String bank = "한양은행";
		System.out.println(branch);	        //==> String branch="역삼동지점";
		//System.out.println(password);		 //==> private int password = 1234;
		
		//==> Field(상태정보)는 Method(행위)를 이용 접근
		System.out.println("은행 비밀번호: "+ this.getPassword(0));
	}
}//end of class

/*
	1. private 을 사용하여 다른 class 로 부터 상태 정보은익
	2. method 를 통해(행위를 통해) 정보은익 여부을 제어
*/
