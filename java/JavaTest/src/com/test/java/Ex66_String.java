package com.test.java;

public class Ex66_String {
	public static void main(String[] args) {
		// Ex66_String.java
		/*
		 	"문자열은 불변(Immutable)이다."
		 */
//		m1();
//		m2();
//		m3();
//		m4();
		m5();
	}

	private static void m5() {
		
		
	}

	private static void m4() {
		
		// StringBuilder 클래스
		String s1 = "홍길동";
		String s2 = new String("홍길동");
		
		// m3같은 조작을 해도 비용이 거의 발생하지 않음.
		StringBuilder s3 = new StringBuilder("홍길동");
		System.out.println(s3);
		System.out.println(s3.length());
		System.out.println(s3.indexOf("홍"));
		
		long begin = 0, end = 0;
		
		// String
		begin = System.currentTimeMillis();
		
		String txt1 = "홍길동";
		
		for (int i = 0; i < 500000; i++) {
			txt1 += ".";
		}
		
		end = System.currentTimeMillis();
		System.out.printf("길이 : %,d자\n시간: %,dms\n", txt1.length(), end - begin); // 75830 밀리초
		
		System.out.println();
		
		// StringBuilder
		begin = System.currentTimeMillis();
		
		StringBuilder txt2 = new StringBuilder("홍길동");
		
		for (int i = 0; i < 500000; i++) {
			txt2.append(".");
		}
		
		end = System.currentTimeMillis();
		System.out.printf("길이 : %,d자\n시간: %,dms\n", txt2.length(), end - begin); // 15 밀리초
		
	}

	private static void m3() {

		// 1. 문자열의 잦은 조작은 하지 않는 것이 좋다.
		String txt = "홍길동";
		
		// for문을 돌려서 "홍길동" 뒤에 "."을 추가하는 코드를 1만 번 반복해서 홍길동.... 으로 만들기?
		// for문이 한 번 돌 때마다 garbage가 계속 생긴다. -> 메모리의 사용 효율이 매우 떨어진다.
		
		// 2. 크기가 큰 문자열의 수정은 하지 않는 것이 좋다.
		// 100만 글자짜리 문자열 뒤에 마침표를 하나 붙이기? -> 메모리의 사용 효율이 매우 떨어진다.
		// 그러나 이 경우는 실제 작업에서 종종 일어날 수 있는 일이기 때문에, 메모리 공간을 덜 낭비할 방법을 만들어냈다. -> m4()로
	}

	private static void m2() {
		String txt1 = "홍길동";
		String txt2 = "홍길동";
		
		// "홍길동" 이 들어간 메모리의 주소값 출력
		System.out.println(txt1.hashCode()); // 54150062
		System.out.println(txt2.hashCode()); // 54150062
		
		// hashCode 값이 왜 같지?
		// -> "홍길동"이 들어간 Heap 영역 속 메모리 공간 하나를 txt1과 txt2가 동시에 참조하고 있기 때문에(메모리 재사용) 메모리 주소값이 같다.
		
		// 값형과 참조형의 차이는?
		// 값형은 Stack 영역에 데이터를 저장한다. 데이터 공간의 메모리 크기는 값이 어떻든 변하지 않는다.
		// 참조형은 Heap 영역 어딘가에 저장된 데이터를 참조하는 주소값을 가져온다. 데이터 공간의 메모리 크기는 할당량에 따라 달라질 수 있다.
		
		txt2 = "홍길동님";
		System.out.println(txt2.hashCode()); // 1678697706
		// txt2만 수정했는데 txt1도 같이 수정되는 일이 일어나서는 안 된다.
		// txt2의 "홍길동"을 향한 참조를 끊고 새 공간에 "홍길동님"을 만든 뒤 그 곳을 참조하게 한다.

		
	}

	private static void m1() {
		String name1 = "홍길동";
		String name2 = "홍길동";
		String name3 = "홍";
		name3 = name3 + "길동";
		
		System.out.println("name1: " + name1);
		System.out.println("name2: " + name2);
		System.out.println("name3: " + name3);
		
		System.out.println();
		
		System.out.println("name1 == name2 : " + (name1 == name2)); // true
		System.out.println("name1 == name3 : " + (name1 == name3)); // false
		
		System.out.println("name1.equals(name2) : " + name1.equals(name2)); // true
		System.out.println("name1.equals(name3) : " + name1.equals(name3)); // true
		
		// "문자열은 불변(Immutable)이다."
		String txt = "홍길동";
		txt += "님"; // txt를 수정? 문자열은 불변인데 어떻게 문자열을 수정해? 안 된다.
		System.out.println(txt);
		
		// String의 구현 방식을 생각해보자.
		// Heap 영역에 "홍길동"이 들어갈 6Byte 길이의 메모리가 잡히고, 그 안에 문자열 "홍길동"이 저장된다.
		// Stack 영역에 참조 변수인 txt(4Byte)를 만들고, Heap 영역 속 홍길동이 저장된 메모리 번지의 주소가 txt와 연결된다.
		// 사용자가 "홍길동님"이라는 문자열을 txt에 넣으려고 시도한다.
		// 메모리 어딘가에 "님"이 2Byte로 잡힌다.
		// 프로그램은 사용 가능한 8Byte길이의 연속적 메모리 공간 어딘가를 찾고, 문자열 "홍길동님"을 채워넣는다.
		// "홍길동"이 저장된 기존 메모리 공간과 txt 사이의 주소 연결을 해제하고, "홍길동님"이 저장된 새로운 매모리 공간의 주소와 txt를 서로 연결한다.
		// 메모리 "홍길동"은 이 시점부터 garbage가 된다.
	}
}

class Item {
	private int num;
	private String type;
	
//	// String.format
//	@Override
//	public String toString() {
//		return String.format("Item [num=%s, type=%s]", num, type);
//	}
	
	
//	// StringBuilder/StringBuffer - Chained calls
//	@Override
//	public String toString() {
//		StringBuilder builder = new StringBuilder();
//		builder.append("Item [num=").append(num).append(", type=").append(type).append("]");
//		return builder.toString();
//	}
	
	// StringBuilder/StringBuffer
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Item [num=");
		builder.append(num);
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}
	
//	// String Concatenation
//	@Override
//	public String toString() {
//		return "Item [num=" + num + ", type=" + type + "]";
//	}
	
	
	
}