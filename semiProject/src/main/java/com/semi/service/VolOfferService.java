package com.semi.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.semi.dao.VolOfferDAO;
import com.semi.domain.VolOffer;

public class VolOfferService {

    private VolOfferDAO volOfferDAO = new VolOfferDAO();

    public void addVolOffer(HttpServletRequest request) {
        VolOffer vo = new VolOffer();
        vo.setTitle(request.getParameter("title"));
        vo.setAuthorId(request.getParameter("authorId"));
        vo.setPhone(request.getParameter("phone"));
        vo.setContent(request.getParameter("content"));
        vo.setRegion(request.getParameter("region"));
        vo.setCategory(request.getParameter("category"));
        vo.setProcessStatus(request.getParameter("processStatus"));

        // 날짜/시간 형식에 맞는 포맷터를 정의합니다.
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            // 문자열을 LocalDateTime과 LocalDate로 직접 파싱합니다.
            vo.setStartTime(LocalDateTime.parse(request.getParameter("startTime"), dateTimeFormatter));
            vo.setEndTime(LocalDateTime.parse(request.getParameter("endTime"), dateTimeFormatter));
            vo.setOfferDate(LocalDate.parse(request.getParameter("offerDate"), dateFormatter));
        } catch (Exception e) {
            e.printStackTrace();
            // 날짜 형식 변환 실패 시 예외 처리
        }

        volOfferDAO.addVolOffer(vo);
    }

    public List<VolOffer> getVolOfferList() {
    	// --- 로그 추가 ---
        System.out.println("➡️ VolOfferService.getVolOfferList() 메소드가 호출되었습니다.");

        return volOfferDAO.getVolOfferList();
    }
}