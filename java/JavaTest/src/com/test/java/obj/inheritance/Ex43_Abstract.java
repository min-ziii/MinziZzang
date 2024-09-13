package com.test.java.obj.inheritance;

public class Ex43_Abstract {
	
	public static void main(String[] args) {
		
		//Ex43_Abstract.java
		/*
		
			Abstract
			- 추상 클래스, Abstract Class
			- 추상 메서드, Abstract Method
		
		*/
		
		G305 m1 = new G305();
		
		m1.color = "black";
		m1.weight = 100;
		m1.buttons = 7;
		
		m1.click();
		m1.charge();
		
		
		M410 m2 = new M410();
		
		m2.weight = 150;
		m2.buttons = 5;
		m2.size = "대형";
		
		m2.click();
		m2.autoScroll();
		
		//ERROR: Cannot instantiate the type Mouse
		// 추상class가 객체를 생성하지 않는 이유는?
		// 추상 method 때문에 (method가 구현부를 못 가져서)
//		Mouse m3 = new mouse();
//		m3.click();
		
	}//main

}//class


//추상 클래스
//- 추상 메서드를 가질 수 없다.
//- 객체 생성을 할 수 없다.
//- 일반 클래스의 부모 역할을 한다. (상속) -> 추상method -> 행동 정의(규약)


//추상 class 선언하기
abstract class Mouse {
	//비슷한 class들 간에 공통되는 멤버들이 있다면 하나로 줄이는 것이 좋다. -> extends를 써서 class 상속
	// weight와 buttons는 G305와 M410에 둘 다 있기 때문에 여기서 선언하여 중복코드를 줄인다.
	public int weight;
	public int buttons;
	
	//추상 method 선언하기 -> 규약
	public abstract void click(); //method 머리는 선언해줄테니 구현은 상속받는 놈들이 알아서 해.
	
}


//마우스 생산
class G305 extends Mouse {
	
	public String color;
//	public int weight;
//	public int buttons;
	
	public void click() {
		System.out.println("클릭합니다.");
	}
	
	public void charge() {
		System.out.println("충전합니다.");
	}
	
}


class M410 extends Mouse {
	
//	public int weight;
//	public int buttons;
	public String size;
	
	public void click() {
		System.out.println("click");
	}
	
	public void autoScroll() {
		System.out.println("Auto Scroll");
	}
	
}

// M410 제작자가 click()을 mousedown()같은 이름으로 바꿔버리면?
// 똑같은 기능인데 이름이 바뀌었으니 사용자가 적응하기 귀찮다.
// 그럼 제작자들끼리 합의해야 하는데 사람 사이에 그러기가 쉽지 않다.
// 그럴 때 추상 클래스를 사용해서 프로그램 단계에서 강제로 합의하게 한다.

// 추상method를 이용해 상속을 구현하는 행위를 부르는 말?
//1. 추상 method를 구현했다.
//2. Override를 했다.




















