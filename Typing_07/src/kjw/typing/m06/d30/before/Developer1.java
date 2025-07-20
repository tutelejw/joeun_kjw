package kjw.typing.m06.d30.before;
public class Developer1
{
	String name = "홍길동";
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

	public void instruct(){
		System.out.println("강의를 통한 수입증가");
		avgIncome++;
	}

	public void instruct(int lectureCount){
		System.out.println(lectureCount+": 개 강의를 통한 수입증가");
		avgIncome +=lectureCount;
	}
}


//instruct 가 2군데 있는데 정상인건가?? 소스설명해줘
/*Java는 메서드 오버로딩을 지원합니다.
오버로딩이란 같은 이름의 메서드를 파라미터만 다르게 하여 여러 개 정의하는 것인데,
컴파일러는 호출 시 전달된 인자의 개수와 타입을 보고 어떤 메서드를 호출할지 결정합니다.

선언된 메서드	인자	호출 예시
public void instruct()				없음			dev.instruct();
public void instruct(int count)		int 1개		dev.instruct(3);
*/

/*	public void instruct(){
		int lectureCount;
		System.out.println("강의를 통한 수입증가");
		avgIncome++;
	}
	메서드 호출할때 이렇게 해도 instruct(3) 호출이 되나??
*/