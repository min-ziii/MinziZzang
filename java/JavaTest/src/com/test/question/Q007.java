package com.test.question;

import java.util.Scanner;

public class Q007 {

		public static void main(String[] args) {
		
		// 영문 소문자를 1글자 입력받은 후 대문자로 변환해서 출력하시오.
		// A = 65, a = 97
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("문자 입력: ");
		String letterLowercase    = scan.next();
		
		char   letterLowerFirst   = letterLowercase.charAt(0);
		int    letterUpperFirst   = letterLowerFirst - 32;
		
		System.out.printf("소문자 '%c'의 대문자는 '%c'입니다.", letterLowerFirst, letterUpperFirst);
		
		scan.close();

		
		
	}

}
