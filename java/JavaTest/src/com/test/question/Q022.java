package com.test.question;

import java.util.Calendar;
import java.util.Scanner;

public class Q022 {
	public static void main(String[] args) {
		
		// 남녀 커플의 이름과 만난날을 입력받아 기념일을 출력하시오.
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("남자 이름: ");
		String nameMan = scan.nextLine();
		System.out.print("여자 이름: ");
		String nameWoman = scan.nextLine();
		System.out.print("만난날(년): ");
		int year = scan.nextInt();
		System.out.print("만난날(월): ");
		int month = scan.nextInt();
		System.out.print("만난날(일): ");
		int date = scan.nextInt();
		
		Calendar start = Calendar.getInstance();
		start.set(year, month - 1, date); // Calendar.MONTH는 1월을 0으로 표기한다. 1을 빼서 Calendar.MONTH가 알아볼 수 있게 5(실제로는 6월)로 만든다.
		System.out.printf("%tF\n", start);
		System.out.printf("'%s'과(와) '%s'의 기념일\n", nameMan, nameWoman);
		
		start.add(Calendar.DATE, 100);
		System.out.printf("100일: %tF\n", start);
		
		start.add(Calendar.DATE, 100); // Calendar.DATE가 stack되고 있다. 200일 기념일을 적는다고 +200하지 말 것.
		System.out.printf("200일: %tF\n", start);

		start.add(Calendar.DATE, 100);
		System.out.printf("300일: %tF\n", start);
		
		start.add(Calendar.DATE, 200);
		System.out.printf("500일: %tF\n", start);
		
		start.add(Calendar.DATE, 500);
		System.out.printf("1000일: %tF\n", start);
		
		scan.close();
	}
}
