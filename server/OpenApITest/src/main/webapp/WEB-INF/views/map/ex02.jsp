<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="http://bit.ly/3WJ5ilK">
	<style>
		#map {
			width: 770px;
			height: 400px;
		}
	</style>
</head>
<body>
	<!-- ex02.jsp -->
	<h1>Kakao Map ! ! _2<small>좌표 이동  or 레벨 변경</small></h1>
	
	<div>
		<div id="map"></div>
	</div>	
	
	<hr>
	
	<div>
		<input type="button" value="한독 빌딩" id="btn1">
		<input type="button" value="상암 경기장" id="btn2">
		<input type="button" value="역삼역" id="btn3">
		<input type="button" value="확대" id="btn4">
		<input type="button" value="축소" id="btn5">
	</div>
	
	
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ccb6453e4414e18006795efd30b2a6ad"></script>
	<script src="https://bit.ly/4cMuheh"></script>
	<script>
		
		//지도를 담을 영역의 DOM 레퍼런스
		var container = document.getElementById('map'); 
		
		//지도를 생성할 때 필요한 기본 옵션
		var options = { 
		center: new kakao.maps.LatLng(37.499317, 127.033276), //지도의 중심좌표.
		level: 3 //지도의 레벨(확대, 축소 정도)
	};

		//지도 생성 및 객체 리턴
		var map = new kakao.maps.Map(container, options); 
	
		$('#btn1').click(()=>{
			
			const pos = new kakao.maps.LatLng(37.499317, 127.033276);
			//map.setCenter(pos);
			map.panTo(pos);
			
		});
	
		$('#btn2').click(()=>{
			
			const pos = new kakao.maps.LatLng(37.569358, 126.898242);
//			map.setCenter(pos);
			map.panTo(pos);
			
		});
	
		$('#btn3').click(()=>{
			
			const pos = new kakao.maps.LatLng(37.500567, 127.036046);
//			map.setCenter(pos);
			map.panTo(pos);
			
		});
	
		$('#btn4').click(()=>{
			
			//map.setLevel(1);
			map.setLevel(map.getLevel() -1);
			
		});
	
		$('#btn5').click(()=>{
			
			//map.setLevel(14);
			map.setLevel(map.getLevel() +1);
			
		});
		
		map.setDraggable(false);
		map.setZoomable(false);
	
	</script>
</body>
</html>






