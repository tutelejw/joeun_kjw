package mini.project.d250806.dao;

import java.sql.*;
import java.util.*;
import mini.project.d250806.model.User;
import mini.project.d250806.util.DBUtil;


public class UserDAO {
   // 회원 등록 메서드
   public int registerUser(User user) throws Exception {
       String sql = "INSERT INTO users VALUES (?, ?, ?)";
       try (Connection conn = DBUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
           pstmt.setString(1, user.getId());
           pstmt.setString(2, user.getPassword());
           pstmt.setString(3, user.getName());
           return pstmt.executeUpdate();
       }
   }


   // 로그인 메서드
   public User login(String id, String password) throws Exception {
       String sql = "SELECT * FROM users WHERE id=? AND password=?";
       try (Connection conn = DBUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
           pstmt.setString(1, id);
           pstmt.setString(2, password);
           ResultSet rs = pstmt.executeQuery();
           if (rs.next()) {
               return new User(rs.getString("id"), rs.getString("password"), rs.getString("name"));
           }
       }
       return null;
   }


   // 전체 사용자 목록 조회 메서드
   public List<User> getAllUsers() throws Exception {
       List<User> list = new ArrayList<>();
       String sql = "SELECT * FROM users";
       try (Connection conn = DBUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {
           while (rs.next()) {
               list.add(new User(rs.getString("id"), rs.getString("password"), rs.getString("name")));
           }
       }
       return list;
   }
}

