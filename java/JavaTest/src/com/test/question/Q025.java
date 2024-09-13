package com.test.question;

import java.util.Scanner;

public class Q025 {
	public static void main(String[] args) {
		// 숫자 2개를 입력받아 큰수와 작은수를 출력하시오.
		// 조건1: 두 숫자가 얼마의 차이를 보이는지 출력하시오.
		// 입력: 첫번째 숫자: 5 
		//		 두번째 숫자: 3 
		// 출력: 큰수는 5이고, 작은수는 3입니다. 두 숫자는 2(가)이 차이납니다.
		// 입력: 첫번째 숫자: 3 
		//		 두번째 숫자: 3 
		// 출력: 두 숫자는 동일합니다.
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("첫번째 숫자: ");
		int numFirst = scan.nextInt();
		System.out.print("두번째 숫자: ");
		int numSecond = scan.nextInt();
		
		int temp = 0;
		
		if (numFirst > numSecond) {
			System.out.printf("큰수는 %d이고, 작은수는 %d입니다. 두 숫자는 %d(가)이 차이납니다.", numFirst, numSecond, numFirst - numSecond);			
		} else if (numFirst < numSecond) {
			temp = numFirst;
			numFirst = numSecond;
			numSecond = temp;
			System.out.printf("큰수는 %d이고, 작은수는 %d입니다. 두 숫자는 %d(가)이 차이납니다.", numFirst, numSecond, numFirst - numSecond);
		} else {
			System.out.println("두 숫자는 동일합니다.");
			
		}
		scan.close();
	}

}
