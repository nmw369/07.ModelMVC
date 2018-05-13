package com.model2.mvc.service.purchase;


import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;



//==> 회원관리에서 CRUD 추상화/캡슐화한 DAO Interface Definition
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseDao {
	public void insertPurchase(Purchase purchase) throws Exception;
	
	public Purchase getPurchase(int tranNo) throws Exception;
	
	public int updatePurchase(Purchase purchase) throws Exception;
	
	public int updateTranCode(Purchase purchase) throws Exception;
	
	public List<Purchase> getPurchaseList(Search search, String buyerId) throws Exception;
	
	public int getTotalCount(Search search, String buyerId) throws Exception;
	
	public void updateCancelCode(Purchase purchase) throws Exception;
	
	public List<Purchase> cancelList(Search search) throws Exception;
	
	public List<Purchase> saleList(Search search) throws Exception;
}