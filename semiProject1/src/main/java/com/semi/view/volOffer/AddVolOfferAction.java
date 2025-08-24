package com.semi.view.volOffer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.domain.VolOffer;
import com.semi.framework.Action;
import com.semi.service.volOffer.VolOfferService;
import com.semi.service.volOffer.impl.VolOfferServiceImpl;


public class AddVolOfferAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		VolOffer volOffer=new VolOffer();
		//volOffer.setPostId(Long.valueOf(request.getParameter("postId")));  postId는 SEQ DB에서 자동 생성
		volOffer.setAuthorId(request.getParameter("authorId"));
		volOffer.setTitle(request.getParameter("title"));
		volOffer.setContent(request.getParameter("content"));
		volOffer.setPhone(request.getParameter("phone"));
		volOffer.setRegion(request.getParameter("region"));
		volOffer.setCategory(request.getParameter("category"));
		
	    // startTime, endTime을 LocalDateTime으로 변환 (null 체크 없음
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");   
	    volOffer.setStartTime(LocalDateTime.parse(request.getParameter("startTime"), formatter));
	    volOffer.setEndTime(LocalDateTime.parse(request.getParameter("endTime"), formatter));
	    
		volOffer.setStatus(request.getParameter("status"));
		volOffer.setOfferFlag(request.getParameter("offerFlag"));
		//volOffer.setCreatedAt(request.getParameter("createdAt"));  //생성일자는 입력시간으로 자동입력되게 Dao 에서 진행
		
		System.out.println("AddVolOfferAction ::"+volOffer);
		
		VolOfferService volOfferService=new VolOfferServiceImpl();
		volOfferService.addVolOffer(volOffer);
		
		return "redirect:/volOffer/listVolOffer.jsp";
	}
}
