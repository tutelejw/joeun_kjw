package InputStream;

import java.io.IOException;

public class InputExample1012 {
	//public static void main(String[] args)  throws Exception{
	public static void main(String[] args)  {
		System.out.println("한글자씩 입력");
		//int input = System.in.read();
		try {
		int input = System.in.read();
		System.out.println("입력 문자 : " + (char)input );
		}catch (IOException e) {
			System.out.println("입력도중 에러 발생" + e.getMessage());
			e.printStackTrace();
		}
		
	}
}
