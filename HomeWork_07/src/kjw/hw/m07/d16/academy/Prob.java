package kjw.hw.m07.d16.academy;

public class Prob {
	public static void main(String[] args) {
		String value = "홍길동";
		String strAdd ="";

		for (int i=0 ; i<value.length();i++) {
			String str=value.substring(i,i+1);
			
			byte[] temp = str.getBytes();
			System.out.println("str : " + str + " toHexString : " + Integer.toHexString(temp[i]).substring(6,8) );
				if(temp.length == 3) {
					//한글이면 str = 
					//str="%"+Integer.toHexString(temp[i]).substring(6,8);
					str="%"+Integer.toHexString(temp[i]).toUpperCase().substring(6,8);
				}
			String imsi = "홍";			
				strAdd += str;
			
//			byte[] imsiByte = imsi.getBytes();
//			System.out.println("imsiByte : " + imsiByte[0] + " "+imsiByte.length +" " + Integer.toHexString(imsiByte[0]));
			
			
			
		}
		System.out.println(strAdd);
	}
}
