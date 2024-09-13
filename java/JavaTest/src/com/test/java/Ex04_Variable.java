package com.test.java;

import java.lang.invoke.LambdaConversionException;

public class Ex04_Variable {

	public static void main(String[] args) {
		
		// Ctrl + Alt + 방향키(up,down) = 줄 복붙
		// Alt + 방향키(up,down) = 줄의 내용을 다른 줄로 이동
		
		// Ex04_Variable.java
		// 모든 자료형 변수 만들어보기
		
		// 정수형
		byte b1; // -128~127
		b1 = 10; // 이 때 이 10을 정수형 Literal로 부른다.
		System.out.println(b1);
		
		/*
		b1 = 128;
		*/
		// Type mismatch: cannot convert from int to byte
		
		/*
		b1 = 3.14;
		*/
		// Type mismatch: cannot convert from double to byte
		
		// byte(=자료형) -> Byte(=Wrapper 클래스)
		// short		 -> Short
		// int		   	 -> Integer
		// long 		 -> Long
		// float 		 -> Float
		// double 		 -> Double
		// boolean 		 -> Boolean
		// character	 -> Character
		
		//byte
		System.out.println(Byte.MAX_VALUE); // = 127
		System.out.println(Byte.MIN_VALUE); // = -128
		
		//short
		short s1;
		s1 = 10000;
		System.out.println(s1);
		System.out.println(Short.MAX_VALUE); // = 32,767
		System.out.println(Short.MIN_VALUE); // = -32,768
		
		//int
		int n1;
		n1 = 1000000000;
		System.out.println(n1);
		System.out.println(Integer.MAX_VALUE); // = 2,147,483,647
		System.out.println(Integer.MIN_VALUE); // = -2,147,483,648
		
		//long
		long l1;
		l1 = 1000;
		System.out.println(Long.MAX_VALUE); // 9223372036854775807
		
		System.out.println(l1);
		

		b1 = 100;
		s1 = 100;
		n1 = 100;
		l1 = 100;

		// java에서 모든 정수형 literal은 int 자료형을 가진다.
		// 이 말은? -> 위 식들의 우항인 '100'은 모두 int 자료형을 가지고 있다.
		// 따라서 우항에 int의 범위인 -2,147,483,648~2,147,483,647을 넘는 수를 넣으려면
		// int형과 다른 자료형을 잡아야 한다고 미리 알려줘야 한다.
		// -> 숫자 뒤에 자료형의 첫 대문자를 적어서 구분한다.
		// l1 = 10000000000L;

		
		
		//float (단정도형)
		float f1 = 123456789012345678901234567890123456789.0F;
		// Long형의 표기법을 넘는 정수는 .0을 붙여 실수로 만든다.
		// 그러나 정확하게 표기되지 않고 1.2345679E38로 출력된다.
		System.out.println(f1);
		
		//double (배정도형)
		double d1 = 123456789012345678901234567890123456789.0D;
		// 그러나 정확하게 표기되지 않고 1.2345678901234568E38로 출력된다.
		System.out.println(d1);
		
		// 이것이 단정도형과 배정도형의 정확도 차이이다.
		
		
		double d2 = 0.2;
		double d3 = 0.1;
		System.out.println(d2 + d3);
		
		// 실수 연산은 정확하게 0.3이 나오지 않고 약간의 오차 범위가 생긴다.
		// 오차범위 보정 class를 사용하지 않는다면 정수로 만들어 연산한 뒤 다시 실수로 나눠주는 방법이 가장 간단하다.
		
		//문자
		char c1;
		/*
		c1 = A;
		*/
		// error: cannot be resolved to a variable.
		// compiler는 뜬금 없는 글자를 보면 글자가 아닌 변수나 식별자로 생각하여 오류를 낸다.

		c1 = 'A'; // 문자형 literal이 되게끔 약속사항을 지켜서 적는다.
		System.out.println(c1);

		c1 = '5'; // 5라는 문자이다. (int)5가 아님.
		System.out.println(c1);
		
		//문자열
		String name = "홍길동"; // 홍길동은 문자열 literal이다.
		System.out.println(name);
		
		//논리형
		boolean flag;
		flag = true; // true(와 false)는 boolean literal이다.
		System.out.println(flag);
		
		
		// 주민등록번호 입출력 ex)950621
		int jumin = 950621;
		System.out.println("주민등록번호: " + jumin); // 주민등록번호: 950621
		
		jumin = 030731; //  compiler는 8진수 30731로 인식한다.
		System.out.println("주민등록번호: " + jumin); // 주민등록번호: 12761
		// 숫자처럼 보이지만 수치가 아닌 데이터의 대표적인 예시. 주민번호는 95만621이나 3만731이 아니다.
		
		// 자바의 기수법
		// -10진수
		// -8진수
		// -16진수
		// -2진수
		System.out.println(10);		// 10진수 10
		System.out.println(010);	// 8진수 10
		System.out.println(0x10);	// 16진수 10
		System.out.println(0b10);	// 2진수 10
		
		String jumin2 = "030731";
		System.out.println("주민등록번호: " + jumin2); // 주민등록번호: 030731
		
		
		//실수 literal의 가독성 차이
		double d4 = 12000000000000.0;
		double d5 = 1.2e+13;
		
		double d6 = 0.012;
		double d7 = 1.2e-2;
		System.out.println(d7);
	}

}
