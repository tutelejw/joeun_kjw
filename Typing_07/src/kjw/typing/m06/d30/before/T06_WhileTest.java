public class T06_WhileTest
{
	public static void main(String[] args){
		int i = 0;
		while(i<10){
			System.out.println("여기는 while 문 내부안임 i = " + i);
			i++;
		}

		int j=0;
		do{
			System.out.println("\n\t여기는 do문 내부임 j = " + j);
			j++;
		} while(j<10);

		System.out.println("\n=======================================");

		int k=1;
		while(k<10){
			System.out.println("5 * "+k+" = "+5*k);
			k++;
		}

/*		while (true ){
			System.out.println("여기는 반복문 내부의 무한루프");
		}
		*/
	}
}