package kjw.hw.m07.d18_oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpStatistics{
 
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
		
		//1단계 : Connection
		Connection con = DriverManager.getConnection(url,user,pass);

		String sql = "SELECT j.job_title, ROUND(AVG(NVL(e.salary, 0)), 0) 평균급여 FROM employees e, jobs j "
				+ "WHERE e.job_id = j.job_id AND e.salary >= ? "
				+ "GROUP BY j.job_title "
				+ "ORDER BY ROUND(AVG(NVL(e.salary, 0)), 0) DESC";
		
		//2단계 : Statement 
		//3단계 : Query문 전송 및 결과 return
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setInt(1, maxSalary);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			System.out.println("[" + rs.getString("job_title") + "] " + rs.getInt("평균급여"));
		}

		if (rs != null) rs.close();
		if (pstmt != null) pstmt.close();
		if (con != null) con.close();
	}

	/// Main
    public static void main(String[] args) throws SQLException {
        EmpStatistics_priv stat = new EmpStatistics_priv();

        int maxSalary = 10000;
        stat.printStatistics(maxSalary);

        maxSalary = 15000;
        stat.printStatistics(maxSalary);
	}//end of main
}//end of class
