package jw.common.util;

import java.sql.Connection;
import java.sql.DriverManager;

import jw.common.pool.OracleConnectionPool;

public class DBUtil {
    // 오라클 DB 연결 정보
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "scott";
    private static final String PASSWORD = "tiger";

    // DB 연결을 반환하는 메서드

    
    public static Connection getConnection (String driverClassName, String url, String id, String passwd) {
    	Connection con = null;
    	try {
    		Class.forName(driverClassName);
    		con = DriverManager.getConnection(url, id, passwd);
    	}catch (Exception e) {
    		e.printStackTrace();
    		throw new RuntimeException("db 접속시 오류 발생 : " + e);
    	}
    	return con;
    }
    
    public static Connection getConnection() {
    	Connection con = null;
    	try {
    		con = OracleConnectionPool.getInstance().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        	if(con == null)
        		con = getConnection("oracle.jdbc.driver.OracleDriver",
        												"jdbc:oracle:thin:@127.0.0.1:1521:xe",
        												"scott","tiger");
        }
    	return con;
        }
 }    