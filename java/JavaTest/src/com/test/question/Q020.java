package com.test.question;

import java.util.Calendar;

public class Q020 {
	public static void main(String[] args) {
		
		// 오늘 태어난 아이의 백일과 첫돌을 출력하시오.
		
		Calendar birthday = Calendar.getInstance(); // 태어난 날(오늘)
		
		birthday.add(Calendar.DATE, 99);			// 오늘 + 99일
		System.out.printf("백일: %tF\n", birthday); // %tF == 연-월-일로 출력, %tT == 시:분:초로 출력
		birthday = Calendar.getInstance();			// 다시 오늘로 초기화
		birthday.add(Calendar.YEAR, 1);				// 오늘 + 1년
		System.out.printf("첫돌: %tF\n", birthday);
	}
}
