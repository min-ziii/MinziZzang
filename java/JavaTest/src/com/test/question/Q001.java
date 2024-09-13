package com.test.question;

import java.util.Scanner;

public class Q001 {
	
	public static void main(String[] args) {
	
		// 태어난 연도를 입력받아 나이를 출력하시오.
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("태어난 년도: ");
		
		int age = scan.nextInt();
		
		System.out.printf("나이: %d세", 2024-age);
		
		scan.close();
	}
}

// CodeReview

// 2024-age로 나이를 표현했지만 year 변수와 age 변수를 따로 만들어서 빼놓는게 코드 보기가 좀 더 편하지 않을까?
// 변수 선언하면서 동시에 scan.nextInt()로 사용자에게서 받아왔는데, 선언 따로 받아오는 절차 따로 하는 것보다 성능적으로 이득이 있는지?