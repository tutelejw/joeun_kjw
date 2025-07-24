package kjw.typing.m07.d18;

class Account{
	private String accountNo;
	private boolean isCreditLine;
	private int balance=0;
	private int creditLineLimit;
	
	public Account() {
	//super();
	}
	
	public Account(String accountNo, boolean isCreditLine, int balance, int creditLineLimit) {
		this.accountNo=accountNo;
		this.isCreditLine=isCreditLine;
		this.balance = balance;
		this.creditLineLimit = creditLineLimit;
	}
		
	public String getAccountNo() {
		return this.accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public boolean getIsCreditLine() {
		return this.isCreditLine;
	}
	public void setIsCreditLine(boolean isCreditLine) {
		this.isCreditLine=isCreditLine;
	}
	public int getBalance() {
		return this.balance;
	}
	
	public int getCreditLineLimit() {
		return creditLineLimit;
	}
	public void setCreditLineLimit(int creditLineLimit) {
		this.creditLineLimit=creditLineLimit;
	}
	public void setBalance(int balance) {
		this.balance=balance;
	}
	
	public void withdraw ( int menoy)  throws Exception{
	//public void withdraw ( int menoy) {
		if(isCreditLine && (creditLineLimit + balance) < menoy) {
			throw new Exception("마통 잔고 및 통잔 잔고가 부족함 출금 불가.");
			//System.out.println("마통 잔고 및 통잔 잔고가 부족함 출금 불가.");
		}else if(! isCreditLine && balance < menoy) {
			throw new Exception("통장 잔고부족 함 출금 불가.");
			//System.out.println("마통장 잔고부족 함 출금 불가.");
		}else {
			this.balance -= menoy;
		}
	}
	public void deposit(int menoy){
		this.balance += menoy;
	}

	public String toString() {
	    return "계좌정보: " + accountNo +
	            ", 잔역: " + balance +
	    		", 마통여부: " + isCreditLine +
	            ", 마통잔액: " + creditLineLimit;
	}
}

public class Prob01 {
	public static void main(String[] args) {
		Account account01 = new Account("111-11-111", true, 1000000, 100000);
		
		try {
			System.out.println("0.최초계좌상태");
			System.out.println(" 계좌정보 " + account01);

			System.out.println("\n0." + account01.getAccountNo() + "계좌 2000000 출금요청");
			account01.withdraw(2000000);
			System.out.println(" 계좌정보 " + account01);
			
			System.out.println("\n1." + account01.getAccountNo() + "계좌 200000 출금요청");
			account01.withdraw(200000);
			System.out.println(" 계좌정보 " + account01);
			
			System.out.println("\n2." + account01.getAccountNo() + "계좌 100000 입금요청");
			account01.deposit(100000);
			System.out.println(" 계좌정보 " + account01);
			
			System.out.println("\n3." + account01.getAccountNo() + "계좌 500000 출금요청");
			account01.withdraw(500000);
			System.out.println(" 계좌정보 " + account01);
			
			System.out.println("\n4." + account01.getAccountNo() + "계좌 600000 출금요청");
			account01.withdraw(600000);
			System.out.println(" 계좌정보 " + account01);
			System.out.println("\n4." + account01.getAccountNo() + "계좌 500000 출금요청");
			account01.withdraw(500000);
			System.out.println(" 계좌정보 " + account01);
			
		} catch(Exception e) {
			System.out.println(">>>> 예외발생 : " + account01.getAccountNo() + " : " + e.getMessage());
		}
		
		System.out.println("====================");
		
		Account account02 = new Account();
		try {
			account02.setAccountNo("222-222-222");
			System.out.println("0.최초계좌상태");
			System.out.println(" 계좌정보 " + account02);
			
			System.out.println("\n1." + account02.getAccountNo() + "계좌 100000 입금요청");
			account02.deposit(100000);
			System.out.println(" 계좌정보 " + account02);
			
			System.out.println("\n2." + account02.getAccountNo() + "계좌 50000 출금요청");
			account02.withdraw(50000);
			System.out.println(" 계좌정보 " + account02);
			
			System.out.println("\n3." + account02.getAccountNo() + "계좌 60000 출금요청");
			account02.withdraw(60000);
			System.out.println(" 계좌정보 " + account02);
			
		}catch(Exception e){
			System.out.println(">>>> 예외발생 : " + account01.getAccountNo() + " : " + e.getMessage());
		}
		
	}//out of main
}// out of class
