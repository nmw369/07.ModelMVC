package com.model2.mvc.web.product;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;

import com.model2.mvc.service.product.impl.ProductServiceImpl;

import sun.util.locale.StringTokenIterator;




//==> 회원관리 Controller
@Controller
@RequestMapping("/product/*")
public class ProductController extends HttpServlet{
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	//setter Method 구현 않음
	
	@Override
	public void init() throws ServletException{
		super.init();
			
		
	}
	
	public ProductController(){
		System.out.println("2");
		System.out.println(this.getClass());
		System.out.println("agsadg");
		
		try {
		 InetAddress local = InetAddress.getLocalHost();
	     System.out.println("My PC IP :" + local.getHostAddress());
	     String IP = local.getHostAddress();
	     ServletContext application = this.getServletContext();
	     application.setAttribute("serverIP", IP);
		}catch (Exception e) {
			e.getMessage();// TODO: handle exception
		}
		
	}
	
	//==> classpath:config/common.properties  ,  classpath:config/commonservice.xml 참조 할것
	//==> 아래의 두개를 주석을 풀어 의미를 확인 할것
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	
	@RequestMapping(value="addProductView", method=RequestMethod.GET)
	public String addProductView() throws Exception {

		System.out.println("/addProductView.do");
		
		return "redirect:/product/addProductView.jsp";
	}
	
	@RequestMapping(value="/addProduct",method=RequestMethod.POST)
	public String addProduct( @ModelAttribute("product") Product product,HttpServletRequest request,@RequestParam("file") MultipartFile[] fileName ) throws Exception {

		System.out.println("/addProduct.do");
		
		//Business Logic
		
		System.out.println(fileName[0].getOriginalFilename()+"??????????????????????????????????????????????????????");
		System.out.println(fileName[1].getOriginalFilename()+"??????????????????????????????????????????????????????");
		
		String temp = "";
		String tempName ="";
		
		
		
		for (int i = 0; i < fileName.length; i++) {
			temp =fileName[i].getOriginalFilename();
			tempName = tempName+":"+temp;
			File file = new File("C:\\workspace\\07.Model2MVCShop(URI,pattern)\\WebContent\\images\\uploadFiles\\"+temp);
			fileName[i].transferTo(file);
					
		}
		product.setFileName(tempName);
		
		
		////////////////////////
		/*String uri = product.getFileName();
		System.out.println("넘어온 경로 !!!!!!!"+uri);
	    File file = new File(uri);
	    //바이트에 동적할당 FileInputStream에 바이트넣으면 이미지 깨짐 DataInputStream사용할것
	    byte[] bytes = new byte[(int)file.length()];
	    System.out.println(bytes.length+"====================================================파일 용량");
	    
	    DataInputStream dateInput = new DataInputStream(new FileInputStream(file));
	    dateInput.readFully(bytes);
	    dateInput.close();
	    String fileName = uri.substring(uri.lastIndexOf("\\")+1, uri.indexOf(".")+4);
	    System.out.println(fileName+"=============================================이미지 파일 이름 출력");
	    FileOutputStream copyfile = new FileOutputStream("C:\\workspace\\07.Model2MVCShop(URI,pattern)\\WebContent\\images\\uploadFiles\\"+fileName);
	    copyfile.write(bytes);
	    copyfile.close();
			
		product.setFileName(fileName);*/
		
		////////////////////////////////////////////////////////이건 안씀 		
		/*if(FileUpload.isMultipartContent(request)) {
			String temDir = "C:\\workspace\\07.Model2MVCShop(URI,pattern)\\WebContent\\images\\uploadFiles\\";
			
			DiskFileUpload fileUpload = new DiskFileUpload();
			fileUpload.setRepositoryPath(temDir);
			fileUpload.setSizeMax(1024 * 1024 * 10);
			fileUpload.setSizeThreshold(1024 * 100);
			
			if(request.getContentLength() < fileUpload.getSizeMax()) {
				StringTokenizer token = null;
				
				List fileItemList = fileUpload.parseRequest(request);
				int size = fileItemList.size();
				for (int i = 0; i < size; i++) {
					FileItem fileItem = (FileItem)fileItemList.get(i);
					if(fileItem.isFormField()) {
						if(fileItem.getFieldName().equals("manuDate")) {
							token = new StringTokenizer(fileItem.getString("euc-kr"),"-");
							String manuDate = token.nextToken() + token.nextToken() + token.nextToken();
							product.setManuDate(manuDate);
						}
						else if(fileItem.getFieldName().equals("prodName")) {
							product.setProdName(fileItem.getString("euc-kr"));
						}
						else if(fileItem.getFieldName().equals("prodDetail")) {
							product.setProdDetail(fileItem.getString("euc-kr"));
						}
						else if(fileItem.getFieldName().equals("price")) {
							product.setPrice(Integer.parseInt(fileItem.getString("euc-kr")));
						}
					}else { //파일형식이면
						if(fileItem.getSize()>0){
							int idx = fileItem.getName().lastIndexOf("\\");
							if(idx ==-1) {
								idx = fileItem.getName().lastIndexOf("/");
							}
							String fileName = fileItem.getName().substring(idx+1);
							product.setFileName(fileName);
							try {
								File uploadedFile = new File(temDir,fileName);
								fileItem.write(uploadedFile);
							}catch (IOException e) {
								System.out.println(e);
							}
						}else {
							product.setFileName("../../images/empty.GIF");
						}
					}
				}
					productService.addProduct(product);
					request.setAttribute("product", product);					
			}else {
				int overSize = (request.getContentLength() / 1000000);
				System.out.println("파일의 크기는 1MB까지 입니다. 올리신 파일 용량은 + " +overSize + "MB입니다.");
				System.out.println("history.back();<script>");
			}			
		}else {
			System.out.println("인코딩 타입이 multipart/form-data가 아닙니다...");
		}*/
		///////////////////////////////////안써
		
		
		
		
		System.out.println("product : "+product);
		
		productService.addProduct(product);
		
		System.out.println("+++++++++++++++++++++++++++++++++++end!!!!");
		
		return "forward:/product/addProduct.jsp";
	}
	
