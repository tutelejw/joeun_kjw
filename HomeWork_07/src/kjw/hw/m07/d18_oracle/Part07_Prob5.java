package kjw.hw.m07.d18_oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Part07_Prob5 {
	static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
	private static void printEmployeeList(String cityName, String deptName)throws Exception{
		//System.out.println(cityName +" / "+ deptName);
		String dburl = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
    	Connection conn=DriverManager.getConnection(dburl,"hr","hr");
    	
    	String sql = "select  l.city, d.department_name, e.first_name, e.salary\r\n"
    			+ "from employees e, departments d, locations l\r\n"
    			+ "where e.department_id = d.department_id\r\n"
    			+ "and d.location_id = l.location_id\r\n"
    			+ "and lower(l.city) like ?             -- 도시명  south  \r\n"
    			+ "and lower(d.department_name) like ?     -- 부서이름 it 해당하는 직원 목록을 출력 \r\n"
    			+ "order by e.employee_id";
		PreparedStatement psmt = conn.prepareStatement(sql);
		//System.out.println(cityName +" / "+ deptName +" / sql : " + sql);

	    psmt.setString(1, "%" + cityName.toLowerCase() + "%");
	    psmt.setString(2, "%" + deptName.toLowerCase() + "%");

	    ResultSet rs = psmt.executeQuery();
		
    	while (rs.next()) {
    		String ct = rs.getString("city");
    		String dname = rs.getString("department_name");
    		String fname= rs.getString("first_name"); 
    		int sal = rs.getInt("salary");
    		System.out.println(ct + " : " +" : "+ dname +" : "+fname+ " : "+ sal);
    	}
	}
	
	public static void main(String[] args)throws Exception{
		printEmployeeList("lon", "resource");
		//printEmployeeList("southlake", "it");
		
	}// out of main
}//out of class


//select  l.city, d.department_name, e.first_name, e.salary
//from employees e, departments d, locations l
//where e.department_id = d.department_id
//and d.location_id = l.location_id
//and lower(l.city) like 'southlake'             -- 도시명  south  
//and lower(d.department_name) like 'it'     -- 부서이름 it 해당하는 직원 목록을 출력 
//order by e.employee_id;

//CITY                   DEPARTMENT_NAME         FIRST_NAME            SALARY
//---------------- ------------------------------------ ---------------- ----------
//Southlake                 IT                                   Alexander                 9000
//Southlake                 IT                                   Bruce                        6000
//Southlake                 IT                                   David                        4800
//Southlake                 IT                                   Valli                          4800
//Southlake                 IT                                   Diana                        4200