package com.test.question;

import java.util.Scanner;

public class Q005 {
	public static void main(String[] args) {
		
		// 바퀴 지름이 26inch인 자전거의 페달을 밟은 횟수를 입력하면 자전거가 몇 m를 달렸는지 출력하시오.
		// 조건1: 기어비는 1:1이다. (페달이 1회전하면 자전거 바퀴도 1회전한다.)
		// 조건2: 모든 출력 숫자는 천단위로 끊어서 표기되도록 한다.
		// 1 inch == 0.0254m
		// 원의 둘레 == 2 * π * 반지름
				
		Scanner scan = new Scanner(System.in);
		
		System.out.print("사용자가 페달을 밟은 횟수: ");
		
		int pedal = scan.nextInt(); // 페달 밟은 횟수
		int diameterInch = 26; // 지름, inch 단위
		final double PI = 3.14; // 원주율
		
		double circumferenceMeter = diameterInch * PI * 0.0254;
		
		double distance = pedal * circumferenceMeter;
		
		System.out.printf("사용자가 총 %,d회 페달을 밟아 자전거가 총 %,fm를 달렸습니다.", pedal, distance);
		
		scan.close();
	}
}

// CodeReview

// 원주율 PI는 Math class 안에 method로 선언되어 있다. Math.PI로 호출 가능
