package kjw.hw.m07.d09;

public class Prob02{
	public static void main(String[] args){
		
		String srcStr = "everyday we have is one more than we deserve";
		String encStr = "";

		for (int i = 0; i < srcStr.length(); i++ ){
		
		char c = srcStr.charAt(i);
		int intTmp = (int)c;
		
		if (intTmp != 32) {
			c = (char)(intTmp+3); 
		}
		//System.out.println("i값 : " + i + " srcStr.charAt값 : " + srcStr.charAt(i)+ " srcTmp값 : " + intTmp +" chTemp 값 : " + c);
		encStr += c;
		}

		System.out.println("암호화할 문자열 : " + srcStr);
		System.out.println("암호화된 문자열 : " + encStr);

	}//end of main
} // end of class


// 프로그램을 구현부 시작
// 참고 문자 'a' 정수값  97  / b 정수값 98 / c 정수값 99 /  z=122 
// 캐스팅 인코딩       -   char c = "a";  		// int i = (char) c; 		// char c1 = (char) i;
//프로그램 구현부 끝