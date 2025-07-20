package kjw.typing.m06.d30.before;
public class Developer1Test{

	public static void main(String[] args){
	
	Developer1 developer = new Developer1();

	System.out.println("이름은" + developer.name);
	System.out.println("직업은" + developer.job);
	System.out.println("평균수입은" + developer.avgIncome);
	System.out.println("PJT 경력은:" + developer.projectCareer);

	System.out.println("=============================");


	// 프로젝트 참여
//	String projectNamea="한미은행";  --개인테스트
//	developer.participateProject(projectNamea);  --개인테스트
	developer.participateProject();
	System.out.println("평균수입은 : " + developer.avgIncome);
	System.out.println("경력은 : " + developer.projectCareer);

	System.out.println("=============================");

	//1개 과목을 강의
	developer.instruct();
	System.out.println("평균수입은 : " + developer.avgIncome);

	System.out.println("=============================");

	//홍길동이 한미은행 프로젝트에 참여한다.
	//String projectName="한미은행";
	//developer.participateProject(projectName);
	developer.participateProject("한미은행");   // 한줄처리, 저장이 안됨,가독성 등 ground rull 따라서
	System.out.println("평균수입은 : " + developer.avgIncome);
	System.out.println("경력은 : " + developer.projectCareer);
	
	System.out.println("=============================");

	int lectureCount = 2;
	developer.instruct(lectureCount);
	//developer.instruct(2);
	System.out.println("평균수입은 : " + developer.avgIncome);
	}

}