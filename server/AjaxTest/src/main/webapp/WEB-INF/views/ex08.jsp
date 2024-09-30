<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
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
	<!-- ex08.jsp -->
	
	<h1>고양이 + Ajax</h1>
	
	<!-- 
	<div><img src="images/catty01.png"></div> 상대 경로
	<div><img src="/ajax/WEB-INF/views/images/catty01.png"></div>
	<div><img src= "/ajax/images/catty01.png"></div> 절대 경로
	  -->
	
	<div><img src= "/ajax/images/catty01.png" id="cat1"></div>
	
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="https://code.jquery.com/ui/1.14.0/jquery-ui.js"></script>
	<script src="https://bit.ly/4cMuheh"></script>
	<script>
	
		//고양이의 위치값 저장 공간
		//1. 쿠키 > 양호, 접속 위치 불편
		//2. 세션 > 안좋음
		//3. DB > 아주 양호
	
		$('#cat1').draggable({
			stop: function(event, ui) {
				//alert(ui.position.left);
				//alert(ui.position.top);
				
				$.ajax({
					type: 'POST',
					url: '/ajax/ex08data.do',
					data: 'catid=' + this.id + '&left=' + ui.position.left + '&top=' + ui.position.top,
					error: function(a, b, c) {
						console.log(a, b, c);
					}
				});
				
				
			}
		});
		
		
		$('#cat1').css({
			left: '100px',
			top: '200px'
		});
		
		$('#cat1').css({
			left: '${dto.left}px',
			top: '${dto.top}'
			
			
		});
		
		
		
		
	</script>
</body>
</html>





