package com.test.java;

import java.util.Calendar;
import java.util.Date;

public class Ex19_DateTime {
	public static void main(String[] args) {
		
		// Ex19_DateTime.java
		
		/*
		   
		   java의 날짜/시간 자료형
		   1. Date Class
		   2. Calendar Class
		   3. java.time Package 속의 여러 Class
		   1,2번은 살짝 구형이라 3번을 주로 쓰는 추세지만 그렇다고 1,2번 class를 안 쓴다는 건 아님.
		   
		   2024년 6월 25일 17시 22분 15초 (시각)
		   8시간 (시간)
		   
		   시각 + 시각은 굳이 하지 않지만, 시각 - 시각은 자주 하는 일.
		   시각 - 시각 = 시간
		   
		   시간 + 시간 = 시간
		   시간 - 시간 = 시간
		   
		   시각 + 시간 = 시각
		   
		   
		 */
// 변수가 무슨 변수인지 모를 땐 변수 클릭 후 F3 or Ctrl+ Click을 사용
		
//		m1();
//		m2();
//		m3();
//		m4();
//		m5();
//		m6();
		m7();
		
		
		
		
		
		
		
		
//		int a = 10;
//		int b = 5;
//		
//		System.out.println(a + " + " + b + " = " + a + b); // a + b가 15가 나와야 할 것 같지만 105가 출력됐다.
//		
//		// +가 산술연산자 +인가? 아니면 문자열 concat연산자 +인가?
//		System.out.println(10 + 20 + 30);	// 60
//		System.out.println(10 + 20 + "30");	// 3030
//		System.out.println(10 + "20" + 30);	// 102030
//		System.out.println("10" + 20 + 30);	// 102030
				
//		int a = 10;
//		a = 20;
//		a = 30;
//		
//		System.out.println(a);
//		
//		test(a);
//				
//		System.out.println(a);
		
		
	} // main
	
	private static void m7() {
		
		// 연산
		// 시간 + 시간 = 시간
		// 시간 - 시간 = 시간
		
		// 2시간 + 1시간 = 3시간.
		int h1 = 2;
		int h2 = 1;
		System.out.printf("%d시간\n", h1 + h2);
		
		// 2시간 30분 + 10분 = 2시간 40분
		int hour = 2;
		int min = 30;
		min += 10;
		System.out.printf("%d시간 %d분\n", hour, min);
		
		// 2시간 30분 + 40분 = 3시간 10분
		hour = 2;
		min = 30;
		min += 40;
		// 자리올림
		hour = hour + (min / 60);
		min = min % 60;
		System.out.printf("%d시간 %d분\n", hour, min);
		
	}

	private static void m6() {
		
		// 연산
		// 시각- 시각 = 시간
		
		// 내가 태어나서 살아온 시간은 얼마나 되나?
		Calendar now = Calendar.getInstance();		// 현재 시간
		Calendar birthday = Calendar.getInstance();	// 생일
		birthday.set(1996, 5, 28);					// 1996년 6월 28일
		
		
//		System.out.println(now - birthday); // The operator - is undefined for the argument type(s) java.util.Calendar, java.util.Calendar
//		// 산술연산자(-)는 반드시 숫자형을 가져야 하기 때문에, (연-월-일) 형인 Calendar형 변수에서는 직접 사용할 수 없다.
		// 그럼 어케함? -> 기준점을 만들고 기준점~목표점까지의 시간을 구한 후, 그 시간끼리 연산을 한다.
		
		System.out.println(birthday.getTimeInMillis()); // 835928297697. long형 변수이다. 기준점은 java 기준 1970-1-1이며, 밀리초 단위로 시간을 구했다.
		System.out.println(now.getTimeInMillis()); // 1719368416517
		
		long livingTime = now.getTimeInMillis() - birthday.getTimeInMillis();
		
		System.out.printf("살아온 시간: %,dms\n", livingTime);
		System.out.printf("살아온 시간: %,d시간\n", livingTime / 1000 / 60 / 60);
		System.out.printf("살아온 시간: %,d일\n", livingTime / 1000 / 60 / 60 / 24);
		
		
		// 개강일부터 종강일은 며칠인가?
		Calendar end = Calendar.getInstance();
		end.set(2024, 11, 2); // 월 - 1을 해주는 걸 잊지 말 것.
		
		System.out.printf("남은 수업일 수: %d일\n", (end.getTimeInMillis() - now.getTimeInMillis()) / 1000 / 60 / 60 / 24);
		
		// 오늘 집에 가려면 몇 시간 남았는지?
		Calendar out = Calendar.getInstance();
		out.set(Calendar.HOUR_OF_DAY, 17); // 12시간 기준 time\
		out.set(Calendar.MINUTE, 50);
		out.set(Calendar.SECOND, 0);
		
		System.out.printf("남은 시간: %.1f 시간\n", (out.getTimeInMillis() - now.getTimeInMillis()) / 1000.0 / 60 / 60);
		
		// getTimeInMillis()
		// Epoch Time, 또는 Tick 이라고 부름
	}

