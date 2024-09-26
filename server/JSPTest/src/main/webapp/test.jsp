<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="http://bit.ly/3WJ5ilK">
	<style>
		
	</style>
</head>
<body>
	<!-- ex25_application -->
	<%
		
		//session vs application
		//session
		//- 개인에게 할당되는 객체
		
		//application
		//- 웹응용프로그램(Web Application) == 사이트
		//- 프로그램에게 할당되는 객체
		
		if (session.getAttribute("count") == null) {
			session.setAttribute("count", 1);
		} else {
			session.setAttribute("count", (int)session.getAttribute("count") + 1);
		}
	
	
		if (application.getAttribute("count") == null) {
			application.setAttribute("count", 1);
		} else {
			application.setAttribute("count", (int)application.getAttribute("count") + 1);
		}
		
	%>
	
	<h1>방문 횟수</h1>
	<div>session count: <%= session.getAttribute("count") %></div>
	<div>session count: ${sessionScope.count}</div>
	
	<div>application count: <%= application.getAttribute("count") %></div>
	<div>application count: ${applicationScope.count}</div>
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="https://bit.ly/4cMuheh"></script>
	<script>
		
	</script>
</body>
</html>





