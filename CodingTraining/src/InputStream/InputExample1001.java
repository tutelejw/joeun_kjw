package InputStream;


public class InputExample1001 {
	public static void main(String[] args) throws Exception { // main 에서 throws Exception 처리 해야 하네..
		System.out.println("한글자씩 입럭 : ");
		int input = System.in.read();
		System.out.println("입력 문자 " + (char)input );
	}//out of main
}//out of class
