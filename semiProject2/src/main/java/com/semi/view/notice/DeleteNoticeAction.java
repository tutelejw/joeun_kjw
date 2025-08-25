// src/com/semi/view/notice/DeleteNoticeAction.java
package com.semi.view.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.semi.framework.Action;
import com.semi.service.notice.NoticeService;
import com.semi.service.notice.impl.NoticeServiceImpl;

public class DeleteNoticeAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        long noticeId = Long.parseLong(request.getParameter("noticeId"));
        NoticeService service = new NoticeServiceImpl();
        service.deleteNotice(noticeId);
        response.sendRedirect(request.getContextPath() + "/listNotice.do");
        return null;
    }
}