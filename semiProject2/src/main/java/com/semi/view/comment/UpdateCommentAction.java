package com.semi.view.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.framework.Action;
import com.semi.service.comment.CommentService;
import com.semi.service.comment.impl.CommentServiceImpl;

public class UpdateCommentAction extends Action {

    private final CommentService commentService = new CommentServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        long commentId   = Long.parseLong(request.getParameter("commentId"));
        long volunteerId = Long.parseLong(request.getParameter("volunteerId"));
        String content   = nvl(request.getParameter("content"));

        // TODO: 권한 확인(댓글 작성자 == 세션 사용자) — CommentDao에 조회 API 추가 후 검증 가능
        commentService.updateComment(commentId, content);

        String qs = buildContextQS(request, "region","category","page","pageSize");
        return "redirect:/detailVolRequest.do?volunteerId=" + volunteerId + qs + "#comments";
    }

    private static String nvl(String s){ return s==null? "" : s.trim(); }
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
