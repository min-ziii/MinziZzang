package com.test.java;

import java.util.Scanner;

public class Ex11_Operator {
	public static void main(String[] args) {
		
		// Ex11_Operator.java
		
		/*
			비교 연산자
			> >= < <= == !=
			- 2항 연산자이고, 피연산자들의 우위 혹은 동등을 비교한다.
			- 피연산자는 숫자형이다.
			- 연산의 결과는 boolean형이다. (true or false only)
			
		 */
		
//		// 흐름의 조건으로 사용
//		// 요구사항) 사용자로부터 나이를 입력받고 20세 이상이면 통과, 미성년이면 거절
//		Scanner scan = new Scanner(System.in);
//		
//		System.out.print("나이 입력: ");
//		
//		int age = scan.nextInt();
//		boolean check = (age >= 20) ? true : false;
//		System.out.println(check);
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		int n1 = 100;
		int n2 = 100;
		int n3 = 50;
		n3 = n1 + n2;
		int n4 = 200;
		
		System.out.println(n1 == n2);	// true
		System.out.println(n1 == n3);	// false
		System.out.println(n3 == n4);	// 100 + 100 == 200 (true)
		
		String s1 = "홍길동";
		String s2 = "홍길동";
		String s3 = "홍";
		s3 = s3 + "길동"; // s3 == 홍길동
		
		System.out.println(s1 == s2);		// true
		System.out.println(s3);				// 홍길동
		System.out.println(s1 == s3);		// false. 왜 똑같은 홍길동인데 false 인가?
		
		// ★문자열을 비교할 때는 ==과 !=를 절대 사용하지 말고, equals() 메서드를 쓸 것.
		
		System.out.println(s1.equals(s2));	// true
		System.out.println(s1.equals(s3));	// true
		
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		/*
			논리 연산자
			&&(and,논리곱)	||(or,논리합)	!(not)
			- 2항 연산자인 &&, ||
			- 1항 연산자인 !
			- 피연산자는 boolean형이다.
			- 연산의 결과도 boolean형이다.
			- 정해진 규칙에 따른 결과를 반환한다.
		
			^(xor, 배타적 논리합)
			- 결과값이 ||의 반대로 나온다.(같으면 0, 다르면 1)
		
		*/
		
		boolean b1 = true;
		boolean b2 = false;
		
		System.out.println(b1 ^ b2); // true
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		/*
			대입 연산자
			=
			+=	-=	/=	%= (복합 대입 연산자)
			- 2항 연산자
			- Left value(변수) = Right value(상수, 변수)
			- Left value와 Right value의 자료형은 반드시 동일해야 한다.
			- 연산자 우선 순위가 가장 낮다.

		연산자 우선 순위
		 - 산술 연산자 > 비교 연산자 > 논리 연산자 > 대입 연산자
		
		 */
		
		int sum = 1 + 2 * 3;
		
		System.out.println(sum); // 7
		
		int m1 = 100;
		int m2;
		int m3;
		
		m2 = m1;
		m3 = m1;
		m3 = m2 = m1;
		System.out.println(m3); // 100
		
		// 복합 대입 연산자
		int n = 10;
		
		// n에 1을 더하기
		n += 1;
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		/*
			대입 연산자
			++	--
			- 1항 연산자
			- 피연산자는 숫자형이다.
			- 누적 연산용
			- 기존 값에 1을 더하거나 뺀다.
			- ★피연산자의 위치를 바꿀 수 있다.
				-연산자의 위치에 따라 우선순위가 바뀐다.
					- ++n: 연산자 우선순위 최상
					- --n: 연산자 우선순위 최하
		 */
		
		// ★하나의 문장 안에 증감 연산자와 다른 연산자를 동시에 사용하지 말 것. 가독성이 매우 떨어짐.
		
		// 1)
		n = 10;
		int result = 10 + ++n;
		System.out.printf("result: %d, n: %d\n", result, n); // result: 21, n: 11
		
		// 2)
		n = 10;
		result = 0;
		result = 10 + n++;
		System.out.printf("result: %d, n: %d\n", result, n); // result: 20, n: 11
		
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		/*
	 		조건 연산자
			?:
			- 3항 연산자
			- A ? B : C
			- A: boolean / B,C: 상수 또는 변수
			- A가 참이면 B, 거짓이면 C
		*/
		
		// 18세 이상 60세 미만이면 통과, 아니면 거절
		int age = 20;
		System.out.println(age >= 18 && age < 60 ? "통과" : "거절");
		
		System.out.println(age >= 20 ? "어른" : 100);
		// B와 C의 자료형은 같아야 한다.
		// 3항 연산자의 결과값을 변수에 넣는다고 가정했을 때, B와 C의 자료형이 다르면 변수의 자료형을 무엇으로 설정할지를 알 수 없다. 
		
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		// 비트 연산자
		//	&(and)	|(or)
		// -bit값을 대상으로 연산
		
		int a = 10;
		int b = 5;
		
		System.out.println(a & b); // 0
		System.out.println(a | b); // 15
		// a는 2진수로 00001010 (메모리 할당은 Byte 단위로 하니까 8자리)
		// b는 2진수로 00000101
		// & 연산 ->   00000000
		// | 연산 ->   00001111
		// 2진수 0은 10진수로 0, 2진수 1111은 10진수로 15
		
		
	}
}
