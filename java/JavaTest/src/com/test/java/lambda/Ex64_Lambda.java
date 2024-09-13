package com.test.java.lambda;

import java.util.Calendar;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntToDoubleFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Ex64_Lambda {
	public static void main(String[] args) {
		//Ex64_Lambda.java
		/*
		   	람다식은 익명 객체의 추상 메서드이다. -> Interface 참조 변수에 저장한다.
		   	- 람다식을 사용하려면 인터페이스가 필요하다!
		   	- ...인터페이스 만들기도 귀찮다.
		   	- 그것도 자바가 해줄 수 있대. wow.
		   	
		   	함수형 인터페이스 (Functional Interface)
		   	- 람다식을 저장하기 위한 평범한 Interface
		   	- 추상 Method를 하나만 가진다.
		   	
		   	1. 표준 API 함수형 인터페이스 -> JDK에서 제공
		   	2. 사용자 정의 함수형 인터페이스 -> 개발자가 직접 선언(Ex63)
		   	
		   	표준 API 함수형 인터페이스
		   	1. Consumer
		   		- Consumer<T>
		   		- BiConsumer<T,U>
		   		- ...
		   		- 매개변수 O, 반환값 X인 추상 메서드를 제공한다.
		   	2. Supplier
		   		- Supplier<T>
		   		- ...
		   		- 매개변수 X, 반환값 O인 추상 메서드를 제공한다.
		   	3. Function
		   		- Function<T,U>
		   		- BiFunction<T,U,R>
		   		- ...
		   		- 매개변수 O, 반환값 O인 추상 메서드를 제공한다.
		   	4. Operator
		   		- Function의 하위 셋
		   		- UnaryOperator<T>
		   		- BinaryOperator<T>
		   		- ...
		   		- 매개변수 O, 반환값 O인 추상 메서드를 제공한다.
		   	5. Predicate
		   		- Function의 하위 셋
		   		- Predicate<T>
		   		- BiPredicate<T,U>
		   		- ...
		   		- 매개변수 O, 반환값 O인 추상 메서드를 제공한다.
		 */
		
		//Consumer
		MyConsumer m1 = num -> System.out.println(num);
		m1.test(100);
		m1.test(200);
		
		//java.util.function.Consumer
		Consumer<Integer> c1 = num -> System.out.println(num);
		c1.accept(100);
		c1.accept(200);
		
		Consumer<Integer> c2 = num -> System.out.println(num * num);
		c2.accept(5);
		
		Consumer<String> c3 = str -> System.out.println(str.length());
		c3.accept("홍길동");
		
		Consumer<Integer> c4 = count -> {
			for (int i = 0; i < count; i++) {
				System.out.println(i);
			}
			System.out.println();
		};
		c4.accept(10);
		
		Consumer<Member> c5 = m -> {
			System.out.println(m.getName());
			System.out.println(m.getAge());
		};
		c5.accept(new Member("홍길동", 20));
		
		BiConsumer<String, Integer> bc1 = (name, age) -> {
			System.out.println(name + "," + age);
		};
		bc1.accept("아무개", 22);
		
		BiConsumer<Integer, Integer> bc2 = (a, b) -> System.out.println(a + b);
		bc2.accept(100, 200);
		
		// Consumer<Integer> ic1이랑 같음
		IntConsumer ic1 = num -> System.out.println(num);
		ic1.accept(100);
		
//		m1();
//		m2();
//		m3();
//		m4();
		m5();
		
		/*
			240715
			람다식 + 함수형 인터페이스
			
			lambda식
			- 익명 객체의 익명 메서드(추상 메서드 구현)를 간단한 표기법으로 선언하는 표현식
			- 고정으로 반복되는 구문을 제거
			- 매개변수 or 구현부를 최소화 표현
			
			함수형 인터페이스
			- 추상 메서드를 1개만 가지는 익명 객체 저장용 인터페이스
			- ★람다식을 저장하는 인터페이스
			
			표준 API 함수형 인터페이스
			- 자바에서 제공하는 람다식을 저장하기 위한 인터페이스
			- 여러 형태의 추상 메서드를 선언한 인터페이스들
			- 람다식 사용을 위해 추가로 인터페이스를 선언하지 않아도 되게끔 비용 절감
			
			
			1. Consumer
			- 반환값 X, 매개변수 O인 추상 메서드
			- void Consumer<T>.accept(인자);
			
			2. Supplier
			- 반환값 O, 매개변수 X인 추상 메서드
			- 반환값 Supplier<T>.get();
			
			3. Function
			- 반환값 O ,매개변수 O인 추상 메서드
			
			4. Operator
			- 반환값 O ,매개변수 O인 추상 메서드
			- Function의 하위셋
			- 
			
			5. Predicate
			- 반환값 O ,매개변수 O인 추상 메서드
			- Function의 하위셋
		*/
	}

	private static void m5() {
		// Predicate
		// - 매개변수를 전달하면 처리 후 반환값을 돌려주는 업무를 구현하는 인터페이스
		// - testXXX() 추상 메서드 제공
		// - 반환값이 반드시 Boolean이어야 한다.
		
		Function<Integer, Boolean> f1 = num -> num > 0;
		System.out.println(f1.apply(10)); // true;
		System.out.println(f1.apply(-10)); // false;
		
		Predicate<Integer> p1 = num -> num > 0;
		System.out.println(p1.test(10)); // true
		System.out.println(p1.test(-10)); // false
		
		Predicate<Integer> p2 = age -> age >= 18;
		System.out.println(p2.test(20)); // true
		
		BiPredicate<String, String> p3 = (s1, s2) -> s1.length() > s2.length();
		System.out.println(p3.test("홍길동님", "홍길동")); // true
		
	}

	private static void m4() {
		// Operator
		// - 매개변수를 전달하면 처리 후 반환값을 돌려주는 업무를 구현하는 인터페이스
		// - applyXXX() 추상 메서드 제공
		// - 추상 메서드의 매개변수와 반환값의 자료형이 동일하다.
		
		BiFunction<Integer, Integer, Integer> f1 = (a, b) -> a + b;
		// 위와 아래는 같다
		BinaryOperator<Integer> o1 = (a, b) -> a + b;
		// ↑ 자료형이 전부 같을 때 씀
		
		System.out.println(f1.apply(10, 20));
		System.out.println(o1.apply(10, 20));
		
		// 변수가 하나면
		UnaryOperator<Integer> o2 = (a) -> a * a;
		System.out.println(o2.apply(5));
		
		
		
	}

	private static void m3() {
		// Fucntion
		// - 매개변수를 전달하면 처리 후 반환값을 돌려주는 업무를 구현하는 인터페이스
		// - applyXXX() 추상 메서드 제공
		
		Function <Integer,Boolean> f1 = num -> num > 0;
		
		System.out.println(f1.apply(10)); // true
		System.out.println(f1.apply(-10)); // false
		
		Function<String,Integer> f2 = str -> str.length();
		
		System.out.println(f2.apply("홍길동")); // 문자열의 길이
		System.out.println(f2.apply("안녕하세요, 홍길동님"));
		
		BiFunction<Integer, Integer, String> f3 // int, int를 받아서 Str 출력
			= (a, b) -> {
				if (a > b) {
					return "크다";
				} else if (a < b) {
					return "작다";
				} else {
					return "같다";
				}
			};
		System.out.println(f3.apply(10, 5)); // a가 b보다 크니까 "크다"가 출력됨
		
		IntToDoubleFunction f4 = num -> num * 1.0;
		System.out.println(f4.applyAsDouble(10)); // 10.0
	}

	private static void m2() {
		
		// Supplier
		
		// 매개변수 없이 반환값을 돌려주는 업무를 구현하는 Interface
		// getXXX() 라는 추상 Method들을 제공한다.
		Supplier<Integer> s1 = () -> { return 100; };
		Supplier<Integer> s2 = () -> 100;
		
		System.out.println(s1.get());
		System.out.println(s2.get());
		
		Supplier<String> s3 = () -> "홍길동";
		System.out.println(s3.get());
		
		Supplier<Integer> s4 = () -> {
			Calendar now = Calendar.getInstance();
			return now.get(Calendar.HOUR_OF_DAY);
		};
		System.out.println(s4.get());
		
		BooleanSupplier s5 = () -> true;
		System.out.println(s5.getAsBoolean());
	}

	private static void m1() {
		
		// Consumer
		
		// 매개변수를 받아서 소비하는 업무를 구현하는 Interface
		// acceptXXX() 라는 추상 Method들을 제공한다.
		MyConsumer m1 = num -> System.out.println(num);
		m1.test(100);
		m1.test(200);
	}
}

class Member {
	private String name;
	private int age;
	
	public Member(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}

	@Override
	public String toString() {
		return this.name + " : " + this.age;
	}
	
	@Override
	public int hashCode() {
		return (this.name + this.age).hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		
		return this.hashCode() == obj.hashCode();
	}
}

// 람다식을 저장하기 위해서 선언한 인터페이스
@FunctionalInterface
interface MyConsumer {
	void test(int num);
}