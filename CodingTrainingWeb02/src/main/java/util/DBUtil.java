package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtil {

	private static final ResourceBundle rb = ResourceBundle.getBundle("db");
	
    public static Connection getConnection() throws SQLException {
        String url = rb.getString("db.url");
        String user = rb.getString("db.username");
        String password = rb.getString("db.password");

		
        try {
            Class.forName(rb.getString("db.driver"));
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
			throw new SQLException("DB연결실패", e);
		}
    }
		
	    public static void close(AutoCloseable... resources) {
	        for (AutoCloseable resource : resources) {
	            try {
	                if (resource != null) {
	                    resource.close();
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }

}