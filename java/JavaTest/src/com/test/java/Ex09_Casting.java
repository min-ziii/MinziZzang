package com.test.java;

public class Ex09_Casting {

	public static void main(String[] args) {
		
		//Ex09_Casting.java
		/*
		 	형변환, 자료형변환
		 	- Casting, Promotion
		 	- 하나의 자료형을 다른 자료형으로 변환하는 작업
		 	- 개발자가 코드 작성을 좀더 유연하게 할 수 있음
		 	- int -> double
		 	- long -> short
		 	- byte -> short
		 
		 	1. 암시적(묵시적) 형변환 (= 자동 형변환. Promotion)
		 		- 큰 자료형 = 작은 자료형
		 		- 100% 안전한 작업.
		 		
		 	2. 명시적(강제적) 형변환, Casting
		 		- 작은 자료형 = 큰 자료형
		 		- 안전성은 경우에 따라 다르다
		 			- Overflow 발생 위험성이 존재
		 
		 */
		
		byte b1;	// 1byte
		short s1;	// 2byte
		
		b1 = 10; // 원본
		
		// LeftValue = RightValue
		// 공간		 = 값
		//*LeftValue와 RightValue의 자료형은 반드시 동일해야만 하며, 만약 그렇지 않으면 무조건 컴파일 오류가 발생한다.*
		
		// short = byte
									// s1 = b1; 사람은 이렇게 적었지만
		
		// (자료형) <- 형변환 연산자
		s1 =(short)b1;				// 실제로는 왼쪽과 같이 형변환 연산자가 붙은 채로 컴파일된다. 
		
		// 검증
		System.out.println(s1);
		
		
		byte b2;
		short s2;
		
		s2 = 200;
		
		// Type mismatch: cannot convert from short to byte
		b2 = (byte)s2;
		
		System.out.println(b2);
		
		
		int money = 2100000000;
		
		short money2;
		
		// Overflow
		// Underflow
		// 가 발생하면 값의 손상이 일어나기 때문에 사전에 체크해서 방지해야 한다.
		money2 = (short)money;
		
		System.out.println("계좌이체 결과 잔액: " + money2);
		
		System.out.println();
		System.out.println();
		System.out.println();

		// 자료형에 따른 형변환
		// - 값형과 참조형 간에는 형변환을 할 수 없다. 값형끼리만 가능
		// byte, short, int, long
		// float, double
		// char
		// boolean (형변환 불가)
		// String (형변환 불가 - 참조형)
		
		float f1;	// 4B
		double d1;	// 8B
		
		f1 = 3.14F;
		
		// 8바이트를 4바이트로
		d1 = f1;
		 
		System.out.println(d1); // 실수 변환에서 생기는 미세오류값은 생각하지 말고.. 일단 3.14는 제대로 출력되니까 성공
		
		d1 = 3.14;
		
		// 4바이트를 8바이트로
		f1 = (float)d1;
		
		System.out.println(f1);// 실수 변환에서 생기는 미세오류값은 생각하지 말고.. 일단 3.14는 제대로 출력되니까 성공
		
		// 만약 float의 범위를 넘는 실수값이 형변환으로 들어간다면 오차가 생길 것이다.
		d1 = 3.1234567890123456789;
		
		f1 = (float)d1;
		
		System.out.println(d1); // 원본
		System.out.println(f1); // 복사본
		
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		
		// 정수-실수 형변환
		int n1 = Integer.MAX_VALUE; // 2147483647
		float n2;
		
		n2 = n1;
		
		System.out.println(n2); // 2.14748365E9 = 2147483650 -> 3 정도의 정밀도 손실값이 생김. 주의
		
		
		// 실수-정수 형변환
		int n3;						// 복사본
		float n4 = 10000000000F;	// 원본
		
//		n3 = n4;				// Type mismatch: cannot convert from float to int (무조건 안 된다는건 아니고 명시적 형변환을 해줘야 된다는 소리.)
		n3 = (int)n4;
		System.out.println(n3); // integer 범위를 벗어나는 실수값이 integer형에 형변환으로 들어가면 Integer.MAX_VALUE값이 출력된다.
		
		
		// 숫자형 크기 비교
		// byte(1B) < short(2B) < int(4B) < long(8B) <<< float(4B) < double(8B)
		// int와 float는 같은 4B크기의 자료형이지만 표현할 수 있는 값의 차이가 매우 크다.
		
		
		// char 형변환
		// - '문자 코드값'과'숫자'를 서로의 자료형으로 형변환할 수 있다.
		// 일종의 정수형변환이라고 생각하면 편함.
		
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		System.out.println(65);			// 65
		System.out.println((char)65);	// A
		
		System.out.println('a');		// a
		System.out.println((int)'a');	// 97
		
//		System.out.println("A");		// String형 자료형(참조형)
//		System.out.println((int)"A");	// 참조형-값형 간의 형변환은 NO
		
		
			// ★ 문자를 정수 형변환할 때는 반드시 int로 변환하라. (short는 사용 금지)
		
		
		char c1;	// 정수 2Byte
		short t1;	// 정수 2Byte
		
		c1 = 'A';
		
		t1 = (short)c1;	// Type mismatch: cannot convert from char to short. short형으로 Casting 해주면 가능.
		
		System.out.println(t1);			// 65
		
		char c2;
		short t2;
		
		t2 = 97;
//		c2 = t2;		// 우항의 범위가 더 큰 거 아니야? 왜 promotion이 안 되고 오류가 생김?
						// - short: -32768 ~ 32767 / - char: 0 ~ 65535
						// 표현할 수 있는 수의 범위는 2B로 같지만 영역이 다르다.
						// short값이 음수라면 오류가 생길 수 있기 때문에 compiler가 경고 메시지를 보내는 것.
		
		c2 = (char)t2;					// 65
		
		char c3 = '가'; 				// 44032
		short t3 = (short)c3;
		
		System.out.println(t3);			// 44032가 나와야 하는데 overflow되어 -21504가 출력된다.
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		// 문자열 "100"을 숫자 100으로 바꾸고 싶다.
		String txt = "100";
		int result;
		
		// 참조형-값형 형변환은 원래 안 된다.
		// 하지만 Wrapper 클래스의 메서드를 사용한다면 가능하다.
		
		result = Integer.parseInt(txt);
		System.out.println(result);
		
		
		// 문자열 "3.14"를 숫자 3.14로 바꾸고 싶다.
		txt = "3.14";
		double result2 = Double.parseDouble(txt);
		System.out.println(result2);
		
		// -> Wrapper별로 문자열을 자기 자료형으로 바꿔주는 method들이 존재한다.
		
		
	}
}
