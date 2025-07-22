package kjw.typing.m07.d22;
import java.sql.*;
//import java.util.*;

//Oracle Driver 사용
//import oracle.jdbc.driver.*;

/*
*	FileName : ConnectionTest01.java
*
*	1. JDBC Programming  절차 이해
 *   ==>DBMS 접근(login과정)			: Connection 객체로 Object Modeling
 *   ==>SQL Prompt  & SQL전송		: Statement  객체로 Object Modeling
 *   ==>SQL문의 결과						: ResultSet 객체로 Object Modeling
*/
public class CreateJDBC{
   
	///Main Method
	//public static void main(String[] args) throws Exception{
	public static void main(String[] args){

		//DB에 로그인 접속정보
		String user ="scott";
		String pwd = "tiger";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		

		/* ====== DBMS 종속정인 아래의 부분을 주석 이유 이해 ====== 
		//1.단계  connection :: login 과정
		//1.1 OracleDriver  instance 생성
		OracleDriver od = new OracleDriver();

		//1.2 oracle에 접속 정보를 갖는  properties instance 생성 
		Properties info = new Properties();
		info.put("user",user);
		info.put("password",pwd);

		//1.3OracleDriver intance를 사용  Connection instance 생성
		Connection con = od.connect(url,info);
		===================================================
		*/
		
		// Interface 기반 Programmin : java.sql.* 이용 DBMS 비종속적인 DB
		// 1.단계 connection : login 과정
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. oracle jdbc driver 로딩 완료");
		} catch (ClassNotFoundException e) {
			System.out.println("\n JDBC 절차중 exception 발생" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		Connection con=null;
		try {
			con = DriverManager.getConnection(url,user,pwd);
			System.out.println("2.connection 인스턴스 생성완료");
		} catch (SQLException e) {
			System.out.println("\n JDBC 절차중 exception 발생" + e.getErrorCode());
			e.printStackTrace();
		}

		//2단계 QUERY (SELECT * FROM product) 전송단계 
		Statement stmt=null;
		try {
			stmt = con.createStatement();
			System.out.println("3.statment 객체 생성완료");
		} catch (SQLException e) {
			System.out.println("\n JDBC 절차중 exception 발생" + e.getErrorCode());
			e.printStackTrace();
		}
		
		String createSql=
				"Create TABLE firstJDBC" +
				"(no	number(3), " +
				"name	varchar2(20), " +
				"email	varchar2(30), " +
				"address	varchar2(50))";

		//3단계 결과 확인
		
		try {
			System.out.println("Query 전송 결과 : "+stmt.executeUpdate(createSql));
			System.out.println("4.query 전송완료");
		} catch (SQLException e) {
			System.out.println("\n ==> JDBC 절차중 Exception 발생 : " + e.getErrorCode());
			e.printStackTrace();
		}
		

		//각각의 객체를 close한다.		
			try {
				if(stmt != null) stmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("\n ==> JDBC 절차중 Exception 발생 : " + e.getErrorCode());
				e.printStackTrace();
			}
		

	}//end of main

}//end of class
