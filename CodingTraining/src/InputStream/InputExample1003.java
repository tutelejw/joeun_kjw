package InputStream;

import java.io.IOException;

public class InputExample1003 {
	public static void main(String[] args) {
		System.out.print("글자 입력 : ");
		
		while (true) {
			try {
				int input = System.in.read();	
				if (input == '\n' || input=='\r' ) {
					break;
				}
				System.out.println("입력한 문자 : " + (char)input);				
			}catch(IOException e) {
				System.out.println("입력 오류 발생 " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
