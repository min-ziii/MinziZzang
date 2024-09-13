package com.test.java.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public class Ex56_Sort {
	public static void main(String[] args) {
		//Ex56_Sort.java
		
		// 정렬
		// - 배열 or 컬렉션(List)에 사용하는 기술
		// -순서가 있는 집합에만 정렬 가능.
		
		// 1. 직접 정렬 알고리즘을 써서 정렬
		// 2. JDK에서 제공하는 Array.sort() 사용
		
//		m1();
//		m2();
//		m3();
//		m4();
//		m5();
		m6();
		
	}

	private static void m6() {

		ArrayList<Employee> list = new ArrayList<Employee>();
		
		list.add(new Employee("홍길동", "부장"));
		list.add(new Employee("아무개", "사원"));
		list.add(new Employee("강아지", "대리"));
		list.add(new Employee("고양이", "과장"));
		list.add(new Employee("병아리", "사원"));
		
//		Collections.sort(list, new Comparator<Employee>() { // Comparator가 없으면 오름차순 / 있으면 사용자 설정
//
//			@Override
//			public int compare(Employee o1, Employee o2) {
//				
////				return o1.getName().compareTo(o2.getName());
////				return o1.getPosition().compareTo(o2.getPosition());
//				
//				return getPositionNumber(o1.getPosition()) - getPositionNumber(o2.getPosition());
//			}
//		});
		
		list.sort(new Comparator<Employee>() { // Comparator가 꼭 있어야 함

			@Override
			public int compare(Employee o1, Employee o2) {
				
				return o1.getName().compareTo(o2.getName());
			}
		});
		
		System.out.println(list);
		
	}
	// m6 연계 method
	private static int getPositionNumber(String position) { // 임의 정렬을 위한 method
		if (position.equals("부장")) {
			return 4;
		} else if (position.equals("과장")) {
			return 3;
		} else if (position.equals("대리")) {
			return 2;
		} else if (position.equals("사원")) {
			return 1;
		} else return 0;
	}

	private static void m5() {
		
		Student2[] list = new Student2[5];
		
		list[0] = new Student2("홍길동", 100, 90, 80);
		list[1] = new Student2("아무개", 96, 76, 86);
		list[2] = new Student2("강아지", 98, 68, 57);
		list[3] = new Student2("고양이", 67, 86, 86);
		list[4] = new Student2("병아리", 96, 56, 96);
		
		Arrays.sort(list, new Comparator<Student2>() {
			@Override
			public int compare(Student2 o1, Student2 o2) {
				
				return (o1.getKor() + o1.getEng() + o1.getMath())
						- (o2.getKor() + o2.getEng() + o2.getMath());
			}
		});
		
		System.out.println(Arrays.toString(list));
		
	}

	private static void m4() {
		
		// 객체 배열
		// 순수 배열로 해도 좋고 collection 배열로 해도 좋다.
		
		User[] list = new User[5];
		
		list[0] = new User("홍길동", 20, 180, 70);
		list[1] = new User("아무개", 25, 180, 65);
		list[2] = new User("강아지", 23, 180, 75);
		list[3] = new User("고양이", 24, 185, 80);
		list[4] = new User("병아리", 22, 190, 60);
		
		System.out.println(Arrays.toString(list));
		Arrays.sort(list, new Comparator<User>() {

			@Override
			public int compare(User o1, User o2) {
//				return o1.getAge() - o2.getAge(); 			 // 나이 오름차순
//				return o2.getAge() - o1.getAge(); 			 // 나이 내림차순
//				return o1.getHeight() - o2.getHeight(); 	 // 키 오름차순
//				return o1.getWeight() - o2.getWeight(); 	 // 무게 오름차순
//				return o1.getName().compareTo(o2.getName()); // 이름 오름차순
				if (o1.getHeight() > o2.getHeight()) {
					return 1;
				} else if (o1.getHeight() < o2.getHeight()) {
					return -1;
				} else {
					// 둘이 키가 같으면 몸무게로 한 번 더 비교해보자. - 2차 정렬
					if (o1.getWeight() > o2.getWeight()) {
						return 1;
					} else if (o1.getWeight() < o2.getWeight()) {
						return -1;
					} else {
						return 0;
					}
				}
			}
			
		});
		System.out.println(Arrays.toString(list)); // ClassCastException (형변환 오류. 뭘 기준을 잡고 sort 하라고?)
		
	}

	private static void m3() {

		Integer[] nums1 = {1, 5, 2, 4, 3};
		
//		Comparator<Integer> c1 = new Comparator<Integer>() { // Interface 변수는 원래 객체 생성이 안 되지만 뒤에 중괄호+추상메서드를 넣으면 가능 
//			@Override
//			public int compare(Integer o1, Integer o2) { // 추상 method
//				return o2 - o1;
//			}
//		};
//		
//		Arrays.sort(nums1, c1);
		
		// 1회성으로 요구되는 Interface 객체를 사용할 때 Anonymous Object를 만들어서 코드를 이렇게 줄일 수 있다.
		Arrays.sort(nums1, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		System.out.println(Arrays.toString(nums1));
		
	}

	private static void m2() {
		
		// 내림차순 정렬
		Integer[] nums1 = {1, 5, 2, 4, 3};
		
		//Arrays.sort(nums1); // 오름차순 정렬이 default
		
		MyComparator c1 = new MyComparator();
		
		Arrays.sort(nums1, c1);
		
		System.out.println(Arrays.toString(nums1));
	}

	private static void m1() {
		
		// 정렬 대상
		// 1. 숫자
		// 2. 문자(열)
		// 3. 날짜/시간
		
		// 1.A 숫자 + 배열
		int[] nums1 = {1, 5, 2, 4, 3};
		System.out.println(Arrays.toString(nums1));
		
		Arrays.sort(nums1); // 오름차순. 퀵 정렬 알고리즘을 사용함.
		
		System.out.println(Arrays.toString(nums1));
		
		System.out.println();
		
		// 1.A 숫자 + List
		ArrayList<Integer> nums2 = new ArrayList<>();
		
		nums2.add(1);
		nums2.add(5);
		nums2.add(2);
		nums2.add(4);
		nums2.add(3);
		
		System.out.println(nums2);
		Collections.sort(nums2); // Arrays.sort()과 같은 기능
		System.out.println(nums2);
		
		System.out.println();
		
		// 2. 문자열
		String[] names1 = { "홍길동", "강아지", "아무개", "고양이", "병아리" };
		
		Arrays.sort(names1); // Object 배열 overloading이 되어있어서 String을 넣는 게 가능
		
		System.out.println(Arrays.toString(names1));
		System.out.println();
		
		String s1 = "홍길동";
		String s2 = "아무개";
		
		System.out.println(s1.compareTo(s2)); // 4361
		// s1.charAt(0)과 s1.charAt(0)의 문자코드값을 비교해서 앞이 크면 양수, 같으면 0, 뒤가 크면 음수
		
		System.out.println();
		
		ArrayList<String> names2 = new ArrayList<>();
		
		names2.add("홍길동");
		names2.add("강아지");
		names2.add("아무개");
		names2.add("고양이");
		names2.add("병아리");
		
		Collections.sort(names2); // 오름차순 정렬
		System.out.println(names2);
		
		System.out.println();
		
		// 3. 날짜/시간
		Calendar[] clist = new Calendar[5]; // Calendar가 들어갈 공간 5개가 생성됨 (값은 전부 null)
		
		clist[0] = Calendar.getInstance();
		clist[0].add(Calendar.DATE, -5);
		
		clist[1] = Calendar.getInstance();
		clist[1].add(Calendar.DATE, 3);
		
		clist[2] = Calendar.getInstance();
		clist[2].add(Calendar.DATE, -2);
		
		clist[3] = Calendar.getInstance();
		clist[3].add(Calendar.DATE, 7);
		
		clist[4] = Calendar.getInstance();
		
		for (Calendar c : clist) {
			System.out.printf("%tF\n", c);
		}
		
		System.out.println();
		
		Arrays.sort(clist);
		
		for (Calendar c : clist) {
			System.out.printf("%tF\n", c);
		}
		
		System.out.println();
		
		// 3-1. 날짜/시간의 Collection 버전
		ArrayList<Calendar> clist2 = new ArrayList<Calendar>();
		clist2.add(Calendar.getInstance());
		clist2.get(0).add(Calendar.DATE, -5);
		
		clist2.add(Calendar.getInstance());
		clist2.get(1).add(Calendar.DATE, 3);
		
		clist2.add(Calendar.getInstance());
		clist2.get(2).add(Calendar.DATE, -2);
		
		clist2.add(Calendar.getInstance());
		clist2.get(3).add(Calendar.DATE, 7);
		
		clist2.add(Calendar.getInstance());
		
		for (Calendar c : clist2) {
			System.out.printf("%tF\n", c);
		}
		
		System.out.println();
		Collections.sort(clist2);
		
		for (Calendar c : clist2) {
			System.out.printf("%tF\n", c);
		}
	}
}

class MyComparator implements Comparator<Integer> { // java.util.Comparator를 상속받는 MyComparator 클래스 선언
	// {1, 5, 2, 4, 3}
	@Override
	public int compare(Integer o1, Integer o2) { // 둘 중 누가 더 크고 작은지
		return o2 - o1; // 내림차순이 되게끔 개발자가 직접 설정
		
//		if (o1 > o2) {
//			return -1;
//		} else if (o1 < o2) {
//			return 1;
//		} else {
//			return 0;
//		}
	}
	
}

class User {
	private String name;
	private int age;
	private int height;
	private int weight;
	
	public User(String name, int age, int height, int weight) { // 생성자(constructor)
		this.name = name;
		this.age = age;
		this.height = height;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		
		return String.format("\n%s(%d세) %dcm %dkg", this.name, this.age, this.height, this.weight);
	}
	
}

class Student2 {
	private String name;
	private int kor;
	private int eng;
	private int math;
	
	public Student2(String name, int kor, int eng, int math) {
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}
	
	@Override
	public String toString() {
		
		return String.format("\n%s %d점 %d점 %d점", this.name, this.kor, this.eng, this.math);
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
	
}

class Employee {
	private String name;
	private String position; // 직급(부장, 과장, 대리, 사원)
	
	public Employee(String name, String position) {
		this.name = name;
		this.position = position;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", position=" + position + "]\n";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
}