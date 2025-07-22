import java.sql.*;

public class Example03{
    
	///Main Method    
	public static void main(String[] args) throws Exception {
		
	    // 입력 Data Validation  check
		if( args.length !=1 ){
			System.out.println("실행방법 java Example1 [id값]");
			System.exit(0);
		}

		String id = args[0];
  
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";

		//1단계 : Connection 
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,"scott","tiger");
		
		//2단계 : Statement 
		Statement stmt = con.createStatement();
		
		//3단계 : Query문 전송 및 결과
		String deleteSql = "DELETE FROM member WHERE id ='"+id+"'";
		
		if( stmt.executeUpdate(deleteSql) == 1 ){
			System.out.println( "number TABLE RECORD DELETE 완료" );
		}else{
			System.out.println( "number TABLE RECORD DELETE  실패" );
		}

	    if (stmt != null) 		stmt.close();
	    if (con != null)			con.close();
	    
	}//end of main
	
}//end of class
