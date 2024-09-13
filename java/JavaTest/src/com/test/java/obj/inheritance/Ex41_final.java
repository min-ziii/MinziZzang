package com.test.java.obj.inheritance;

import java.util.Calendar;

public class Ex41_final {
	
	public static void main(String[] args) {
		
		//Ex41_final.java
		/*
			
			final 키워드
			- 한번 결정하면 바꿀 수 없다.
			- 안정성이 높아진다.
			
			1. 변수 적용(지역 변수, 멤버 변수)
				- 초기화 이후에 값을 변경할 수 없다.
				- (이름 있는)상수
				- 대문자
			
			2. 메서드 적용
				- 상속 시 오버라이딩 방지
			
			3. 클래스 적용
				- 상속 방지
		
		*/
		
		int a = 10;
		final int b = 20;
		
		a = 100;
		//b = 200;
		
		System.out.println(Calendar.YEAR); //상수 > final
		
		
		//성별
//		Gender gender = new Gender();
//		System.out.println(gender.male); //1
//		
//		Gender gender2 = new Gender();
//		System.out.println(gender2.male);
		
		//System.out.println(Gender.male);
		
		//Gender.male = 3;
		
		//System.out.println(Gender.male);
				
		System.out.println(Gender.MALE);
		System.out.println(Gender.FEMALE);
				
	}//main

}//class


class Gender {
	
	public static final int MALE = 1;
	public static final int FEMALE = 2;
	
}



//문제 풀이
//1. 끝까지 스스로 풀이
//2. 해답 참고



final class FinalParent {
	final public boolean check() {
		
		return true;
	}
}

//class FinalChild extends FinalParent {
////	@Override
////	public boolean check() {
////	
////		return false;
////	}
//}
























