package kjw.hw.m07.d16;

public class Prob {
	
	public String toEncoding(String str) {
		//String value = str;
		//, 한글자씩 toHexString 3 이상이면 한글 변환
		String strTmp="";
		
		for (int i = 0; i < str.length(); i++) {  //글자갯수로 for문 
			String oneString = str.substring(i,i+1);
			System.out.println("oneString  값 " + oneString);
			byte[] temp = oneString.getBytes();   //getBytes() 기본적으로 UTF-8 인코딩을 사용 바이트 배열 크기가 다르다
			System.out.println("temp.length : " + temp.length);  //"홍"은 한글 문자로, temp byte 배열크기 3 유니코드 U+D64D에 해당
			
			if (temp.length == 3) {
				oneString=Integer.toHexString(temp[2]);
			}

			strTmp += oneString;

		}
		
		return strTmp;
	}
	
	//toEncoding(String str) 메서드 완성
	//인자로 받은 문자열을 인코딩하여 리턴시켜주는 메서드
	//영문자 대소문자 이거나 숫자인경우 변환없이 그대로 리턴
	//한글인 경우는 16진수 값으로 변환한 후 변환된 값 앞에 % 기소를 추가
	
	public static void main(String[] args) {
		/******************** 힌트 16진수 변환 *****************************/
		///*
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
//		System.out.println("입력 : 홍길동");
//		System.out.println("출력 : " + p1.toEncoding("홍길동"));
//		System.out.println("입력 : 홍 길 동");
//		System.out.println("출력 : " + p1.toEncoding("홍 길 동"));
//		System.out.println("입력 : Hong길동");
//		System.out.println("출력 : " + p1.toEncoding("Hong길동"));
//		System.out.println("입력 : 0319");
//		System.out.println("출력 : " + p1.toEncoding("0319"));
	}//out of main
}//out of class
