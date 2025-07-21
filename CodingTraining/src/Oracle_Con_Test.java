
import java.sql.*;

public class Oracle_Con_Test {
	public static void main(String[] args) {
	    // 1. 접속 정보
	    String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 또는 orcl, orcl10g 등
	    String user = "hr"; // 사용자명
	    String pass = "hr"; // 비밀번호
	
	    Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	
	    try {
	        // 2. 드라이버 로딩
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	
	        // 3. 연결
	        conn = DriverManager.getConnection(url, user, pass);
	        System.out.println("log -------- Oracle 연결 성공!");
	
	        // 4. 쿼리 실행
	        stmt = conn.createStatement();
	        String sql = "SELECT employee_id, first_name, last_name FROM employees";
	        rs = stmt.executeQuery(sql);
	
	        // 5. 결과 출력
	        while (rs.next()) {
	            int id = rs.getInt("employee_id");
	            String first = rs.getString("first_name");
	            String last = rs.getString("last_name");
	            System.out.println(id + " - " + first + " " + last);
	        }
	
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        // 6. 연결 해제
	        try { if (rs != null) rs.close(); } catch (Exception e) {}
	        try { if (stmt != null) stmt.close(); } catch (Exception e) {}
	        try { if (conn != null) conn.close(); } catch (Exception e) {}
	    }
	}

}
