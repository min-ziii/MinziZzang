package com.test.java;

public class Ex17_Method {
	public static void main(String[] args) {
		// Ex17.Method.java
		
		/*
		   Method Overloading
		   - 같은 이름의 Method를 여러개 만드는 기술
		   - Method의 인자 리스트를 다양한 형태로 구성해서 같은 이름의 method를 여러개 만드는 기술
		   
		   Method Overloading을 하는 이유?
		   - 성능 향상 (X)
		   - 개발자에게 도움 (O)
		   
		   Method Overloading 구현이 가능한 조건
		   1. 매개변수 개수
		   1. 매개변수 자료형
		   
		   Method Overloading 구현이 불가능한 조건
		   1. 매개변수 이름
		   2. 반환값 자료형
		   
		   
		   Method를 만드는 중...
		   1. public static void test() {}				// O
		   2. public static void test() {}				// X, 1이 이미 있음
		   3. public static void test(int n) {}			// O, 1과 매개변수의 개수가 다름
		   4. public static void test(int m) {}			// X, 3과 매개변수의 개수와 타입이 같음
		   5. public static void test(String s) {}		// O, 3과 매개변수의 타입이 다름
		   6. public static void test(int n, int m) {}	// O 
		   7. public static int test() {}				// X, 호출 시 1과 구분할 수 없음
		   
		   
		   Method 호출을 하면 Method Overloading의 가능 여부에 대한 검증을 할 수 있다.
		   test();			// 1번 call
		   test(10);		// 3번 call
		   test("홍길동");	// 5번 call
		   test(10,20);		// 6번 call
		   
		 */
		
		// 요구사항01) 선을 그리는 Method를 구현하시오
		// 요구사항02) ----------로 그리시오
		// 요구사항02) **********로 그리시오
		drawLine();
		drawLine("$");
		
		System.out.println(100);
		
	} // main
	
	// println method를 쓰면서 우리는 이미 Overloading의 장점을 경험하고 있다.
	public static void println(int num)	{
		System.out.println(num);
	}
	public static void println(String txt)	{
		System.out.println(txt);
	}
	public static void println(boolean flag)	{
		System.out.println(flag);
	}
	
	public static void drawLine() {
		
		System.out.println("==========");
	}
	
//	public static void drawLine() {
//		
//		System.out.println("----------");
//	}
//	
//	public static void drawLine() {
//		
//		System.out.println("**********");
//	}

	public static void drawLine(String c) {
		
		System.out.print(c);
		System.out.print(c);
		System.out.print(c);
		System.out.print(c);
		System.out.print(c);
		System.out.print(c);
		System.out.print(c);
		System.out.print(c);
		System.out.print(c);
		System.out.print(c);
		System.out.println();
	}

	
}
