class Pizza2{
	String type ; //피자 종류
	int size ; // 피자 사이즈인치
	
	//default constructor
	public Pizza2() {
		type="치즈";
		size =10;
		System.out.println("default constructor 초기화 : " + type +" / "+ size);
	}
	public Pizza2(String type, int size) {
		this.type=type;
		this.size=size;
		System.out.println("type, size 지정 constructor 초기화 : " + type +" / "+ size);
	}
	
	public void addTopping(String topping) {
		System.out.println("토핑 추가됨 : " + topping);
	}
	
	public void addTopping(String topping1, String topping2) {
		System.out.println("토핑 2 가지 추가됨 : " + topping1 + " / " + topping2 );
	}
	
	public void printPizzaInfo() {
		System.out.println("주문하신 피자는 " + size + " 인치 " + type + "피자입니다.");
	}
}
public class PizzaApp2 {
	public static void main(String[] args) {
	
		Pizza2 pizza1 = new Pizza2();
		pizza1.printPizzaInfo();
		Pizza2 pizza2 = new Pizza2(" 페퍼로니 ", 12);
		pizza2.printPizzaInfo();
		
		pizza2.addTopping("올리브");
		pizza2.addTopping("베이컨","파인애플");
		
		
	}
}
