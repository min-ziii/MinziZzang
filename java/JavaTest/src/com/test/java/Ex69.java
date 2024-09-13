package com.test.java;

public class Ex69 {
	public static void main(String[] args) {
		
		// 의존(dependency)	 관계
		Child c1 = new Child();
	}
}

class Parent {
	private int a;
	public void aaa() {
		
	}
}

class Child extends Parent {
	private int b;
	public void bbb() {
		
	}
}

interface Mouse {
	void click();
	boolean dblclick();
}

abstract class AbstractMouse implements Mouse{
	private int a;
	public void aaa() {
		
	}
	public abstract void drag();
	public abstract void drop();
}

class M305 implements Mouse {

	@Override
	public void click() {
		
	}

	@Override
	public boolean dblclick() {
		return false;
	}
	
}

class M405 extends AbstractMouse {

	@Override
	public void drag() {
		
	}

	@Override
	public void drop() {
		
	}

	@Override
	public void click() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean dblclick() {
		// TODO Auto-generated method stub
		return false;
	}
	
}

/*
	일반 클래스 + 추상 클래스 + 인터페이스의 상속 관계
	
		 자식			부모
		
	- 일반 클래스 -> 일반 클래스 : 가능
	- 일반 클래스 -> 인터페이스	 : 가능 (Override)
	- 일반 클래스 -> 추상 클래스 : 가능 (Override)
	- 인터페이스  -> 일반 클래스 : 불가능 (인터페이스는 일반클래스로부터 상속받는 요소들의 구현부를 가질 수 없다.)
	- 추상 클래스 -> 일반 클래스 : 가?능 (가능...은 하지만 권장하지 않음)
	- 인터페이스  -> 인터페이스  : 가능 (extends로 가능. 구현을 못하니까 구현을 하는 implements 키워드는 쓸 필요가 없음.)
	- 추상 클래스 -> 추상 클래스 : 가능 (extends로 가능. 부모의 추상 메서드를 그대로 물려받을 수도 있고, Override해서 구현 후 넘겨줄 수도 있음.)
	- 추상 클래스 -> 인터페이스  : 가능
	- 인터페이스  -> 추상 클래스 : 불가능 (구현부 부분을 상속받을 수 없으니까.)
	
	- 일반 클래스 -> 일반 클래스 -> 일반 클래스 -> 추상 클래스 -> 추상 클래스 -> 추상 클래스 -> 인터페이스 -> 인터페이스 -> 인터페이스

*/


/*
class AAA {
	public int a;
	void aaa() {
	}
}

//interface BBB extends AAA {
//	
//}

abstract class CCC extends AAA {
	
}

interface DDD {
	void ddd();
}
interface EEE extends DDD{
	void eee();
}

abstract class FFF {
	private int a;
	public abstract void aaa();
}
abstract class GGG extends FFF {
	private int b;
	public abstract void bbb();
}

interface HHH {
	void aaa();
}
abstract class III implements HHH{
	public abstract void bbb();
	@Override
	public void aaa () {
		
	}
}

class JJJ extends III {
	@Override
	public void bbb() {
		
	}	
}

//abstract class KKK {
//	public int a;
//	public abstract void aaa();
//}
//interface LLL extends KKK {
//	
//}
*/