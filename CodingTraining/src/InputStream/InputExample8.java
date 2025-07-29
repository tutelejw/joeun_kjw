package InputStream;

import java.io.FileInputStream;
import java.io.IOException;

public class InputExample8 {
	FileInputStream fls;

	public static void main(String[] args) {
		FileInputStream fls = null;
		try {
			fls = new FileInputStream("file/test.txt");	
			int ch;
			while ((ch = fls.read()) != -1) {
				System.out.println((char)ch);
			}
		}catch(IOException e) {
			System.out.println("에러 " + e.getMessage());
			e.printStackTrace();
		}finally {
			try{
				if(fls!= null) fls.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
