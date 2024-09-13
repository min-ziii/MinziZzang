package com.test.question;

import java.util.Scanner;

public class Q032 {
	public static void main(String[] args) {
		// 주차 요금을 계산하시오.
		// 무료주차 30분 + 초과 10분당 2000원
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("[들어온 시간]");
		System.out.print("시: ");
		int hourIn = scan.nextInt();
		System.out.print("분: ");
		int minuteIn = scan.nextInt();
		System.out.println();
		System.out.println("[나간 시간]");
		System.out.print("시: ");
		int hourOut = scan.nextInt();
		System.out.print("분: ");
		int minuteOut = scan.nextInt();
		
		int time = 0;
		int money = 0;
		
		time = ((hourOut - hourIn) * 60) + (minuteOut - minuteIn);
		
		if (time < 40) {
			System.out.printf("주차 요금은 %d원입니다.", money);
		} else {
			money = ((time / 10) * 2000) - 6000;
			System.out.printf("주차 요금은 %d원입니다.", money);
		}
		
		scan.close();
		
	}
}
