package com.test.begin.di1;

public class Main {

	public static void main(String[] args) {
		
		//목적] Hong에게 일을 시키자!
		//Main > Hong (X)
		//Main > Service > Hong (O)
		
		//Main > (위임) > Service > (위임) > Hong
		//Main > (의존) > Service > (의존) > Hong
		//Service는 Main의 의존 객체이다.
		//***여태까지 방식 > 필요할 때마다 직접 의존 객체를 생성해서 사용하는 방식
		Service service = new Service(); //필요 > 의존 객체 생성
		service.work();
		
	}
	
}
