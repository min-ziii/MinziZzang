package com.test.thymeleaf.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString //한글 잘 가져옴
public class AddressDTO {
	
		private String seq;
		private String name;
		private String age;
		private String address;
		private String gender;


}
