package com.test.java.array;

import java.util.Arrays;
import java.util.Calendar;

public class Ex25_array {
	public static void main(String[] args) {
		// Ex25_array.java
		
		/*
		   배열, Array
		   - 데이터를 저장하는 자료구조
		   - 데이터의 집합 -> 변수들을 모아놓은 집합
		   - 배열이 뭐에요? -> 같은 성격의 같은 자료형을 저장하는 데이터 집합입니다.
		   
		   - 배열 + 반복문을 잘 쓸 수 있어야 한다.
		   
		   
		 */
		
//		m1();
//		m2();
//		m3();
//		m4();
//		m5();
//		m6();
//		m7();
//		m8();
		m9();
		
		
		
	}

	private static void m9() {
		
		// 배열의 초기화 리스트
		
		// 배열 초기화1 (값이 규칙적인 경우)
		int[] nums1 = new int[5];
		
		for (int i = 0; i < nums1.length; i++) {
			nums1[i] = (i+1) * 10; // 10의 배수로 nums1을 초기화
		}
		
		System.out.println(Arrays.toString(nums1));
		
		// 배열 초기화2
		int[] nums2 = new int[5];
		
		nums2[0] = 97;
		nums2[1] = 54;
		nums2[2] = 76;
		nums2[3] = 88;
		nums2[4] = 60;
		
		// 배열 초기화3
		int[] nums3 = new int[] { 97,54,76,88,60 };
		
		// 배열 초기화4
		int[] nums4 = { 97,54,76,88,60 };
		
		System.out.println(Arrays.toString(nums2));
		System.out.println(Arrays.toString(nums3));
		System.out.println(Arrays.toString(nums4));
		
		String[] names = { "홍길동", "아무개", "하하하" };
		
		System.out.println(Arrays.toString(names));
		
	}

	private static void m8() {
		
		// Arrays Class
		// -유틸리티 Class
		// -배열과 관련된 여러가지 기능을 구현한 Class
		
		int[] nums = new int[3];
		
		nums[0] = 10;
		nums[1] = 20;
		nums[2] = 30;
		
		// 배열 안을 확인 해볼까?
		System.out.println(nums); // [I@5aaa6d82
								  // [ 		  == 배열
								  // I 		  == Integer
								  // 5aaa6d82 == 메모리 주소
								  // 안됨. 쓸모 있는 데이터가 아님.
		
		// 배열 확인 method를 만들어볼까?
		printArray(nums);
		
		// 더 편한 방법 (개발자 debug용)
		System.out.println(Arrays.toString(nums)); // dump라고 부름
		
		
		// Arrays -> 깊은 복사 method를 지원함.
		int[] copy = Arrays.copyOfRange(nums, 0, nums.length);
		
		nums[0] = 111;
		
		System.out.println(Arrays.toString(nums));
		System.out.println(Arrays.toString(copy));
		
	}

	private static void printArray(int[] nums) {

		for(int i=0; i < nums.length; i++) {
			System.out.println(nums[1]);
		}
		
	}

	private static void m7() {
		
		// 배열 복사
		
		// 1. 얕은 복사 (Shallow Copy) -> 기본 동작
		// -주소값 복사
		
		// 2. 깊은 복사 (Deep Copy)
		// -요소 복사(실제 값 복사)
		
		int[] nums = new int[3];
		nums[0] = 10;
		nums[1] = 20;
		nums[2] = 30;
		
		int[] copy = new int[3];
		
		// 얕은 복사(주소값 복사)
//		copy = nums;
		
		// int = int 깊은 복사
		for (int i=0; i < nums.length; i++) {
			copy[i] = nums[i];
		}
		
		nums[0] = 111;
		
		System.out.println(nums[0]);
		System.out.println(copy[0]);
	}

	private static void m6() {
		
		int a = 10;
		changeValue(a);
		System.out.println("a: " + a);
		
		int[] b = new int[3];
		b[0] = 10;
		changeArray(b);
		System.out.println("b[0]: " + b[0]);
	}

	private static void changeArray(int[] b) {
		
		b[0] = 20;
		
	}

	private static void changeValue(int a) {
		
		a = 20;
		
	}

