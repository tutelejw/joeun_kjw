package com.semi.view.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.framework.Action;
import com.semi.domain.Notice;
import com.semi.service.notice.NoticeService;
import com.semi.service.notice.impl.NoticeServiceImpl;

public class AddNoticeAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("[AddNoticeAction] /addNotice.do, method=" + request.getMethod());

        // GET이면 등록 화면으로만 포워드 (프로퍼티스에서 동일 URL을 GET/POST 공용으로 쓸 때 대비)
        if (!"POST".equalsIgnoreCase(request.getMethod())) {
            return "forward:/notice/addNoticeView.jsp";
        }

        request.setCharacterEncoding("UTF-8");

        String authorId = nvl(request.getParameter("authorId"));
        String title    = nvl(request.getParameter("title"));
        String content  = nvl(request.getParameter("content"));

        // 필수값 검증
        if (authorId.isEmpty() || title.isEmpty() || content.isEmpty()) {
            request.setAttribute("error", "필수값(authorId, title, content)을 모두 입력하세요.");
            request.setAttribute("authorId", authorId);
            request.setAttribute("title", title);
            request.setAttribute("content", content);
            return "forward:/notice/addNoticeView.jsp";
        }

        // 등록
        Notice notice = new Notice();
        notice.setAuthorId(authorId);
        notice.setTitle(title);
        notice.setContent(content);

        NoticeService noticeService = new NoticeServiceImpl();
        long newId = noticeService.addNotice(notice);
        System.out.println("[AddNoticeAction] new noticeId = " + newId);

        // 목록으로
        return "redirect:/listNotice.do";
    }

    private String nvl(String s){
        return s == null ? "" : s.trim();
    }
}
