package com.test.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//JSP Model 2 > (발전) > Spring MVC

//서블릿 역할
public class Ex01Controller implements Controller{
	
	//요청 매서드
	//doGet/doPost == handleRequest
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//업무처리
		
		//JSP 호출
		//RequestDispatcher > forward(req, resp)
		//System.out.println("정상작동");
		
		int count = 100;
		String id = "hong";
		
		request.setAttribute("count", count);
		
		
		//Model > 계층간 데이터 전송
		//View > 페이지
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("id", id);
		
		//Spring > View Reslover 동작(servlet-context.xml)
		
		//mv.setViewName("/WEB-INF/views/ex01.jsp");
		mv.setViewName("ex01");
		
		return mv;
	}
	
}




















