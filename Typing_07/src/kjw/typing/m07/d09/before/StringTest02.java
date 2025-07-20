package jp01.part03;

/*
*	FileName : StringTest02.java
*
*    1.  String.class  :   immutable (정적인 문자열 Object Modeling)
*    2.  StringBuffer.class  : mutable (동적인 문자열 Object Modeling)
*         StringBuilder.class  : mutable (동적인 문자열 Object Modeling)
*         
*         ==> StringBuilder / StringBuffer 는 동적인 문자열을 다루며, API 도 동일
*         ==> 차이점은 StringBuiler 는 동기화(synchronize :: Thread 학습 후 다시 언급)
*                 를 지원하지 않아 StringBuffer 보다 속도면에서 나은 성능을 보장.
*/
public class  StringTest02{

	///Main method
	public static void main(String[] args)	{

		//String.class 이용 문자열 생성
		String s1 = "홍";
		String s2 = "길";
		String s3 = "동";
		s1 = s1+s2;
		s1 = s1+s3;
		
		//동적인StringBuffer / StringBuiler 사용 문자열 생성
		StringBuffer sb = new StringBuffer("홍");
		//StringBuilder sb = new StringBuilder("홍");
		sb.append(s2);
		sb.append(s3);

		System.out.println(s1);
		//System.out.println(s1.toString());
		System.out.println(sb);
		//System.out.println(sb.toString());

		//출력결과는 동일하게 홍길동
		//정적인 String, 동적인 StringBuffer는 내부적으로
		//처리하는 과정은 garbage 발생 등 차이점을 발생이해

	}//end of main

}//end of class