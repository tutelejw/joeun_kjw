package shop.sevices.user.test;

import shop.sevices.user.User;

public class ClientTestApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User client = new User("홍길동", 0, null, null);
		
		System.out.println("이름 : " +client.getName());
		System.out.println("모든정보 : " +client);
	}

}
