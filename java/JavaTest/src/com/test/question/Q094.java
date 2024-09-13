package com.test.question;

import java.util.Calendar;

public class Q094 {
	public static void main(String[] args) {
		// Bugles 클래스를 설계하시오.
		
		Bugles snack = new Bugles();

		snack.setSize(500);
		snack.setCreationTime("2024-07-04");
		System.out.println("가격 : " + snack.getPrice() + "원");
		System.out.println("유통 기한이 " + snack.getExpiration() + "일 남았습니다.");

		snack.eat();


		Bugles snack2 = new Bugles();

		snack2.setSize(300);
		snack2.setCreationTime("2024-06-26");
		System.out.println("가격 : " + snack2.getPrice() + "원");
		System.out.println("유통 기한이 " + snack2.getExpiration() + "일 남았습니다.");

		snack2.eat();
	}
}

class Bugles {
	private int price;
	private int weight;
	private Calendar creationTime; // 제조일자
	private int expiration; // 유통기한

	public void eat() {
		if (getExpiration() < 0) {
			System.out.println("유통기한이 지나 먹을 수 없습니다.\n");
		} else {
			System.out.println("과자를 맛있게 먹습니다.\n");
		}
		
	}

	public int getExpiration() {
		Calendar now = Calendar.getInstance();
		return this.expiration - (((int)(now.getTimeInMillis() - this.creationTime.getTimeInMillis())) / 1000 / 60 / 60 / 24);
		
	}

	public void setCreationTime(String time) {
		
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(time.substring(0, 4)),
			  Integer.parseInt(time.substring(5, 7)) - 1,
			  Integer.parseInt(time.substring(8)));
		
		this.creationTime = c;
	}

	public int getPrice() {
		return price;
	}

	public void setSize(int weight) { // 용량, 쓰기 전용
		if (weight == 300 || weight == 500 || weight == 800) {
			this.weight = weight;
			
			if (this.weight == 300) {
				this.expiration = 7;
				this.price = 850;
			} else if (this.weight == 500) {
				this.expiration = 10;
				this.price = 1200;
			} else if (this.weight == 850) {
				this.expiration = 15;
				this.price = 1950;
			}
		}	
	}
		
}
