package com.semi.view.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.domain.Comment;
import com.semi.framework.Action;
import com.semi.service.comment.CommentService;
import com.semi.service.comment.impl.CommentServiceImpl;

public class AddCommentAction extends Action {

    private final CommentService commentService = new CommentServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String authorId = getLoginUserId(request);
        if (authorId == null || authorId.isEmpty()) {
            throw new IllegalArgumentException("로그인 정보가 없습니다.(authorId)");
        }

        long volunteerId = Long.parseLong(request.getParameter("volunteerId"));
        String content   = nvl(request.getParameter("content"));
        if (content.isEmpty()) {
            throw new IllegalArgumentException("댓글 내용을 입력하세요.");
        }

        Comment c = new Comment();
        c.setAuthorId(authorId);
        c.setPostId(volunteerId);
        c.setContent(content);

        commentService.addComment(c);

        String qs = buildContextQS(request, "region","category","page","pageSize");
        return "redirect:/detailVolRequest.do?volunteerId=" + volunteerId + qs + "#comments";
    }

    private static String nvl(String s){ return s==null? "" : s.trim(); }
    private static String getLoginUserId(HttpServletRequest req){
        if(req.getSession(false)!=null){
            Object v1=req.getSession(false).getAttribute("userId"); if(v1!=null) return String.valueOf(v1);
            Object v2=req.getSession(false).getAttribute("loginUserId"); if(v2!=null) return String.valueOf(v2);
        }
        String p=req.getParameter("authorId"); return p==null?null:p.trim();
    }
    private static String buildContextQS(HttpServletRequest req, String... keys){
        StringBuilder sb = new StringBuilder();
        for(String k: keys){
            String v=req.getParameter(k);
            if(v!=null && !v.trim().isEmpty()){
                sb.append("&").append(k).append("=").append(url(v.trim()));
            }
        }
        return sb.toString();
    }
    private static String url(String s){
        try{ return java.net.URLEncoder.encode(s,"UTF-8"); }catch(Exception e){ return s; }
    }
}
