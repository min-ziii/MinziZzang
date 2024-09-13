package com.test.question;

import java.util.Scanner;

public class Q061 {
	public static void main(String[] args) {
		// 값을 입력받고 입력받은 순서의 반대로 출력
		
		Scanner scan = new Scanner(System.in);
		
		int num = 0;
		int[] nums = new int[5];
		
		for (int i=0; i < nums.length; i++) {
			System.out.print("숫자: ");
			num = scan.nextInt();
			nums[i] = num;
		}
		
		for (int j=nums.length-1; j >= 0; j--) {
			System.out.printf("nums[%d] = %d\n", j, nums[j]);
		}
		
		scan.close();
	}
}