	@RequestMapping(value="/getProduct")
	public String getProduct( @RequestParam("prodNo") int prodNo , Model model , HttpServletRequest request ,HttpServletResponse response) throws Exception {
		
		System.out.println("/getProduct.do");
		//Business Logic
		Product product = productService.getProduct(prodNo);
		// Model 과 View 연결
		/*List<String> filelist = new ArrayList<String>();*/
		
		
		
		String[] filelist = null;
		
		if(product.getFileName().contains(":")) {
			filelist = product.getFileName().split(":");
			
			for (int i = 0; i < filelist.length-1; i++) {
				filelist[i]=filelist[i+1];
			}
			
			model.addAttribute("filelist",filelist);
			
		}else {
			model.addAttribute("filelist",product.getFileName());
		}
		
		
		
		model.addAttribute("product", product);
		
		String lookup = request.getParameter("lookup");
		System.out.println(lookup+"***********lookup체크한것");
		if(lookup!=null && lookup.equals("yes")) {
		productService.updateLookup(productService.getProduct(prodNo));
		
		System.out.println("updateLookup메소드실행완료======");
		}
		String menu = request.getParameter("menu");
		//manage 쿠키 로직
				if(menu.equals("manage")){
					System.out.println("/////AddProductAction Action class execute end : b4 return/////");
					return "forward:/product/updateProductView";
				}else{
					System.out.println("/////AddProductAction Action class execute end : b4 return/////");
					
					//manage일 경우는 필요 없다 : 쿠키리스트? 에 새롭게 만들어질 쿠키를 넣어서 history.jsp에서 모두를 출력예정
					Cookie[] cookies = request.getCookies(); //request 객체로부터 쿠키를 받아서 Cookies[] data type의 cookies 라는 참조변수에 대입
					Cookie cookie = null; // Cookie data type의 cookie instance 생성하고 초기값 null
					for(int i = 0; i < cookies.length; i++){ //cookies라는 list에 존재하는 모든 cookie들에 대하여 
						
						if(cookies[i].getName().equals("history")){ // cookies[]에 각각의 cookie 이름에 "history"라는 name을 가지는 cookie가 존재한다면
							cookie = cookies[i];
							cookie.setValue(cookies[i].getValue()+","+prodNo); // 그 cookie에 history 라는 이름을 가진 cookie 객체를 생성한다.
							cookie.setMaxAge(60*10); //쿠키의 소멸예정시각 10분
							
						}
					}
					/*System.out.println(cookie.getValue()+":::cookie출력");*/
					
					if(cookie == null){ //for문을 다 돌려서 모든 cookie를 검색해도 history 이름을 가진 cookie가 존재하지 않는다면,
						cookie = new Cookie("history", ""+prodNo); //
						cookie.setMaxAge(60*10);
						
					}
					response.addCookie(cookie);
					if(request.getParameter("tranCode")!=null) {
					request.setAttribute("tranCode", request.getParameter("tranCode"));
					}
				}
				System.out.println("========================================getend");
		//파일이 여러개일때
				
				
				
				
		return "forward:/product/getProduct.jsp";
	}
	
