public class User01{
	String name = "홍길동";
	int javaLevel = 0;

	public User01(){
	System.out.println("Constructor Method");
	}

	public String getName(){
	return name;
	}

	public int getJavaLevel(){
	return javaLevel;
	}

	public static void main(String[] args){
		System.out.println("1111=========================");
		User01 user111 = new User01(); //user01 class??
		System.out.println("1111=========================");

		System.out.println("=========================");
		User01 user222 = new User01();   //user method??
		System.out.println("=========================");

		//결과 테스트
		System.out.println("0 User01 user;=========================");
		User01 user;
		System.out.println("1user = new User01();=========================");
		user = new User01();
		System.out.println("2new User01();=========================");
		new User01();
		System.out.println("3결과출력=========================");

		//정상결과??
		System.out.println("name : " + user.getName());
		System.out.println("javaLevel : " + user.getJavaLevel());
	}//end of main


}//end of class