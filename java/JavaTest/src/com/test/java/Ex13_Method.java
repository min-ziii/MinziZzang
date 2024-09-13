package com.test.java;

public class Ex13_Method {
	
	// main method
	// -특수 method
	// -이름이 예약어(main)
	// -프로그램을 실행시키면 java가 최우선적으로 찾는 method
	// -프로그램의 시작점(Entry point)
	// -프로그램의 종착점(End point)
	public static void main(String[] args) {
			
		// Ex13_Method.java
		
		/*
		  	Method
		  	- 함수(Function), 프로시저(Procedure)
		  	- 같은 목적을 가진 코드의 집합
		  	- ★코드 재사용의 단위
		  	
		  	Method 사용
		  	1. 선언
		  	2. 호출(사용)
		  	
		  	
		 */
		
		// 요구사항) "안녕하세요."를 5번 출력하라.
		// 수정사항) "안녕하세요."를 "반갑습니다."로 수정하라.
		
		//하드 코딩
		System.out.println("반갑습니다.");
		System.out.println("반갑습니다.");
		System.out.println("반갑습니다.");
		System.out.println("반갑습니다.");
		System.out.println("반갑습니다.");
		
		// 선언한 method를 호출
		hello();
		
	} // main
	
	// method의 선언 위치(class 내부)
	// method는 class의 멤버이다.
	// method끼리는 선언 순서가 무관하다.
	
	
	/*
	   method header (=Signature)
	   
	   접근 지정자	정적 키워드	 반환 타입	 메서드명	인자 리스트
	   public		static		 void		 hello		() {
	   		method body (구현부) - 코드 작성
	   }
	 */
	
	public static void hello() {
		//hello method의 할 일
		System.out.println("안녕하세요.");
	}
	
} // class
