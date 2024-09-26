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
	<!-- ex12_request.jsp -->
	<h1>요청 시 발생 정보들</h1>
	
	<p>요청 URL: <%= request.getRequestURI() %></p>
	<p>요청 서버 도메인: <%= request.getServerName() %></p>
	<p>요청 서버 포트: <%= request.getServerPort() %></p>
	<p>쿼리 문자열: <%= request.getQueryString() %></p>
	<p>요청 메소드 방식: <%= request.getMethod() %></p>
	
	<p>컨텍스트 경로: <%= request.getContextPath() %></p>
	
	<div>
		<a href="ex11_form.jsp">11번 문제</a>
		<a href="<%= request.getContextPath() %>/ex11_form.jsp">11번 문제2</a> <!-- /jsp/~ -->
	</div>
	
	<p>클라이언트 주소: <%= request.getRemoteAddr() %></p>
	<p>클라이언트: <%= request.getHeaders("user-agent")%></p>
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="https://bit.ly/4cMuheh"></script>
	<script>
		
	</script>
</body>
</html>






