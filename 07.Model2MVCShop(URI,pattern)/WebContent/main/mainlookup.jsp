<%@ page contentType="text/html; charset=EUC-KR"%>
<%@ page pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>

<html>
<head>
<!-- 화면 변경 -->
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript" src="../javascript/calendar.js">
</script>

<script type="text/javascript">
<!--
function day(){
	//Form 유효성 검증
 	
	var manuDate = document.detailForm.lookupday.value;
	
}
function lookuplist(){
	document.detailForm.submit();
}
-->
</script>
</head>
<body>
<form name="detailForm" method="post">
<h3 align="center">조회할 날짜를 선택하세요.</h3>
	<p align="center"><input type="text" name="manuDate" readonly="readonly" class="ct_input_g"  
				style="width: 100px; height: 19px"	maxLength="10" minLength="6"/>
		&nbsp;<img src="../images/ct_icon_date.gif" width="15" height="15" 
		onclick="show_calendar('document.detailForm.manuDate', document.detailForm.manuDate.value)"/>
	</p>
<p align="center"><input type="button" value="확인" onclick="location.href='/mainView.do?start=yes'"></p>
    </form>
   </body>
</html>