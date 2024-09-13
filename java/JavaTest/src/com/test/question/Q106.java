package com.test.question;

public class Q106 {
	public static void main(String[] args) {
		// MyStack 클래스를 구현하시오.
		
		//배열 생성
		MyStack stack = new MyStack();

		//추가
		stack.push("빨강");
		stack.push("노랑");
		stack.push("파랑");
		stack.push("주황");
		stack.push("검정");

		//읽기
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());

		//개수
		System.out.println(stack.size());

		//확인
		System.out.println(stack.peek());
		System.out.println(stack.peek());
		System.out.println(stack.size());

		//크기 조절
		stack.trimToSize();

		//초기화
		stack.clear();
		System.out.println(stack.size());
	}
}

class MyStack { // Stack의 구동 원리에 대해 생각해본다. index는 data 입출력위치 선정, stack의 크기 판단 등의 기준점이 된다.
	private String[] list = new String[4]; // 5개 값이 들어오지만 크기를 5로 한다면 increaseSpace()가 동작할 타이밍이 안 나오니까 4로 선언.
	private int index = 0;
	private void increaseSpace() {
		
		if (this.index == this.list.length) {
			String[] temp = new String[this.list.length * 2];
			for (int i = 0; i < this.list.length; i++) {
				temp[i] = this.list[i];
			}
			this.list = temp;
		}
	}
	
	void push(String value) { // 값을 순차적으로 추가한다.
		increaseSpace();
		this.list[this.index] = value;
		index++;
	}
	String pop() { // 값을 순차적으로 가져온다.
		String word = this.list[this.index-1];
		this.index--;
		return word;
	}
	int size() { // 요소의 개수를 반환한다.
		return this.index;
	}
	String peek() { // 이번에 가져올 값을 확인한다.
		return this.list[this.index-1];
	}
	void clear() { // 배열의 모든 요소를 삭제한다.
		this.index = 0;
		
	}
	void trimToSize() { // 배열 안의 요소의 개수만큼 배열의 길이를 줄인다.
		String[] temp = new String[this.index];
		for (int i = 0; i < this.index; i++) {
			temp[i] = this.list[i];
		}
		this.list = temp;
	}
}