import java.sql.*;

public class Example02{

    ///Main Method    
	public static void main(String[] args) throws Exception {
		
	    // 입력 Data Validation  check
	    if( args.length !=3 ){
			System.out.println("실행방법 java Example02 [no값] [id값] [pwd값]");
			System.exit(0);
		}
	    
		int no = Integer.parseInt(args[0]);
		String id = args[1];
		String pwd = args[2];
  
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";

		//1단계 : Connection 
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,"scott","tiger");
		
		//2단계 : Statement 
		Statement stmt = con.createStatement();
		
		//3단계 : Query문 전송 및 결과 
		String insertSql = "insert into member values("+no+",'"+id+"','"+pwd+"')";
		
		
		if( stmt.executeUpdate(insertSql)== 1 ){
			System.out.println( "number TABLE DATA INSERT 완료" );
		}else{
			System.out.println( "number TABLE DATA INSERT 실패" );
		}

	    if (stmt != null) 		stmt.close();
	    if (con != null)			con.close();
	    
	}//end of main
	
}//end of class
