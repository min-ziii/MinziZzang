package com.test.question;

import java.util.Scanner;

public class Q030 {
	public static void main(String[] args) {
		// 영문자 1개를 입력받아 대/소문자 변환을 한 뒤 출력하시오.
		// 조건1: 유효성 검사를 하시오.(영문자만 입력 가능)
		// A = 65, a = 97
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("문자 입력: ");
		String letter = scan.next();
		
		char letterFirst = letter.charAt(0);
		int letterConvert = 0;
		
		if (letterFirst < 65 || letterFirst > 122) {
			System.out.println("영문자만 입력하시오.");
		} else if (letterFirst < 97) { // letterFirst가 영문 소문자
			letterConvert = letterFirst + 32;
			System.out.printf("'%c'의 소문자는 '%c'입니다.", letterFirst, letterConvert);
		} else { // letterFirst가 영문 대문자
			letterConvert = letterFirst - 32;
			System.out.printf("'%c'의 대문자는 '%c'입니다.", letterFirst, letterConvert);
		}
		
		scan.close();
	}
}
