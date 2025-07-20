public class T08_ArrayTest01
{
	public static void main(String[] args){
//	public static void main(String args[]){


		int[] intArray = new int[9];
		intArray[0]=1;
		intArray[1]=2;
		intArray[2]=3;
		intArray[3]=4;
		intArray[4]=5;
		intArray[5]=6;
		intArray[6]=7;
		intArray[7]=8;
		intArray[8]=9;

//		int[] intArray = {1,2,3,4,5,6,7,8,9};
//		int[] intArray = new int[](1,2,3,4,5,6,7,8,9};
		
		for (int j=0; j<9 ;j++ )
		{
			System.out.println("intArrayj["+j+"] 값은 : " + intArray[j]);
		}

		System.out.println("=============================");
		for (int k=0;k<intArray.length ;k++ )
		{
			System.out.println("5 X "+intArray[k] + " = " +5*intArray[k]);
		}

		int []j=intArray;
		System.out.println("===============================");
		System.out.println("j[0]의 값은 : " +j[0]);
		System.out.println("j[5]의 값은 : " +j[5]);

		System.out.println("테스트 - j[5]==> " +j[5]);
		System.out.println("테스트 - intArray[5]==> " +intArray[5]);
		j[5]=1234;
		System.out.println("j[5]==> " +j[5]);
		System.out.println("intArray[5]==> " +intArray[5]);
	}
}


//마지막 j[5] 값과 intArray[5]의 값이 같은 이유를 알려줘