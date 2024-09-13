package com.test.question;

public class Q092 {
	public static void main(String[] args) {
		
		// book Class를 설계하시오.
		
		Book b1 = new Book();

		b1.setTitle("자바의 정석");
		b1.setPrice(45000);
		b1.setAuthor("남궁성");
		b1.setPublisher("도우출판");
		b1.setIsbn("8994492038");
		b1.setPage(1022);

		System.out.println(b1.info());
		
	}
}

class Book {
	   private String title;
	   private int price;
	   private String author;
	   private String publisher;
	   private String pubYear = "2019";			// 발행 년도 -> 읽기 전용
	   private String isbn;
	   private int page;

	   public String info() { 					// 객체의 모든 정보를 문자열로 반환
		   return String.format("제목: %s\n"
				   			  + "가격: %,d원\n"
				   			  + "저자: %s\n"
				   			  + "출판사: %s\n"
				   			  + "발행년도: %s년\n"
				   			  + "ISBN: %s\n"
				   			  + "페이지: %,d장\n", this.title, this.price, this.author, this.publisher, this.pubYear, this.isbn, this.page);
	   }

	public void setPage(int page) {				// 페이지 setter, 1~무제한 유효성 검사
		if (page < 1) {
			return;
		}
		this.page = page;
	}

	
	public void setIsbn(String isbn) {			// ISBN setter
		this.isbn = isbn;
		
	}
	
	public String getIsbn(String isbn) {		// ISBN getter
		return isbn;
	}
	

	public void setPublisher(String publisher) {// 출판사 setter(쓰기) 전용
		this.publisher = publisher;
		
	}

	
	public void setAuthor(String author) {		// 저자 setter, 제한 없음
		this.author = author;
	}
	
	public String getAuthor(String author) {	// 저자 getter
		return author;
	}

	
	public void setPrice(int price) {			// 가격 setter, 0원~백만원까지 유효성 검사
		if (price < 0 || price > 1000000) {
			return;
		}
		this.price = price;
	}
	
	public int getPrice(int price) {			// 가격 getter
		return price;
	}

	
	public void setTitle(String title) {		// 제목 setter, 최대 50자 이내로 유효성 검사
		if (title.length() > 50) {
			return;
		}
		this.title = title;
	}
	
	public String getTitle(String title) {		// 제목 getter
		return title;
	}
	
}
