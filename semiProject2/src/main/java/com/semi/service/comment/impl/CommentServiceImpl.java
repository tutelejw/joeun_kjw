// src/main/java/com/semi/service/comment/impl/CommentServiceImpl.java
package com.semi.service.comment.impl;

import com.semi.domain.Comment;
import com.semi.service.comment.CommentService;
import com.semi.service.comment.dao.CommentDao;

public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao = new CommentDao();

    @Override
    public long addComment(Comment comment) throws Exception {
        return commentDao.addComment(comment);
    }

    @Override
    public int updateComment(long commentId, String content) throws Exception {
        return commentDao.updateComment(commentId, content);
    }

    @Override
    public int deleteComment(long commentId) throws Exception {
        return commentDao.deleteComment(commentId);
    }
}
