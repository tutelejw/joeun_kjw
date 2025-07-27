package SelfStudy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleJdbcExample {

    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE"; 
    private static final String USER = "your_username"; // Oracle 사용자명
    private static final String PASS = "your_password"; // Oracle 비밀번호

    public static void main(String[] args) {
        Connection conn = null; // 데이터베이스 연결 객체
        Statement stmt = null;   // SQL 쿼리를 실행하는 객체 (간단한 쿼리용)
        PreparedStatement pstmt = null; // SQL 쿼리를 실행하는 객체 (매개변수 있는 쿼리용, 보안 및 성능 우수)
        ResultSet rs = null;     // 쿼리 결과 셋 객체

        try {
            // 1. JDBC 드라이버 로드 (Oracle 10g 이후부터는 생략 가능하지만 명시적으로 적는 경우도 많음)
            try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.out.println("Oracle JDBC 드라이버 로드 성공!");
            

            // 2. 데이터베이스 연결
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Oracle 데이터베이스에 성공적으로 연결되었습니다!");
            

            // 3. Statement 객체를 이용한 데이터 조회 예제 (간단한 쿼리)
            String selectSql = "SELECT employee_id, first_name, last_name, salary FROM employees WHERE ROWNUM <= 5"; // 예시 쿼리
            stmt = conn.createStatement(); // Statement 객체 생성
            rs = stmt.executeQuery(selectSql); // 쿼리 실행 및 결과 셋 받기
            

            // 결과 셋 처리
            while (rs.next()) { // 다음 레코드가 있다면 true 반환
                int id = rs.getInt("employee_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                double salary = rs.getDouble("salary");
                System.out.println("ID: " + id + ", 이름: " + firstName + " " + lastName + ", 급여: " + salary);
            }

            // 4. PreparedStatement 객체를 이용한 데이터 삽입 예제 (보안 및 성능에 유리, 매개변수 바인딩)
            System.out.println("\n--- PreparedStatement를 이용한 데이터 삽입 ---");
            // 실제 테이블에 맞게 컬럼과 값 수정 (테스트용이므로 실제 운영 DB에는 신중하게 사용)
            String insertSql = "INSERT INTO employees (employee_id, first_name, last_name, email, hire_date, job_id, salary) VALUES (?, ?, ?, ?, SYSDATE, ?, ?)";
            pstmt = conn.prepareStatement(insertSql);

            int newId = 9999;
            String newFirstName = "길동";
            String newLastName = "홍";
            String newEmail = "HONGGD";
            String newJobId = "IT_PROG"; // 예시 직무 ID
            double newSalary = 5000.00;

            pstmt.setInt(1, newId); // 첫 번째 ?에 newId 값 바인딩
            pstmt.setString(2, newFirstName);
            pstmt.setString(3, newLastName);
            pstmt.setString(4, newEmail);
            pstmt.setString(5, newJobId);
            pstmt.setDouble(6, newSalary);

            int rowsAffected = pstmt.executeUpdate(); // INSERT, UPDATE, DELETE 쿼리는 executeUpdate() 사용
            System.out.println(rowsAffected + "개의 레코드가 삽입되었습니다.");

        } catch (SQLException e) {
            // SQL 관련 예외 처리
            System.err.println("데이터베이스 오류 발생: " + e.getMessage());
            e.printStackTrace(); // 개발 단계에서 디버깅을 위해 스택 트레이스 출력
        } finally {
            // 6. 리소스 해제 (역순으로 닫는 것이 일반적)
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
                System.out.println("\n데이터베이스 연결 및 리소스가 성공적으로 해제되었습니다.");
            } catch (SQLException e) {
                System.err.println("리소스 해제 중 오류 발생: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}