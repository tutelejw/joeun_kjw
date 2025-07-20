public class T09_ArrayTest02
{
	public static void main(String[] args){
		
	int[][] value = new int[9][9];

	for (int i=0;i<9 ;i++ )	{
//		value[0][i] = (i+1);
		value[0][i] = 1* (i+1);
		System.out.println("첫번째 for문 - value 값 : " + value[0][i]);
	}

	for (int j=0; j < value.length;j++ ){
		System.out.println("==================================");
		for (int k=0;k<value[j].length ;k++ )		{
//			value[j][k] = 1* (j+1);
			System.out.println("value["+j+"]["+k+"]="+value[j][k]);
		}

	}

	}
}