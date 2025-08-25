// src/main/java/com/semi/service/comment/dao/CommentDao.java
package com.semi.service.comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.semi.common.util.DBUtil;
import com.semi.domain.Comment;

/**
 * 댓글 DAO (구체 클래스)
 * - commentid: seq_comments.NEXTVAL
 * - createdat: SYSDATE
 */
public class CommentDao {

    /** 등록(생성된 commentId 반환) */
    public long addComment(Comment c) throws Exception {
        final String insert =
            "INSERT INTO comments (commentid, authorid, content, volunteerid, createdat) " +
            "VALUES (seq_comments.NEXTVAL, ?, ?, ?, SYSDATE)";
        final String fetchId = "SELECT seq_comments.CURRVAL FROM dual";

        try (Connection con = DBUtil.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(insert)) {
                int idx = 1;
                ps.setString(idx++, c.getAuthorId()); // DB authorid
                ps.setString(idx++, c.getContent());
                ps.setLong(idx,   c.getPostId());     // DB volunteerid
                ps.executeUpdate();
            }
            try (PreparedStatement ps2 = con.prepareStatement(fetchId);
                 ResultSet rs = ps2.executeQuery()) {
                return rs.next() ? rs.getLong(1) : 0L;
            }
        }
    }

    /** 내용 수정 */
    public int updateComment(long commentId, String content) throws Exception {
        final String sql = "UPDATE comments SET content = ? WHERE commentid = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, content);
            ps.setLong(2, commentId);
            return ps.executeUpdate();
        }
    }

    /** 삭제 */
    public int deleteComment(long commentId) throws Exception {
        final String sql = "DELETE FROM comments WHERE commentid = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, commentId);
            return ps.executeUpdate();
        }
    }
}
