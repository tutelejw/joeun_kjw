package jw.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class AbstractDao {
	protected void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if ( rs!= null) rs.close();
		}catch (Exception e) {
		}
		try {
			if(pstmt != null) pstmt.close();
		}catch (Exception e) {
		}
		try {
			if(conn != null) conn.close();
		}catch(Exception e) {
		}
	}
	protected void close(Connection conn, PreparedStatement pstmt) {
		close(conn, pstmt, null);
	}
}
