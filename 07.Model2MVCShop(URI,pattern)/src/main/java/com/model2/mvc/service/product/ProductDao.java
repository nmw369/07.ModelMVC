package com.model2.mvc.service.product;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;



//==> 회원관리에서 CRUD 추상화/캡슐화한 DAO Interface Definition
public interface ProductDao {
	
	public int insertProduct(Product product) throws Exception;
	
	public Product findProduct(int prodNo) throws Exception;
	
	public void updateProduct(Product product) throws Exception;
	
	public List<Product> getProductList(Search search) throws Exception;

	public int getTotalCount(Search search) throws Exception;
	
	public List<Product> getMainList() throws Exception;
	
	public void updateLook(Product product) throws Exception;
	
	public void insertLookup(Product product) throws Exception;
	
	public void daycheck(Product product) throws Exception;
	
	public List<Product> getlookupList(String day) throws Exception;
	
	public List<Product> getTodayLookupList(String today) throws Exception;
	
	public void updateEA(int sEA,String prodName) throws Exception;
	
	
}