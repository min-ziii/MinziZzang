package com.test.question;

import java.util.Calendar;

public class Q042 {
	public static void main(String[] args) {
		// 서기 1년 1월 1일부터 오늘까지 며칠째인지 합을 구하시오.
		// 조건1: 요일 계산할 때는 Calendar 클래스 사용 금지

		Calendar now = Calendar.getInstance();
		
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;
		int day = now.get(Calendar.DATE);
		
		String dayoftheWeek = "";
		
		
		
		
		
		System.out.printf("%d년 %d월 %d일은 %,d일째 되는 날이고 요일입니다.", year, month, day, dateCalc(year), dayoftheWeek);
	}

	private static int dateCalc(int year) {
		// 윤년의 조건: 4의 배수이지만 100의 배수가 아닌 수. 그러나 400의 배수는 예외적으로 허용.
		// 1900년은 윤년이 아니다. 2000년은 윤년이다.
		int sum = 0;
		for (int i = 1; i < year; i++) { // 2024년을 받았다면 2023년까지
			sum = (leapYear(year) == true) ? sum + 366 : sum + 365;
		}
		return sum;
	}

	private static boolean leapYear(int year) {
		if (year % 4 == 0 && ((year % 100 != 0) || year % 400 == 0)) { // 윤년인가?
			return true;
		} else {
			return false;
		}	
	}
}
