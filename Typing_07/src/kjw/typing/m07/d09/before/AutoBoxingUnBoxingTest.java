package kjw.typing.m07.d09.before;
//package jp01.part08;

import java.util.*;

/*
	FileName : AutoBoxingUnBoxingTest.java
	
	::JDK 1.5추가기능 AutoBoxing/Auto-unBoxing
	1. primitive / Reference 는 상호 형변환 불가
	2. primitive / Reference 와의 호환을 위해 Wrapper class 제공
	    ==> int i = 1;
		==> Integer integer = new Integer(i);
		==> int j = integer.intValue();

		==> JDK 1.5에서는 위의 인스턴스 생성이 내부적으로 자동(?)으로 이루어 진다.
		==> Integer integer = 1    
				( 내부적으로  Integer integer = new Integer(i); 수행 :: autoboxing )
		==> int j = integer
				( 내부적으로  int j = integer.intValue(); 수행 :: autounboxing )
		==> 모든 Wrapper class에서 적용됨
*/
public class AutoBoxingUnBoxingTest{
	
	///Field
	
	///Constructor
	
	///Method
	//==>jdk1.4의 경우
	public void add14(int intValue,double doubleValue,boolean boo){
		Integer i = new Integer(intValue);
		Double d = new Double(doubleValue);
		Boolean b = new Boolean(boo);

		//==>각각의 출력하면...
		System.out.println("1 i.toString() : "+i);
		System.out.println("2 i.intValue() : "+i.intValue());
		System.out.println("3 d.toString() : "+d);
		System.out.println("4 d.doubleValue() : "+d.doubleValue());
		System.out.println("5 b.toString () : "+ b);
		System.out.println("6 b.booleanValue() : "+ b.booleanValue());
		
		double result = i.intValue()+d.doubleValue();
		System.out.println("합 : "+result);
	}
	
	//==>jdk1.5의 경우
	public void add15(int intValue,double doubleValue,boolean boo){
		//==> Autoboxing 기능 이해 ::  Method add14() 비교
		Integer i = intValue;			//내부적수행 ==>Integer i = new Integer(intValue); 
		Double d = doubleValue; //내부적수행 ==>Double d = new Double(doubleValue); 
		Boolean b = boo;				//내부적수행 ==>Boolean b = new Boolean(boo);

		//==>각각의 출력하면...
		System.out.println("11 i.toString() : "+i);
		System.out.println("12 i.intValue() : "+i.intValue());
		System.out.println("13 d.toString() : "+d);
		System.out.println("14 d.doubleValue() : "+d.doubleValue());
		System.out.println("15 b.toString () : "+ b);
		System.out.println("16 b.booleanValue() : "+ b.booleanValue());
		
		//==> Auto-unboxing 기능 이해.
		double result = i+d;          //내부적수행 ==>i.intValue()+d.doubleValue();
		System.out.println("합 : "+result);
	}
	
	//==>jdk1.4의 경우
	public void addArrayList14(int intValue){
		ArrayList arryList = new ArrayList();
		arryList.add(new Integer(intValue));
		Integer i = (Integer)arryList.get(0);
		System.out.println(i.intValue());
	}
	
	//==>jdk1.5의 경우
	//==>아래의 코딩에서 generic/autoboxing/auto unboxing 적용 이해
	public void addArrayList15(int intValue){
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		arrayList.add(intValue);      //==>autoboxing
		int i = arrayList.get(0); 		//==>autounboxing
		System.out.println(i);
	}
	
	///Main Method
	public static void main(String args[]){
		
		AutoBoxingUnBoxingTest test = new AutoBoxingUnBoxingTest();
		test.add14(1,1.2,true);
		
		System.out.println("===================");

		test.add15(1,1.2,true);
		
		System.out.println("===================");

		test.addArrayList14(10);
		
		System.out.println("===================");
		
		test.addArrayList15(10);
		
	}//end of main
	
}//end of class


//Note: Recompile with -Xlint:unchecked for details.