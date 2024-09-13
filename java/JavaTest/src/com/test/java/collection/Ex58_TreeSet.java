package com.test.java.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class Ex58_TreeSet {
	public static void main(String[] args) {
		// Ex58_TreeSet.java
		
		/*
		   	컬렉션 이름
		   				[사용법=Interface이자 물리적인 특징]
		   	Array		List
		   	Linked		List
		   	Hash		Set
		   	Tree		Set
		   	Hash		Map
		   	Tree		Map
		   	
		   	TreeSet
		   	- 순서가 없다.
		   	- 중복값을 가지지 않는다.
		   	- 이진 탐색 트리 구조, Binary Search Tree
		   		-내부 데이터가 정렬이 되어있는 구조로 저장
		 */
		TreeSet<Integer> set = new TreeSet<>();
//		HashSet<Integer> set = new HashSet<>();
		
		// 순서가 중요하거나 범위를 가지고 무언가를 할 때는 TreeSet 권장
		// 아니라면 속도가 더 빠른 HashSet 권장
		
		set.add(6);
		set.add(2);
		set.add(5);
		set.add(8);
		set.add(1);
		set.add(9);
		set.add(3);
		set.add(4);
		set.add(10);
		set.add(7);
		
		System.out.println(set.size());
		System.out.println(set);
		
		TreeSet<String> names = new TreeSet<String>();
		
		names.add("홍길동");
		names.add("아무개");
		names.add("강아지");
		names.add("고양이");
		names.add("병아리");
		names.add("홍길동");
		
		System.out.println(names);
		
		// Set > (변환) > List;
		ArrayList<String> list = new ArrayList<String>(names);
		
		Collections.sort(list);
		
		System.out.println(list);
		
		System.out.println();
		
		// 범위 관련 기능 제공
		System.out.println(set.first());
		System.out.println(set.last());
		
		System.out.println(set.headSet(3));
		System.out.println(set.tailSet(7));
		System.out.println(set.subSet(3, 7));
		
	}// main
}// class
