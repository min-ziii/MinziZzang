package com.test.java.obj;

public class Ex33_Class {
	
	public static void main(String[] args) {
		
		//Ex33_Class.java
		
		String[] nick = { "코딩초보", "에러마왕", "오타쟁이" };
		
		
		User u1 = new User();
		
		u1.setName("홍길동");
		u1.setId("hong");
		
		u1.setNick(nick);
		
		//u1.setNick("코딩초보");
		//u1.setNick("에러마왕");
		//u1.setNick("오타쟁이");
		
		//u1.setNick("코딩초보,에러마왕,오타쟁이");
				
		System.out.println(u1); //com.test.java.obj.User@73a28541
		
		//방금 만든 객체(u1)의 상태를 확인하고 싶다. > 개발자 업무
		System.out.println(u1.getName());
		System.out.println(u1.getId());
		System.out.println(u1.getNick());
		System.out.println();
		
		System.out.println(u1.info()); //디버깅용(덤프)
		
		System.out.println();
		System.out.println();
		
		//홍길동 별명?
		//System.out.println(u1.getNick());
		
		for (int i=0; i<u1.getNick().length; i++) {
			
			System.out.println("별명: " + u1.getNick()[i]);
			
		}
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		Person p1 = new Person();
		
		p1.setName("홍길동");
		p1.setAge(20);
		
//		String[] nickList = new String[3];
//		nickList[0] = "강아지";
//		nickList[1] = "고양이";
//		nickList[2] = "병아리";
		
//		p1.setNick(nickList);
		
		p1.addNick("강아지");
		p1.addNick("고양이");
		p1.addNick("병아리");
		
		
		System.out.println(p1.info());		
		
		System.out.println(p1.getNick(0));
		System.out.println(p1.getNick(1));
		System.out.println(p1.getNick(2));
		
	}

}




















