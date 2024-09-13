package com.test.question;

import java.util.Scanner;

public class Q012 {
	public static void main(String[] args) {
		// 숫자를 전달하면 '짝수' 혹은 '홀수'라는 단어를 반환하는 method를 선언하시오.
		
		Scanner scan = new Scanner(System.in);
		
		int n = scan.nextInt();
		
		String result = getNumber(n); 
		System.out.printf("입력하신 숫자 '%d'는(은) '%s'입니다.\n", n, result);
		
		scan.close();
		
	}
	
	public static String getNumber(int n) {
		
		int remainder = n % 2;
		String result = (remainder == 0) ? "짝수" : "홀수";
		
		return result;
	}
	
	
}
