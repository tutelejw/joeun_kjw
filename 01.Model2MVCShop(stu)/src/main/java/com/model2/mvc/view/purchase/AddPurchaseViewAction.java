package com.model2.mvc.view.purchase;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;


public class AddPurchaseViewAction extends Action {

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 1. prodNo 파라미터 받기
        String prodNoStr = request.getParameter("prodNo");

        System.out.println("==== [디버깅] AddPurchaseViewAction 진입 ====");
        System.out.println("[파라미터] prodNo : " + prodNoStr);
        System.out.println("===========================================");

        if (prodNoStr == null || prodNoStr.trim().isEmpty()) {
            throw new IllegalArgumentException("[에러] 상품 번호(prodNo)가 전달되지 않았습니다.");
        }

        int prodNo = 0;
        try {
            prodNo = Integer.parseInt(prodNoStr);
        } catch (NumberFormatException e) {
            System.out.println("[에러] prodNo 파싱 실패: " + prodNoStr);
            e.printStackTrace();
            throw e;
        }

        // 2. 서비스 호출해서 DB에서 상품 정보 조회
        ProductService service = new ProductServiceImpl();
        ProductVO productVO = service.getProduct(prodNo);

        if (productVO == null) {
            throw new IllegalStateException("[에러] 해당 상품을 찾을 수 없습니다. prodNo=" + prodNo);
        }

        // 3. 디버깅 출력
        System.out.println("==== [디버깅] 조회된 ProductVO 정보 ====");
        System.out.println(productVO);
        System.out.println("===================================");

        // 4. request scope에 저장해서 JSP에서 사용할 수 있도록 전달
        request.setAttribute("vo", productVO);
//       // 로그인 성공 후 세션 저장
//        UserVO user = userService.getUser(userId);
//        session.setAttribute("user", user);

        // 5. forward 방식으로 JSP 이동
        return "forward:/purchase/addPurchaseView.jsp";
		//return "redirect:/product/addPurchaseView.jsp";
		//return "redirect:/product/listProduct.jsp";
	}
}