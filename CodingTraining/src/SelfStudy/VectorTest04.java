package SelfStudy;

import java.util.List;
import java.util.Vector;

public class VectorTest04 {
	public static void main(String[] args) {
		List<String> strList = new Vector<>();
		strList.add("봄");
		strList.add("여름");
		strList.add("가을");
		strList.add("겨울");
		
		for (int i=0 ; i < strList.size(); i++) {
			System.out.println(strList.get(i));
		}// out of for
	}//out of main
}// out of class
