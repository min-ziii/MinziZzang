package com.test.java.obj;

public class Ex30_Class {
	
	public static void main(String[] args) {
		
		//Ex30_Class.java
		/*
		
			클래스 역할
			1. 데이터 집합
				- 멤버 변수만 구현
			
			2. 데이터 집합 + 행동
				- 멤버 변수 구현 > 객체 특성(성질)
				- 멤버 메서드 구현 > 객체 행동
			
			3. 행동
				- 멤버 메서드만 구현
		
		*/
		
		Member m1 = new Member();
		
		m1.name = "홍길동"; //특성(Property), 속성(Attribute)
		m1.age = 20;
		m1.hello();
		
		
		Member m2 = new Member();
		
		m2.name = "아무개";
		m2.age = 25;
		m2.hello();
		
		
	}

}//class


class Member {
	
	//멤버 변수
	public String name;
	public int age;
	
	//멤버 메서드
	public void hello() {
		//System.out.println("안녕하세요.");
		System.out.printf("안녕하세요. 저는 %s입니다.\n", name);
	}
	
}




















