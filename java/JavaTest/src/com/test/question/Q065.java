package com.test.question;

import java.util.Scanner;

public class Q065 {
	public static void main(String[] args) {
		// 난수를 20개 발생 후 배열에 넣는다.
		// 난수는 1 ~ 20 사이
		// 배열을 탐색하여 범위에 만족하는 숫자만 출력한다.
		
		Scanner scan = new Scanner(System.in);
		
		int[] num = new int[20];

		for (int i = 0; i < num.length; i++) {
			num[i] = (int)(Math.random() * 20) + 1; // 1~20
		}
		
		System.out.print("최대 범위: ");
		int max = scan.nextInt();
		
		System.out.print("최소 범위: ");
		int min = scan.nextInt();
		
		for (int i = 0; i < num.length; i++) {
			if (num[i] > max) {
				max = num[i];
			}
		}
		
		System.out.printf("원본: ");
		for (int i=0; i < num.length; i++) {
			if (i == num.length - 1) {
				System.out.print(num[i]);
			} else {
				System.out.print(num[i] + ", ");
			}
			
		}
		System.out.println();
		
		System.out.printf("결과: ");
		for (int i=0; i < num.length; i++) {
			if (num[i] >= min && num[i] <= max) {
				if (i == num.length - 1) {
					System.out.print(num[i]);
				} else {
					System.out.print(num[i] + ", ");
				}
			}
		}
		scan.close();
	}
}
