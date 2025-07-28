package kjw.typing.m07.d28;

public class JavaVersionCheck {
    public static void main(String[] args) {
        // Java 버전 정보 출력
        String javaVersion = System.getProperty("java.version");
        String javaVendor = System.getProperty("java.vendor");
        String javaHome = System.getProperty("java.home");

        System.out.println("Java Version : " + javaVersion);
        System.out.println("Java Vendor  : " + javaVendor);
        System.out.println("Java Home    : " + javaHome);
    }
}
