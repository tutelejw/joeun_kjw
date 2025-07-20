public class ExceptionTest03 {

	//Field
	private int sum;
	private int avg;

	//Constructor
	public ExceptionTest03() {
	}

	//Method
	public void sum(int x, int y) {
		System.out.println("1. ==> sum 시작");
		sum = x + y;
		System.out.println("1. ==> 합 : " + sum);
		System.out.println("1. ==> sum 끝");
	}

	//JVM에게 ArithmeticException을 던지도록 throws 선언
	public void avg(int z) throws ArithmeticException { // 'throws ArithmeticException' 추가
		System.out.println("2. ==> avg 시작");
		//z = 0 일 경우 불능
		avg = sum / z;
		System.out.println("2. ==> 평균 : " + avg);
		System.out.println("2. ==> avg 끝");
	}

	//main method
	public static void main(String[] args) {
		int i = Integer.parseInt(args[0]);
		int j = Integer.parseInt(args[1]);
		int k = Integer.parseInt(args[2]);

		ExceptionTest03 et = new ExceptionTest03();
		et.sum(i, j);
		try {
			et.avg(k); // throws 선언된 메소드 호출 시 try-catch로 감싸야 함
		} catch (ArithmeticException e) {
			System.out.println("====> JVM이 예외 처리함");
			System.out.println("====> Exception의 메시지 : " + e.getMessage());
		}
		System.out.println("main Method End ...");
	} //end of main
} // end of class