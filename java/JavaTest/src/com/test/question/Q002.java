package com.test.question;

import java.util.Scanner;

public class Q002 {

	public static void main(String[] args) {
		
		// 숫자 2개를 입력받아 연산식을 출력하시오.
		// 조건1: 출력 숫자에 천단위 표기
		// 조건2: 결과값은 소수 이하 1자리까지 표기
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("첫번째 숫자: ");
		int num01 = scan.nextInt();
		System.out.print("두번째 숫자: ");
		int num02 = scan.nextInt();
		
		double num01toDouble = (double)num01;
		double num02toDouble = (double)num02;
		
		int plus 	  		 = num01 + num02;
		int minus 	  		 = num01 - num02;
		int multi 	  		 = num01 * num02;
		double divide 		 = num01toDouble / num02toDouble;
		int remainder 		 = num01 % num02;
		
		System.out.printf("\n%d + %d = %,d", num01, num02, plus);
		System.out.printf("\n%d - %d = %,d", num01, num02, minus);
		System.out.printf("\n%d * %d = %,d", num01, num02, multi);
		System.out.printf("\n%.0f / %.0f = %,.1f", num01toDouble, num02toDouble, divide);
		System.out.printf("\n%d %% %d = %,d", num01, num02, remainder);
				
		scan.close();
	}

}

// CodeReview

// 변수 선언에서 형변환 과정을 따로 빼서 진행했는지 아닌지 정도만 차이가 있었다.
