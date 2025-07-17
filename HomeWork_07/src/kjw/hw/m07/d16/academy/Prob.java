package kjw.hw.m07.d16.academy;


// java.lang.Integer  , String  API 연습
public class Prob {
	public String toEncoding(String str) {
		String value ="";
		//System.out.println("str :  " + str);
		for (int i = 0; i < str.length(); i++) {
			String oneStr=str.substring(i,i+1);
			//char c=str.charAt(i);   ->> 강사님 방식 아래에 String로 형변환 방식 참고
			//String temp=c+"";  char를 String로 형변환 쉽게 참고. 추후 예제로 
			byte[] temp = oneStr.getBytes();
			//System.out.println("oneStr : " + oneStr + " oneStr.length() : " + oneStr.length() + " temp : " + temp.length);
			if(temp.length == 3) {
				for (int j=0; j < temp.length; j++) {
				value += "%"+Integer.toHexString(temp[j]).toUpperCase().substring(6,8);	
				}
			}else {
				value += oneStr;
			}
		}
		return value;
	}
	public static void main(String[] args) {
		/******************** 힌트 16진수 변환 *****************************/
		/*
		String value = "홍";
		byte[] temp = value.getBytes();   //getBytes() 기본적으로 UTF-8 인코딩을 사용 바이트 배열 크기가 다르다
		System.out.println(Integer.toHexString(temp.length));  //"홍"은 한글 문자로, 유니코드 U+D64D에 해당
		System.out.println(Integer.toHexString(temp[0]));      //UTF-8에서 유니코드 코드 포인트에 따라 인코딩 크기가 다름, "홍" 한글 문자는 3바이트로 인코딩
		System.out.println(Integer.toHexString(temp[1]));
		System.out.println(Integer.toHexString(temp[2]));
		/*	
		value = "A";
		temp = value.getBytes();  // "A"는 ASCII 문자로, UTF-8에서는 1바이트로 표현
		System.out.println(Integer.toHexString(temp.length));
		System.out.println(Integer.toHexString(temp[0]));
		*/
		/******************** 힌트 16진수 변환 *****************************/ 
		Prob p1 = new Prob();
		System.out.println("입력 : korea임시");
		System.out.println("출력 : " + p1.toEncoding("Korea임시"));
		System.out.println("입력 : 홍길동");
		System.out.println("출력 : " + p1.toEncoding("홍길동"));
		System.out.println("입력 : 홍 길 동");
		System.out.println("출력 : " + p1.toEncoding("홍 길 동"));
		System.out.println("입력 : Hong 길 동");
		System.out.println("출력 : " + p1.toEncoding("Hong 길 동"));
		System.out.println("입력 : 0319");
		System.out.println("출력 : " + p1.toEncoding("0319"));
	}//out of main
}
