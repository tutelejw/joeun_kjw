package kjw.hw.m07.d14;

public class CellPhone {
	private String phoneNumber;  //전화번호000-000-0000
	private int totalCallTime; //전화사용시간 10 100 200
	private int totalFee; //사용 요금
	private int feePerCallTime;  //시간당 사용요금.
	
	public CellPhone() {
	}
	public CellPhone(String phoneNumber, int feePerCallTime) { //전화번호, 전화사용시간.
		this.phoneNumber=phoneNumber;
		this.feePerCallTime=feePerCallTime;	
	}
	
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public int getTotalCallTime() {
		return totalCallTime;
	}
	public int getTotalFee() {
		return totalFee;
	}
	public int getFeePerCallTime() {
		return feePerCallTime;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber=phoneNumber;
	}
	public void setTitalCallTime(int totalCallTime) {
		this.totalCallTime=totalCallTime;
	}
	public void setTotalFee (int totalFee) {
		this.totalFee = totalFee;
	}
	public void setFeePerCallTime(int feePerCallTime) {
		this.feePerCallTime=feePerCallTime;
	}
	
	public void call(String phoneNumber, int totalCallTime) {
		this.totalCallTime += totalCallTime;
		System.out.println(phoneNumber + " 번호로 " +totalCallTime + " 통화함.");
	}
	public int calculateTotalFee() {
		this.totalFee = this.totalCallTime * this.feePerCallTime;
		return this.totalFee;
	}
}
