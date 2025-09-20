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

public class VehicleControlSystem {

}
