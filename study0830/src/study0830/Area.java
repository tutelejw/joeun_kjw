package study0830;

interface AreaComputable {
	double getArea();
} // end of interface

class Circle implements AreaComputable{
	private double radius;
	
	public Circle(double radius) {
		this.radius = radius;
	}
	public double getArea() {
		return Math.PI * radius * radius;
	}
}

class Rectangle implements AreaComputable{
	private double width;
	private double height;
	public Rectangle (double width, double height) {
		this.width = width;
		this.height=height;
	}
	public double getArea() {
		return width*height;
	}
}

public class Area {
	public static void main(String[] args) {
		AreaComputable AcCir = new Circle(99);
		AreaComputable AcRec = new Rectangle(12,12);
		
		
		System.out.println(AcCir.getArea());
		System.out.println(AcRec.getArea());
	}
}
