public class T03_IfElseETC
{
	public static void main(String[] args){
		System.out.println("입력하신 \"1번째 인수 \" : " + args[0]);
		System.out.println("입력하신 \"2번째 인수 \" : " + args[1]);

		System.out.println("======================================");

		int i = Integer.parseInt(args[0]);  //입력 문자를 int Data Type으로 변경한는 Interger.parseInt() 사용
		int j = Integer.parseInt(args[1]);

		System.out.println("i= " + i + "\t j=" + j);  // \t 는 tab으로 띄울때.
		System.out.println("한줄처리\n");
		System.out.println("======================================");

		if(i>j) {
			System.out.println("i 가 j 보다 큽니다.");
		} else if (i == j){
			System.out.println("i 와 j 는 같습니다.");
		} else if (i<j){
			System.out.println("i 보다 j 가 큽니다.");
		}
	}
}