package com.test.toy.test;

import com.test.toy.user.model.UserDTO;
import com.test.toy.user.repository.UserDAO;

public class DAOTest {
	
	public static void main(String[] args) {
		
		//UserDAO dao1 = new UserDAO();
		//UserDAO dao2 = new UserDAO();
		
//		UserDAO dao1 = UserDAO.getInstance();
//		UserDAO dao2 = UserDAO.getInstance();
//		
//		System.out.println(dao1 == dao2);		//F
//		System.out.println(dao1.equals(dao2));	//F
		
		
		UserDAO dao = UserDAO.getInstance();
		
		UserDTO dto = new UserDTO();
		dto.setId("cow");
		dto.setPw("1111");
		dto.setEmail("cow@gmailcom");
		dto.setName("음매소");
		dto.setIntro("안녕");
		
		int result = dao.register(dto);
		System.out.println(result);
		
		//Junit 연결! build path > lib > classpath > lib 추가에서 junit5 apply
		
		dao.register(dto);
		
	}

}
