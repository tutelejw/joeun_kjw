package com.semi.view.volOffer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.domain.User;
import com.semi.domain.VolOffer;
import com.semi.framework.Action;
import com.semi.service.volOffer.VolOfferService;
import com.semi.service.volOffer.impl.VolOfferServiceImpl;


public class DetailVolOfferAction_org extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 세션에서 userId를 가져오는 코드
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user");
	    String userId = null;
	    if (user != null) {
	        userId = user.getUserId();
	        System.out.println("로그인된 사용자 ID (userId): " + userId); // userId 로그 출력
	    } else {
	        System.out.println("로그인된 사용자 ID (userId): 없음-로그인되지 않은 상태 입니다.");
	    }
		
		String postIdStr = request.getParameter("postId");// 파라미터에서 postId를 가져오는 코드
		Long postId = Long.parseLong(postIdStr);// postId 값을 Long 타입으로 변환

		VolOfferService volOfferService=new VolOfferServiceImpl();
		VolOffer volOffer=volOfferService.getVolOffer(postId);

	    // 세션에 VolOffer 저장
	    request.getSession().setAttribute("volOffer", volOffer);
	   
	    // 로그인된 사용자 정보가 있으면 세션에 저장
	    if (userId != null) {
	        session.setAttribute("userId", userId);
	    }
	    
	    //request.setAttribute("volOffer", volOffer);
		
		return "forward:/volOffer/detailVolOffer.jsp";
	}
}