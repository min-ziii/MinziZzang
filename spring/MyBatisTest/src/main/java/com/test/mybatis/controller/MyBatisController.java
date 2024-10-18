package com.test.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.mybatis.dao.MyBatisDAO;
import com.test.mybatis.dto.AddressDTO;

@Controller
public class MyBatisController {

	//Controller > (의존) > DAO > (의존) > SqlSessionTemplate
	@Autowired
	private MyBatisDAO dao;
	
	
	@GetMapping(value="/test.do")
	public String test() {
		
		System.out.println(dao == null);
		
		return "list";
		
	}
	
	//반환값(X), 매개변수(X)
	@GetMapping(value="/m1.do")
	public String m1() {
		
		dao.m1();
		
		return "list";
	}
	
	
	@GetMapping(value="/m2.do")
	public String m2(Model model, @RequestParam(name="seq") String seq) {
		
		//삭제
		//- delete from tblAddress where seq= 11
		
		int result = dao.m2(seq);
		
		model.addAttribute("result", result);
		
		return "list";
	}
	

	
	@GetMapping(value="/m3.do")
	public String m3() {
		
		//수정
		//1	독수리	3	서울시 강북구 미아동	m
		
		AddressDTO dto = new AddressDTO();
		dto.setSeq("1");
		dto.setAge(4);
		dto.setAddress("서울시 강남구 대치동");
		dto.setGender("f");
		
		int result = dao.m3(dto);
		
		System.out.println(result);
		
		
		return "list";
	}
	
}















