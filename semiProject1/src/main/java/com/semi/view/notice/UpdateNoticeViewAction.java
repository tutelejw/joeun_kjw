// src/com/semi/view/notice/UpdateNoticeViewAction.java
package com.semi.view.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.semi.domain.Notice;
import com.semi.framework.Action;
import com.semi.service.notice.NoticeService;
import com.semi.service.notice.impl.NoticeServiceImpl;

public class UpdateNoticeViewAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        long noticeId = Long.parseLong(request.getParameter("noticeId"));
        NoticeService service = new NoticeServiceImpl();
        Notice notice = service.getNotice(noticeId);
        request.setAttribute("notice", notice);
        return "/notice/updateNoticeView.jsp";
    }
}