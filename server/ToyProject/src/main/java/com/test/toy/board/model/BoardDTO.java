package com.test.toy.board.model;

import lombok.Data;

@Data
/*
 @Data는 아래 항목을 다 포함
 
  @Getter
  @Setter
  @ToString
  @EqualsAndHashCode
  @RequiredArgsConstructor
 */

public class BoardDTO {
	
		private String seq;
		private String subject;
		private String content;
		private String regdate;
		private int readcount;
		private String id;

		
		private String regtime; //가공된 작성 시간
		private String name; //작성자 이름
		private double isnew; //최신글
		
}
