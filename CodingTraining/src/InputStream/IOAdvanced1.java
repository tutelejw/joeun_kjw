package InputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IOAdvanced1 {
	public static void main(String[] args) {
		InputStreamReader reader = new InputStreamReader(System.in);
		char[] buffer = new char[100];
		System.out.println("문장을 입력 : ");
		int len=0;
		try {
			len = reader.read(buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String input = new String(buffer, 0, len).trim();
		System.out.println("입력 내용 " + input);
	}
}
