package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;



public class PurchaseDAO {
	
	public PurchaseDAO(){
	}
	public void insertPurchase(PurchaseVO purchase) throws Exception {
		Connection con = DBUtil.getConnection();
		
	        String sql = "	            INSERT INTO transaction (\r\n"
	        		+ "	                tran_no, prod_no, buyer_id, payment_option, receiver_name,\r\n"
	        		+ "	                receiver_phone, demailaddr, dlvy_request, tran_status_code,\r\n"
	        		+ "	                order_date, dlvy_date\r\n"
	        		+ "	            ) VALUES (\r\n"
	        		+ "	                seq_transaction_tran_no.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?\r\n"
	        		+ "	            )";
System.out.println("insertPurchase SQL : " + sql);
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.setInt(1, purchase.getPurchaseProd().getProdNo());
	        stmt.setString(2, purchase.getBuyer().getUserId());
	        stmt.setString(3, purchase.getPaymentOption());
	        stmt.setString(4, purchase.getReceiverName());
	        stmt.setString(5, purchase.getReceiverPhone());
	        stmt.setString(6, purchase.getDivyAddr());
	        stmt.setString(7, purchase.getDivyRequest());
	        stmt.setString(8, purchase.getTranCode());
	        stmt.setDate(9, purchase.getOrderDate());

	        // 배송희망일자(String → java.sql.Date 변환)
	        java.sql.Date dlvyDate = null;
	        if (purchase.getDivyDate() != null && !purchase.getDivyDate().isEmpty()) {
	            dlvyDate = java.sql.Date.valueOf(purchase.getDivyDate()); // "yyyy-MM-dd" 형식일 때
	        }

	        stmt.setDate(10, dlvyDate);
			stmt.executeUpdate();
			con.close();
	}

