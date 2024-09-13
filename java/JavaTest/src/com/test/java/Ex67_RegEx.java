package com.test.java;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex67_RegEx {
	public static void main(String[] args) {
		//Ex67_RegEx.java
		/*
		 	정규 표현식, Regular Expression
		 	 - 정규식
		 	 - 특정 패턴을 가지는 문자열을 검색하는 도구
		 	 
		 	 「Ben Forta - 손에 잡히는 정규표현식」
		 	 
		 	 정규 표현식 요소 (문법)
		 	 
		 	 1. x
		 	 	- x는 임의의 문자 1개이다.
		 	 	ex) a 검색-> a 결과
		 	 	ex) abc 검색 -> abc 결과
		 	 	원하는 검색어 그 자체.
		 	 
		 	 2. .
		 	 	- .은 임의의 문자 1개이다. (wild card)
		 	 	ex) . -> 문자 1개 결과
		 	 	ex) .. -> 문자 2개 결과
		 	 	ex) a.c -> abc, aac, acc, adc, aec, a1c, aㄱc 등 문자 하나가 넣어져서 검색됨
		 	 	ex) a..c -> aaac, abbc, accc, addc 등
		 	 	
		 	 3. 출현 횟수
		 	 	- 바로 앞의 문자(또는 집합)의 출현 횟수를 표현
		 	 	
		 	 	3.1 생략
		 	 		- 바로 앞의 대상의 출현 횟수가 생략 X , 중복 X
		 	 		- 출현 횟수 : 1
		 	 		- 찾고자하는 글자를 적으면 그걸 찾아줌
		 	 	
		 	 	3.2 ?
		 	 		- 바로 앞의 대상의 출현 횟수가 생략될 수는 있지만 중복될 수는 없음.
		 	 		- 출현 횟수: 0 ~ 1
		 	 		ex) abc -> abc만 찾음. / a?bc -> bc, abc
		 	 		ex) 홍?길동 -> 홍은 있어도 되고 없어도 된다. 길동이 있는가?
		 	 		ex) 홍길?동 -> 길은 있어도 되고 없어도 된다. 홍길이라는 단어가 있는지?
		 	 		ex) 홍길동? -> 동은 있어도 되고 없어도 된다.
		 	 		ex) 두 글자를 위 조건처럼 찾고 싶으면 (자바)?코드 처럼 괄호를 쓴다.
		 	 		ex) (자바)?코드 -> 자바 코드가 있는데 코드에 에러가 있어요.
		 	 	
		 	 	3.3 +
		 	 		- 바로 앞의 대상의 출현 횟수가 생략될 수는 없지만 중복될 수 있음.
		 	 		- 출현 횟수 : 1 ~ Infinity
		 	 		ex) a+bc -> bc, ac, aabc, aaabc, aaaaaaaabc 등
		 	 		ex) 홍+길동이 네 이름이지?
		 	 	
		 	 	3.4 *
		 	 		0 바로 앞의 대상의 출현 횟수도 생략 가능 , 중복도 가능
		 	 		- 출현 횟수: 0 ~ Infinity
		 	 		ex a*bc -> bc, abc, abc, aaabc, aaaaaaaabc
		 	 		ex 홍*길동 -> 안녕~ 길동아~ 네이름은 홍길동이지? 홍홍홍홍홍길동
		 	 		
		 	 		ex) abc, aabc, aaabc 만 찾고 싶다. -> a?a?abc
		 	 		
		 	 	4. 선택, choice
		 	 		- 연산자 or 역할
		 	 		- [] 안의 문자 중 하나를 선택
		 	 		
		 	 		4.1 [열거값]
		 	 			ex) 제 나이는 12살입니다. 제 동생은 10살이에요. 키는 160cm입니다 몸무게는 50kg입니다.
		 	 			1. 숫자를 찾고 싶다면?
		 	 			[012] (0,1,2 중 하나를 찾겠다는 뜻. 0 || 1 || 2 와 같다.)
		 	 			
		 	 			ex) 제 나이는 12살입니다. 제 동생은 10살이에요. 키는 160cm입니다 몸무게는 50kg입니다.
		 	 			2. 한글을 찾고 싶다면?
		 	 			[나생은] (문장 속에서 나, 생, 은이라는 글자 하나를 찾는다.)
		 	 			... 이왕이면 ASCII코드 순으로 적어주는 것이 좋다.
		 	 			
		 	 			ex2) 홍길동, 아무개, 김길동, 박길동, 최길동
		 	 			3. 성을 붙여서 X길동을 찾고 싶다면?
		 	 			[김박최홍]길동
		 	 			주의: 박최길동이란 이름이 있다면 최길동만 찾아준다.
		 	 			
		 	 			ex2) 홍길동, 아무개, 김길동, 박길동, 최길동, 박최길동
		 	 			4. [김박최홍]?길동 으로 검색한다면?
		 	 			?의 왼쪽을 선택할 수도 있고 안 할 수도 있다.
		 	 			여전히 박최길동은 최길동 3글자만 검색이 잡힌다.
		 	 			
		 	 			ex2) 홍길동, 아무개, 김길동, 박길동, 최길동, 박최길동, 김김김길동
		 	 			5. [김박최홍]+길동 으로 검색한다면?
		 	 			박최길동과 김김김길동도 검색할 수 있게 되었다.
		 	 			
		 	 			ex3) 숫자 찾기 -> [0123456789]  -> 숫자를 하나하나 찾는다.
		 	 			ex3) 숫자 찾기 -> [0123456789]+ -> 연속되는 숫자도 전부 찾을 수 있다.
		 	 			숫자는 ASCII 코드 순으로 적어주는 게 좋지만 무조건 지켜야 하는 것은 아니다.
		 	 			
		 	 			ex4) 주민등록번호 찾기 -> 950716-213456, 000103-1928374
		 	 			숫자6, -, 숫자7의 형식을 가짐.
		 	 			[0123456789][0123456789][0123456789][0123456789][0123456789][0123456789] : 연속된 숫자 6자리를 찾는 코드
		 	 			[0123456789][0123456789][0123456789][0123456789][0123456789][0123456789]-
		 	 			[0123456789][0123456789][0123456789][0123456789][0123456789][0123456789][0123456789] : 6자리-7자리(주민등록번호)를 찾는 코드
		 	 			
		 	 			ex5) 전화번호 찾기 -> 010-1234-5678 010-536-0987 010-2734-0458 010-4321-9434
		 	 			010-[0123456789][0123456789][0123456789][0123456789]? : 010-3자리나 4자리를 찾을 수 있음.
		 	 			010-[0123456789][0123456789][0123456789][0123456789]?-[0123456789][0123456789][0123456789][0123456789] : 전화번호 찾는 코드
		 	 			
		 	 			ex6)영어 소문자 찾기 -> [abcdefghijklmnopqrstuvwxyz]+
		 	 			영어 대문자 찾기 -> [ABCDEFGHIJKLMNOPQRSTUVWXYZ]+
		 	 			영문자 찾기 -> [abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ]+
		 	 			
		 	 			EX7) 한글 찾기 -> [가나다라마바사... 몇만 자를 언제 다 쳐? 
		 	 			
		 	 			★[]에서 연속된 문자를 간단하게 표현하는 방법
		 	 			숫자 -> [0123456789] -> 연속된 [0-9]로 줄여서 표현할 수 있다.
		 	 			숫자 -> [01236789] (중간에 4,5가 없음) -> [0-36-9]
		 	 			영어 소문자 -> [a-z]
		 	 			영어 대문자 -> [A-Z]
		 	 			영문자 -> [A-Za-z] or [a-zA-Z]/
		 	 			주민등록번호 -> [0-9][0-9][0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9][0-9][0-9][0-9]
		 	 			전화번호 -> 010-[0-9]?[0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]
		 	 			
		 	 			한글 -> [가-힣]
		 	 			
		 	 			ex) 홍씨의 이름(성+이름이 3글자인 경우)만 찾기 -> 홍길동, 홍순신, 홍재석, 홍아지, 홍양이
		 	 			홍[가-힣][가-힣] 
		 	 			ex) 영문자+숫자 찾기 -> [A-Za-z0-9]
		 	 			ex) 영문자+숫자+한글 찾기 -> [A-Za-z0-9가-힣]
		 	 			ex) 식별자에 적합한 문자 (영어 + 숫자 + _) -> [A-Za-z0-9_]
		 	 			
		 	 		
		 	 		4.2 [^열거값]
		 	 			- 4.1의 부정형
		 	 			- 열거값을 제외한 나머지 검색
		 	 			
		 	 			ex) 숫자가 아닌 부분 찾기 -> [^0-9]+
		 	 			
		 	 		5. 출현횟수EX
		 	 			- 바로 앞의 문자의 출현 횟수를 표현
		 	 			- ?, +, *를 사용해 더 세밀한 패턴 지정 가능
		 	 			
		 	 			5.1 {n}
		 	 			- n: 출현 횟수
		 	 			ex) a{1} : bc ab, bc, abc, ac, abbc. -> a가 한 번 나온 단어를 출력
		 	 			
		 	 			5.2 {n, m}
		 	 			- n : 최소 출현 횟수
		 	 			- m : 최대 출현 횟수
		 				a{1,2}bc
		 				
		 	 			
		 	 			5.3 (n, }
		 	 			n : 최소 출현 횟수
		 	 			m을 생략 : 최대 출연수가 무제한
		 	 			
		 	 			5.4 주민등록번호 -> [0-9]{6}-[0-9]{7}
		 	 			
		 	 			
		 	 			5.5 전화번호 -> 010-[0-9]{3,4}-[0,9]{4}
		 	 			
		 	 			
		 	 			5.6 한글 2~4자 찾기 -> [가-힣]{2, 4}
		 	 			
		 	 			
		 	 			5.7 영문자 5자 이상 -> [A-Za-z]{5,}
		 	 			
		 	 			5.8 대문자로 시작하는 영문자 5자 이상 -> [A-Z][A-Za-z]{4,}
		 	 		
		 	 		6. 처음과 끝
		 	 			- 입력 컨트롤 or 입력 값들을 대상으로 사용
		 	 			
		 	 			6.1 ^
		 	 				-뒤의 요소를 꾸며서,  뒤의 표현으로 반드시 시작
		 	 				- str.startsWith("홍")
		 	 				ex) ^홍길동
		 	 			
		 	 			6.2 $
		 	 				앞의 표현으로 반드시 끝나야 함
		 	 				- str.endsWith("동")
		 	 				ex) 홍길동$
		 	 				
		 	 		7. 줄임 표현
		 	 			7.1 \d or \D
		 	 				- Digital (숫자)
		 	 				- [0-9]를 줄임표현으로 줄이면 -> \d
		 	 				- [^0-9] -> \D
		 	 				ex) [0-9]{6}-[0-9]{7} == \d{6}-\d{7}
		 	 				
		 	 			7.2 \w or \W
		 	 				- word -> 영어 대소문자 + 숫자 + _ 조합을 word 라고 한다.
		 	 				ex) [A-Za-z0-9_]{2,5} == \w{2,5}
		 	 				
		 	 			7.3 \s or \S
		 	 				- 공백 문자 (space, enter, \n)
		 	 				ex) \s+
		 */
		
//		m1();
//		m2();
//		m3();
//		m4();
//		m5();
		m6();
		
	} // main

	private static void m6() {
		
		// 유효성 검사 -> 회원가입
		// 1. ID -> 필수값, 4~12자 이내, 영문자+숫자+_, 숫자로 시작 불가
		// 2. 암호 -> 필수값, 4~12자 이내
		// 3. 이름 -> 필수값, 한글 2~5자 이내
		// 4. 나이 -> 필수값, 숫자, 1~120세까지
		
		Scanner scan = new Scanner(System.in);
			System.out.println("[회원 가입]");
			
		while (true) {
			System.out.print("아이디: ");
			String id = scan.nextLine();
		
			if (invalidId(id)) { // 유효성 검사는 잘못된 걸 찾는 게 로직 짜기가 더 쉽다.
				System.out.println("필수값, 4~12자 이내, 영문자+숫자+_, 숫자로 시작 X");
			} else {
				break;
			}
		
		}
		
		while (true) {
			System.out.print("비밀번호: ");
			String pw = scan.nextLine();
		
			if (invalidPw(pw)) {
				System.out.println("필수값, 4~12자 이내, 영문자+숫자+_, 숫자로 시작 X ");
			} else {
				break;
			}
			
		}
		
		while (true) {
			System.out.print("이름: ");
			String name = scan.nextLine();
		
			if (invalidName(name)) { // 유효성 검사는 잘못된 걸 찾는 게 로직 짜기가 더 쉽다.
				System.out.println("필수값, 4~12자 이내, 영문자+숫자+_, 숫자로 시작 X ");
			} else {
				break;
			}
		
		}
		
		while (true) {
			try {
				System.out.print("나이: ");
				int age = scan.nextInt();
				
				if (invalidAge(age)) { // 필수값, 숫자, 1~120세까지
					System.out.println("필수값, 4~12자 이내, 영문자+숫자+_, 숫자로 시작 X ");
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println("숫자만 입력하시오.");
				scan = new Scanner(System.in);
			}
			
		
		}
		
		System.out.println("회원가입 진행");
		System.out.println("완료");
		
		
		
	}
	
	private static boolean invalidAge(int age) {
		if (age < 1 || age > 120) {
			return true;
		}
		return false;
	}

	private static boolean invalidName(String name) {
		String regex = "^[가-힣]{2,5}$";
		
		Pattern p1 = Pattern.compile(regex);
				
		Matcher m1 = p1.matcher(name);
		
		return !m1.find();
	}
	
	private static boolean invalidPw(String pw) {
		String regex = "[A-Za-z_][A-Za-z0-9_]{4,12}";
		
		Pattern p1 = Pattern.compile(regex);
				
		Matcher m1 = p1.matcher(pw);
		
		return m1.find();
	}
	
	//필수값, 4~12자 이내, 영문자+숫자+_, 숫자로 시작 X
	private static boolean invalidId(String id) {
		String regex = "[A-Za-z_][A-Za-z0-9_]{4,12}";
		
		Pattern p1 = Pattern.compile(regex);
				
		Matcher m1 = p1.matcher(id);
		
		return m1.find();
	}

//	private static boolean invalidId(String id) {
//		// 필수
//		// 4~12자 이내
//		// 영문자+숫자+_
//		// 숫자로 시작 X
//		
//		if (id == null || id.equals("")) {
//			return true;
//		}
//		
//		if (id.length() < 4 || id.length() > 12) {
//			return true;
//		}
//		
//		for (int i = 0; i < id.length(); i++) {
//			char c = id.charAt(i);
//				
//			if ((c < 'A' || c > 'Z') && (c > 'a' || c > 'z') && (c < '0' || c > '9') && c != '_') {
//				return true;
//			}
//		}
//		char c2 = id.charAt(0);
//		if (c2 > '0' &&  c2 <= 9) {
//			return true;
//		}
//		
//		return false;
//	}

	private static void m5() {
		String txt = "안녕하세요. 홍길동입니다. 당신 이름도 길동이에요? 아~ 김길동이군요. 저쪽에 최길동도 있어요. 그리고 남궁길동도입니다.";
		
		// 요구사항) 모든 길동이 찾기
		//String regex = "[홍김최(남궁)]길동";
		String regex = "(홍|김|최|남궁)(길동)";
		
		Pattern p1 = Pattern.compile(regex);
		
		Matcher m1 = p1.matcher(txt);
		
		while(m1.find()) {
			System.out.println("찾은 이름: " + m1.group());
			System.out.println("성: " + m1.group(1));
			System.out.println("이름: " + m1.group(2));
			System.out.println();
			
		}
	}

	private static void m4() {
		String txt = "E(lectronic) Mail\r\n"
				+ "\r\n"
				+ "인터넷을 이용하는 메시지 송수신 규약. 직역하면 전자우편이다.\r\n"
				+ "\r\n"
				+ "메일 송신자가 메일 서버로 메일을 보내면 메일 수신자는 메일 서버에서 메일을 받는다. 송신자-서버-수신자가 분리되어 있는 점이 인스턴트 메신저와의 차이점이다. 인스턴트 메신저는 이용자가 송수신자 구분 없이 인터넷에 방을 만들고 들어가서 실시간으로 대화한다.\r\n"
				+ "\r\n"
				+ "영문으론 e-mail과 email 둘 다 맞는 표기법이다.\r\n"
				+ "\r\n"
				+ "마이크로소프트에서는 이메일을 한국어로 반만 직역하여, 전자 메일이라 부른다. 우체국의 우편 체계와 직관적으로 구분하기 위해서 '전자우편'으로 완전히 번역하지 않았다고 한다.\r\n"
				+ "2. 역사[편집]\r\n"
				+ "\r\n"
				+ "1984년 BBC의 'Database'라는 프로그램에서 소개된 'E-mail을 보내는 방법'[1]\r\n"
				+ "1970년대 초반에 발명되었다. 'E-mail'이라는 표현은 1990년대에나 통용되기 시작했고, 전자 우편(electronic mail)이라는 용어는 팩스같이 전자기기를 통한 문서의 교환 방법에 구분 없이 사용되었기 때문에 mail@mail.com이메일의 발명 시점을 정확히 집어서 말하긴 힘들다. 1969년 ARPANET이 만들어지면서 메시지들을 전송하려는 시도가 있었으며, 1971년에는 골뱅이(@) 문자를 사용하는 mail@mail.com메일이 처음으로 보내졌고, 메일 규격을 표준화하려는 시도가 1973년 RFC 561 등으로부터 시작되었다. 현재 사용되는 것과 같은 mail@mail.com메일 전송 규약인 SMTP의 첫 표준 RFC 821이 등장한 것은 1982년.\r\n"
				+ "\r\n"
				+ "간혹 인도계 미국인 소년 시바 아야두라이(V.A. Shiva Ayyadurai)가 1978년에 이메일을 최초로 발명했다고 하는 기사를 찾을 수 있으나#, 이는 당사자의 일방적인 주장으로, 이것을 보도했던 워싱턴 포스트에서도 정정보도를 낸 바 있다. 이메일의 최초 사용자라고 일반적으로 인정받는 사람은 ARPANET의 작업에 참여했던 레이 톰린슨이다. mail@mail.com인터넷은 원래 웹 서핑 하려고 만들어졌던 게 아니라, 문자정보를 주고 받기 위해 만들어진 네트워크였다. 즉, 이메일은 인터넷의 탄생 목적과 연관이 있다. 메일주소 중간에 @를 사용해서 사용자 계정 이름과 이메일 서버 이름을 구분하는 방식 역시 1971년에 인터넷의 전신인 ARPANET 시절 처음 등장했을 정도로 그 역사가 깊다.[2]\r\n"
				+ "\r\n"
				+ "국내에선 인터넷이 대중화되기 전, 그러니까 PC통신 시절에도 '전자 메일'이라는 이름으로 비슷한 게 있었지만 이 시절에는 서비스 제공자끼리 협조가 안 돼서 같은 서비스 가입자끼리만 주고받을 수 있었다는 점이 좀 다르다. (예: 이 프로그램을 사용하시다가 궁금하신 점이 있으면 천리안 namu0821이나 하이텔 namuking으로 메일 보내주세요.) 시간이 지나면서 인터넷이 대중화되자 이들 PC통신 서비스도 인터넷 이메일의 편지함을 자사의 전자메일과 연동시키는 방법으로 인터넷에서도 이메일을 주고받을 수 있도록 했다.";
		
		// 정규식 사이트: http://regexlib.com
		
		// 이메일 주소 검색
		String regex = "\\w+@([a-zA-Z_]+?)\\.[a-zA-Z]{2,3}$";
		
		Pattern p1 = Pattern.compile(regex);
		
		Matcher m1 = p1.matcher(txt);
		
		while(m1.find()) {
			System.out.println(m1.group());
			System.out.println();
		}
	}

	private static void m3() {
		
		// Java는 정규식 클래스를 제공한다.
		String txt = "안녕하세요. 홍길동입니다. 제 연락처는 010-1234-5678입니다. 혹시 연락이 안 되면 010-8765-9876으로 전화 주세요.";
		
		// txt 안에 전화번호가 포함되어 있는지?
		// indexOf()나 contains()로 확인하고 싶은데... 얘들은 RegEx를 쓸 수 없다.
		String regex = "([0-9]{3})-([0-9]{3,4})-([0-9]{4})";
		
		// 정규 표현식 객체
		Pattern p1 = Pattern.compile(regex);
		
		// 검색 + 결과
		Matcher m1 = p1.matcher(txt);
		
//		System.out.println(m1.find()); // true. 대상(txt)에서 정규식 패턴에 일치하는 부분을 발견했다는 뜻
		
//		if (m1.find()) {
//			System.out.println(("txt 내에서 전화번호 발견"));
//		} else {
//			System.out.println(("txt 내에서 전화번호 미발견"));
//		}
		
		// find()는 iterator처럼 순서대로 찾는 전진 커서이다.
//		System.out.println(m1.find()); // true
//		System.out.println(m1.find()); // true
//		System.out.println(m1.find()); // true
//		System.out.println(m1.find()); // false
		
//		int count = 0;
//		while (m1.find()) {
//			count++;
//		}
//		System.out.println("전화번호를 총 " + count +"회 발견했습니다.");
		
		// ([0-9]{3})-([0-9]{3,4})-([0-9]{4})처럼 ()로 묶었으면, group()으로 안의 데이터를 떼서 가져올 수 있음
		while(m1.find()) {
			System.out.println("[전화번호 발견]");
			System.out.println("전화번호: " + m1.group());
			System.out.println("전화번호: " + m1.group(0));
			System.out.println("통신사: " + m1.group(1));
			System.out.println("앞자리: " + m1.group(2));
			System.out.println("뒷자리: " + m1.group(3));
			System.out.println();
		}
		
		
		
	}

	private static void m2() {
		
		// 이름 뜯어오기
		String names1 = "홍길동,아무개,강아지,고양이,병아리";
		
//		String[] list1 = names1.split(",");
//		
//		for (String name : list1) {
//			System.out.println(name);
//		}
		
		// 앗. 가운데에 , 대신 ;를 썼네.
		String names2 = "홍길동,아무개,강아지;고양이,병아리";
//		
//		String[] list2 = names1.split(",");
//		
//		for (String name : list2) {
//			if (name.contains(";")) {
//				String[] sublist = names2.split(";");
//			} else {
//				System.out.println(name);
//			}
//		}
		
//		// 위처럼 for 문을 쓰면서 노가다를 할 필요가 없다.
//		String[] list3 = names2.split("[.,;\\s]"); // split은 RegEx를 지원하니까 정규표현식을 이용해서 특수문자의 조건을 전부 집어넣으면 된다.
		
		// ,가 아니라 ,,가 적혀있는데?
		String names3 = "홍길동,,아무개,강아지;고양이,병아리";
		
		// +로 갯수 제한을 없애면 되지.
		String[] list4 = names2.split("[.,;\\s]+");
	}

	private static void m1() {

		// Java에서는 여러 method가 정규표현식을 지원한다.
		
		// 게시판에서 글을 쓰는데, 개인정보(전화번호)를 masking 한다.
		String txt = "안녕하세요. 홍길동입니다. 제 연락처는 010-1234-5678입니다. 혹시 연락이 안 되면 010-8765-9876으로 전화 주세요.";
		
		// 이전에 썼던 방법은 String.replace()였지만 이 방법은 전화번호를 미리 알고 있을 때나 가능하다.
//		System.out.println(txt.replace("010-1234-5678", "XXX-XXXX-XXXX"));
		
		// 이제는 RegEx를 지원하는 replaceAll()을 사용해본다.
		System.out.println(txt.replaceAll("[0-9]{3}-[0-9]{3,4}-[0-9]{4}", "XXX-XXXX-XXXX")); // 0~9까지 3자리, 0~9까지 3 or 4자리, 0~9까지 3자리
		
		// 이젠 전화번호 구분을 - 대신 .로 쓰네
		String txt2 = "안녕하세요. 홍길동입니다. 제 연락처는 010.1234.5678입니다. 혹시 연락이 안 되면 010.8765.9876으로 전화 주세요.";
		
		// 응안통해ㅋㅋ
		System.out.println(txt2.replaceAll("[0-9]{3}-[0-9]{3,4}-[0-9]{4}", "XXX-XXXX-XXXX")
							   .replaceAll("[0-9]{3}.[0-9]{3,4}.[0-9]{4}", "XXX-XXXX-XXXX"));
		
		 // replaceFirst는 첫 번째로 써진 전화번호만 masking함.
		System.out.println(txt2.replaceFirst("[0-9]{3}-[0-9]{3,4}-[0-9]{4}", "XXX-XXXX-XXXX"));
	}
} // class
