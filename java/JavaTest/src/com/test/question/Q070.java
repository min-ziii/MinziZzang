package com.test.question;

public class Q070 {
	public static void main(String[] args) {
		int[][] list = new int[5][5];
		int n = 1;
		
		for (int i = 0; i < list.length; i++) {
			if (i % 2 == 0) { 	// 1,3,5행
				for(int j = 0; j < list.length; j++) {
					list[i][j] = n;
					n++;
				}
			} else { 			// 2,4행
				for(int j = list.length-1; j >= 0; j--) {
					list[i][j] = n;
					n++;
				}
			}
		}
		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list.length; j++ ) {
				System.out.printf("%2d\t", list[i][j]);
			}
			System.out.println();
		}
	}
}
