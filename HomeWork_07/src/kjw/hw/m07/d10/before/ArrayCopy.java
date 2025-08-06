package kjw.hw.m07.d10.before;
import java.util.List;
import java.util.Vector;

public class ArrayCopy {
	public List<String>moveToVectorList(String[] datas){
		List<String> vector = new Vector<>();
		for (int i=datas.length-1; i >= 0; i--) { // 역방향  스택을 쓰면 이렇게 안해도 된다??
			//System.out.println(datas[i]);
			vector.add(datas[i]);
		}
		return vector;
	}
	
	public Vector<String>moveToVector(String[] datas){
		Vector<String> vector = new Vector<>();
		//for (int i=0; i < datas.length; i++) {  // 정방향
		for (int i=datas.length-1; i >= 0; i--) { // 역방향
			//System.out.println(datas[i]);
			vector.add(datas[i]);
		}
		return vector;
	}
	
	public static void main(String args[]) {
		ArrayCopy aCopy = new ArrayCopy();
		String datas[]= {"1","2","3","4","5"};
		
		System.out.println("Vector\t"+aCopy.moveToVector(datas));
		System.out.println("List\t" + aCopy.moveToVectorList(datas));
	}
}
