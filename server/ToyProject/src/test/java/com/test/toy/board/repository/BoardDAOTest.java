package com.test.toy.board.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.test.toy.board.model.BoardDTO;

public class BoardDAOTest {
	
	private static BoardDAO dao;
	
	@BeforeAll
	static void init() {
		dao = BoardDAO.getInstance();
	}
	
	
	
	//@Disabled
	@DisplayName("게시판 글을 작성합니다.")
	@Test
	void testAdd() {
		
		BoardDAO dao = BoardDAO.getInstance();
		
		BoardDTO dto = new BoardDTO();
		dto.setId("hong");
		dto.setSubject("게시판");
		dto.setContent("내용");

		int result = dao.add(dto);
		
		
		assertEquals(1, result);
		
	}
	
	@Disabled
	@DisplayName("제목빼고 글을 작성합니다.")
	@Test
	void testAdd2() {
		
		BoardDAO dao = BoardDAO.getInstance();
		
		BoardDTO dto = new BoardDTO();
		dto.setId("hong");
		dto.setSubject(null);
		dto.setContent("내용");
		
		int result = dao.add(dto);
		
		
		assertEquals(1, result);
		
	}
	
	@Disabled
	@DisplayName("로그인을 안했는데 글을 작성합니다")
	@Test
	void testAdd3() {
		
		BoardDAO dao = BoardDAO.getInstance();
		
		BoardDTO dto = new BoardDTO();
		dto.setId(null);
		dto.setSubject(null);
		dto.setContent("내용");
		
		int result = dao.add(dto);
		
		
		assertEquals(1, result);
		
	}
	
	@DisplayName("목록을 가져옵니다.")
	@Test
	void testlist() {
		
//		ArrayList<BoardDTO> list = dao.list();
//		
//		
//		assertEquals(1, list.size());
//		
		
	}
	
	@DisplayName("(날짜를 가공해서)목록을 가져옵니다.")
	@Test
	void testlist2() {
		
//		ArrayList<BoardDTO> list = dao.list();
//		
//		
//		assertEquals(6, list.size());
//		
//		System.out.println(list.get(0).getRegtime());
//		System.out.println(list.get(4).getRegtime());
//		
		
	}
	
	@DisplayName("글 1개를 상세보기합니다.")
	@Test
	void testGet() {
		
		String seq = "2";
		
		BoardDTO dto = dao.get(seq);
		
		assertNotNull(dto);
		assertEquals("게시판", dto.getSubject());
		assertEquals("2", dto.getSeq());
		
	}

	@DisplayName("2번 글의 조회수를 증가시킵니다.")
	@Test
	void testUpdateReadCount() {
		
		String seq = "2";
		
		int count = dao.get(seq).getReadcount();
		
		dao.updateReadcount(seq); //0 > 1
		
		assertEquals(count + 1, dao.get(seq).getReadcount());
		
		
	}
	
	@DisplayName("제목/내용을 수정합니다.")
	@Test
	void testEdit() {
		
		Calendar now = Calendar.getInstance();
		
		BoardDTO dto = new BoardDTO();
		dto.setSeq("2");
		dto.setSubject("제목입니다." + String.format("%tT", now));
		dto.setContent("내용입니다." + String.format("%tT", now));
		
		int result = dao.edit(dto);
		
		assertEquals(1, result);
		
	}
	
	@DisplayName("게시물을 삭제합니다.")
	@Test
	void testDel() {
		
		String seq = "5";
		
		int result = dao.del(seq);
		
		assertEquals(1, result);
		
		
	}
	
}








