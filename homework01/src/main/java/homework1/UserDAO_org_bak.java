package homework1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO_org_bak {
    private final String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private final String user = "scott";
    private final String password = "tiger";

    private Connection conn;
    private PreparedStatement pstmt;

    public UserDAO_org_bak() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public UserVO login(String id, String pwd) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UserVO vo = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, user, password);

            //String sql = "SELECT id, pwd, 'Y' active FROM users WHERE id = ?";
            String sql = "SELECT id, pwd FROM ADDUSER_TEST WHERE id = ?";
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
    
    public void addUser(UserVO vo) {
        String sql = "INSERT INTO ADDUSER_TEST  (id, pwd, gender, married) VALUES (?, ?, ?, ?)";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getId());
            pstmt.setString(2, vo.getPwd());
            pstmt.setString(3, vo.getGender());
            pstmt.setString(4, vo.getMarried());

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (pstmt != null) pstmt.close(); } catch (Exception ignored) {}
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }
    }
    public List<UserVO> getAllUsers() {
        List<UserVO> userList = new ArrayList<>();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");

            String sql = "SELECT no, id, pwd, gender, married FROM ADDUSER_TEST";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                UserVO user = new UserVO();
                user.setNo(rs.getString("no"));
                user.setId(rs.getString("id"));
                user.setPwd(rs.getString("pwd"));
                user.setGender(rs.getString("gender"));
                user.setMarried(rs.getString("married"));
                userList.add(user);
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userList;
    }
}
