package com.test.java.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/admin/admin.do")
public class Admin extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		//인증받지 못한 사용자 차단
		HttpSession session = req.getSession();
		
		
		/*
		 	자바 short-circuit
		 	- 논리 연산 시 발생하는 정책
		 	
		 	int a = 10;
		 	
		 	if((a % 2 == 0) && a > 0) {
		 	
		 	}
		  
		  
		 */
		
				
		if (session.getAttribute("auth") == null || (int)session.getAttribute("lv") ==1 ) {
					
					
			PrintWriter writer = resp.getWriter();
			writer.println("<html><body><script>");
			writer.println("alert('invalid access');");
			writer.println("location.href='/auth/index.do';");
			writer.println("</script></body></html>");
			writer.close();
					
			return;		
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/admin.jsp");
		dispatcher.forward(req, resp);
	}

}