package m07.day14;

public class StringUtil {
	private static String replaceString(String[] strArr) {
		if (strArr == null || strArr.length == 0) {
			return "입력 없음";
		}

		int maxCount = 0;
		int targetIndex = -1;

		for (int i = 0; i < strArr.length; i++) {
			int count = countChar(strArr[i], 'a');
			if (count > maxCount) {
				maxCount = count;
				targetIndex = i;
			}
		}

		if (maxCount == 0) {
			return "a 없음";
		}

		return strArr[targetIndex].replace('a', 'A');
	}

	private static int countChar(String str, char ch) {
		int count = 0;
		for (char c : str.toCharArray()) {
			if (c == ch) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println("변경된 문자열: "
				+ StringUtil.replaceString(new String[] { "java program", "array", "java program area", "append" }));

		System.out.println("변경된 문자열(입력 없음): "
				+ StringUtil.replaceString(new String[] { }));
		
		System.out.println("변경된 문자열(a없음): "
				+ StringUtil.replaceString(new String[] { "jv progrm", "rry", "jv progrm re", "ppend" }));
	}//end of main
}// end of class
