package com.test.question;

import java.util.Scanner;

public class Q040 {
	public static void main(String[] args) {
		// 아래와 같이 출력하시오.
		
		// 입력
		// 시작 숫자: 1
		// 종료 숫자: 10
				
		// 출력
		// 1 - 2 + 3 - 4 + 5 - 6 + 7 - 8 + 9 - 10 = -5
		
		Scanner scan = new Scanner(System.in);
		System.out.print("시작 숫자: ");
		int numStart = scan.nextInt();
		System.out.print("종료 숫자: ");
		int numEnd = scan.nextInt();
		
		int sum = 0;
		boolean flag = false;
		
		for (int i=numStart; i < numEnd + 1; i++) {
			System.out.print(i);
			
			if (flag == false) {
				sum += i;
				System.out.print(" - ");
				flag = true;
				
			} else {
				sum -= i;
				System.out.print(" + ");
				flag = false;
				
			}
		}
		System.out.printf("\b\b= %d", sum); // backspace 두 번
		
		scan.close();
		
	}
}
