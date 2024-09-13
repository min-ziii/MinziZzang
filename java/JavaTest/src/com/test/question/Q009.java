package com.test.question;

public class Q009 {
	public static void main(String[] args) {
		// 이름을 전달하면 이름 뒤에 '님'을 붙여서 반환하는 method를 선언하시오.
		
		String result = "";
		result = getName("홍길동");
		System.out.printf("고객: %s\n", result);
		
		result = getName("아무개");
		System.out.printf("고객: %s\n", result);
		
	}
	
	private static String getName(String name) {
		return name + "님";
	}	
}
