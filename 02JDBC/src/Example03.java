import java.sql.*;

public class Example03 {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("사용법: java Example03 <id>");
            return;
        }

        String id = args[0];

        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "scott";
        String password = "tiger";

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url, user, password);
        Statement stmt = con.createStatement();

        // SQL 문자열 직접 조립 (주의: SQL Injection 취약)
        String sql = "DELETE FROM member WHERE id = '" + id + "'";

        int result = stmt.executeUpdate(sql);

        if (result > 0) {
            System.out.println("ID '" + id + "'에 해당하는 회원이 삭제되었습니다.");
        } else {
            System.out.println("ID '" + id + "'에 해당하는 회원이 없습니다.");
        }

        stmt.close();
        con.close();
    }
}
