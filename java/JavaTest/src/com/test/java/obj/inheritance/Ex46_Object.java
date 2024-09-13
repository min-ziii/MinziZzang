package com.test.java.obj.inheritance;

import java.util.Scanner;

public class Ex46_Object {
	public static void main(String[] args) {
		
		// Ex46_Object.java
		/*
			Object Class
			- 모든 Class의 부모 Class이다.
			
			
		*/
		
		AAA a1 = new AAA();
		AAA a2 = new BBB(); // 부모 변수에 자식 객체를 넣는다. Upcasting
		AAA a3 = new CCC(); // 조부모 변수에 조손 객체를 넣는다
		
		// Object 참조 변수는 만능 타입이다. -> 모든 것을 저장
		Object o1 = new Object();
		Object o2 = new AAA();
		Object o3 = new BBB();
		Object o4 = new CCC();
		
		Object o5 = new Scanner(System.in);
		Object o6 = new Test();
		Object o7 = new int[3];
		Object o8 = "홍길동";
		
		// 값형의 data를 Object 변수에 넣을 때
		// -자동으로 Boxing이 발생
		//이상하다? 100(int, 값형), true(boolean, 값형)는 둘 다 참조형이 아닌데?
		Object o9 = 100; // boxing
//		Object o9 = new Integer(100);
		Object o10 = true;
		
		System.out.println((boolean)o10? "O" : "X");
		
		System.out.println((int)o9 + 100); // unboxing
		System.out.println((Integer)o9 + 100);
		
		int a = 100;
		Integer b = new Integer(100);
		
		Object[] list = new Object [5];
		list[0] = 100;
		list[1] = 222;
		list[2] = 333;
		list[3] = 444;
		list[4] = 555;
		
		for (int i = 0; i < list.length; i++) {
			System.out.println((int)list[i] + 100);
		}
	} //main
	
	
	
}//class

class AAA { // extends Object
	public int a;
}

class BBB extends AAA {
	public int b;
}

class CCC extends BBB {
	public int c;
}