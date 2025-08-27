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

public class UpdateVolOfferAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 요청 파라미터 받기
        String postId = request.getParameter("postId");
        String title = request.getParameter("title");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String content = request.getParameter("content");
        
        // 추가된 파라미터들
        String authorId = request.getParameter("authorId");  // 작성자 ID
        String phone = request.getParameter("phone");        // 전화번호
        String region = request.getParameter("region");      // 지역
        String category = request.getParameter("category");  // 카테고리
        
        // 디버깅 로그 찍기
        System.out.println("디버깅 - postId: " + postId);
        System.out.println("디버깅 - title: " + title);
        System.out.println("디버깅 - startTime: " + startTime);
        System.out.println("디버깅 - endTime: " + endTime);
        System.out.println("디버깅 - content: " + content);
        System.out.println("디버깅 - authorId: " + authorId);
        System.out.println("디버깅 - phone: " + phone);
        System.out.println("디버깅 - region: " + region);
        System.out.println("디버깅 - category: " + category);
        
        // VolOffer 객체 생성
        VolOffer volOffer = new VolOffer();
        volOffer.setPostId(Long.valueOf(postId));  // 게시물 번호
        volOffer.setTitle(title);                  // 제목
        volOffer.setContent(content);              // 내용
        volOffer.setAuthorId(authorId);            // 작성자 ID
        volOffer.setPhone(phone);                  // 전화번호
        volOffer.setRegion(region);                // 지역
        volOffer.setCategory(category);            // 카테고리
        volOffer.setOfferFlag("o");            // 카테고리
        
        // startTime, endTime을 LocalDateTime으로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        volOffer.setStartTime(LocalDateTime.parse(startTime, formatter));  // 시작시간
        volOffer.setEndTime(LocalDateTime.parse(endTime, formatter));      // 종료시간
        
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

        return "redirect:/detailVolOffer.do?postId=" + postId;
    }
}
