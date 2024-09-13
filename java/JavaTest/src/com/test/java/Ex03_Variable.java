package com.test.java;

public class Ex03_Variable {

	public static void main(String[] args) {
		
		/*
			 Ex03_Variable.java
			 
			 자료형 + 변수
			 
			 변수(Variable)
			 - 데이터를 저장하는 공간
			 - 개발자가 명령어를 사용해서 메모리에 할당받은 공간
			 - 변할 수 있는 수
			 
			 1. 변수 선언
			 - 자료형 변수명;
			 
			 2. 변수 초기화
			 - 변수명 = 값;
			 
			 3. 변수 호출
		*/

		// 국어 점수
		byte korean;
		korean = 90;
		System.out.println(korean);
		
		// 영어 점수
		byte english;
		english = 80; // 초기화
		System.out.println(english);
		
		english = 70; // 치환
		System.out.println(english);
		
		// 변수 <> 상수(=Literal)
		
		System.out.println(100);
		System.out.println(100);
		
		// 변수명을 만들 때 규칙
		// 1. 영문자 + 숫자 + _,$을 사용한다. -> 관습적 + 물리적
		// 2. 숫자로 시작 불가
		// 3. 예약어(Keyword) 사용 불가
		// 4. 의미를 쉽게 알아볼 수 있게 작성
		// 5. 동일한 변수명 선언 불가
		
		byte kor;
		byte kor1;
		byte kor_1;
		//byte kor%1;
		//byte 1kor;
		byte _1kor;
		//byte byte;
		
		// 변수 생성하는 법
		int n1; // 선언
		n1 = 100; // 초기화;
		
		int n2 = 200; // 선언과 동시에 초기화
		
		int n3, n4; // 동시에 변수 선언
		
		int n5 = 100, n6 = 200;
		
		int n7, n8 = 100, n9, n10, n11 = 200, n12 = 300;
		
		// Ctrl + Shift + F4를 누르면 개행이 어긋난 코드가 자동으로 정렬된다.
		// format 관리를 일부러 해놓은 곳도 정렬되어버리므로 유의.
		
		// 지도 좌표(x, y)
		double x1; // 우리집 x좌표
		double y1; // 우리집 y좌표
		
		double x2, y2; // 친구집 x, y 좌표
		
		// 변수 개수가 많아지는 경우는 전자가 더 가독성이 높다.
		
		double x3, // 마트 x좌표
			   y3; // 마트 y좌표
		
		// java는 공백문자 처리가 자유롭기 때문에 이런 식으로도 가능.
		
		double x4,
		y4;
		
		// 열이 달라지면 가독성이 떨어지니 조심.
		
		/*
		float f1;
		double f1;
		*/
		// error: Duplicate local variable f1.
		// 자료형이 달라도 동일한 이름의 변수는 선언할 수 없다.
		
		/*
		int num;
		System.out.println(num);
		*/
		// error: The local variable num may not have been initialized.
		// 변수 num이 초기화되지 않았다. (null 상태이다.)
		
		
		// 식별자(변수명 + 다른 요소의 이름 포함)
		
		// 1. Hungarian 표기법
		//	- 식별자의 접두어로 자료형을 표시하는 방법.
		// 		intWeight / iWeight / i_weight
		//	java보다는 dotnet 쪽에서 주로 사용함.
		//	변수명 작성 시 사용
		
		// 2. PASCAL 표기법
		//	- 식별자의 첫 문자를 대문자로, 나머지를 소문자로 표기함.
		//	- 식별자가 2개 이상의 합성어일 경우 각 단어의 첫 문자를 대문자로 표기함.
		//		Weight / BodyWeight
		// 	클래스명 작성 시 사용
		
		// 3. Camel 표기법
		//	- 식별자의 첫 문자를 대문자로, 나머지를 소문자로 표기함.
		//	- 식별자가 2개 이상의 합성어일 경우 각 단어의 첫 문자를 대문자로 표기함.
		//		bodyWeight
		//	변수명 작성 시 사용
		
		// 4. Snake 표기법
		//	- 전부 소문자 표기
		//	2개 이상의 단어일 경우 '_'로 연결
		//		body_weight
		// 용례가 딱히 정해지진 않음
		
		// 5. Kebab 표기법
		//	- 전부 소문자 표기
		//	2개 이상의 단어일 경우 '-'로 연결
		//	java에서는 불가능
		//	HTML/CSS 작성 시 사용하기도 함
		//		body-weight
		
		
		// 값을 변화시키면 안 되는 데이터(변수 형태로 선언된 상수)가 있다.
		// 상수는 모든 문자를 대문자로 적는다.
		final double PI = 3.14;
		System.out.println(PI * 10);
		// The final local variable cannot be assigned.
		// It must be blank and not using a compound assignment.
		
	}

}
