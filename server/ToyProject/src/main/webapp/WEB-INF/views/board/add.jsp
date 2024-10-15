<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<%@include file="/WEB-INF/views/inc/asset.jsp"%>
	<link rel="stylesheet" href="/toy/asset/css/tagify.css">		
	<style>
		
	</style>
</head>
<body>
	<!-- add.jsp -->
	<%@include file="/WEB-INF/views/inc/header.jsp"%>	
	
	<div id="main">
		<h1 class="page">게시판
			<c:if test="${mode == 'new'}"><small>쓰기</small></c:if>
			<c:if test="${mode == 'reply'}"><small>답변</small></c:if>
		</h1>
		
		<form method="POST" action="/toy/board/add.do" enctype="multipart/form-data">
		<table class="vertical">
			<tr>
				<th>제목</th>
				<td><input type="text" name="subject" id="subject" required class="full"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content" id="content" required class="full"></textarea></td>
			</tr>
			<tr>
				<th>태그</th>
				<td><input type="text" name="tag" id="tag" class="full"></td>
			</tr>
			<tr>
				<th>장소</th>
				<td><input type="file" name="attach" class="full" accept="image/*"></td>
			</tr>
		</table>
		
		<div>
			<button type="button" class="back" onclick="location.href='/toy/board/list.do';">돌아가기</button>
			<button type="submit" class="add primary">쓰기</button>
		</div>
		<input type="hidden" name="mode" value="${mode}">
		<input type="hidden" name="thread" value="${thread}">
		<input type="hidden" name="depth" value="${depth}">
		</form>
	</div>
	
	
	<script src="/toy/asset/js/tagify.js"></script>
	<script>
		
		new Tagify(document.getElementById('tag'));
		
		/* 
		
		window.onclick = function(){
			//[{"value":"ㅇㅇ"},{"value":"자바"}] => 이렇게 뜸!
			alert(document.getElementById('tag').value);
		}; 
		
		*/

	
	</script>
</body>
</html>





