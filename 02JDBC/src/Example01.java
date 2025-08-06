import java.sql.*;

public class Example01 {
    public static void main(String[] args) throws Exception {
        // 1. DB 접속 정보
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "scott";
        String password = "tiger";

		String driver = "oracle.jdbc.driver.OracleDriver";
        Class.forName(driver);
		
        // 2. 테이블 생성 SQL
        String createTableSQL =
        	    "CREATE TABLE member (" +
        	    "no NUMBER, " +
        	    "id VARCHAR2(10), " +
        	    "pwd VARCHAR2(10)" +
        	    ")";

        // 3. JDBC 작업
        try (
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement()
        ) {
            // 드라이버 등록 (생략 가능하지만 명시적으로 쓰는 것이 좋음)
            Class.forName("oracle.jdbc.driver.OracleDriver");

            stmt.executeUpdate(createTableSQL);
            System.out.println("테이블 'member'가 성공적으로 생성되었습니다.");
        } catch (SQLException e) {
            System.err.println("SQL 오류: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC 드라이버 로딩 실패: " + e.getMessage());
        }
    }
}
