package InputStream;

import java.io.IOException;

public class InputExample1004 {
	public static void main(String[] args) {
		//Filed
		byte[] buffer = new byte[10];
		System.out.print("최대 10글자 입력 : ");
		
		try {
			int byteRead = System.in.read(buffer);
			String result = new String(buffer, 0, byteRead).trim();
			System.out.println("입력한 내용 : " + result);
		}catch (IOException e) {
			System.out.println("에러 발생" + e.getMessage());
			e.getStackTrace();
			
		}
		
	}//out of main
}//out of class
