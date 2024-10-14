package com.test.toy.board.model;

import lombok.Data;

@Data
//@Getter
//@Setter
//@ToString
//@EqualsAndHashCode
//@RequiredArgsConstructor
public class BoardDTO {
	
	private String seq;
	private String subject;
	private String content;
	private String regdate;
	private int readcount;
	private String id;
	
	private String regtime; 		//가공된 작성시각
	private String name; 			//작성자 이름
	private double isnew; 			//최신글
	
	private String commentCount; 	//댓글 수
	private int thread; 			//답변형
	private int depth; 				//답변형
	private int ing;				//삭제 유무(1. 존재 0.상태 변화(삭제))
	private String attach;			//첨부파일(사진)
	
}










