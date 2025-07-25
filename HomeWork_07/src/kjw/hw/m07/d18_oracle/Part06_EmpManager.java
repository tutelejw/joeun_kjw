package kjw.hw.m07.d18_oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Part06_EmpManager {
    
	static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void printEmployee(String city, int lo, int hi) throws Exception{
    	String dburl = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
    	Connection conn=DriverManager.getConnection(dburl,"hr","hr");
    	
    	String sql = "SELECT e.first_name, d.department_name, e.salary\r\n"
    			+ "FROM employees e, departments d, locations l\r\n"
    			+ "WHERE e.department_id = d.department_id\r\n"
    			+ "  AND d.location_id = l.location_id\r\n"
    			+ "  AND l.city = ?  \r\n"
    			+ "  AND e.salary BETWEEN ? AND ? ";
    	PreparedStatement psmt = conn.prepareStatement(sql);
    	psmt.setString(1,city);
    	psmt.setInt(2, lo);
    	psmt.setInt(3, hi);
    	ResultSet rs = psmt.executeQuery();
    	
    	while (rs.next()) {
    		String fname= rs.getString("first_name");
    		String dname = rs.getString("department_name");
    		int sal = rs.getInt("salary");
    		System.out.println(fname +" : "+ dname + " : "+ sal);
    	}
    	
    	System.out.println(city +" : "+ lo + " : "+ hi);
		if (rs != null) rs.close();
		if (psmt != null) psmt.close();
		if (conn != null) conn.close();
    }
    public static void main(String[] args) throws Exception {
    	new Part06_EmpManager().printEmployee("South San Francisco", 7000, 10000);
    	
    }//out of main
}// out of class

//SELECT e.first_name, d.department_name, e.salary
//FROM employees e, departments d, locations l
//WHERE e.department_id = d.department_id
//  AND d.location_id = l.location_id
//  AND l.city = 'South San Francisco'  
//  AND e.salary BETWEEN 7000 AND 10000 ; 


//FIRST_NAME                               DEPARTMENT_NAME                                                  SALARY
//---------------------------------------- ------------------------------------------------------------ ----------
//Matthew                                  Shipping                                                           8000
//Adam                                     Shipping                                                           8200
//Payam                                    Shipping                                                           7900