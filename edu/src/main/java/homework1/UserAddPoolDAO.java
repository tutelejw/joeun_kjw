package homework1;

import java.sql.*;
import jw.common.pool.OracleConnectionPool;

public class UserAddPoolDAO {

    public UserVO login(String id, String pwd) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UserVO vo = null;

        try {
            conn=OracleConnectionPool.getInstance().getConnection();

//            String sql = "SELECT id, pwd, active FROM users WHERE id = ?";
            String sql = "SELECT id, pwd,'Y' active FROM SCOTT.ADDUSER_TEST  WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String dbPwd = rs.getString("pwd");
                if (dbPwd.equals(pwd)) {
                    vo = new UserVO();
                    vo.setId(rs.getString("id"));
                    vo.setPwd(dbPwd);
                    vo.setActive(rs.getString("active"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
        return vo;
    }
}