package kjw.hw.m07.d14;

public class StringUtil {
	private static String replaceString(String[] strArr) {
		if (strArr == null || strArr.length == 0) {
			System.out.println("배열이 비어있음.");
			return "";
		}
		
		String maxAString = "";
		int maxACount = -1;
		
		for(int i=0; i< strArr.length; i++) { 
			String str = strArr[i];
			//System.out.println("strArr[i] : " + strArr[i]);
			
			int currentACount=0;
			for(int j=0; j<str.length();j++) {
				if(str.charAt(j) == 'a') {
					currentACount++;
				}
			}//out of inner for
			if(currentACount > maxACount) {
				maxACount = currentACount;
				maxAString = str;
			//	System.out.println("maxAString111111 : " + maxAString);
			}
		}// out of outer for
		
		return maxAString.replace('a', 'A');
		
	} // end of method

	public static void main(String[] args) {
		// 기본org
//		String[] arr = {"java program", "array", "java program area", "append"};
//		String result = StringUtil.replaceString(arr);
//		System.out.println("변경된 문자열: " + result);

		
//		String result = StringUtil.replaceString(new String[] {"java program", "array", "java program area", "append"});
//		System.out.println("변경된 문자열" + result);
		
//		String[] arr = {"java program", "array", "java program area", "append"};
//		System.out.println("변경된 문자열: " + StringUtil.replaceString(arr));

		
		System.out.println("변경된 문자열 : " + StringUtil.replaceString(new String[] {"java program", "array", "java program area", "append"}));
		//System.out.println("변경된 문자열(입력 없음): " + StringUtil2.replaceString(new String[] { }));

	}//end of main
}// end of class
