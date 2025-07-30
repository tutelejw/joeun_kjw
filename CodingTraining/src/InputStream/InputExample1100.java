package InputStream;

import java.io.FileOutputStream;

public class InputExample1100 {
	private static String input=null;

	public static void main(String[] args) {
		byte[] buffer = new byte[100];
		System.out.println("파일저장할 문장 입력 : " );
		try {
			int len = System.in.read(buffer);
			input = new String(buffer, 0 , len).trim();	
		} catch (Exception e) {
			e.printStackTrace();			
		}
		
		
		try (FileOutputStream fos = new FileOutputStream("file/output.txt")){
			fos.write(input.getBytes());
			System.out.println("파일저장완료");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
