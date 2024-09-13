package com.test.java;

import java.util.Scanner;

public class Ex23_While {
	public static void main(String[] args) {
		
		//Ex23_While.java
		
//		m1();
//		m2();
//		m3();
//		m4();
		m5();
		
	}

	private static void m5() {
		
		/*
		   -K&R Style
		   while (조건) {
		   		문장;
		   }
		   -Allman Style
		   while (조건)
		   {
		   		문장;
		   }
		   
		   -while: 선조건 후실행
		   -do while: 선실행 후조건
		   
		   do {
		   		문장;
		   } while(조건);
		   		   
		 */
		
		int num = 11;
		
		while(num <= 10) {
			System.out.println(num);
			num++;
		}
		System.out.println();
//-------------------------------------//		
		num = 11;
		
		do {
			System.out.println(num);
			num++;
		} while (num <= 10);
		
	}

	private static void m4() {
		
		// 자판기
		// - (메뉴 출력 -> 음료 선택 -> 가격 출력) x loop
		
		Scanner scan = new Scanner(System.in);
		
		boolean loop = true;
		// 반복문 밖으로 선언부를 빼서 메모리 관리
		String sel = "";
		int price = 0;
		
		while (loop) {
			System.out.println("==========");
			System.out.println("  자판기");
			System.out.println("==========");
			System.out.println("1. 콜라");
			System.out.println("2. 사이다");
			System.out.println("3. 박카스");
			System.out.println("4. 종료");
			System.out.println("==========");
			
			System.out.print("선택: ");			
			sel = scan.nextLine();
			
			if (sel.equals("1")) {
				price = 700;				
			} else if (sel.equals("2")) {
				price = 600;				
			} else if (sel.equals("3")) {
				price = 500;
			} else {
				// 프로그램 종료 -> 무한 루프 탈출
				loop = false;
			}
		}
		System.out.println(price + "원입니다.");
		System.out.println();
		System.out.println("계속하시려면 엔터 키를 누르세요.");
		scan.nextLine();
	}

	private static void m3() {
		
		// 1~10 누적 (for)
		int sum = 0;
		
		for (int i=1; i <=10; i++) {
			sum += i;
		}
		System.out.println(sum);
		
		// 1~10 누적 (while)		
		sum = 0;
		int num = 1;
		
		while (num <= 10) {
			sum += num;
			num++;
		}
		System.out.println(sum);
		
	}

	private static void m2() {

		/*
		   반복문
		   1. for
		   2. while
		   3. do while
		   4. for
		   
		   while (조건식) {
		   		
		   }
		   
		 */
		
		// 요구사항) 1~10 출력
		for (int i=0; i < 10; i++) {
			System.out.println(i);
		}
		System.out.println();
//-------------------------------------//	
		int num = 1; // 초기식
		
		while (num <= 10) { // 조건식
			System.out.println(num);
			num++; // 증감식
		}
		
		// for 무한 루프
//		for (;;) {
//			
//		}
		// while 무한 루프
//		while (true) {
//			
//		}
		
		
	}

	private static void m1() {

		Scanner scan = new Scanner(System.in);
		
		
		System.out.print("이름: ");
		String name = scan.nextLine();
		
		System.out.print("나이: ");
		int age = scan.nextInt();
		
//		scan.skip("\r\n"); // nextInt()가 버퍼 공간에서 삭제하지 않고 남겨둔 enter 값을 수동으로 지워줌. 정석.
		scan.nextLine();   // \r\n값을 지워주는 nextLine() method를 호출해서 enter 값을 지움
		
		System.out.print("주소: ");
		String address = scan.nextLine();

		
		System.out.println(name);
		System.out.println(address);
		System.out.println(age);
	}
}
