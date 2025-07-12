package ch3;

public class VarTest2 {
	public static void main (String[] args) {
		String name = "홍길동";
		String address = "서울시 서초구 서초동";
		boolean isBoolean = true;
		
		int num=10;
		System.out.println(num);
		
		address = "경기도 수원시 팔달구";
		System.out.println(name +"이 실제 주소는"+address +"입니다.");
		System.out.println();
		System.out.println("isBoolean 의 값은"+ isBoolean + "입니다.");
		
		isBoolean = false;
		System.out.println("isBoolean 의 값은"+ isBoolean + "입니다.");
	}

}
