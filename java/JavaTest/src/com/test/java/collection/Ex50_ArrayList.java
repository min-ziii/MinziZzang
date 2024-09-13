package com.test.java.collection;

import java.util.ArrayList;
import java.util.Arrays;

public class Ex50_ArrayList {

	public static void main(String[] args) {
		// Ex50_ArrayList.java
		/* 
		   1. 제어문
		   2. 배열과 컬렉션
		   3. 클래스
		   
		   컬렉션, Collection
		   - JCF, Java Collection Framework
		   - 향상된 배열
		   - 뭐가 향상됐는데?
		   	1. 성능.
		   	2 사용법
		   	3. 길이의 가변성 -> 배열 길이가 줄거나 늘어남
		   	
		   	컬렉션 종류
		   	1. List 계열
		   		- ArrayList (★)
		   		- LinkedList
		   		- Queue
		   		- Stack
		   		- Vector(-)
		   	2. Set 계열
		   		- HashSet (★)
		   		- TreeSet
		   	3. Map 계열
		   		- HashMap(★)
		   		- TreeMap
		   		- Properties
		   		- HashTable
		   		
		  	ArrayList 클래스
		  	- 순수 배열과 구조가 거의 비슷함
		 */
		
//		m1();
//		m2(); ★
//		m3();
//		m4();
//		m5();
//		m6();
		m7();
	}

