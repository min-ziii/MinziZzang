package com.test.toy.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.test.toy.user.model.UserDTO;
import com.test.toy.user.repository.UserDAO;
import com.test.toy.util.OutputUtil;

@WebServlet("/user/register.do")
public class Register extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/register.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
		//RegisterOk.java
		//1. 데이터 가져오기
		//2. DB 작업 > insert
		//3. 결과 처리
		
		//아래 코드 필터 등록!
		//req.setCharacterEncoding("UTF-8");
		
		// 인코딩 제대로 되는지 확인 > System.out.println(req.getParameter("name"));
		
		
		//req > MultipartRequest
		
		try {
			
			System.out.println(req.getRealPath("/asset/pic"));
			
			MultipartRequest multi = new MultipartRequest(
										req,
										req.getRealPath("/asset/pic"),
										1024 * 1024 * 10,
										"UTF-8",
										new DefaultFileRenamePolicy()
					
								);
			
			//1.
			String id = multi.getParameter("id");
			String pw = multi.getParameter("pw");
			String name = multi.getParameter("name");
			String email = multi.getParameter("email");
			String intro = multi.getParameter("intro");
			String pic = multi.getFilesystemName("pic");
			
			//System.out.println(pic == null);
			//System.out.println(pic == "");
			
			if(pic == null) {
				pic = "pic.png";
			}
			
			
			//2.
			UserDTO dto = new UserDTO();
			dto.setId(id);
			dto.setPw(pw);
			dto.setName(name);
			dto.setEmail(email);
			dto.setIntro(intro);
			dto.setPic(pic);
			
			//DAO의 역할 > DB 작업 수행
			//*** 객체의 역할 > 객체를 1개만 필요로 하는 경우 > 싱글톤(Singleton) 패턴
			//3.
			
			UserDAO dao = UserDAO.getInstance();
			
			int result = dao.register(dto);
			
			if (result == 1) {
				
				resp.sendRedirect("/toy/index.do");
				
			} else {
				
				OutputUtil.redirect(resp, "실패했습니다.");
				
			}
			
			
		} catch (Exception e) {
			System.out.println("Register.doPost");
			e.printStackTrace();
		}
		
	}

}

















