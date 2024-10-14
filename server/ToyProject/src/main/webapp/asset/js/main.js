//webapp > asset > js > main.js

//댓글 쓰기
//$(document).ready(() => {
window.onload = () => {
	$('#btnAddComment').click(() => {

		$.ajax({
			type: 'POST',
			url: '/toy/board/addcomment.do',
			data: {
				content: $('#addComment input[name=content]').val(),
				bseq: $('#view tr:nth-child(1) td:last-child').text()
			},
			dataType: 'json',
			success: function(result) {

				if (result.result == 1) {

					//alert('댓글 쓰기 성공!!');
					loadComment();
					$('#addComment input[name=content]').val('');

				} else {
					alert('댓글 쓰기 실패;;');
				}

			},
			error: function(a, b, c) {
				console.log(a, b, c);
			}
		});

	});//click


	//댓글 목록가져오기
	function loadComment() {

		$.ajax({
			type: 'GET',
			url: '/toy/board/listcomment.do',
			data: {
				bseq: $('#view tr:nth-child(1) td:last-child').text()
			},
			dataType: 'json',
			success: function(list) {

				$('#comment tbody').html('');

				$(list).each((index, item) => {

					let temp = `
					<tr data-seq="${item.seq}">
						<td>
							<div>${item.content}</div>
							<div>${item.regdate}</div>
						</td>
						<td>
							<div>
								<div>${item.name}(${item.id})</div>`;

					if (lv != 0 && (auth == item.id || lv == 2)) {

						temp += `<div>
									<span class="material-symbols-outlined" onclick="delComment(${item.seq});">delete</span>
									<span class="material-symbols-outlined" onclick="editComment(${item.seq});">edit_note</span>
								</div>`;
					}

					temp += `</div>
						</td>
					</tr>
					
					`;

					$('#comment tbody').append(temp);

				});

			},
			error: function(a, b, c) {
				console.log(a, b, c);
			}
		});

	}

	loadComment();


	$('#addComment input[name=content]').keydown((evt) => {
		if (evt.keyCode == 13) {
			$('#btnAddComment').click();
		}
	});


	//좋아요와 싫어요
	$('#btnGood').click(() => {


		$.ajax({
			type: 'POST',
			url: '/toy/board/goodbad.do',
			data: {
				state: 1,
				bseq: $(event.target).data('seq')
			},
			dataType: 'json',
			success: function(result) {
				//alert(result.result);
				loadGoodBad();
			},
			error: function(a, b, c) {
				console.log(a, b, c);
			}
		});

	});


	$('#btnBad').click(() => {
		
		$.ajax({
			type: 'POST',
			url: '/toy/board/goodbad.do',
			data: {
				state: 0,
				bseq: $(event.target).data('seq')
			},
			dataType: 'json',
			success: function(result) {
				//alert(result.result);
				loadGoodBad();
			},
			error: function(a, b, c) {
				console.log(a, b, c);
			}
		});

	});


	function loadGoodBad() {
		
		$.ajax({
			type: 'GET',
			url: '/toy/board/loadgoodbad.do',
			data: {
				bseq: seq
			},
			dataType: 'json',
			success: function(result) {
				//console.log(result);
				
				//alert(result.state);
				if(result.state == '1') {
					$('#btnGood').css('color', 'skyblue');
					$('#btnBad').css('color', '#555');
				} else if (result.state == '0') {
					$('#btnBad').css('color', 'tomato');
					$('#btnGood').css('color', '#555');
				}
				
				
				
				$(result.arr).each((index, item)=>{
					
					if(item.state == '1'){
						$('#good').text(item.cnt);
					} else if (item.state == '0'){
						$('#bad').text(item.cnt);
					}
					
				});
				
			},
			error: function(a,b,c) {
				console.log(a,b,c);
			}
		});
		
	}
	
	loadGoodBad();

};//ready



function delComment(cseq) {

	if (!confirm('정말 삭제하겠습니까?')) { return; }


	const tr = $(event.target).parents('tr');
	//alert(tr[0].nodeName);

	$.ajax({
		type: 'POST',
		url: '/toy/board/delcomment.do',
		data: {
			cseq: cseq
		},
		dataType: 'json',
		success: function(result) {

			if (result.result == 1) {
				//alert('성공');
				//alert(event.target);
				tr.remove();

			} else {
				alert('댓글 삭제 실패;;');
			}

		},
		error: function(a, b, c) {
			console.log(a, b, c);
		}
	});

}

let temp_content;

function editComment(cseq) {

	//이전 눌렀던 수정 폼을 되돌리기
	$('#comment tbody tr').each((index, item) => {
		if ($(item).children().eq(0).children().eq(0).children().length) {
			const content =
				$(item).children().eq(0).children().eq(0).children().eq(0).val();
			$(item).children().eq(0).children().eq(0).html('');
			$(item).children().eq(0).children().eq(0).text(content);

		}
	});



	const div = $(event.target).parents('tr').children().eq(0).children().eq(0);
	const content = div.text();
	temp_content = content;
	const seq = $(event.target).parents('tr').data('seq');

	div.html('');

	$('<input type="text" style="width: 535px;">')
		.val(content)
		//취소하기 esc
		.keydown((evt) => {
			if (evt.keyCode == 13) {

				const txt = evt.target;

				$.ajax({
					type: 'POST',
					url: '/toy/board/editcomment.do',
					data: {
						content: $(evt.target).val(),
						seq: seq
					},
					dataType: 'json',
					success: function(result) {

						if (result.result == 1) {

							let item = $(txt).parents('tr');

							const content =
								$(item).children().eq(0).children().eq(0).children().eq(0).val();
							$(item).children().eq(0).children().eq(0).html('');
							$(item).children().eq(0).children().eq(0).text(content);

						} else {
							alert('댓글 수정 실패');
						}

					},
					error: function(a, b, c) {
						console.log(a, b, c)
					}
				});

			} else if (evt.keyCode == 27) {



				let item = $(evt.target).parents('tr');

				const content = temp_content;
				$(item).children().eq(0).children().eq(0).children().eq(0).val();
				$(item).children().eq(0).children().eq(0).html('');
				$(item).children().eq(0).children().eq(0).text(content);
			}

		})
		.appendTo(div);



}













