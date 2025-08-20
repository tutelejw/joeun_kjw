package com.semi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.semi.dao.VolOfferDAO;
import com.semi.domain.VolOffer;

public class VolOfferService {

    private VolOfferDAO volOfferDAO = new VolOfferDAO();

    /**
     * 봉사 제공 게시글 등록 서비스
     */
    public void addVolOffer(HttpServletRequest request) {
        VolOffer vo = new VolOffer();
        vo.setTitle(request.getParameter("title"));
        vo.setAuthorId(request.getParameter("authorId"));
        vo.setPhone(request.getParameter("phone"));
        vo.setContent(request.getParameter("content"));
        vo.setRegion(request.getParameter("region"));
        vo.setCategory(request.getParameter("category"));
        vo.setProcessStatus(request.getParameter("processStatus"));

        // 날짜 형식 변환
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdfDateOnly = new SimpleDateFormat("yyyy-MM-dd");
        try {
            vo.setStartTime(sdf.parse(request.getParameter("startTime")));
            vo.setEndTime(sdf.parse(request.getParameter("endTime")));
            vo.setOfferDate(sdfDateOnly.parse(request.getParameter("offerDate")));
        } catch (ParseException e) {
            e.printStackTrace();
            // 날짜 형식 변환 실패 시 예외 처리
        }

        volOfferDAO.addVolOffer(vo);
    }

    /**
     * 봉사 제공 게시글 목록 조회 서비스
     */
    public List<VolOffer> getVolOfferList() {
        return volOfferDAO.getVolOfferList();
    }
}