package com.semi.view.volOffer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.domain.VolOffer;
import com.semi.framework.Action;
import com.semi.service.volOffer.VolOfferService;
import com.semi.service.volOffer.impl.VolOfferServiceImpl;


public class DetailVolOfferAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String postIdStr = request.getParameter("postId");
		Long postId = Long.parseLong(postIdStr);
		
		
		VolOfferService volOfferService=new VolOfferServiceImpl();
		VolOffer volOffer=volOfferService.getVolOffer(postId);

	    // 세션에 VolOffer 저장
	    request.getSession().setAttribute("volOffer", volOffer);
	    
		//request.setAttribute("volOffer", volOffer);
		
		return "forward:/volOffer/detailVolOffer.jsp";
	}
}