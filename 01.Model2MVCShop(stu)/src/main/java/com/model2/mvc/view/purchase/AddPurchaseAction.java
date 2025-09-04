package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;


public class AddPurchaseAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 1. 구매자 정보는 세션에서
        HttpSession session = request.getSession();
        UserVO buyer = (UserVO) session.getAttribute("user");

        // 2. 상품 정보는 request에서
        int prodNo = Integer.parseInt(request.getParameter("prodNo"));
        ProductVO product = new ProductVO();
        product.setProdNo(prodNo);

        // 3. 파라미터로부터 구매 정보 수집
        PurchaseVO purchase = new PurchaseVO();
        purchase.setBuyer(buyer);
        purchase.setPurchaseProd(product);
        purchase.setPaymentOption(request.getParameter("paymentOption"));
        purchase.setReceiverName(request.getParameter("receiverName"));
        purchase.setReceiverPhone(request.getParameter("receiverPhone"));
        purchase.setDivyAddr(request.getParameter("receiverAddr"));
        purchase.setDivyRequest(request.getParameter("receiverRequest"));
        purchase.setTranCode("1"); // 기본 구매 상태 (예: '1' = 주문 접수)

        // 주문일은 현재일자
        purchase.setOrderDate(new java.sql.Date(System.currentTimeMillis()));

        // 배송 희망일자 (String → Date)
        String dlvyDateStr = request.getParameter("receiverDate");
        if (dlvyDateStr != null && !dlvyDateStr.isEmpty()) {
            purchase.setDivyDate(dlvyDateStr);  // "yyyy-MM-dd" 형식
        }

        // 4. DB에 저장
        PurchaseService service = new PurchaseServiceImpl();
        service.addPurchase(purchase);
		

        // 5. 다음 페이지로 이동
        request.setAttribute("purchase", purchase);
//        return "forward:/purchase/addPurchaseResult.jsp";
		return "redirect:/product/listProduct.jsp";
    }
}
