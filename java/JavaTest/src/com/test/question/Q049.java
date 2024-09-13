package com.test.question;

import java.util.Scanner;

public class Q049 {
	public static void main(String[] args) {
		// 아래와 같이 출력하시오.
		// 별 출력도 있고 System.out.print(" "); 공백 출력도 있음
		
		Scanner scan = new Scanner(System.in);
		System.out.print("행: ");
		int line = scan.nextInt();
		
		for (int i = 0; i < line; i++) {
			for (int k = 0; k < i; k++) {
				System.out.print(" ");
			}
			for (int j = i; j < line; j++) {
				System.out.print("*");
			}
			
			System.out.println();
		}
		scan.close();
	}
}
