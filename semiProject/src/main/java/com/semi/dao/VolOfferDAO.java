package com.semi.dao;

import java.sql.Connection;
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
            pstmt.setTimestamp(4, new Timestamp(vo.getStartTime().getTime()));
            pstmt.setTimestamp(5, new Timestamp(vo.getEndTime().getTime()));
            pstmt.setString(6, vo.getContent());
            pstmt.setString(7, vo.getRegion());
            pstmt.setString(8, vo.getCategory());
            pstmt.setDate(9, new java.sql.Date(vo.getOfferDate().getTime()));
            pstmt.setString(10, vo.getProcessStatus());

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, pstmt, conn); // rs는 사용하지 않으므로 null 처리
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

            while (rs.next()) {
                VolOffer vo = new VolOffer();
                vo.setPostId(rs.getInt("POST_ID"));
                vo.setTitle(rs.getString("TITLE"));
                vo.setAuthorId(rs.getString("AUTHOR_ID"));
                vo.setPhone(rs.getString("PHONE"));
                vo.setStartTime(rs.getTimestamp("START_TIME"));
                vo.setEndTime(rs.getTimestamp("END_TIME"));
                vo.setContent(rs.getString("CONTENT"));
                vo.setRegion(rs.getString("REGION"));
                vo.setCategory(rs.getString("CATEGORY"));
                vo.setOfferDate(rs.getDate("OFFER_DATE"));
                vo.setProcessStatus(rs.getString("PROCESS_STATUS"));
                vo.setCreatedAt(rs.getTimestamp("CREATED_AT"));
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return list;
    }
}