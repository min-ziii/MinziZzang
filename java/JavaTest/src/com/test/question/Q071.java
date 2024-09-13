package com.test.question;

public class Q071 {
	public static void main(String[] args) {
		int[][] list = new int[5][5];
		int n = 25;
		
		for (int i=0; i < list.length; i++ ) {
			for (int j=0; j < list.length; j++) {
				list[i][j] = n;
				n--;
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
