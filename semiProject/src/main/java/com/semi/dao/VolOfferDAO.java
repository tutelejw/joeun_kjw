package com.semi.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.semi.common.util.DBUtil;
import com.semi.domain.VolOffer;

public class VolOfferDAO {

    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    /**
     * 봉사 제공 게시글 등록
     */
    public void addVolOffer(VolOffer vo) {
        String sql = "INSERT INTO VOL_OFFER (POST_ID, TITLE, AUTHOR_ID, PHONE, START_TIME, END_TIME, CONTENT, REGION, CATEGORY, OFFER_DATE, PROCESS_STATUS) "
                   + "VALUES (SEQ_VOL_OFFER.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getAuthorId());
            pstmt.setString(3, vo.getPhone());
            // LocalDateTime -> Timestamp 변환
            pstmt.setTimestamp(4, Timestamp.valueOf(vo.getStartTime()));
            pstmt.setTimestamp(5, Timestamp.valueOf(vo.getEndTime()));
            pstmt.setString(6, vo.getContent());
            pstmt.setString(7, vo.getRegion());
            pstmt.setString(8, vo.getCategory());
            // LocalDate -> java.sql.Date 변환
            pstmt.setDate(9, Date.valueOf(vo.getOfferDate()));
            pstmt.setString(10, vo.getProcessStatus());

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(pstmt, conn);
        }
    }

    /**
     * 봉사 제공 게시글 목록 조회
     */
    public List<VolOffer> getVolOfferList() {
        List<VolOffer> list = new ArrayList<>();
        String sql = "SELECT * FROM VOL_OFFER ORDER BY POST_ID DESC";

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            int rowCount = 0;
            while (rs.next()) {
                VolOffer vo = new VolOffer();
                vo.setPostId(rs.getInt("POST_ID"));
                vo.setTitle(rs.getString("TITLE"));
                vo.setAuthorId(rs.getString("AUTHOR_ID"));
                vo.setPhone(rs.getString("PHONE"));
                // Timestamp -> LocalDateTime 변환
                vo.setStartTime(rs.getTimestamp("START_TIME").toLocalDateTime());
                vo.setEndTime(rs.getTimestamp("END_TIME").toLocalDateTime());
                vo.setContent(rs.getString("CONTENT"));
                vo.setRegion(rs.getString("REGION"));
                vo.setCategory(rs.getString("CATEGORY"));
                // java.sql.Date -> LocalDate 변환
                vo.setOfferDate(rs.getDate("OFFER_DATE").toLocalDate());
                vo.setProcessStatus(rs.getString("PROCESS_STATUS"));
                vo.setCreatedAt(rs.getTimestamp("CREATED_AT").toLocalDateTime());
                list.add(vo);
                rowCount++;
             // 각 행 데이터 출력
                System.out.println("[DEBUG] 조회된 게시글: " + vo.getPostId() + " | 제목: " + vo.getTitle());
            }
            System.out.println("[DEBUG] 총 조회 건수: " + rowCount);
        } catch (Exception e) {
        	System.out.println("[ERROR] getVolOfferList 예외 발생: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
            System.out.println("[DEBUG] DB 자원 정리 완료");
        }
        return list;
    }
}