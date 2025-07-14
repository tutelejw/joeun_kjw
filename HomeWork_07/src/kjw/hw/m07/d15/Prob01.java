package kjw.hw.m07.d15;

import java.util.Vector;
import java.util.List;

	//java.uti.Vector, String, StringBuffer
	//일단 돌아가게 , interface 기반 변경, for문 확장형으로
public class Prob01 {
	public Vector<String> dataChange(String[] strData){
		//Vector<String> result = new Vector<>();
		List<String> result = new Vector<>();
		
		for (int i=strData.length -1; i>= 0; i--) {
			StringBuffer sb = new StringBuffer(strData[i]);
			//result.add(sb.toString());
			result.add(sb.reverse().toString());
		}
		return (Vector<String>) result;
	}
	
	///main method
	public static void main(String[] args) {
		Prob01 st = new Prob01();
		String[] strData = {"Java Programming", "JDBC", "Oracle10g", "JSP/Servlet"};
		Vector<String> v = st.dataChange(strData);
		
		for(int i=0; i< v.size(); i++) {
			System.out.println(v.elementAt(i));
		}
	}//end of main
}//end of class

//"JSP/Servlet"} 순서도 역순 , 글자도 역순으로 출력되어야함.
//"Oracle10g"
//"JDBC"
//"Java Programming"
