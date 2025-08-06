package com.model2.mvc.service.product.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.__ing__ProductDAO;
import com.model2.mvc.service.product.vo.ProductVO;


public class ProductServiceImpl implements ProductService{
	
	private __ing__ProductDAO productDAO;
	
	public ProductServiceImpl() {
		productDAO=new __ing__ProductDAO();
	}

	public void addProduct(ProductVO productVO) throws Exception {
		productDAO.insertProduct(productVO);
	}

	public ProductVO getProduct(String productId) throws Exception {
		return productDAO.findProduct(productId);
	}

	public HashMap<String,Object> getProductList(SearchVO searchVO) throws Exception {
		return productDAO.getProductList(searchVO);
	}

	public void updateProduct(ProductVO productVO) throws Exception {
		productDAO.updateProduct(productVO);
	}


}