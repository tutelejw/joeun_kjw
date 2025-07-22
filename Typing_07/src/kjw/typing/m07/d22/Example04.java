import java.sql.*;

public class Example04{

    ///Main Method    
	public static void main(String[] args) throws Exception {
		
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";

		//1단계 : Connection 
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,"scott","tiger");
		
		//2단계 : Statement 
		Statement stmt = con.createStatement();
		
		//3단계 : Query문 전송 및 결과 
		ResultSet rs = stmt.executeQuery("SELECT * FROM member");

		while (rs.next()){
			int no = rs.getInt("no");
			String id = rs.getString("id");
			String pwd = rs.getString(3);
			System.out.println("회원정보 ==> no:"+no+",id:"+id+",pwd:"+pwd+"");
		}
		
		if(rs != null)				rs.close();
	    if (stmt != null) 		stmt.close();
	    if (con != null)			con.close();
	    
	}//end of main
	
}//end of class
