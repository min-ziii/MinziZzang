package com.test.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

	@GetMapping("/login.do")
	public @ResponseBody String login() {
		
		
		return "ok";
	}
}
