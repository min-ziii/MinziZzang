package com.test.java;

public class Ex14_Method {

	public static void main(String[] args) {
		
		// Ex14_Method.java
		
		// public static void hello()
		
		// - hello: method이름 -> camel 표기법 + 의미있게
		// - (): 인자리스트(Parameters, Arguments), 매개변수
		
		// 요구사항1) '홍길동'에게 인사를 하는 method를 구현하시오.
		// 요구사항2) '아무개'에게 인사를 하는 method를 구현하시오.
		// 요구사항3) '테스트'에게 인사를 하는 method를 구현하시오.
		// 요구사항4) 우리 강의실의 모든 사람(23명)에게 각각 인사를 하는 method를 구현하시오.
		// 요구사항5) 대한민국 모든 국민에게...
		hello();
		hello2();
		hello3();
		
		helloEveryone("홍길동");
		helloEveryone("아무개");
		helloEveryone("테스트"); // 실인자
		
		// 인자값과 매개변수 자료형이 서로 동일해야 한다.
		
		int age = 20;
		checkAge(age);
		
		byte age2 = 20;
		checkAge(age2); // 암시적 형변환 가능
		
		// 성적 계산 method
		// ★매개변수의 순서와 개수를 틀리지 않게 조심
		checkScore(100, 90, 80);
		
		
		
	} // main
	
	public static void hello() {
		System.out.println("홍길동님 안녕하세요.");
	}
	
	public static void hello2() {
		System.out.println("아무개님 안녕하세요.");
	}
	
	public static void hello3() {
		System.out.println("테스트님 안녕하세요.");
	}
	
	public static void helloEveryone(String name) { // 매개변수, 가인자
		// String name = '테스트';
		System.out.println(name + "님 안녕하세요.");
	}

	public static void checkAge(int age) {
		String result = age >= 18 ? "통과" : "거절";
		System.out.printf("입력한 나이 %d세는 %s입니다.\n", age, result);			
	}
	
	/**
	 * 성적을 계산합니다.
	 * @param kor 국어
	 * @param eng 영어
	 * @param math 수학
	 */
	public static void checkScore(int kor, int eng, int math) {
		
		int total = kor + eng + math;
		double avg = (double)total / 3; // 혹은 3을 3.0으로 실수 상수로 만들어도 된다.
		
		String result = avg >= 60 ? "합격" : "불합격";
		 
		System.out.printf("평균 점수 %.1f점은 %s입니다.\n", avg, result);		
	}
}
