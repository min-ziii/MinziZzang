package com.test.question;

import java.util.Arrays;
import java.util.Random;

public class Q066 {
	public static void main(String[] args) {
		// 중복되지 않는 임의의 숫자 6개를 만드시오.(로또)
		// 숫자의 범위: 1 ~ 45, 오름차순 정렬
		
		Random r = new Random();
		int[] lotto = new int[6];
//		int temp = 0;
		
		for (int i = 0; i < lotto.length; i++) {
			lotto[i] = r.nextInt(45) + 1;
		}
		// �Ʒ� �ּ� �κ�(�������� ����)�� Arrays.sort() method�� ���� �� �ٷ� ����ȭ�Ǿ���.
		Arrays.sort(lotto); 
		
//		for (int i = 0; i < lotto.length; i++) {
//			for (int j = 0; j < lotto.length-i-1; j++) {
//				if (lotto[j] > lotto[j+1]) {
//					temp = lotto[j];
//					lotto[j] = lotto[j+1];
//					lotto[j+1] = temp;
//				}
//			}
//		}
		
		System.out.print("[");
		for (int i = 0; i < 6; i++) {
			if (i == 5) {
				System.out.printf("%d", lotto[i]);
			} else {
				System.out.printf("%d" + ", ", lotto[i]);
			}
		}
		System.out.print("]");
	}
}
