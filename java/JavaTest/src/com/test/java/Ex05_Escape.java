package com.test.java;

public class Ex05_Escape {
	public static void main(String[] args) {
		
		// Ex05_Escape.java
		
		// Escape Sequence란?
		// -컴파일러가 번역할 때 소스 상의 문자를 화면 그대로 출력하지 않고 사전에 미리 약속된 표현으로 바꿔 출력하는 요소
		
		//	\n	\r	\t	\'	\"	\b	\\
		
		
		// 1. \n (개행 문자 = enter)
		// - new line, Line feed
		
		// 요구사항1) "안녕하세요. 홍길동 님." 3회 출력해주세요.
		
		String msg = "안녕하세요. 홍길동 님.";
		System.out.println(msg);
		System.out.println(msg);
		System.out.println(msg);
		
		// 요구사항2) "안녕하세요." 와 홍길동 님." 을 각각 다른 라인에 출력해주세요.
		// ** 문자열 literal 내에는 개행문자를 직접 작성할 수 없다.
		msg = "안녕하세요.\n홍길동 님.";
		System.out.println(msg);
		
		
		// 2. \r
		// -Carriage Return
		// -캐럿(carret, 텍스트 입력 시 깜빡이는 | 모양)의 위치를 현재 라인의 맨 앞으로 아동
		
		msg = "안녕하세요. \r홍길동 님.";
		System.out.println(msg);
		
		
		// 운영체제의 enter
		/*
		  1. Windows: \r\n
		  2. MacOS: \r
		  3. Linux: \n
		 */
		
		
		// 3. \t
		// -탭 문자, tab
		msg = "하나	둘 	셋";
		System.out.println(msg);
		
		msg = "하나\t둘\t셋";
		System.out.println(msg);
		
		
		// 4. \b (backspace)
		msg = "홍길동\b입니다.";
		System.out.println(msg);
		
		
		// 5. \", \', \\
		// -이미 역할이 있는 문자들을 역할을 못 하게 만든다.
		// escape sequence라는 단어의 정의에 가장 적합한 문자들.
		
		// 요구사항) 홍길동: "안녕하세요." 출력해주세요.
		msg = "홍길동: \"안녕하세요.\""; // 안쪽의 쌍따옴표를 바깥쪽의 쌍따옴표와 다른 종류라고 인식시킴
		System.out.println(msg);
		
		// 요구사항) 수업 폴더가 어딘지 출력해주세요.
		// -C:\class\code\java
		
		// String path = "C:\class\code\java";
		
		// error: Invalid escape sequence (valid ones are  \b  \t  \n  \f  \r  \"  \'  \\ )
		// 잘못된 escape 문자(complier가 생각하기에는 \c, \j)를 썼으니 valid한 escape sequence를 사용해달라는 경고문. 
		
		String path = "C:\\class\\code\\java";
		System.out.println(path);
		
	}
}
