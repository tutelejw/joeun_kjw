package kjw.hw.m07.d15;

import java.io.*;
import java.util.*;

public class FileController {
	public void reverseLine(String oldf, String newf) throws Exception{
		//reverseLine 메서드 구현.
		//라인 수 세기
		BufferedReader br = new BufferedReader(new FileReader(oldf));
		List<String>m = new ArrayList<String>();
		
		String line = null;
		while ((line = br.readLine())!=null) {
			m.add(line);
		}
		br.close();
		
		PrintWriter pw = new PrintWriter(new FileOutputStream(newf));
		for (int i=m.size() - 1; i>=0; i--) {
			pw.println(m.get(i));
		}
		pw.flush();
		pw.close();	
		
		System.out.println("reverseLine 메서드 구현.");
	} // out of method
	
	public static void main(String[] args) throws Exception{
		FileController c = new FileController();
		String oldf = "files/oldf.txt";
		String newf = "files/newf.txt";
		c.reverseLine (oldf,newf);
	}//out of main
} //out of classs

//reverseLine 메서드 구현
//String 타입의 텍스트 파일명을 입력받아 텍스트의 라인순서를 반대로하여 두번째 인자인 출력할 String 타입의 텍스트 파일명으로 저장
 