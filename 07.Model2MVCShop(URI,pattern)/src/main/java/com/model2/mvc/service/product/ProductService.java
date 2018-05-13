package com.model2.mvc.service.product;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;



//==> 회원관리에서 서비스할 내용 추상화/캡슐화한 Service  Interface Definition  
public interface ProductService {
	public int addProduct(Product product) throws Exception;
	
	public Product getProduct(int prodNo) throws Exception;
	
	public void updateProduct(Product product) throws Exception;
	
	public Map<String,Object> getProductList(Search search) throws Exception;
	
	public Map<String,Object> getMainList() throws Exception;
	
	public void updateLookup(Product product) throws Exception;
	
	public void daycheck(String today ,Product product) throws Exception;
	
	public Map<String,Object> getLookupList(String today) throws Exception;
	
	public void updateEA(int sEA,Product prod) throws Exception;
	
	
}