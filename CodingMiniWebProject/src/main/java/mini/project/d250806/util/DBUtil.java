package mini.project.d250806.util;

import java.sql.*;


public class DBUtil {
   public static Connection getConnection() throws Exception {
       Class.forName("oracle.jdbc.driver.OracleDriver");
       return DriverManager.getConnection(
           "jdbc:oracle:thin:@localhost:1521:xe", // 호스트, 포트, SID
           "scott", // 오라클 사용자 계정
           "tiger"  // 비밀번호
       );
   }
}
