package com.test.question;

import java.util.Scanner;

public class Q029 {
	public static void main(String[] args) {
		// 숫자 2개와 연산자 1개를 입력받아 연산 과정과 결과를 출력하시오.
		/*  정수만 입력하시오.(유효성 검사 필요없음)
			나머지 연산 결과는 소수이하 첫째자리까지 출력 하시오.
			연산자는 산술 연산자(+, -, *, /, %)만 입력하시오.
			
			입력1
			첫번째 숫자: 5 
			두번째 숫자: 3 
			연산자: *
			
			출력1
			5 * 3 = 15
			---------------------
			입력2
			첫번째 숫자: 10 
			두번째 숫자: 3 
			연산자: /
			
			출력2
			10 / 3 = 3.3
			---------------------
			입력3
			첫번째 숫자: 3 
			두번째 숫자: 2 
			연산자: >=
			
			출력3
			연산이 불가능합니다.
		*/
		
		Scanner scan = new Scanner(System.in);
		System.out.print("첫번째 숫자: ");
		int numFirst = scan.nextInt();
		System.out.print("두번째 숫자: ");
		int numSecond = scan.nextInt();
		System.out.print("연산자: ");
		String operator = scan.next();
		
		switch (operator) {
			case "+":
				System.out.printf("%d %s %d = %d", numFirst, operator, numSecond, numFirst + numSecond);
				break;
			case "-":
				System.out.printf("%d %s %d = %d", numFirst, operator, numSecond, numFirst - numSecond);
				break;
			case "*":
				System.out.printf("%d %s %d = %d", numFirst, operator, numSecond, numFirst * numSecond);
				break;
			case "/":
				if (numSecond == 0) {
					System.out.println("0으로는 나눌 수 없습니다.");
					break;
				} else {
					System.out.printf("%d %s %d = %.1f", numFirst, operator, numSecond, (double)numFirst / numSecond);
					break;
				}
			case "%":
				System.out.printf("%d %s %d = %d", numFirst, operator, numSecond, numFirst % numSecond);
				break;
			default:
				System.out.println("연산이 불가능합니다.");
				
		}
		scan.close();		
	}
}
