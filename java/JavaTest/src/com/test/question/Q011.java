package com.test.question;

import java.util.Scanner;

public class Q011 {
	public static void main(String[] args) {
		// 숫자 2개를 입력받아 연산식을 반환하는 method를 선언하시오.
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("첫번째 숫자: ");
		int n1 = scan.nextInt();
		System.out.print("두번째 숫자: ");
		int n2 = scan.nextInt();
		
		String result = "";
		
		result = add(n1, n2);
		System.out.println(result);
		
		result = subtract(n1, n2);
		System.out.println(result);

		result = multiply(n1, n2);
		System.out.println(result);

		result = divide(n1, n2);
		System.out.println(result);

		result = mod(n1, n2);
		System.out.println(result);
		
		scan.close();
				
	}

	private static String add(int n1, int n2) {
		int add = n1 + n2;
		String result = n1 + " + " + n2 + " = " + add;
		return result;
	}
	
	private static String subtract(int n1, int n2) {
		int subtract = n1 - n2;
		String result = n1 + " - " + n2 + " = " + subtract;
		return result;
	}
	
	private static String multiply(int n1, int n2) {
		int multiply = n1 * n2;
		String result = n1 + " * " + n2 + " = " + multiply;
		return result;
	}
	
	private static String divide(int n1, int n2) {
		double divide = (double)n1 / (double)n2; // n1, n2 중 하나 이상을 Casting하지 않으면 목값만 구해지는 것 같다.
		String result = String.format("%d / %d = %.1f", n1, n2, divide); // 출력 안 하고 값만 담아두려고 할 때
		return result;
	}
	
	private static String mod(int n1, int n2) {
		int mod = n1 % n2;
		String result = n1 + " % " + n2 + " = " + mod;
		return result;
	}
}
