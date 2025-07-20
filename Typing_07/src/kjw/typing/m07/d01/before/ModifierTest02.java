class TopSecret02{
	private int secretNo = 7777;

	//public TopSecret02(){
	//}

	public int getSecretNo(int pwd){
		if(pwd == 0){
			return secretNo;
		}else {
			return 0;
		}
	}
} // end of class

public class ModifierTest02{
	public static void main(String[] args){
		TopSecret02 topSecret = new TopSecret02();
		System.out.println(topSecret.getSecretNo(0));
		System.out.println(topSecret.getSecretNo(1));
	}  // end of main
} // end of class

