package com.semi.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBUtil {
	
	///Field
	private final static String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final static String JDBC_URL = "jdbc:oracle:thin:test/test@127.0.0.1:1521:xe";
	
	///Constructor
	private DBUtil(){
	}
	
	///Method
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * 데이터베이스 관련 자원(Connection, Statement, ResultSet)을 닫는 메소드입니다.
	 * @param con Connection 객체
	 * @param stmt Statement 객체 (PreparedStatement 포함)
	 * @param rs ResultSet 객체
	 */
	public static void close(Connection con, Statement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		close(con, stmt);
	}

	/**
	 * 데이터베이스 관련 자원(Connection, Statement)을 닫는 메소드입니다.
	 * @param con Connection 객체
	 * @param stmt Statement 객체 (PreparedStatement 포함)
	 */
	public static void close(Connection con, Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}