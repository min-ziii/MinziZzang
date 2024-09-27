package com.test.ajax;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ex03data.do")
public class Ex03Data extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//webapp > data.txt 절대 경로:\ 상대 경로:.\
		//String path = req.getRealPath("./data.txt");
		//System.out.println(path);X
		
		//File file = new File(path);
		//System.out.println(file.getAbsolutePath());X
		
		//System.out.println(file.exists());X
		
		//Scanner scan = new Scanner(file);
		
		//String data = scan.nextLine();
		
		int a = 0;
		a = 10 / a ;
		
		AjaxDAO dao = new AjaxDAO();
		String question = dao.getQuestion();

		//resp.sendRedirect("/ajax/ex03.do?data=" + URLEncoder.encode(question, "UTF-8"));
		
		//req.setAttribute("question", question);
		
		//RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/ex03ok.jsp");
		//dispatcher.forward(req, resp);
		
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain");
		PrintWriter writer = resp.getWriter();
		writer.print(question);
		writer.close();
		
	}

}