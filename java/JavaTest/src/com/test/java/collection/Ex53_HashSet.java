package com.test.java.collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class Ex53_HashSet {
	 public static void main(String[] args) {
		
		 // 1. List
		 // - 순서가 있는 집합
		 // - 첨자 (방번호)
		 // - 데이터 중복 허용
		 
		 // 2. Map
		 // - 순서가 없는 집합
		 // - 키 (방이름)
		 // 데이터 중복 허용
		 
		 // 3. Set
		 // - 순서가 없는 집합
		 // - 식별자가 없음(방번호x, 방이름x)
		 // - 데이터 중복 불가★
		 
		 // 데이터 집합을 만들고 싶다. 어떤 데이터 집합을 선택할 것인가?
		 // 배열보단 컬렉션이 관리에 편할 것 같은데, 그럼 무슨 컬렉션을 쓰지?
		 // 1. 순수 배열과 비슷한 상황 						  -> ArrayList
		 // 2. 요소를 구분할 때 이름으로 가독성을 높이고 싶다 -> HashMap
		 // 3. 요소의 중복을 허용하지 않는다	 			  -> HashSet
		 
//		 m1();
//		 m2();
//		 m3();
		 m4();
	}

	private static void m4() {
		
		// HashSet과 객체(Object)에 대한 이야기.
		
		HashSet<String> set1 = new HashSet<>();
		
		set1.add("홍길동");
		set1.add("아무개");
		set1.add("하하하");
		
		System.out.println(set1);
		
		set1.add("홍길동"); // HashSet은 중복값을 허용하지 않기 때문에 출력은 이전과 같다.
		
		// 중복된 Member를 배제하는 HashSet 객체
		HashSet<Member> set2 = new HashSet<Member>();
		
		set2.add(new Member("홍길동", 20));
		set2.add(new Member("아무개", 22));
		set2.add(new Member("하하하", 25));
		
		System.out.println(set2);
		
		set2.add(new Member("홍길동", 20)); // Set의 add()는 객체를 비교할 때 equals()를 쓰는데, 개발자가 equals()를 재정의했다.
		
		System.out.println(set2); // HashSet은 중복값을 허용하는데 왜 한 번 더 출력이 될까?
		
		Member m1 = new Member("홍길동", 20);
		Member m2 = new Member("아무개", 22);
		Member m3 = new Member("홍길동", 20);
		
		// 주소값(참조형) 변수의 비교는 연산자를 사용하지 말 것. -> equals() 사용 권장
		System.out.println(m1 == m2); 		// false
		System.out.println(m1.equals(m2));  // false
		
		System.out.println(m1 == m3);		// false
		System.out.println(m1.equals(m3));  // false-> equals() 재정의 이후 true
		
		System.out.println("m1: " + m1.hashCode()); // (hashCode 재정의 이전)메모리 주소값 반환: 1321640594
		System.out.println("m2: " + m2.hashCode());
		System.out.println("m3: " + m3.hashCode()); // 메모리 주소값 반환: 457233904
		
		
		
	}

	private static void m3() {
		
		// List vs Set
		
		// 상황) 마트에서 경품을 추첨하고 있다.
		// - 중복 당첨을 허용하거나
		// - 중복 당첨을 허용하지 않는 행사일 수도 있다.
		
		String[] list = {"홍길동", "아무개", "하하하", "호호호", "강아지",
						 "고양이", "병아리", "호랑이", "사자", "햄스터"};
		
		Random rnd = new Random();
		
		// 당첨자들을 배열에 저장할 것.
		// 1. 중복 당첨 허용 (ArrayList)
		ArrayList<String> result1 = new ArrayList<>();
		
		for (int i = 0; i < 5; i++) {
			result1.add(list[rnd.nextInt(list.length)]); // list[0] ~ list[9]
		}
		System.out.println(result1);
		
		// 2. 중복 당첨 불허 (HashSet)
		HashSet<String> result2 = new HashSet<>();
		
		while (result2.size() < 5) {
			result2.add(list[rnd.nextInt(list.length)]);
		}
		System.out.println(result2);
	}

	private static void m2() {
		
		// 로또 번호 만들기 -> 난수 + 유일한 수
		
//		for (int i = 0; i < 10; i++) {
//			System.out.println((int)(Math.random() * 45) + 1);
//		}
		
		Random rnd = new Random();
		
		ArrayList<Integer> lotto = new ArrayList<Integer>();
		
//		int n = 0;
//		for (int i = 0; i < 6; i++) {
//			n = rnd.nextInt(45) + 1;
//			
//			if (check(lotto, n)) {
//				lotto.add(n);
//			} else {
//				i--;
//			}
//		}
		
//		while (lotto.size() < 6) {
//			int n = rnd.nextInt(45) + 1;
//			
//			if (check(lotto, n)) {
//				lotto.add(n);
//			}
//		}
//		
//		System.out.println(lotto);
		
		HashSet<Integer> lotto2 = new HashSet<>();
		
		while (lotto2.size() < 6) {
			int n = rnd.nextInt(45) + 1;
			// HashSet은 중복된 값을 허용하지 않기 때문에(위치를 특정할 수 없기 때문) check(n)으로 중복값 검사를 따로 할 필요가 없다.
			lotto2.add(n);
		}
		System.out.println(lotto2);
		
		
		
	}
	
	private static boolean check(ArrayList<Integer> lotto, int n) {
		for (int i = 0; i < lotto.size(); i++) {
			if (lotto.get(i) == n) {
				return false;
			}
		}
		return true;
	}

	private static void m1() {

		HashSet<String> set = new HashSet<String>();
		
		System.out.println(set);
		
		// 1. 요소 추가
		set.add("사과");
		set.add("딸기");
		set.add("바나나");
		System.out.println(set);
		
		// 2. 요소 개수
		System.out.println(set.size());
		
		// 3. 요소 읽기 -> method 제공 안함
		// - 대신 요소 읽기 도구(Iterator)를 제공함 == enhanced for문
		
		// set으로부터 Iterator를 얻어낸다.
		Iterator<String> iter = set.iterator();
		
//		System.out.println(iter.hasNext()); // 읽어올 다음 데이터가 있는가?
//		System.out.println(iter.next()); // 읽어라.
//		
//		System.out.println(iter.hasNext());
//		System.out.println(iter.next());
//		
//		System.out.println(iter.hasNext());
//		System.out.println(iter.next());
//		
//		System.out.println(iter.hasNext()); // 더 읽어올 게 없으므로 false가 출력된다.
		
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		iter = set.iterator(); // 새로운 반복기를 생성한다. 커서를 BOF에서 시작하도록 조절한다.
		
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		System.out.println();
		
		for (String item : set) { // while하고 똑같은 내용
			System.out.println(item);
		}
		
	}
	
}
