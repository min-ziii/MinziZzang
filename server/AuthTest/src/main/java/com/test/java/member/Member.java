package com.test.java.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/member.do")
public class Member extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//인증받지 못한 사용자 차단
		HttpSession session = req.getSession();
		
		if (session.getAttribute("auth") == null) {
			
			//절대 경로
			//1. 클라이언트 > /root == webapp
			//2. 서버 > / == webapp
			//resp.sendRedirect("/auth/index.do"); 
			
			PrintWriter writer = resp.getWriter();
			writer.println("<html><body><script>");
			writer.println("alert('invalid access');");
			writer.println("location.href='/auth/index.do';");
			writer.println("</script></body></html>");
			writer.close();
			
			return;
			
		} else {
			
		}
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/member.jsp");
		dispatcher.forward(req, resp);
	}

}