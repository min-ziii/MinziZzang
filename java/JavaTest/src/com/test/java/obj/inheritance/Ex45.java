package com.test.java.obj.inheritance;

public class Ex45 {
	public static void main(String[] args) {
		
		// Ex45.java
		
		// 상황) 프린터 대리점을 운영한다.
		// 1. LG100 모델 5대, HP200 모델 3대의 재고를 보유하고 있다.
		// 2. 하루 한 번씩 제품들을 점검해야 한다.
		
		// 추가 상황)
		// 1. 프린터 재고 변동 - LG100이 500대, HP200이 300대로 증가
		// 2. 프린터 종류 변동 - Dell300, BenQ400을 매장에 추가
		
		// 결론.
		// 형제 관계의 Class들은 부모 배열을 만들어서 한 번에 관리할 수 있다. (Upcasting)
		// 가끔 자식 Class의 구현 기능을 접근해야 할 일이 생기면 Downcasting을 사용한다.
		
//		m1();
//		m2();
		m3();
//		m4();
	}
	
	private static void m4() {
		
		// Abstract Class Printer 1개
		// Class LG100 1개
		// Class HP200 1개
		
		LG100 p1 = new LG100();
		HP200 p2 = new HP200();
//		Printer p3 = new Printer();
		Printer p3 = new LG100(); // UpCasting (참조 형변환)
		Printer p4 = new HP200();
		
		Printer[] list = new Printer[2];
		list[0] = new LG100(); // == p3
		list[1] = new HP200(); // == p4
		
	}

	private static void m3() {
		
		// Case 3: 배열 + 참조 형변환 사용
		
		Printer[] list = new Printer[80];
		
		for (int i=0; i<list.length; i++) {
			
			if (i < 5) {
				list[i] = new LG100();
			} else {
				list[i] = new HP200();
			}
		}
		
		// 점검 code
		for (int i=0; i<list.length; i++) {
			list[i].print();
//			list[i].check();
			
//			System.out.println(객체 instanceof 클래스);
//			System.out.println(list[i] instanceof LG100); // 이 Class의 Instance냐? 를 물어보는 연산자
//			System.out.println(list[i] instanceof HP200);
			
			if (list[i] instanceof LG100) {
				LG100 lg = (LG100)list[i]; // DownCasting으로 자식 Class의 고유 기능에 접근할 수 있다.
				lg.check();
			} else if (list[i] instanceof HP200 ){
				HP200 hp = (HP200)list[i];
				hp.call();
			}
			
		}
		
	}

	private static void m2() {
		
		// Case 2: 배열 사용
		// 수량 변동에는 강한 모습을 보이지만, 종류 변동에는 취약한 모습을 보인다.
		LG100[] lg = new LG100[500]; // LG프린터 객체가 들어갈 '공간'을 선언한 것
		HP200[] hp = new HP200[300];
		Dell300[] dell = new Dell300[5];
		
		for (int i=0; i<lg.length; i++) {
			lg[i] = new LG100();
		}
		
		for (int i=0; i<hp.length; i++) {
			hp[i] = new HP200();
		}
		
		// 점검을 반복하는 code
		for (int i=0; i<lg.length; i++) {
			lg[i].print();
			lg[i].check();
		}
		for (int i=0; i<hp.length; i++) {
			hp[i].print();
			hp[i].call();
		}
	}

	private static void m1() {
		
		// Case 1: 일일이 선언
		// - 가장 비효율적인 방법.
		// - 프린터 1대당 비용이 동일하게 발생함.
		LG100 lg1 = new LG100();
		LG100 lg2 = new LG100();
		LG100 lg3 = new LG100();
		LG100 lg4 = new LG100();
		LG100 lg5 = new LG100();
		
		HP200 hp1 = new HP200();
		HP200 hp2 = new HP200();
		HP200 hp3 = new HP200();
		
		lg1.print();
		lg2.print();
		lg3.print();
		lg4.print();
		lg5.print();
		
		hp1.print();
		hp2.print();
		hp3.print();
		
		
		
	}
	
}

// 부모 역할이 가능한 것
// 1. 일반 Class
// 2. 추상 Class
// 3. Interface

// 구현부(멤버 변수, 구현부가 포함된 Method)를 가질 수 있는 부모
// 1. 일반 Class
// 2. 추상 Class

//print()라는 공통 Method를 추상 Method로 선언할 수 있는 부모 -> 추상 Class

abstract class Printer {
	
	String model;
	int price;
	
	public abstract void print();
}

class LG100 extends Printer {
	
	private String color;
	
	public void print() {
		System.out.println("LG 기술을 사용한 출력");
	}
	
	public void check() {
		System.out.println("자가 점검");
	}
	
}

class HP200 extends Printer {
	
	private String type;
	
	public void print() {
		System.out.println("HP 기술을 사용한 출력");
	}
	
	public void call() {
		System.out.println("상담원 연결");
	}
}

class Dell300 extends Printer {
	
	public void print() {
		System.out.println("Dell 기술을 사용한 출력");
	}
}

class BenQ400 extends Printer {
	
	public void print() {
		System.out.println("BenQ 기술을 사용한 출력");
	}
}
