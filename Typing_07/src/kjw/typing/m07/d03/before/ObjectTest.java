package kjw.typing.m07.d03.before;  

/*
  FileName : ObjectTest.java 
  
   ==> static modifier 이해
  	1. 상태와 무관한 상태값  ( ? )
  	2. 상태와 무관한 행위 ( ? )
  	
  	==> ObjectTest.class 실행시 출력결과를 예측 실행 비교
  	==> static : 클래스로 생성된 인스턴스들이 모두 공유하는 속성 , 행위
  	                   : ClassName.~~ 접근 가능.
*/
public class ObjectTest{
	
	///Field
	int i;
	static int j;
	

	///constructor
	public ObjectTest(){
		System.out.println("ObjectTest default Constructor :: C> : i = "+i );
	}
	
	///mehtod(instance method)
	public void printInstanceInt(){
		System.out.println("D> : i = " +i);
	}
	///static method ( OR class method )
	public static void printClassInt(){
		System.out.println("E> : j = "+j);
	}

		///Static Block
	static{
		System.out.println("A> : j = "+j + "static block를 제일 아래로 이동함다. 그래도 제일 우선 실행됨다");
		j = 1;
		System.out.println("B> : j = "+j + "static block를 제일 아래로 이동함다. 그래도 제일 우선 실행됨다");
	}
		
	///main mehtod
	public static void main(String args){
		
		System.out.println("|===============|");
		ObjectTest obj1 = new ObjectTest();
		obj1.printInstanceInt();
		obj1.printClassInt();
		System.out.println("|===============|");
		ObjectTest.printClassInt();
		
	    //==> 위의 출력결과를 이해한다면 아래를 주석풀고 실행한다면 결과를 예측하면...
		System.out.println("|===============|");
		ObjectTest obj2 = new ObjectTest();
		obj2.i=100;
		System.out.println("obj2.i = "+obj2.i + " :  obj1.i = "+obj1.i );
		
		System.out.println("|===============|");
		obj2.j = 3333;
		System.out.println("obj2.j = "+obj2.j + " : obj1.j = "+obj1.j  );
		System.out.println("ObjectTest.j = "+ObjectTest.j );
		
	}//end of main
	
}//end of class
