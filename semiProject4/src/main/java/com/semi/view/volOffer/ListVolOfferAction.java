package com.semi.view.volOffer;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.common.Page;
import com.semi.common.Search;
import com.semi.domain.User;
import com.semi.framework.Action;
import com.semi.service.user.UserService;
import com.semi.service.user.impl.UserServiceImpl;
import com.semi.service.volOffer.VolOfferService;
import com.semi.service.volOffer.impl.VolOfferServiceImpl;


public class ListVolOfferAction extends Action {

	@Override
	public String execute(HttpServletRequest request,	HttpServletResponse response) throws Exception {

		Search search=new Search();
		
		int currentPage=1;
	    String userId = null ;   // 변수선언시 filed 초기화 해줘야함. 
	    String region = null ;	//초기화하지 않아도 클래스 내에서 사용할 수 있지만, 지역 변수는 반드시 초기화해야함.
	    
        // 세션에서 사용자 정보 및 지역 정보 가져오기
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user"); // 세션에서 User 객체 가져오기
		

		if (user != null) {
		     userId = user.getUserId();   // userId 가져오기
		     region = user.getRegion();
		    System.out.println("111111111111 user: " + userId + " / region : " +  region);
		} else  {
			System.out.println("ListVolOfferAction - session user is null ============");
		}
         //region = user != null ? user.getRegion() : null;  // 삼항연산자 사용자가 로그인되어 있으면 지역 정보 가져오기
        System.out.println("==if 밖에서 로그 확인========= region : " + region + " / userId : "+ userId );

		if(request.getParameter("currentPage") != null){
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		}

		search.setCurrentPage(currentPage);
		search.setSearchCondition(request.getParameter("searchCondition"));
		search.setSearchKeyword(request.getParameter("searchKeyword"));
		
		// web.xml  meta-data 로 부터 상수 추출 
		int pageSize = Integer.parseInt( getServletContext().getInitParameter("pageSize"));
		int pageUnit  =  Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		search.setPageSize(pageSize);
		
		// Business logic 수행
		VolOfferService volOfferService=new VolOfferServiceImpl();
		Map<String , Object> map=volOfferService.getVolOfferList(search, region);
		
		Page resultPage	= 
					new Page( currentPage, ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println("ListVolOfferAction ::"+resultPage);
		
		// Model 과 View 연결
		request.setAttribute("list", map.get("list"));
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("search", search);
		System.out.println("========List: " + map.get("list"));
		System.out.println("========Result Page: " + resultPage.getTotalCount() + ", " + resultPage.getCurrentPage());

		return "forward:/volOffer/listVolOffer.jsp";
	}
}