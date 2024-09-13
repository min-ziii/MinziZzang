package com.test.java.obj;

public class Book {
	
	public String title;
	
	//private int price; //왜?
	//public int price;
	private int price;
	
	private String publisher;
	private String author;
	private int discount;
	private int pages;
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	//aaa() > setPrice() > Setter 
	//- set키워드 + 멤버변수명 > 캐멀 표기법
	public void setPrice(int price) {
		
		this.price = price;
		
		//this > 객체 접근 연산자
		//this.title = ""; //O
		//title = ""; 	 //X
		
	}
	
	//bbb() > getPrice() > Getter
	//- get키워드 + 멤버변수명 > 캐멀
	public int getPrice() {
		return this.price;
	}
	
	public void aaa(int a) {
		
		//개입 > 통제 가능
		if (a >= 0 && a <= 100000) {
			price = a;
		}
		
	}
	
	public int bbb() {
		return price;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	
	

}











