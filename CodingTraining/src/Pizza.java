public class Pizza {
	String type; // 피자종류
	int size;//파지사이즈,인치
	
	//default constructor
	public Pizza() {
		type="치즈피자";
		size=10;
	}
	
	public Pizza(String type, int size) {
		this.type = type;
		this.size=size;
	}
	public void addTopping(String topping) {
		System.out.println("topping " + topping + " 추가됨");
	}
	public void addTopping(String topping1, String topping2) {
		System.out.println("topping " + topping1 +" : " + topping2 + " 추가됨");
	}
	public void printPizzaInfo() {
		System.out.println("주문하신 피자는 " + type + size );
	}
}
