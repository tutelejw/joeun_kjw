// src/main/java/com/semi/service/comment/CommentService.java
package com.semi.service.comment;

import com.semi.domain.Comment;

/**
 * 댓글 서비스 인터페이스
 */
public interface CommentService {

    long addComment(Comment comment) throws Exception;

    int updateComment(long commentId, String content) throws Exception;

    int deleteComment(long commentId) throws Exception;
}
