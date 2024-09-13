package com.test.java;

public class Ex06_Output {

	public static void main(String[] args) {
		
		// 기본 입출력
		// - 콘솔 입출력, Console Input Output (IO)
		// - 입력장치 -> 키보드
		// - 출력장치 -> 모니터
		
		// Ex06_Output.java
		
		/*
			콘솔 출력
			 - 클래스.필드.메서드(인자);
			
			1. System.out.println(값);
				- println 메서드
				- print line -> 값을 행 단위로 출력한다.
							 -> 값을 출력하고 enter를 쳐라.
			
			2. System.out.print(값);
				- print 메서드
				- print		 -> 값을 출력하고 enter는 치지 않는다.
				
			3. System.out.printf(값);
				- print format 메서드
							 -> 형식을 제공해서 값을 출력한다
		*/
		
		System.out.println(100);
		System.out.print(200);
		System.out.println(300);
		
		// 성적표 출력하기 (1, 2 사용)
		String name01 = "홍길동";
		int kor01 = 100;
		int eng01 = 90;
		int math01 = 80;

		String name02 = "아무개";
		int kor02 = 95;
		int eng02 = 89;
		int math02 = 78;
		
		System.out.println("================================");
		System.out.println("            성적표");
		System.out.println("================================");
		System.out.println("[이름]\t[국어]\t[영어]\t[수학]");
		System.out.println("--------------------------------");
		
		System.out.print(name01 + "\t");
		System.out.print(kor01 + "\t");
		System.out.print(eng01 + "\t");
		System.out.println(math01);
		
		System.out.print(name02 + "\t" + kor02 + "\t" + eng02 + "\t" + math02 + "\n");
		
		
		// 요구사항) "안녕하세요, 홍길동 님" 이라고 출력해 주세요. (3 사용)
		
		String name = "홍길동";
		
		System.out.printf("안녕하세요, %s 님\n\n", name);
		
		// 요구사항) "안녕하세요, 홍길동 님. 반갑습니다. 홍길동 님. 안녕히가세요. 홍길동 님" 이라고 출력해 주세요.
		System.out.printf("안녕하세요, %s 님. 반갑습니다. %s 님. 안녕히가세요. %s 님.\n\n", name, name, name);
		
		
		// printf()의 형식 문자
		// 1. %s -> String
		// 2. %d -> Decimal(정수) -> byte / short / int / long 다 됨
		// 3. %f -> Float -> Float, Double
		// 4. %c -> Char
		// 5. %b -> Boolean
		
		System.out.printf("문자열: %s\n", "문자열");
		System.out.printf("정수: %d\n", 100);
		System.out.printf("실수: %f\n", 3.14);
		System.out.printf("문자: %c\n", 'A');
		System.out.printf("논리: %b\n", true);
		System.out.println();
		
		System.out.printf("문자열: %s\n", 100);
		System.out.printf("문자열: %s\n", 3.14);
		System.out.printf("문자열: %s\n", 'A');
		System.out.printf("문자열: %s\n", false);
		// %s는 전부 다 담을 수 있다.
		System.out.println();
		
		// System.out.printf("정수: %d\n", 3.14); -> FormatConversionException 오류 발생
		// System.out.printf("정수: %d\n", "홍길동"); -> FormatConversionException 오류 발생
		// System.out.printf("정수: %d\n", 'A'); -> FormatConversionException 오류 발생
		// System.out.printf("정수: %d\n", true); -> FormatConversionException 오류 발생
		// %d는 정수만 담을 수 있다.
		
		// System.out.printf("실수: %f\n", 3); -> IllegalFormatConversionException 오류 발생
		
		
		// A(65), B(66), ~ ... ~, Z(90), ~ ... ~, a(97), ~ ... ~, z(122)
		// 0(48) ~ 9(57) -> 문자(char)형을 가지는 숫자. '0'. '9' 라고 생각하자.
		// 가(44032) ~ 힣(55203) -> 11,172자가 등록되어 있음
		System.out.printf("문자: %c\n", 65);
		
		
	
		// 형식 문자의 확장 기능
		// 1. %숫자d, %숫자s, %숫자f, %숫자d, %숫자b
		// - 정수
		// - 출력할 너비 지정
		// - 양수(우측 정렬), 음수(좌측 정렬)
		
		int num = 123;
		System.out.printf("[%d]\n", num);
		System.out.printf("[%10d]\n", num);  // 숫자 앞에 공간을 10칸만큼 확보하고 출력. 단, 확보한 공간보다 숫자의 자리수가 크면 의미 없음
		System.out.printf("[%-10d]\n", num); // 숫자 뒤에 공간을 10칸만큼 확보하고 출력. 단, 확보한 공간보다 숫자의 자리수가 크면 의미 없음
		System.out.println();
		
		// 2. %숫자f
		// -소수점 이하 자릿수 지정 가능
		double num2 = 3.14;
		System.out.println(num2);			// 3.14
		System.out.printf("%f\n", num2);	// 3.140000
		System.out.printf("%.2f\n", num2);	// 3.14
		System.out.printf("%.1f\n", num2);	// 3.1
		System.out.printf("%.0f\n", num2);	// 3
		
		System.out.printf("%.3f\n", 3.4567); // n자리 이하를 없앨 때 반올림함. 꼭 확인하면서 작업할 것.(*)
		System.out.println();
		
		// 3. %,d , %,f
		// 자릿수 표기
		// -1000000
		
		int price = 1234567;
		System.out.printf("금액: %,d\n", price); // 1,234,567로 3자리씩 끊어서 출력됨
		
		// 요구사항) 천단위(3자리마다)로 끊고 + 소수 이하 2자리까지 출력해주고 + 출력 너비(20자리, 우측 정렬)		
		double num3 = 1234567.89012345;
		
		System.out.printf("[%,20.2f]\n", num3);
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		
		// 메뉴판 출력 -> 열 정렬 -> tab
		System.out.println("==========================");
		System.out.println("    음료 가격 (단위: 원)");
		System.out.println("==========================");
		System.out.printf("콜라:\t\t%,6d\n", 2500);
		System.out.printf("스무디:\t\t%,6d\n", 2500);
		System.out.printf("사이다:\t\t%,6d\n", 500);
		System.out.printf("아메리카노:\t%,6d\n", 15000);
		
		
		
	}
}
