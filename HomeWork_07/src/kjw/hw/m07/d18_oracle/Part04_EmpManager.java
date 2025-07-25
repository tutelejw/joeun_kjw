package kjw.hw.m07.d18_oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Part04_EmpManager {
    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
//public void printEmployee(String jobs[])throws SQLException{
public void printEmployee(String jobs[])throws SQLException{
    	String dburl = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
    	Connection conn=DriverManager.getConnection(dburl,"hr","hr");
        
        StringBuilder placeholders = new StringBuilder();
        for (int i = 0; i < jobs.length; i++) {
            placeholders.append("?");
            if (i < jobs.length - 1) {
                placeholders.append(", ");
            }
        }
    	String sql="select 	e.employee_id, e.first_name, e.salary, j.job_title"
        		+ "    		from employees e, jobs j"
        		+ "    		where e.job_id= j.job_id"
        		+ "    		and j.job_title in (" + placeholders + ") "
        		//+ "    		and j.job_title in ('Accountant','Stock Clerk')"
        		+ "    		order by e.employee_id";
    	
    	System.out.println("jobs.length = " + jobs.length);
        System.out.println("sql : " + sql);

        PreparedStatement  psmt = conn.prepareStatement(sql);
        for (int i = 0; i < jobs.length; i++) {
            psmt.setString(i + 1, jobs[i]);
            System.out.println("jobs : " + jobs[i]);
        }
       
        ResultSet rs=psmt.executeQuery();
        
        while(rs.next()) {
        	int eid=rs.getInt("employee_id");
        	String fname=rs.getString("first_name");
        	String sal=rs.getString("salary");
        	String j_title=rs.getString("job_title");
        	System.out.println(eid +" / " + fname + " / " +  sal +  " / " + j_title);
        }    
    }//out of printEmployee
    		
    public static void main(String[] args) throws SQLException{
    	//String jobs = "Accountant";
    	String[] jobs = {"Accountant", "Stock Clerk"};
    	new Part04_EmpManager().printEmployee(jobs);
    }
}//out of class

//EMPLOYEE_ID    FIRST_NAME            SALARY       JOB_TITLE
//----------- ---------------------------------------- ---------- ----------------------------------------------------------------------
//        109 Daniel                                         9000 Accountant
//        110 John                                           8200 Accountant
//        111 Ismael                                         7700 Accountant
//        112 Jose Manuel                                    7800 Accountant
//        113 Luis                                           6900 Accountant