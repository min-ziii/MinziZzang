package com.test.begin.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class TestController {

	@GetMapping("/hello")
	public String hello() {
		
		return "안녕";
	}
		
	
}
