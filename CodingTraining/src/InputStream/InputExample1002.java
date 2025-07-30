package InputStream;

import java.io.IOException;

public class InputExample1002 {
	public static void main(String[] args)  {
		int i=0;
		while (i!=3) {
		System.out.println("여러글자 입력하시오");
		try {
			int input = System.in.read();
			System.out.println("첫 글자만 출력  : " + (char)input);	
		}catch(IOException e) {
			System.out.println("에러발생했다." + e.getMessage());
			e.printStackTrace();
		}
		i++;
		}
			
		
	}//out of main
}// out of class
