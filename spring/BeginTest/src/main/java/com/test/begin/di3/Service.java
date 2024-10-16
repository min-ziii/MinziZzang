package com.test.begin.di3;

public class Service {

	//의존 주입
	//1. 의존 객체를 저장할 멤버 변수 생성
	private Hong hong;
	
	//2. 의존 주입 도구 생성
	//- 생성자 or Setter
//	public Service(Hong hong) {
//		this.hong = hong;
//	}
	
	public void setHong(Hong hong) {
		this.hong = hong;
	}
	
	public void work() {
		
		//자신의 업무 > 일부가 의존 객체를 의존
		//3. 의존 객체 사용
		hong.coding();
		
		
		
	}
	
}
