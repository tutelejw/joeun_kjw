package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBUtil - 오라클 DB 연결 유틸리티 클래스
 * ※ DB 정보는 환경에 맞게 바꿔주세요.
 */

public class DBUtil {
    // JDBC URL, 사용자명, 비밀번호를 자신의 환경에 맞게 수정하세요.
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "test";
	private static final String PASSWORD = "test";
	
    // 드라이버 로드 및 연결 반환
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		//Class.forName 호출 생략 (JDBC 4.0 이상에서는 자동 로딩됨)
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("DBUtutil.getConnection --- 호출부분" );
		return DriverManager.getConnection(URL, USER, PASSWORD);

	}
}
