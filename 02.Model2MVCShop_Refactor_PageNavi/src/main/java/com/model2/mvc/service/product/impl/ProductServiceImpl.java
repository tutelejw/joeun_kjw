package com.model2.mvc.service.product.impl;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDao;
import com.model2.mvc.service.domain.Product;


public class ProductServiceImpl implements ProductService{
	
	///Field
	private ProductDao productDao;
	
	///Constructor
	public ProductServiceImpl() {
		productDao=new ProductDao();
	}

	///Method
	public void addProduct(Product product) throws Exception {
		productDao.insertProduct(product);
	}

	public Product getProduct(String prodNo) throws Exception {
		return productDao.findProduct(prodNo);
	}

	public Map<String,Object> getProductList(Search search) throws Exception {
		return productDao.getProductList(search);
	}

	public void updateProduct(Product product) throws Exception {
		productDao.updateProduct(product);
	}

	public boolean checkDuplication(String prodNo) throws Exception {
		boolean result=true;
		Product product=productDao.findProduct(prodNo);
		if(product != null) {
			result=false;
		}
		return result;
	}
}