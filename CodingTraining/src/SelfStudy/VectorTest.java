package SelfStudy;

import java.util.List;
import java.util.Vector;

public class VectorTest {
	public static void main(String[] args) {
		List<String> vcString1 = new Vector<>();
		//System.out.println(vcString1);
		System.out.println(vcString1.size());
		vcString1.add("바나나");
		System.out.println(vcString1.size());
		vcString1.add("나무");
		System.out.println(vcString1.size());
		
		for (int i=0; i<10; i++) {
			String temp = i+"";
			vcString1.add(0, temp);
			System.out.println(vcString1.size());
		}
		System.out.println(vcString1);
		System.out.println(vcString1.size());
		
		for (int j=0; j< vcString1.size(); j++) {
		System.out.println("vcString1get : " +  j + " / " + vcString1.get(j));
		}
	}//out of main
}//out of class
