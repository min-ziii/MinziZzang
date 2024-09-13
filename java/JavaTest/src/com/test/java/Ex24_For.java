package com.test.java;

public class Ex24_For {
	public static void main(String[] args) {
		
		// Ex24_For.java
		/*
			중첩된 반복문
			- 단일 for문
			- 이중 for문
			- 3중 for문
			- ...
			
			for () {
			
			}
		
			for () {			
				for () {
				
				}
			}
			
			for () {
				for () {
					for () {
					}
				}
			}
			
			
			
			
		*/
		
		//달력 만들기 -> 년, 월
		//1. 해당 월의 마지막 일은 무엇인가?
		//2. 해당 월의 1일은 무

		
//		m1();
//		m2();
//		m3();
		m4();
		
	}

	private static void m4() {
		
		// 별찍기 (제어문 연습)
		
		// 5x5
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		System.out.println();
		
		for (int i = 0; i < 5; i++) {
			for (int j = i; j < 5; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		System.out.println();
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j <= i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		System.out.println();
		
	}

	private static void m3() {
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				
				if (i == 5 || j == 5) {
					continue;
				}
				System.out.printf("i: %d, j: %d\n", i, j);
			}
		}
		
	}

	private static void m2() {
		
		// 이궈궈단~~~~~~~~~~~~
		
		for (int dan = 2; dan <= 9; dan++) {
			System.out.println("==========");
			System.out.printf("    %d단\n", dan);
			System.out.println("==========");
			for (int i = 1; i <= 9; i++) {
				System.out.printf("%d x %d = %2d\n", dan, i, dan * i);
			}
		}
		
	}

	private static void m1() {

		//단일 for문
		for (int i = 0; i < 10; i++) {
			System.out.println("i: " + i);
		}
		System.out.println();
		
		//이중 for문 -> loop 변수가 2개
		for (int i = 0; i < 10; i++) { // 대회전
			
			for (int j = 0; j < 10; j++) { // 소회전
				
				// System.out.println("안녕하세요."); // 몇 번 실행되나? -> 100번
				System.out.printf("i: %d, j: %d\n", i, j);
			}
		}
		System.out.println();
		
		//3중 for문
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 60; j++) {
				for (int k = 0; k < 60; k++) {
//					System.out.printf("i: %d, j: %d, k: %d\n", i, j, k);
					System.out.printf("%d시, %d분, %d초\n", i, j, k);
				}
				System.out.println();
			}
		}
		
//		// 아파트
//		for (단지) {
//			for (동) {
//				for (층) {
//					for (호) {
//						
//					}
//				}
//			}
//		}		
	}
}
