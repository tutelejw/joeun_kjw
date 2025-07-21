import java.sql.*;  // JDBC API 사용을 위한 import

public class Oracle_Con_Test {
    public static void main(String[] args) {

        // 1. Oracle 데이터베이스 접속 정보 (URL, 사용자 계정, 비밀번호)
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // Oracle 접속 주소 (SID가 'xe'인 경우)
        String user = "hr";   // 사용자명 (예: HR 계정)
        String pass = "hr";   // 비밀번호

        // 2. JDBC에서 사용하는 객체 선언
        Connection conn = null;      // DB 연결 객체
        Statement stmt = null;       // SQL 실행 객체
        ResultSet rs = null;         // 조회 결과를 담는 객체

        try {
            // 3. JDBC 드라이버 로딩
            // → ojdbc14.jar 안에 포함된 드라이버 클래스 이름
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 4. Oracle DB에 접속
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("\n==============================");
            System.out.println("log -------- Oracle 연결 성공!");
            System.out.println("==============================\n");

            // 5. Statement 객체 생성 (SQL 실행용)
            stmt = conn.createStatement();

            // 6. 실행할 SQL문 정의 (사원 테이블에서 사원번호, 이름, 성 조회)
            String sql = "SELECT employee_id, first_name, last_name FROM employees";

            // 7. SQL 실행 → 결과를 ResultSet에 저장
            rs = stmt.executeQuery(sql);

            // 8. 결과 출력 (ResultSet에서 한 줄씩 읽기)
            while (rs.next()) {
                // 각 열의 데이터를 타입에 맞게 꺼냄
                int id = rs.getInt("employee_id");       // 정수형 사원번호
                String first = rs.getString("first_name"); // 이름
                String last = rs.getString("last_name");   // 성

                // 출력
                System.out.println(id + " - " + first + " " + last);
            }

        } catch (Exception e) {
            // 예외 발생 시 스택 추적 출력
            e.printStackTrace();
        } finally {
            // 9. 리소스 정리 (닫기 순서: ResultSet → Statement → Connection)
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}
