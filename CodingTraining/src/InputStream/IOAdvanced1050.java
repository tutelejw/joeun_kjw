package InputStream;

import java.util.Scanner;

public class IOAdvanced1050 {
	public static void main(String[] args) {
		String word;
		String line;
		
		Scanner sc = new Scanner(System.in)	;
		System.out.print("next 공백까지만 문자열 읽기 : ");
		word = sc.next();
		
		//sc.next(); //버퍼 초기화?? next와 nextLine 로 해보고 초기화 안해보기도 하고
		sc.nextLine(); // sc에 데이터가 있을수도 있고 nextLine으로 사용할거니까 nextLine을 초기화 해줘야 하는군?
		
		System.out.print("nextLine 공백 포함 문자열 읽기");
		line = sc.nextLine();
		
		System.out.println( "next - word : " + word );
		System.out.println("nextLine - line : " + line );
		
		sc.close();
	}
}
