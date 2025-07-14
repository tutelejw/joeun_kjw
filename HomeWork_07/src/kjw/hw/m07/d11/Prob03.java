package kjw.hw.m07.d11;

public class Prob03 {
    public static String myReplace(String str, char oldChar, char newChar) {
//        boolean changed = false;
//        StringBuilder sb = new StringBuilder();
//
//        for (int i = 0; i < str.length(); i++) {
//            char c = str.charAt(i);
//            if (c == oldChar) {
//                sb.append(newChar);
//                changed = true;
//            } else {
//                sb.append(c);
//            }
//        }
//     
        //   return changed ? sb.toString() : str;
    	
    	// return str.replace(oldChar, newChar);  //특정 문자를 다른 문자로 바꾸는 기능
        
        String regex = java.util.regex.Pattern.quote(String.valueOf(oldChar));  // replaceAll 사용 (정규표현식)
        return str.replaceAll(regex, String.valueOf(newChar));

    }

    public static void main(String[] args) {

        System.out.println("문자열에 특정문자 변경하는 테스트");

        System.out.println("----------Sample1----------");
        String str1 = myReplace("Hello world", 'l', '*');
        System.out.println(str1); 

        System.out.println("----------Sample2----------");
        String str2 = myReplace("Hello world", ' ', '-');
        System.out.println(str2);

        System.out.println("----------Sample3----------");
        String str3 = myReplace("Hello world", 'a', '*');
        System.out.println(str3); 
    }
}
