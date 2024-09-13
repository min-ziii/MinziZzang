package com.test.java;

public class Ex10_Operator {
	public static void main(String[] args) {
		
		// Ex10_Operator.java
		
		/*
			연산자 (Operator)
			 - 수학 연산자 -> 프로그래밍 연산자
			 - 피연산자를 대상으로 미리 정해진 연산(행동)을 한 뒤, 연산의 결과를 반환하는 요소
			 - 기호를 주로 사용하며, 단어로 된 연산자도 존재한다.
			 
			 1. 문장(Statement)
			 2. 표현식(Expression)
			 3. 연산자(Operator)
			 4. 피연산자(Operand)
			 5. 연산자 우선 순위
			 6. 연산자 연산 방향
			 
			 1. 문장(Statement)
			 - 하나 이상의 표현식이 모여 문장이 된다.
			 ex) int n1 = 10; / int n2 = 10 + 20; / int n3 = scan.nextInt();
			  
			 2. 표현식(Expression)
			 - 문장을 구성하는 최소 단위
			 ex) int n1 = 10; 이라는 문장의 표현식은 10 / int n1 / n1 = 10
			 
			 3. 연산자(Operator)
			 ex) int n1 = 10; 이라는 문장의 연산자는 =
		 
		 	 4. 피연산자(Operand)
		 	 ex) int n1 = 10; 이라는 문장의 피연산자는 10 20
		 
		 	 5. 연산자 우선 순위
		 	 - 하나의 문장 안에 속한 연산자가 2개 이상일 경우, 누구를 먼저 실행할 지 정해져있는 순위
		 	 ex) 1 + 2 × 3 이라는 문장에서는 ×가 먼저 실행되고 +가 나중에 실행된다.
		 	 
		 	 6. 연산자 연산 방향
		 	 - 하나의 문장 안에 속한 연산자들 중 우선순위가 동일한 연산자가 여러개일 경우, 누구를 먼저 실행할지 정해져 있는 순위
		 	 ex) 1 + 2 + 3 이라는 문장에서는 왼쪽의 +가 먼저 실행되고 오른쪽의 +가 먼저 실행된다.
		 	 
		 	 
		 	연산자 종류
		 	 1. 행동(목적)에 따른 분류
		 	 	a. 산술 연산자
		 	 	b. 비교 연산자
		 	 	c. 논리 연산자
		 	 	d. 대입 연산자
		 	 	e. 증감 연산자
		 	 	f. 조건 연산자
		 	 	g. 비트 연산자
		 	 	
		 	 2. 형태(피연산자의 개수)에 따른 분류
		 		a. 1항 연산자
		 		b. 2항 연산자
		 		c. 3항 연산자
		 		
		 		
		 	산술 연산자
		 	 +	-	*	/	%(산술연산자 %는 mod 라고 읽는다. 문자로 출력 시 %%로 작성해야 에러가 생기지 않는다.)
		 	 
		 	 - 2항 연산자이고, 피연산자로 숫자형을 가진다.
		 	 
		 	 - 산술연산 관련 이야기
		 	 	정수 ÷ 정수 = 정수이고, 실수 ÷ 실수 = 실수이다.
		 	 	그럼 정수 ÷ 실수 or 실수 ÷ 정수는? -> 결과값이 전부 실수형
		 	 	= 모든 산술연산자의 자료형 결과값은 데이터 손실을 최소화하기 위해 두 피연산자 중 더 큰 쪽의 자료형으로 반환된다. 
		 	 	int 2개에 각각 20억을 넣고 서로 더하면 overflow가 발생하기 때문(런타임 오류) -> 한 쪽을 long형같이 큰 자료형으로 Casting해준다.
		 	 	int 미만 모든 자료형의 산술 연산 결과는 항상 int형이다.
		 	 	ex) byte j = 10; byte k = 20;
		 	 	byte l = j + k;
		 	 	↑ (j + k)는 int형이라 Type mismatch error가 발생한다.
		 	 	∴ byte l = byte(j + k);
		 	 	
		 	 	귀찮으니까 일반적인 정수를 다룰 땐 int형으로 사용하는 것이 좋다. (물리적 코드작성의 편의성과 논리적 메모리관리의 효율성이 서로 타협한 결과)
		 	 	실수의 경우도 float형보다는 double형을 더 많이 사용한다.
		 	 
		 	 
		 */

		
	}
}
