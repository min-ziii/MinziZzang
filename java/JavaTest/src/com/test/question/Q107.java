package com.test.question;

import java.util.Arrays;

public class Q107 {
	public static void main(String[] args) {
		// MyHashMap 클래스를 구현하시오.
		MyHashMap map = new MyHashMap();
		
		//추가
		map.put("국어", "합격");
		map.put("영어", "불합격");
		map.put("수학", "보류");
		
		//읽기
		System.out.println(map.get("국어"));
		System.out.println(map.get("영어"));
		System.out.println(map.get("수학"));
		
		//개수
		System.out.println(map.size());
		
		//수정
		map.put("영어", "합격");
		System.out.println(map.get("영어"));
		
		//삭제
		map.remove("영어");
		System.out.println(map.get("영어"));

		//검색(key)
		if (map.containsKey("국어")) {
		      System.out.println("국어 점수 있음");
		} else {
		      System.out.println("국어 점수 없음");
		}

		//검색(value)
		if (map.containsValue("합격")) {
		      System.out.println("합격 과목 있음");
		} else {
		      System.out.println("합격 과목 없음");
		}

		//초기화
		map.clear();
		System.out.println(map.size());
	}
}

class MyHashMap { // HashMap은 <key, value>로 구성되며, 순서가 없다.
	private String[] keys; // key들이 들어갈 배열 공간 keys 선언
	private String[] values; // value들이 들어갈 배열 공간 values 선언
	private int index;

	public MyHashMap() { // 생성자. class의 변수들을 초기화하는 용도
		this.keys = new String[2]; // increaseSpace()가 자연스럽게 발동되기 위한 배열 크기
		this.values = new String[this.keys.length];
		this.index = 0;
	}
	
	public String[] getKeys() {
		return keys;
	}

	public void setKeys(String[] keys) {
		this.keys = keys;
	}

	public String[] getValues() {
		return values;
	}

	public void setValues(String[] values) {
		this.values = values;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return "[keys=" + Arrays.toString(keys)
			 + ", values=" + Arrays.toString(values)
			 + ", index=" + index + "]";
	}

	public boolean containsKey(String key) {
		for (int i = 0; i < this.index; i++) {
			if (key.equals(keys[i])) {
				return true;
			}
		}
		return false;
	}

	public boolean containsValue(String value) {
		for (int i = 0; i < this.index; i++) {
			if (this.values[i].equals(value)) {
				return true;
			} 
		}
		return false;
	}
	
	public String remove(String key) { // key의 요소를 삭제하고, 삭제한 key에 해당하는 value를 return한다.
		// 실제 HashMap 클래스에서는 {key, value}가 순서가 가지지 않기 때문에 ArrayList처럼 왼쪽 시프트가 일어나지 않지만,
		// 구현할 MyHashMap에서는 내부 구조상 배열을 사용하기 때문에 편의상 시프트되어 key공간 하나가 사라지는 식으로 구현한 듯.
		int tempIndex = 0;
		String tempStringValues = "";
		
		if (this.index < 0) { // 이미 다 지워져서 더 지워질 게 없으면
			this.index = 0;
			return null;
		} else {
			for (int i = 0; i < this.index; i++) { // keys[]에서 값이 들어있는 범위만큼만 반복문을 돌림
				if (this.keys[i].equals(key)) { // String 배열의 값 비교는 == 연산자가 아니라 equals()로 한다. 
					tempIndex = i;
				}
			}
					
			for (int i = tempIndex; i < this.keys.length; i++) {
				if (i == this.keys.length-1) { // 배열의 오른쪽 끝 공간에 범위 바깥의 값을 넣으려는 것을 막음
					this.keys[this.keys.length-1] = null; // HashMap의 구현방식에 정확하게 들어맞는 방법은 아니지만 그냥 넘어갑시다.
				} else {
					this.keys[i] = this.keys[i+1]; // 삭제될 위치를 기준으로 오른쪽에 있는 값들이 왼쪽으로 한 칸씩 옮겨간다.
				}
			}
			
			this.index--; // key가 하나 사라졌으니 keys[]의 크기도 하나 줄게끔 조절해야 한다.
			
			// keys[tempIndex]의 값이 삭제되었으니, values[tempIndex]의 값도 삭제되고 왼쪽으로 시프트가 일어나야 한다.
			// key-value가 한 쌍인 원본 HashMap 클래스로 생각하면, key가 지워졌으니 value도 같이 지워져야 하는 것.
			
			tempStringValues = values[tempIndex]; // 삭제될 values[]의 값을 옮겨놓는다.
												  //HashMap 클래스에서 remove()를 사용할 때 dump해보면 value값이 출력되기 때문
			
			for (int i = tempIndex; i < this.values.length; i++) {
				if (i == this.values.length-1) {
					this.values[this.values.length-1] = null;
				} else {
					this.values[i] = this.values[i+1];
				}
			}
			return tempStringValues;
		}
	}
	
	public void clear() { // index를 0으로 재설정하기만 하면 기존위치의 값들은 앞으로 들어올 값으로 다 덮어씌워질 것. -> 모두 삭제
		this.index = 0;
	}
	
	public void put(String key, String value) {
		// key와 value를 받아서 MyHashMap의 keys[]와 values[]에 각각 넣는다. 다 찼을 경우 배열들의 크기를 2배로 늘린다.
		if (this.index == this.keys.length) {
			increaseSpace();
		}
		if (this.index > -1) {
			for (int i = 0; i < this.index; i++) {
				if (this.keys[i] == key) {
					this.values[i] = value;
				}
			}
			this.keys[this.index] = key;
			this.values[this.index] = value;
			index++;
		} else {
			this.index = 0;
			this.keys[this.index] = key;
			this.values[this.index] = value;
			index++;
		}
		
	}
	
	public String get(String key) {
		if (index > -1) { // keys[]가 비어있지 않으면
			for (int i = 0; i < this.keys.length; i++) { // 입력받은 key가 keys[]의 어디에 있는 값과 일치하는가?
				if (key.equals(keys[i])) {
					return values[i];
				}
			}
		}
		return null;
	}

	public int size() { // keys[]와 values[]의 크기를 계산하는 method
		// TODO Auto-generated method stub
		return this.index;
	}
	
	private void increaseSpace() { // 가변형 공간이라는 특성을 가지는 HashMap 컬렉션의 공간확대 기능을 담당하는 method
		
			String[] tempKeys = new String[this.keys.length * 2]; // 공간이 다 찼을 경우 2배로 늘림
			for (int i = 0; i < this.keys.length; i++) { // 공간을 2배 늘린 tempKeys[]에 keys[]의 값들을 복사하고
				tempKeys[i] = this.keys[i];
			}
			this.keys = tempKeys; // tempKeys[]를 keys[]에 덮어씌우면 공간이 2배가 된 keys[] 완성
			
			String[] tempValues = new String[this.values.length * 2]; // keys[]를 끝냈으니 values[]에도 똑같이 적용
			for (int i = 0; i < this.values.length; i++) {
				tempValues[i] = this.values[i];
			}
			this.values = tempValues;
	}
	
}