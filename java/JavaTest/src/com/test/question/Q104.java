package com.test.question;

public class Q104 {
	public static void main(String[] args) {
		
		// 배열 생성
		MyArrayList list = new MyArrayList();
		System.out.println(list);
		
		// 추가
		list.add("홍길동");
		list.add("아무개");
		list.add("하하하");
		list.add("호호호");
		System.out.println(list);
		
		// 읽기
		System.out.println(list.get(0));
		System.out.println(list.get(1));
		System.out.println(list.get(2));
		
		// 개수 (배열의 길이가 아니라 요소의 개수를 말하는 것)
		System.out.println(list.size());
		
	}
}
