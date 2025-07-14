package kjw.hw.m07.d14;

public class SmartPhone  extends CellPhone{
	private boolean isMonthlyFixedRate;  //월정액 요금제 유무 
	private int monthlyFixedFee; //월정액 요금
	
	public SmartPhone() {
	}
	
	public SmartPhone(String phoneNumber, int feePerCallTime) {
	super(phoneNumber, feePerCallTime);
	}
	
	public SmartPhone(String phoneNumber, int feePerCallTime, boolean isMonthlyFixedRate, int monthlyFixedFee) {
		super(phoneNumber, feePerCallTime);
		this.isMonthlyFixedRate = isMonthlyFixedRate;
		this.monthlyFixedFee = monthlyFixedFee;
		System.out.println( "내용확인 " +phoneNumber + " " +feePerCallTime + " " +isMonthlyFixedRate);
		}

	public void calculateTotalFee() {
		
		if (isMonthlyFixedRate) {
			System.out.println("고객님은 "+ monthlyFixedFee + " 정액 요금제 입니다.");
			setTotalFee(monthlyFixedFee);
		}else {
			//System.out.println("고객님은 "+ monthlyFixedFee + " 정액 요금제 아닙니다.");
			super.calculateTotalFee();
		}
		
	}
	
}
