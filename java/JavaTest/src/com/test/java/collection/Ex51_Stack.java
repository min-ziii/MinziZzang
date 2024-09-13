package com.test.java.collection;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Ex51_Stack {
	public static void main(String[] args) {
		
		/*
		   List 계열
		   - ArrayList
		   - Stack
		   - Queue
		   
		   Stack
		   - 후입선출
		   - LIFO, Last Input First Output
		   - 저장소에 나중에 들어간 요소가 먼저 나온다.
		   ex) 메모리 구조(Stack)
		   ex) 브라우저 페이지 되돌리기(Ctrl+z), 다시하기
		   
		   Queue
		   - 선입선출
		   - FIFO, First Input First Output
		   - 저장소에 먼저 들어간 요소가 먼저 나온다.
		   ex) 출서기
		   ex) 주문 리스트
		   ex) 순서가 있는 목록
		   
		   
		 */
//		 m1();
		 m2();
	}

	private static void m2() {
		
		//Queue
		Queue<String> queue = new LinkedList();// Queue는 Stack과 달리 Interface이기 때문에 new Queue같은 객체 구현을 바로 할 수 없음.
		
		// 1. 요소 추가하기
		// -boolean add(T value)
		queue.add("빨강"); // 다른 언어는 enqueue() 라는 method를 쓰는 듯.
		queue.add("노랑");
		queue.add("파랑");
		
		// 2. 요소 개수
		System.out.println(queue);
		
		// 3. 요소 읽기
//		System.out.println(queue.poll()); // 다른 언어의 dequeue()
//		System.out.println(queue.size());
//		
//		System.out.println(queue.poll());
//		System.out.println(queue.size());
//		
//		System.out.println(queue.poll());
//		System.out.println(queue.size());
//		
//		System.out.println(queue.poll()); // 더이상 읽을 게 없어도 Stack처럼 오류가 나지 않고 null을 반환한다.
//		// 어차피 전자든 후자든 예외처리는 해야 한다.
		
//		while(queue.size() > 0) {
//			System.out.println(queue.poll());
//		}
		
		System.out.println(queue.peek()); // 읽기 only
		System.out.println(queue.size());
		
	}

	private static void m1() {
		
		//Stack
		Stack<String> stack = new Stack<String>(); // Class이기 때문에 객체 구현 가능.
		
		// 1. 요소 추가
		// - T push(T value)
		
		stack.push("빨강");
		stack.push("노랑");
		stack.push("파랑");
		
		// 2. 요소 개수
		System.out.println(stack.size());
		
		// 3. 요소 읽기
		// -T pop()
//		System.out.println(stack.pop()); // 읽기 + 삭제
//		System.out.println(stack.size());
//
//		System.out.println(stack.pop());
//		System.out.println(stack.size());
//		
//		System.out.println(stack.pop());
//		System.out.println(stack.size());
		
//		for (int i = 0; i < stack.size(); i++ ) { // 제대로 출력되지 않음. stack.size()가 실시간으로 변경되기 때문
//			System.out.println(stack.pop());
//		}
		
//		while (stack.size() > 0) {					// 주로 사용하는 방식. index가 아니라 요소를 기준으로 돌림.
//			System.out.println(stack.pop());
//		}
		
//		while (!stack.isEmpty()) {
//			System.out.println(stack.pop());
//		}
		
		// 4. 요소 읽기2
		// - T peek()
		System.out.println(stack.peek()); // 읽기 only
		System.out.println(stack.size());
		System.out.println(stack.peek());
		System.out.println(stack.size());
		System.out.println(stack.peek());
		System.out.println(stack.size());
		
	}
}
