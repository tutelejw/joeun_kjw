package kjw.hw.m07.d15;

import java.io.*;
import java.util.*;

public class FileController {
	public void reverseLine(String oldf, String newf) throws Exception{
		//reverseLine 메서드 구현.
		//라인 수 세기
		BufferedReader br = new BufferedReader(new FileReader(oldf));
		int lineCount = 0;
		while (br.readLine() != null) {
			lineCount++;
		}
		//br.close();
		
		//배열에 저장
		String[] lines = new String[lineCount];
		
		//BufferedReader br = new BufferedReader(new FileReader(oldf));
		for (int i=0; i <lineCount; i++) {
			lines[i] = br.readLine();
		}
		br.close();
		BufferedWriter bw = new BufferedWriter(new FileWriter(newf));
		for (int i = lineCount - 1; i >= 0; i--) {
			bw.write(lines[i]);
			bw.newLine(); // 줄바꿈
		}
		bw.close();
		
		
		System.out.println("reverseLine 메서드 구현.");
	} // out of method
	
	public static void main(String[] args) throws Exception{
		FileController c = new FileController();
		String oldf = "./src/files/oldf.txt";
		String newf = "./src/files/newf.txt";
		c.reverseLine (oldf,newf);
	}//out of main
} //out of classs

//reverseLine 메서드 구현
//String 타입의 텍스트 파일명을 입력받아 텍스트의 라인순서를 반대로하여 두번째 인자인 출력할 String 타입의 텍스트 파일명으로 저장
 