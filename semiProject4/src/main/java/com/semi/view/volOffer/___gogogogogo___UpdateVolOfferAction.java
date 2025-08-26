package com.semi.view.volOffer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.domain.VolOffer;
import com.semi.framework.Action;
import com.semi.service.volOffer.VolOfferService;
import com.semi.service.volOffer.impl.VolOfferServiceImpl;


public class ___gogogogogo___UpdateVolOfferAction extends Action {

	@Override
	public String execute(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		
		String postId=(String)request.getParameter("postId");
		
		VolOffer volOffer=new VolOffer();
		volOffer.setVolOfferId(volPostId);
		volOffer.setVolOfferName(request.getParameter("volOfferName"));
		volOffer.setAddr(request.getParameter("addr"));
		volOffer.setPhone(request.getParameter("phone"));
		volOffer.setEmail(request.getParameter("email"));
		
		VolOfferService volOfferService=new VolOfferServiceImpl();
		volOfferService.updateVolOffer(volOffer);
		
		HttpSession session=request.getSession();
		Long sessionId=((VolOffer)session.getAttribute("volOffer")).getPostId();
		
		if(sessionId.equals(postId)){
			session.setAttribute("volOffer", volOffer);
		}
		
		return "redirect:/getVolOffer.do?postId="+postId;
	}
}