package com.test.java.obj.inheritance;

public class Ex39_Overriding {
	
	public static void main(String[] args) {
		
		//Ex39_Overriding.java
		/*
			
			메서드 오버로딩, Method Overloading
			- 메서드 이름 동일 X N 생성
			
			메서드 오버라이딩, Method Overriding
			- 메서드 재정의 > 상속받은 메서드를 수정하는 기술
			- 클래스 상속에서 발생						
			
		*/
		
		
		OverrdingParent hong = new OverrdingParent();
		hong.name = "홍길동";
		hong.hello();
		
		OverrdingChild jhong = new OverrdingChild();
		jhong.name = "홍철수";
		jhong.hello();
		//jhong.hi();
		
		
	}//main

}//class


//업무 > 동네 사람
class OverrdingParent {
	
	public String name;
	
	public void hello() {
		System.out.printf("안녕하세요. 좋은 아침입니다. 저는 %s입니다.\n"
							, name);
	}
	
}

class OverrdingChild extends OverrdingParent {
	
	//일종의 덮어쓰기 > Overriding
	public void hello() {
		System.out.printf("하이~ 난 %s야!!\n", this.name);
	}
	
//	public void hi() {
//		
//		System.out.printf("하이~ 난 %s야!!\n", this.name);
//		
//	}
	
}
























