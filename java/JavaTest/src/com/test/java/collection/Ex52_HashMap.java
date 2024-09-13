package com.test.java.collection;

import java.util.ArrayList;
import java.util.HashMap;

public class Ex52_HashMap {	
	public static void main(String[] args) {
		// Ex52_HashMap.java
		/*
		   List -> ArrayList
		   Map -> HashMap
		   
		   ArrayList
		   - 요소 접근에 첨자(index)를 사용
		   - 순서(==방번호)가 있는 집합
		   ex) 1강의실, 2강의실, ...
		   - 스칼라 배열(scalar array)
		   - 요소 = 첨자(index) + 값(value)
		   - index는 유일해야 한다.
		   - value는 중복이 가능하다.
		   
		   HashMap
		   - 요소 접근에 key(방의 이름) 사용. 방번호는 없음
		   - 순서가 없는 집합
		   ex) 햇님반, 달님반 등
		   - 연관 배열, 사전 구조(Dictionary)
		   - 요소 = 키(Key) + 값(value)
		   - key는 유일해야 한다.
		   - value는 중복이 가능하다.
		 */
		
//		m1();
		m2();
	}

	private static void m2() {

		HashMap<String,Integer> map1 = new HashMap<>(); // 거의 String을 쓴다.
		
		map1.put("방이름", 100);
		
		HashMap<Boolean,String> map2 = new HashMap<>();
		map2.put(true, "참");
		map2.put(false, "거짓");
		
		HashMap<Integer, String> map3 = new HashMap<>(); // 이런 식으로 쓸거면 굳이 HashMap을 쓸 필요가 없고 ArrayList를 쓰면 되지.
		map3.put(1, "하나");
		map3.put(2, "둘");
		System.out.println(map3.get(1));
		
		HashMap<String, String> map = new HashMap<>();
		
		// 1. 요소 추가
		map.put("red", "빨강");
		map.put("yellow", "노랑");
		map.put("blue", "파랑");
		
		// 2. 요소 개수
		System.out.println(map.size());
		System.out.println(map); // red=빨강 -> key와 value의 한 쌍을 java에서는 Entry 라고 부른다.
		
		// 3. 요소 읽기
		System.out.println(map.get("red"));
		System.out.println(map.get("yellow"));
		System.out.println(map.get("blue")); // 없는 방의 이름을 불러도 오류가 나지 않고 null이 나온다.
		
		// 4. 요소 수정 (추가와 method가 같다)
		// - key는 식별자이기 때문에 수정할 수 없다.
		// - value는 수정할 수 있다.
		map.put("yellow", "옐로우");
		System.out.println(map);
		
		// 5. 요소 삭제
		// ArrayList는 중간값이 빠지면 나머지 값이 밀리면서 shift가 발생한다.
		// HashMap 은 괜찮다.
		map.remove("yellow");
		System.out.println(map.get("yellow"));
		System.out.println(map.size());
		System.out.println(map);
		
		// 6. 검색
		// ArrayList.IndexOf()
		// HashMap.indexOf -> X
		System.out.println(map.containsKey("red"));
		System.out.println(map.containsValue("빨강"));
		
		// 7. 초기화
		map.clear();
		System.out.println(map);
		
	}

	private static void m1() {
		// 학생 1명의 국영수 점수 저장 -> 어떤 데이터 집합을 사용할 것인가?
		
		// 1. 배열
		// 2. ArrayList
		
		// 4. Class
		// 3. HashMap
		
		// 1. 배열
		//- 집합
		//- 첨자는 의미가 없다. -> 각각의 방이 무슨 데이터인지 파악하기 들
		int[] s1 = new int[3];
		
		s1[0] =100;
		s1[1] = 90;
		s1[2] = 80;
		
		System.out.println(s1[0] + s1[1] + s1[2]);
		
		// 2. ArrayList
		ArrayList<Integer> s2 = new ArrayList<Integer>();
		
		s2.add(100);
		s2.add(90);
		s2.add(80);
		
		System.out.println(s2.get(0) + s2.get(1) + s2.get(2));
		
		// 4. class
		Score s3 = new Score(100, 90, 80);
		
		s3.setMath(88);
		
		System.out.println(s3.getTotal());
		
		// 3. HashMap
		HashMap<String,Integer> s4 = new HashMap<String,Integer>();
		
		s4.put("kor", 100);
		s4.put("eng", 90);
		s4.put("math", 80);
		
		System.out.println(s4.get("kor") + s4.get("eng") + s4.get("math"));
		
	}
	
}

class Score {
	
	private int kor;
	private int eng;
	private int math;
	
	public Score() {
		this.kor = 0;
		this.eng = 0;
		this.math = 0;
	}
	
	public Score(int kor, int eng, int math) {
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}
	
	public int getTotal() {
		return this.kor + this.eng + this.math;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}
	
}