package kjw.typing.m06.d30.before;
public class Developer2{

	String name = "홍길동";
	//String name = "홍길동";
	String job = "개발자";
	int avgIncome = 100; //평균 수입.
	int projectCareer;  //PJT 경록

	public void participateProject(){
		System.out.println("프로그램개발로 수입증가, 경력 쌓음");
		avgIncome++;
		projectCareer++;
	}

	public void participateProject(String project){
		System.out.println(project + "==> project 참여로 수입증가, 경력쌓음");
		avgIncome++;
		projectCareer++;
	}

	public void instruct(){ //과목 강의
		System.out.println("강의를 통한 수입증가");
		avgIncome++;
	}

	public void instruct(int lectureCount){
		System.out.println(lectureCount+": 개 강의를 통한 수입증가");
		avgIncome +=lectureCount;
	}

//상태값을 return 하는 행위 getter method 정의 - 약속
	public String getName(){
	System.out.println("getName() mothod");
	return name;
	}
	public String getJob(){
	System.out.println("getJob() mothod");
	return job;
	}
	public int getAvgIncome(){
	System.out.println("getAvgIncome() mothod");
	return avgIncome;
	}

	public int getProjectCareer(){
	System.out.println("getProjectCareer() mothod");
	return projectCareer;
	}

	public static void main(String[] args){
	
	Developer2 developer = new Developer2();
	
//	System.out.println("이름은" + developer.name);
	System.out.println("직업은" + developer.job);
	System.out.println("평균수입은" + developer.avgIncome);
	System.out.println("PJT 경력은:" + developer.projectCareer);

	System.out.println("=============================");

//	String name = developer.getName();
	String job = developer.getJob();
	int avgIncome = developer.getAvgIncome();

//	System.out.println("이름은 : " + name);
	System.out.println("이름은 developer.getName() : " + developer.getName());
	System.out.println("직업은 : " + job);
	System.out.println("평균수입은 : " + avgIncome);

	System.out.println("평균수입은 : " + developer.getProjectCareer());

	
	}


}