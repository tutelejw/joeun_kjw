package kjw.m07.d14;

public class Prob {
	// 구분자 # 파싱해서 배열에 저장 리턴 메서드
	private static String[] stringSplit(String str1, String str2) {
		
		// 구분자가 몇개인지 카운트 해서 배열 크기 결정
		int count=0;
		for (int i=0; i<str1.length(); i++) {
			if(str1.charAt(i) == str2.charAt(0)) {
				count ++;
			}
		}
		// 배열 크기 구분자 count 개수 + 1
		String[] result = new String[count+1];
		int startIndex = 0;
		int resultIndex = 0;
		for (int i=0; i < str1.length() ; i++) {
			if(str1.charAt(i) == str2.charAt(0)) {
				result[resultIndex]= str1.substring(startIndex, i);
				startIndex = i+1;
				resultIndex++;
			}
		}
		result[resultIndex]= str1.substring(startIndex); 
		return result;
	}//end of method
	
	public static void main(String[] args) {
		String str = "PROD-001#X-note#Samsung#6000000";
		String[] strs = stringSplit(str,"#"); //stringSplit 메서드 제작 필요
		System.out.println("===문자열 처리결과 ===");
		for(int i = 0; i< strs.length; i++) {
			System.out.println(strs[i]);
		}		
	}//end of main
}// end of class
