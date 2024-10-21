<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.9.0/styles/stackoverflow-light.css">
	
	<link rel="stylesheet" href="http://bit.ly/3WJ5ilK">
	
<script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.9.0/highlight.min.js"></script>
	
	
	<style>
		table th {width: 120px;}
		table td {width: 263px;}
		
		#code {
			margin: 1rem;
			border: 1px solid var(--border-color);
		}
		
		#code * {
			font-family: consolas;
		}
	</style>
</head>
<body>
	<!-- view.jsp -->
	<h1>Code <small>보기</small></h1>
	
	<table>
		<tr>
			<th>번호</th>
			<td>${dto.seq}</td>
			<th>날짜</th>
			<td>${dto.regdate}</td>
		</tr>
		<tr>
			<td colspan="4">${dto.subject}</td>
		</tr>
	</table>
	
	<pre id="code"><code class="language-${dto.language}"><c:out value="${dto.code}"></c:out></code></pre>
	
	<div>
		<button type="button" class="back" onclick="history.back();">BACK</button>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="https://bit.ly/4cMuheh"></script>
	<script>
		hljs.highlightAll();
	</script>
</body>
</html>





