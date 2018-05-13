<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>Model2 MVC Shop</title>

<link href="/css/left.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
function history(){
	popWin = window.open("/product/history","popWin","left=300, top=200, width=300, height=200, marginwidth=0, marginheight=0, scrollbars=no, scrolling=no, menubar=no, resizable=no");
}
function chating() {
	popWin 
		= window.open("/product/chating","popWin", "left=300,top=200,width=400,height=300,marginwidth=0,marginheight=0,scrollbars=no,scrolling=no,menubar=no,resizable=no");
}
function naverLogin() {
	popWin 
		= window.open("/user/NaverLogin","popWin");
}
</script>
</head>

<body background="/images/left/imgLeftBg.gif" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  >

<table width="159" border="0" cellspacing="0" cellpadding="0">

<!--menu 01 line-->
<tr>
	<td valign="top"> 
		<table  border="0" cellspacing="0" cellpadding="0" width="159" >	
			<tr>
				<c:if test="${ !empty user }">
					<tr>
						<td class="Depth03">
							<a href="/user/getUser?userId=${user.userId}" target="rightFrame">개인정보조회</a>
						</td>
					</tr>
				</c:if>
			
				<c:if test="${user.role == 'admin'}">
					<tr>
						<td class="Depth03" >
							<a href="/user/listUser" target="rightFrame">회원정보조회</a>
						</td>
					</tr>
				</c:if>
			
				<tr>
					<td class="DepthEnd">&nbsp;</td>
				</tr>
		</table>
	</td>
</tr>

<!--menu 02 line-->
<c:if test="${user.role == 'admin'}">
	<tr>
		<td valign="top"> 
			<table  border="0" cellspacing="0" cellpadding="0" width="159">
				<tr>
					<td class="Depth03">
						<a href="../product/addProductView.jsp;" target="rightFrame">판매상품등록</a>
					</td>
				</tr>
				
				<tr>
					<td class="DepthEnd">&nbsp;</td>
				</tr>
				<tr>
					<td class="Depth03">
						<a href="/purchase/listSale?"  target="rightFrame">판매된상품관리</a>
					</td>
				</tr>
				<tr>
					<td class="Depth03">
						<a href="/purchase/cancelList" target="rightFrame">구매취소목록</a>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</c:if>

<!--menu 03 line-->
<tr>
	<td valign="top"> 
		<table  border="0" cellspacing="0" cellpadding="0" width="159">
			<tr>
				<td class="Depth03">
					<a href="/product/listProduct?menu=search" target="rightFrame">상 품 검 색</a>
				</td>
			</tr>
			
			<c:if test="${ !empty user && user.role == 'user'}">
			<tr>
				<td class="Depth03">
					<a href="/purchase/listPurchase"  target="rightFrame">구매이력조회</a>
				</td>
			</tr>
			<tr>
				<td class="Depth03">
					<a href="javascript:chating()">실시간상담</a>
				</td>
			</tr>
			</c:if>
			
			<tr>
				<td class="DepthEnd">&nbsp;</td>
			</tr>
			<tr>
				<td class="Depth03"><a href="javascript:history()">최근 본 상품</a></td>
			</tr>
			<tr>
				<td class="Depth03">
					<a href="/purchase/mainView" target="rightFrame">Home</a>
				</td>
			</tr>
			<tr>
				<td class="Depth03">
					<a href="javascript:naverLogin()">네이버 로그인</a>
				</td>
			</tr>
		</table>
	</td>
</tr>

</table>

</body>
</html>
