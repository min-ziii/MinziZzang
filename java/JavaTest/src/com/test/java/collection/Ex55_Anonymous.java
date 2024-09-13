package com.test.java.collection;

public class Ex55_Anonymous {
	public static void main(String[] args) {
		// Ex55_Anonymous.java
		/*
		   	익명 클래스, Anonymous Class
		   	- 익명 객체, Anonymous Object
		   	- 이름 없는 클래스
		   	
		   	프로젝트
		   	- 수많은 클래스를 만든다 -> 50개 ~ 수천 개 이상
		   	
		    실명 클래스
			- class BBB
			- 객체를 여러개 만들 수 있다.(장점)
			- 너무 많은 클래스를 관리하는 건 어렵다.
			
			익명 클래스
			- 객체를 여러개 만들 수 없다.(단점)
			- 객체를 평생 하나 만든다. (특징)
			- 객체 반복 없이 1회성으로만 필요할 경우 익명 클래스를 사용하면 클래스 관리에 유리해진다.
			
		 */
		
		// 요구사항) Interface를 구현한 클래스를 선언하고, 그 클래스로 객체를 만들어라.
		BBB b1 = new BBB();
		b1.test();
		
		AAA b2 = new BBB(); // Upcasting. 부모 타입으로 참조 변수를 생성
		b2.test();
		
		AAA b3 = new AAA() { // 익명 클래스로 만든 익명 객체
			@Override
			public void test() {
				System.out.println("익명 개체 메서드");	
			}
		};
		b3.test();
		
		AAA b4 = new AAA() {
			@Override
			public void test() {
				System.out.println();
			}
		};
		
	} // main
}

interface AAA {
	void test();
}

class BBB implements AAA {

	@Override
	public void test() {
		System.out.println("추상 Method를 구현함.");
		
	}
	
}