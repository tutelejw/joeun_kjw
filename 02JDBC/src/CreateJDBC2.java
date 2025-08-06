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


//  CreateJDBC.java 에서 Exception 처리 방식을 줄일 수 있게 적용 함.
//  

public class CreateJDBC2{
   
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
		
		Connection con=null;
		Statement stmt=null;
		
		String createSql=
				
				"Create TABLE firstJDBC" +
				"(no	number(3), " +
				"name	varchar2(10), " +
				"email	varchar2(14), " +
				"address	varchar2(10))";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. oracle jdbc driver 로딩 완료");
			
			con = DriverManager.getConnection(url,user,pwd);
			System.out.println("2.connection 인스턴스 생성완료");
			
			//2단계 QUERY (SELECT * FROM product) 전송단계
			stmt = con.createStatement();
			System.out.println("3.statment 객체 생성완료");

			//3단계 결과 확인		
			System.out.println("Query 전송 결과 : "+stmt.executeUpdate(createSql));
			System.out.println("4.query 전송완료");

		} catch (ClassNotFoundException e) {
			System.out.println("\n JDBC 절차중 exception 발생" + e.getLocalizedMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("\n JDBC 절차중 exception 발생" + e.getErrorCode());
			e.printStackTrace();
		}finally {
			//각각의 객체를 close한다.		
			try {
				if(stmt != null) stmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("\n ==> JDBC 절차중 Exception 발생 : " + e.getErrorCode());
				e.printStackTrace();
			}
		}
		
	}//end of main

}//end of class
