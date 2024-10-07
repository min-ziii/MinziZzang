<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="http://bit.ly/3WJ5ilK">
	<link href="/api/star/star-rating.css" rel="stylesheet">
	<style>
		#map1 {
			width: 1170px;
			height: 400px;
			border: 1px solid #ccc;
			padding: 3px;
		}
		.main > button {
			float : right;
			border: 0;
			background-color: #FFF;
			margin-top: 20px;
		}
		.main > button:hover {
			color: tomato;
		}
		#map2 {
			width: 490px;
			height: 300px;
			border: 1px solid #ccc;
		}
		#form1 table {
			width: 490px;
		}
		#list {
			display: grid;
			grid-template-columns: repeat(3, 383px);
			gap: 10px;
		}
		#list .item {
			border: 1px solid #CCC;
			border-radius: 3px;
			display: grid;
			grid-template-columns:84px auto;
			grid-template-rows:84px auto;
			cursor: pointer;
		}
		#list .item:hover {
			border-color: #555;
		}
		#list .item > img {
			margin: 10px;
		}
		#list .item > div > div:first-child {
			margin-top: 15px;
			color: cornflowerblue;
			font-weight: bold;
		}
		#list .item > div:last-child {
			grid-column:1/3;
			padding: 15px;
		}
	</style>
