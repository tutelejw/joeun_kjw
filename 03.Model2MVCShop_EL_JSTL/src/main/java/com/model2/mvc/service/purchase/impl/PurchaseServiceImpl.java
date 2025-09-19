package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;


public class PurchaseServiceImpl implements PurchaseService{
	
	private PurchaseDAO purchaseDAO;
	
	public PurchaseServiceImpl() {
		purchaseDAO=new PurchaseDAO();
	}

	public void addPurchase(Purchase purchase) throws Exception {
		purchaseDAO.insertPurchase(purchase);
		System.out.println("PurchaseServiceImpl 의 addProdut 입니다. -- " + purchase);
	}

	public Purchase getPurchase(int tranNo) throws Exception {
		return purchaseDAO.findPurchase(tranNo);
	}

	public HashMap<String,Object> getPurchaseList(Search search) throws Exception {
		return purchaseDAO.getPurchaseList(search);
	}

	public void updatePurchase(Purchase purchase) throws Exception {
		purchaseDAO.updatePurchase(purchase);
	}
	
    public void updatePurchaseDelivery(int prodNo, int tranCode) throws Exception {
        System.out.println(getClass()+ " prodNo / tranCode :: " + prodNo + " /  " + tranCode);
        purchaseDAO.updatePurchaseDelivery(prodNo, tranCode);
    }

}