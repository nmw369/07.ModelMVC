package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.purchase.PurchaseService;

@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {
	
	@Autowired
	@Qualifier("purchaseDaoImpl")
	private PurchaseDao purchaseDao;
	public void setPurchaseDao(PurchaseDao purchaseDao){
		this.purchaseDao = purchaseDao;
	}
	public PurchaseServiceImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		purchaseDao.insertPurchase(purchase);
	}
	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		return purchaseDao.getPurchase(tranNo);
	}
	@Override
	public int updatePurcahse(Purchase purchase) throws Exception {
		return purchaseDao.updatePurchase(purchase);
	}
	@Override
	public int updateTranCode(Purchase purchase) throws Exception {
		return purchaseDao.updateTranCode(purchase);
	}
	@Override
	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", purchaseDao.getPurchaseList(search, buyerId));
		map.put("totalCount", new Integer(purchaseDao.getTotalCount(search,buyerId)));
		
		return map;
	}
	@Override
	public void updateCancelCode(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		purchaseDao.updateCancelCode(purchase);
	}
	@Override
	public Map<String, Object> cancelList(Search search) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("list", purchaseDao.cancelList(search));
		return map;
	}
	@Override
	public Map<String, Object> saleList(Search search) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("list", purchaseDao.saleList(search));
		return map;
	}
	
	
	
}
