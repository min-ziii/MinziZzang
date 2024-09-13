package com.test.question;

import java.util.Scanner;

public class Q051 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.print("입력: ");
		int line = scan.nextInt();
		
		for (int i=1; i <= line; i++) {
			for (int j = line-i; j >= 0; j--) {
				System.out.print(" ");
			}
			for (int k=1; k <= (i * 2)-1; k++) {
				System.out.print("*");
			}
			System.out.println();
			
		}
		scan.close();

	}

}
