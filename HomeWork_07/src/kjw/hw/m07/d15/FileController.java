package kjw.hw.m07.d15;

public class FileController {
	public void reverseLine(String oldf, String newf) throws Exception{
		//reverseLine 메서드 구현.
		System.out.println("reverseLine 메서드 구현.");
	} // out of method
	
	public static void main(String[] args) throws Exception{
		FileController c = new FileController();
		String oldf = "files/oldf.txt";
		String newf = "files/newf.txt";
		c.reverseLine(oldf,newf);
	}//out of main
} //out of classs

//reverseLine 메서드 구현
//String 타입의 텍스트 파일명을 입력받아 텍스트의 라인순서를 반대로하여 두번째 인자인 출력할 String 타입의 텍스트 파일명으로 저장
 