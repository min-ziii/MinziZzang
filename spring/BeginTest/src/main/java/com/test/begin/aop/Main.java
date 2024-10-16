package com.test.begin.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		
		//주업무 객체
		//Memo memo = new MemoImpl();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("com/test/begin/aop/memo.xml");
		Memo memo = (Memo)context.getBean("memo");
		
		//글쓰기
		memo.add("스프링 수업 중..");
		
		//메모 읽기
		String content;
		try {
			content = memo.read(5);
			System.out.println(content);
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("예외처리");
		}
		
		//수정하기
		boolean result = memo.edit(10, "수정한 내용");
		System.out.println(result);
		
		//삭제하기
		result = memo.del(10);
		System.out.println(result);
		
		//비밀글
		memo.addSecret("비밀글");
		
	}

}
