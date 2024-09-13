package com.test.question;

import java.util.Scanner;

public class Q044 {
	public static void main(String[] args) {
	// 숫자를 N개 입력받아 아래와 같이 출력하시오.
	// 조건1: 누적값이 100을 넘어가는 순간 루프를 종료하시오.
	// 조건2 : 짝수는 더하고 홀수는 빼시오.
	
		Scanner scan = new Scanner(System.in);
	
		boolean flag = true;
		int num = 0;
		int sum = 0;
		String numbers = "";
	
		while (flag) {
			System.out.print("숫자: ");
			num = scan.nextInt();
		
			if (num % 2 == 0) {
				sum += num;
				numbers += " + " + num;
				if (sum >= 100) {
					numbers += " = " + sum;
					break;
				}
			} else {
				sum -= num;
				numbers += " - " + num;
			}
		}
		System.out.println(numbers);
		
		scan.close();
	}
}
