public class PizzaApp {
	public static void main(String[] args) {
		Pizza pizza1 = new Pizza();
		pizza1.printPizzaInfo();
		
		Pizza pizza2 = new Pizza("페퍼로니", 12);
		pizza2.addTopping("올리브");
		pizza2.addTopping("베이컨", "파인애플");
		pizza2.printPizzaInfo();
				
		
	}
}
