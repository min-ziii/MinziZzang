package com.test.question;

import java.util.Scanner;

public class Q045 {
	public static void main(String[] args) {
		// 사용자가 입력한 범위의 숫자까지 369 게임 과정을 출력하시오.
		// 조건: 최대 3자리까지만 입력하시오.
		
		Scanner scan = new Scanner(System.in);
		System.out.print("최대 숫자: ");
		int num = scan.nextInt();
		
		for (int i=1; i <= num; i++) {
			
			if (i % 3 == 0) {
				System.out.print("짝");
				divide(i);
				System.out.print(" ");
			} else {
				divide(i);
			System.out.print(i);
			System.out.print(" ");
			}
		}
	}

	private static void divide(int i) {
		
	}
}
