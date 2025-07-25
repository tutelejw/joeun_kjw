package kjw.hw.m07.d18_oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;

public class Part05_Prob5 {
	public static void main(String[] args) throws Exception {

		if(args.length != 1) {
			System.out.println("부서의 아이디를 입력하세요...");
			System.exit(1);
		}
		String dept_id=args[0];
		int int_did = Integer.parseInt(dept_id);
		
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
	
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		    String user = "hr";
		    String pass = "hr";			
		    
		    con = DriverManager.getConnection(url,user,pass);
		    
//		    String sql="select e1.employee_id, d.department_name, e1.salary, e2.avg_salary "
//		    		+ " from employees e1, "
//		    		+ "	(select department_id, round(avg(salary),0) avg_salary "
//		    		+ "	from employees "
//		    		+ "    where department_id= ?    "  // 입력받은 값 변수 처리 필요
//		    		+ "	group by department_id) e2, "
//		    		+ "    departments d "
//		    		+ " where e1.department_id = e2.department_id "
//		    		+ " and e1.department_id = d.department_id ";
		    
		   String sql="select e1.employee_id, d.department_name, e1.salary, e2.avg_salary \r\n"
		    		+ " from employees e1, \r\n"
		    		+ "	(select department_id, round(avg(salary),0) avg_salary \r\n"
		    		+ "	from employees \r\n"
		    		+ "    where department_id=?   \r\n"
		    		+ "	group by department_id) e2,\r\n"
		    		+ "    departments d\r\n"
		    		+ " where e1.department_id = e2.department_id\r\n"
		    		+ " and e1.department_id = d.department_id";
		    		
		    psmt = con.prepareStatement(sql);
		    
		    psmt.setInt(1, int_did);
		    rs=psmt.executeQuery();
		    
		    while(rs.next()) {
		    int eid=rs.getInt("EMPLOYEE_ID");
		    String dname=rs.getString("DEPARTMENT_NAME");
		    int sal=rs.getInt("SALARY");
		    int avg_sal=rs.getInt("AVG_SALARY");
		    System.out.println(eid + dname + sal + avg_sal);
		    }
		    
	}//out of main
}// out of class



//EMPLOYEE_ID 			DEPARTMENT_NAME                                                  SALARY				 AVG_SALARY
//----------- ------------------------------------------------------------ ---------- ----------
//        116 Purchasing                                                         2900       4150
//        114 Purchasing                                                        11000       4150
//        115 Purchasing                                                         3100       4150
//        117 Purchasing                                                         2800       4150
//        118 Purchasing                                                         2600       4150
//        119 Purchasing                                                         2500       4150
//        
//select e1.employee_id, d.department_name, e1.salary, e2.avg_salary
//from employees e1, 
//	(select department_id, round(avg(salary),0) avg_salary 
//	from employees 
//    where department_id=30    -- 입력받은 값 변수 처리 필요
//	group by department_id) e2,
//    departments d
//where e1.department_id = e2.department_id
//and e1.department_id = d.department_id
//;
