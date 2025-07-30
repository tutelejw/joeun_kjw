package jw04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbBean {

    private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
    private static final String USER = "scott";
    private static final String PASSWORD = "tiger";

    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로딩 실패: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
