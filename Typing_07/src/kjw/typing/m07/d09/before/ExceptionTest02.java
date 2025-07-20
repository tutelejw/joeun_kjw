public class ExceptionTest02 {

	//Field
	private int sum;
	private int avg;

	//Constructor
	public ExceptionTest02() {
	}

	//Method
	public void sum(int x, int y) {
		System.out.println("1. ==> sum 시작");
		sum = x + y;
		System.out.println("1. ==> 합 : " + sum);
		System.out.println("1. ==> sum 끝");
	}

	public void avg(int z) {
		System.out.println("2. ==> avg 시작");
		//z = 0 일 경우 불능
		try {
			avg = sum / z;
		} catch (ArithmeticException e) {
			// JVM에서 ArithmeticException이 발생되면 Exception이 던져진다
			System.out.println("!!!!==> ArithmeticException 발생");
			System.out.println("!!!!==> Exception 처리됨");
		} finally {
			System.out.println("!!!!==> avg() Z : " + z + " 값을 나누지 못함");
		}
		System.out.println("2. ==> 평균 : " + avg); // 이 부분은 예외 발생 시 0으로 출력됨
		System.out.println("2. ==> avg 끝");
	}

	//main method
	public static void main(String[] args) {
		int i = Integer.parseInt(args[0]);
		int j = Integer.parseInt(args[1]);
		int k = Integer.parseInt(args[2]);

		ExceptionTest02 et = new ExceptionTest02();
		et.sum(i, j);
		et.avg(k);

		System.out.println("main Method End ...");
	} //end of main
} // end of class