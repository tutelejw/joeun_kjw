class TopSecret01{
	int secretNo=7777;

	public TopSecret01(){
	}

	public int getSecretNo(){
		return secretNo;
	}
}// end of class

public class ModifierTest01{
	public static void main(String[] args){
		TopSecret01 topSecret = new TopSecret01();
		System.out.println(topSecret.getSecretNo());
	}//end of main
} //end of class