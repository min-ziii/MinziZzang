package com.test.java;

import java.util.Iterator;
import java.util.Scanner;

public class Ex22_For {
	public static void main(String[] args) {
		//Ex22_For.java
		/*
		    	
		    	반복문
		    	- 코드를 원하는 횟수만큼 반복실행하는 제어문
		    	- 생산성 + 유지보수를 위해 사용
		    	
		    	for 문
		    	
		    	for (초기식; 조건식; 증감식) {
		    	 	구현부;
		    	}
		    	
		    	
		 */
		
//		m1();
//		m2();
//		m3();
//		m4();
//		m5();
//		m6();
//		m7();
//		m8();
//		m9();
//		m10();
//		m11();
//		m12();
		m13();
		
	}
	
	private static void m13() {
		
		// 난수 만들기
		// -임의의 수 만들기
		
		// 1. Math class
		// 2. Random class
		
		
		for (int i = 0; i < 10; i++) {
											
			// 난수값 가공 예시1 - 소수점 이하 1자리의 값만 이용하기
			// Math.Random()으로 받은 숫자에 10을 곱한 뒤, 소수점 이하의 값은 버린다.
			// ex) 0.236341 -> 2
			// System.out.println((int)(Math.random() * 10));
			
			// 난수값 가공 예시2 - 주사위 굴리기 (1 ~ 6)
			// Math.Random()으로 받은 숫자에 6을 곱한 뒤, 소수점 이하의 값은 버린다.
			// 0.0 -> 0.0
			// 0.1 -> 0.6
			// 0.2 -> 1.2
			// 0.3 -> 1.8
			// 0.4 -> 2.4
			// 0.5 -> 3.0
			// 0.6 -> 3.6
			// 0.7 -> 4.2
			// 0.8 -> 4.8
			// 0.9 -> 5.4
			// 정수값만 보면 0 ~ 5까지 6개의 수가 나왔다.
			
			System.out.println((int)(Math.random() * 6) + 1);
			
			
		}
		
		
		
	}

	private static void m12() {
		
		// 지역 변수의 scope(영역)
		// -자신이 포함된 method 영역
		// -자신이 포함된 제어문 영역
		// -> Block Scope를 가진다. 라고 표현함
		
		int a = 10; // 영역 (m12)
		
		// 초기화
		// -초깃값을 몇으로 해야 하는데? -> 업무를 살펴보고 판단
		//								 -> 1. 필요한 값
		//								 -> 2. 최대한 피해를 주지 않는 값
		int b = 0;
		double c = 0.0;
		char d = '\0'; // NULL 문자
		boolean e = false;
		String f = "";
		
		if (a > 0) {
			
			System.out.println(a);
			b = a * 2; // 영역 (if)
			
			if (b < 0) {
				int g = 10;
			}
		}
		System.out.println(b); // 위에서 b를 초기화하지 않으면 a가 false일 때 error가 나는 경우가 있기 때문에 꼭 초기화를 해준다.
		
		int i = 0;
		for (i = 0; i < 10; i++) { // i를 여기서 처음 선언했다면 i의 block scope도 for 구문 내부만이다.
			System.out.println("반복");
		}
		System.out.println(i);
	}

	private static void m11() {

		int sum = 0;
		
		for (int i = 1; i <= 3; i++ ) {
			sum += i;
			System.out.print(i + " + ");
		}
		
		System.out.println("\b\b= " + sum); // backspace로 지우면 간단하게 처리되는데 Eclipse 콘솔에서는 안 먹히니까 windows 콘솔에 가서 치면 제대로 나옴
											// C:\class\code\java\JavaTest\bin>java.exe com.test.java.Ex22_For
		
	}

	private static void m10() {

		// 1 + 2 + 3 = 6을 그대로 출력하라
		// 이렇게 하지 마!
		int sum = 0;
		
		for (int i = 1; i <= 3; i++ ) {
			sum += i;
			System.out.print(i);
			
			if (i != 3) {
				System.out.print(" + ");
			}
		}
		System.out.println(" = " + sum);
		
	}

