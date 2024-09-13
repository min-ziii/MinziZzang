package com.test.java.obj.inheritance;

public class Ex47_Generic {
	public static void main(String[] args) {
		// Ex47_Generic.java
		/*
			Generic(제네릭)
			- 제네릭 클래스
		
		
		*/
		
		// 요구사항) 클래스를 설계하라.
		// 멤버 변수는 int 1개
		// 멤버 변수를 중심으로 여러가지 행동을 하는 Method를 선언
		
		// 추가사항1) String을 중심으로 여러가지 행동을 하는 Method를 선언
		// 추가사항2) boolean을 중심으로 여러가지 행동을 하는 Method를 선언
		// 추가사항3) double을...
		// 추가사항4) char...
		// 추가사항5) float...
		// 추가사항6) Student를 중심으로...
		// 추가사항x) ...
		// 어떻게 해야 효율적으로 코드를 짤 수 있을까? -> Object형으로 짜보자.
		// ...집어넣을 때는 좋은데 뺄 때 뭘로 뺄건지 형변환을 명시해야 했음.
		
		// 1. int형 클래스
		WrapperInt n1 = new WrapperInt(10);
		System.out.println(n1);
		System.out.println(n1.getData());
		System.out.println(n1.getData() * 2);
		System.out.println();
		
		// 2. String형 클래스
		WrapperString s1 = new WrapperString("홍길동");
		System.out.println(s1);
		System.out.println(s1.getData());
		System.out.println(s1.getData().length());
		System.out.println();
		
		// 3. Boolean형 클래스
		WrapperBoolean b1 = new WrapperBoolean(true);
		System.out.println(b1);
		System.out.println(b1.getData());
		System.out.println(b1.getData() ? "합격" : "불합격");
		System.out.println();
		
		// 4-1. Object형 클래스 하나로 int
		WrapperObject n2 = new WrapperObject(20);
		System.out.println(n2);
		System.out.println(n2.getData());
//		System.out.println(n2.getData() * 2); // 피연산자가 Object형이라 형변환 unboxing 해야함.
		System.out.println((int)n2.getData() * 2);
		System.out.println();
		
		// 4-2. Object형 클래스 하나로 String
		WrapperObject s2 = new WrapperObject("대한민국");
		System.out.println(s2);
		System.out.println(s2.getData());
		System.out.println(((String)s2.getData()).length());
		System.out.println();
		
		// 4-3. Object형 클래스 하나로 Boolean
		WrapperObject b2 = new WrapperObject(true);
		System.out.println(b2);
		System.out.println(b2.getData());
		System.out.println((boolean)b2.getData() ? "합격" : "불합격");
		System.out.println();
		
		// 5-1. Generic 클래스 하나로 int
		Wrapper<Integer> n3 = new Wrapper<Integer>(30);
		System.out.println(n3);
		System.out.println(n3.getData());
		System.out.println(n3.getData() * 2); // Object와 다르게 unboxing할 필요가 없다.
		System.out.println();
		
		// 5-2. Generic 클래스 하나로 String
		Wrapper<String> s3 = new Wrapper<String>("독도");
		System.out.println(s3);
		System.out.println(s3.getData());
		System.out.println(s3.getData().length());
		System.out.println();
		
		// 5-3. Generic 클래스 하나로 Boolean
		Wrapper<Boolean> b3 = new Wrapper<Boolean>(true);
		System.out.println(b3);
		System.out.println(b3.getData());
		System.out.println(b3.getData()? "참" : "거짓");
		System.out.println();
		
	} // main
} // class

class WrapperInt {
	
	private int data; // 1. 멤버 변수 int를 만든다.
	
	public WrapperInt(int data) { // 생성자 (Constructor)
		this.data = data;
	}
	
	public int getData() { // getter
		return data;
	}
	
	public void setData(int data) { // setter
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "[data=" + data + "]";
	}
}

class WrapperString {
	
	private String data; // 추가1. 멤버 변수 String을 만든다.
	
	public WrapperString(String data) { // 생성자 (Constructor)
		this.data = data;
	}
	
	public String getData() { // getter
		return data;
	}
	
	public void setData(String data) { // setter
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "[data=" + data + "]";
	}
}

class WrapperBoolean {
	
	private boolean data;
	
	public WrapperBoolean(boolean data) { // 생성자 (Constructor)
		this.data = data;
	}
	
	public boolean getData() { // getter
		return data;
	}
	
	public void setData(boolean data) { // setter
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "[data=" + data + "]";
	}
}

class WrapperObject {
	
	private Object data;
	
	public WrapperObject(Object data) { // 생성자 (Constructor)
		this.data = data;
	}
	
	public Object getData() { // getter
		return data;
	}
	
	public void setData(Object data) { // setter
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "[data=" + data + "]";
	}
}

// 제네릭 클래스. 클래스는 하나인데 T에 뭘 넣냐에 따라 뭐든 될 수 있음
class Wrapper<T> {
	private T data;
	
	public Wrapper(T data) {
		this.setData(data);
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "[data=" + data + "]"; // 양쪽이 문자열이니까 data쪽도 자동적으로 문자열로 치환됨
	}
}