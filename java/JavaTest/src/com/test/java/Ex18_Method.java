package com.test.java;

public class Ex18_Method {
	public static void main(String[] args) {
		
		// Ex18_Method.java
		
		// 재귀 메서드 (Recursive Method)
		// -재귀 구조를 가지는 method
		// -method 구현부에서 자기 자신을 호출하는 method
		// -특정 행동을 반복해야 하는 경우에 사용(몇 번 반복할지 모르는 경우)
		
		//m1();
		//m2();
		//m3();
		
		// 팩토리얼
		// 4! = 4 x 3 x 2 x 1
		
		// 요구사항) 팩토리얼 method를 구현하시오.
		
		int n = 4;
		
		int result = factorial(n); // Ctrl + 1 누르면 함수가 만들어짐
		
		// TODO 나중에 다시 봐야 하는데 잊어버릴 것 같은 부분을 TODO로 걸어놓으면 Window-Show View-Tasks 뷰에서 확인할 수 있다.
		// FIXME TODO보다 더 높은 중요도를 가지는 사항에 대해서 따로 걸어놓고 싶으면 쓰면 됨.
		
		System.out.printf("%d! = %d\n", n, result);
		
	} // main
	
	
	
	private static int factorial(int n) {
		
		return (n == 1) ? 1 : n * factorial(n-1);
	}

	public static void m1() {
		System.out.println("첫번째 메서드");
	}
	
	public static void m2() {
		System.out.println("두번째 메서드");
	}
	
	// 재귀 method
	public static void m3() {
		System.out.println("세번째 메서드");
		m3(); // 재귀 호출
	}
}
