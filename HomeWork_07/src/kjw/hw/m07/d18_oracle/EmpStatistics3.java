package kjw.hw.m07.d18_oracle;
import java.sql.*;

public class EmpStatistics3{
	private static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private static final String user = "hr";
	private static final String pass = "hr";

	//1단계 : Connection 
	static {
		try {
			Class.forName(ORACLE_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void printStatistics(int maxSalary) throws SQLException {
		System.out.println("=================================");
		System.out.println("Max Salary: " + maxSalary);
		System.out.println("=================================");

		Connection con = DriverManager.getConnection(url,user,pass);

		String sql = "SELECT j.job_title, ROUND(AVG(NVL(e.salary, 0)), 0) 평균급여 FROM employees e, jobs j "
				+ "WHERE e.job_id = j.job_id AND e.salary >= ? "
				+ "GROUP BY j.job_title "
				+ "ORDER BY ROUND(AVG(NVL(e.salary, 0)), 0) DESC";
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
		new EmpStatistics3().printStatistics(10000);
		new EmpStatistics3().printStatistics(15000);
	    
	}//end of main
}//end of class