	private static void m9() {
		
		// 분기문 -> 단독 사용 불가. 조건문 or 반복문과 같이 사용함
		// - break -> 자신이 포함된 제어문(if 제외)을 탈출
		// - continue -> 자신이 포함된 제어문(if 제외)의 처음으로 돌아감
		
//		for (int i=1; i<=10; i++) {
//			
//			if (i == 5) {
//				break;
//			}
//			System.out.println(i);
//			
//			
//		}
		
//		for (int i=1; i<=10; i++) {
//			
//			if (i == 5) {
//				continue;
//			}
//			System.out.println(i);
//			
//			
//		}
		
		// 사용자에게 숫자를 입력받고 홀수의 합을 구함
		// - 숫자를 몇 개 입력받는지는 사용자 마음대로 함 (회전수를 미리 알 수 없다 -> 무한 루프)
		// - 0을 입력하면 프로그램을 종료
		
		int sum = 0;
		Scanner scan = new Scanner(System.in);
		
		for (;;) {
			System.out.print("숫자: ");
			int num = scan.nextInt();
			
			if (num == 0) {
				break;
			}
			
			if (num % 2 == 1) {
				// 홀수
				sum += num;
			} else {
				// 짝수
				System.out.println("짝수를 입력 -> 프로그램 종료");
				break;
			}
		}
		System.out.println("합: " + sum);
		
		
	}

	private static void m8() {
		
		// 무한 루프 (Infinite loop)
		
		// 약 21억 번 회전
//		for (int i=0; i<10; i--) {
//			System.out.println(i);
//		}
		
		// 무한 루프 직접 만들기
//		for (int i = 0; true; i++) {
//			System.out.println(i);
//		}
		
//		for (;;) {
//			System.out.println("실행문");
//		}		
	}

	private static void m7() {
		
		// 구구단 출력
		
		/*
		   	5 x 1 = 5
		   	5 x 2 = 10
		   	5 x 3 = 15
		   	...
		   	5 x 9 = 45		   	
		 */
		
		Scanner scan = new Scanner(System.in);
		System.out.print("단 입력: ");
		
		int dan = scan.nextInt();
		
		for (int i=1; i<10; i++) {
			
			System.out.printf("%d x %d = %2d\n", dan, i, dan * i); // %2d로 한자리수 결과값을 우측 정렬
		}
		
	}

	private static void m6() {
		
		// 사용자가 입력한 숫자들의 합 -> 10개 입력
		Scanner scan = new Scanner(System.in);
		
		int sum = 0;
		
		for (int i = 0; i < 10; i++) {
			
			// 사용자로부터 숫자를 입력받고 누적시키기
			System.out.print("숫자: ");
			int num = scan.nextInt();
			sum += num;
		}
		System.out.printf("합: %d", sum);
		
		
	}

	private static void m5() {
		
		// 요구사항) 1~ 사용자가 입력한 숫자까지의 합
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("숫자: ");
		int num = scan.nextInt();
		
		int sum = 0; // 누적 변수
		
		for (int i = 1; i <= num; i++) {
			sum += i;
		}
		
		System.out.printf("1 ~ %d = %d\n", num, sum);
		
	}

	private static void m4() {
		
		// 요구사항) 1~10까지의 합을 구하라. -> 누적 연산
		
		// sum = 0 + 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10
		
		// 1. 누적 변수 선언
		int sum = 0;
		
		// 2. 원하는 수열 만들기
		for (int i = 1; i <= 10; i++) {
			sum += i;
			// i(1) 1 = 0 + 1
			// i(2) 3 = (0 + 1) + 2
			// i(3) 6 = ((0 + 1) + 2) + 3
			// i(4) 10 = ((0 + 1) + 2) + 3) + 4
			// ...
			// i(10) 55 =(0 + 1 + ... + 9) + 10 
		}
		System.out.println(sum);
		
	}

