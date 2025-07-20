package kjw.typing.m07.d18;

class Account{
	private String accountNo;
	private boolean isCreditLine;
	private int balance;
	private int test;
	
	public Account() {
	}
	public Account(String accountNo, boolean isCreditLine, int balance, int test) {
		this.accountNo=accountNo;
		this.isCreditLine=isCreditLine;
		this.balance = balance;
		this.test = test;
	}
	
	public void withdraw ( int menoy) {
		balance -= menoy;
	}
	
	//return "";	
}

public class Prob01 {
	public static void main(String[] args) {
		Account account01 = new Account("111-11-111", true, 1000000, 100000);
		
		try {
			System.out.println("0.최초계좌상태");
			System.out.println(" 계좌정보 " + account01);
			
			System.out.println("\n" + account01.getAccountNo() + "계좌 200000 출금요청");
			account01.withdraw(200000);
			
			
		}
	}//out of main
}// out of class
