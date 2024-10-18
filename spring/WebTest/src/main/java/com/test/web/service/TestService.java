package com.test.web.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component
@Service
public class TestService {
	
	public void work() {
		System.out.println("주 업무를 진행합니다.");
	}
	
	public String get() {
		
		//DB or 조작
		
		return "스프링";
		
	}
}
