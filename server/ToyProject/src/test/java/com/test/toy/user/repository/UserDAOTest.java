package com.test.toy.user.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.test.toy.user.model.UserDTO;

class UserDAOTest {
	
	private static UserDAO dao;
	
	@BeforeAll
	static void init() {
		dao = UserDAO.getInstance();
	}
	

	@Disabled
	@Test
	void TestRegister() {
		
		//DAO의 회원 가입 기능
		
		UserDAO dao = UserDAO.getInstance();
		
		UserDTO dto = new UserDTO();
		dto.setId("meme");
		dto.setPw("1111");
		dto.setEmail("meme@gmailcom");
		dto.setName("나야나");
		dto.setIntro("안녕~ 나야");
		dto.setPic("pic.pmg");
		
		int result = dao.register(dto);
		
		//System.out.println(result);
		
		assertEquals(1, result);
		
	}

	
	@Test
	void testLogin() {
		
		UserDAO dao = UserDAO.getInstance();
		
		String id = "lion";
		String pw = "1111";
		
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setPw(pw);
		
		UserDTO result = dao.login(dto);
		
		assertNotNull(result);
		//assertEquals(result, result);
		
	}
	
	
	@Disabled
	@Test
	void testLogin2() {
		
		UserDAO dao = UserDAO.getInstance();
		
		String id = "lion";
		String pw = "1343";
		
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setPw(pw);
		
		UserDTO result = dao.login(dto);
		
		assertNotNull(result);
		//assertEquals(result, result);
		
	}
	
	@Disabled
	@Test
	void testLogin3() {
		
		UserDAO dao = UserDAO.getInstance();
		
		String id = "lion";
		String pw = "1111";
		
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setPw(pw);
		
		UserDTO result = dao.login(dto);
		
		assertEquals("사자", result.getName());
		
	}
	
	
	@Test
	void testGetUser() {
		
		String id = "kelly";
		
		UserDTO dto = dao.getUser(id);
		
		assertNotNull(dto);
		assertEquals("켈리", dto.getName());
		
	}
	
}



