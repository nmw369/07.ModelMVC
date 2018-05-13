<%@ page contentType="text/html; charset=EUC-KR"%>
<%@ page pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>

<html>
<head>
<!-- 화면 변경 -->
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />



	<link rel="stylesheet" href="/css/admin.css" type="text/css" >
  <link rel="stylesheet" href="https://cdn.jsdelivr.net\/bxslider/4.2.12/jquery.bxslider.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
  
    <script type="text/javascript">
   /*  playAlert = setInterval(function() {
		   alert('스크립트경고창확인');
	}, 30000000); */
    </script>
  
  
<c:if test="${user.role!= 'admin' }">
  <script type="text/javascript">
    $(document).ready(function(){
      $('.slider').bxSlider();
    });
  </script>
  </c:if>

  <c:if test="${user.role=='admin'}">
	
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	
	<!-- 달력용 스크립트 -->
	<script type="text/javascript" src="../javascript/calendar.js">
	</script>
	
	<script type="text/javascript">
	<!--
	function day(){
		//Form 유효성 검증
	 	
		var manuDate = document.detailForm.manuDate.value;
		
	}
	function lookuplist(){
		var start = "yes";
		document.getElementById("start").value = start;
		document.detailForm.submit();
	}
	function chart(){
		var start = "chart";
		document.getElementById("start").value = start;
		document.detailForm.submit();
	}
	-->
	</script>
	
	
	
	
	<script>
	// 차트를 사용하기 위한 준비입니다.
		google.charts.load('current', {packages:['corechart']});	
	</script>

	<!-- 여기에 차트가 생성됩니다. -->
	
	<%-- <c:if test="${start=='chart' || empty manuDate}"> --%>
		<%-- <c:if test="${!empty lookuplist}"> --%>
	<script type="text/javascript">
		
		
	// 로딩 완료시 함수 실행하여 차트 생성
	google.charts.setOnLoadCallback(drawChart);
	
	function drawChart() {
		
		/* var test = "${a}";
		alert(test);
		 */
		
	// 차트 데이터 설정
		var data = google.visualization.arrayToDataTable([
			['상품명', '조회수'], // 항목 정의
			<c:forEach var="lookup" items="${lookuplist}" varStatus="status">
			
				<c:if test="${!status.last}">
				['${lookup.prodName}', parseInt('${lookup.lookup}')], // 항목, 값 (값은 숫자로 입력하면 그래프로 생성됨)
				</c:if>				
				<c:if test="${status.last}">
				['${lookup.prodName}', parseInt('${lookup.lookup}')]
				</c:if>
			</c:forEach>
			]);

	// 그래프 옵션
				var options = {
				title : '실시간 상품별 조회수', // 제목
				width : 1100, // 가로 px
				height : 400, // 세로 px
				bar : {
					groupWidth : '80%' // 그래프 너비 설정 %
				},
				legend : {
					position : 'none' // 항목 표시 여부 (현재 설정은 안함)
				}
			};

		var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
		chart.draw(data, options);
		}
		
	
	</script>
		</c:if>
  		<%-- </c:if> --%>
 	<%-- </c:if> --%>
  </head>
<body>
				
		<c:if test="${user.role== 'admin' }">
		
		<form name="detailForm" method="post" action="/purchase/mainView">
			<h3 align="center">조회할 날짜를 선택하세요.</h3>
				
				<p align="center"><input type="text" name="manuDate" readonly="readonly" class="ct_input_g"  
							style="width: 100px; height: 19px"	maxLength="10" minLength="6"
							id="manuDate" value="${pday}"/>
					&nbsp;<img src="../images/ct_icon_date.gif" width="15" height="15" 
					onclick="show_calendar('document.detailForm.manuDate', document.detailForm.manuDate.value)"/>
				</p>
	   	<input type="hidden" name="start" id="start" value="${start}">
				
		<p align="center">
		<input type="button" value="상품조회수차트" onclick="chart()">
		<input type="button" value="상품조회수목록" onclick="lookuplist()">
		</p>
		<c:if test="${start=='chart'}">		
		<h4 align="center">${day} 의 조회수 차트입니다.</h4>
		
		<!-- 차트호출 -->
		<div id="chart_div"></div>
		</c:if>
		
		</form>
		
				
		<c:if test="${!empty lookuplist}">
		<c:if test="${!empty start && start=='yes'}">
			<h4 align="center">${day} 의 조회수 목록입니다.</h4>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
			<tr>
				<td class="ct_list_b" width="100">상품명</td>
				<td class="ct_line02"></td>
				<td class="ct_list_b" width="100">조회수</td>
				<td class="ct_line02"></td>
				<td class="ct_list_b" width="150">판매량</td>
				<td class="ct_line02"></td>
			</tr>
				
			<c:forEach var="lookup" items="${lookuplist}">
			<tr>
			<td align="center">${lookup.prodName}</td>
			<td></td>
			<td align="center">${lookup.lookup }</td>
			<td></td>
			<td align="center"></td>
			<td></td>
			</tr>
			</c:forEach>
		
		</table>
		</c:if>
		
		</c:if>
		
		<c:if test="${empty lookuplist && user.role== 'admin'}">
			<h3 align="center">선택된 날짜의 데이터가 없습니다. 다른 날짜를 선택해 주세요</h3>
		</c:if>
		</c:if>
		
<c:if test="${user.role!= 'admin' }">
		<h3 align="center">★오늘의 인기상품★</h3>
		  <div class="slider">
		  	<c:forEach var="pro" items="${list}">
		    <div>
			
			<c:if test="${!empty pro.fileName }">
		    <img src="/images/uploadFiles/${pro.fileName}" width="40%"  align="left">
		    </c:if>
		    <c:if test="${empty pro.fileName }">
		    <img src="/images/uploadFiles/notimage.gif" width="40%"  align="left">
		    </c:if>
		  
		    <table width="40%" height="60%" border="0" cellspacing="0" cellpadding="0" style="margin-top:13px;">
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			
			<tr>
				
				<td width="104" class="ct_write">
					상품번호 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
				</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="105">${pro.prodNo}</td>
							<td>	</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			
			<tr>
				<td width="104" class="ct_write">
					상품명 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle">
				</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01">${pro.prodName}</td>
			</tr>
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			<tr>
				<td width="104" class="ct_write">상품상세정보</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01">${pro.prodDetail}</td>
			</tr>
		
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			<tr>
				<td width="104" class="ct_write">제조일자</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01">
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="26">${pro.manuDate}</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			
			<tr>
				<td width="104" class="ct_write">가격</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01">${pro.price}</td>
			</tr>
			
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			
			<tr>
				<td width="104" class="ct_write">등록일자</td>
				<td bgcolor="D6D6D6" width="1"></td>
				<td class="ct_write01">${pro.regDate}</td>
			</tr>
		
			<tr>
				<td height="1" colspan="3" bgcolor="D6D6D6"></td>
			</tr>
			</table>
   	 </div>
    </c:forEach>
    
 	 </div>

 </c:if> 
 
  </body>
</html>