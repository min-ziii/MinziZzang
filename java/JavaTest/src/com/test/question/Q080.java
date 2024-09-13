package com.test.question;

import java.util.Scanner;

public class Q080 {
	public static void main(String[] args) {
		// 문장을 입력받아 역순으로 출력하시오.
		Scanner scan = new Scanner(System.in);
		
		System.out.print("문장 입력:");
		String sentence = scan.nextLine();
		
		for (int i = 0; i < sentence.length(); i++) {
			System.out.print(sentence.charAt(sentence.length()-1-i));
		}
		scan.close();
	}
}
