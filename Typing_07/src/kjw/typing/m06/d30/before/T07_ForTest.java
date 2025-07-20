public class T07_ForTest
{
	public static void main(String[] args){
	
	int j=5;
	System.out.println(j + " 단을 출력합니다.");

	for(int i=1; i<10; i++){
		System.out.println( j + " * " + i + " = " + j*i);

	}

	int k=1;
	while (k < 10){
		System.out.println("5 *" + k + " = " + 5*k);
		k++;
	}

//	System.out.println("i의 최종변경값 : " + i);
//	System.out.println("k의 최종변경값 : " + k);

/*	for( ; ; ){
		System.out.println("여기는 반복문 내부의 무한 루프");
	}*/
//	System.out.println("error 가 발생한다. 이유는...");
	}

}