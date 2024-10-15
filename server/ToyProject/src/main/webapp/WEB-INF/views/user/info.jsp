<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<%@include file="/WEB-INF/views/inc/asset.jsp"%>
<style>
</style>
</head>
<body>
	<!-- info.jsp -->
	<%@include file="/WEB-INF/views/inc/header.jsp"%>

	<div id="main">
		<h1>
			회원 <small>개인정보</small>
		</h1>

		<table id="info">
			<tr>
				<td rowspan="3"><img src="/toy/asset/pic/${dto.pic}"></td>
				<th>이름</th>
				<td>${dto.name}</td>
				<th>아이디</th>
				<td>${dto.id}</td>
			</tr>
			<tr>
				<th>등급</th>
				<td>${dto.lv == '1' ? '일반회원' : '관리자'}</td>
				<th>이메일</th>
				<td>${dto.email}</td>
			</tr>
			<tr>
				<td colspan="4">${dto.intro}</td>
			</tr>
		</table>

		<h2 id="titleCalendar">
			<span> 
				<span>활동 내역</span> 
				<span>2024.10</span>
			</span> 
			<span>
				<span class="material-symbols-outlined" id="btnPrev">arrow_back_ios</span>
				<span class="material-symbols-outlined" id="btnNow">today</span>
				<span class="material-symbols-outlined" id="btnNext">arrow_forward_ios</span>
			</span>
		</h2>

		<table id="tblCalendar">
			<thead>
				<tr>
					<th>Sun</th>
					<th>Mon</th>
					<th>Tue</th>
					<th>Wed</th>
					<th>Thu</th>
					<th>Fri</th>
					<th>Sat</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>


	</div>

	<script>
		
		
		let year = 0;
		let month = 0;
		
		let now = new Date();
		year = now.getFullYear();
		month = now.getMonth();
		
		function create(year, month) {
			$('#titleCalendar span:first-child span:last-child').text(year + '.' + String(month + 1).padStart(2, '0'));
			
			//해당 월 1일의 요일
			let date = new Date(year, month, 1);
			let firstDay = date.getDay();
			
			//해당 월의 마지막 날
			//console.log(new Date(year, month + 1, 0));
			
			let lastDate = new Date(year, month + 1, 0).getDate();
			
			let temp = '';
			
			temp += '<tr>';

			for (let i=0; i<firstDay; i++){
			temp += '<td></td>';
			}
			
			
			//날짜 <td>
			for (let i=1; i<=lastDate; i++) {
				temp += '<td>';
				temp += `<span class="no" data-date="\${i}">\${i}</span>`;
				temp += '<div>';
				/* 
				temp += '<span class="lcnt">1</span>';
				temp += '<span class="bcnt">2</span>';
				temp += '<span class="ccnt">1</span>'; 
				*/
				temp += '</div>';
				temp += '</td>';
				
				if((i + firstDay)%7 == 0){
				temp += '</tr><tr>';
				}
			}
			
			temp += '</tr>';
			
			$('#tblCalendar tbody').html(temp);
			
		}
		
		
	
		create(year, month);
		loadCalendar(year, month);
		
		$('#btnPrev').click(()=>{
			now.setMonth(now.getMonth() -1);
			year = now.getFullYear();
			month = now.getMonth();
			create(year, month);
			loadCalendar(year, month);
		});
		$('#btnNow').click(()=>{
			now = new Date();
			year = now.getFullYear();
			month = now.getMonth();
			create(year, month);
			loadCalendar(year, month);
		});
		$('#btnNext').click(()=>{
			now.setMonth(now.getMonth() +1);
			year = now.getFullYear();
			month = now.getMonth();
			create(year, month);	
			loadCalendar(year, month);
		});
	
		
		function loadCalendar(year, month) {
			
			$.ajax({
				type: 'GET',
				url: '/toy/user/loadcalendar.do',
				data: {
					year: year,
					month: month + 1
				},
				dataType: 'json',
				success: function(result) {
					
					$(result).each((index, item)=>{
						
						let date = parseInt(item.regdate.split('-')[2]);
						
$('#tblCalendar span[data-date='+ date +']+div').append('<span class="lcnt">' + item.cnt + '</span>');
						
						if(item.bcnt > 0){
$('#tblCalendar span[data-date='+ date +']+div').append('<span class="bcnt">' + item.bcnt + '</span>');
						}
						
						if(item.ccnt > 0){
$('#tblCalendar span[data-date='+ date +']+div').append('<span class="ccnt">' + item.ccnt + '</span>');
						}
						//temp += '<span class="lcnt">1</span>';
						//temp += '<span class="bcnt">2</span>';
						//temp += '<span class="ccnt">1</span>';
						
						
					});
					
					
				},
				error: function(a,b,c){
					console.log(a,b,c);
				}
			});
		}
		
	</script>
</body>
</html>






