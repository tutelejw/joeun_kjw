package InputStream;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class InputExample1080 {
	
//줄단위로 읽기 줄단위로 읽을때는 BufferedReader 로 InputStreamReader과 FileInputStream 을감싸서 사용
//BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("file/test"),"UTF-8")).
//	그냥 텍스트 파일이고 인코딩 신경 안 씀	new FileReader()
//	UTF-8, MS949 등 인코딩 신경 써야 함	new InputStreamReader(new FileInputStream(...), "UTF-8")
	public static void main(String[] args) {
		BufferedReader reader = null;
		try {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream("file/test"),"UTF-8"));
				String line;
				while((line = reader.readLine()) != null ) {
					System.out.println(line);
				}
			}catch(IOException e){
				System.out.println("에러에러 : " + e.getMessage());
				e.printStackTrace();
			}finally {
	            try {
	                if (reader != null) reader.close();
	            } catch(IOException e) {
	                e.printStackTrace();
	            }
			}
	}
	
//한개씩 읽기
//	public static void main(String[] args) {
//		FileInputStream fls = null;
//		try {
//			fls = new FileInputStream("file/test");	
//			int ch;
//			while ((ch = fls.read()) != -1) {
//				System.out.println((char)ch);
//			}
//		}catch(IOException e) {
//			System.out.println("에러 " + e.getMessage());
//			e.printStackTrace();
//		}finally {
//			try{
//				if(fls!= null) fls.close();
//			}catch(IOException e) {
//				e.printStackTrace();
//			}
//		}
//		
//	}//out of main
}// out of class
