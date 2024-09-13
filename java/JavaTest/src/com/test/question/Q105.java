package com.test.question;

import com.test.java.collecton.MyQueue;

public class Q105 {
	public static void main(String[] args) {
		// MyQueue 클래스 구현
		MyQueue queue = new MyQueue();
		
		System.out.println(queue);
		
		// 요소 추가
		queue.add("빨강");
		System.out.println(queue);
		
		queue.add("노랑");
		System.out.println(queue);
		
		queue.add("주황");
		System.out.println(queue);
		
		queue.add("초록");
		System.out.println(queue);
		
		queue.add("파랑");
		System.out.println(queue);
		
		System.out.println("큐의 크기: " + queue.size());
		
		// 요소 읽기(+삭제) = stack의 pop() 같은 기능
		System.out.println(queue.poll());
		System.out.println(queue);
		
		System.out.println(queue.size());
		
		queue.add("남색");
		System.out.println(queue);
		System.out.println(queue.peek());
		
		queue.clear();
		System.out.println(queue);
		
		System.out.println(queue.size());
		
		System.out.println(queue.poll());
		System.out.println(queue.peek());
		
		queue.add("빨강");
		queue.add("노랑");
		queue.add("파랑");
		System.out.println(queue);
		
		queue.trimToSize();
		
		System.out.println(queue);
		
		
		
//		MyQueue queue2 = new MyQueue();
//		
//		for (int i = 0; i < 10; i++) {
//			queue2.add("" + i);
//		}
//		
//		queue.trimToSize();
		
	}
}
