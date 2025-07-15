package kjw.hw.m07.d15;

//String 연습
public class Prob02 {
	public static String getParameter(String url, String paramName) {
		System.out.println("url : " + url + " paramName : " +  paramName);
		int index = url.indexOf("?"); //indexOf  - String 클래스 포함 메서드 구분자 위치 숫자 반환 0부터 카운트.
		String tmp1 = url.substring(index + 1, url.length()); //url의 ? 다음 글자수 부터 끝까지 tmp1에 담고.
		String[] tmp2 = tmp1.split("&"); //& csv로 나누어서 배열로 저장 tmp2
		
		String result=null;
		for (int i=0; i<tmp2.length;i++) { //tmp2 의 갯수만큼 for 문
			if (tmp2[i].startsWith(paramName)){				//tmp2를 for 문으로 돌려서서 String 클래스의 startsWith 메소드로 문자열 paramName = prodName 의 값을 반환
			String[] tmp3=tmp2[i].split("=");
			result=tmp3[1];
			break;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		String url1 = "http://localhost/order?prodId=PROD-001&prodName=갤럭시3&price=980000";
		
		String prodName = getParameter(url1, "prodName");  //getParameter 파라미터 파싱해서 가져오기??
		System.out.println("제품 이름 : " + prodName);
		
		String url2 = "http://localhost/registUser?userId=USER-001&userName=홍길동&address=서울시 강남구&userAge=26";
		String userAddress = getParameter(url2, "address");  //getParameter 파라미터 파싱해서 가져오기??
		System.out.println("회원 주소 : " + userAddress);		
	}// out of main
	
	
}// out of class


//매개변수로 받은 url에서 두번째 매개변수에 해당 하는 파라미터 값을 리턴 getParameter() 메서드 구현
//main 메서드 수정안함.
//getParameter 메서드 시그니처 수정안함.
//제품이름 : 갤럭시3
//회원주소 : 서울시 강남구