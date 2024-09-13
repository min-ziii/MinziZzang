package com.test.java.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

public class Ex59_TreeMap {
	public static void main(String[] args) {
		// Ex59_TreeMap.java
		
		// TreeMap
		// - 키 + 값 -> Map
		// 이진 탐색 트리 -> 내부 정렬
		
		TreeMap<String,String> map = new TreeMap<String,String>();
		
		map.put("white", "회색");
		map.put("black", "검정색");
		map.put("red", "빨강");
		map.put("yellow", "노랑");
		map.put("blue", "파랑");
		
		System.out.println(map);
		
		System.out.println(map.get("white"));
		
		System.out.println(map.firstKey());
		System.out.println(map.lastKey());
		
		System.out.println(map.headMap("m")); // a ~ m 사이의 값을 가져오라
		System.out.println(map.tailMap("m")); // m ~ z 사이의 값을 가져오라
		
		System.out.println(map.subMap("r", "w")); // r이상 w미만 값을 가져오라
		
		/*
		   	컬렉션 정리
		   	
		   	List
		   	- ArrayList★ (사용빈도 1순위)
		   	- Stack
		   	- Queue
		   	- LinkedList (중간삽입같은 걸 자주 하면)
		   	- Vector (오래된 애라서 안 씀. Vector의 대용으로 나온 것이 ArrayList)
		   	
		   	Set
		   	- HashSet★ (사용빈도 3순위. 중복값이 필요 없는 경우 사용)
		   	- TreeSet (정렬이 된 Set을 쓸 일이 있으면?)
		   	
		   	Map
		   	- HashMap★ (사용빈도 2순위)
		   	- TreeMap
		   	- Properties (나중에 볼 것. 용도가 한정되어 있음.)
		   	- HashTable (오래된 애라서 안 씀. HashTable의 대용으로 나온 것이 HashMap)
		 */
		
		//Vector의 사용 예시
		Vector<Integer> v = new Vector<>();
		v.add(100);
		System.out.println(v.get(0));
		
		
		
		HashMap<String,String> map2 = new HashMap<>();
		
		map2.put("white", "회색");
		map2.put("black", "검정색");
		map2.put("red", "빨강");
		map2.put("yellow", "노랑");
		map2.put("blue", "파랑");
		
		// Map은 loop를 돌릴 수 없지만, 방법이 있긴 함
		
		Set<String> set = map2.keySet(); // xxSet의 부모 타입
		System.out.println(set); // key만 들어있음, 중복값 불가
		
		for (String key : set) {
			System.out.println(key);
		}
		
		Collection<String> values = map2.values();
		System.out.println(values); // value만 들어있음, 중복값 가능
		
		for (String value : values) {
			System.out.println(value);
		}
		
		System.out.println();
		
		for (String key : set) {
			System.out.printf("map[%s] = %s\n"
							  , key, map2.get(key));
		}
		
	}
}
