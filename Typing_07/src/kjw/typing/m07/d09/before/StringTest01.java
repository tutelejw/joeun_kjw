//package jp01.part03;

/*
	FileName : StringTest01.java
	
	 1. 문자열을 Object Modeling 한 String 특징
	 2. 실행결과를 에측하고,  예측한 결과와 맞는지 확인
	 3. 인스턴스 비교시 == 과 equals() Method 다시 이해 
*/

public class  StringTest01{
    
	///main method
	public static void main(String[] args)	{
		
		String s1 = "홍길동";
		String s2 = "홍길동";
		String s3 = new String("홍길동");
		String s4 = new String("홍길동");
		
		// == 비교
		System.out.println("s1==s2 : "+(s1==s2)); // false
		System.out.println("s1==s3 : "+(s1==s3)); // false
		System.out.println("s1==s4 : "+(s1==s4)); // false
		System.out.println("s1==s2 : "+(s2==s3)); // false
		System.out.println("s1==s2 : "+(s2==s4)); // false
		System.out.println("s3==s4 : "+(s3==s4)); // false

		// equals() 비교
		System.out.println("s1.equals(s2) : "+s1.equals(s2)); // false
		System.out.println("s1.equals(s3) : "+s1.equals(s3));// false
		System.out.println("s1.equals(s4) : "+s1.equals(s4));// false
		System.out.println("s1.equals(s4) : "+s2.equals(s3));// false
		System.out.println("s1.equals(s4) : "+s2.equals(s4));// false
		System.out.println("s3.equals(s4) : "+s3.equals(s4));// false
		
	}//end of main

}//end of class