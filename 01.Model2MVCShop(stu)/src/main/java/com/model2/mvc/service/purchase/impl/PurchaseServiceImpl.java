package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;


public class PurchaseServiceImpl implements PurchaseService{
	
	private PurchaseDAO purchaseDAO;
	
	public PurchaseServiceImpl() {
		purchaseDAO=new PurchaseDAO();
	}

	public void addPurchase(PurchaseVO purchaseVO) throws Exception {
		purchaseDAO.insertPurchase(purchaseVO);
		System.out.println("PurchaseServiceImpl 의 addProdut 입니다. -- " + purchaseVO);
	}

//	public PurchaseVO getPurchase(int prodNo) throws Exception {
//		return purchaseDAO.findPurchase(prodNo);
//	}
//
	public HashMap<String,Object> getPurchaseList(SearchVO searchVO) throws Exception {
		return purchaseDAO.getPurchaseList(searchVO);
	}
//
//	public void updatePurchase(PurchaseVO purchaseVO) throws Exception {
//		purchaseDAO.updatePurchase(purchaseVO);
//	}
//
//	public boolean checkDuplication(int prodNo) throws Exception {
//		boolean result=true;
//		PurchaseVO purchaseVO=purchaseDAO.findPurchase(prodNo);
//		if(purchaseVO != null) {
//			result=false;
//		}
//		return result;
//	}
}