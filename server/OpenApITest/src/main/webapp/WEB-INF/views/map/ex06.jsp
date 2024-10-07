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
	<!-- ex06.jsp -->
	<h1>Kakao Map ! !_6<small>주소를 통해서 좌표 얻기</small></h1>
	
	<div>
		<div id="map"></div>
	</div>	
	
	<hr>
	<div>
		<input type="text" id="address" class="full" 
			placeholder="주소를 입력하세요.">
	</div>
	
	
	
	
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ccb6453e4414e18006795efd30b2a6ad&libraries=services"></script>
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
	
		
		$('#address').keydown(function(evt) {
		
			//if(evt.keyCode == 13) {
			if(evt.key == 'Enter') {
				
				search($(this).val());
				
			}
			
		});
		
		let m1 = null;
		
		function search(address) {
			
			const geocoder = new kakao.maps.services.Geocoder();
			
			geocoder.addressSearch(address, function(result, status){
				
				//console.log(result[0].x + ',' + result[0].y);
				
				if(status == kakao.maps.services.Status.OK){
					
					if (m1 != null) {
						m1.setMap(null);
					}
					
					m1 = new kakao.maps.Marker({
						position: new kakao.maps.LatLng(result[0].y, result[0].x)	
						
					});
					
					m1.setMap(map);
					
					map.panTo(new kakao.maps.LatLng(result[0].y, result[0].x));
					
					
				} else {
					alter('검색 실패');
				}
			
			});
			
		}
		
	
	</script>
</body>
</html>



<!-- http://localhost:8090/api/map.do?n=ex06 -->





