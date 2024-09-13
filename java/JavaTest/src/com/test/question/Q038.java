package com.test.question;

import java.util.Scanner;

public class Q038 {
	public static void main(String[] args) {
		// 입력 횟수(N)와 숫자 N개를 입력받아 짝수의 합과 홀수의 합을 각각 출력하시오.
		
		Scanner scan = new Scanner(System.in);
		System.out.print("입력 횟수: ");
		int repeat = scan.nextInt();
		
		int num = 0;
		int odd = 0;
		int oddCount = 0;
		int even = 0;
		int evenCount = 0;
		
		for (int i=0; i < repeat; i++) {
			System.out.print("숫자: ");
			num = scan.nextInt();
			
			switch(num % 2) { 
				case 0:
					evenCount++;
					even += num;
					break;
				case 1:
					oddCount++;
					odd += num;
					break;
			}
		}
		System.out.printf("짝수 %d개의 합: %d\n", evenCount, even);
		System.out.printf("홀수 %d개의 합: %d\n", oddCount, odd);
		scan.close();
	}
}
