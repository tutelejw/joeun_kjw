package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDao;

public class PurchaseServiceImpl implements PurchaseService {

    private PurchaseDao purchaseDao;

    public PurchaseServiceImpl() {
        purchaseDao = new PurchaseDao();
    }

    @Override
    public void addPurchase(Purchase purchase) throws Exception {
        purchaseDao.addPurchase(purchase);
    }

    @Override
    public Purchase getPurchase(int tranNo) throws Exception {
        return purchaseDao.findPurchase(tranNo);
    }

    @Override
    public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception {
        List<Purchase> list = purchaseDao.getPurchaseList(search, buyerId);
        int totalCount = purchaseDao.getTotalCount(buyerId);
        
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("totalCount", totalCount);
        
        return map;
    }

    @Override
    public Map<String, Object> getSaleList(Search search) throws Exception {
        // DAO 호출 및 Map 패키징
        return null; // getPurchaseList와 유사하게 구현
    }

    @Override
    public void updatePurchase(Purchase purchase) throws Exception {
        purchaseDao.updatePurchase(purchase);
    }

    @Override
    public void updateTranCode(Purchase purchase) throws Exception {
        purchaseDao.updateTranCode(purchase);
    }
}