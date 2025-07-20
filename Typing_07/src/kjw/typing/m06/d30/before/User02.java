public class User02{
	String name;
	int javaLevel;

	public User02(){
		System.out.println("Constructor Method");
		name = "홍길동";
		javaLevel = 0;
	}

	public void callTest(){   // Method
		System.out.println("callTest-------");
	}

	public String getName(){
		return name;
	}

	public int getJavaLevel(){
		return javaLevel;
	}

	public static void main(String[] args){
		System.out.println("==================");
		User02 user = new User02();//어쨋든 호출하면 한번 수행을 하면서 읽어 들인다??
		System.out.println("==================");
//		user.callTest();
		System.out.println("name : "+user.getName());
		System.out.println("javaLevel : "+user.getJavaLevel());
	}//end of main

}// end of class