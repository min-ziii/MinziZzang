package com.test.question;

import java.util.Scanner;

public class Q031 {
	public static void main(String[] args) {
		// 숫자 5개를 입력받아 짝수와 홀수의 개수를 출력하시오.
		// for문말고 따로 입력받으시오.
		
		Scanner scan = new Scanner(System.in);
		
		int num = 0;
		int odd = 0;		
		int even = 0;
		
		for (int i = 0; i < 5; i++) {
			System.out.print("숫자 입력: ");
			num = scan.nextInt();
			
			if (num % 2 == 0) {
				even++;
			} else {
				odd++;
			}
		}
		System.out.printf("짝수는 %d개 홀수는 %d개 입력했습니다.\n", even, odd);
		
		if (even > odd) {
			System.out.printf("짝수가 홀수보다 %d개 더 많습니다.\n", even - odd);
		} else {
			System.out.printf("홀수가 짝수보다 %d개 더 많습니다.\n", odd - even);
		}
		scan.close();
	}
}
