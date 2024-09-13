package com.test.question;

public class Q097 {
	public static void main(String[] args) {
		// Refrigerator 클래스와 Item 클래스를 설계하시오.
		
		Refrigerator r = new Refrigerator();

		Item item1 = new Item();
		item1.setName("김치");
		item1.setExpiration("2024-07-27");
		r.add(item1);//냉장고에 넣기

		Item item2 = new Item();
		item2.setName("깍두기");
		item2.setExpiration("2024-07-20");
		r.add(item2);//냉장고에 넣기

		Item item3 = new Item();
		item3.setName("멸치볶음");
		item3.setExpiration("2024-07-22");
		r.add(item3);//냉장고에 넣기

		Item item4 = r.get("깍두기");//냉장고에서 꺼내기
		System.out.printf("\n%s의 유통기한 : %s\n", item4.getName(), item4.getExpiration());

		System.out.printf("냉장고 안의 총 아이템 개수 : %d개\n", r.count());

		r.listItem();
	}
}

// 출력
//'김치'를 냉장고에 넣었습니다. //r.add(item1);
//'깍두기'를 냉장고에 넣었습니다. //r.add(item2);
//'멸치볶음'를 냉장고에 넣었습니다. //r.add(item3);
//
//깍두기의 유통기한 : 2024-07-20 //printf();
//냉장고 안의 총 아이템 개수 : 2개 //printf();
//
//[냉장고 아이템 목록] //r.listItem();
//김치(2024-07-27) 
//멸치볶음(2024-07-22)

class Refrigerator {
	private Item[] items = new Item[100];
	private int index = 0;

	public void add(Item item) {
		System.out.printf("'%s'를 냉장고에 넣었습니다.\n", item.getName());
		this.items[this.index] = item;
		this.index++;
		}
	   	
	public Item get(String name) {
		Item item = null;
		int point = 0;
		// name과 일치하는 String 값이 items[]에 있는지 검사하고
		// 있으면 해당 위치의 오른쪽 value들을 왼쪽으로 한 칸 shift
		// 없으면 없다. 라고 안내 메시지라도 하나 띄우기
		for (int i = 0; i < this.index; i++) {
			if (name.equals(this.items[i].getName())) {
				item = this.items[i];
				point = i;
				for (int j = point; j < index; j++) {
					this.items[j] = this.items[j+1];
				}
				this.index--;
			}
		}

		return item;
	}

	public int count() {
		return this.index;
	}

	public void listItem() {
		System.out.println("\n[냉장고 아이템 목록]");
		for (int i = 0; i < index; i++) {
			Item item = this.items[i];
			System.out.printf("%s(%s)\n", item.getName(),item.getExpiration());
		}
		
	}
}

class Item {
	private String name;
	private String expiration;
	   
	public String getName() {
		return name;
	}
	public String getExpiration() {
		return expiration;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
	
}