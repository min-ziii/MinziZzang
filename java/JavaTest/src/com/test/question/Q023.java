package com.test.question;

import java.util.Calendar;
import java.util.Scanner;

public class Q023 {
	public static void main(String[] args) {
		
		// 아빠와 딸의 생일을 입력받아 아빠가 딸보다 며칠을 더 살았는지 출력하시오.
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("아빠 생일(년): ");
		int dadYear = scan.nextInt();
		System.out.print("아빠 생일(월): ");
		int dadMonth = scan.nextInt();
		System.out.print("아빠 생일(일): ");
		int dadDate = scan.nextInt();
		System.out.print("딸 생일(년): ");
		int daughterYear = scan.nextInt();
		System.out.print("딸 생일(월): ");
		int daughterMonth = scan.nextInt();
		System.out.print("딸 생일(일): ");
		int daughterDate = scan.nextInt();
		
		Calendar dad = Calendar.getInstance();
		Calendar daughter = Calendar.getInstance();
		
		dad.set(dadYear, dadMonth - 1, dadDate);
		daughter.set(daughterYear, daughterMonth - 1, daughterDate);
		
		long lifeDays = (daughter.getTimeInMillis() - dad.getTimeInMillis()) / 1000 / 60 / 60 / 24;
		
		System.out.printf("아빠는 딸보다 총 %,d일을 더 살았습니다.", lifeDays);
		
		scan.close();
	}
}
