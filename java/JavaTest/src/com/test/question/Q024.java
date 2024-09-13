package com.test.question;

import java.util.Scanner;

public class Q024 {
	public static void main(String[] args) {
		
		// 여러 배달 음식을 같은 시간에 받기를 원하는 고객이 있다.
		// 고객이 각각의 매장에 몇시에 전화를 해야 모든 음식을 같은 시간에 받을 수 있는지 알려주시오.
		// 조건1: 전화를 걸면 짜장면은 10분 뒤에 도착합니다.
		// 조건2: 전화를 걸면 치킨은 18분 뒤 도착합니다.
		// 조건3: 전화를 걸면 피자는 25분 뒤 도착합니다.
		// 조건4: 음식을 받기 원하는 시간은 오후 11시 이전에만 가능합니다.(날짜 변경 금지)
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("음식을 받기 원하는 시각 ");
		System.out.print("시: ");
		int hour = scan.nextInt();
		System.out.print("분: ");
		int min = scan.nextInt();
		
		int deliverTime_ChineseNoodle = 10;
		int deliverTime_Chicken		  = 18;
		int deliverTime_Pizza 		  = 25;
		
		String name_ChineseNoodle = "짜장면";
		String name_Chicken 	  = "치킨";
		String name_Pizza 		  = "피자";
		
		if (hour >= 23) {
			System.out.println("오후 11시 이후에는 배달을 하지 않습니다."); // 조건4
		} else {
			if (min < deliverTime_ChineseNoodle) {
				
				hour -= 1; // 분단위 계산의 자리내림 보정
				
				deliverTime_ChineseNoodle = min + 60 - deliverTime_ChineseNoodle;
				callWhen(name_ChineseNoodle, hour, deliverTime_ChineseNoodle);
				
				deliverTime_Chicken 	  = min + 60 - deliverTime_Chicken;
				callWhen(name_Chicken, hour, deliverTime_Chicken);
				
				deliverTime_Pizza 		  = min + 60 - deliverTime_Pizza;
				callWhen(name_Pizza, hour, deliverTime_Pizza);
				
			} else if (min < deliverTime_Chicken) {
				
				deliverTime_ChineseNoodle = min - deliverTime_ChineseNoodle;
				callWhen(name_ChineseNoodle, hour, deliverTime_ChineseNoodle);
				
				hour -= 1; // 분단위 계산의 자리내림 보정
				
				deliverTime_Chicken 	  = min + 60 - deliverTime_Chicken;
				callWhen(name_Chicken, hour, deliverTime_Chicken);
				
				deliverTime_Pizza 		  = min + 60 - deliverTime_Pizza;
				callWhen(name_Pizza, hour, deliverTime_Pizza);
				
			} else if (min < deliverTime_Pizza) {
				deliverTime_ChineseNoodle = min - deliverTime_ChineseNoodle;
				callWhen(name_ChineseNoodle, hour, deliverTime_ChineseNoodle);
				
				deliverTime_Chicken 	  = min - deliverTime_Chicken;
				callWhen(name_Chicken, hour, deliverTime_Chicken);
				
				hour -= 1; // 분단위 계산의 자리내림 보정
				
				deliverTime_Pizza 		  = min + 60 - deliverTime_Pizza;	
				callWhen(name_Pizza, hour, deliverTime_Pizza);
				
			} else {
				deliverTime_ChineseNoodle = min - deliverTime_ChineseNoodle;
				callWhen(name_ChineseNoodle, hour, deliverTime_ChineseNoodle);
				
				deliverTime_Chicken 	  = min - deliverTime_Chicken;
				callWhen(name_Chicken, hour, deliverTime_Chicken);
				
				deliverTime_Pizza		  = min - deliverTime_Pizza;
				callWhen(name_Pizza, hour, deliverTime_Pizza);
			}
		}
		scan.close();
	} // main


	private static void callWhen(String menu, int hour, int min) {
		System.out.printf("%s: %d시 %d분\n", menu, hour, min);
	}
	
	
	
}
