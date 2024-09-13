package com.test.question;

import java.util.Scanner;

public class Q004 {
	
	public static void main(String[] args) {
		
		// 섭씨온도를 입력받아서 화씨온도로 변환하시오.
		// 조건1: ℉ = ℃ × 1.8 + 32
		// 조건2: 소수 입력 가능
		// 조건3: 소수 이하 1자리까지 출력하시오.
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("섭씨(℃): ");
		
		double celcius	  = scan.nextDouble();
		double fahrenheit = celcius * 1.8 + 32;

		System.out.printf("섭씨 %.1f℃는 화씨 %.1f℉입니다.", celcius, fahrenheit);
		
		scan.close();
	}
}

// CodeReview

// 변수 선언을 Double로 했는데 scanner로 사용자 값을 입력받을 때 nextInt를 썼다. 코드 실행에는 이상이 없었는데 어떻게 바뀐거냐?