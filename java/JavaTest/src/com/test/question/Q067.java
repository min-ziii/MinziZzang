package com.test.question;

import java.util.Arrays;
import java.util.Scanner;

public class Q067 {
	// 배열에 요소를 삽입하시오.
	// 배열 길이 10, 마지막 요소는 우측으로 옮겨질 공간이 없으면 삭제된다.

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("삽입 위치: ");
		int dest = scan.nextInt();
		System.out.print("값: ");
		int val = scan.nextInt();
		
		int[] arr = new int[] {5, 6, 1, 3, 2, 8, 7, 4, 10, 9};
		System.out.println("원본: " + Arrays.toString(arr));
		
		for (int i = arr.length; i >= dest; i--) {
			if (i == arr.length) {
				continue;
			} else if (i == dest) {
				arr[i] = val;
			} else {
				arr[i] = arr[i-1];
			}
		}
		System.out.println("결과: " + Arrays.toString(arr));
		
		scan.close();
	}

}
