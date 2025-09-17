package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;



public class PurchaseDAO2 {
	
	public PurchaseDAO2(){
	}
	public void insertPurchase(Purchase purchase) throws Exception {
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

	public Purchase findPurchase(int tranNo) throws Exception {
	    
	    Connection con = DBUtil.getConnection();

	    String sql = "SELECT "
	            + " T.tran_no, T.prod_no, T.buyer_id, U.user_name, "
	            + " T.PAYMENT_OPTION, T.receiver_name, T.receiver_phone, T.DEMAILADDR, T.dlvy_request, T.DLVY_DATE, T.ORDER_DATE "
	            + "FROM transaction T "
	            + "JOIN users U ON T.buyer_id = U.user_id "
	            + "WHERE T.TRAN_NO = ?";

	    PreparedStatement stmt = con.prepareStatement(sql);
	    stmt.setInt(1, tranNo);

	    ResultSet rs = stmt.executeQuery();

	    Purchase purchase = null;
	    if (rs.next()) {
	        purchase = new Purchase();

	        // Product 생성 및 설정
	        Product product = new Product();
	        product.setProdNo(rs.getInt("prod_no"));
	        purchase.setPurchaseProd(product);

	        // User 생성 및 설정
	        User buyer = new User();
	        buyer.setUserId(rs.getString("buyer_id"));
	        buyer.setUserName(rs.getString("user_name"));  // 이름 세팅 추가
	        purchase.setBuyer(buyer);

	        // 나머지 필드 설정
	        purchase.setTranNo(rs.getInt("tran_no"));
	        purchase.setPaymentOption(rs.getString("payment_option"));
	        purchase.setReceiverName(rs.getString("receiver_name"));
	        purchase.setReceiverPhone(rs.getString("receiver_phone"));
	        purchase.setDivyAddr(rs.getString("demailaddr"));
	        purchase.setDivyRequest(rs.getString("dlvy_request"));
	        purchase.setDivyDate(rs.getString("dlvy_date"));
	        purchase.setOrderDate(rs.getDate("order_date"));
	    }

	    con.close();

	    return purchase;
	}


	public HashMap<String,Object> getPurchaseList(Search search) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		//select tran_no, buyer_id, receiver_name, receiver_phone, dlvy_request, tran_status_code from transaction;
		String sql = 
			    "SELECT T.tran_no, T.prod_no, T.buyer_id, U.user_name, T.receiver_name, T.receiver_phone, T.dlvy_request, "
			  + " CASE TRIM(T.tran_status_code) "
			  + "   WHEN '0' THEN '판매중' "
			  + "   WHEN '1' THEN '구매완료' "
			  + "   WHEN '2' THEN '배송중' "
			  + "   WHEN '3' THEN '배송완료' "
			  + "   ELSE '알수없음' "
			  + " END AS tran_status_code "
			  + "FROM transaction T "
			  + "JOIN users U ON T.buyer_id = U.user_id "
			  + "ORDER BY T.tran_no DESC";

			PreparedStatement stmt = con.prepareStatement(sql,
			                    ResultSet.TYPE_SCROLL_INSENSITIVE,
			                    ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery();

		rs.last();
		int total = rs.getRow();
		System.out.println("로우의 수:" + total);

		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("count", new Integer(total));

		rs.absolute(search.getCurrentPage() * search.getPageSize() - search.getPageSize()+1); 
		System.out.println("search.getPage():" + search.getCurrentPage());
		System.out.println("search.getPageUnit():" + search.getPageSize());

		ArrayList<Purchase> list = new ArrayList<Purchase>();

		if (total > 0) {
		    for (int i = 0; i < search.getPageSize(); i++) {
		        Purchase vo = new Purchase();

		        vo.setTranNo(rs.getInt("TRAN_NO"));
		        vo.setReceiverName(rs.getString("RECEIVER_NAME"));
		        vo.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
		        vo.setDivyRequest(rs.getString("DLVY_REQUEST"));
		        vo.setTranCode(rs.getString("TRAN_STATUS_CODE"));
		        
		        // Product 생성 및 설정
		        Product product = new Product();
		        product.setProdNo(rs.getInt("PROD_NO"));
		        vo.setPurchaseProd(product);

		        User buyer = new User();
		        buyer.setUserId(rs.getString("BUYER_ID"));
		        buyer.setUserName(rs.getString("USER_NAME"));  // 여기에 buyer 이름 추가
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
    // 구매 정보 수정 메서드
    // 구매 정보 수정 메서드
    public void updatePurchase(Purchase purchase) throws SQLException {
        // DB 연결
        Connection con = DBUtil.getConnection();
//SELECT receiver_name, receiver_phone, demailaddr, dlvy_request, dlvy_date FROM transaction ;
        String sql = " UPDATE transaction SET "
                + " receiver_name = ?, receiver_phone = ?, demailaddr = ?, dlvy_request = ?, dlvy_date = ? "
                + " WHERE tran_no = ?";

        PreparedStatement stmt = con.prepareStatement(sql);

        // ?에 값 설정
        stmt.setString(1, purchase.getReceiverName());
        stmt.setString(2, purchase.getReceiverPhone());
        stmt.setString(3, purchase.getDivyAddr());
        stmt.setString(4, purchase.getDivyRequest());
        stmt.setString(5, purchase.getDivyDate());
        stmt.setInt(6, purchase.getTranNo());

        // 쿼리 실행
        int rowsUpdated = stmt.executeUpdate();
        System.out.println("Rows updated: " + rowsUpdated); // 디버깅용 출력

        // 리소스 해제
         con.close();
    }
    
    public void updatePurchaseDelivery(int prodNo, int tranCode) throws Exception {
        Connection con = DBUtil.getConnection();
        System.out.println(getClass().getSimpleName() + " :: prodNo / tranCode  : " + prodNo +" / "+ tranCode);
        String sql = "UPDATE transaction SET tran_status_code = ? WHERE prod_no = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, tranCode);
        stmt.setInt(2, prodNo);

        int result = stmt.executeUpdate();
        System.out.println("배송상태 업데이트 결과: " + result + "건");

//        stmt.close();
        con.close();
    }

}