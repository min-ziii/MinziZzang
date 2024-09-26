<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<script src="https://kit.fontawesome.com/fc01a97dc6.js" crossorigin="anonymous"> /* fontawesome */
	</script>
	<link rel="stylesheet" href="http://bit.ly/3WJ5ilK">
	<style>
		
	</style>
</head>
<body>
	<!-- ex11_form.jsp -->
	<h1>버튼 만들기</h1>
	
	<form method="POST" action="ex11_ok.jsp">
	<table class="vertical">
		<tr>
			<th>너비(px)</th>
			<td><input type="number" min="20" max="300" step="10" name="width" value="120"></td>
		</tr>
		<tr>
			<th>높이(px)</th>
			<td><input type="number" min="10" max="200" step="10" name="height" value="30"></td>
		</tr>
		<tr>
			<th>텍스트</th>
			<td><input type="text" name="txt" value="Button"></td>
		</tr>
		<tr>
			<th>배경색</th>
			<td><input type="color" name="bgcolor" value="#FFFFFF"></td>
		</tr>
		<tr>
			<th>글자색</th>
			<td><input type="color" name="txtcolor" value="#000000"></td>
		</tr>
		<tr>
			<th>글자 크기(px)</th>
			<td><input type="number" min="10" max="100" step="1" name="fontsize" value="16"></td>
		</tr>
		<tr>
			<th>버튼 개수(ea)</th>
			<td><input type="number" min="1" max="30" step="1" name="btnea" value="1"></td>
		</tr>
		<tr>
			<th>버튼 간격</th>
			<td>
				<div>
					<label for="horizontal">좌우 간격 :</label> <input type="range"
							id="horizontal" min="0" max="100" name="horizontal_interval"
							value="0">
				</div>
				<div>
					<label for="vertical">상하 간격 : </label> <input type="range"
							id="vertical" min="0" max="100" name="vertical_interval"
							value="0">
				</div>
			</td>
		</tr>
		<tr>
			<th>아이콘</th>
			<td>
			<label><input type="radio" name="icon" value="없음">없음</label>
			</td>
		</tr>
		<tr>
			<th>테두리</th>
			<td>
			<select name="sel1">
				<option value="감추기">감추기</option>
				<option value="보이기">보이기</option>
					</td>
		</tr>
	</table>
	<div>
		<input type="submit" value="만들기">
		<!-- <input type="button" value="보내기">
		<button>Send</button> -->
	</div>
	</form>
	
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="https://bit.ly/4cMuheh"></script>
	<script>
		
	</script>
</body>
</html>






