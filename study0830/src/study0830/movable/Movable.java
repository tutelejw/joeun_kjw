package study0830.movable;

public interface Movable {
	public void move();
}

//public interface Movable: 이것은 Movable이라는 이름의 인터페이스를 선언하는 부분입니다.
//void move();: 이 줄은 Movable 인터페이스를 구현하는 모든 클래스가 반드시 가져야 하는 move라는 메서드를 정의합니다. 
//메서드의 내용(본문)은 없고, 이름과 반환 타입만 정의합니다.
//인터페이스는 규칙만 제시할 뿐, 실제 동작은 각 클래스가 알아서 구현하도록 합니다.