	private static void m5() {

		// 배열 복사(모든 참조형 복사)
		
		// 값형 복사
		int a = 10;	// 원본
		int b;		// 복사본
		
		b = a;
		
		// 값형 -> 원본을 수정해도 복사본에 영향을 주지 않음
		// 		-> Side Effect가 없음.
		a = 20; // 원본 수정
		
		System.out.println("a: " + a);
		System.out.println("b: " + b);
		System.out.println();
		
		
		int[] nums = new int[3];
		nums[0] = 111;
		nums[1] = 222;
		nums[2] = 333;
		
		int[] copy = new int [3];
		
		//참조형 -> 원본을 수정하면 복사본도 수정됨
		//		 -> Side Effect가 발생함
		//int[] = int[]
		copy = nums;
		
		nums[0] = 111;
		System.out.println(copy[0]);
		System.out.println(copy[1]);
		System.out.println(copy[2]);
		
		
		
	}

	private static void m4() {
		
		// 각 자료형별 배열 만들기
		
		// 정수 배열(byte, short, int, long)
		byte[] list1 = new byte[5];
		list1[0] = 10;
		System.out.println(list1[0]);
		
		// 실수 배열(float, double)
		double[] list2 = new double[5];
		list2[0] = 3.14;
		System.out.println(list2[0]);
		
		// 문자 배열(char) == String 
		char[] list3 = new char[5];
		list3[0] = 'A';
		System.out.println(list3[0]);
		
		// 논리 배열(boolean)
		boolean[] list4 = new boolean[5];
		list4[0] = true;
		System.out.println(list4[0]);
		
		// 참조형 배열
		String[] list5 = new String[5];
		list5[0] = "홍길동";
		System.out.println(list5[0]);
		
		
		Calendar[] list6 = new Calendar[5]; // Calendar 변수를 5개 만들었지만 값이 들어가있진 않음
		list6[0] = Calendar.getInstance();
		System.out.println(list6[0]);
		
	}

	private static void m3() {

		// 방 1개: 배열의 요소(Element)
		// 방번호: 배열의 인덱스(Index)
		// 방 개수: 배열의 길이(Length)
		
		// 모든 참조형의 특징
		// - 할당된 공간을 초기화하지 않아도 자동으로 초기화가 된다.
		
		// 배열의 자동 초기화
		// int[]	 = 모든 방을 0으로 초기화
		// double[]	 = 모든 방을 0.0으로 초기화
		// char[]	 = 모든 방을 '\0'로 초기화
		// boolean[] = 모든 방을 false로 초기화
		// String[]	 = 모든 방을 null로 초기화
		int[] nums = new int [5];
		
		nums[0] = 10;
		//nums[1] = 20;
		nums[2] = 30;
		nums[3] = 40;
		nums[4] = 50;
		
		System.out.println(nums[0]);
		System.out.println(nums[1]); // 배열 값을 안 넣어도 0으로 자동 초기화되어있다.
		System.out.println(nums[2]);
		System.out.println(nums[3]);
		System.out.println(nums[4]);
		// java.lang.ArrayIndexOutOfBoundsException:
		// Index 5 out of bounds for length 5
		// System.out.println(nums[5]);
		
		// 배열 탐색 -> for문 사용
		for (int i = 0; i < nums.length; i++) {
			System.out.printf("nums[%d] = %d\n", i, nums[i]);
		}
		
		// 배열의 길이 = 5
		// 첨자 범위 = 0 ~ 4 ( 0 ~ 길이-1)
		
	}

	private static void m2() {

		// 요구사항) 학생 3명의 국어 점수를 저장하고 총점과 평균을 구하라.
		
		// 배열 선언하기
		// 자료형[] 변수명 = new 자료형[길이];
		// 배열타입 배열명 = new 배열타입[배열의 길이];
		int[] kors = new int[3];
		
		kors[0] = 100;
		kors[1] = 90;
		kors[2] = 80;
		
		int total = 0;
		
		for (int i = 0; i < kors.length; i++) {
			total += kors[i];
		}
		
		double avg = (double)total / kors.length;
		System.out.printf("총점: %d점, 평균: %.1f점\n", total, avg);
		
	}

	private static void m1() {
		
		// 요구사항) 학생 3명의 국어 점수를 저장하고 총점과 평균을 구하라.
		// 추가) 학생수가 300명으로 증가
		
		// 여태까지의 방식
		int kor1;
		int kor2;
		int kor3;
		// +297
		
		kor1 = 100;
		kor2 = 90;
		kor3 = 80;
		// +297
		
		int total = kor1 + kor2 + kor3; // + kor4 + kor5 + ... + kor300;
		
		double avg = total / 3.0; // 3.0 -> 300
		
		System.out.printf("총점: %d점, 평균: %.1f점\n", total, avg);
		
	}
}
