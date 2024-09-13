package com.test.java.obj.inheritance;

public class Ex37_Inheritance {
	
	//Ctrl + Shift + L
	
	public static void main(String[] args) {
		
		//Ex37_Inheritance.java
		/*
		
			상속, Inheritance
			- 부모가 가지는 재산을 자식에게 물려주는 행동
			- 부모(클래스)가 가지는 재산(멤버 변수, 멤버 메서드)을 자식(클래스)에게 물려주는 행동
			
			
			상속 관계에 있는 클래스 호칭
			- 부모 클래스 <-> 자식 클래스
			- 슈퍼 클래스 <-> 서브 클래스
			- 기본 클래스 <-> 확장 클래스
			- 기본 클래스 <-> 파생 클래스
			
		
		*/
		
		
		//상속 발생 > 부모 클래스는 아무런 영향을 받지 않는다.
		Parent p = new Parent();
		
		p.a = 10;
		p.b = 20;
		p.ccc();


		Child c = new Child();
		
		c.a = 10;
		c.b = 20;
		c.ccc();
		
		c.d = 30;
		c.e = 40;
		c.fff();
		
		
	}//main

}//class


//부모 클래스
class Parent {
	
	public int a;
	public int b;
	
	public void ccc() {
		System.out.println("ccc");
	}
	
}

//자식 클래스
class Child extends Parent {
	
	//부모 멤버 > 상속
	
	public int d;
	public int e;
	
	public void fff() {
		System.out.println("fff");
	}
	
}












