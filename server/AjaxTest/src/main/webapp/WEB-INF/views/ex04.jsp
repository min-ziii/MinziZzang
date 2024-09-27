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
	<!-- ex04.jsp -->
	
	<h1>Ajax</h1>
	
	<!-- 
	<div>
		<h3></h3>
		<div><input type="button" value="" id="btn"></div>
		<div id="div"></div>
	</div> 
	-->
	
	<div>
		<h3>1. GET + 데이터 가져오기</h3>
		<div><input type="button" value="확인" id="btn1"></div>
		<div id="div1"></div>
	</div>
	
	
	<div>
		<h3>2. GET + 데이터 보내기</h3>
		<div><input type="button" value="보내기" id="btn2"></div>
		<div><input type="text"  id="txt2"></div>
	</div>
	
	<div>
		<h3>3. GET + 데이터 주고 받기</h3>
		<div><input type="button" value="확인" id="btn3"></div>
		<div><input type="text"  id="txt3"></div>
		<div id="div3"></div>
	</div>
	
	<div>
		<h3>4. POST + 데이터 주고 받기</h3>
		<div><input type="button" value="확인" id="btn4"></div>
		<div><input type="text"  id="txt4"></div>
		<div id="div4"></div>
	</div>
	<!-- 여기까지 바닐라 -->
	
	
	<div>
		<h3>5. jQuery</h3>
		<div><input type="button" value="확인" id="btn5"></div>
		<div><input type="text"  id="txt5"></div>
		<div id="div5"></div>
	</div>
	
		
	
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="https://bit.ly/4cMuheh"></script>
	<script>
		
		$('#btn1').click(()=> {
			
			const ajax = new XMLHttpRequest();
			
			ajax.onreadystatechange = ()=>{
				if(ajax.readyState == 4 && ajax.status == 200) {
					$('#div1').text(ajax.responseText);
				}
			};
			//true(비동기) => 경우의 수가 많음, 도착이 일정하지 않음, 유저의 자유로움
			//false(동기)  => 페이지에 유저가 동작할 수 없음
			ajax.open('GET', '/ajax/ex04data.do', false);
			ajax.send();
			
		});
		
		$('#btn2').click(()=> {
			
			const ajax = new XMLHttpRequest();
			
			//서버로부터 가져올 데이터 없으면 ajax.onreadystatechange 구현 안함
			
			ajax.open('GET', '/ajax/ex04data.do?txt2=' + $('#txt2').val());
			ajax.send();
			
		});

		$('#btn3').click(()=> {
			
			const ajax = new XMLHttpRequest();
			
			ajax.onreadystatechange = ()=>{
				if(ajax.readyState == 4 && ajax.status == 200) {
					$('#div3').text(ajax.responseText);
				}
			};
			
			ajax.open('GET', '/ajax/ex04data.do?txt3=' + $('#txt3').val());
			ajax.send();
			
		});
		
		
		$('#btn4').click(()=> {
			
			const ajax = new XMLHttpRequest();
			
			ajax.onreadystatechange = ()=>{
				if(ajax.readyState == 4 && ajax.status == 200) {
					$('#div4').text(ajax.responseText);
				}
			};
			
			ajax.open('POST', '/ajax/ex04data.do');
			ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
			
			//POST 데이터 전송 (쿼리문 스트링을 따로 빼서 작성): 대용량 데이터O
			ajax.send('txt4=' + $('#txt4').val());
			
		});
		
		////////////////////////////////jQuery 버전////////////////////////////////
		/*
		function aaa() {
			function bbb() {
				
			}
		}
		aaa.bbb();
		*/
		
	
		//$ = jQuery
		$('#btn5').click(()=> {
			
		/*
			//ajax.send() 역할!
			$.ajax({
				
			//페이지 요청 정보
				type: 'GET',
				url: '/ajax/ex04data.do',
				
				//결과 수신 이벤트
				success: function(result) {
					$('#div5').text(result);
				}
			});
		*/	
			
		
			$.ajax({
				type: 'GET',
				url: '/ajax/ex04data.do',
				data: 'txt2=' + $('#txt5').val()
			});
		
		
		
		/*
			$.ajax({
				type: 'GET',
				url: '/ajax/ex04data.do',
				data: 'txt3=' + $('#txt5').val(),
				success: function(result) {
					$('#div5').text(result);
				}
				
			*/
	
		/*
			$.ajax({
				type: 'POST',
				url: '/ajax/ex04data.do',
				data: 'txt4=' + $('#txt5').val(),
				success: function(result) {
					$('#div5').text(result);
				}	
			});
		*/
		
	});
		
	
	</script>
</body>
</html>






