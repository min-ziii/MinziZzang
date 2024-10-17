package com.test.web.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.web.model.AddressDTO;

@Controller
public class Ex06Controller {

	//요청 메서드의 반환 자료형
	
	//1. String
	//- JSP 파일명
	//- ViewResolver
//	@RequestMapping(value="/ex06.do", method=RequestMethod.GET)
//	@GetMapping(value="/ex06.do")
//	public String ex06() {
//		
//		return "ex06";
//	}
	
	//2. void
	//- 요청 주소와 동일한 이름의 JSP 페이지를 호출한다.
//	@GetMapping(value="/ex06.do")
//	public void ex06() {
//		
//	}
	
	//3. String + 키워드
	//- "redirect: URL"
	//- "forward: URL"
//	@GetMapping(value="/ex06.do")
//	public String ex06() {
//		
//		//resp.sendRedirect("/web/ex05.do");
//		//page.forward("/web/ex05.do");
//		
//		//return "redirect:/ex05.do";
//		
//		return "forward:/ex05.do";
//		
//	}

	//4. Stirng + 키워드 + QueryString
//	@GetMapping(value="/ex06.do")
//	public String ex06(RedirectAttributes rttr) {
//		
//		String page = "5";
//		String column = "subject";
//		String word = "java";
//		
//		rttr.addAttribute("page", page);
//		rttr.addAttribute("column", column);
//		rttr.addAttribute("word", word);
//		
//		//resp.sendRedirect("ex05.do?page5&column=subject&word=java")
//		
//		//return "redirect:/ex05.do?page=" + page + "&column=" + column + "&word=" + word;
//		
//		return "redirect:/ex05.do";
//	}

	
	//5. @ResponseBody
	//- JSON 반환
	//- Ajax 응답용
//	@GetMapping(value="/ex06.do")
//	public @ResponseBody int ex06() {
//		
//		int num = 100;
//		
//		return num;
//	}
	
	//6.
//	@GetMapping(value="/ex06.do")
//	public @ResponseBody AddressDTO ex06() {
//		
//		AddressDTO dto = new AddressDTO();
//		dto.setName("아리");
//		dto.setAge("24");
//		dto.setAddress("서울시 강남구");
//		
//		return dto;
//	}
	
	//7.
	@GetMapping(value="/ex06.do")
	public @ResponseBody ArrayList<AddressDTO> ex06() {
		
		ArrayList<AddressDTO> list = new ArrayList<>();
		
		AddressDTO dto = new AddressDTO();
		dto.setName("아리");
		dto.setAge("24");
		dto.setAddress("서울시 강남구");
		
		AddressDTO dto2 = new AddressDTO();
		dto2.setName("수리");
		dto2.setAge("25");
		dto2.setAddress("서울시 강북구");
		
		AddressDTO dto3 = new AddressDTO();
		dto3.setName("마리");
		dto3.setAge("26");
		dto3.setAddress("서울시 강동구");
		
		list.add(dto);
		list.add(dto2);
		list.add(dto3);
		
		return list;
	}
	 
} 




















