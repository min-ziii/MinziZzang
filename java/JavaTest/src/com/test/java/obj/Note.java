package com.test.java.obj;

public class Note {
	
	private String color;
	private int pages;
	private int price = 1000;
	
	//color > Setter > 쓰기 전용 프로퍼티
	public void setColor(String color) {
		this.color = color;
	}
	
	//color > Getter > X
	
	//Getter만 구현 > 읽기 전용 프로퍼티
	public int getPrice() {
		return this.price;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}
	
	//n1.getType()
	
	
	//가상 프로퍼티
	//계산된 프로퍼티
	public String getType() {
		
		if (this.pages <= 10) {
			return "얇은 노트";
		} else if (this.pages <= 50) {
			return "보통 노트";
		} else {
			return "두꺼운 노트";
		}
		
	}

}




























