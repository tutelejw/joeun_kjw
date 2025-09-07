package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;


public class UpdatePurchaseViewAction extends Action{

	@Override
	public String execute(	HttpServletRequest request, HttpServletResponse response) throws Exception {
		int tranNo=Integer.parseInt(request.getParameter("tranNo"));
//		String userId=request.getParameter("userId");
		
		PurchaseService service=new PurchaseServiceImpl();
		PurchaseVO vo=service.getPurchase(tranNo);

	    if (vo == null) {
	        System.err.println("해당 tranNo에 대한 Purchase 정보 없음: " + tranNo);
	        throw new Exception("해당 거래 정보를 찾을 수 없습니다.");
	    } else {
	        System.out.println("Purchase 정보 조회 성공: " + vo);
	    }
		
		request.setAttribute("vo", vo);
		
		return "forward:/purchase/updatePurchase.jsp";
	}
}
