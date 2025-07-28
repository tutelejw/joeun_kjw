public class CompanyApp0728 {
	public static void main(String[] args) {
		Employee0728 ep = new Employee0728("홍길동사원", 400);
		Manager0728 ma = new Manager0728("김유신매니저",400,400);
		
		ep.getPay();
		ma.getPay();
		ep.printInfo();
		ma.printInfo();
		
	}// out of main
}// out of class CompanyApp0728 
