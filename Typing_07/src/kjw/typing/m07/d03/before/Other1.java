package kjw.typing.m07.d03.before;  
//package jb04.part08; //이러면 protected 까지는 되고.  package


/*
	FileName : Other,java
	
	1. Encapsulation(information hiding)을 지원하는 Access Modifier 이해
	2. public / protected /       / private 의 사용 및 이해 

	ㅇ Other1은 Father 와 다른 package 에 존재, 관계가 없다
	ㅇ 주석을 풀고 
	      ==> Compile 시 error 를 확인하고, error 의 의미이해
	      ==> 각 Access Modifier 의 접근범위를 확인할 것  
*/

public class Other1{
	
	///Field
	//==> jb04.part07.Father를 이해할것. 
	kjw.typing.m07.d03.before.Father unknown = new kjw.typing.m07.d03.before.Father();	//==> Father 인스턴스 생성

	///Constructor
	public Other1(){
		System.out.println(this.unknown.name);		//==> public String name = "홍길동";
		//System.out.println(unknown.bank);		    	//==> protected String bank = "한양은행";
		//System.out.println(unknown.branch);	   		//==> String branch="역삼동지점";
		//System.out.println(unknown.password); 		//==> private int password = 1234;
		
		//==> Field(상태정보)는 Method(행위)를 이용 접근
		System.out.println(this.unknown.getBranch());
		System.out.println(unknown.getPassword(0));
	}
}//end of class

/*
	1. private 을 사용하여 다른 class 로 부터 상태 정보 은익
	2. method 를 통해(행위를 통해) 정보은익 여부을 제어
*/
