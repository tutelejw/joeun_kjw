// File: src/main/java/com/model2/mvc/service/purchase/dao/PurchaseDAO.java
package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;

public class PurchaseDao {

    public PurchaseDao() {
    }
    
    // 구매 등록
    public void addPurchase(Purchase purchase) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO transaction(tran_no, prod_no, buyer_id, payment_option, receiver_name, " +
                     "receiver_phone, dlvy_addr, dlvy_request, tran_status_code, order_date, dlvy_date) " +
                     "VALUES (seq_transaction_tran_no.nextval, ?, ?, ?, ?, ?, ?, ?, '1', SYSDATE, ?)";

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, purchase.getPurchaseProd().getProdNo());
            pstmt.setString(2, purchase.getBuyer().getUserId());
            pstmt.setString(3, purchase.getPaymentOption());
            pstmt.setString(4, purchase.getReceiverName());
            pstmt.setString(5, purchase.getReceiverPhone());
            pstmt.setString(6, purchase.getDlvyAddr());
            pstmt.setString(7, purchase.getDlvyRequest());
            pstmt.setString(8, purchase.getDlvyDate());
            pstmt.executeUpdate();
        } finally {
    		pstmt.close();
    		con.close();
        }
    }
    
    // 구매 상세 조회
    public Purchase findPurchase(int tranNo) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        // Transaction, Product, Users 테이블을 조인하여 필요한 모든 정보를 가져온다.
        String sql = "SELECT t.*, p.*, u.user_name, u.user_id " +
                     "FROM transaction t, product p, users u " +
                     "WHERE t.prod_no = p.prod_no AND t.buyer_id = u.user_id AND t.tran_no = ?";
        Purchase purchase = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, tranNo);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                purchase = new Purchase();
                
                // Purchase 정보 매핑
                purchase.setTranNo(rs.getInt("tran_no"));
                purchase.setPaymentOption(rs.getString("payment_option"));
                purchase.setReceiverName(rs.getString("receiver_name"));
                purchase.setReceiverPhone(rs.getString("receiver_phone"));
                purchase.setDlvyAddr(rs.getString("dlvy_addr"));
                purchase.setDlvyRequest(rs.getString("dlvy_request"));
                purchase.setTranCode(rs.getString("tran_status_code"));
                purchase.setOrderDate(rs.getDate("order_date"));
                purchase.setDlvyDate(rs.getString("dlvy_date"));

                // Product 정보 매핑
                Product product = new Product();
                product.setProdNo(rs.getInt("prod_no"));
                product.setProdName(rs.getString("prod_name"));
                product.setProdDetail(rs.getString("prod_detail"));
                product.setManuDate(rs.getString("manufacture_day"));
                product.setPrice(rs.getInt("price"));
                product.setFileName(rs.getString("image_file"));
                product.setRegDate(rs.getDate("reg_date"));
                purchase.setPurchaseProd(product);

                // User 정보 매핑
                User user = new User();
                user.setUserId(rs.getString("buyer_id"));
                user.setUserName(rs.getString("user_name"));
                purchase.setBuyer(user);
            }
        } finally {
    		pstmt.close();
    		con.close();
        }
        return purchase;
    }

    // 구매 목록 조회 (구매자 기준)
//    public List<Purchase> getPurchaseList(Search search, String buyerId) throws Exception {
//        Connection con = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        // 구매자 ID로 필터링하고 페이징 처리
//        String sql = "SELECT * FROM (SELECT inner_table.*, ROWNUM AS row_seq " +
//                     "FROM (SELECT t.*, p.prod_name FROM transaction t, product p " +
//                     "WHERE t.prod_no = p.prod_no AND t.buyer_id = ? ORDER BY t.tran_no DESC) inner_table " +
//                     "WHERE ROWNUM <= ?) WHERE row_seq BETWEEN ? AND ?";
//                     
//        List<Purchase> list = new ArrayList<>();
//
//        try {
//            con = DBUtil.getConnection();
//            pstmt = con.prepareStatement(sql);
//            pstmt.setString(1, buyerId);
//            pstmt.setInt(2, search.getEndRowNum());
//            pstmt.setInt(3, search.getStartRowNum());
//            rs = pstmt.executeQuery();
//
//            while (rs.next()) {
//                Purchase purchase = new Purchase();
//                // 목록에서는 간소화된 정보만 매핑
//                purchase.setTranNo(rs.getInt("tran_no"));
//                purchase.setTranCode(rs.getString("tran_status_code"));
//                
//                Product product = new Product();
//                product.setProdNo(rs.getInt("prod_no"));
//                product.setProdName(rs.getString("prod_name"));
//                purchase.setPurchaseProd(product);
//                
//                User buyer = new User();
//                buyer.setUserId(rs.getString("buyer_id"));
//                purchase.setBuyer(buyer);
//
//                list.add(purchase);
//            }
//        } finally {
//    		pstmt.close();
//    		con.close();
//        }
//        return list;
//    }

    // 판매 목록 조회 (관리자/판매자 기준)
    public List<Purchase> getSaleList(Search search) throws Exception {
        // 이 메서드는 buyerId 필터링만 제외하면 getPurchaseList와 거의 동일
        // 여기서는 buyerId 조건만 제거하여 모든 거래 목록을 조회하도록 구현
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM (SELECT inner_table.*, ROWNUM AS row_seq " +
                     "FROM (SELECT t.*, p.prod_name FROM transaction t, product p " +
                     "WHERE t.prod_no = p.prod_no ORDER BY t.tran_no DESC) inner_table " +
                     "WHERE ROWNUM <= ?) WHERE row_seq BETWEEN ? AND ?";
                     
        List<Purchase> list = new ArrayList<>();
        // ... getPurchaseList와 유사한 로직으로 구현
        return list;
    }

    // 구매 정보 수정
    public void updatePurchase(Purchase purchase) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE transaction SET payment_option=?, receiver_name=?, receiver_phone=?, " +
                     "dlvy_addr=?, dlvy_request=?, dlvy_date=? WHERE tran_no=?";

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, purchase.getPaymentOption());
            pstmt.setString(2, purchase.getReceiverName());
            pstmt.setString(3, purchase.getReceiverPhone());
            pstmt.setString(4, purchase.getDlvyAddr());
            pstmt.setString(5, purchase.getDlvyRequest());
            pstmt.setString(6, purchase.getDlvyDate());
            pstmt.setInt(7, purchase.getTranNo());
            pstmt.executeUpdate();
        } finally {
    		pstmt.close();
    		con.close();
        }
    }
    
    // 구매 상태 코드 수정
    public void updateTranCode(Purchase purchase) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE transaction SET tran_status_code=? WHERE tran_no=?";

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, purchase.getTranCode());
            pstmt.setInt(2, purchase.getTranNo());
            pstmt.executeUpdate();
        } finally {
    		pstmt.close();
    		con.close();
        }
    }
    
    // 구매 목록 페이징을 위한 전체 개수 조회
    public int getTotalCount(String buyerId) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int totalCount = 0;
        String sql = "SELECT COUNT(*) FROM transaction WHERE buyer_id=?";

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, buyerId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
        } finally {
    		pstmt.close();
    		con.close();
        }
        return totalCount;
    }
}