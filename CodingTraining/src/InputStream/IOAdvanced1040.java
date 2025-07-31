package InputStream;

import java.util.Scanner;

public class IOAdvanced1040 {
	public static void main(String[] args) {
		String name;
		int age;
		Scanner sc = new Scanner(System.in);
		System.out.print("이름 입력 : ");
		name = sc.nextLine();
		
		System.out.print("나이 입력 : ");
		age = sc.nextInt();
		
		System.out.println("이름 : " + name + " / 나이 : " + age);
	}//out of main
}//out of class