	private static void m5() {
		
		// 연산
		// 시각 + 시간 = 시각
		// 시각 - 시간 = 시각
		
		// 오늘 만난 커플이 100일이 되는 시점은?
		
		Calendar now = Calendar.getInstance(); // 오늘
		
//		System.out.printf("1일차: %tF\n", now); 	// 2024-06-26
//		now.add(Calendar.DATE, 100);				// 100일 후
//		System.out.printf("100일차: %tF\n", now); 	// 2024-10-04
		
//		now.add(Calendar.YEAR, 20);		// 20년 후
//		now.add(Calendar.MONTH, 5);		// 5개월 후
//		now.add(Calendar.HOUR, 3);		// 3시간 후
//		now.add(Calendar.DATE, -7);		// 일주일 전
//		now.add(Calendar.MINUTE, -45);	// 45분 전
		
		System.out.printf("%tF %tT\n", now, now); // 연-월-일 시:분:초
		
	}

	private static void m4() {
		Calendar now = Calendar.getInstance();
		
		// 내가 원하는 부분만 추출
		// -int get(int)
		System.out.println(now.get(Calendar.YEAR));			// 2024, 년
		System.out.println(now.get(Calendar.MONTH));		// 5, 월(0~11)
		System.out.println(now.get(Calendar.DATE));			// 26, 일
		System.out.println(now.get(Calendar.HOUR));			// 2, 시(12H)
		System.out.println(now.get(Calendar.HOUR_OF_DAY)); 	// 14, 시(24H)
		System.out.println(now.get(Calendar.MINUTE));		// 49, 분
		System.out.println(now.get(Calendar.SECOND));		// 5, 초
		System.out.println(now.get(Calendar.MILLISECOND));	// 101, 밀리초
		System.out.println(now.get(Calendar.AM_PM));		// 0, 오전(0), 오후(1)
		System.out.println(now.get(Calendar.DAY_OF_YEAR));	// 178 일(년)
		System.out.println(now.get(Calendar.DAY_OF_MONTH));	// 26, 일(월)
		System.out.println(now.get(Calendar.DAY_OF_WEEK));	// 4, 일(주) == 요일, 1(일) ~ 7(토)
		System.out.println(now.get(Calendar.WEEK_OF_YEAR));	// 26, 주(년)
		System.out.println(now.get(Calendar.WEEK_OF_MONTH));// 5, 주(월)
		
		
		// 요구사항) "오늘은 2024년 6월 26일입니다." 출력
		System.out.printf("오늘은 %d년 %d월 %d일입니다.\n"
							, now.get(Calendar.YEAR)
							, now.get(Calendar.MONTH) + 1 // 1월이 0부터 시작하니까 1을 더함
							, now.get(Calendar.DATE));
		
		// 요구사항) "오늘은 2024년 06월 26일입니다." 출력
		System.out.printf("오늘은 %d년 %s월 %d일입니다.\n" // 삼항연산자와 String 붙여넣기로 구현
							, now.get(Calendar.YEAR)
							, now.get(Calendar.MONTH) + 1 < 10 ? "0" + (now.get(Calendar.MONTH) + 1) : (now.get(Calendar.MONTH) + 1) + ""
							, now.get(Calendar.DATE));
		
		// 요구사항) "오늘은 2024년 06월 26일입니다." 출력
		System.out.printf("오늘은 %d년 %s월 %s일입니다.\n" // addZero() method를 따로 구현해서 String으로 집어넣음
				, now.get(Calendar.YEAR)
				, addZero(now.get(Calendar.MONTH) + 1)
				, addZero(now.get(Calendar.DATE)));
		
		// 요구사항) "오늘은 2024년 6월 26일입니다." 출력
		System.out.printf("오늘은 %d년 %02d월 %02d일입니다.\n" // 2자리를 확보하고, 자리가 다 차지 않았을 경우 0으로 채움
							, now.get(Calendar.YEAR)
							, now.get(Calendar.MONTH) + 1
							, now.get(Calendar.DATE));
		
		// printf, String format
		// -날짜/시간 관련된 형식 문자
		System.out.printf("%tF\n", now); // 2024-06-26
		System.out.printf("%tT\n", now); // 10:40:25 (시:분:초)
		System.out.printf("%tA\n", now); // 수요일
		
	}

