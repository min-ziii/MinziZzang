package com.test.question;

import java.util.Scanner;

public class Q014 {
	public static void main(String[] args) {
		
		// 지하철 탑승 소요시간을 구하시오.
		// 지나가는 역의 개수, 환승역의 횟수, 시간대를 전달받아 총 걸리는 시간을 반환하는 method를 선언하시오.
		
		// 조건1: int getTime(int station, int change, int time) method를 완성하라.
		// 조건2: 각 역간 소요시간은 2분이다.
		// 조건3: 환승 소요시간은 시간대에 따라 다르며, 평상시 3분, 출근시 4분, 퇴근시 5분이다.
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("역의 개수: ");
		int station = scan.nextInt();
		System.out.print("환승역의 횟수: ");
		int change = scan.nextInt();
		System.out.print("시간대(1.평상시, 2.출근시, 3.퇴근시): ");
		int time = scan.nextInt();
		
		int reachTime = getTime(station, change, time);
		
		System.out.printf("총 소요 시간은 %d분입니다.", reachTime);
		
		scan.close();
	}
	
	public static int getTime(int station, int change, int time) {
		int reachTime = (station * 2) + change * (time + 2);
		return reachTime;
	}
}
