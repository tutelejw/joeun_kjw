// src/com/semi/view/notice/DetailNoticeAction.java
package com.semi.view.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.semi.domain.Notice;
import com.semi.framework.Action;
import com.semi.service.notice.NoticeService;
import com.semi.service.notice.impl.NoticeServiceImpl;

public class DetailNoticeAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String idParam = request.getParameter("noticeId");
        if (idParam == null || idParam.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "noticeId가 필요합니다.");
            return null;
        }
        
        long noticeId = Long.parseLong(idParam);
        
        NoticeService service = new NoticeServiceImpl();
        Notice notice = service.getNotice(noticeId);
        
        request.setAttribute("notice", notice);
        
        return "/notice/detailViewNotice.jsp";
    }
}