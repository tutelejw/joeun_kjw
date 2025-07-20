package SelfStudy;

import java.util.List;
import java.util.Vector;

public class VectorTest05 {
	public static void main(String[] args) {
		List<Integer> intList = new Vector<>();
		
		for (int i=0; i < 10; i++) {
			intList.add(i+1);
		}
		for (int j=0; j < intList.size();j++) {
			if (intList.get(j) % 2 == 0) {
				System.out.println(intList.get(j));
			}
		}
	}

}
