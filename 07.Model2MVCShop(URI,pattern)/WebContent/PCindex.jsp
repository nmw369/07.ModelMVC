<%@ page contentType="text/html; charset=euc-kr" %>

<html>
<head>
<title>Model2 MVC Shop</title>
<!--화면 기기 화면에 맞춰서 보여주기  -->
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />
</head>


<frameset rows="80,*" cols="*" frameborder="NO" border="0" framespacing="0">
  
  <frame src="/layout/top.jsp" name="topFrame" scrolling="NO" noresize >
  
  <frameset rows="*" cols="160,*" framespacing="0" frameborder="NO" border="0">
    <frame src="/layout/left.jsp" name="leftFrame" scrolling="NO" noresize>
    <frame src="/purchase/mainView" name="rightFrame"  scrolling="auto">
  </frameset>

</frameset>

<noframes>
<body>
</body>
</noframes>

</html>