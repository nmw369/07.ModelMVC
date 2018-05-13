
<%-- <%@page import="com.model2.mvc.service.domain.User"%>
<%@page import="com.model2.mvc.service.domain.Product"%> --%>
<%@ page contentType="text/html;charset=EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%-- <%
	Product productVO = (Product) request.getAttribute("prodVO");
	System.out.println("ADDPURVIEW확인 +++++++::"+productVO);
	User userVO = (User) session.getAttribute("user");
	System.out.println("ADDPURVIEW확인 +++++++::"+userVO);
%> --%>
<html>
	<head>
	<!-- 화면 변경 -->
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />
	
		<link rel="stylesheet" href="/css/admin.css" type="text/css">
		
		<title>purView</title>
		
		<script type="text/javascript" src="../javascript/calendar.js">
		</script>
		
		<script type="text/javascript">
		function fncAddPurchase() {
			document.addPurchase.submit();
		}
		</script>
	</head>
	
	<body>
	
		<form name="addPurchase" method="post" action="/purchase/addPurchase">
		
		<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
			<tr>
				<td width="15" height="37">
					<img src="/images/ct_ttl_img01.gif" width="15" height="37">
				</td>
				<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="93%" class="ct_ttl01">상품상세조회</td>
							<td width="20%" align="right">&nbsp;</td>
						</tr>
					</table>
				</td>
				<td width="12" height="37">
					<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
				</td>
			</tr>
		</table>
		
		<input type="hidden" name="prodNo" value="${prodVO.prodNo}" />
		
		<input type="hidden" name="purchaseProd.prodNo" value="${prodVO.prodNo}" />
		<input type="hidden" name="buyer.userId" value="${user.userId}" />
		<input type="hidden" name="tranCode" value="2"/>
		<!-- <input type="hidden" name="cancelCode" value="0"/> -->
		 
		
		<table width="600" border="0" cellspacing="0" cellpadding="0"	align="center" style="margin-top: 13px;">
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			<tr>
				<td width="300" class="ct_write">
					상품번호 
					<img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
				</td>
				<td bgcolor="D6D6D6" width="1">
				</td>
				<td class="ct_write01" width="299">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="105">
								${prodVO.prodNo}
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6">
				</td>
			</tr>
			<tr>
				<td width="104" class="ct_write">
					상품명 
					<img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
				</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01">
					${prodVO.prodName}
				</td>
			</tr>
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			<tr>
				<td width="104" class="ct_write">
					상품상세정보 
					<img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
				</td>
				<td bgcolor="D6D6D6" width="1">
				</td>
				<td class="ct_write01">
					${prodVO.prodDetail}
				</td>
			</tr>
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			<tr>
				<td width="104" class="ct_write">
					제조일자
				</td>
				<td bgcolor="D6D6D6" width="1">
				</td>
				<td class="ct_write01">
					${prodVO.manuDate}
				</td>
			</tr>
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6">
				</td>
			</tr>
			<tr>
				<td width="104" class="ct_write">
					가격
				</td>
				<td bgcolor="D6D6D6" width="1">
				</td>
				<td class="ct_write01">
					${prodVO.price}
				</td>
			</tr>
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			
			<tr>
				<td width="104" class="ct_write">
					수량
				</td>
				<td bgcolor="D6D6D6" width="1">
				</td>
				<td class="ct_write01">
					<select name="sEA" class="ct_input_g" style="width:80px">
						<c:forEach var="i" begin="1" end="${prodVO.nEA}">
							<option value="${i}">${i}</option>
							<c:set var="i" value="${i+1}"></c:set>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			
			<tr>
				<td width="104" class="ct_write">
					등록일자
				</td>
				<td bgcolor="D6D6D6" width="1">
				</td>
				<td class="ct_write01">
					${prodVO.regDate}
				</td>
			</tr>
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			<tr>
				<td width="104" class="ct_write">
					구매자아이디
					<img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
				</td>
				<td bgcolor="D6D6D6" width="1">
				</td>
				<td class="ct_write01">
					${user.userId}
				</td>
				
			</tr>
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6">
				</td>
			</tr>
			<tr>
				<td width="104" class="ct_write">
					구매방법
				</td>
				<td bgcolor="D6D6D6" width="1">
				</td>
				<td class="ct_write01">
					<select name="paymentOption" class="ct_input_g" style="width: 100px; height: 19px" maxLength="20">
						<option value="1" selected="selected">현금구매</option>
						<option value="2">신용구매</option>
					</select>
				</td>
			</tr>
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			<tr>
				<td width="104" class="ct_write">
					구매자이름
				</td>
				<td bgcolor="D6D6D6" width="1">
				</td>
				<td class="ct_write01">
					<input type="text" name="receiverName" 	class="ct_input_g" style="width: 100px; height: 19px" maxLength="20" value="${user.userName}" />
				</td>
			</tr>
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6">
				</td>
			</tr>
			<tr>
				<td width="104" class="ct_write">
					구매자연락처
				</td>
				<td bgcolor="D6D6D6" width="1">
				</td>
				<td class="ct_write01">
					<input 	type="text" name="receiverPhone" class="ct_input_g" style="width: 100px; height: 19px" maxLength="20" value="${user.phone}" />
				</td>
			</tr>
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			<tr>
				<td width="104" class="ct_write">
					구매자주소
				</td>
				<td bgcolor="D6D6D6" width="1">
				</td>
				<td class="ct_write01">
					<input 	type="text" name="divyAddr" class="ct_input_g" style="width: 100px; height: 19px" maxLength="20" 	value="${user.addr}" />
				</td>
			</tr>
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6">
				</td>
			</tr>
			<tr>
				<td width="104" class="ct_write">구매요청사항</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01">
					<input type="text" name="divyRequest" 	class="ct_input_g" style="width: 100px; height: 19px" maxLength="20" />
				</td>
			</tr>
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			<tr>
				<td width="104" class="ct_write">
					배송희망일자
				</td>
				<td bgcolor="D6D6D6" width="1">
				</td>
				<td width="200" class="ct_write01">
					<input 	type="text" readonly="readonly" name="divyDate" class="ct_input_g" style="width: 100px; height: 19px" maxLength="20"/>
					<img src="../images/ct_icon_date.gif" width="15" height="15" onclick="show_calendar('document.addPurchase.divyDate', document.addPurchase.divyDate.value)"/>
				</td>
			</tr>
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
		</table>
		
		<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
			<tr>
				<td width="53%"></td>
				<td align="center">
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="17" height="23">
								<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
							</td>
							<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
								<a href="javascript:fncAddPurchase();">구매</a>
							</td>
							<td width="14" height="23">
								<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
							</td>
							<td width="30"></td>
							<td width="17" height="23">
								<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
							</td>
							<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
								<a href="javascript:history.go(-1)">취소</a>
							</td>
							<td width="14" height="23">
								<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
	
	</body>
</html>