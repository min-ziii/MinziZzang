package com.test.question;

import java.util.Random;

public class Q096 {
	public static void main(String[] args) {
		// Box 클래스와 Macaron 클래스를 설계하시오.
//		조건..
//		Box 객체의 정보
//		1Box에는 10개의 마카롱을 담을 수 있다.(멤버 변수 = Macaron 배열)
//		Box 객체의 사용
//		Box 객체를 생성시 Box에 마카롱 객체를 10개 담는다.(무작위)
//		품질 검사에 통과하지 못하는 마카롱을 구분한다.
//		Macaron 객체의 정보
//		생산 크기(5cm ~ 15cm) → 판매 유효 크기(8cm ~ 14cm)
//		생산 색상(red, blue, yellow, white, pink, purple, green, black) → 판매 유효 색상(black을 제외한 모든 색상)
//		생산 샌드 두께(1mm ~ 20mm) → 판매 유효 두께(3mm ~ 18mm)
		
		Box box1 = new Box();

		box1.cook();
		box1.check();
		box1.list();
	}
}

class Box {
	   
	private Macaron[] list = new Macaron[10];
	Random rand = new Random();
	String[] result = new String[list.length];
	
	public void cook() {
		String[] colour = {"red", "blue", "yellow", "white", "pink", "purple", "green", "black"};
		
	    for (int i = 0; i < list.length; i++) {
		    Macaron m = new Macaron();
		    
		    m.setSize(rand.nextInt(11) + 5); // 생산 크기(5cm ~ 15cm)
		    m.setColor(colour[rand.nextInt(colour.length)]); // 생산 색상(red, blue, yellow, white, pink, purple, green, black)
		    m.setThickness(rand.nextInt(20) + 1); // 생산 샌드 두께(1mm ~ 20mm)
		    
		    this.list[i] = m;
	    }
	   System.out.printf("마카롱을 %d개 만들었습니다.\n", this.list.length);
	}
	
	public void check() {
		int checkYes = 0;
		int checkNo = 0;
		
		for (int i = 0; i < list.length; i++) {
			if (8 <= list[i].getSize() && list[i].getSize() <= 14) { // 판매 유효 크기(8cm ~ 14cm)
				if (3 <= list[i].getThickness() && list[i].getThickness() <= 18) { // 판매 유효 두께(3mm ~ 18mm)
					if (list[i].getColor() != "black") { // 판매 유효 색상(black을 제외한 모든 색상)
						checkYes++;
						result[i] = "합격";
					} else {
						checkNo++;
						result[i] = "불합격";
					}
				} else {
					checkNo++;
					result[i] = "불합격";
				}
			} else {
				checkNo++;
				result[i] = "불합격";
			}
		}
		
		System.out.printf("\n[박스 체크 결과]\n"
						+ "QC 합격 개수 : %d개\n"
						+ "QC 불합격 개수 : %d개\n",checkYes, checkNo);
	}
	
	public void list() {
		System.out.println("\n[마카롱 목록]");
		for (int i = 0; i < list.length; i++) {
			System.out.printf("%d번 마카롱 : %dcm(%s, %dmm) : %s\n",
							  i+1, list[i].getSize(), list[i].getColor(), list[i].getThickness(), result[i]);
		}
	}
}

class Macaron {
	private int size;
	private String color;
	private int thickness;
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getThickness() {
		return thickness;
	}
	public void setThickness(int thickness) {
		this.thickness = thickness;
	}
}