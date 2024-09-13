package com.test.question;

import java.util.Scanner;

public class Q027 {
	public static void main(String[] args) {
		// 문자 1개를 입력받아 아래와 같이 출력하시오.
		/*
		    f, F → Father
			m, M → Mother
			s, S → Sister
			b, B → Brother
			유효성 검사를 하시오.(위의 문자가 아닌 문자는 예외 처리)
		 */
		
		Scanner scan = new Scanner(System.in);
		System.out.print("문자: ");
		String s = scan.next();
		
		switch (s) {
		case "f":
			System.out.println("Father");
			break;
		case "F":
			System.out.println("Father");
			break;
		case "m":
			System.out.println("Mother");
			break;
		case "M":
			System.out.println("Mother");
			break;
		case "s":
			System.out.println("Sister");
			break;
		case "S":
			System.out.println("Sister");
			break;
		case "b":
			System.out.println("Brother");
			break;
		case "B":
			System.out.println("Brother");
			break;
		default:
			System.out.println("입력한 문자가 올바르지 않습니다.");
		}
		scan.close();
	}
}
