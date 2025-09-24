package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class AddPurchaseAction extends Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

	    // 1. 구매자 정보는 세션에서
	    HttpSession session = request.getSession();
	    User buyer = (User) session.getAttribute("user");

	    System.out.println("[디버그] 구매자 ID : " + buyer.getUserId());
	    System.out.println("[디버그] 구매자 이름 : " + buyer.getUserName());
	    System.out.println("[디버그] 구매자 전화번호 : " + buyer.getPhone());

	    // 2. 상품 정보는 request에서
	    int prodNo = Integer.parseInt(request.getParameter("prodNo"));
	    Product product = new Product();
	    product.setProdNo(prodNo);

	    System.out.println("[디버그] 상품 번호 : " + prodNo);

	    // 3. 파라미터로부터 구매 정보 수집
	    String paymentOption = request.getParameter("paymentOption");
	    String receiverName = request.getParameter("userName");
	    String receiverPhone = request.getParameter("phone");
	    String receiverAddr = request.getParameter("receiverAddr");
	    String receiverRequest = request.getParameter("receiverRequest");
	    String receiverDate = request.getParameter("receiverDate");

	    System.out.println("[디버그] 결제 방식 : " + paymentOption);
	    System.out.println("[디버그] 수령인 이름 : " + receiverName);
	    System.out.println("[디버그] 수령인 전화번호 : " + receiverPhone);
	    System.out.println("[디버그] 수령인 주소 : " + receiverAddr);
	    System.out.println("[디버그] 배송 요청사항 : " + receiverRequest);
	    System.out.println("[디버그] 배송 희망일 : " + receiverDate);

	    Purchase purchase = new Purchase();
	    purchase.setBuyer(buyer);
	    purchase.setPurchaseProd(product);
	    purchase.setPaymentOption(paymentOption);
	    purchase.setReceiverName(receiverName);
	    purchase.setReceiverPhone(receiverPhone);
	    purchase.setDivyAddr(receiverAddr);
	    purchase.setDivyRequest(receiverRequest);
	    purchase.setTranCode("1"); // 기본 구매 상태

	    // 주문일 현재일자
	    purchase.setOrderDate(new java.sql.Date(System.currentTimeMillis()));

	    if (receiverDate != null && !receiverDate.isEmpty()) {
	        purchase.setDivyDate(receiverDate); // yyyy-MM-dd
	    }

	    // 4. DB에 저장
	    PurchaseService service = new PurchaseServiceImpl();
	    service.addPurchase(purchase);

	    // 5. 다음 페이지로 이동
	    request.setAttribute("purchase", purchase);
	    return "redirect:/listProduct.do";
	}

}