package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;


public class __ing__ProductDAO {
	
	public __ing__ProductDAO(){
	}

	public void insertProduct(ProductVO productVO) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "insert into Product values (?,?,?,'product',?,?,?,?,sysdate)";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, productVO.getFileName());
		stmt.setString(2, productVO.getManuDate());
		stmt.setInt(3, productVO.getPrice());
		stmt.setString(4, productVO.getProdDetail());
		stmt.setString(5, productVO.getProdName());
		stmt.setInt(6, productVO.getProdNo());
		stmt.executeUpdate();
		
		con.close();
	}

	public ProductVO findProduct(String productId) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "select * from ProductS where Product_ID=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, productId);

		ResultSet rs = stmt.executeQuery();

		ProductVO productVO = null;
		while (rs.next()) {
			productVO = new ProductVO();
			productVO.setFileName(rs.getString("IMAGE_FILE"));
			productVO.setManuDate(rs.getString("MANUFACTURE_DAY"));
			productVO.setPrice(rs.getInt("PRICE"));
			productVO.setProdDetail(rs.getString("PROD_DETAIL"));
			productVO.setProdName(rs.getString("PROD_NAME"));
			productVO.setProdNo(rs.getInt("PROD_NO"));
			productVO.setRegDate(rs.getDate("REG_DATE"));
		}
		
		con.close();

		return productVO;
	}

	public HashMap<String,Object> getProductList(SearchVO searchVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "select * from ProductS ";
		if (searchVO.getSearchCondition() != null) {
			if (searchVO.getSearchCondition().equals("0")) {
				sql += " where Product_ID='" + searchVO.getSearchKeyword()
						+ "'";
			} else if (searchVO.getSearchCondition().equals("1")) {
				sql += " where Product_NAME='" + searchVO.getSearchKeyword()
						+ "'";
			}
		}
		sql += " order by Product_ID";

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

		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {
				ProductVO vo = new ProductVO();
				vo.setProductId(rs.getString("Product_ID"));
				vo.setProductName(rs.getString("Product_NAME"));
				vo.setPassword(rs.getString("PASSWORD"));
				vo.setRole(rs.getString("ROLE"));
				vo.setSsn(rs.getString("SSN"));
				vo.setPhone(rs.getString("CELL_PHONE"));
				vo.setAddr(rs.getString("ADDR"));
				vo.setEmail(rs.getString("EMAIL"));
				vo.setRegDate(rs.getDate("REG_DATE"));

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
		
		Connection con = DBUtil.getConnection();

		String sql = "update ProductS set Product_NAME=?,CELL_PHONE=?,ADDR=?,EMAIL=? where Product_ID=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, productVO.getProductName());
		stmt.setString(2, productVO.getPhone());
		stmt.setString(3, productVO.getAddr());
		stmt.setString(4, productVO.getEmail());
		stmt.setString(5, productVO.getProductId());
		stmt.executeUpdate();
		
		con.close();
	}
}