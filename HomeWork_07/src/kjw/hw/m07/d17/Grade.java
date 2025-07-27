package kjw.hw.m07.d17;

import java.io.BufferedReader;
import java.io.FileReader;

public class Grade {
	public void printGrade(String fileName) throws Exception {
		System.out.println(fileName);
		FileReader fr = null;
		BufferedReader br = null;
		
		fr = new FileReader(fileName);
		br = new BufferedReader(fr)	;
		
		String line = null;
		int total = 0;
		int count = 0;
		
		while ((line = br.readLine()) != null) {
			String[] token = line.split(",");
			int score = Integer.parseInt(token[1]);
			total += score;
			System.out.println(token[0] +" 의 점수는 " + score +"입니다.");
			count++;
		}
		System.out.println("총점 : " + total + " 입니다.");
		System.out.println("평균 : " + total/count + " 입니다.");
		if(br != null) br.close();
		if(fr != null) fr.close();	
	}
	
	public static void main(String[] args) throws Exception {
//	Grade grade = new Grade();
//	String fileName = "files/score.txt";
//	grade.printGrade(fileName);
	new Grade().printGrade("./files/score.txt");
	
	}//out of main
}//out of class
