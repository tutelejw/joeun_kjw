package kjw.typing.m07.d22;
import java.sql.*;

public class InsertJDBC{
   
	///Main Method
	public static void main(String[] args) throws Exception{
		
		//DB에 로그인 접속정보
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		
		Class.forName(driver);
		System.out.println("1. oracle jdbc driver 로딩 완료");
		
		Connection con = DriverManager.getConnection(url,"scott","tiger");
		System.out.println("2.connection 인스턴스 생성완료");
		
		
		Statement stmt = con.createStatement();
		System.out.println("3.statment 객체 생성완료");

		//2단계 QUERY (SELECT * FROM product) 전송단계
		String sql1="Insert into firstJDBC values(1,'홍길동','a@lycos.co.kr','한양')";
		String sql2="Insert into firstJDBC values(2,'홍길순','b@lycos.co.kr','한양')";
		String sql3="Insert into firstJDBC values(3,'이순신','c@lycos.co.kr','한양')";
		
//		int i=stmt.executeUpdate(sql1);
//		System.out.println("1번 insrt 유무 : " + i + "개의 행이 만들어짐");
		int i;
		System.out.println( "1번 insrt 유무 : " + stmt.executeUpdate(sql1) + "개의 행이 만들어짐");
		
		i=stmt.executeUpdate(sql2);
		System.out.println("2번 insrt 유무 : " + i + "개의 행이 만들어짐");
		
		i=stmt.executeUpdate(sql3);
		System.out.println("3번 insrt 유무 : " + i + "개의 행이 만들어짐");
		
		
		//각각의 객체를 close한다.		
		if(stmt != null) stmt.close();
		if(con != null) con.close();
	
		
	}//end of main

}//end of class
