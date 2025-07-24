package kjw.typing.m07.d09.before;

public class FindOccException extends Exception { // Exception 클래스를 상속받아 사용자 정의 예외 클래스를 정의합니다.
                                                 // Exception을 상속받으므로 이 예외는 Checked Exception이 됩니다.

    private String msg; // 예외 메시지를 저장할 private 멤버 변수입니다.

    // --- 생성자 (Constructors) ---

    // Default Constructor (기본 생성자)
    public FindOccException() {
        // super()는 부모 클래스(Exception)의 생성자를 호출합니다.
        // 이 메시지는 예외 객체가 생성될 때 부모 클래스의 message 필드에 저장됩니다.
        super(":: FindOddException Default Constructor ::");
        
        // 사용자 정의 예외 메시지 필드에도 기본 메시지를 할당합니다.
        this.msg = "Default message"; 
    }

    // Constructor with message (메시지를 인자로 받는 생성자)
    public FindOccException(String msg) {
        // super(msg)는 부모 클래스(Exception)의 생성자를 호출하여,
        // 인자로 받은 'msg'를 부모 클래스의 message 필드에 설정합니다.
        // 이 메시지는 나중에 e.getMessage()로 접근할 수 있습니다.
        super(msg); 
        
        // 사용자 정의 예외 메시지 필드에도 인자로 받은 'msg'를 할당합니다.
        this.msg = msg; 
        
        // 예외 객체가 생성될 때 콘솔에 메시지를 출력합니다.
        // 이는 디버깅 목적으로 예외 객체 생성 시점을 확인하는 데 유용합니다.
        System.out.println(":: FindOddException Constructor :: " + msg); 
    }

    // --- 메소드 (Methods) ---

    // test 메소드: 특정 조건에 따라 FindOddException을 발생시키는 메소드
    public void test(int number) throws FindOccException { // throws 키워드는 이 메소드가 FindOddException을 발생시킬 수 있음을 선언합니다.
                                                         // FindOddException이 Checked Exception이므로 반드시 throws를 명시해야 합니다.

        // 현재 실행 중인 메소드와 클래스 이름, 인자 값을 출력하여 실행 흐름을 추적하는 데 도움을 줍니다.
        System.out.println("test() " + this.getClass().getName() + " start " + number);

        // 예외 발생 조건: 만약 'number'가 0과 같으면,
        if (number == 0) {
            // 새로운 FindOddException 객체를 생성하고 throw 키워드를 사용하여 예외를 발생시킵니다.
            // 이 시점에서 메소드 실행은 즉시 중단되고, 호출 스택의 상위 메소드로 예외가 던져집니다.
            throw new FindOccException("number is '0' 발생!!!!"); 
        }
        
        // 'number'가 0이 아닌 경우에만 이 줄이 실행됩니다.
        System.out.println("test() " + this.getClass().getName() + " end ");
    }

    // --- main 메소드 (프로그램의 시작점) ---
    public static void main(String[] args) {
        // FindOddException 클래스의 인스턴스를 생성합니다.
        // 이 때 기본 생성자가 호출되어 ":: FindOddException Default Constructor ::" 메시지가 부모에 저장됩니다.
        FindOccException testMe = new FindOccException(); 

        // try-catch 블록: 예외 발생 가능성이 있는 코드를 감싸서 예외를 처리합니다.
        try {
            // testMe 객체의 test 메소드를 호출합니다.
            testMe.test(10); // 'number'가 0이 아니므로 예외가 발생하지 않습니다.
                             // "test() FindOddException start 10"과 "test() FindOddException end"가 출력됩니다.
            
            testMe.test(0);  // 'number'가 0이므로 여기서 FindOddException이 발생합니다.
                             // "test() FindOddException start 0"이 출력된 후,
                             // ":: FindOddException Constructor :: number is '0' 발생!!!!"가 출력되고,
                             // throw new FindOddException(...)에 의해 예외가 발생하여,
                             // 이 이후의 try 블록 코드는 실행되지 않고 즉시 catch 블록으로 제어가 넘어갑니다.
        } catch (FindOccException e) { // FindOddException 타입의 예외가 발생하면 이 catch 블록이 실행됩니다.
            // 예외가 발생했음을 알리는 메시지와 예외 객체의 클래스 이름을 출력합니다.
            System.out.println("main Method에서 " + e.getClass().getName() + "이 발생됨.");
            
            // 발생한 예외의 메시지를 출력합니다. 
            // 이는 예외 객체 생성 시 super(msg)로 전달된 메시지("number is '0' 발생!!!!")입니다.
            System.out.println("예외 메시지 : " + e.getMessage()); 
        }

        // try-catch 블록이 종료된 후 실행되는 코드입니다.
        // 예외가 발생했든 안 했든 (catch 블록이 처리했으므로), 이 줄은 항상 실행됩니다.
        System.out.println("main Method End ..."); 
    }
}