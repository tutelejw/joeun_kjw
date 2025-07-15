package kjw.hw.m07.d15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

//import java.io.*;
//import java.util.*;

public class FileController {
	public void reverseLine(String oldf, String newf) throws Exception{
		//reverseLine 메서드 구현.
		//라인 수 세기
		//FileReader fileReader = new FileReader(oldf);
		//BufferedReader br = new BufferedReader(fileReader); 
		BufferedReader br = new BufferedReader(new FileReader(oldf)); //oldf 파일 br로 선언 초기화
		List<String>m = new ArrayList<String>();
		
		String line = null;
		while ((line = br.readLine())!=null) { //라인을 읽어서 null 이 아니면 
			m.add(line);  //line을  ArrayList의 add 메서드로  m 배열에 추가..
		}  //마지막줄에 null 만나서 종료
		br.close();  // 파일 읽기가 끝나서 bufferedreader 종료
		
		PrintWriter pw = new PrintWriter(new FileWriter(newf)); 
		for (int i=m.size() - 1; i>=0; i--) {   // size = List interface의 메서드 
			pw.println(m.get(i));  //newf 파일의 라인을 역순으로 가져오기 get = List interface의 메서드
		}
		pw.flush();  //println()은 데이터를 버퍼에 쌓아두고, flush()는 그 데이터를 실제로 파일에 기록
		pw.close();	 //pw 파일 쓰기 완료 후 종료
		
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
 