	private static void m3() {

		// 반복문을 쓰는 목적
		// 1. 회전수 제어
		// 2. 루프 변수 제어 <-
		
		// ★업무를 구현하다보면 특정한 숫자의 패턴을 쓰는 경우가 많다. -> 반복문의 루프 변수를 활용하게 된다.
		
		
		// 요구사항) 숫자를 1 ~ 10까지 출력하라.
		System.out.println(1);
		System.out.println(2);
		System.out.println(3);
		System.out.println(4);
		System.out.println(5);
		System.out.println(6);
		System.out.println(7);
		System.out.println(8);
		System.out.println(9);
		System.out.println(10);
		System.out.println();
		
		
		int num = 1;
		System.out.println(num);
		num++;
		System.out.println(num);
		num++;
		System.out.println(num);
		num++;
		System.out.println(num);
		num++;
		System.out.println(num);
		num++;
		System.out.println(num);
		num++;
		System.out.println(num);
		num++;
		System.out.println(num);
		num++;
		System.out.println(num);
		num++;
		System.out.println(num);
		System.out.println();
		
		num = 1;
		for (int i = 0; i < 10; i++) {
			System.out.println(num);
			num++;
		}
		System.out.println();
		
		for (int i = 0; i < 10; i++) {
			System.out.println(i + 1);
		}
		System.out.println();
		
		for (int i = 1; i < 11; i++) {
			System.out.println(i);
		}
		System.out.println();
		
		for (int i = 1; i < 11; i += 2) {
			System.out.println(i);
		}
		System.out.println();
		
		for (int i = 2; i < 11; i += 2) {
			System.out.println(i);
		}
		System.out.println();
		
		// 1~100 사이의 7의 배수 출력하기
		for (int i = 7; i < 100; i += 7) {
			System.out.println(i);
		}
		System.out.println();
	}

	private static void m2() {
		
		// 반복문을 쓰는 목적
		// 1. 회전수 제어 <-
		// 2. 루프 변수 제어
		
		for (int i = 0; i < 5; i++) {
			System.out.println("실행문");
		}
		System.out.println();
		
		for (int i = 1; i <= 5; i++) {
			System.out.println("실행문");
		}
		System.out.println();
				
		for (int i = 123; i < 128; i++) {
			System.out.println("실행문");
		}
		System.out.println();
		
		for (int i = 5; i > 0; i--) {
			System.out.println("실행문");
		}
		System.out.println();
		
		for (int i = 1; i < 41; i += 10) {
			System.out.println("실행문");
		}
		System.out.println();
		
		
		// 5개의 for문은 전부 출력 결과가 같다.
		// 가장 보편적인 방법은?
		// - 루프 변수는 0부터 시작
		for (int i = 0; i < 5; i++) {
			System.out.println("실행문");
		}
		System.out.println();
		
	}

	private static void m1() {
		// 요구사항) "안녕하세요." x5 출력
		System.out.println("안녕하세요.");
		System.out.println("안녕하세요.");
		System.out.println("안녕하세요.");
		System.out.println("안녕하세요.");
		System.out.println("안녕하세요.");
		
		hello();
		hello();
		hello();
		hello();
		hello();
		
		// 순서
		
		// 1. int i = 0; -> 초기식 실행
		
		// 2. i < 5; -> 조건식 실행
		// 3. 구현부 실행
		// 4. i++ -> 증감식 실행
		
		// 5. i < 5; -> 조건식 실행
		// 6. 구현부 실행
		// 7. i++ -> 증감식 실행
		
		// 8. ... i가 i < 5를 만족할 때까지 계속 반복
		

		for (int i = 0; i < 5; i++) {
			System.out.println("hello~");
		}
		
		
	}

	private static void hello() {
		System.out.println("반갑습니다.");
	}
}
