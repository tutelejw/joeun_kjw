// src/main/java/com/academy/dao/PhotoDAO.java
package com.academy.dao;

import com.academy.util.DBUtil;
import com.academy.vo.PhotoVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class PhotoDAO {
    private static final Logger logger = Logger.getLogger(PhotoDAO.class);

    public void insertPhoto(PhotoVO photo) {
        String sql = "INSERT INTO mini_photos (photo_path, photo_description, photo_date, user_id, is_public) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, photo.getPhotoPath());
            pstmt.setString(2, photo.getPhotoDescription());
            pstmt.setDate(3, new java.sql.Date(photo.getPhotoDate().getTime()));
            pstmt.setString(4, photo.getUserId());
            pstmt.setString(5, photo.getIsPublic());
            pstmt.executeUpdate();
            logger.info("사진 정보 DB 저장 성공: " + photo.getPhotoPath());
        } catch (SQLException e) {
            logger.error("사진 정보 DB 저장 중 오류 발생: " + e.getMessage());
        }
    }

    public List<PhotoVO> getPhotos(String userId) {
        List<PhotoVO> photoList = new ArrayList<>();
        String sql = "SELECT * FROM mini_photos WHERE is_public = 'Y' OR user_id = ? ORDER BY photo_date DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    PhotoVO photo = new PhotoVO();
                    photo.setPhotoId(rs.getInt("photo_id"));
                    photo.setPhotoPath(rs.getString("photo_path"));
                    photo.setPhotoDescription(rs.getString("photo_description"));
                    photo.setPhotoDate(rs.getDate("photo_date"));
                    photo.setUserId(rs.getString("user_id"));
                    photo.setIsPublic(rs.getString("is_public"));
                    photoList.add(photo);
                }
            }
        } catch (SQLException e) {
            logger.error("사진 목록 조회 중 DB 오류 발생: " + e.getMessage());
        }
        return photoList;
    }
}