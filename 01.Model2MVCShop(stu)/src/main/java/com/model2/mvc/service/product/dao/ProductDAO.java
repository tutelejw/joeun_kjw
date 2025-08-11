package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;



public class ProductDAO {
	
	public ProductDAO(){
	}

	public void insertProduct(ProductVO productVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		//String sql = "insert into PRODUCT (IMAGE_FILE, MANUFACTURE_DAY, PRICE, PROD_DETAIL, PROD_NAME, REG_DATE) values "
		String sql = "insert into PRODUCT (PROD_NO,IMAGE_FILE, MANUFACTURE_DAY, PRICE, PROD_DETAIL, PROD_NAME, REG_DATE) values "
				+ "(seq_product_prod_no.nextval,?,?,?,?,?,sysdate)";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, productVO.getFileName());
		stmt.setString(2, productVO.getManuDate());
		stmt.setInt(3, productVO.getPrice());
		stmt.setString(4, productVO.getProdDetail());
		stmt.setString(5, productVO.getProdName());
		stmt.executeUpdate();
		con.close();
	}

	public ProductVO findProduct(int prodNo) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "SELECT PROD_NO,\r\n"
				+ "    PROD_NAME,\r\n"
				+ "    PROD_DETAIL,\r\n"
				+ "    MANUFACTURE_DAY,\r\n"
				+ "    PRICE,\r\n"
				+ "    IMAGE_FILE,\r\n"
				+ "    REG_DATE \r\n"
				+ "	 FROM PRODUCT where PROD_NO=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, prodNo);

		ResultSet rs = stmt.executeQuery();

		ProductVO productVO = null;
		while (rs.next()) {
			productVO = new ProductVO();
			productVO.setProdNo(rs.getInt("PROD_NO"));
			productVO.setProdName(rs.getString("PROD_NAME"));
			productVO.setProdDetail(rs.getString("PROD_DETAIL"));
			productVO.setManuDate(rs.getString("MANUFACTURE_DAY"));
			productVO.setPrice(rs.getInt("PRICE"));
			productVO.setFileName(rs.getString("IMAGE_FILE"));
			productVO.setRegDate(rs.getDate("REG_DATE"));
		}
		
		con.close();

		return productVO;
	}

	public HashMap<String,Object> getProductList(SearchVO searchVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
	    // 기존 SQL (거래 상태 코드 없음)
	    /*
	    String sql = "SELECT PROD_NO,\r\n"
	            + "    PROD_NAME,\r\n"
	            + "    PROD_DETAIL,\r\n"
	            + "    MANUFACTURE_DAY,\r\n"
	            + "    PRICE,\r\n"
	            + "    IMAGE_FILE,\r\n"
	            + "    REG_DATE \r\n"
	            + " FROM PRODUCT ";
	    */
		
	    // [수정됨] 거래 상태 코드 포함을 위해 TRANSACTION 테이블 JOIN
		String sql = 
			    "SELECT P.PROD_NO, P.PROD_NAME, P.PROD_DETAIL, P.MANUFACTURE_DAY, " +
			    "       P.PRICE, P.IMAGE_FILE, P.REG_DATE, " +
			    "       CASE WHEN T.PROD_NO IS NOT NULL THEN '재고없음' ELSE '판매중' END AS TRAN_STATUS_TEXT " +
			    "FROM PRODUCT P LEFT JOIN TRANSACTION T ON P.PROD_NO = T.PROD_NO";
	    
	    if (searchVO.getSearchCondition() != null) {
	        if (searchVO.getSearchCondition().equals("0")) {
	            sql += " AND P.PROD_NO = '" + searchVO.getSearchKeyword() + "' ";
	        } else if (searchVO.getSearchCondition().equals("1")) {
	            sql += " AND P.PROD_NAME = '" + searchVO.getSearchKeyword() + "' ";
	        }
	    }
	    sql += " ORDER BY P.PROD_NO DESC";

		PreparedStatement stmt = 
			con.prepareStatement(	sql,
														ResultSet.TYPE_SCROLL_INSENSITIVE,
														ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = stmt.executeQuery();

		rs.last();
		int total = rs.getRow();
		System.out.println("로우의 수:" + total);

		HashMap<String,Object> map = new HashMap<String,Object>();
		//map.put("count", total);
		map.put("count", new Integer(total));

		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
		System.out.println("searchVO.getPage():" + searchVO.getPage());
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());

		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {
				ProductVO vo = new ProductVO();
				vo.setProdNo(rs.getInt("PROD_NO"));
				vo.setProdName(rs.getString("PROD_NAME"));
				vo.setProdDetail(rs.getString("PROD_DETAIL"));
				vo.setManuDate(rs.getString("MANUFACTURE_DAY"));
				vo.setPrice(rs.getInt("PRICE"));
				vo.setFileName(rs.getString("IMAGE_FILE"));
				vo.setRegDate(rs.getDate("REG_DATE"));
	            vo.setProTranCode(rs.getString("TRAN_STATUS_TEXT"));  // 바로 상태텍스트 세팅

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

	public void updateProduct(ProductVO productVO) throws Exception {
	    // 로그를 추가하여 값 확인
	    System.out.println("ProductDAO 의 updateProduct 실행 시작");
	    System.out.println("Received ProductVO:");
	    System.out.println("Product Name: " + productVO.getProdName());
	    System.out.println("Product Detail: " + productVO.getProdDetail());
	    System.out.println("Manufacture Date: " + productVO.getManuDate());
	    System.out.println("Price: " + productVO.getPrice());
	    System.out.println("File Name: " + productVO.getFileName());
	    System.out.println("Product No: " + productVO.getProdNo());

		Connection con = DBUtil.getConnection();

		String sql = "update PRODUCT set PROD_NAME=?,PROD_DETAIL=?,MANUFACTURE_DAY=?,PRICE=?,IMAGE_FILE=? where PROD_NO=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		//productVO = new ProductVO();
		
		stmt.setString(1, productVO.getProdName());
		stmt.setString(2, productVO.getProdDetail());
		stmt.setString(3, productVO.getManuDate());
		stmt.setInt(4,productVO.getPrice());
		stmt.setString(5, productVO.getFileName());
		stmt.setInt(6,productVO.getProdNo());
		//stmt.executeUpdate();
	    // SQL 실행
		System.out.println("Executing SQL: " + sql);
		System.out.println("Product No: " + productVO.getProdNo());
	    int rowsAffected = stmt.executeUpdate();
	    System.out.println("Rows affected: " + rowsAffected);
		
		con.close();
	}
}