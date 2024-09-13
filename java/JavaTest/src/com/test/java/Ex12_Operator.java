package com.test.java;

import java.util.Scanner;

public class Ex12_Operator {
	
	public static void main(String[] args) {
		
		// Ex12_Operator.java
		
		// 요구사항) 사용자로부터 문자를 하나 입력받는다. 입력받은 문자가 영어 소문자인지, 아닌지 판별하는 프로그램을 작성하라.
		Scanner scan = new Scanner(System.in);
		
		System.out.print("문자를 하나만 입력해주세요: ");
		
		String word = scan.next();
		char wordFirst = word.charAt(0);	// 입력한 문자열의 첫 문자를 따오는 method
		String resultYes = "영어 소문자입니다.";
		String resultNo = "영어 소문자가 아닙니다.";
		
		String check = (wordFirst >= 'a' && wordFirst <= 'z') ? resultYes : resultNo;
		System.out.printf("입력하신 문자 '%s'은(는) %s", word, check);
		scan.close();
	}
}
