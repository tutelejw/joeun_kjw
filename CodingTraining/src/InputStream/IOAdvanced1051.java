package InputStream;

import java.util.Scanner;

//scanner로 받아서 next와 nextLine으로 각각 받고 중간에 scanner 객체 초기화
public class IOAdvanced1051 {
	public static void main(String[] args) {
		String word;
		String line;
		
		Scanner sc = new Scanner( System.in);
		System.out.print("next 입력 : ");
		word = sc.next();
		sc.nextLine(); // sc 초기화 다음에 nextLine 사용 해야함.
		
		System.out.print("nextLine 입력 : ");
		line = sc.nextLine();
		
		System.out.println("next : " + word); 
		System.out.println("nextLine : " + line);
		
		sc.close();
		
	}
}
