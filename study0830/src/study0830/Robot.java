package study0830;

interface Movable{
	void move();
}

interface Activable{
	void activate();
}

class Robot_imp implements Movable, Activable{
	private String name;
	public Robot_imp(String name) {
		this.name=name;
	}
	public void move() {
		System.out.println(name  + " 가 지정된 경로로 이동합니다.");
	}
	public void activate() {
		System.out.println(name  + " 가 기계를 작동시킵니다.");
	}
}

class Main{
	
}
public class Robot {
	public static void main(String[] args) {
		Robot_imp robot_imp = new Robot_imp("로보");
		Movable movableRobotMovable = robot_imp;
		
		movableRobotMovable.move();
		Activable activableRobotActivable = robot_imp;
		activableRobotActivable.activate();
		
		controlRobot(robot_imp);
	}//end of main

	public static void controlRobot(Movable robotToMove) {
		robotToMove.move();
		if(robotToMove instanceof Activable) {
			System.out.println("로봇이 활성화 가능");
		}
	}
}


