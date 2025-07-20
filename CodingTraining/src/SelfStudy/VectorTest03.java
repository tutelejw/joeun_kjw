package SelfStudy;

import java.util.List;
import java.util.Vector;

public class VectorTest03 {
	public static void main(String[] args) {
		List<String> vcStrList = new Vector<>();
		vcStrList.add("피카츄");
		vcStrList.add("라이츄");
		vcStrList.add("파이리");
		vcStrList.remove(1);
		System.out.println(vcStrList);
	}// out of main
}// out of class
