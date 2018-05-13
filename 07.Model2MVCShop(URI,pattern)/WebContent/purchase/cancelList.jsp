<%@ page contentType="text/html; charset=EUC-KR"%>
<%@ page pageEncoding="EUC-KR"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>

<html>
	<head>
	<!-- 화면 변경 -->
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />
	<title>구매 취소내역</title>
		
		<link rel="stylesheet" href="/css/admin.css" type="text/css">
		
		<script type="text/javascript">
			function fncGetList(currentPage) {
				document.getElementById("currentPage").value = currentPage;
				document.detailForm.submit();
			}
		</script>
	</head>
<!-- 	<style>

body{
    background: url("/images/clock2.jpg") no-repeat center center fixed; 
    -webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    background-size: cover;
}
</style> -->
	
	
	
	
	<body bgcolor="#ffffff" text="#000000">
	
		<div style="width: 98%; margin-left: 10px;">
		
		<form name="detailForm" action="/purchase/cancelList" method="post">
		
		<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
			<tr>
				<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
				<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="93%" class="ct_ttl01">구매 취소내역조회</td>
						</tr>
					</table>
				</td>
				<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
			</tr>
		</table>
		
		<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
			<tr>
				<td colspan="11">전체 ${totalCount} 건, 현재 페이지</td>
			</tr>
			<tr>
				<td class="ct_list_b" width="100">사용자ID</td>
				<td class="ct_line02"></td>
				<td class="ct_list_b" width="100">상품명</td>
				<td class="ct_line02"></td>
				<td class="ct_list_b" width="150">상품가격</td>
				<td class="ct_line02"></td>
				<td class="ct_list_b" width="150">수량</td>
				<td class="ct_line02"></td>
				<td class="ct_list_b" width="150">취소사유</td>
				<td class="ct_line02"></td>
				<td class="ct_list_b">현황</td>
				
			</tr>
							
							<%-- <c:set var="i" value="${currentPage}"></c:set>
							<c:set var="j" value="${pageSize}"></c:set>
							<c:set var="k" value="2"></c:set> --%>
							<c:forEach var="vo" items="${list}">
								
							<tr class="ct_list_pop" bgcolor="#ffffff">
								
								<%-- <td class="ct_list_test1" align="center">
							
									<c:set var="k" value="${k-1}"></c:set>
									<c:set var="i" value="${currentPage}"></c:set>
							
								</td>
								<td></td> --%>
								
								<td class="ct_list_test1" align="left">
									<a	href="/product/getProduct?prodNo=${vo.buyer.userId}&menu=search&cancel=yes">
										${vo.buyer.userId}
									</a>
								</td>
								<td></td>
								
								<td class="ct_list_test1" align="left">
									<a	href="/product/getProduct?prodNo=${vo.purchaseProd.prodNo}&menu=search&cancel=yes">
										${vo.purchaseProd.prodName}
									</a>
								</td>
								<td></td>
								
								<td class="ct_list_test1" align="left">${vo.purchaseProd.price}</td>
								<td></td>
								
								<td class="ct_list_test1" align="left">${vo.sEA}</td>
								<td></td>
								
								<td class="ct_list_test1" align="left">
								<a href="">
										취소사유</a>
								</td>
								<td></td>
	
								<c:if test="${vo.cancelCode=='1'}">
									<td class="ct_list_test1" align="left">구매취소신청됨 
									<a href="/purchase/cancel?cancelCode=${vo.cancelCode}&tranNo=${vo.tranNo}">허가</a> 
									<a href="/purchase/cancel?cancelCode=${vo.cancelCode+1}&tranNo=${vo.tranNo}">거절</a>
									</td>
									
									
								</c:if>
								<c:if test="${vo.cancelCode=='2'}">
									<td class="ct_list_test1" align="left">구매취소허가함</td>
								</c:if>
								<c:if test="${vo.cancelCode=='3'}">
									<td class="ct_list_test1" align="left">구매취소거부함</td>
								</c:if>									
								<td></td>
								
								
								
							</tr>
							<tr>
								<td colspan="11" bgcolor="D6D7D6" height="1"></td>
							</tr>
							</c:forEach>
		</table>
		
		<%-- <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
			<tr>
				<td align="center">
			
					<input type="hidden" id="currentPage" name="currentPage" value="${resultPage.currentPage}"/>
				 
				 <jsp:include page="../common/pageNavigator.jsp"/>
				 
				 
				</td>
			</tr>
		</table> --%>
		
		<!--  페이지 Navigator 끝 -->
		</form>
		
		</div>
		
	</body>
</html>