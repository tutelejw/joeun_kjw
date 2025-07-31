package jw04;

import java.sql.*;

public class UserDAO {

    public UserVO getUserById(String id) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UserVO user = null;

        try {
            conn = DbBean.getConnection();
            String sql = "SELECT * FROM users WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new UserVO();
                user.setId(rs.getString("id"));
                user.setPwd(rs.getString("pwd"));
                user.setActive(rs.getInt("active") == 1);
            }
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }

        return user;
    }
}