package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;


public class UpdatePurchaseAction2 extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		int tranNo=Integer.parseInt(request.getParameter("tranNo"));
		

        // PurchaseVO 객체 생성
        PurchaseVO purchaseVO = new PurchaseVO();
        purchaseVO.setTranNo(tranNo);

        // 구매자 이름, 연락처, 주소 등의 필드를 받아오기
//        purchaseVO.setReceiverName(request.getParameter("receiverName"));
//        purchaseVO.setReceiverPhone(request.getParameter("receiverPhone"));
//        purchaseVO.setReceiverAddr(request.getParameter("receiverAddr"));
//        purchaseVO.setReceiverRequest(request.getParameter("receiverRequest"));
		
		PurchaseService service=new PurchaseServiceImpl();
		service.updatePurchase(purchaseVO);
		
		HttpSession session=request.getSession();
		int sessionId=((PurchaseVO)session.getAttribute("vo")).getTranNo();
	
//		if(sessionId.equals(tranNo)){
		if(sessionId == tranNo){
			session.setAttribute("purchase", purchaseVO);
		}
		
		return "redirect:/getPurchase.do?tranNo="+tranNo;
	}
}