package com.test.question;

import java.util.Arrays;
import java.util.Scanner;

public class Q068 {

	public static void main(String[] args) {
		// 배열의 요소를 삭제하시오. 배열길이는 10
		// 마지막 요소는 0으로 채우시오.
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("삭제 위치: ");
		int dest = scan.nextInt();
		
		int[] arr = new int[] {5, 6, 1, 3, 2, 8, 7, 4, 10, 9};
		System.out.println("원본: " + Arrays.toString(arr));
		
		for (int i = dest; i < arr.length; i++) {
			if (i == arr.length-1) {
				arr[i] = 0;
			} else {
				arr[i] = arr[i+1]; 
			}
		}
		System.out.println("결과: " + Arrays.toString(arr));
		
		scan.close();

	}

}
