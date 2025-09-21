package study0920.interface_abstract;

abstract class Vehicle{
	protected int speed;
	
	public Vehicle(int speed) {
		this.speed=speed;
	}
	public void accelerate() {
		speed += 10;
		System.out.println(" 속도가 10  증가. 현재 속도 : "  +  speed);
	}
	public abstract void drive();
}

interface Driveable{
	void turnLeft();
	void turnRight();
}

class Car extends Vehicle implements Driveable{
	
	public Car (int speed) {
		super(speed);
	}
	public void drive() {
		System.out.println("자동차가 도로를 달립니다." + speed );
	}
	public void turnLeft() {
		System.out.println("자동차가 좌회전 합니다.");
	}
	public void turnRight() {
		System.out.println("자동차가 우회전 합니다.");
	}
}

class Bicycle extends Vehicle{
	public Bicycle (int speed) {
		super(speed);
	}
	public void drive() {
		System.out.println("자전거가 길을 달립니다.");
	}
	
}

public class VehicleControlSystem {
	public static void main(String[] args) {
		Car car = new Car(50);
		car.drive();
		car.accelerate();
		car.turnLeft();
	}
}