</head>
<body class="wide">
	<!-- food.jsp -->
	<h1 class="main">
		Food<small>맛집 리스트</small><button class="add" data-modal-button="add">추가</button>
	</h1>
	
	<div>
		<div id="map1"></div>
	</div>
	
	<hr>
	
	<div id="list">
		<!-- <div class="item">
			<img src="/api/marker/bakery.png">
			<div>
				<div>장소명</div>
				<div>주소</div>
				<div>별점</div>
			</div>
				<div>메뉴</div>
		</div> -->
	</div>
	
	
	
	<!-- 팝업창 -->
	<div data-modal-window="add" data-modal-title="맛집 추가하기">
		<div id="map2"></div>
		<form id="form1">
		<table>
			<tr>
				<th>장소명</th>
				<td><input type="text" name="name" id="name" class="full"></td>
			</tr>
			<tr>
				<th>카테고리</th>
				<td>
					<select name="category" id="category">
						<c:forEach items="${clist}" var="cdto">
						<option value="${cdto.seq}" 
						data-img="${cdto.img}">${cdto.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			 <tr>
				<th>주소</th>
				<td><input type="text" name="address" id="address" class="full"></td>
			</tr>
			<tr>
				<th>별점</th>
				<td>
				<select class="star-rating" name="star" id="star">
   			 	<option value="">Select a rating</option>
    			<option value="5">Excellent</option>
    			<option value="4">Very Good</option>
    			<option value="3" selected>Average</option>
    			<option value="2">Poor</option>
    			<option value="1">Terrible</option>
				</select>
			</td>
			</tr>
			<tr>
				<th>추천 메뉴</th>
				<td><textarea name="menu" id="menu" class="full"></textarea></td>
			</tr>
		</table>
		<div>
			<button class="ok" data-modal-ok="add">추가</button>
			<button type="button" class="cancel" data-modal-cancel="add">취소</button>
		</div>
		<input type="hidden" name="lat">
		<input type="hidden" name="lng">
		</form>
	</div>
	
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ccb6453e4414e18006795efd30b2a6ad&libraries=services"></script>
	
	<script src="/api/star/star-rating.js"></script>
	
	<script src="https://bit.ly/4cMuheh"></script>
	<script>
	
		let map;
		let m2 = null;
		
		function init() {
			
		const container = document.getElementById('map1'); 
	
		const options = { 
		center: new kakao.maps.LatLng(37.499317, 127.033276),
		level: 3 
	};

		map = new kakao.maps.Map(container, options); 
		}
		
		
		
		function addMap() {
		
		const container = document.getElementById('map2'); 
	
		
		const options = { 
		center: new kakao.maps.LatLng(37.499317, 127.033276), 
		level: 3 
	};

		const map = new kakao.maps.Map(container, options); 
		
		let m1 = null;
		
		kakao.maps.event.addListener(map, 'click', function(evt){
			
			if(m1 != null) {
				m1.setMap(null);
			}
			
			let img = null;
			$('#category').children().each((index, item)=> {
    			
    			if(item.selected) {
    				//alert($(item).data('img'));
    				img = $(item).data('img');
    			}
			});
			
			const path = '/api/marker/' + img;
			const size = new kakao.maps.Size(64, 64);
			const op = {
				offset: new kakao.maps.Point(32, 96)
			};
			const mImg = new kakao.maps.MarkerImage(path, size, op);
			
			m1 = new kakao.maps.Marker({
				//position: new kakao.maps.LatLng(37.499347, 127.033207)
				position: evt.latLng
			});
			
			m1.setImage(mImg);
			m1.setMap(map);
			
			$('input[name=lat]').val(evt.latLng.getLat());
			$('input[name=lng]').val(evt.latLng.getLng());
			
			//추가한 장소의 주소 검색하기
			const geocoder = new kakao.maps.services.Geocoder();
			geocoder.coord2Address(evt.latLng.getLng()
									, evt.latLng.getLat()
									, function(result, status){
				
			//alert();
			
			if(status == kakao.maps.services.Status.OK){
				
				//console.log(result);
				$('#address').val(result[0].address.address_name);
				
				
			} else {
				alert('주소를 찾지 못했습니다.');
			}
			
			});
			
	    	});//addMap > clcik
		
			const stars = new StarRating('#star');
	    	
	    	//카테고리 변경 시 마커 이미지도 같이 변경
	    	$('#category').change(function(){
	    		
	    		//alert($(this).val());
	    		//alert($().data('img'));
	    		
	    		$(this).children().each((index, item)=> {
	    			
	    			if(item.selected) {
	    				//alert($(item).data('img'));
	    				
	    				const path = '/api/marker/' + $(item).data('img');
	    				const size = new kakao.maps.Size(64, 64);
	    				const op = {
	    					offset: new kakao.maps.Point(32, 96)
	    				};
	    				const mImg = new kakao.maps.MarkerImage(path, size, op);
	    				
	    				m1.setImage(mImg);
	    			}
	    			
	    		});
	    		
	    	});
	    	
		
		$('#form1 .ok').click(()=>{
			//alert($('#form1').serialize());
			$.ajax({
				type: 'POST',
				url: '/api/addfood.do',
				/* data: {
					lat: 111,
					lng: 222,
					name: '중국집',
					category: 1
				} */
				data: $('#form1').serialize(),
				
				dataType: 'json',
				success: function(result){
					
				},
				error: function(a, b, c) {
					console.log(a, b, c);
				}
				
			});
			
		});
		
		}//addMap
		
		init();
		
		$('.main .add').click(()=>{
			setTimeout(addMap,100);
		});
		
		
		function load() {
			
			$.ajax({
				type: 'GET',
				url: '/api/listfood.do',
				dataType: 'json',
				success: function(arr) {
					
					$('#list').html('');
					
					$(arr).each((index, item)=> {
						
						$('#list').append(`
								
								<div class="item" data-lat="\${item.lat}" data-lng="\${item.lng}" data-img="\${item.category.img}">
								<img src="/api/marker/\${item.category.img}">
								<div>
									<div>\${item.name}</div>
									<div>\${item.address}</div>
								<div>
			<select class="star" disabled>
				<option value="5">강추해요</option>
				<option value="4">좋아요</option>
				<option value="3">괜찮아요</option>
				<option value="2">그냥 그래요</option>
				<option value="1">별로예요</option>
			</select>
								</div>
								</div>
									<div>
									\${item.menu.replace(/\r\n/g, '<br>')}
									</div>
							</div>
						
						`);//append
						
						$('#list > div:last-child select').val(item.star);
									
					});
					
					new StarRating('.star');
					$('.star').parent().css('--gl-tooltip-background','transparent'); 
					
					$('.item').click(()=>{
				
						$('.item').css('background-color', 'transparent');
						
						$(event.currentTarget).css('background-color','rgba(225,215,0,.25)');
						
				const lat = $(event.currentTarget).data('lat');
				const lng = $(event.currentTarget).data('lng');
				const img = $(event.currentTarget).data('img');
				
				console.log(lat, lng, img);
				
						if(m2 != null) {
							m2.setMap(null);
						}
						
						const path = '/api/marker/' + img;
						const size = new kakao.maps.Size(64, 64);
						const op = {
							offset: new kakao.maps.Point(32, 96)
						};
						const mImg = new kakao.maps.MarkerImage(path, size, op);
						
						m2 = new kakao.maps.Marker({
							position: new kakao.maps.LatLng(lat, lng)
						});
						
						m2.setImage(mImg);
						m2.setMap(map);
						
						map.panTo(new kakao.maps.LatLng(lat, lng));
						
						$('html, body').animate({
			                scrollTop: 0
			            }, 500);
						
					});
					
				},
				error: function(a,b,c) {
					console.log(a,b,c);
				}
				
			});
			
		}
		
		load();
		
		
	</script>
</body>
</html>