	/*@RequestMapping("/updateProductView.do")
	public String updateProductView( @RequestParam("prodNo") int prodNo , Model model ) throws Exception{

		System.out.println("/updateProductView.do");
		//Business Logic
		Product Product = productService.getProduct(prodNo);
		// Model 과 View 연결
		model.addAttribute("Product", Product);
		
		return "forward:/product/updateProduct.jsp";
	}*/
	
	@RequestMapping(value="/updateProductView", method=RequestMethod.GET)
	public String updateProductView(@RequestParam("prodNo") int prodNo , Model model , HttpSession session) throws Exception{

		System.out.println("/updateProductView.do");
		//Business Logic
		System.out.println("/updateProduct.do");
		//Business Logic
		Product product = productService.getProduct(prodNo);
				
		model.addAttribute("vo",product);
		System.out.println("들어오기는함?");
		
		return "forward:/product/updateProduct.jsp";
		
	}
	
	
	@RequestMapping(value="/updateProduct", method=RequestMethod.POST)
	public String updateProduct( @ModelAttribute("Product") Product product , Model model , HttpSession session) throws Exception{

		System.out.println("/updateProduct.do");
		//Business Logic
		productService.updateProduct(product);
		
		String prodNo = product.getProdNo()+"";
		System.out.println(prodNo+"---");
		model.addAttribute("product",productService.getProduct(product.getProdNo()));
		
		return "forward:/product/getProduct.jsp?prodNo="+prodNo.trim()+"&menu=manage";
	}
			
	@RequestMapping(value="/listProduct")
	public String listProduct( @ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception{
		
		System.out.println("/listProduct.do");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
	
		
		//추가기능 날짜,가격 Sorting
		String daysorting = "";
		String sorting = "low";
		System.out.println("Daysorting를 위한 parameter!!!!!!!!!!!!!!!!!+++++++:::::"+daysorting);
		
		if(request.getParameter("daysorting")!=null && request.getParameter("daysorting").equals("highDay")) {
			search.setDaySorting(request.getParameter("daysorting"));
			search.setSorting("a");
			daysorting = request.getParameter("daysorting");
		}else if(request.getParameter("daysorting")!=null && request.getParameter("daysorting").equals("lowDay")) {
			
			daysorting = request.getParameter("daysorting");
			search.setDaySorting(daysorting);
			search.setSorting("a");
		}else if(search.getSorting()!="a") {
			
			if(request.getParameter("sorting")!=null && request.getParameter("sorting").equals("high")) {
				search.setSorting(request.getParameter("sorting"));
				sorting = request.getParameter("sorting");
			}else {
				search.setSorting(sorting);
			}
			
		}
		
		request.setAttribute("sorting", sorting);
		System.out.println("sorting를 위한 parameter!!!!!!!!!!!!!!!!!+++++++:::::"+sorting);
		request.setAttribute("daysorting", daysorting);
		
		// Business logic 수행
		
		//검색시 값입력
		search.setSearchCondition(request.getParameter("searchCondition"));
		search.setSearchKeyword(request.getParameter("searchKeyword"));
		/*search.setStartRowNum();
		search.setEndRowNum();*/
		
		System.out.println(request.getParameter("searchCondition")+":::::::::"+request.getParameter("searchKeyword"));
		
		
				
		Map<String , Object> map=productService.getProductList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		
	
		
		return "forward:/product/listProduct.jsp";
	}
	@RequestMapping(value="history")
	public String history() {
	
		return "forward:/history.jsp";
	}
	
	
	@RequestMapping(value="chating")
	public String chating(HttpServletRequest request) {
		System.out.println(request.getRemoteAddr()+"::채팅에 접속한 클라이언트 IP 정보");
	      String ip = request.getRemoteAddr();
	      
	         
	      
	      HttpSession session = request.getSession(false);
	      User user = (User)session.getAttribute("user");
	      
	      //ipcheck 
	  
	      /*.checkIP(ip);*/
	      
	      /*Map<String, Object> map = new UserDao().ip*/
	      
	      request.setAttribute("clientIP", request.getRemoteAddr());
	      
	      
	      
	      
	      return "forward:/webSocket/chating.jsp";
	}
	
	
	
	
}