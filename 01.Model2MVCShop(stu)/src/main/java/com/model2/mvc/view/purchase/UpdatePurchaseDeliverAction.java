package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class UpdatePurchaseDeliverAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            int prodNo = Integer.parseInt(request.getParameter("prodNo"));

            PurchaseService purchaseService = new PurchaseServiceImpl();
            purchaseService.updatePurchaseDelivery(prodNo);

            // 성공 후 리다이렉트
            return "redirect:/listProduct.do?menu=manage";

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMsg", "배송 상태 변경 중 오류 발생");
            return "forward:/error.jsp";
        }
    }
}
