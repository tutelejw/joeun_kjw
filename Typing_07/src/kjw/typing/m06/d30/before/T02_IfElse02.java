public class T02_IfElse02
{
	public static void main(String[] args){
		int x = 1;
		int y = 2;

		if(x==3 & y==2){
			System.out.println("x=1 이고 y=2입니다."); //조건이 안맞아서 실행이 안됨
		}

		if (x==1 | y==3){
			System.out.println("x=1 이거나 y=3입니다."); //or 조건으로 성립되서 실행됨.
		}

		if (x==3 && y==2){
			System.out.println("x=1 이고 y=2입니다.");
		}

		if (x==1 || y==3){
			System.out.println("x=1 이거나 y=3입니다.");
		}
	}
}