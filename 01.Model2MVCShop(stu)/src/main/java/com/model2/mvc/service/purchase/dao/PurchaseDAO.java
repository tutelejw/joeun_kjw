package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	    String sql = "SELECT "
	            + " T.tran_no, T.prod_no, T.buyer_id, U.user_name, "
	            + " T.PAYMENT_OPTION, T.receiver_name, T.receiver_phone, T.DEMAILADDR, T.dlvy_request, T.DLVY_DATE, T.ORDER_DATE "
	            + "FROM transaction T "
	            + "JOIN users U ON T.buyer_id = U.user_id "
	            + "WHERE T.TRAN_NO = ?";

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
	        buyer.setUserName(rs.getString("user_name"));  // 이름 세팅 추가
	        purchaseVO.setBuyer(buyer);

	        // 나머지 필드 설정
	        purchaseVO.setTranNo(rs.getInt("tran_no"));
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

		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1); 
		System.out.println("searchVO.getPage():" + searchVO.getPage());
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());

		ArrayList<PurchaseVO> list = new ArrayList<PurchaseVO>();

		if (total > 0) {
		    for (int i = 0; i < searchVO.getPageUnit(); i++) {
		        PurchaseVO vo = new PurchaseVO();

		        vo.setTranNo(rs.getInt("TRAN_NO"));
		        vo.setReceiverName(rs.getString("RECEIVER_NAME"));
		        vo.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
		        vo.setDivyRequest(rs.getString("DLVY_REQUEST"));
		        vo.setTranCode(rs.getString("TRAN_STATUS_CODE"));
		        
		        // ProductVO 생성 및 설정
		        ProductVO product = new ProductVO();
		        product.setProdNo(rs.getInt("PROD_NO"));
		        vo.setPurchaseProd(product);

		        UserVO buyer = new UserVO();
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
    public void updatePurchase(PurchaseVO purchaseVO) throws SQLException {
        // DB 연결
        Connection con = DBUtil.getConnection();
//SELECT receiver_name, receiver_phone, demailaddr, dlvy_request, dlvy_date FROM transaction ;
        String sql = " UPDATE transaction SET "
                + " receiver_name = ?, receiver_phone = ?, demailaddr = ?, dlvy_request = ?, dlvy_date = ? "
                + " WHERE tran_no = ?";

        PreparedStatement stmt = con.prepareStatement(sql);

        // ?에 값 설정
        stmt.setString(1, purchaseVO.getReceiverName());
        stmt.setString(2, purchaseVO.getReceiverPhone());
        stmt.setString(3, purchaseVO.getDivyAddr());
        stmt.setString(4, purchaseVO.getDivyRequest());
        stmt.setString(5, purchaseVO.getDivyDate());
        stmt.setInt(6, purchaseVO.getTranNo());

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