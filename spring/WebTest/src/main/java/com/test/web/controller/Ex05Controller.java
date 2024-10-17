package com.test.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.web.model.AddressDTO;

@Controller
public class Ex05Controller {

	@RequestMapping(value = "/ex05.do")
	public String ex05() {

		return "ex05";
	}

//1.
//	//doGet or doPost
//	@RequestMapping(value="/ex05ok.do", method= {RequestMethod.GET, RequestMethod.POST})
//	public String ex05ok(HttpServletRequest req) {
//		
//		String data = req.getParameter("data");
//		
//		System.out.println(data);
//		
//		req.setAttribute("data", data);
//		
//		return "ex05ok";
//	}

	/*
	 * 
	 * 파라미터 자동 수집
	 * 
	 * 
	 * 
	 * 
	 */
//2.
//	//doGet or doPost
//		@RequestMapping(value="/ex05ok.do", method= {RequestMethod.POST})
//		public String ex05ok(@RequestParam("data") String data, Model model) {
//			
//			//String data = req.getParameter("data");
//			
//			System.out.println(data);
//			
//			//req.setAttribute("data", data);
//			model.addAttribute("data", data);
//			
//			return "ex05ok";
//		}

//3.
//	//doGet or doPost
//	@RequestMapping(value="/ex05ok.do", method= {RequestMethod.POST})
//	public String ex05ok(@RequestParam(name="data", defaultValue="기본값") String data, Model model) {
//		
//		//list.do?page=1
//		//list.do
//		
//		//String data = req.getParameter("data");
//		
//		System.out.println(data);
//		
//		//req.setAttribute("data", data);
//		model.addAttribute("data", data);
//		
//		return "ex05ok";
//	}

//4.	
//	//doGet or doPost
//	@RequestMapping(value="/ex05ok.do", method= {RequestMethod.POST})
//	public String ex05ok(String num, Model model) {
//		
//		
//		System.out.println(num);
//		
//		model.addAttribute("data", num);
//		
//		return "ex05ok";
//	}

//5.	
//	//doGet or doPost
//	@RequestMapping(value="/ex05ok.do", method= {RequestMethod.POST})
//	public String ex05ok(
//							Model model,
//							@RequestParam("name") String name,
//							@RequestParam("age") String age,
//							@RequestParam("address") String address,
//							HttpServletRequest req
//					) {
//		
//		//req.setCharacterEncoding("UTF-8");
//		
//		AddressDTO dto = new AddressDTO();
//		
//		dto.setName(name);
//		dto.setAge(age);
//		dto.setAddress(address);
//		
//		model.addAttribute("dto", dto);
//		
//		return "ex05ok";
//	}

//6.	
//	//doGet or doPost
//	@RequestMapping(value="/ex05ok.do", method= {RequestMethod.POST})
//	public String ex05ok(Model model, AddressDTO dto, int seq) {
//		
//		model.addAttribute("dto", dto);
//		
//		System.out.println(seq);
//		
//		return "ex05ok";
//	}

//7.	
//	//doGet or doPost
//	@RequestMapping(value="/ex05ok.do", method= {RequestMethod.POST})
//	public String ex05ok(Model model,
//						 //@RequestParam("cb") String[] cb
//						 @RequestParam("cb") ArrayList<String> list
//						 ) {
//		
//		System.out.println(Arrays.toString(cb));
//		System.out.println(list);
//		
//		return "ex05ok";
//	}

//8.	
	// doGet or doPost
//	@RequestMapping(value = "/ex05ok.do", method = { RequestMethod.POST })
//	public String ex05ok(@RequestParam("data") String data, Model model) {
//	@RequestMapping(value = "/ex05ok.do", method = { RequestMethod.POST })
//	public String ex05ok(@ModelAttribute("data") String data) {	
	@RequestMapping(value = "/ex05ok.do", method = RequestMethod.POST)
	public String ex05ok(@ModelAttribute("dto") AddressDTO dto) {
			
		
		return "ex05ok";
	}

}
