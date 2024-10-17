<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>1017 다섯번째 예제</title>
	<link rel="stylesheet" href="http://bit.ly/3WJ5ilK">
	<style>
		
	</style>
</head>
<body>
	<!-- ex05.jsp -->
	<h1>입력</h1>
	
	<form method="POST" action="/web/ex05ok.do">
		<div>
			<input type="text" name="data">
		</div>
		<div>
			<input type="submit" value="보내기">
		</div>
	</form>
	
	<hr>

	<form method="POST" action="/web/ex05ok.do">
		<div><input type="text" name="name"></div>
		<div><input type="text" name="age"></div>
		<div><input type="text" name="address"></div>
		<div><input type="submit" value="Send"></div>
		
		<input type="hidden" name="seq" value="10">
	</form>
	
	<hr>
	
	<form method="POST" action="/web/ex05ok.do">
		<div><input type="checkbox" name="cb" value="java">Java</div>
		<div><input type="checkbox" name="cb" value="python">Python</div>
		<div><input type="checkbox" name="cb" value="basic">Basic</div>
		<div><input type="submit" value="Send"></div>
	</form>
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="https://bit.ly/4cMuheh"></script>
	<script>
		
	</script>
</body>
</html>





