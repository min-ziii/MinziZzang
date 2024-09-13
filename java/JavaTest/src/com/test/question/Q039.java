package com.test.question;

import java.util.Scanner;

public class Q039 {
	public static void main(String[] args) {
		// 아래와 같이 출력하시오.
		
		// 입력
		// 시작 숫자: 1
		// 종료 숫자: 10
		
		// 출력
		// 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10 = 55
		
		Scanner scan = new Scanner(System.in);
		System.out.print("시작 숫자: ");
		int numStart = scan.nextInt();
		System.out.print("종료 숫자: ");
		int numEnd = scan.nextInt();
		
		int sum = 0;
		
		for (int i=numStart; i < numEnd + 1; i++) {
			System.out.print(i + " + ");
			sum += i;
		}
		System.out.printf("\b\b= %d", sum); // backspace 두 번
		
		scan.close();
		
	}
}
