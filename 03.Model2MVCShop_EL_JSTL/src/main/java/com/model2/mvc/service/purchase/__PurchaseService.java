package com.model2.mvc.service.purchase;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;


/*
 * 회원관리를 추상화 캡슐화한 ProductService Interface
 */
public interface __PurchaseService {
	
	public void addProduct(Product product) throws Exception;
	
	public Product getProduct(String prodNo) throws Exception;
	
	public Map<String, Object> getProductList(Search search) throws Exception;
	
	public void updateProduct(Product product) throws Exception;
	
	public boolean checkDuplication(String prodNo) throws Exception;
	
}