package study0830.movable;

class Car implements Movable {

	public void move() {
		System.out.println("도로위를 달립니다.");
	}
}

//public class Car implements Movable: 이 코드는 Car 클래스가 Movable 인터페이스의 규칙을 따르겠다고 선언하는 것입니다. 
//implements 키워드를 사용합니다.

//@Override: 이 어노테이션은 부모 클래스나 인터페이스의 메서드를 재정의했음을 나타냅니다.

//public void move() { ... }: Movable 인터페이스에서 정의한 move() 메서드를 실제로 구현하는 부분입니다. 
//자동차는 '도로 위를 달리는' 방식으로 움직이므로, 그 내용을 메서드 안에 넣습니다.

class Airplane implements Movable{
	public void move() {
		System.out.println("비행기가 하늘을 납니다.");
	}
}

