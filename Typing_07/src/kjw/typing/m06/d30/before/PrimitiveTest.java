public class PrimitiveTest 
{
	public static void main(String[] args){
		
		boolean boo = true;

		char c = '흥';

		byte b = 127;

		short s = 1000;
		int i = 1000;
		long l = 1000L;

		float f = 1000.5F;
		double d = 1000.5;

		System.out.print("자바의 Primitive Data type 을 ++");
		System.out.println("::사용하여 표준출력 장치로 ,,," + ":: 출력");

		System.out.println("논리형 : " + boo);
		System.out.println("문자형 : " + c);
		System.out.println("정수형 byte : " + b);
		System.out.println("정수형 short : " + s);
		System.out.println("정수형 int : " + i);
		System.out.println("정수형 long : " + l);
		System.out.println("실수형 float : " + f);
		System.out.println("실수형 double : " + d);
	}

}
