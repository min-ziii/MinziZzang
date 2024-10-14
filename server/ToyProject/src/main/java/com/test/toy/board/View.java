package com.test.toy.board;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.GpsDirectory;
import com.test.toy.board.model.BoardDTO;
import com.test.toy.board.repository.BoardDAO;

@WebServlet("/board/view.do")
public class View extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//1. 데이터 가져오기(seq)
		//2. DB 작업 > select
		//3. 결과(DTO) > JSP 호출하기
		
		HttpSession session = req.getSession();
		
		//1.
		String seq = req.getParameter("seq");
		String word = req.getParameter("word");
		String column = req.getParameter("column");
		String page = req.getParameter("page");
		
		//2.
		BoardDAO dao = BoardDAO.getInstance();
		
		//2.3 조회수 증가
		if (session.getAttribute("read") != null &&
				session.getAttribute("read").toString().equals("n")) {
			dao.updateReadcount(seq);
			session.setAttribute("read", "y");
		}
		
		BoardDTO dto = dao.get(seq);
		
		
		//2.5 DTO 조작
		
		//개행문자 처리
		String content = dto.getContent();
		content = content.replace("\r\n", "<br>");
		
		
		if (column.equals("content")) {
			//검색어 강조
			//"한승원 작가는 11일 방송된"
			// > "<span style='color:tomato;background-color:gold;'>한승원</span> 작가는 11일 방송된"
			content = content.replace(word, "<span style='color:tomato;background-color:gold;'>" + word + "</span>");
		}
		
		dto.setContent(content);
		
		
		//첨부파일 > 메타 데이터 가져오기
		if (dto.getAttach() != null
				&& !dto.getAttach().trim().equals("")
				&& (
					dto.getAttach().toLowerCase().endsWith(".jpg")
					|| dto.getAttach().toLowerCase().endsWith(".jepg")
					|| dto.getAttach().toLowerCase().endsWith(".gif")
					|| dto.getAttach().toLowerCase().endsWith(".png")
					)) {
			
			BufferedImage img = ImageIO.read(new File(req.getRealPath("/asset/place/"+dto.getAttach())));
			
			
			
			//GPS
			try {
				
				Metadata metadata = ImageMetadataReader.readMetadata(new File(req.getRealPath("/asset/place/"+dto.getAttach())));
				
				
				GpsDirectory gps =
				metadata.getFirstDirectoryOfType(GpsDirectory.class);
				
				if(gps.containsTag(GpsDirectory.TAG_LATITUDE)
						&& gps.containsTag(GpsDirectory.TAG_LONGITUDE)) {
					
					req.setAttribute("lat", gps.getGeoLocation().getLatitude());
					req.setAttribute("lng", gps.getGeoLocation().getLongitude());
					
				}
				
				
				
			} catch (Exception e) {
				System.out.println("View.doGet");
				e.printStackTrace();
			}
			
		}
		
		
		
		
		//3.
		req.setAttribute("dto", dto);
		req.setAttribute("column", column);
		req.setAttribute("word", word);
		req.setAttribute("page", page);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/view.jsp");
		dispatcher.forward(req, resp);
	}

}





















