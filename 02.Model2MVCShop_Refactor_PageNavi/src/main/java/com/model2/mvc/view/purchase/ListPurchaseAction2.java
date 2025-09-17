package com.model2.mvc.view.purchase;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;


public class ListPurchaseAction2 extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		Search search=new Search();
		
		int page=1;
		if(request.getParameter("currentPage") != null)
			page=Integer.parseInt(request.getParameter("currentPage"));
		
		search.setCurrentPage(page);
		search.setSearchCondition(request.getParameter("searchCondition"));
		search.setSearchKeyword(request.getParameter("searchKeyword"));
		
		String pageUnit=getServletContext().getInitParameter("pageSize");
		search.setPageSize(Integer.parseInt(pageUnit));
		
		PurchaseService service=new PurchaseServiceImpl();
		HashMap<String,Object> map=service.getPurchaseList(search);
		System.out.println("map : " + map);
		System.out.println("search : " + search);
		request.setAttribute("map", map);
		request.setAttribute("search", search);
		
		//return "forward:/purchase/readPurchase.jsp";
		return "forward:/purchase/listPurchase.jsp";

	}
}