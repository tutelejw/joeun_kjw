package jw05;

import java.sql.*;

public class UserDAO {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "scott";
    private static final String PASSWORD = "tiger";

    public UserVO login(String id, String pwd) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UserVO vo = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            //String sql = "SELECT id, pwd, 'Y' active FROM users WHERE id = ?";
            String sql = "SELECT id, pwd FROM users WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                //String dbPwd = rs.getString("pwd");
            	System.out.println("rs.next : true---------");
                if (rs.getString("pwd").equals(pwd)) {
                    vo = new UserVO();
                    vo.setId(rs.getString("id"));
                    vo.setPwd(rs.getString("pwd"));
                    //vo.setActive(rs.getString("active"));
                    //System.out.println("id 값 : " + rs.getString("id")  + "/ active 값 : " +rs.getString("active") );
                    System.out.println("id 값 : " + rs.getString("id") );
                }
                System.out.println("rs.next : false---------");
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