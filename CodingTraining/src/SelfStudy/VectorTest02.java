package SelfStudy;

import java.util.List;
import java.util.Vector;

public class VectorTest02 {
	public static void main(String[] args) {
		//vector 에 5개 정수 데이터 추가
		List<Integer> vcIntList = new Vector<>();
		for (int i=0 ; i<5; i++) {
			vcIntList.add((i+1)*10);
		}
		System.out.println("Vector 크기 : " + vcIntList.size());
		System.out.println("엔덱스 2의 값 : " + vcIntList.get(2));
	}// out of main
}// out of class
