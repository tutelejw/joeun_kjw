package kjw.hw.m07.d18_oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Part03_EmpManager {

    static {
	        try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }

    public void printEmployee(String name,int salary)throws SQLException{
    	String dburl = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
    	Connection conn=DriverManager.getConnection(dburl,"hr","hr");
    	//이곳 프로그램 구현
    	
		String sql = "select employee_id, first_name, salary from employees "
				+ "where lower(first_name) like ? and salary > ? ";
//				+ "where lower(first_name) like '%ai%' and salary > 4000 ";
//		System.out.println("name : " + name + " / salary : " +salary +" / sql : " + sql);
		PreparedStatement psmt = conn.prepareStatement(sql);
		
		psmt.setString(1, "%"+name.toLowerCase()+"%");
		psmt.setInt(2, salary);
		
		ResultSet rs = psmt.executeQuery();
		
		boolean hasResult=false;
		while(rs.next()) {
			hasResult=true;
			String eid=rs.getString("employee_id");
			String fname=rs.getString("first_name");
			int sal=rs.getInt("salary");
			System.out.println( eid +" : " +fname +"  : "  +sal);
		}
		if (!hasResult) {
			System.out.println("조건이 맞는 사람 없음");
		}
		if (rs != null) rs.close();
		if (psmt != null) psmt.close();
		if (conn != null) conn.close();
    }
    
    public static void main(String[] args) throws SQLException{
    	new Part03_EmpManager().printEmployee("al", 4000);
    }
    
    
}
