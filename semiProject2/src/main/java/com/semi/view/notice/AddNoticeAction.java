package com.semi.view.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.framework.Action;
import com.semi.domain.Notice;
import com.semi.domain.User; // ★ 세션에 담긴 로그인 사용자
import com.semi.service.notice.NoticeService;
import com.semi.service.notice.impl.NoticeServiceImpl;

/*
 * 공지 등록 처리 액션
 * - authorId는 클라이언트 파라미터 대신 서버 세션의 로그인 사용자로 확정
 * - 필수값 검증 실패 시: 등록 화면으로 되돌림(에러 메시지)
 * - 성공 시: 방금 등록한 공지 상세로 forward
 */
public class AddNoticeAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 1) 파라미터(제목/내용만 신뢰), 작성자는 세션에서 뽑음
        String title   = nvl(request.getParameter("title"));
        String content = nvl(request.getParameter("content"));

        // 2) 로그인 세션에서 authorId 결정
        String authorId = "";
        HttpSession session = request.getSession(false);
        if (session != null) {
            Object obj = session.getAttribute("user"); // LoginAction에서 setAttribute("user", user)
            if (obj instanceof User) {
                User user = (User)obj;
                if (user.getUserId() != null) {
                    authorId = user.getUserId().trim();
                }
            }
        }

        System.out.println("[AddNoticeAction] /addNotice.do :: authorId=" + authorId
                + ", hasTitle=" + (!title.isEmpty()) + ", hasContent=" + (!content.isEmpty()));

        // 3) 필수값 검증(로그인/입력 확인)
        if (authorId.isEmpty() || title.isEmpty() || content.isEmpty()) {
            request.setAttribute("error", "작성자/제목/내용은 필수입니다. (로그인/세션 또는 입력 확인)");
            return "/notice/addNoticeView.jsp";
        }

        // 4) 도메인 구성
        Notice notice = new Notice();
        notice.setAuthorId(authorId);
        notice.setTitle(title);
        notice.setContent(content);

        try {
            // 5) INSERT
            NoticeService service = new NoticeServiceImpl();
            long newId = service.addNotice(notice);
            System.out.println("[AddNoticeAction] INSERT OK :: newId=" + newId);

            // 6) 상세로 forward (프레임워크의 redirect 접두어 유무와 무관하게 동작)
            Notice created = service.getNotice(newId);
            request.setAttribute("notice", created);
            return "/notice/detailViewNotice.jsp";

        } catch (Exception e) {
            // DB/서비스 오류를 화면에 노출해 원인 파악
            e.printStackTrace();
            request.setAttribute("error", "[등록 실패] " + e.getClass().getSimpleName() + " : "
                    + (e.getMessage() == null ? "no message" : e.getMessage()));
            return "/notice/addNoticeView.jsp";
        }
    }

    private String nvl(String s){ return s==null ? "" : s.trim(); }
}
