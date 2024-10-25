package com.test.security.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		//403 > 하고 싶은 일을 추가
		//- 로그 기록
		//- 알림
		
		System.out.println("CustomAccessDeniedHandler 호출되었습니다.");
		
		response.sendRedirect("/security/accesserror.do");
		
		
	}

	
	
}
