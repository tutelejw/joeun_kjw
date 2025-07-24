package kjw.typing.m07.d09.before;


/*
*	FileName : CalendarTest.java  
*
*     1. 시간과 날짜를 객체모델링한 java.util.Calendar 
*     2. 시간과 날짜를 객체모델잉한 java.util.Data를 확인하여 deprecation 의미 확인
*     3.  static 의 활용
*     4.  wrapper class 사용
*     5.  java.lang package 이외 다른 package 사용
*/
import java.util.*;

public class CalendarTest{
	
	///Main method
	public static void main(String args[])	{
		
		////==> Calendar instance 생성 (Calendar.class는 abstract class 로 인스턴스 불가.)
		//==>1. 객체생성 할 수 없다면 Calendar.class는 어떻게 사용할 것인가.. (static Method 활용)
		Calendar cld = Calendar.getInstance();

		//==> 2. 다름 방법은... ( Generalization / ~ is a ~ / 묵시적 형변환 / Hierarchy 이해..)
		//Calendar cld = new GregorianCalendar();

		// static  활용 : 각 각의 static Field  API에서 확인
		System.out.println("년 : "+cld.get(Calendar.YEAR));
		System.out.println("월 : "+cld.get(Calendar.MONTH));
		System.out.println("일 : "+cld.get(Calendar.DATE));
		System.out.println(cld.get(Calendar.YEAR)+"년의: "+
				                                                           cld.get(Calendar.WEEK_OF_YEAR)+"번째주");
		System.out.println(cld.get(Calendar.YEAR)+"년의: "+
                                                                           cld.get(Calendar.WEEK_OF_MONTH)+"번째달");
		System.out.println("이번달의 : "+cld.get(Calendar.DAY_OF_MONTH)+"번째일");
		System.out.println(cld.get(Calendar.YEAR)+"년의: "+
                                                                          cld.get(Calendar.DAY_OF_YEAR)+"번째일");
		System.out.println("이번주의 : "+cld.get(Calendar.DAY_OF_WEEK)+"번째일");
		System.out.println("오전,오후 : "+cld.get(Calendar.AM_PM));
		System.out.println("시간 : "+cld.get(Calendar.HOUR));
		System.out.println("24시간 : "+cld.get(Calendar.HOUR_OF_DAY));
		System.out.println("분 : "+cld.get(Calendar.MINUTE));
		System.out.println("초 : "+cld.get(Calendar.SECOND));
		System.out.println("밀리초 : "+cld.get(Calendar.MILLISECOND));

		// wrapper class  이해 및 사용
		// equals(Object obj) ==> 인자를 Object 로 받는다는 의미는...
		// java 의 모든 class 는 equals Method 의 인자로 전달 될 수 있으나
		// 인자로 전달될수 없는 8EA의 Data Type 이 있다(Primitive Data Type)
		//==> Primitive Data 를 Reference Data 로 사용할수 있게 한다.
		int i = 100;
		Integer integer = new Integer(i);  
		System.out.println(" 1 : "+integer.toString());
		System.out.println(" 2 : "+integer.intValue());
		System.out.println(" 3 : "+integer.doubleValue());
	}//end of main
}//end of class