package kjw.m07.d14;
public class Prob3 {
	public static void main(String[] args) {
		CellPhone cellPhone = new CellPhone("010-010-010", 1000);
		cellPhone.call("017-017-017", 10);
		cellPhone.call("016-016-016", 10);
		cellPhone.calculateTotalFee();
		System.out.println(cellPhone.getPhoneNumber() + " 통화 시간: " + cellPhone.getTotalCallTime()
				+ ", 단가: " + cellPhone.getFeePerCallTime() + ", 총 요금: " + cellPhone.getTotalFee() + "원");

		System.out.println("========================================");

		SmartPhone smartPhone = new SmartPhone("000-000-000", 200);
		smartPhone.call("017-017-017", 10);
		smartPhone.calculateTotalFee();
		System.out.println(smartPhone.getPhoneNumber() + " 통화 시간: " + smartPhone.getTotalCallTime()
				+ ", 단가: " + smartPhone.getFeePerCallTime() + ", 총 요금: " + smartPhone.getTotalFee() + "원");

		System.out.println("========================================");

		SmartPhone smartPhone01 = new SmartPhone("111-111-111", 0, true, 3000);
		smartPhone01.call("017-017-017", 10);
		smartPhone01.call("016-016-016", 10);
		smartPhone01.calculateTotalFee();
		System.out.println(smartPhone01.getPhoneNumber() + " 통화 시간: " + smartPhone01.getTotalCallTime()
				+ ", 단가: " + smartPhone01.getFeePerCallTime() + ", 총 요금: " + smartPhone01.getTotalFee() + "원");
	} // end of main
} // end of class