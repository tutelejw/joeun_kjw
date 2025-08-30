package study0830.movable;

public class Main{

public static void main(String[] args) {
	Movable myCar = new Car();
	Movable myAirplane = new Airplane();
//	myCar.move();
//	myAirplane.move();

	Movable[] thingsThatMove = {new Car(), new Airplane()};
	for (int i=0; i<thingsThatMove.length ; i++) {
		System.out.println("포문 실행");
		System.out.println("i : " + i);
		System.out.println("thingsThatMove : " + thingsThatMove[i]);
		thingsThatMove[i].move();
		
	}
	//Movable[] thingsThatMove = { myCar.move(), myAirplane() };
	
}
}