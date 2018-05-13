<%@ page contentType="text/html; charset=euc-kr" %>

<html>
<head>
<title>Model2 MVC Shop</title>
<!--화면 기기 화면에 맞춰서 보여주기  -->
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />
</head>
<script type="text/javascript">
/* PC인지 모바일인지 Check */

var filter = "win16|win32|win64|mac|macintel";



if( navigator.platform  ){

	if( filter.indexOf(navigator.platform.toLowerCase())<0 ){

		alert("모바일 기기에서 접속");

		// 특정 주소로 이동시키는 것
		location.href = "/Mobileindex.jsp";
		
	}else{

		/* alert("PC에서 접속"); */
		location.href = "/PCindex.jsp";

	}

}


</script>


</html>