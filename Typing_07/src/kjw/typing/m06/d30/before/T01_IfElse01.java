public class T01_IfElse01  
{
	public static void main(String[] args) 
	{
		boolean b1 = true;
		boolean b2 ;

		b2 = !b1;
		int x = 1;
		int y = 2;

		if(x<y){
			System.out.println("x = " + x + ", y = " + y + " y가 크네요");
		}else{
			System.out.println("x = " + x + ", y = " + y + " x가 크네요");
		}

		if (x == y){
			System.out.println("x = " + x + ", y = " + y + " x,y는 같아요");

		}

		if (b1){
			System.out.println("if 의 조건문이 b2 = false 입니다.");
		} else if(b1) {
			System.out.println("if 의 조건문이 b1 = true 입니다.");
		}
	}
}
