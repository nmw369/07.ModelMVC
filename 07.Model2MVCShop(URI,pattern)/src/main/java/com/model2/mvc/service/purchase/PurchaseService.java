package com.model2.mvc.service.purchase;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseService {
	public void addPurchase(Purchase purchase) throws Exception;
	
	public Purchase getPurchase(int tranNo) throws Exception;
	
	public int updatePurcahse(Purchase purchase) throws Exception;
	
	public int updateTranCode(Purchase purchase) throws Exception;
	
	public Map<String,Object> getPurchaseList(Search search,String buyerId) throws Exception;
	
	public void updateCancelCode(Purchase purchase) throws Exception;
	
	public Map<String,Object> cancelList(Search search) throws Exception;
	
	public Map<String,Object> saleList(Search search) throws Exception;
}