	private static String addZero(int i) {
		
		String result = i < 10? "0" + i : "" + i;
		
		return result;
	}

	private static void m3() {
		
		// Method 이름의 패턴
		// -getXXX(): getter. 읽기 작업을 할 때 쓰는 method
		//	ex) getName(), getAge(), getNum()
		
		// -setXXX(): setter. 쓰기 작업을 할 때 쓰는 method
		//	ex) setName("홍길동"), setAge(20)
		
		// -isXXX() : 확인. 반환값(boolean)을 받을 때 쓰는 method -> isWhat? yes OR no.
		//	ex isNumeric("10")
		
		// Calendar Class
		// 1. int get(int)
		// 2.void set(값)
		
		
		// 현재 시각
		Calendar now = Calendar.getInstance();
		
		// 특정 시각 만들기
		// ->현재 시각을 만들고 값을 수정해서 원하는 시각으로 만든다.
		Calendar christmas = Calendar.getInstance(); // 2024-06-26
		
		
		System.out.println(Calendar.YEAR); // 캘린더 상수
		//christmas.set(year.25);
		christmas.set(Calendar.YEAR, 2025); 
		christmas.set(Calendar.MONTH, 2025); 
		christmas.set(Calendar.DATE, 2025);  
		christmas.set(Calendar.DATE, 2025);
		
//		christmas.set(2024, 12, 25);
		christmas.set(2024, 12, 25, 14, 30); // 2024.12.25 14시 30분
		christmas.set(2024, 12, 25, 14, 30 ,10);
		
		System.out.println(christmas);
	}

//	// Line 왼쪽에 더블클릭을 하면 파란 원으로 Breakpoint가 만들어진다.
//	// F11로 Debug를 시작하면 Breakpoint Line 직전까지 실행이 된다.
//	// F6을 누르면 그 이후 한 줄씩 실행된다.
//	// F8을 누르면 다음 Breakpoint로 이동한다.
//	// 확인이 끝난 Breakpoint는 지워주는 게 좋다.
//	private static void test(int a) {
//		int b = a + 10;
//		b *=2;
//		System.out.println(b);
//	}


	private static void m2() {

		// 현재 시각을 Calendar()로 얻어오기
		Calendar now = Calendar.getInstance();
		
		System.out.println(now);
		
		// java.util.GregorianCalendar
		//[time=1719304861158,areFieldsSet=true,areAllFieldsSet=true,lenient=true,zone=sun.util.calendar.ZoneInfo
		//[id="GMT+09:00",offset=32400000,dstSavings=0,useDaylight=false,transitions=0,lastRule=null],
		//firstDayOfWeek=1,minimalDaysInFirstWeek=1,ERA=1,YEAR=2024,MONTH=5,
		//WEEK_OF_YEAR=26,WEEK_OF_MONTH=5,DAY_OF_MONTH=25,DAY_OF_YEAR=177,DAY_OF_WEEK=3,DAY_OF_WEEK_IN_MONTH=4,
		//AM_PM=1,HOUR=5,HOUR_OF_DAY=17,MINUTE=41,SECOND=1,MILLISECOND=158,ZONE_OFFSET=32400000,DST_OFFSET=0]

		
	}

	private static void m1() {
		
		// 현재 시각을 Date()로 얻어오기
		// 컴퓨터 메인보드의 시간을 가져옴
		Date now = new Date();
		System.out.println(now);
	}
}
