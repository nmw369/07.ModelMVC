package com.model2.mvc.service.product.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;

@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao{
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	//constract
	public ProductDaoImpl() {
		
		System.out.println(this.getClass());
	}

	@Override
	public int insertProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("insertProductÃâ·Â¹®"+product);
		return sqlSession.insert("ProductMapper.addProduct",product);
	}

	@Override
	public Product findProduct(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("findProduct:::"+prodNo);
		return sqlSession.selectOne("ProductMapper.getProduct",prodNo);
	}

	@Override
	public void updateProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.selectOne("ProductMapper.updateProduct",product);
	}

	@Override
	public List<Product> getProductList(Search search) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("ListProduct ::::"+search);
		
		return sqlSession.selectList("ProductMapper.getProductList",search);
	}

	@Override
	public int getTotalCount(Search search) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("ProductMapper.getTotalCount",search);
	}

	@Override
	public List<Product> getMainList() throws Exception {
		// TODO Auto-generated method stub
		
		return sqlSession.selectList("ProductMapper.getMainList");
	}

	@Override
	public void updateLook(Product product) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.selectOne("ProductMapper.updateLookup",product);
	}

	@Override
	public void insertLookup(Product product) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("insert!!!!!!!!!!!!!!");
			sqlSession.selectOne("ProductMapper.insertLookup", product);
	}
	@Override
	public void daycheck(Product product) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("dayCheck end!!!!!!!!!!!!!!!!!");
			sqlSession.selectOne("ProductMapper.dayCheck",product);
	
	}

	@Override
	public List<Product> getlookupList(String day) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(day+"==========lookuplist1");
		return sqlSession.selectList("ProductMapper.lookupList",day);
	}

	@Override
	public List<Product> getTodayLookupList(String today) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(today+"==========lookuplist2");
		return sqlSession.selectList("ProductMapper.todayLookup",today);
	}

	@Override
	public void updateEA(int nEA, String prodName) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("nEA", nEA);
		map.put("prodName", prodName);
		
		sqlSession.selectOne("ProductMapper.updateEA",map);
		
	}



		
}
