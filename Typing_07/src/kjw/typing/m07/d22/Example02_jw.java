package kjw.typing.m07.d22;
import java.sql.*;

public class Example02_jw {
    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.out.println("사용법: java Example02 <no> <id> <pwd>");
            return;
        }

        int no = Integer.parseInt(args[0]);
        String id = args[1];
        String pwd = args[2];

        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "scott";
        String password = "tiger";
        

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url, user, password);
        Statement stmt = con.createStatement();

//        stmt.setInt(1, no);
//        stmt.setString(2, id);
//        stmt.setString(3, pwd);
        
        String sql = "INSERT INTO member (no, id, pwd) VALUES (" +no + ", '" + id + "', '" + pwd + "')";
        
        int result = stmt.executeUpdate(sql);

        if (result > 0) {
            System.out.println("회원 정보가 성공적으로 입력되었습니다.");
        } else {
            System.out.println("회원 정보 입력 실패.");
        }

        stmt.close();
        con.close();
    }
}
