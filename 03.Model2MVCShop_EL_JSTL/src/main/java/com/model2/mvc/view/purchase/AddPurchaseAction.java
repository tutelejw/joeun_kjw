package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class AddPurchaseAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        // 1. 구매자 정보 (세션에서 가져옴)
        User buyer = (User) request.getSession().getAttribute("user");
        
        // 2. 상품 정보 (hidden input으로 받음)
        Product purchaseProd = new Product();
        purchaseProd.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
        
        // 3. Purchase 객체 생성 및 정보 바인딩
        Purchase purchase = new Purchase();
        purchase.setBuyer(buyer);
        purchase.setPurchaseProd(purchaseProd);
        purchase.setPaymentOption(request.getParameter("paymentOption"));
        purchase.setReceiverName(request.getParameter("receiverName"));
        purchase.setReceiverPhone(request.getParameter("receiverPhone"));
        purchase.setDivyAddr(request.getParameter("divyAddr"));
        purchase.setDivyRequest(request.getParameter("divyRequest"));
        purchase.setDivyDate(request.getParameter("divyDate"));
        
        // 4. 서비스 호출
        PurchaseService service = new PurchaseServiceImpl();
        service.addPurchase(purchase);
        
        request.setAttribute("purchase", purchase);
        
        return "forward:/purchase/addPurchase.jsp";
    }
}