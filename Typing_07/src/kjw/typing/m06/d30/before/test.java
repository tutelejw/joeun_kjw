public class T10_Example5
{
	public static void main(String[] args){
	
	String[][] text = new String[9][9];
/*	text[1][1] = "0";
	int i=2;
	int j=1;
*/
	for (int i=0; i<9 ;i++ ){
		System.out.println((i+1)+" 단을 출력합니다.");

		for (int j=0;j<9 ;j++ ){
			text[i][j] = (i+1)+"*"+(j+1)+"="+(i+1)*(j+1);
			System.out.println("text["+(i+1)+"]["+(j+1)+"] : " + text[i][j]);
		}
	}
	}
}