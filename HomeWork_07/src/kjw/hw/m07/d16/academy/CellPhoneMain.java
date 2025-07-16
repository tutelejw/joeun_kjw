package kjw.hw.m07.d16.academy;

public class CellPhoneMain {
	public static void main(String[] args) {
		
			CellPhone myPhone = new CellPhone("SCH-600");
			
			myPhone.charge(20); // 20분 충전함
			myPhone.printBattery();  //배터리 사용량 계산 출력? void printBattery
			
			myPhone.call(300); // 20분 충전함
			myPhone.printBattery();  //배터리 사용량 계산 출력? void printBattery

			myPhone.charge(50); // 20분 충전함
			myPhone.printBattery();  //배터리 사용량 계산 출력? void printBattery

			myPhone.call(40); // 20분 충전함
			myPhone.printBattery();  //배터리 사용량 계산 출력? void printBattery
			
			try {
				myPhone.call(-20); //통화 시간이 잘못 입력 되었다ㅏ.
			}catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
			
			CellPhone yourPhone = new CellPhone("SCH-600");
			
			if(myPhone.equals(yourPhone)) {
				System.out.println("동일 모델 입니다.");
			}else {
				System.out.println("다른 모델 입니다.");
			}
	}// end of main
}//end of class
