package SelfStudy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
//import 할게 많구나 sql.* 로 써야하나???


public class OracleDbConTest {
	public static void main(String[] args) throws Exception {
		String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String id="scott";
		String password="tiger";
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		Class.forName(driver);
		
		Connection con = DriverManager.getConnection(url,id,password);
		Statement stmt = con.createStatement();

		String sql1="select * from tab";
		
		stmt.execute(sql1);
		System.out.println(sql1);  //select 일때는  출력을 찍어줘야함.
		

	    if (stmt != null) 		stmt.close();
	    if (con != null)			con.close();
	    
	}//end of main
}// end of class
