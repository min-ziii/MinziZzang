package com.test.java.collection;

import java.util.ArrayList;
import java.util.LinkedList;

public class Ex57_LinkedList {
	public static void main(String[] args) {
		// Ex57_LinkedList.java
		
		/*
		   	Collection이라는 부모 Interface가 있고, 그걸 상속받은 List라는 Interface가 있다.
		   	List를 상속받은 ArrayList라는 Class와 LinkedList Interface가 있다.
		   	-> ArrayList에서 보던 method들이 LinkedList에도 많을 것.
		   	
		   	ArrayList VS LinkedList
		   	- Method 구성이 유사하다. -> 사용법도 유사하다.
		   	- 내부 구조가 다르다. -> 사용 용도가 다르다.
		   	
		   	LinkedList의 종류
		   	1. LinkedList
		   	2. Double LinkedList
		   	3. Double Circular LinkedList -> 순환 구조. java에서 구현한 LinkedList
		   	
		   	ArrayList(배열)
		   	- 순차적인 추가/삭제는 매우 빠름
		   	- 읽기도 매우 빠름
		   	- 중간에 추가/삭제는 매우 느림 -> shift 때문.
		   	
		   	LinkedList
		   	- 순차적인 추가/삭제는 괜찮은 편
		   	- 중간에 추가/삭제는 매우 빠름
		   	
		   	개발자들은 보통 ArrayList를 쓰고, 특정 부분에만 LinkedList로 교체해서 사용하는 편
		   	
		   	[1. 순차적으로 데이터 추가하기, Append]
			ArrayList 작업 시간: 198ms
			LinkedList 작업 시간: 2,259ms

			[2. 중간에 데이터 추가하기, Insert]
			ArrayList 작업 시간: 9,716ms
			LinkedList 작업 시간: 1ms

			[3. 데이터 삭제 하기, Insert]
			ArrayList 작업 시간: 6,559ms
			LinkedList 작업 시간: 0ms

			[4. 순차적으로 데이터 삭제하기, Delete]
			ArrayList 작업 시간: 18ms
			LinkedList 작업 시간: 78ms


		 */
		
//		m1();
		m2();
		
	}//main

	private static void m2() {
		// 입출력 속도비교
		ArrayList<Integer> list1 = new ArrayList<>();
		LinkedList<Integer> list2 = new LinkedList<>();
		
		long begin = 0, end = 0;
		
		// 1. 순차적으로 데이터 추가 (append)
		System.out.println("[1. 순차적으로 데이터 추가하기, Append]");
		
		begin = System.currentTimeMillis();
				
		for (int i =0; i <10000000; i++) {
			list1.add(i);
		}
		
		end = System.currentTimeMillis();
		System.out.printf("ArrayList 작업 시간: %,dms\n", end - begin);
		
		begin = System.currentTimeMillis();
		
		for (int i = 0; i < 10000000; i++) {
			list2.add(0, i);
		}
		end = System.currentTimeMillis();
		
		System.out.printf("LinkedList 작업 시간: %,dms\n", end - begin);
		
		System.out.println();
		
		
		// 2. 중간에 데이터 추가 (Insert)
		System.out.println("[2. 중간에 데이터 추가하기, Insert]");
				
		begin = System.currentTimeMillis();
				
		for (int i = 0; i < 1000; i++) {
			list1.add(0, i);
		}
				
		end = System.currentTimeMillis();
				
		System.out.printf("ArrayList 작업 시간: %,dms\n", end - begin);
				
		begin = System.currentTimeMillis();
				
		for (int i = 0; i < 1000; i++) {
			list2.add(0, i);
		}
		end = System.currentTimeMillis();
				
		System.out.printf("LinkedList 작업 시간: %,dms\n", end - begin);
		
		System.out.println();
		
		
		// 3. 중간에 데이터 추가 (Insert)
		System.out.println("[3. 데이터 삭제 하기, Insert]");
				
		begin = System.currentTimeMillis();
				
		for (int i = 0; i < 1000; i++) {
			list1.remove(0);
		}
				
		end = System.currentTimeMillis();
				
		System.out.printf("ArrayList 작업 시간: %,dms\n", end - begin);
				
		begin = System.currentTimeMillis();
				
		for (int i = 0; i < 1000; i++) {
			list2.remove(0);
		}
		end = System.currentTimeMillis();
				
		System.out.printf("LinkedList 작업 시간: %,dms\n", end - begin);
				
		System.out.println();
		
		
		// 4. 순차적으로 데이터 삭제하기 (Delete)
		System.out.println("[4. 순차적으로 데이터 삭제하기, Delete]");
				
		begin = System.currentTimeMillis();
				
		for (int i = list1.size()-1; i >= 0; i--) {
			list1.remove(i);
		}
				
		end = System.currentTimeMillis();
				
		System.out.printf("ArrayList 작업 시간: %,dms\n", end - begin);
				
		begin = System.currentTimeMillis();
				
		for (int i = list2.size()-1; i >= 0; i--) {
			list2.remove(i);
		}
		end = System.currentTimeMillis();
				
		System.out.printf("LinkedList 작업 시간: %,dms\n", end - begin);
				
		System.out.println();
		
		
	}

	private static void m1() {
		
		// 사용법
		ArrayList<Integer> list1 = new ArrayList<>();
		LinkedList<Integer> list2 = new LinkedList<>();
		
		list1.add(100);
		list1.add(200);
		list1.add(300);
		
		list2.add(100);
		list2.add(200);
		list2.add(300);
		
		System.out.println(list1.size());
		System.out.println(list2.size());
		
	}
}//class