	private static void m7() {
		
		// 배열의 길이는 불변이다.
		// 컬렉션의 길이는 가변이다.
		// 컬렉션은 내부적으로 배열을 쓴다. -> ?????
		
		int[] list = new int[4];
		
		ArrayList<Integer> list2 = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			list2.add(i);
		}
		list2.trimToSize(); // 데이터의 값 담긴 배열		System.out.println(list2);
		
	}

	private static void m6() {
		
		// 학생을 여러명 관리한다. 데이터 집합이 필요
		// 1. Index를 쓴다.		-> 길이 고정 -> 학생 수가 정확하고 고정되어 있음
		// 2. ArrayList를 쓴다. -> 길이 가변 -> 학생 수가 불명확함
		// 웬만하면 ArrayList를 쓰는 경우가 많은 듯.
		
		// 성적표(학생의 집합)
		ArrayList<Student> list = new ArrayList<Student>();
		
		String[] names = { "홍길동", "아무개", "강아지", "고양이", "병아리" };
		
		for (int i = 0; i < 5; i ++) {
			Student s = new Student();
			
			s.setNo(i + 1);
			s.setName(names[i]);
			
			// 60~100
			s.setKor((int)(Math.random() * 41) + 60); // 0~40을 먼저 만들고 그 수들에 60씩 더하면 60~100의 범위를 가지게 됨
			s.setEng((int)(Math.random() * 41) + 60);
			s.setMath((int)(Math.random() * 41) + 60);

			list.add(s);
		}
		
		//출력
		System.out.println("=========================================================");
		System.out.println("                        성적표");
		System.out.println("=========================================================");
		System.out.println("[번호]\t[이름]\t[국어]\t[영어]\t[수학]\t[총점]\t[평균]");
		
		for (Student s : list) { 
			System.out.printf("%5d\t%s\t%5d\t%5d\t%5d\t%5d\t%5.1f\n"
							  ,s.getNo()
							  ,s.getName()
							  ,s.getKor()
							  ,s.getEng()
							  ,s.getMath()
							  ,s.getTotal()
							  ,s.getAvg());
		}
		
	}

	private static void m5() {
		
		// 객체 배열
		
//		Cup[] list = new Cup[5];
		ArrayList<Cup> list = new ArrayList<Cup>();
		
		list.add(new Cup("White", 5000));
		list.add(new Cup("Blue", 3000));
		list.add(new Cup("Yellow", 7000));
		
		for (Cup c : list) {
			System.out.println(c);
		}
		System.out.println();
		System.out.println(list);
	}

	private static void m4() {

		// 제어문 -> 반복문
		// 1. for문
		// 2. enhanced for문
		
		int[] nums = {100, 200, 300, 400, 500 };
		
		for (int i = 0; i < nums.length; i++) {
			System.out.println(nums[i]);
		}
		System.out.println();
		
		for (int num : nums) { // num이 nums로 가서 차례대로 값을 읽어옴
			System.out.println(num);
		}
		System.out.println();
		
		ArrayList<String> names = new ArrayList<>();
		
		names.add("강아지");
		names.add("고양이");
		names.add("코뿔소");
		
		for (String name : names) {
			System.out.println(name);
		}
		System.out.println();
		
		// 배열 dump
		System.out.println(Arrays.toString(nums));
		
		// 컬렉션 dump
		System.out.println(names); // .toString()을 굳이 적지 않아도 됨
		
		
	}

	private static void m3() {

		int[] 		ns1 = new int[3];
		ArrayList<Integer> ms1 = new ArrayList<Integer>();
		
		int[][] 	ns2 = new int[3][4];
		ArrayList<ArrayList<Integer>> ms2 = new ArrayList<ArrayList<Integer>>();
		
		int[][][] 	ns3 = new int[3][4][5];
		ArrayList<ArrayList<ArrayList<Integer>>> ms3 = new ArrayList<ArrayList<ArrayList<Integer>>>();
		
	}

	private static void m2() {

		// String[] list;
		ArrayList<String> list = new ArrayList<>();
		
		// 1. 요소 추가하기
		// -boolean add(T value)
		// 배열의 마지막에 추가한다 (Append Mode)
		list.add("바나나");
		list.add("사과");
		list.add("딸기");
		list.add("파인애플");
		list.add("귤");
		
		// 2. 요소 개수
		// - int size()
		System.out.println(list.size());
		
		// 3. 요소 읽기
		// - T get(int index)
		System.out.println(list.get(2));
//		System.out.println(list.get(5)); // IndexOutOfBoundsException
		
		// 4. 요소 수정
		// - T set(int index, T new Value)
		list.set(2, "참외");
		System.out.println(list.get(2));
		System.out.println("원하는 과일: " + list.get(3));
		
		// 5. 요소 삭제
		// - 방을 없애겠다는 소리.
		// - T remove(int index) -> 방번호로 삭제
		// - boolean remove(T value) -> 값으로 삭제
		// -삭제된 방 이후 모든 요소의 방번호는 -1이 된다. -> Left Shift 발생
		
		list.remove(2);			// 방번호는 유일하지만
		//list.remove("참외");	// 참외는 유일하지 않을 가능성이 있다. 조심.
		
		System.out.println(list.size());
		System.out.println(list.get(2)); // 지운 참외를 다시 가져오라고? ㄴㄴ파인애플임
		
		System.out.println("원하는 과일: " + list.get(3)); // 파인애플 아니고 귤임.
		
		// 6. 요소 추가하기
		// - 원하는 위치에 추가
		// 삽입 모드 (Insert Mode)
		// - void add(int index, T value)
		// - 삽입된 방 이후 모든 요소의 방번호는 +1이 된다. -> Right Shift 발생
		list.add(1, "망고");
		System.out.println(list.get(1)); // 망고
		System.out.println(list.get(2)); // 사과
		
		// 7. 요소 검색
		// - int indexOf(T value)
		// - int lastIndexOf(T value)
		// - boolean contains(T value)
		System.out.println(list.indexOf("사과"));
		System.out.println(list.indexOf("키위"));
		System.out.println(list.contains("파인애플"));
		
		// 8. 요소 탐색
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
			
		// 9. 초기화
		// - 모든 요소 삭제 -> 모든 방.remove()
		// -void clear();
		list.clear();
		System.out.println(list.size());
		
		// 10. 빈 배열 확인
		// - boolean isEmpty()
		System.out.println(list.size()==0);
		System.out.println(list.isEmpty());
		
		list.add("사과");
		
		System.out.println(list.size()==0);
		System.out.println(list.isEmpty());
		}
	}

	private static void m1() {
		 
		// 배열
		// 타입 명시 (int)
		// 길이 명시 (3)
		int[] nums1 = new int [3];
		
		// 요소 접근-> 첨자 사용 (Indexer)
		nums1[0] = 10; // 배열명[index]의 형식
		nums1[0] = 20;
		nums1[2] = 30;
		
		System.out.println(nums1[0]);
		System.out.println(nums1[1]);
		System.out.println(nums1[2]);
		
		System.out.println(nums1.length);
		System.out.println();
		
		
		// 컬렉션
		// - Object 배열 방식으로는 타입이 명시되어 있지 않다.
		// - Generic 방식으로는 타입을 명시할 수 있다.
		// - 길이는 명시 하지 않는다. (가변형이다.)
//		ArrayList nums2 = new ArrayList(); // Object[]
		ArrayList<Integer> nums2 = new ArrayList<Integer>();
		
		// 요소 접근
		// Indexer 대신 Method로 접근해야 한다.
		nums2.add(100); // nums2[0] = 100;
		nums2.add(200); // nums2[1] = 200;
		nums2.add(200); // nums2[2] = 300;
		
		System.out.println(nums2.get(0)); // nums2[0]
		System.out.println(nums2.get(1)); // nums2[1]
		System.out.println(nums2.get(2)); // nums2[2]
		
		System.out.println(nums2.size()); // nums2.length
		
		nums2.add(400);
		nums2.add(500);
		
		System.out.println(nums2.size()); // add로 값을 집어넣으면 길이도 늘어남(가변형)
		
		for (int i = 0; i < nums2.size(); i++) {
			System.out.println(nums2.get(i));
		}
				
	}
	
}
class Cup {
	private String color;
	private int price;
	
	public Cup(String color, int price) {
		this.color = color;
		this.price = price;
	}
	@Override
	public String toString() {
		return this.color + ":" + this.price;
	}
}

class Student {
	private int no;			// 번호
	private String name;	// 이름
	private int kor;		// 국어
	private int eng;		// 영어
	private int math;		// 수학
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	// 계산한 값을 따로 저장해놓는 변수와 method를 굳이 만들지 말 것. 관리가 귀찮음.
	public int getTotal() {
		return this.kor + this.eng + this.math;
	}
	public double getAvg() {
		return (this.kor + this.eng + this.math) / 3.0;
	}
	
}