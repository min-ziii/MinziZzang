package com.test.question;

import java.util.Scanner;

public class Q003 {

	public static void main(String[] args) {
		
		// 사각형의 너비와 높이를 입력받아 넓이와 둘레를 출력하시오.
		// 조건1: 사각형 넓이 = 너비 * 높이
		// 조건2: 사각형 둘레 - 너비 * 2 + 높이 *2
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("너비(cm): ");
		int width  = scan.nextInt();		// 너비
		
		System.out.print("높이(cm): ");
		int height = scan.nextInt();		// 높이
		
		System.out.printf("사각형의 넓이는 %d㎠입니다.\n", width * height);
		System.out.printf("사각형의 둘레는 %dcm입니다.\n", (width * 2) + (height * 2));
		
		scan.close();
	}

}

// CodeReview

// 변수를 영어로 선언하는게 낫지 않는가?
// 특수기호 모음판이 java나 eclipse에 따로 있는가?
