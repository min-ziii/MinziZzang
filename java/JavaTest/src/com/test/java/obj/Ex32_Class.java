package com.test.java.obj;

public class Ex32_Class {
	
	public static void main(String[] args) {
		
		//Ex32_Class.java
		
		
		//이순신(대리)
		Employee lee = new Employee();
		
		lee.setName("이순신");
		lee.setDepartment("영업부");
		//lee.setBoss(??);
		
		
		//홍길동(직원)
		Employee hong = new Employee();
		
		hong.setName("홍길동");
		hong.setDepartment("영업부");
		hong.setBoss(lee);
				
		System.out.println(hong.getName());
		System.out.println(hong.getDepartment());
		System.out.println(hong.getBoss()); //Employee
		System.out.println(hong.getBoss().getName());
		System.out.println(hong.getBoss().getDepartment());
	
		
		//Ctrl + Shift + S
		
//		Child c1 = new Child();
//		
//		c1.setName("홍철수");
//		c1.setAge(7);		
//		
//		
//		Parent p1 = new Parent();
//		
//		p1.setName("홍길동");
//		p1.setAge(35);
//		p1.setChild(c1);
		
		
		
		Parent p2 = new Parent();
		
		p2.setName("홍길동");
		p2.setAge(35);
		
		Parent p3 = new Parent();
		
		p3.setName("김영희");
		p3.setAge(33);
		
		Child c2 = new Child();
		
		c2.setName("홍철수");
		c2.setAge(5);
		
		c2.setFather(p2);
		c2.setMother(p3);
		
		
		
	}

}















