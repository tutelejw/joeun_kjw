package jw.service.user.dao;

import jw.common.pool.OracleConnectionPool;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserLoginPoolDAO {

    // 공통 자원 해제 메서드
    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try { if (rs != null) rs.close(); } catch (Exception ignored) {}
        try { if (pstmt != null) pstmt.close(); } catch (Exception ignored) {}
        try { if (conn != null) conn.close(); } catch (Exception ignored) {}
    }

    public UserVO login(String id, String pwd) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UserVO vo = null;

        
        try {
            conn = OracleConnectionPool.getInstance().getConnection();
            String sql = "SELECT id, pwd FROM ADDUSER_TEST WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next() && rs.getString("pwd").equals(pwd)) {
                vo = new UserVO();
                vo.setId(rs.getString("id"));
                vo.setPwd(rs.getString("pwd"));
            }
            System.out.println("login 요청:  id= " + vo.getId() + " ,pwd= " + vo.getPwd() );
            System.out.println(sql + "\n end");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, rs);
        }

        return vo;
    }

    public void addUser(UserVO vo) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = OracleConnectionPool.getInstance().getConnection();
            String sql = "INSERT INTO ADDUSER_TEST (id, pwd, gender, married) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, vo.getId());
            pstmt.setString(2, vo.getPwd());
            pstmt.setString(3, vo.getGender());
            pstmt.setString(4, vo.getMarried());
            pstmt.executeUpdate();
            System.out.println("addUser 요청:  id= " + vo.getId() + " ,pwd= " + vo.getPwd() + " , gender= " + vo.getGender() + " , married=" + vo.getMarried());
            System.out.println(sql + "\n end");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, null);
        }
    }

    public List<UserVO> getAllUsers() {
        List<UserVO> userList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = OracleConnectionPool.getInstance().getConnection();
            String sql = "SELECT no, id, pwd, gender, married FROM ADDUSER_TEST";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                UserVO user = new UserVO();
                user.setNo(rs.getString("no"));
                user.setId(rs.getString("id"));
                user.setPwd(rs.getString("pwd"));
                user.setGender(rs.getString("gender"));
                user.setMarried(rs.getString("married"));
                userList.add(user);
                System.out.println("[getAllUsers] 사용자 정보: id=" + user.getId() + ", pwd=" + user.getPwd()+ ", gender=" + user.getGender()+ ", married=" + user.getMarried());

            }
            System.out.println(sql + "\n end");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, rs);
        }

        return userList;
    }

    public UserVO getUserById(String id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UserVO user = null;

        try {
            conn = OracleConnectionPool.getInstance().getConnection();
            String sql = "SELECT * FROM ADDUSER_TEST WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new UserVO();
                user.setNo(rs.getString("no"));
                user.setId(rs.getString("id"));
                user.setPwd(rs.getString("pwd"));
                user.setGender(rs.getString("gender"));
                user.setMarried(rs.getString("married"));
                System.out.println("[getUserById] 사용자 정보: id=" + user.getId() + ", pwd=" + user.getPwd()+ ", gender=" + user.getGender()+ ", married=" + user.getMarried());
                System.out.println(sql + "\n end");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, rs);
        }

        return user;
    }
}
