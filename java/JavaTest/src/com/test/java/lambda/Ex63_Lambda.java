package com.test.java.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Ex63_Lambda {
	public static void main(String[] args) {
		//Ex63_Lambda.java
		/*
		   	람다식, Lambda Expression
		   	- 함수형 프로그래밍 기법 중 하나
		   	- 익명 함수Anonymous Function를 간단하게 표현하는 방법
		   	
		   	람다식 형식
		   	- 인터페이스 변수 = 람다식;
		 */
		
		// 요구사항) MyInterface를 구현(상속)한 객체를 생성하시오.
		
		// Case 1. Class 선언 + 객체 생성
		MyInterface m1 = new MyClass();
		m1.test();
		
		// Case 2. Anonymous 객체 생성 -> 한번만 만들어도 되거나 여러 번 만들어도 기능 차이가 없는 경우
		MyInterface m2 = new MyInterface() {

			@Override
			public void test() {
				System.out.println("익명 객체에서 구현한 Method");	
			}
		};
		m2.test();
		
		
		// Case 3. lambda 식: 익명 클래스의 추상 메소드를 간단한 표현으로 구현하는 기술
		MyInterface m3 = () -> {
			System.out.println("lambda식에서 구현한 Method");
		};
		m3.test();
		
		TestInterface t1 = new TestInterface() {
			
			@Override
			public void bbb() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void aaa() {
				// TODO Auto-generated method stub
				
			}
		};
		t1.aaa();
		t1.bbb();
		
//		// error: The target type of this expression must be a functional interface
//		// 람다식의 타입(TestInterface)이 functional interface(추상메소드가 하나뿐인 인터페이스)여야 한다.
//		TestInterface t2 = () -> {
//			
//		};
		
		// 추상 Method의 형식
		// 1. 매개변수의 유/무
		// 2. 반환값의 유/무
		
		NoParameterNoReturn pr1 = new NoParameterNoReturn() {
			
			@Override
			public void call() {
				System.out.println("pr1");
				
			}
		};
		pr1.call();
		
		NoParameterNoReturn pr2 = () -> {
			System.out.println("pr2");
		};
		pr2.call();
		
		// Lambda식 구현부가 딱 한 문장이면 중괄호를 생략할 수 있다.
		NoParameterNoReturn pr3 = () -> System.out.println("pr3");
		pr3.call();
		
		// void call();
		ParameterNoReturn pr4 = (int n) -> {
			System.out.println("pr4: " + n);
		};
		pr4.call(10);
		pr4.call(20);
		
		// Lambda식의 매개변수 자료형은 생략할 수 있다.
		ParameterNoReturn pr5 = (n) -> {
			System.out.println("pr5: " + n);
		};
		pr5.call(10);
		pr5.call(20);
		
		// Lambda식의 매개변수 자료형에 붙는 소괄호도 생략할 수 있다. 매개변수가 없으면 안 됨.
		ParameterNoReturn pr6 = n -> {
			System.out.println("pr6: " + n);
		};
		pr6.call(10);
		pr6.call(20);
		
		ParameterNoReturn pr7 = n -> System.out.println("pr7: " + n);
		pr7.call(10);
		pr7.call(20);
		
		MultiParamaterNoReturn pr8 = (String name, int age) -> {
			System.out.println(name + "," + age);
		};
		pr8.call("홍길동", 20);
		
		MultiParamaterNoReturn pr9 = (name, age) -> {
			System.out.println(name + "," +  age);
		};
		pr9.call("아무개", 20);
		
//		// 매개변수가 딱 하나일 때만 매개변수의 괄호를 생략할 수 있다.
//		MultiParamaterNoReturn pr10 = name, age -> {
//			System.out.println(name + "," +  age);
//		};
//		pr10.call("아무개", 20);
		
		NoParameterReturn pr11 = () -> {
			return 10;
		};
		System.out.println(pr11.call());
		
		NoParameterReturn pr12 = () -> 10;
		System.out.println(pr12.call());
		
		ParameterReturn pr13 = (int a, int b) -> {
			return a + b;
		};
		System.out.println(pr13.call(10, 20));
		
		ParameterReturn pr14 = (a, b) -> a + b;
		System.out.println(pr14.call(30, 40));
		
		// 배열과 컬렉션을 정렬: sort();
		ArrayList<Integer> nums = new ArrayList<>();
		Random rnd = new Random();
		
		for (int i = 0; i < 10; i ++) {
			nums.add(rnd.nextInt(100));
		}
		
		System.out.println(nums);
		
		nums.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1; // 내림차순
			};
		});
		
		nums.sort((o1, o2) -> o2 - o1);
		
		System.out.println(nums);
		
		
		
	}// main
	
}// class

@FunctionalInterface // functional interface임을 선언하는 주석(annotation)
interface MyInterface {
	void test();
}

class MyClass implements MyInterface {

	@Override
	public void test() {
		System.out.println("MyClass Class에서 구현한 Method");
	}
	
}

interface TestInterface {
	void aaa();
	void bbb();
}

interface NoParameterNoReturn {
	void call();
}

interface ParameterNoReturn {
	void call(int n);
}

interface MultiParamaterNoReturn {
	void call(String name, int age);
}

interface NoParameterReturn {
	int call();
}

interface ParameterReturn {
	int call(int a, int b);
}