package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;


public class GetProductAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		int prodNo=Integer.parseInt(request.getParameter("prodNo"));
		String menu = request.getParameter("menu");  // menu 파라미터 확인
		
		
		ProductService service=new ProductServiceImpl();
		ProductVO vo=service.getProduct(prodNo);
		
		request.setAttribute("vo", vo);
		System.out.println("test");
		// if로 manege와 user 분기로 updateProduct.jsp로 분기해야 하는건가? --- 할일
		//return "forward:/product/readProduct.jsp";  //상품수정 jsp 이동
		//return "forward:/product/getProduct.jsp";  // 상품정보 조회 이동
		//8/9 14시 일단 상품 정보 조회는 됨. 상품수정 분기 확인해야함.
		
		// 분기 처리
		
	    if ("manage".equals(menu)) {
	    	System.out.println("==== GetProductAction - menu = manage " + menu);
	        return "forward:/product/readProduct.jsp";  // 관리자용 뷰
	        
	    } else {
	    	System.out.println("======= GetProductAction - menu = search " + menu);
	        return "forward:/product/getProduct.jsp";   // 일반 사용자용 상품 조회
	    }
	    
	    
	}
}