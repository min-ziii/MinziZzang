package com.test.question;

import java.util.Scanner;

public class Q036 {
	public static void main(String[] args) {
		// 시작 숫자, 종료 숫자, 증감치를 입력받아 숫자를 순차적으로 출력하시오.
		
		// 입력
		// 시작 숫자: 5
		// 종료 숫자: 12
		// 증감치: 1
		
		// 출력
		// 5
		// 6
		// 7
		// 8
		// 9
		// 10
		// 11
		// 12
		
		Scanner scan = new Scanner(System.in);
		System.out.print("시작 숫자: ");
		int numStart = scan.nextInt();
		System.out.print("종료 숫자: ");
		int numEnd = scan.nextInt();
		System.out.print("증감치: ");
		int increment = scan.nextInt();
		
		for (int i=numStart; i <= numEnd; i += increment) {
			System.out.println(i);
		}
		scan.close();
	}
}
