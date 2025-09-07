package kjw.typing.m06.d30.before;
public class DeveloperTest {

	public static void main(String[] args) {
		Developer developer = new Developer();
		System.out.println("이름 : " + developer.name);
		System.out.println("직업 : " + developer.job);
		System.out.println("평균수입은 : " + developer.avgIncome);
		System.out.println("PJT경력은 : " + developer.projectCareer);
		
		System.out.println("===================================");
		
		developer.participateProject();
		System.out.println("평균수입은 : " + developer.avgIncome);
		System.out.println("project 참여 경력은 : " + developer.projectCareer);
		
		System.out.println("===================================");
		
		developer.instruct();
		System.out.println("평균수입은 : " + developer.avgIncome);
		developer.instruct();
		developer.instruct();
		developer.instruct();
		developer.instruct();
		System.out.println("평균수입은 : " + developer.avgIncome);
	}
}



/*
new는 클래스의 인스턴스(객체) 를 생성하고, 그 주소를 참조 변수에 저장합니다.

🔸 위 코드에서 무슨 일이 일어날까?
Developer developer = new Developer();
new Developer()
→ Developer 클래스의 생성자가 호출되어
→ Heap 메모리에 새로운 Developer 객체가 생성됨

Developer developer = ...
→ 생성된 객체의 참조(주소) 가 developer라는 참조 변수에 저장됨

Java는 클래스가 설계도(blueprint) 역할을 하기 때문에, 그걸 실제 메모리에서 사용할 수 있는 실체(객체) 로 만들려면 new가 필요합니

Developer d;						객체의 참조 변수만 선언 (null 상태)
d = new Developer();				실제로 객체를 생성하고 참조 변수에 할당
Developer d = new Developer();		위 둘을 한 줄로 결합

*/


/*
1. 아래와 같이 object modeling 함.

1.1 상태
name=홍길동
job=개발자
avgIncome = 100
projectCareer

1.2 행위
participateProject 
avgIncome  projectCareer 1증가

instruct
avgIncome 1 증가

2.11 1.2 상태와 행위를 갖는 developer bean 구현

3.utf8로 컴파일 

4. DeveloperTest application 구현

4.1 Developer instance 구현
4.2 행위 호출
*/