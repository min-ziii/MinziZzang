package com.test.java.collection;

import java.util.Objects;

class Member {
	private String name;
	private int age;
	
	public Member(String name, int age) {
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
		// TODO Auto-generated method stub
		return String.format("%s(%d살)", this.name, this.age);
	}

	@Override
	public int hashCode() {
		
//		System.out.println("홍길동20".hashCode());
//		System.out.println("아무개25".hashCode());
//		System.out.println("홍길동20".hashCode()); // 글자가 같으면 해시코드가 똑같이 나온다.
		return (this.name + this.age).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		
		// 이름과 나이가 동일하면 같은 객체로 취급하겠다는 code
		return this.hashCode() == obj.hashCode();
	}
	
	
	
	
	
}
