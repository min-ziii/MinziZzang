package com.test.java.obj;

public class Ex36_Static {
	
	public static void main(String[] args) {
		
		//Ex36_Static.java
		
		//요구사항]
		//1. 펜을 생산하시오.
		//2. 생산된 펜의 개수를 세시오.
		
		
		//Case 1.
		//- 간편함.
		//- Pen과 count간의 관계 애매..
		//- count++ 오류
		
		/*
		int count = 0; //누적 변수
		
		Pen p1 = new Pen("MonAmi", "Black");
		count++;
		
		Pen p2 = new Pen("MonAmi", "Black");
		count++;
		
		Pen p3 = new Pen("MonAmi", "Black");
		count++;
		
		System.out.println("펜의 개수: " + count);
		*/
				
		
		
		//Case 2.
		//- Pen과 count간의 관계 > 강해짐
		//- count 변수를 객체마다 가지고 있음.(문제점) > 
		//	a. 누적 변수를 여러개??(물리적 이유 > 약함)
		//	b. 왜? 누적 변수를 p1껄 사용?? > 왜 누적 변수를 개인이 가지고 있는가? > 공용 누적값을 왜 객체 변수(개인 값을 저장하는 공간)에 넣었는가?
		
		/*
		Pen p1 = new Pen("MonAmi", "Black");
		p1.count++;
		
		Pen p2 = new Pen("MonAmi", "Black");
		p1.count++;
		
		Pen p3 = new Pen("MonAmi", "Black");
		p1.count++;
		
		System.out.println("펜의 개수: " + p1.count);
		System.out.println("펜의 개수: " + p2.count);
		System.out.println("펜의 개수: " + p3.count);
		*/
		
		
		//Case 3.
		//- Pen클래스와 count간의 관계 강화
		//- 개인 데이터와 공용 데이터 구분
		/*
		Pen p1 = new Pen("MonAmi", "Black");
		Pen.count++;
		
		Pen p2 = new Pen("MonAmi", "Black");
		Pen.count++;
		
		Pen p3 = new Pen("MonAmi", "Black");
		Pen.count++;
		
		System.out.println("펜의 개수: " + Pen.count);
		*/
		
		
		//Case 4.
		Pen p1 = new Pen("MonAmi", "Black");
		Pen p2 = new Pen("MonAmi", "Black");
		Pen p3 = new Pen("MonAmi", "Black");
		
		System.out.println("펜의 개수: " + Pen.count);
		
		m1();
		
	}//main
	
	public static void m1() {
		
		Pen p = new Pen("젤리펜", "Blue"); //객체 변수 생성
		
		
		
	}//제어 종료 > 객체 변수 소멸

}//class


class Pen {
	
	//멤버 변수의 생명주기
	//1. 객체 변수
	//	- new(생성) ~ 객체 소멸(소멸)
	//2. 정적 변수
	//	- 클래스로딩(생성) ~ 프로그램 종료(소멸)
	
	private String model;
	private String color;
	public static int count = 0; //누적 변수(개인X > 모두O)
	
	public Pen(String model, String color) {
		
		this.model = model;
		this.color = color;
		
		Pen.count++;
		
	}
	
	public String info() {
		return String.format("[model: %s, color: %s]"
								, this.model
								, this.color);
	}
	
}


//class Pen {
//	
//	private String model;
//	private String color;
//	public static int count = 0; //누적 변수(개인X > 모두O)
//	
//	public Pen(String model, String color) {
//		this.model = model;
//		this.color = color;
//	}
//	
//	public String info() {
//		return String.format("[model: %s, color: %s]"
//								, this.model
//								, this.color);
//	}
//	
//}



//class Pen {
//	
//	private String model;
//	private String color;
//	public int count = 0; //누적 변수
//	
//	public Pen(String model, String color) {
//		this.model = model;
//		this.color = color;
//	}
//	
//	public String info() {
//		return String.format("[model: %s, color: %s]"
//								, this.model
//								, this.color);
//	}
//	
//}


//class Pen {
//	
//	private String model;
//	private String color;
//	
//	public Pen(String model, String color) {
//		this.model = model;
//		this.color = color;
//	}
//	
//	public String info() {
//		return String.format("[model: %s, color: %s]"
//								, this.model
//								, this.color);
//	}
//	
//}












