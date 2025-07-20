
public class MethodSyntaxTestApp {

	public static void main(String[] args) {
		
		MethodSyntax methodSyntax = new MethodSyntax();
		
		System.out.println("\n=====================");
		methodSyntax.browerOn();
		
		System.out.println("\n=====================");
		boolean result=methodSyntax.documentWork();
		System.out.println("doc작업유무 : " + result);
		
		System.out.println("\n=====================");
		int value = methodSyntax.sum(1, 1);
		System.out.println("계산 결과 값 : " + value);
		System.out.println("계산 결과 값 : " + methodSyntax.sum(1, 2));
		
		System.out.println("\n=====================");
		String add = methodSyntax.getAdd();
		System.out.println("Field정보 add : " + add);
		System.out.println("Field정보 add : " + methodSyntax.getAdd());
		
		System.out.println("\n=====================");
		String[] info = methodSyntax.getAllInformation();
		System.out.println("field 정보 모두 출력");
		
		for(int i=0; i<info.length; i++) {
			System.out.println(i+1 + "번째 정보 : " + info[i]);
		}
		}
}
