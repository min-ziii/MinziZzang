package com.test.question;

public class Q064 {
	// 1~20 사이의 난수를 담고 있는 배열을 생성하고 최댓값과 최솟값을 출력하시오.

	public static void main(String[] args) {
		
		int[] num = new int[20];
		int max = 1;
		int min = 20;
		
		for (int i = 0; i < num.length; i++) {
			num[i] = (int)(Math.random() * 20) + 1; // 1~20
		}
		
		for (int i = 0; i < num.length; i++) {
			if (num[i] > max) {
				max = num[i];
			}
			if (num[i] < min) {
				min = num[i];
			}
		}
		
		System.out.printf("원본: ");
		for (int i=0; i < num.length; i++) {
			System.out.print(num[i] + ", ");
		}
	
		System.out.println();
		System.out.printf("최댓값: %d\n", max);
		System.out.printf("최솟값: %d\n", min);
		
	}

}
