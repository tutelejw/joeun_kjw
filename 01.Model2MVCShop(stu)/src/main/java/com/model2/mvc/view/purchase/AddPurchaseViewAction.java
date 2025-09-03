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

	    // 1. request 파라미터 값 먼저 디버깅
		String prodNo = request.getParameter("prodNo");
	    String prodName = request.getParameter("prodName");
	    String fileName = request.getParameter("fileName");
	    String prodDetail = request.getParameter("prodDetail");
	    String manuDate = request.getParameter("manuDate");
	    String priceStr = request.getParameter("price");
	    String regDateStr = request.getParameter("regDate");

	    System.out.println("==== [요청 파라미터 디버깅] ====");
	    System.out.println("prodNo   : " + prodNo);
	    System.out.println("prodName   : " + prodName);
	    System.out.println("fileName   : " + fileName);
	    System.out.println("prodDetail : " + prodDetail);
	    System.out.println("manuDate   : " + manuDate);
	    System.out.println("price      : " + priceStr);
	    System.out.println("regDate    : " + regDateStr);
	    System.out.println("================================");

	    // 2. ProductVO 생성 및 값 세팅
	    ProductVO productVO = new ProductVO();
	    productVO.setProdNo(Integer.parseInt(prodNo));
	    productVO.setProdName(prodName);
	    productVO.setFileName(fileName);
	    productVO.setProdDetail(prodDetail);
	    productVO.setManuDate(manuDate);

	    // 숫자 파싱 처리
	    if (priceStr != null && !priceStr.isEmpty()) {
	        productVO.setPrice(Integer.parseInt(priceStr));
	    } else {
	        System.out.println("[경고] 가격 정보가 없습니다. priceStr: " + priceStr);
	    }

	    // 날짜 파싱 처리
	    if (regDateStr != null && !regDateStr.isEmpty()) {
	        try {
	            java.sql.Date sqlDate = java.sql.Date.valueOf(regDateStr);  // 문자열 → java.sql.Date
	            productVO.setRegDate(sqlDate);
	        } catch (IllegalArgumentException e) {
	            System.out.println("[에러] regDate 형식이 잘못되었습니다: " + regDateStr);
	            e.printStackTrace();
	        }
	    }

	    // 디버깅 출력
	    System.out.println("[디버깅] ProductVO 상태 ↓↓↓↓↓");
	    System.out.println(productVO);

	    // 3. 서비스 호출
	    ProductService service = new ProductServiceImpl();
	    service.addProduct(productVO);

	    // 4. 이동
	    return "forward:/purchase/addPurchaseView.jsp";
		//return "redirect:/product/addPurchaseView.jsp";
		//return "redirect:/product/listProduct.jsp";
	}
}