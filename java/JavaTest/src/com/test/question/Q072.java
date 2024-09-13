package com.test.question;

public class Q072 {
	public static void main(String[] args) {
		int[][] list = new int[5][5];
		int n = 0;
		
		for (int i=0; i<list.length; i++) {
			for (int j=0; j<list.length; j++) {
				if (j == 0) {
					n = i + 1;
				}
				list[i][j] = n;
				n += 5;
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
