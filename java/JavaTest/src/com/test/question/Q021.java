package com.test.question;

import java.util.Calendar;
import java.util.Scanner;

public class Q021 {
	public static void main(String[] args) {
		
		// 태어난 연도를 입력하면 나이를 출력하시오.
		// 조건1: 올해를 기준으로 나이를 계산하시오.
		// 조건2: 내년, 내후년에 실행해도 그때에 맞는 나이를 출력한다.
		// 조건3: 우리나라 나이로 출력하시오. (+1 하란 뜻)
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("태어난 연도: ");
		int birthYear = scan.nextInt();
		
		Calendar now = Calendar.getInstance();
		birthYear = now.get(Calendar.YEAR) - birthYear + 1;
		
		System.out.printf("나이: %d세", birthYear);
		scan.close();
	}
}
