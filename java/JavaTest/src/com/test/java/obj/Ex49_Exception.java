package com.test.java.obj;

import java.util.Scanner;

public class Ex49_Exception {

	public static void main(String[] args) {

		// Ex49_Exception.java
		/*
		   	예외, Exception
		   	- 런타임 오류
		   	- 컴파일러가 발견할 수 없고, 실행 중에 발견됨
		   	- 개발자가 미리 예측하고 사전 조치를 취해야 함
		   	
		   	예외 처리, Exception Handling
		   	1. 제어문 사용
		   	2. try catch문 사용
		 */

//		m1();
//		m2();
//		m3();
//		m4();
		try {
			m5();
		} catch (Exception e) {
			System.out.println("예외 처리");
		}
	}

	private static void m5() throws Exception {
		
		// 예외 미루기
		// main에서 method를 호출한 쪽에 예외처리를 넘기겠다.
		
		int n = 0;
		System.out.println(100 / n);
		
	}

	private static void m4() {
		Scanner scan = new Scanner(System.in);
		System.out.print("숫자: ");
		int num = scan.nextInt();
		
		try { 
			// 비즈니스 코드 영역
			System.out.println(100 / num);
		} catch (Exception e) {
			// 에러 처리 코드 영역
			System.out.println("에외 처리");
		} finally {
			//성공하든 실패하든 반드시 실행돼야 하는 코드가 있을 때(ex-자원 해제)
			System.out.println("finally");
			scan.close();
		}
		
		System.out.println("종료");
		
	}

	private static void m3() {
		
		try {
			int num = 0;
			System.out.println(100 / num); // throw new ArithmeticException()
		
			int[] nums = { 10, 20, 30 };
			System.out.println(nums[5]); // throw new ArrayIndexOutOfBoundsException()
		
			Scanner scan = null;
			scan.nextInt();	// throw new NullPointerException()
		} catch (ArithmeticException e) {
			System.out.println("0으로 나누기");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("배열 첨자 오류(범위를 벗어남)");
		} catch (NullPointerException e) {
			System.out.println("null 참조");
		} catch (Exception e) {
			System.out.println("예외 처리");
		}
				
	}

	private static void m2() {
		
		try {
			int num = 0;
			System.out.println(100 / num);
		} catch (ArithmeticException e) {
			System.out.println("0으로 나누려고 시도");
		}
		
		try {
			int[] nums = { 10, 20, 30 };
			System.out.println(nums[5]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("배열 첨자 오류(범위를 벗어남)");
		}
		
		try {
			Scanner scan = null;
			scan.nextInt();
		} catch (NullPointerException e) {
			System.out.println("null 참조");
		}
	}

	private static void m1() {
		
		// 요구사항) 숫자를 입력받고 업무를 처리하라.
		Scanner scan = new Scanner(System.in);
		
		System.out.print("숫자: ");
		int num = scan.nextInt();
		
		// ★예외 처리
		
		// 예외 조건문 사용
		if (num != 0 ) {
			// 비즈니스 코드(업무 코드, 원래 하려고 했던 일)
			System.out.printf("100 / %d = %d\n", num, 100 / num);
		} else {
			// 예외 처리 코드
			System.out.println("0을 입력하지 마시오.");
		}
		
		// try-catch 문 사용
		// -try문: 자신의 영역에서 오류가 발생하는지 감시
		// -catch문: 오류 발생 시 처리
		try {
		// 비즈니스 코드(업무 코드)
			System.out.println(111);
		System.out.printf("100 / %d = %d\n", num, 100 / num);
			System.out.println(222);
		} catch (Exception e) {
			// 예외 처리 코드
			System.out.println("0을 입력하지 마시오.");
		}
		
		scan.close();
	}

}
