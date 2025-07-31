package InputStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOAdvanced1020 {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("한줄 입력 : ");
		try {
		String line = br.readLine();
		System.out.println(line);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
