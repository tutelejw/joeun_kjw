
final class TopSecret03{
///Field
	private final int secretNo=7777;

///Constructor
	private TopSecret03(){
	}

///Method
	public int getSecretNo(int pwd){
//		if(pwd ==0){
//			return secretNo;
//		}else{
//			return 0;
//		}
//		pwd == 0?return secretNo:return 0;  << 오류오류오류오류 잘못된
		return (pwd == 0)?secretNo : 0;

	}
	
	public static TopSecret03 getInstance(){
		TopSecret03 topSecret = new TopSecret03();
		return topSecret;
		//return new TopSecret03();
	}
}//end of class

public class ModifierTest03{
	///Main
	public static void main(String[] args){
		TopSecret03 topSecret = TopSecret03.getInstance();
		System.out.println(topSecret.getSecretNo(0));
	}//end of main

}//end of class

