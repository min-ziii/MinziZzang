package com.test.java;

import java.util.Scanner;

public class Ex15_Method {
	public static void main(String[] args) {
		// Ex15_Method.java
		
		// public static void hello() {}
		
		// void: 반환 타입
		// void == 없다. == NULL
		
		String name = getName();
		System.out.println(name);
		
		String result = checkScore(100, 90, 80);
		System.out.println(result);
		
		Scanner scan = new Scanner(System.in);
		
		int num = scan.nextInt();
		
	} // main
	
	// This method must return a result of type String
	// String형으로 method를 선언하면 return 값을 무조건 String형으로 줘야 함.
	// String -> 반환 타입
	public static String getName() {
		
		// return 문
		return "홍길동"; // return문을 만나는 순간 해당 method는 무조건 종료되고 main의 호출부분으로 돌아간다.
		
//		System.out.println("종료"); // Unreachable code.
	}
	
	public static String checkScore(int kor, int eng, int math) {
		
		int total = kor + eng + math;
		double avg = (double)total / 3; // 혹은 3을 3.0으로 실수 상수로 만들어도 된다.
		
		String result = avg >= 60 ? "합격" : "불합격";
		 
		//System.out.printf("평균 점수 %.1f점은 %s입니다.\n", avg, result);
		return result;
	}
	
}
