public class Adder{

		public int execute(String expr){
			int result = 0;
			String strNum = "";
//			System.out.println("expr = " + expr + "length" + expr.length());

			for (int i=0; i<expr.length(); i++ )	{
				char c = expr.charAt(i);
//				System.out.print ( "(i) :  " + expr.charAt(i));
				if (c == '+'){
					result += Integer.parseInt(strNum);
					strNum = "";
				}else{
					strNum += c;
				}
			}
		return result += Integer.parseInt(strNum);
		}	
	
	public static void main(String[] args){
		Adder adder = new Adder();
		String expr = "3+5+9+1";
		System.out.println(expr + "=" + adder.execute(expr));
		expr = "11+45+77+3";
		System.out.println(expr + "=" + adder.execute(expr));
		expr = "33+51+12+11";
		System.out.println(expr + "=" + adder.execute(expr));
	}// out of main
}//out of class

//클래스 실행 결과
//3+5+9+1=18
//11+45+77+3=136
//33+51+12+11=107