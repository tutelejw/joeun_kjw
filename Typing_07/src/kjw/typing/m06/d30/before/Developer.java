package kjw.typing.m06.d30.before;

public class Developer {

	String name = "홍길동";
	String job = "개발자";
	int avgIncome = 100;
	int projectCareer;
	
	public void participateProject() {
		System.out.println("프로그램개발로 수입증가, 경력쌓음");
		avgIncome++;
		projectCareer++;
	}
	
	public void instruct() {
		System.out.println("강의를 통한 수입증가");
		avgIncome++;
	}
	
}
