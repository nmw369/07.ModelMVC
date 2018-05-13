package com.model2.mvc.service.product.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService{
	
	
	
	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDao productDao;
	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	///constract
	public ProductServiceImpl() {
		System.out.println(this.getClass());
		
		
	}

	@Override
	public int addProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		product.setManuDate(product.getManuDate().replaceAll("-", ""));
		return productDao.insertProduct(product);
	}

	@Override
	public Product getProduct(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("ServicegetProduct::"+prodNo);
		return productDao.findProduct(prodNo);
	}

	@Override
	public void updateProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		productDao.updateProduct(product);
	}

	@Override
	public Map<String, Object> getProductList(Search search) throws Exception {
		// TODO Auto-generated method stub
		List<Product> list= productDao.getProductList(search);
		int totalCount = productDao.getTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		return map;
	}

	@Override
	public Map<String, Object> getMainList() throws Exception {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = new HashMap<String,Object>();
		
		map.put("list", productDao.getMainList());
		
		return map;
	}

	@Override
	public void updateLookup(Product product) throws Exception {
		// TODO Auto-generated method stub
		productDao.updateLook(product);
	}

	@Override
	public void daycheck(String today,Product product) throws Exception {
		// TODO Auto-generated method stub
		productDao.insertLookup(product);
		
		Product checkProduct = productDao.findProduct(product.getProdNo());
		checkProduct.setToday(today);
		productDao.daycheck(checkProduct);
		
	}

	
	
	@Override
	public Map<String, Object> getLookupList(String today) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String,Object>();
		
		Date day = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
		String temp = date.format(day);
		
		System.out.println(today+"==========lookuplist");
		
		if(today.equals(temp)) {
		map.put("lookuplist", productDao.getTodayLookupList(today));
		}else {
		map.put("lookuplist",productDao.getlookupList(today));
		}		
		return map;
	}

	@Override
	public void updateEA(int sEA, Product prod) throws Exception {
		// TODO Auto-generated method stub
		String prodName = prod.getProdName();
		sEA = prod.getnEA()-sEA;
		
		productDao.updateEA(sEA, prodName);
		
	}

	


}
