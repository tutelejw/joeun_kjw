package com.semi.service.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.semi.common.util.DBUtil;
import com.semi.domain.Notice;

/**
 * Notice JDBC DAO
 * - Oracle ROWNUM 기반 페이징
 * - LOWER/UPPER 없이 LIKE 사용(요청 스타일 반영)
 */
public class NoticeDao {

    // ====== SELECT COUNT (검색 포함) ======
    public int count(String keyword) throws SQLException {
        System.out.println("[NoticeDao] count() :: keyword=" + keyword);

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(*) ")
           .append("FROM notice ");
        if (keyword != null && !keyword.isEmpty()) {
            sql.append("WHERE (title LIKE ? OR content LIKE ? OR authorid LIKE ?)");
        }

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql.toString());
            if (keyword != null && !keyword.isEmpty()) {
                String like = "%" + keyword + "%";
                pstmt.setString(1, like);
                pstmt.setString(2, like);
                pstmt.setString(3, like);
            }
            rs = pstmt.executeQuery();
            rs.next();
            return rs.getInt(1);
        } finally {
            DBUtil.close(rs);
            DBUtil.close(pstmt);
            DBUtil.close(conn);
        }
    }

    // ====== 목록 (ROWNUM 페이징) ======
    public List<Notice> findList(int startRow, int endRow, String keyword) throws SQLException {
        System.out.println("[NoticeDao] findList() :: startRow=" + startRow + ", endRow=" + endRow + ", keyword=" + keyword);

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String base = "SELECT noticeid, authorid, title, content, createdat FROM notice";
        String where = (keyword != null && !keyword.isEmpty())
                ? " WHERE (title LIKE ? OR content LIKE ? OR authorid LIKE ?)" : "";
        String order = " ORDER BY noticeid DESC";

        String pagingSql =
                "SELECT * FROM ( " +
                "  SELECT t.*, ROWNUM rnum FROM ( " +
                base + where + order +
                "  ) t WHERE ROWNUM <= ? " +
                ") WHERE rnum >= ?";

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(pagingSql);

            int idx = 1;
            if (keyword != null && !keyword.isEmpty()) {
                String like = "%" + keyword + "%";
                pstmt.setString(idx++, like);
                pstmt.setString(idx++, like);
                pstmt.setString(idx++, like);
            }
            pstmt.setInt(idx++, endRow);
            pstmt.setInt(idx, startRow);

            rs = pstmt.executeQuery();

            List<Notice> list = new ArrayList<>();
            while (rs.next()) {
                list.add(map(rs));
            }
            return list;
        } finally {
            DBUtil.close(rs);
            DBUtil.close(pstmt);
            DBUtil.close(conn);
        }
    }

    // ====== 단건 조회 ======
    public Notice findById(long noticeId) throws SQLException {
        System.out.println("[NoticeDao] findById(" + noticeId + ")");

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "SELECT noticeid, authorid, title, content, createdat FROM notice WHERE noticeid = ?";

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, noticeId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return map(rs);
            }
            return null;
        } finally {
            DBUtil.close(rs);
            DBUtil.close(pstmt);
            DBUtil.close(conn);
        }
    }

    // ====== 등록 ======
    public long insert(Notice notice) throws SQLException {
        System.out.println("[NoticeDao] insert() :: authorId=" + notice.getAuthorId());

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // createdat = SYSDATE, PK = seq_notice.NEXTVAL
        String sql = "INSERT INTO notice (noticeid, authorid, title, content, createdat) "
                   + "VALUES (seq_notice.NEXTVAL, ?, ?, ?, SYSDATE)";

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, notice.getAuthorId());
            pstmt.setString(2, notice.getTitle());
            pstmt.setString(3, notice.getContent());

            int updated = pstmt.executeUpdate();
            if (updated == 0) {
                throw new SQLException("공지 등록 실패");
            }

            // Oracle에서 GENERATED KEYS 비표준 -> MAX로 보정
            DBUtil.close(pstmt);
            pstmt = conn.prepareStatement("SELECT MAX(noticeid) FROM notice");
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            }
            return -1L;
        } finally {
            DBUtil.close(rs);
            DBUtil.close(pstmt);
            DBUtil.close(conn);
        }
    }

    // ====== 매핑 유틸 ======
    private Notice map(ResultSet rs) throws SQLException {
        Notice n = new Notice();
        n.setNoticeId(rs.getLong("noticeid"));
        n.setAuthorId(rs.getString("authorid"));
        n.setTitle(rs.getString("title"));
        n.setContent(rs.getString("content"));

        Timestamp ts = rs.getTimestamp("createdat");
        LocalDateTime ldt = (ts != null ? ts.toLocalDateTime() : null);
        n.setCreatedAt(ldt);

        return n;
    }
}
