package InputStream;

import java.io.IOException;

public class InputExample1007 {
	public static void main(String[] args) {
		byte[] buffer = new byte[20];
		System.out.print("입력 ㅣ(exit입력종료): ");
		
		try {
			while(true) {
				int len = System.in.read(buffer);
				String input = new String(buffer,0, len).trim();
				System.out.println("len 값 : " + len);
				if(input.equals("exit")) {
					break;
				}
				System.out.println(" 출력 : " + input);
			}
		}catch(IOException e){
			System.out.println("에러에러" + e.getMessage());
			e.printStackTrace();
		}
	}
}
