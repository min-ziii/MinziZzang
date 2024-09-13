package com.test.java;

public class Ex16_LocalVariable {
	
	int b = 20; // 멤버 변수
	
	public static void main(String[] args) {
		// Ex16_LocalVariable.java
		
		/*
		   자바의 변수
		   - 선언된 위치, 역할
		   
		   1. 멤버 변수(Member Variable)
		   		- 클래스 수업
		   		- class 내에서 선언한 변수
		    
		   2. 지역 변수(Local Variable)
		    	- 여태 사용한 변수
		    	- method 내에서 사용한 변수
		    	- 변수 scope(사용 영역): 자신이 포함된 method 영역
		    
		   지역 변수의 생명 주기 (Life Cycle)
		   - 변수가 언제 태어나서(메모리 생성)~ 언제 죽는지(메모리 소멸)
		   - 생성: 변수 선언문이 실행될 때
		   - 소멸: 변수 선언문이 포함된 블럭을 빠져나갈 때
		   
		 */
		
		int a = 10; // main의 지역 변수
		
		test();
		
	} // main
	
	public static void test() {
		
		int a = 20; // test의 지역 변수
		System.out.println(a);
		
	}
	
	
}
