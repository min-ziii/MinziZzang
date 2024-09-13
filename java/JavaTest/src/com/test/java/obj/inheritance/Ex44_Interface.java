package com.test.java.obj.inheritance;

public class Ex44_Interface {
	public static void main(String[] args) {
		// Ex44_Interface.java
		
		/*
		   	인터페이스, Interface
		   	- 클래스의 일종
		   	
		 */
		
//		A100 a = new A100();
//		
//		a.open();
//		a.set("핸드폰");
//		a.close();
//		a.clean();
//		
//		B200 b = new B200();
//		
//		b.open();
//		b.set("지갑");
//		b.close();
//		b.check();
		
		// 위처럼 코드를 짜면 어떤게 Interface 제공 method이고 어떤게 Class 고유 method인지 구분할 수가 없다.
		
		// Interface는 객체를 생성할 수 없다.
		// Interface는 변수는 생성할 수 있다.
		
//		Bag a; // Interface 참조 변수
//		A100 b; // Class 참조 변수
//		
//		b = new A100();
//		
//		//Bag = A100
//		//부모 = 자식
//		//->Upcasting! (100% 가능.)
//		a = b;
		
		Bag a = new A100();
		
		a.open();
		a.set("핸드폰");
		a.close();
//		a.clean();
		
		Bag b = new B200();
		
		b.open();
		b.set("지갑");
		b.close();
//		b.check();
		
		// a.clean()과 b.check()는 존재하는 기능이지만, 가방들의 객체는 Bag을 거쳐서 참조하기 때문에 Bag에 없는 method는 인식을 못 한다.
		
	}
	
// 인터페이스 선언하기
	
}

interface Keyboard {
	
	// 인터페이스 멤버 선언하기
	// -멤버 변수를 가질 수 없다.
	// -멤버 method
	// 	-구현 method는 가질 수 없다. -> {}를 가진 method는 안된다는 소리. (Abstract methods do not specify a body.)
	// 	-추상 method는 가능.
	
	// ★인터페이스는 추상method의 집합이다.
	// ★인터페이스는 구현 멤버(변수와 일반 method)를 가질 수 없다. 
	
	// ★Keyboard가 가져야 할 행동 규칙을 가진다. -> 추상 method들을 말하는 것.
	// 원형들이니 public을 굳이 선언할 필요가 없다. 어차피 public임.
	void on();
	void off();
	void keydown();
	void keyup();
}

// "제가 만든 F87 Class는 Keyboard Interface를 구현했습니다."
class F87 implements Keyboard { // Class 이름에서 solution 찾으면 Interface function들이 좌라락 나옴 

	@Override
	public void on() {
		System.out.println("전원 On");
		
	}

	@Override
	public void off() {
		System.out.println("전원 Off");
		
	}

	@Override
	public void keydown() {
		System.out.println("키 다운");
		
	}

	@Override
	public void keyup() {
		// TODO Auto-generated method stub
		
	}
	
	
}

// 가방 Class를 두 종류 만들어볼까?
// 가방이라면 가져야 할 행동 규칙을 정의하고 싶다. -> 추상 Class 혹은 Interface를 쓰겠지만, 대부분 Interface를 많이 쓴다.

interface Bag {
	void open();
	void close();
	String get(String item);
	void set(String item);
}

class A100 implements Bag {
	public String color;
	public int price;
	@Override
	public void open() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String get(String item) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void set(String item) {
		// TODO Auto-generated method stub
		
	}
	
	public void clean() {
		// 자동 세척 기능이 있다고 칩시다
	}
}

class B200 implements Bag {
	public int price;
	public String size;
	@Override
	public void open() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String get(String item) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void set(String item) {
		// TODO Auto-generated method stub
		
	}
	
	public void check() {
		// 가방 자가 점검 기능이 있다고 칩시다
	}
}
