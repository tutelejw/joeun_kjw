package kjw.typing.m07.d22;
import java.sql.*;

public class Example04_jw {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "scott";
        String password = "tiger";

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url, user, password);
        Statement stmt = con.createStatement();

        String sql = "SELECT no, id, pwd FROM member ORDER BY no";

        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int no = rs.getInt("no");
            String id = rs.getString("id");
            String pwd = rs.getString("pwd");

            System.out.println("회원정보 => 번호:" + no + " , ID:" + id + ", pwd:" + pwd);
        }

        rs.close();
        stmt.close();
        con.close();
    }
}
