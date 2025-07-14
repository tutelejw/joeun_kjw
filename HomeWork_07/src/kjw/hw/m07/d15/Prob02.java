package kjw.hw.m07.d15;

//String 연습
public class Prob02 {
	public static void main(String[] args) {
		String url1 = "http://localhost/order?prodId=PROD-001&prodName=갤럭시3&price=980000";
		
		String prodName = getParameter(url1, "prodName");  //getParameter 파라미터 파싱해서 가져오기??
		System.out.println("제품 이름 : " + prodName);
		
		String url2 = "http://localhost/registUser?userId=USER-001&userName=홍길동&address=서울시 강남구&userAge=26";
		String userAddress = getParameter(url2, "address");  //getParameter 파라미터 파싱해서 가져오기??
		System.out.println("회원 주소 : " + userAddress);		
	}// out of main
	
	public static String getParameter(String url, String paramName) {
		System.out.println("url : " + url + " paramName : " +  paramName);
		return null;
	}
}// out of class


//매개변수로 받은 url에서 두번째 매개변수에 해당 하는 파라미터 값을 리턴 getParameter() 메서드 구현
