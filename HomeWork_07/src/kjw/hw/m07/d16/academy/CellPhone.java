package kjw.hw.m07.d16.academy;

public class CellPhone {
	private String model;
	private double battery;

	public CellPhone() {
	}

	public CellPhone(String model) {
		this.model = model;
		this.battery = 0.0;
	}

	public void charge(int minutes) {
		if (minutes < 0) {
			throw new IllegalArgumentException("충전 시간 입력 오류");
		}
		battery += minutes * 3;
		if (battery > 100) {
			battery = 100;
		}
		System.out.println("충전시간 :" + minutes + "분");
	}


	public void call(int minutes) {
		if (minutes < 0) {
			throw new IllegalArgumentException("통화 시간 입력 오류");
		}
		System.out.println("통화 시간 : " + minutes + " 분");
		battery -= minutes * 0.5;
		if (battery < 0) {
			battery = 0.0;
		}
	}

	public void printBattery() {
		System.out.printf("남은 배터리 양 : %.1f\n", battery);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof CellPhone))
			return false;
		CellPhone other = (CellPhone) obj;
		return this.model.equals(other.model);
	}
}
