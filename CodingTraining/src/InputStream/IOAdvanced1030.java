package InputStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOAdvanced1030 {
	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("여러줄 입력 : ");
		try {
			String line;
			while( !(line = bf.readLine()).equals("exit")) {
				System.out.println(line);	
			}
			System.out.println("입력종료");
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
