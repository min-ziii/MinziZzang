package com.test.question;

import java.util.Scanner;

public class Q043 {
	public static void main(String[] args) {
		// 컴퓨터가 1~10 사이의 숫자 1개를 생각하면 사용자가 맞추는 프로그램을 구현하시오.
		
		Scanner scan = new Scanner(System.in);
		
		int answer = (int)(Math.random() * 10);
		System.out.println("컴퓨터가 1~10 사이의 숫자 하나를 생각했습니다.");
		
		boolean flag = true;
		int number = 0;
		int attempt = 0;
		
		while(flag) {
			System.out.print("숫자: ");
			number = scan.nextInt();
			attempt++;
			if (number == answer) {
				correct(answer, attempt);
				break;
			} else {
				wrong(answer, attempt);
				if (attempt == 9) {
					System.out.printf("컴퓨터가 생각한 숫자는 %d입니다.\n", answer);
					System.out.println("모든 기회를 실패했습니다.\n");
					break;
				}
				
			}
		}
		
		System.out.println("프로그램을 종료합니다.");
		scan.close();
	}

	private static void wrong(int answer, int attempt) {
		System.out.println("틀렸습니다.");
	}

	private static void correct(int answer, int attempt) {
		System.out.printf("맞았습니다.\n"
						+ "컴퓨터가 생각한 숫자는 %d입니다.\n"
						+ "정답을 맞추는데 시도한 횟수는 %d회입니다.\n\n", answer, attempt);
		
	}
}
