package com.test.toy;

import com.test.toy.user.model.UserDTO;

public class LombokTest {

	public static void main(String[] args) {
		
		UserDTO dto = new UserDTO();
		dto.setName("홍길동");
		
		dto.getId();
	}
}
