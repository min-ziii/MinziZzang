package com.test.question;

public class Q093 {
	public static void main(String[] args) {
		// Note Class를 설계하라.
		
		Note note = new Note();

		note.setSize("B4");
		note.setColor("노란색");
		note.setPage(25);
		note.setOwner("홍길동");

		System.out.println(note.info());


		Note note2 = new Note();

		note2.setSize("A4");
		note2.setColor("검정색");
		note2.setPage(100);

		System.out.println(note2.info());
	}
}

class Note {
	private String size;
	private String color;
	private int page;
	private String owner;
	private int price;
	
	public void setSize(String size) {			// 크기 setter
		switch(size) {
		case "A3":
			this.size = "A3";
			break;
		case "A4":
			this.size = "A4";
			break;
		case "A5":
			this.size = "A5";
			break;
		case "B3":
			this.size = "B3";
			break;
		case "B4":
			this.size = "B4";
			break;
		case "B5":
			this.size = "B5";
			break;
		}
		
	}
	
	public String info() {
		String thickness = "";
		
		if (this.page <= 50) {
			thickness = "얇은";
		} else if (this.page <= 100) {
			thickness = "보통";
		} else {
			thickness = "두꺼운";
		}
		
		price = 500;
		
		switch (this.size) {
		case "A3":
			price += 400;
			break;
		case "A4":
			price += 200;
			break;
		case "B3":
			price += 500;
			break;
		case "B4":
			price += 300;
			break;
		case "B5":
			price += 100;
			break;
		}
		
		switch (this.color) {
		case "검정색":
			price += 100;
			break;
		case "노란색":
			price += 200;
			break;
		case "파란색":
			price += 200;
			break;
		}
		price += (this.page - 10) * 10;
		
		if (this.owner == null) {
			return String.format("■■■■■■ 노트 정보 ■■■■■■\n"
							   + "주인 없는 노트\n"
							   + "■■■■■■■■■■■■■■■■■■■■■■");
		} else {
			return String.format("■■■■■■ 노트 정보 ■■■■■■\n"
				 	   		   + "소유자: %s\n"
				 	   		   + "특성: %s %s %s노트\n"
				 	   		   + "가격: %,d원\n"
				 	   		   + "■■■■■■■■■■■■■■■■■■■■■■\n",
				 	   		   this.owner, this.color, thickness, this.size, this.price);
		}
		
	}
	
	public void setOwner(String owner) {		// 소유자명 setter
		if (owner.length() > 2 && owner.length() < 5) {
			this.owner = owner;
		} else {
			return;
		}
		
	}

	public void setPage(int page) {				// 페이지수 setter, 10 ~ 200 페이지 이내
		if (page > 10 || page < 200) {
			this.page = page;
		} 
		
	}

	public void setColor(String color) {		// 표지 색상 setter
		switch(color) {
		case "검정색":
			this.color = "검정색";
			break;
		case "흰색":
			this.color = "흰색";
			break;
		case "노란색":
			this.color = "노란색";
			break;
		case "파란색":
			this.color = "파란색";
			break;
		}
	}
}