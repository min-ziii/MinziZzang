package com.test.question;

import java.util.Calendar;

public class Q019 {
	public static void main(String[] args) {
		
		// 현재 시간을 출력하는 메소드 선언하시오.
		// 조건1: void nowTime()을 완성하라.
		// 현재 시간을 24시간으로 한 번, 12시간으로 한 번 출력하시오.
		
		nowTime();
		
	}

	private static void nowTime() {
		Calendar now = Calendar.getInstance();
		String amPm = now.get(Calendar.AM_PM) == 0 ? "오전" : "오후";
		System.out.printf("현재 시간: %d시 %d분 %d초\n"
							, now.get(Calendar.HOUR_OF_DAY)
							, now.get(Calendar.MINUTE)
							, now.get(Calendar.SECOND));
		
		System.out.printf("현재 시간: %s %d시 %d분 %d초\n"
							, amPm
							, now.get(Calendar.HOUR) == 0 ? 12 : now.get(Calendar.HOUR)
							, now.get(Calendar.MINUTE)
							, now.get(Calendar.SECOND));
	}
}
