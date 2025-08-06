package kjw.hw.m07.d07;

import java.util.Scanner;

public class FactorialUtil {

	public int factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

	public static String resultStr(int n) {
        String list = "";
        for (int i = n; i >= 1; i--) {
            list += i;
            if (i > 1) {
                list += "*";
            }
        }
        return "(" + list + ")";
    }
	
	public static void main(String[] args) {
	
		Scanner keyboard = new Scanner(System.in);
		System.out.print("입력하세요 : ");
		int num = keyboard.nextInt();
		
		FactorialUtil util = new FactorialUtil();
		System.out.print("팩토리얼 결과 값 = " + util.factorial(num) + resultStr(num));		
		keyboard.close();
	} // out of main
} // out of class


/*
public class FactorialUtil {

	public String factorial(int n) {
		int result = 1;
		String resultStr="";
		for (int i = n ; i >= 1; i--) {
			result *= i;
			resultStr += i;
			if (i > 1) {
                resultStr += "*";
            }
			//System.out.print(resultStr);
		}
		return result + "(" + resultStr + ")";
	}

	
	public static void main(String[] args) {
	
		Scanner keyboard = new Scanner(System.in);
		System.out.print("입력하세요 : ");
		
		FactorialUtil util = new FactorialUtil();
		System.out.print("팩토리얼 결과 값 = " + util.factorial(keyboard.nextInt()));	
		
		keyboard.close();
	} // out of main
} // out of class
*/