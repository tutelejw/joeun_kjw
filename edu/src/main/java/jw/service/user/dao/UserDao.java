package jw.service.user.dao;

import java.sql.*;
import jw.common.util.DBUtil;
import jw.common.dao.AbstractDao;

public class UserDao extends AbstractDao {
    // 로그인 체크 메서드
    public boolean login(String id, String pwd) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM users WHERE id = ? AND pwd = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pwd);
            rs = pstmt.executeQuery();

            // 결과가 있으면 로그인 성공
            result = rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, rs);
        }

        return result;
    }
}