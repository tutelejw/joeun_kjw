class A {
    public void def(String message) {
        System.out.println("def(String message) " + this.getClass().getName() + " start " + message);
        System.out.println("def(String message) " + this.getClass().getName() + " end ");
    }
}

class B extends A {
    public void def(String message) {
        System.out.println(":::: B class def(String message) start ::::");
        System.out.println(":::: B class def(String message) end ::::");
    }
}

public class AnonymousInnerClass {
    public static void main(String[] args) {

        // 1. 일반적인 상속과 오버라이딩
		System.out.println("1.=====================");
        A a = new B(); // A 타입으로 B 객체 생성
        System.out.println("a.getClass() " + a.getClass().getName());
        a.def("Hello");
        System.out.println("y end of Class");

        System.out.println("\n\n");

        A a2 = new A() {
            @Override
            public void def(String message) {
                System.out.println(">>> 익명 이너 클래스 오버라이딩된 def() 메소드 시작");
                System.out.println(">>> 메시지 : " + message);
                System.out.println(">>> 클래스 이름 : " + this.getClass().getName()); // 이 이름은 컴파일러가 부여
                System.out.println(">>> 익명 이너 클래스 오버라이딩된 def() 메소드 끝");
            }
        };

        System.out.println("a2.getClass() " + a2.getClass().getName()); // 컴파일러가 부여한 이름 출력 (예: jp02.part05.AnonymousInnerClass$1)
        a2.def("Hello");
        System.out.println("y end of Class");

        System.out.println("\n\n");

    } //end of main
} // end of class