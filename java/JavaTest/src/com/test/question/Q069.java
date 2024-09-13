package com.test.question;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Q069 {
	public static void main(String[] args) {
		// 배열의 요소를 순차적으로 2개씩 더한 결과를 배열로 생성한 뒤 출력하시오.
		// 원본 배열 길이: 사용자 입력
		//원본 배열 요소: 난수(1~9)
		//결과 배열 길이: 사용자 입력 / 2
		
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		
		System.out.print("배열 길이: ");
		
		int len = scan.nextInt();
		int[] arr = new int[len];
		int[] arrNew;
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt(9) + 1;
		}
		
		if (len % 2 == 1) {
			arrNew = new int[(len/2)+1];
		} else {
			arrNew = new int[len/2];
		}

		System.out.println("원본: " + Arrays.toString(arr));
		
		for (int i = 0; i < arr.length-1; i+=2) {
			arrNew[i/2] = arr[i] + arr[i+1];
		}
		
		if (arr.length % 2 == 1) {
			arrNew[arrNew.length-1] = arr[arr.length-1];
		}
		
		System.out.println("결과: " + Arrays.toString(arrNew));
		
		scan.close();
	}
}
