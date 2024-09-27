package com.test.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ex05data.do")
public class Ex05Data extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("ex05data");
		
		String id = req.getParameter("id");
		
		AjaxDAO dao = new AjaxDAO();
		
		System.out.println(id);
		
		int result = dao.check(id);
		
		PrintWriter writer = resp.getWriter();
		writer.print(result);
		writer.close();
		
	}

}