	public PurchaseVO findPurchase(int tranNo) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "	select \r\n"
				+ "		prod_no, buyer_id, PAYMENT_OPTION, receiver_name, receiver_phone, DEMAILADDR, dlvy_request,DLVY_DATE, ORDER_DATE\r\n"
				+ "		from transaction where TRAN_NO=?" ;
	
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, tranNo);

		ResultSet rs = stmt.executeQuery();

		PurchaseVO purchaseVO = null;
	    if (rs.next()) {
	        purchaseVO = new PurchaseVO();

	        // ProductVO 생성 및 설정
	        ProductVO product = new ProductVO();
	        product.setProdNo(rs.getInt("prod_no"));
	        purchaseVO.setPurchaseProd(product);

	        // UserVO 생성 및 설정
	        UserVO buyer = new UserVO();
	        buyer.setUserId(rs.getString("buyer_id"));
	        purchaseVO.setBuyer(buyer);

	        // 나머지 필드 설정
	        purchaseVO.setPaymentOption(rs.getString("payment_option"));
	        purchaseVO.setReceiverName(rs.getString("receiver_name"));
	        purchaseVO.setReceiverPhone(rs.getString("receiver_phone"));
	        purchaseVO.setDivyAddr(rs.getString("demailaddr"));
	        purchaseVO.setDivyRequest(rs.getString("dlvy_request"));
	        purchaseVO.setDivyDate(rs.getString("dlvy_date"));
	        purchaseVO.setOrderDate(rs.getDate("order_date"));
	    }
		
		con.close();

		return purchaseVO;
	}

	public HashMap<String,Object> getPurchaseList(SearchVO searchVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		//select tran_no, buyer_id, receiver_name, receiver_phone, dlvy_request, tran_status_code from transaction;
		String sql = 
			    "select tran_no, buyer_id, receiver_name, receiver_phone, dlvy_request,"
			    + " CASE TRIM(tran_status_code)\r\n"
			    + "        WHEN '0' THEN '판매중'\r\n"
			    + "        WHEN '1' THEN '구매완료'\r\n"
			    + "        WHEN '2' THEN '배송중'\r\n"
			    + "        WHEN '3' THEN '배송완료'\r\n"
			    + "        ELSE '알수없음'\r\n"
			    + "    END AS tran_status_code "
			    + " from transaction T";
	    
	    if (searchVO.getSearchCondition() != null) {
	    	if (searchVO.getSearchCondition().equals("0")) {
	    		sql += " where T.tran_no like '%" + searchVO.getSearchKeyword() + "%' ";
	    	} else if (searchVO.getSearchCondition().equals("1")) {
	    		sql += " where T.tran_no like '%" + searchVO.getSearchKeyword() + "%' ";
	    	}
	    }
	    sql += " ORDER BY T.tran_no DESC";
	    System.out.println("PurchaseDAO getPurchaseList - SQL : "+sql);
		PreparedStatement stmt = 
			con.prepareStatement(	sql,
														ResultSet.TYPE_SCROLL_INSENSITIVE,
														ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = stmt.executeQuery();

		rs.last();
		int total = rs.getRow();
		System.out.println("로우의 수:" + total);

		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("count", new Integer(total));

		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1); 
		System.out.println("searchVO.getPage():" + searchVO.getPage());
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());

		ArrayList<PurchaseVO> list = new ArrayList<PurchaseVO>();

		if (total > 0) {
		    for (int i = 0; i < searchVO.getPageUnit(); i++) {
		        PurchaseVO vo = new PurchaseVO();

		        // 거래번호
		        vo.setTranNo(rs.getInt("TRAN_NO"));

		        // 수신자 이름 / 전화번호 / 요청사항
		        vo.setReceiverName(rs.getString("RECEIVER_NAME"));
		        vo.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
		        vo.setDivyRequest(rs.getString("DLVY_REQUEST"));

		        // 거래 상태 코드
		        vo.setTranCode(rs.getString("TRAN_STATUS_CODE"));

		        // 구매자 정보 (UserVO에 buyer_id만 넣기)
		        UserVO buyer = new UserVO();
		        buyer.setUserId(rs.getString("BUYER_ID"));
		        vo.setBuyer(buyer);

		        list.add(vo);

		        if (!rs.next())
		            break;
		    }
		}
		System.out.println("list.size() : "+ list.size());
		map.put("list", list);
		System.out.println("map().size() : "+ map.size());

		con.close();
			
		return map;
	}
//
//	public void updateProduct(ProductVO productVO) throws Exception {
//	    // 로그를 추가하여 값 확인
//	    System.out.println("ProductDAO 의 updateProduct 실행 시작");
//	    System.out.println("Received ProductVO:");
//	    System.out.println("Product Name: " + productVO.getProdName());
//	    System.out.println("Product Detail: " + productVO.getProdDetail());
//	    System.out.println("Manufacture Date: " + productVO.getManuDate());
//	    System.out.println("Price: " + productVO.getPrice());
//	    System.out.println("File Name: " + productVO.getFileName());
//	    System.out.println("Product No: " + productVO.getProdNo());
//
//		Connection con = DBUtil.getConnection();
//
//		String sql = "update PRODUCT set PROD_NAME=?,PROD_DETAIL=?,MANUFACTURE_DAY=?,PRICE=?,IMAGE_FILE=? where PROD_NO=?";
//		
//		PreparedStatement stmt = con.prepareStatement(sql);
//		//productVO = new ProductVO();
//		
//		stmt.setString(1, productVO.getProdName());
//		stmt.setString(2, productVO.getProdDetail());
//		stmt.setString(3, productVO.getManuDate());
//		stmt.setInt(4,productVO.getPrice());
//		stmt.setString(5, productVO.getFileName());
//		stmt.setInt(6,productVO.getProdNo());
//		//stmt.executeUpdate();
//	    // SQL 실행
//		System.out.println("Executing SQL: " + sql);
//		System.out.println("Product No: " + productVO.getProdNo());
//	    int rowsAffected = stmt.executeUpdate();
//	    System.out.println("Rows affected: " + rowsAffected);
//		
//		con.close();
//	}
}