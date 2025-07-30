package InputStream;

import java.io.FileInputStream;
import java.io.IOException;

public class InputExample1090 {
//	public static void main(String[] args) {
//		try(FileInputStream fis = new FileInputStream("file/test")){
//			int ch;
//			while ((ch = fis.read()) != -1) {
//				System.out.println((char)ch);
//			}
//		}catch (IOException e) {
//			e.printStackTrace();
//		}
//	}//out of main

	public static void main(String[] args) {
	FileInputStream fis = null;
	try {
		fis = new FileInputStream("file/test");
		int ch;
		while ((ch = fis.read()) != -1) {
			System.out.println((char)ch);
		}
	} catch (Exception e) {
		System.out.println("에러발생 : " + e.getMessage());
		e.printStackTrace();
		// TODO: handle exception
	}finally {
		try {
			fis.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}// end of main
}//out of class
