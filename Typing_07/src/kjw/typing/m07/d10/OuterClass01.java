package kjw.typing.m07.d10;
//package jp02.part05;

/*
* FileName : OuterClass01.java
*  
*  << Inner Member Class >>
*  1. innerMember Class 는 class 를 구성하는 Field,method 와 같이 Class 가 구성하는
*       Member 가 될 수 있다. 
*  2. 사용이유는 : OuterClass와 밀접한 관련
*  3. 컴파일후 innerClass는 어떤 형태의 class 가 되는지 확인( ~~~$~~~.class)
*/
public class OuterClass01{
	
	///Field
	private String outer = "OuterClass 의 Field";
    
	///Constructor
    public OuterClass01(){
    }
    
    ///Method
	public void outerMethod(){
		System.out.println(":: "+this.getClass().getName()+" start..");
		// innerClass에 접근할 겨우  인스턴스 생성 후 접근
		InnerClass ic = new InnerClass();
        ic.innerMethod();
		System.out.println(":: "+this.getClass().getName()+" end..");
	 }

    ///InnerMember Class
	public class InnerClass{
		///Field
		private String inner = "InnerClass 의 Field";
		///Constructor
		public InnerClass(){
		}
		///Method
		public void innerMethod(){
			//innerClass에서 outer class 로 접근
            //OuterClass의 Field, Method 에 쉽게 접근 :: 레퍼런스 불필요
			System.out.println("==> "+this.getClass().getName()+" start..");
            //==>OuterClass 의 Field 접근 방식  :: 2 가지 
            System.out.println(outer);
			System.out.println(OuterClass01.this.outer);
			//==>아래의 실행문을 주석 풀면 compile error 가 발생한다. 이유는...
			//System.out.println(this.outer);
			System.out.println(inner);
			System.out.println("==>"+this.getClass().getName()+" end..");
		}
	}

	///main method
	public static void main(String[] args){
		new OuterClass01().outerMethod();
	}//end of main
}//end of class