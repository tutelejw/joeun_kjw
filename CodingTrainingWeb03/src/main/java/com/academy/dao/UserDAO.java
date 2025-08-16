// src/main/java/com/academy/dao/UserDAO.java
package com.academy.dao;

import com.academy.util.DBUtil;
import com.academy.vo.UserVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class UserDAO {
    private static final Logger logger = Logger.getLogger(UserDAO.class);

    public boolean checkUserId(String userId) {
        String sql = "SELECT user_id FROM mini_users WHERE user_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            logger.error("ID 중복 체크 중 DB 오류 발생: " + e.getMessage());
            return false;
        }
    }

    public void insertUser(UserVO user) {
        String sql = "INSERT INTO mini_users (user_id, user_password, user_name, user_age, user_address, memory_title) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getUserPassword());
            pstmt.setString(3, user.getUserName());
            pstmt.setInt(4, user.getUserAge());
            pstmt.setString(5, user.getUserAddress());
            pstmt.setString(6, user.getMemoryTitle());
            pstmt.executeUpdate();
            System.out.println("사용자등록 성공 " + user.getUserId());
            logger.info("사용자 등록 성공: " + user.getUserId());
        } catch (SQLException e) {
        	System.out.println("사용자등록 실패 " + user.getUserId());
            logger.error("사용자 등록 중 DB 오류 발생: " + e.getMessage());
        }
        System.out.println("UserDAO - insertUser sql : " + sql );
    }
    
    public UserVO loginUser(String userId, String password) {
        String sql = "SELECT user_id, user_password FROM mini_users WHERE user_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("user_password");
                    if (verifyPassword(password, storedHash)) {
                        UserVO user = new UserVO();
                        user.setUserId(userId);
                        return user;
                    }
                }
            }
        } catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            logger.error("로그인 중 DB 또는 해싱 오류 발생: " + e.getMessage());
        }
        return null;
    }

    private boolean verifyPassword(String password, String storedHash) throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = 15;
        byte[] salt = new byte[16];
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, 256);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        String generatedHash = Base64.getEncoder().encodeToString(hash);
        return generatedHash.equals(storedHash);
    }
}