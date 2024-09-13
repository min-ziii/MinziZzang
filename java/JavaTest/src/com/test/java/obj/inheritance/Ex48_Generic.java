package com.test.java.obj.inheritance;

public class Ex48_Generic {
	public static void main(String[] args) {
		// Ex48_Generic.java
		
		
		Item<String> s1 = new Item<String>();
		s1.a = 10;
		s1.b = "문자열";
		s1.c = "홍길동";
		
		Item<Integer> s2 = new Item<Integer>();
		
		s2.c = 100;
		
		Pen<Integer> p1 = new Pen<Integer>();
		Pen<Integer> p2 = new Pen<>();

		p1.a = 10;
		p1.b = 20;
		p1.c = 30;
		
		Desk<Boolean> d1 = new Desk<>();
		
		d1.setData(true);
		System.out.println(d1.getData());
		
		Laptop<String, Integer> l1 = new Laptop<String, Integer>("맥북", 1000);
		
		System.out.println(l1.getA());
		System.out.println(l1.getB());
		
		
		
	}
}

// Generic Class
//  T:타입 변수(매개변수 역할).  -> 자료형을 저장하는 함수
//  T = string이 될 수도 있고
//  T = Integer가 될 수 도 있다.


class Item<T> {
	public int a;
	public String b;
	public T c;
	
}

class Pen<T> {
	public T a;
	public T b;
	public T c;
}

class Desk<T> {
	private T data;
	
	public void setData(T data) {
		this.data = data;
	}
	
	public T getData() {
		return this.data;
	}
	
	public void Test(T item) {
		System.out.println(item);
	}
	
	public void aaa() {
		T a; // 지역 변수에도 쓸 순 있는데 안 하는 게 좋음
	}
}

class Laptop<T, U> {
	private T a;
	private U b;
	
	public Laptop(T a, U b) {
		this.a = a;
		this.b = b;
	}
	
	public T getA() {
		return this.a;
	}
	
	public U getB() {
		return this.b;
	}
}