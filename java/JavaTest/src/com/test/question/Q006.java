package com.test.question;

import java.util.Scanner;

public class Q006 {

	public static void main(String[] args) {
		
		// 사용자의 한 달 수입을 입력받아 세후 금액을 출력하시오.
		// 조건: 세금은 수입의 3.3%이다.
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("한달 수입 금액(원): ");
		int    grossAmount  	   = scan.nextInt();
		double netAmount 		   = grossAmount * 0.967; 
		double tax				   = grossAmount * 0.033;
		
		int    netAmountToInt	   = (int)netAmount;
		int    taxToInt 		   = (int)tax;
		
		System.out.printf("세후 금액(원): %,d원\n", netAmountToInt);
		System.out.printf("세금(원): %,d원\n", taxToInt);
		
		scan.close();

	}

}

// CodeReview

// 만약 법령 개정으로 세금이 3.3%에서 다른 수치로 변경된다면?
// final double TAX = 0.033;
// 으로 지정해놓고 세금의 percentage가 바뀔 때마다 final변수를 조정하면 될 듯?
// netAmount = grossAmount * ( 100 - (TAX x 100) );
// 
// 아니면
// final double TAX = 3.3; // <- 3.3%니까 0.033이라는 수치보다는 좀 더 가시성 높게?
// netAmount = grossAmount * (100 - TAX);
//
// TAX를 이렇게 정하면 세금이 몇 원인지를 넣는 변수 이름을 TaxAmount로 바꿔야 할 듯
