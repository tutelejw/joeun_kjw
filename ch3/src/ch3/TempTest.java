package ch3;

public class TempTest {
	public static void main(String[] args) {
		int BASE=32;
		float celsius=0f, fahrenheit=0f;
		fahrenheit=100;
		
		celsius=(fahrenheit-BASE)*5/9;  //실수 100.0f-32=100.0f-32.0f=68.0f
		System.out.println("fffdlatl : "+(fahrenheit-BASE));
		System.out.println("화씨: " + fahrenheit+", 도에 대한 썹씨는 :" + celsius + "입니다.");
		
		fahrenheit=(float)celsius*9.0f/5.0f+BASE;		System.out.println("섭씨 : " + celsius + "에 대한 화씨는 : " + fahrenheit + "입니다.");
	}
}
