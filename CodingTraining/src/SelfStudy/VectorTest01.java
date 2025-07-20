package SelfStudy;

import java.util.List;
import java.util.Vector;

public class VectorTest01 {
	public static void main(String[] args) {
		List<String> arrStr = new Vector<>();
		arrStr.add("사과");
		arrStr.add("바나나");
		arrStr.add("딸기");
		
		for (int i=0 ; i < arrStr.size(); i++) {
			System.out.println("vc값 : " + i + " " + arrStr.get(i));
		}
	}// out of main
}//out of class
