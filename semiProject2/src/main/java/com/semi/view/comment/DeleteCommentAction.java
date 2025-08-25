package com.semi.view.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.framework.Action;
import com.semi.service.comment.CommentService;
import com.semi.service.comment.impl.CommentServiceImpl;

public class DeleteCommentAction extends Action {

    private final CommentService commentService = new CommentServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        long commentId   = Long.parseLong(request.getParameter("commentId"));
        long volunteerId = Long.parseLong(request.getParameter("volunteerId"));

        // TODO: 권한 확인(댓글 작성자 == 세션 사용자)
        commentService.deleteComment(commentId);

        String qs = buildContextQS(request, "region","category","page","pageSize");
        return "redirect:/detailVolRequest.do?volunteerId=" + volunteerId + qs + "#comments";
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
