package com.test.question;

public class Q010 {
	
	// 숫자 1개를 전달하면 4자리로 출력하는 메소드를 선언하시오.
	// 조건1: void digit(int num)
	// 조건2: 조건문 대신 3항 연산자를 사용할 것
	// 조건3: 입력한 숫자가 4자리 이상이면 그대로 출력
	
	public static void main(String[] args) {
		digit(1);
		digit(12);
		digit(321);
		digit(5678);
		digit(98765);
	}
	
	public static void digit(int num) {
		// 1-1. 숫자의 길이를 비교하는 method가 Integer class 안에는 없어서, 받은 값을 String으로 변환한 후 length() method를 사용하기로 했다.
		String numStr = Integer.toString(num);
		
		// 1-2. Integer class의 method 대신 String class의 valueOf() method를 사용했다.
		// String numStr = String.valueOf(num);
		
		// 1-3. 1-2를 응용해서 int를 String으로 더 편하게 변환했다.
		// String numStr = "" + num;
		
		// 2-1. String으로 변환한 num을 조건에 맞게 0을 붙이는 작업을 삼항연산자와 ""를 사용해 수행했다.
		String result = numStr.length() == 1 ? "000" + numStr : numStr.length() == 2 ? "00" + numStr : numStr.length() == 3 ? "0" + numStr : numStr.length() <= 4 ? numStr : numStr;
		System.out.printf("%d → %s\n", num, result);
		
		// 3-1. 다 필요 없고 print format에서 약간의 조작만으로 조건에 맞게 0을 붙여 출력할 수 있도록 구현했다. 제일 간단하다.
		System.out.printf("%d → %04d\n", num, num);
		
	}
	
}
