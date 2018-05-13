package com.model2.mvc.service.domain;

import java.sql.Date;


public class Product {
	
	private String fileName;
	private String manuDate;
	private int price;
	private String prodDetail;
	private String prodName;
	private int prodNo;
	private Date regDate;
	private int lookup;
	private String proTranCode;
	private int nEA;
	private int sEA;
	private int totalEA;
	private String today;
	private int eaCode;
	
	public Product(){
	}
	
	
	


	




	public int getEaCode() {
		return eaCode;
	}










	public void setEaCode(int eaCode) {
		this.eaCode = eaCode;
	}










	public String getToday() {
		return today;
	}










	public void setToday(String today) {
		this.today = today;
	}










	public int getnEA() {
		return nEA;
	}










	public void setnEA(int nEA) {
		this.nEA = nEA;
	}










	public int getsEA() {
		return sEA;
	}










	public void setsEA(int sEA) {
		this.sEA = sEA;
	}










	public int getTotalEA() {
		return totalEA;
	}










	public void setTotalEA(int totalEA) {
		this.totalEA = totalEA;
	}










	public int getLookup() {
		return lookup;
	}


	public void setLookup(int lookup) {
		this.lookup = lookup;
	}


	public String getProTranCode() {
		return proTranCode.trim();
	}
	public void setProTranCode(String proTranCode) {
		if(Integer.parseInt(proTranCode)>=5) {
			this.proTranCode = "1".trim();
		}else {
		this.proTranCode = proTranCode.trim();
		} 
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getManuDate() {
		return manuDate;
	}
	public void setManuDate(String manuDate) {
		this.manuDate = manuDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getProdDetail() {
		return prodDetail;
	}
	public void setProdDetail(String prodDetail) {
		this.prodDetail = prodDetail;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getProdNo() {
		return prodNo;
	}
	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	// Override
	public String toString() {
		return "ProductVO : [fileName]" + fileName
				+ "[manuDate]" + manuDate+ "[price]" + price + "[prodDetail]" + prodDetail
				+ "[prodName]" + prodName + "[prodNo]" + prodNo +"[proTranCode]"+proTranCode
				+ "[today]"+today;
	}	
}