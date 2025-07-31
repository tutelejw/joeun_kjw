
/*
 * 1. JDBC Programming 절차 이해
 * 2. 각각의 Exception 이해 
 * 3. executeQuery(SQL),executeUpdate(SQL) Method 이해 
 */

import java.sql.*;

public class CreateJDBC{
    
	///Main method
	public static void main(String[] args) {

		//JDBC 절차에 필용한 객체 및 필요 정보 선언  
		Connection con = null;
		Statement stmt = null;
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";

		//==>Connetion 객체생성
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. driver loaing OK");
		}catch(ClassNotFoundException e){
			System.out.println("\n==>Driver Loading시 Exception 발생\n ");
			e.printStackTrace();
		}

		try{			
			con = DriverManager.getConnection(url,"scott","tiger");
			System.out.println("2. connection 인스턴스 생성 완료");
		}catch(SQLException e){
			System.out.println("\n==>JDBC 절차 중 Exception 발생 : "+e.getErrorCode());
			e.printStackTrace();
		}

		//==>query늘 전송하기 위한 Statement객체 생성
		try{
			stmt = con.createStatement();
			System.out.println("3. statement객체 생성완료");
		}catch(SQLException e){
			System.out.println("\n==>JDBC 절차 중 Exception 발생 : "+e.getErrorCode());
			e.printStackTrace();
		}

		//Create Query 만들기 
		String createSql = 
			"create table firstJDBC"+
				"(no number(3),"+
				" name varchar(20),"+
				" email varchar2(30),"+
				" address varchar2(50) )";
		
		//==>Query전송 
		try{
			 System.out.println( stmt.executeUpdate(createSql) );
			System.out.println("4. query전송완료");
		}catch(SQLException e){
			System.out.println("\n==>JDBC 절차 중 Exception 발생 : "+e.getErrorCode());
			e.printStackTrace();
		}

		try{
		    if (stmt != null) 			stmt.close();
		    if (con != null)			con.close();
		}catch(SQLException e){
			System.out.println("\n==>JDBC 절차 중 Exception 발생 : "+e.getErrorCode());
			e.printStackTrace();
		}

	}//end of main

}//end of class
