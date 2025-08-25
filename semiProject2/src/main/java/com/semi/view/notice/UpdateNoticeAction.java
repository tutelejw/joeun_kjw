// src/com/semi/view/notice/UpdateNoticeAction.java
package com.semi.view.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.semi.domain.Notice;
import com.semi.framework.Action;
import com.semi.service.notice.NoticeService;
import com.semi.service.notice.impl.NoticeServiceImpl;

public class UpdateNoticeAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        long noticeId = Long.parseLong(request.getParameter("noticeId"));
        Notice notice = new Notice();
        notice.setNoticeId(noticeId);
        notice.setTitle(request.getParameter("title"));
        notice.setContent(request.getParameter("content"));
        NoticeService service = new NoticeServiceImpl();
        service.updateNotice(notice);
        response.sendRedirect(request.getContextPath() + "/getNotice.do?noticeId=" + noticeId);
        return null;
    }
}