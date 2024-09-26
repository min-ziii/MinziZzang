<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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
	<!-- ex24_jstl.jsp -->
	<h1>JSTL</h1>
	
	<h2>변수 선언</h2>
	
	<%
		int a = 10; //지역 변수
		pageContext.setAttribute("b", 20); //pageContext 변수
	
	%>
	
	<!-- 
	
		set 변수는 pageContext 변수를 만드는 태그 
		
		JSTL 변수 == pageContext 변수
	-->
	<c:set var="c" value="30"></c:set>
	
	
	<div>a: ${a}</div>
	<div>a: <%= a %></div>
	
	<div>b: ${b}</div>
	
	<%-- <div>c: <%= c %></div> JSP주석 --%>
	<div>c: ${c }</div>
	<div>c: <%= pageContext.getAttribute("c") %></div>
	
	<h2>변수 수정</h2>
	
	<c:set var="c" value="300"/>
	<div>c: ${c }</div>
	
	<h2>변수 삭제</h2>
	
	<c:remove var="c"/>
	<div>c: ${c }</div>
	<div>c: ${empty c }</div>
	
	<h2>조건문</h2>
	
	<c:set var="num" value="0"/>
	
	<!-- ***내장 객체의 값을 꺼낼 때는 반드시 EL을 사용한다. -->
	<c:if test="${ num > 0}">
		<div>${num}은 양수입니다.</div>
	</c:if>
	
	<c:if test="${num <= 0 }">
		<div>${num }은 양수가 아닙니다.</div>
	</c:if>
	
	<h2>조건문</h2>
	<c:choose>
		<c:when test="${num > 0}">양수</c:when>
		<c:when test="${num < 0}">음수</c:when>
		<c:when test="${num == 0}">영</c:when>
		<c:otherwise>영</c:otherwise>
	</c:choose>
	
	<h2>반복문(일반 for + 향상된 for)</h2>
	
	<% for (int i=0; i<10; i++) { %>
		<div>숫자: <%= i %></div>
	<% } %>
	
	<hr>
	
	<c:forEach var="i" begin="0" end="9" step="1">
		<div>숫자: ${i}</div>
	</c:forEach>
	
	<%
		String[] names = {"홍길동","아무개","하하하","호호호","히히히"};
	
		pageContext.setAttribute("names", names);
	%>
	
	<table>
		<tr>
			<th>이름</th>
			<th>인덱스</th>
			<th>카운트</th>
			<th></th>
			<th></th>
			<th></th>
		</tr>
		<c:forEach items="${names}" var="name" varStatus="status">
		<tr>
			<td>${name}</td>
			<td>${status.index}</td>
			<td>${status.count}</td>
			<td>${status.current}</td>
			<td>${status.first}</td>
			<td>${status.last}</td>
		</tr>
		</c:forEach>
	</table>
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="https://bit.ly/4cMuheh"></script>
	<script>
		
	</script>
</body>
</html>






