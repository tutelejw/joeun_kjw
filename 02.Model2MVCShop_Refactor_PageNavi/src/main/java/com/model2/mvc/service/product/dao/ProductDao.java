package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;
//import com.model2.mvc.service.product.Product;


public class ProductDao {
	
	///Field
	
	///Constructor
	public ProductDao() {
	}

	///Method
	public void insertProduct(Product product) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
//		String sql = 	"INSERT "+ 
//								"INTO PRODUCT "+ 
//								"VALUES (?,?,?,'user',?,?,?,?,SYSDATE)"; //쿼리 수정 해야함.. 봐야함...
		String sql = "INSERT INTO PRODUCT (PROD_NO,IMAGE_FILE, MANUFACTURE_DAY, PRICE, PROD_DETAIL, PROD_NAME, REG_DATE) values "
								+ "(seq_product_prod_no.nextval,?,?,?,?,?,sysdate)";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setString(1, product.getFileName());
		pStmt.setString(2, product.getManuDate());
		pStmt.setInt(3, product.getPrice());
		pStmt.setString(4, product.getProdDetail());
		pStmt.setString(5, product.getProdName());
		pStmt.executeUpdate();
		
		System.out.println("ProductDao - insertProduct method 부분 : " + sql);
		
		pStmt.close();
		con.close();
	}

	
	public Product findProduct(int prodNo) throws Exception {
		
		Connection con = DBUtil.getConnection();
			
//		String sql = 	"SELECT "+
//								"user_id ,  user_name ,  password , role , cell_phone ,  addr ,  email , reg_date " + 
//								"FROM product WHERE user_id = ?";   
		String sql = "SELECT PROD_NO,\r\n"
				+ "    PROD_NAME,\r\n"
				+ "    PROD_DETAIL,\r\n"
				+ "    MANUFACTURE_DAY,\r\n"
				+ "    PRICE,\r\n"
				+ "    IMAGE_FILE,\r\n"
				+ "    REG_DATE \r\n"
				+ "	 FROM PRODUCT where PROD_NO=?";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setInt(1, prodNo);

		ResultSet rs = pStmt.executeQuery();

		Product product = null;
		while (rs.next()) {
			product = new Product();
			product.setProdNo(rs.getInt("PROD_NO"));
			product.setProdName(rs.getString("PROD_NAME"));
			product.setProdDetail(rs.getString("PROD_DETAIL"));
			product.setManuDate(rs.getString("MANUFACTURE_DAY"));
			product.setPrice(rs.getInt("PRICE"));
			product.setFileName(rs.getString("IMAGE_FILE"));
			product.setRegDate(rs.getDate("REG_DATE"));
		}
		
		System.out.println("ProductDao - findProduct method 부분 : " + sql);
		
		rs.close();
		pStmt.close();
		con.close();
		
		return product;
	}

	public Map<String , Object> getProductList(Search search) throws Exception {
		
		Map<String , Object>  map = new HashMap<String, Object>();
		
		Connection con = DBUtil.getConnection();
		
		// Original Query 구성
		//String sql = "SELECT prod_no ,  user_name , email  FROM  users ";   // 쿼리 수정해야함...

		// 거래 상태 코드 포함을 위해 TRANSACTION 테이블 JOIN
		String sql = 
			    "SELECT P.PROD_NO, P.PROD_NAME, P.PROD_DETAIL, P.MANUFACTURE_DAY, " +
			    "       P.PRICE, P.IMAGE_FILE, P.REG_DATE, " +
			    "       CASE WHEN T.PROD_NO IS NOT NULL THEN '재고없음' ELSE '판매중' END AS TRAN_STATUS_TEXT " +
			    "FROM PRODUCT P LEFT JOIN TRANSACTION T ON P.PROD_NO = T.PROD_NO";
	
	    if (search.getSearchCondition() != null) {
			if ( search.getSearchCondition().equals("0") &&  !search.getSearchKeyword().equals("") ) {
	    		sql += " where P.PROD_NO like '%" + search.getSearchKeyword() + "%' ";
				} else if ( search.getSearchCondition().equals("1") && !search.getSearchKeyword().equals("")) {
	    		sql += " where P.PROD_NAME like '%" + search.getSearchKeyword() + "%' ";
	    	}
	    }
	    sql += " ORDER BY P.PROD_NO DESC";

	    System.out.println("ProductDAO getProductList - SQL : "+sql);
	    System.out.println("ProductDAO::Original SQL :: " + sql);
		
		//==> TotalCount GET
		int totalCount = this.getTotalCount(sql);
		System.out.println("ProductDAO :: totalCount  :: " + totalCount);
		
		//==> CurrentPage 게시물만 받도록 Query 다시구성
		sql = makeCurrentPageSql(sql, search);
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
	
		System.out.println(search);

		List<Product> list = new ArrayList<Product>();
		
		while(rs.next()){
			Product product = new Product();
			product.setProdNo(rs.getInt("PROD_NO"));
			product.setProdName(rs.getString("PROD_NAME"));
			product.setProdDetail(rs.getString("PROD_DETAIL"));
			product.setManuDate(rs.getString("MANUFACTURE_DAY"));
			product.setPrice(rs.getInt("PRICE"));
			product.setFileName(rs.getString("IMAGE_FILE"));
			product.setRegDate(rs.getDate("REG_DATE"));
			product.setProTranCode(rs.getString("TRAN_STATUS_TEXT"));  // 바로 상태텍스트 세팅
			list.add(product);
		}
	    System.out.println("ProductDAO getProductList -  List<Product> list  : ");
	    
		//==> totalCount 정보 저장
		map.put("totalCount", new Integer(totalCount));
		//==> currentPage 의 게시물 정보 갖는 List 저장
		map.put("list", list);

		rs.close();
		pStmt.close();
		con.close();

		return map;
	}

	public void updateProduct(Product vo) throws Exception {
	    // 로그를 추가하여 값 확인
	    System.out.println("ProductDao 의 updateProduct 실행 시작");
	    System.out.println("Received Product:");
	    System.out.println("Product Name: " + vo.getProdName());
	    System.out.println("Product Detail: " + vo.getProdDetail());
	    System.out.println("Manufacture Date: " + vo.getManuDate());
	    System.out.println("Price: " + vo.getPrice());
	    System.out.println("File Name: " + vo.getFileName());
	    System.out.println("Product No: " + vo.getProdNo());
	    
		Connection con = DBUtil.getConnection();

		String sql = "update PRODUCT set PROD_NAME=?,PROD_DETAIL=?,MANUFACTURE_DAY=?,PRICE=?,IMAGE_FILE=? where PROD_NO=?";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setString(1, vo.getProdName());
		pStmt.setString(2, vo.getProdDetail());
		pStmt.setString(3, vo.getManuDate());
		pStmt.setInt(4,vo.getPrice());
		pStmt.setString(5, vo.getFileName());
		pStmt.setInt(6,vo.getProdNo());

		System.out.println("Executing SQL: " + sql);
		System.out.println("Product No: " + vo.getProdNo());
		// ✅ SQL 실행
		int row = pStmt.executeUpdate();  
		System.out.println(getClass() +" updateProduct  ::  업데이트된 행 수: " + row);
		pStmt.close();
		con.close();
	}
	
	// 게시판 Page 처리를 위한 전체 Row(totalCount)  return
	private int getTotalCount(String sql) throws Exception {
		
		sql = "SELECT COUNT(*) "+
		          "FROM ( " +sql+ ") countTable";
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		int totalCount = 0;
		if( rs.next() ){
			totalCount = rs.getInt(1);  //CONT(*) 
		}
		
		pStmt.close();
		con.close();
		rs.close();
		
		return totalCount;
	}
	
	// 게시판 currentPage Row 만  return 
	private String makeCurrentPageSql(String sql , Search search){
		sql = 	"SELECT * "+ 
					"FROM (		SELECT inner_table. * ,  ROWNUM AS row_seq " +
									" 	FROM (	"+sql+" ) inner_table "+
									"	WHERE ROWNUM <="+search.getCurrentPage()*search.getPageSize()+" ) " +
					"WHERE row_seq BETWEEN "+((search.getCurrentPage()-1)*search.getPageSize()+1) +" AND "+search.getCurrentPage()*search.getPageSize();
		
		System.out.println("ProductDAO :: make SQL :: "+ sql);	
		
		return sql;
	}
}