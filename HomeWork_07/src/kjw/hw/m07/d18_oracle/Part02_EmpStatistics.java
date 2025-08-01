package kjw.hw.m07.d18_oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Part02_EmpStatistics{
 
    String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
    String user = "hr";
    String pass = "hr";

    static {
	        try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }
 

	public void printStatistics(int maxSalary) throws SQLException {
		System.out.println("=================================");
		System.out.println("Max Salary: " + maxSalary);
		System.out.println("=================================");
		
		// 1단계: DB 연결(Connection 생성)
		Connection con = DriverManager.getConnection(url,user,pass);

		String sql = "SELECT j.job_title, ROUND(AVG(NVL(e.salary, 0)), 0) 평균급여 FROM employees e, jobs j "
				+ "WHERE e.job_id = j.job_id AND e.salary >= ? "
				+ "GROUP BY j.job_title "
				+ "ORDER BY ROUND(AVG(NVL(e.salary, 0)), 0) DESC";
		
		// 2단계: PreparedStatement 생성 (SQL 준비)
		PreparedStatement psmt = con.prepareStatement(sql);
		
		// 3단계: SQL 실행 및 결과(ResultSet) 반환
		psmt.setInt(1, maxSalary);
		ResultSet rs = psmt.executeQuery();

		while (rs.next()) {
			System.out.println("[" + rs.getString("job_title") + "] " + rs.getInt("평균급여"));
		}

		if (rs != null) rs.close();
		if (psmt != null) psmt.close();
		if (con != null) con.close();
	}

	// Main
    public static void main(String[] args) throws SQLException {
        
        int maxSalary = 10000;
        new Part02_EmpStatistics().printStatistics(maxSalary);

        maxSalary = 15000;
        new Part02_EmpStatistics().printStatistics(maxSalary);
        
	}//end of main
}//end of class
