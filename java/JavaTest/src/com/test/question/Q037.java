package com.test.question;

import java.util.Scanner;

public class Q037 {
	public static void main(String[] args) {
		// 숫자 1개를 입력받아 1부터 입력한 숫자까지의 합을 출력하시오.
		
		Scanner scan = new Scanner(System.in);
		System.out.print("숫자: ");
		int end = scan.nextInt();
		
		int i = 0;
		int result = 0;
		
		for (i = 1; i <= end; i++) {
			result += i;
		}
		System.out.printf("1 ~ %d = %d", end, result);
		
		scan.close();
	}
}
