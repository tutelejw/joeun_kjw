package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;


public class UpdatePurchaseAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		int tranNo=Integer.parseInt(request.getParameter("tranNo"));
//		String tranNo=(String)request.getParameter("tranNo");
		
		PurchaseVO purchaseVO=new PurchaseVO();
		purchaseVO.setTranNo(tranNo);
		purchaseVO.setPurchaseName(request.getParameter("purchaseName"));
		purchaseVO.setAddr(request.getParameter("addr"));
		purchaseVO.setPhone(request.getParameter("phone"));
		purchaseVO.setEmail(request.getParameter("email"));
		
		PurchaseService service=new PurchaseServiceImpl();
		service.updatePurchase(purchaseVO);
		
		HttpSession session=request.getSession();
		String sessionId=((PurchaseVO)session.getAttribute("purchase")).getTranNo();
	
		if(sessionId.equals(purchaseId)){
			session.setAttribute("purchase", purchaseVO);
		}
		
		return "redirect:/getPurchase.do?tranNo="+tranNo;
	}
}