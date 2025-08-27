package com.semi.view.volOffer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.domain.VolOffer;
import com.semi.framework.Action;
import com.semi.service.volOffer.VolOfferService;
import com.semi.service.volOffer.impl.VolOfferServiceImpl;


public class UpdateVolOfferAction2 extends Action {

	@Override
	public String execute(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		
		//String postId=(String)request.getParameter("postId");
		
		
        String postId = request.getParameter("postId");
        String title = request.getParameter("title");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String content = request.getParameter("content");
		
        // T를 없애고 공백으로 대체
//        startTime = startTime.replace("T", " ");
//        endTime = endTime.replace("T", " ");
        
        // 디버깅 로그 찍기
        System.out.println("디버깅 - postId: " + postId);
        System.out.println("디버깅 - title: " + title);
        System.out.println("디버깅 - startTime: " + startTime);
        System.out.println("디버깅 - endTime: " + endTime);
        System.out.println("디버깅 - content: " + content);
        
        VolOffer volOffer=new VolOffer();
		//volOffer.setPostId(Long.valueOf(request.getParameter("postId")));  postId는 SEQ DB에서 자동 생성
        volOffer.setPostId(Long.valueOf(request.getParameter("postId")));  // 게시물 번호
        volOffer.setTitle(title);     // 제목
	    // startTime, endTime을 LocalDateTime으로 변환 (null 체크 없음
//	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm:ss");   
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
	    volOffer.setStartTime(LocalDateTime.parse(request.getParameter("startTime"), formatter));
	    volOffer.setEndTime(LocalDateTime.parse(request.getParameter("endTime"), formatter));
//        volOffer.setStartTime(startTime);  // 시작시간
//        volOffer.setEndTime(endTime);      // 종료시간
        volOffer.setContent(content);      // 내용
        
        // 디버깅 로그 - 업데이트된 VolOffer 객체 확인
        System.out.println("디버깅 - Updated VolOffer - execute: " + volOffer);
        
        // 서비스 객체를 통해 DB 업데이트
        VolOfferService volOfferService = new VolOfferServiceImpl();
        volOfferService.updateVolOffer(volOffer);
		
        // 세션에 저장된 volOffer와 비교하여 수정된 volOffer가 동일한지 확인
        HttpSession session = request.getSession();
        Long sessionId = (Long) session.getAttribute("volOfferId");  // 세션에서 가져온 게시물 ID

        // 수정된 게시물이 세션의 게시물과 동일하다면 세션 업데이트
        if (sessionId != null && sessionId.equals(postId)) {
            session.setAttribute("volOffer", volOffer);
        }
		
		return "redirect:/detailVolOffer.do?postId="+postId;
	}
}