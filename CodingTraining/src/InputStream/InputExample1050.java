package InputStream;


import java.io.IOException;

public class InputExample1050 {
	public static void main(String[] args) {
		System.out.print("한글자 씩 입력 : ");
		int ch;
		
		try {
			while ((ch = System.in.read()) !=  -1) {
				if(ch == 'q') {
					break;
				}
				if(ch == '\n' || ch == '\r') {
					continue;
				}
				System.out.println("입력 값 : " + (char)ch);	
			}
			System.out.println("종료함다.");
		}catch(IOException e) {
			System.out.println("에러 발생 " + e.getMessage());
			e.printStackTrace();
		}
		
	}
}

//try {
//	int input = System.in.read();
//	System.out.println("입력 값 : " + (char)input);
//}catch(IOException e) {
//	System.out.println("에러 발생 " + e.getMessage());
//	e.printStackTrace();
//}