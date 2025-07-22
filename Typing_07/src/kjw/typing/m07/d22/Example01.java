import java.sql.*;

public class Example01{

	public static void main(String[] args) throws Exception {
		
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";

		//1단계 : Connection 
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,"scott","tiger");
		
		//2단계 : Statement 
		Statement stmt = con.createStatement();
		
		//3단계 : Query문 전송 및 결과 return
		String createSql = "CREATE TABLE MEMBER(no NUMBER, id VARCHAR2(10), pwd VARCHAR2(10))";
		
		stmt.executeUpdate(createSql);
		
		System.out.println("number TABLE 생성완료");

	    if (stmt != null) 		stmt.close();
	    if (con != null)			con.close();
	    
	}//end of main
	
}//end of class
