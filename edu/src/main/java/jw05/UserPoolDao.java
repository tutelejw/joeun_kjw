package jw05;

import java.sql.*;
import jw.common.pool.OracleConnectionPool;

public class UserPoolDao {
//    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
//    private static final String USER = "scott";
//    private static final String PASSWORD = "tiger";

    public UserVO login(String id, String pwd) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UserVO vo = null;

        try {
            //Class.forName("oracle.jdbc.driver.OracleDriver");
            //conn = DriverManager.getConnection(URL, USER, PASSWORD);
            conn=OracleConnectionPool.getInstance().getConnection();

            //String sql = "SELECT id, pwd,'Y' active FROM users WHERE id = ?";
            String sql = "SELECT id, pwd FROM users WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String dbPwd = rs.getString("pwd");
                if (dbPwd.equals(pwd)) {
                    vo = new UserVO();
                    vo.setId(rs.getString("id"));
                    vo.setPwd(dbPwd);
                  //  vo.setActive(rs.getString("active"));
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
    
    
    
    
    public void getUser(UserVO userVO) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UserVO vo = null;

        try {
            conn = OracleConnectionPool.getInstance().getConnection();

            //String sql = "SELECT id, pwd, 'Y' active FROM users WHERE id = ?";
            String sql = "SELECT id, pwd FROM users WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userVO.getId());
            //pstmt.setString(2, userVO.getPwd());
            //pstmt.setString(3, userVO.getActive());
            
            rs = pstmt.executeQuery();

            if (rs.next()) {
            	//System.out.println("DB 에서 확인 한 정보 id, pwd, active : " + userVO.getId() +" / " + userVO.getPwd() +" / " +userVO.getActive());
            	System.out.println("DB 에서 확인 한 정보 id, pwd : " + userVO.getId() +" / " + userVO.getPwd());
                vo = new UserVO();
                vo.setId(rs.getString("id"));
                vo.setPwd(rs.getString("pwd"));
                //vo.setActive(rs.getString("active"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
//        return vo;
    }
}
