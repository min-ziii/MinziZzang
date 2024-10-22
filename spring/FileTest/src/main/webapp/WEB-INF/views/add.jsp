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
#attach-zone {
	border: 1px solid var(--border-color);
	background-color: var(--back-color);
	width: 300px;
	height: 100px;
}

#attach-zone .item {
	font-size: 12px;
	margin: 5px 10px;
}
</style>
</head>
<body>
	<!-- add.jsp -->

	<h1>
		파일 업로드 <small>단일 파일</small>
	</h1>

	<form method="POST" action="/file/addok.do"
		enctype="multipart/form-data" id="form1">
		<table class="vertical">
			<tr>
				<th>텍스트</th>
				<td><input type="text" name="txt" value="홍길동"></td>
			</tr>
			<tr>
				<th>파일</th>
				<td><input type="file" name="attach"></td>
			</tr>
		</table>

		<div>
			<button>보내기</button>
		</div>
	</form>


	<h1>
		파일 업로드 <small>다중 파일. multiple</small>
	</h1>

	<form method="POST" action="/file/multi_addok.do"
		enctype="multipart/form-data" id="form2">
		<table class="vertical">
			<tr>
				<th>텍스트</th>
				<td><input type="text" name="txt" value="홍길동"></td>
			</tr>
			<tr>
				<th>파일</th>
				<td><input type="file" name="attach" multiple></td>
			</tr>
		</table>

		<div>
			<button>보내기</button>
		</div>
	</form>

	<h1>
		파일 업로드 <small>다중 파일. File Drop</small>
	</h1>

	<form method="POST" action="/file/multi_addok.do"
		enctype="multipart/form-data" id="form3">
		<table class="vertical">
			<tr>
				<th>텍스트</th>
				<td><input type="text" name="txt" value="홍길동"></td>
			</tr>
			<tr>
				<th>파일</th>
				<div id="attach-zone"></div>
				<td><input type="file" name="attach" style="display: none;"></td>
			</tr>
		</table>

		<div>
			<button>보내기</button>
		</div>
	</form>

	<h1>
		파일 업로드 <small>다중 파일. Ajax</small>
	</h1>

	<form id="form4">
		<table class="vertical">
			<tr>
				<th>텍스트</th>
				<td><input type="text" name="txt" value="홍길동"></td>
			</tr>
			<tr>
				<th>파일</th>
				<td><input type="file" name="attach" multiple></td>
			</tr>
		</table>

		<div>
			<button type="button">보내기</button>
		</div>
	</form>


	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src="https://bit.ly/4cMuheh"></script>
	<script>
		
		function checkFile(filename, filesize){
			
			const maxSize = 104857670;
			const regex = /(.*)\.(exe|sh)$/gi;
			
			if (filesize > maxSize) {
				alert('단일 파일의 크기가 10MB 이하만 가능');
				return true;
			}
			
			if(regex.test(filename)) {
				alert('해당 파일은 업로드 불가');
				return true;
			}
			
			return false;
			
		}
	
		//폼이 전송되기 직전 > 첨부파일 검사!!
		$('#form1').submit(()=>{
			//alert($('input[name=attach]')[0].files[0].name);
			
			const filename = $('input[name=attach]')[0].files[0].name;
			const filesize = $('input[name=attach]')[0].files[0].size;
			
			if(checkFile(filename, filesize)) {
				
				event.preventDefault();
				return false;
			}
			
		});
	
		$('#attach-zone')
		.on('dragenter', e => {
			e.preventDefault();
			e.stopPropagation();
		})
		.on('dragover', e => {
			e.preventDefault();
			e.stopPropagation();
			})
		.on('dragleave', e => {
			e.preventDefault();
			e.stopPropagation();
		})
		.on('drop', e => {
			e.preventDefault();
			
			const files = e.originalEvent.dataTransfer.files;
			
			//console.log(files);
			
			if(files != null && files != undefined) {
				
				$('#attach-zone').empty();
				
				let temp = '';
				
				for (let i=0; i<files.length; i++){
					let filename = files[i].name;
					let filesize = files[i].size / 1024 / 1024;
					
					filesize = filesize < 1 ? filesize.toFixed(2): filesize.toFixed(1);
					
					temp += `
							<div class="item">
								<span>\${filename}</span>
								<span>\${filesize}MB</span>
							</div>
						`;
				}
				
				$('#attach-zone').append(temp);
				
				$('#form3 input[name=attach]').prop('files', files);
			}
			
		});
		
		
		$('#form4 button').click(()=>{
			
			//IE(X)
			const formData = new FormData();
			const files = $('#form4 input[name=attach]')[0].files;
			
			for (let i=0; i<files.length; i++) {
				formData.append('attach', files[i]);
			}
			
			formData.append('txt', $('#form4 input[name=txt]').val());
		
			$.ajax({
				
				type: 'POST',
				url: '/file/multi_addok.do',
				data: formData,
				processData: false,
				contentType: false,
				success: function(result) {
					
				},
				error: function(a,b,c) {
					console.log(a,b,c);
				}
				
			});
			
		});
		
	</script>
</body>
</html>





