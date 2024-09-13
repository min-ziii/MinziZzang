package com.test.question;

public class Q041 {
	public static void main(String[] args) {
		// 누적값이 1000을 넘어가는 순간 루프를 종료하시오.
		
//		1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10 
//		+ 11 + 12 + 13 + 14 + 15 + 16 + 17 + 18 + 19 + 20 + 21 + 22
//		+ 23 + 24 + 25 + 26 + 27 + 28 + 29 + 30 + 31 + 32 + 33 + 34
//		+ 35 + 36 + 37 + 38 + 39 + 40 + 41 + 42 + 43 + 44 + 45 = 1035
		// 무한루프인데 sum값을 검사해서 종료시키는 루프.
		
		boolean flag = true;
		int i = 1;
		int sum = 0;
		
		while(flag) {
			System.out.print(i);
			sum += i;
			i++;
			if (sum > 1000) {
				System.out.println(" = " + sum);
				flag = false;
			} else {
			System.out.print(" + ");
			} 
		}
		
	}
}
