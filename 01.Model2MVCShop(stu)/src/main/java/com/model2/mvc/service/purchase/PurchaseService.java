package com.model2.mvc.service.purchase;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;


public interface PurchaseService {
	
	public void addPurchase(PurchaseVO purchaseVO) throws Exception;
	
//	public PurchaseVO getPurchase(String prodNo) throws Exception;
	public PurchaseVO getPurchase(int tranNo) throws Exception;
//	
	public HashMap<String, Object> getPurchaseList(SearchVO searchVO) throws Exception;
//	
	public void updatePurchase(PurchaseVO purchaseVO) throws Exception;
//	
//	public boolean checkDuplication(int prodNo) throws Exception;

	public void updatePurchaseDelivery(int tranNo) throws Exception;

}