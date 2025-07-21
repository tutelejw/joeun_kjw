package SelfStudy;

class BankAccount{
	private String ownerName; //사용자명
	private int balance; //잔액
	//private int withdraw; //출금
	
	//Constructor default
	public BankAccount() {
	}
	public BankAccount(String ownerName, int balance) {
	this.ownerName=ownerName;
	this.balance=balance;
	}
	public String setOwnerName (String ownerName) {
		this.ownerName = ownerName;
		return ownerName;
	}
	public void deposit(int amount){
		balance += amount;
		System.out.println(amount + " 원을 입급합니다. 잔액은 : " + balance + " 입니다.");
	}
	public void deposit(int amount, String message) {	//overloading
		balance += amount;
		System.out.println(amount + " 원을 입급합니다. 잔액은 : " + balance + " 입니다. " + message);
	}
	public int withdraw(int amount) {
		balance -= amount;
		return balance;
	}
	public void printBalance() {
	System.out.println("사용자 : "+ ownerName + ", 현재 잔액 : " + balance );
	}	
} // out of bean class

public class BankApp {
	public static void main(String[] args) {
		BankAccount ba = new BankAccount();
		ba.setOwnerName("김자바");
		ba.deposit(50000);
		ba.deposit(10000, "보너스입금");
		ba.withdraw(20000);
		ba.printBalance();
		
	}// out of main
}//out of main class
