package com.test.java.lambda;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.test.data.Data;
import com.test.data.Item;
import com.test.data.User;
import com.test.util.MyUtil;

public class Ex65_Stream {
	public static void main(String[] args) {
		// Ex65_Stream.java
		/*
		 	자바 스트림
		 	1. 입출력 스트림
		 	 - 파일 입출력, 콘솔 입출력, 네트워크 입출력 등
		 	 
		 	2. 스트림
		 	- Java 8(JDK 1.8)에서 lambda식과 같이 출시
		 	- 배열이나 컬렉션을 탐색(조작)하는 기술(★ㅈㄴ편함!!!)
		 	- 파일 입출력 지원
		 	- 디렉토리 탐색 지원
		 	- 등등...
		 	
		 	표준 API 함수형 인터페이스
		 	1. Consumer
		 	(매개변수) -> {};
		 	
		 	2. Supplier
		 	() -> {return 값;};
		 	
		 	3. Function
		 	(매개변수) -> {return 값;};
		 	
		 	4. Operator
		 	(매개변수) -> {return 값;};
		 	- 매개변수와 반환값의 자료형이 동일해야 한다.
		 	
		 	5. Predicate
		 	(매개변수) -> {return 값;};
		 	- 반환값이 항상 Boolean이다.
		 */
		
//		m1();
//		m2();
//		m3();
//		m4();
//		m5();
//		m6();
//		m7(); // ★★★
//		m8();
//		m9();
		m10();
		
	} // main
	
	private static void m10() {

		// 매칭
		// - allMatch(), anyMatch(), noneMatch()
		// - filter()와는 전혀 다름
		// - 최종 파이프.
		
		// - boolean allMatch(Predicate) : 모든 요소가 조건을 모두 만족하는지?
		// - boolean anyMatch(Predicate) : 일부 요소가 조건을 만족하는지?
		// - boolean noneMatch(Predicate) : 모든 요소가 조건을 모두 불만족하는지?
		
		// 요구사항) 배열에 짝수만 있는가?
		
		int[] nums = {2, 4, 6, 8, 10};
		
		// 1. 기존 방식
		boolean result = false;
		
		for (int n : nums) {
			if (n % 2 == 0) {
				result = true;
				break;
			}
		}
		
		if (result) {
			System.out.println("짝수만 존재");
		} else {
			System.out.println("홀수가 존재");
		}
		
		// nums 안의 수가 모두 짝수인지?
		// 2. allMatch() 파이프, 조건 && 조건 && 조건
		result = Arrays.stream(nums)
					   .allMatch(n -> n % 2 == 0);
		
		System.out.println("2, 4, 6, 8, 10 allMatch 짝수? : " + result);
		
		
		// nums 안에 홀수가 하나라도 들어있는지?
		// 3. anyMatch() 파이프, 조건 || 조건 || 조건
		nums = new int[] {2, 4, 6, 8, 10, 3};
		result = Arrays.stream(nums)
					   .anyMatch(n -> n % 2 == 1);
		
		System.out.println("2, 4, 6, 8, 10, 3 anyMatch 홀수? : " + result);
		
		
		// nums 안에 짝수가 단 하나도 없는지?
		// 4. noneMatch() 파이프 (모두 불만족해야 true)
		nums = new int[] {2, 4, 6, 8, 10};
		result = Arrays.stream(nums)
					   .noneMatch(n -> n % 2 == 0);
		
		System.out.println("2, 4, 6, 8, 10 noneMatch 짝수? : " + result);
		
		
		// userList 속 모든 user의 키가 190이 넘는가? true or false로 대답하라.
		System.out.println(Data.getUserList().stream()
											 .allMatch(user -> user.getHeight() >= 190));
	}

	private static void m9() {

		// 집계 Reduce
		// - count()
		// - max()
		// - min()
		// - sum()
		// - avg()
		// - 최종 파이프
		// - 앞의 stream 요소를 취합해서 하나의 통곗값을 생성
		
		long count = Data.getIntList().stream().count();
		System.out.println(count);
		
		System.out.println(Data.getUserList().stream().filter(user -> user.getGender() == 1).count());
		System.out.println(Data.getUserList().stream().filter(user -> user.getGender() == 2).count());
		
		// max(), min()
		List<Integer> nums = Data.getIntList(); // 0 ~ 100 사이
		
		int max = -1;
		int min = 101;
		
		for(int n : nums) {
			if (n > max) {
				max = n;
			}
		}
		for (int m : nums) {
			if (m < min) {
				min = m;
			}
		}
		System.out.println(max);
		System.out.println(min);
		
		System.out.println(nums.stream().max(Comparator.naturalOrder()));
		
		nums.stream().max(Comparator.naturalOrder()); // Optional[99] 라는 결과가 출력된다.
		
		// Optional<Integer>란?
		// - int or Integer 타입과 거의 동일한 자료형
		// - null을 가질 수 있는 int
		
//		nums.clear();
		
		Optional<Integer> result = nums.stream().max(Comparator.naturalOrder());
		System.out.println(result.get());
		
		result = nums.stream().min((a, b) -> a - b);
		System.out.println(result.get());
		 
		//값형 변수는 null을 가질 수 없다.(null은 참조형만 가능)
//		int a = null; // -> 이게 안 된다는 소리.
		
		//둘 다 null
//		Integer n1 = null; // = nums.stream().max(Comparator.naturalOrder());
//		Optional<Integer> n2 = nums.stream().max((a, b) -> a - b);
//		
//		if (n1 != null) {
//			System.out.println(n1);
//		}
//		
//		System.out.println(n2.isEmpty());
		
		// Optional은 NullPoiterException 예외에서 안전한 자료형이다.
		
		
		
		Optional<User> user1 = Data.getUserList().stream().max((u1, u2) -> u1.getHeight() - u2.getHeight());
		System.out.println(user1.get());
		
		Optional<User> user2 = Data.getUserList().stream().min((u1, u2) -> u1.getHeight() - u2.getHeight());
		System.out.println(user2.get());
		
		// count(), max(), min()은 Stream<T>로 모든 자료형에 적용할 수 있다.
		// sum(), avg()는 IntStream으로 숫자에만 적용할 수 있다.
		
		//Stream<Integer> 변환 IntStream
		System.out.println(nums.stream().mapToInt(n -> n).sum());
				
		System.out.println(nums.stream().mapToInt(n -> n).average().getAsDouble());
		System.out.println();
				
		//남자 평균 키?
		System.out.println(
						
		Data.getUserList().stream()
						  .filter(user -> user.getGender() == 1)
						  .mapToInt(user -> user.getHeight())
						  .average()
						  .getAsDouble()
		);
	}

	private static void m8() {

		// 정렬
		// - sorted(Comparator)
		// - 중간 파이프
		// - 사용법이 기존 컬렉션의 sort() 메서드와 동일함.
		
		Data.getIntList().stream()
//						 .sorted() // 오름차순
						 .sorted((o1, o2) -> o2 - o1) // 내림차순
						 .forEach(n -> System.out.println(n));
		
		ArrayList<Integer> list = new ArrayList<>();
		list.add(5);
		list.add(2);
		list.add(4);
		list.add(1);
		list.add(3);
		
//		list.sort((o1, o2) -> o1 - o2);
		list.sort(Comparator.naturalOrder());
		System.out.println(list);
		System.out.println();
		
//		list.sort((o1, o2) -> o2 - o1);
		list.sort(Comparator.reverseOrder());
		System.out.println(list);
		System.out.println();
		
		Data.getIntList().stream()
						 .sorted(Comparator.reverseOrder())
						 .forEach(n -> System.out.println(n));
		System.out.println();
		
		Data.getIntList().stream()
						 .distinct()
						 .filter(n -> n % 2 == 0)
						 .sorted(Comparator.naturalOrder())
						 .forEach(n -> System.out.println(n));
		System.out.println();
		
		
	}

	private static void m7() {
		
		// ★★★변환 파이프
		// - map(Function), mapXXX(Function)
		// - 중간 파이프에 속함
		// - 앞쪽 stream의 요소를 다른 형태의 stream으로 반환한다.
		// 예) int형 stream을 String형 stream으로 바꾸기
		
		List<String> list = Data.getStringList(10);
		System.out.println(list);
		System.out.println();
		
		// 긴 단어는 "긴 단어"라고 출력하고 짧은 단어는 "짧은 단어"라고 출력하는 일을 stream으로 진행한다면.
//		list.stream()
//			.forEach(word -> System.out.println(word));
//		System.out.println();
		
//		list.stream()
//			.forEach(word -> {
//				if (word.length() >= 5) {
//					System.out.println("긴 단어");
//				} else {
//					System.out.println("짧은 단어");
//				}
//			});
//		System.out.println();
		
		list.stream()
			.map(word -> word.length() >= 5 ? "긴 단어" : "짧은 단어") // 이런 행동(수정 후 넘기기)을 Mapping이라고 부름
			.forEach(word -> System.out.println(word));
		System.out.println();
		
		
		Data.getUserList().stream()
						  .forEach(user -> System.out.println(user));
		System.out.println();
		
		Data.getUserList().stream()
//						  .map(user -> user.getName()) // User type : String
//						  .map(user -> user.getAge()) // User type: Integer
						  .map(user -> user.getGender() == 1? "남자" : "여자")
						  .forEach(name -> System.out.println(name));
		System.out.println();
		
		// names를 Member 객체로 변환
		String[] names = { "홍길동","아무개","강아지","고양이","병아리"};
		Arrays.stream(names)
			  .map(name -> new Member(name, 20))
			  .forEach(m -> System.out.println(m));
			  
		// User -> 변환 -> Member
		Data.getUserList()
			.stream()
			.map(user -> new Member(user.getName(), user.getAge()))
			.forEach(m -> System.out.println(m));
			System.out.println();
		 
		
	}

	private static void m6() {
		
		// 중복 제거
		// - distinct()
		// - 중간 파이프
		// - 앞의 stream에서 중복 요소를 제거
		// - 유일한 요소만 다음 파이프로 전달
		// - Set 역할
		
		List<Integer> list = Data.getIntList();
		System.out.println(list.size());
		
		// 요구사항) list에서 중복값 제거하기
		// 1.
		Set<Integer> set = new HashSet<>();
		for (int n : list) {
			set.add(n); // 자동으로 중복값 제거
		}
		System.out.println(set.size());
		
		// 2.
		Set<Integer> set2 = new HashSet<>(list);
		System.out.println(set2.size());
		
		// 3.
		System.out.println(list.stream().distinct().count()); // count()는 최종 파이프.
		System.out.println();
		
		// 4.
		Data.getStringList().stream()
							.filter(word -> word.length() >= 5)
							.distinct()
							.forEach(word -> System.out.println(word));
		System.out.println();
		
		// Ex64의 Member 클래스 사용
		ArrayList<Member> mlist = new ArrayList<Member>();
		mlist.add(new Member("홍길동", 20));
		mlist.add(new Member("아무개", 22));
		mlist.add(new Member("강아지", 5));
		mlist.add(new Member("고양이", 3));
		mlist.add(new Member("홍길동", 20)); // 같은 사람으로 쳐줄까?
		
		mlist.stream()
			 .distinct() // equals()를 내부적으로 사용하기 때문에 기본적으로는 다른 사람으로 판별한다.
			 .forEach(m -> System.out.println(m)); // hashCode()와 equals()를 사용자 설정으로 Override한 이후에는 동일 인물로 판정하게 됐다.
		
		
		
	}

	private static void m5() {
		
		Data.getStringList().stream()
							.filter(word -> word.length() >=5)
							.filter(word -> word.startsWith("애"))
							.forEach(word -> System.out.println(word));
		System.out.println();
		
		Data.getUserList().stream()
						  .filter(user -> user.getGender() == 1) // 남자만
						  .filter(user -> user.getHeight() >= 180) // 180cm 이상
						  .filter(user -> user.getWeight() >= 80) // 80kg 이상
						  .forEach(user -> System.out.println(user));
		System.out.println();
	}

	private static void m4() {

		/*
		 	스트림 Stream
		 	- 데이터 소스로부터 생성
		 	- list.stream.forEach();
		 	
		 	파이프 Pipe
		 	- stream 객체 method
		 	1. 중간 파이프
		 		- 반환값이 stream
		 	2. 최종 파이프
		 		- 반환값이 stream이 아님
		 	
		 	[최종 정리]
		 	- forEach(Consumer)
		 	-  ↑ 는 최종 파이프이다.
		 	- 앞의 stream으로부터 요소를 전달받아 최종 처리하는 method
		 	
		 	필터링
		 	- filter(Predicate)
		 	-  ↑ 는 중간 파이프이다.
		 	- 앞의 stream으로부터 요소를 전달받는데, 조건에 맞는 요소만 남기고 맞지 않는 요소는 버린다.
		 	
		 	
		 */
		
		List<Integer> list = Data.getIntList(20);
		System.out.println(list);
		System.out.println();
		
		// 요구사항) 짝수만 출력하기
		// 1. for문
		for(int i = 0; i < list.size(); i++) {
			if (list.get(i) % 2 == 0) {
				System.out.printf("%4d", list.get(i));
			}
		}
		System.out.println();
		
		// 2. enhanced for문
		for (int n : list) {
			if (n % 2 == 0) {
				System.out.printf("%4d", n);
			}
		}
		System.out.println();
		
		// 3. stream
		list.stream().forEach(n -> {
			if (n % 2 == 0) {
				System.out.printf("%4d", n);
			}
		});
		
		// 4. 중간 파이프를 사용한 stream
		list.stream()
			.filter(n -> n % 2 == 0) // filter() 자체가 boolean값을 반환하는 Predicate 객체를 사용하기 때문에 뒷 조건(삼항연산자)은 필요 없음.
			.forEach(n -> System.out.printf("%4d", n));
		// filter()는 들어온 값을 가지고 자신의 일을 한 뒤 stream으로 정제해서 다음 pipe로 반환한다.
		// 가독성을 높이기 위해 pipe 단위로 개행을 해주는 편.
		System.out.println();
		
		// 4-A.
		list.stream()
			.filter(n -> n % 2 == 0 && n >= 50)
			.forEach(n -> System.out.printf("%4d", n));
		System.out.println();
		
		// 4-B.
		list.stream()
			.filter(n -> n % 2 == 0)
			.filter(n -> n >= 50) // filter()는 중간 파이프니까 조건 별로 pipe를 나눠버려도 상관없다.
			.forEach(n -> System.out.printf("%4d", n));
		System.out.println();
		
		// 초대량의 data를 극단적인 pipe 수로 나눈다고 가정했을 때는 4-A가 더 성능이 좋겠지만...
		// 웬만하면 가독성이 높은 쪽으로 작성하자...
	}

	private static void m3() {
		// stream을 얻어오는 법
		// -stream() 메서드를 사용한다
		// 1. 배열로부터
		// 2. 컬렉션으로부터
		// 3. 숫자 범위
		// 4. 파일로부터
		// 5. 디렉토리부터
		// 스트림을 얻으면 뭐든 사용법이 동일하다.
		
		// 1.
		int[] nums1 = { 100, 200, 300, 400, 500 };
		Arrays.stream(nums1).forEach(num -> System.out.println(num));
		System.out.println();
		
		// 2.
		ArrayList<Integer> nums2 = new ArrayList<>();
		nums2.add(100);
		nums2.add(200);
		nums2.add(300);
		nums2.add(400);
		nums2.add(500);
		nums2.stream().forEach(num -> System.out.println(num));
		System.out.println();
		
		LinkedList<Integer> nums3 = new LinkedList<>();
		nums3.add(100);
		nums3.add(200);
		nums3.add(300);
		nums3.add(400);
		nums3.add(500);
		nums3.stream().forEach(num -> System.out.println(num));
		
		HashSet<Integer> nums4 = new HashSet<>();
		nums4.add(100);
		nums4.add(200);
		nums4.add(300);
		nums4.add(400);
		nums4.add(500);
		nums4.stream().forEach(num -> System.out.println(num));
		System.out.println();
		
		// HashMap은 stream이 없다.
		// List 계열, Set 계열만 stream을 제공한다.
		
		// 3. 숫자 범위 (일련의 숫자 집합을 얻어낼 때 사용. 모를 땐 for문을 돌렸겠지.)
		IntStream.range(1, 11).forEach(num -> System.out.println(num));
		System.out.println();
		
		try {
			
			// 4. 파일로부터 스트림을 얻어낼 수도 있다. (읽기O 쓰기X. BufferedReader보다 빠름)
			Path path = Paths.get("dat\\score.txt");
			Files.lines(path).forEach(line -> System.out.println(line)); // txt를 한 줄씩
			System.out.println();
			
			// 5. 디렉토리. dir.listFiles()
			Path dir = Paths.get("C:\\class\\dev\\eclipse");
			Files.list(dir).forEach(p -> { System.out.println(p.getFileName()); }); // 파일명&폴더명 읽어오기
			System.out.println();
			
		} catch (Exception e) {
			System.out.println("Ex65_Stream.m3");
			e.printStackTrace();
		}
		
		
	}

	private static void m2() {
		
		// 배열(컬렉션) 탐색
		List<String> list = Data.getStringList(10);
		System.out.println(list);
		System.out.println();
		
		// 1. for문(while문)
		for (int i = 0; i < list.size(); i++) {
			System.out.printf("%s\t", list.get(i)); // list 안의 문자열 데이터만 가져오기
		}
		System.out.println();
		
		
		// 2. enhanced for문 (== foreach문이라고 더 많이 부름)
		for (String word : list) {
			System.out.printf("%s\t", word);
		}
		System.out.println();
		
		
		// 3. iterator
		Iterator<String> iter = list.iterator();
		// 내부 커서가 하나씩 움직이면서 데이터를 가져오는 iterator 판독기
		
		while (iter.hasNext()) { // 확인
			System.out.printf("%s\t", iter.next()); // 읽기
		}
		System.out.println();
		
		
		// 4. stream (java.util.Stream)
		// 4-1. list.stream() -> ArrayList로부터 stream 객체를 생성
		// 4-2. stream을 다양하게 사용 -> 결과가 ArrayList에 반영됨
		
		Stream<String> stream = list.stream();
		
		Consumer<String> c1 = str -> System.out.println(str);
		c1.accept("홍길동");
		
		// stream은 Consumer 객체(여기선 c1)를 요구한다.
		// list의 문자열을 가져와서 c1이 가진 유일한 메서드인 c1.accept()에 넣는다.
		stream.forEach(c1); // c1.accept(가져온 단어) X 10회 호출
		
		// 위 코드를 줄이면
//		stream.forEach(str -> System.out.println(str));
		
		// 더 줄이면
		list.stream().forEach(str -> System.out.println(str));
		
		
		// 함수형 프로그래밍
		Data.getIntList(10).stream().forEach(num -> System.out.println(num));
		
		Data.getUserList().stream().forEach(user -> {
			System.out.println("[유저]");
			System.out.println("유저명: " + user.getName());
			System.out.println("나이: " + user.getAge());
			System.out.println();
			
		});
		
	}
	

	private static void m1() {
		
		int[] nums1 = Data.getIntArray();
//		System.out.println(Arrays.toString(nums1)); // 랜덤 숫자 100개
		
		int[] nums2 = Data.getIntArray(10);
		System.out.println(Arrays.toString(nums2)); // 10개만
		
		List<Integer> nums3 = Data.getIntList(); // 매개변수로 양의 정수를 넣으면 그 갯수만큼
		System.out.println(nums3); // ArrayList로 가져오고 싶으면.
		
		String[] txt1 = Data.getStringArray(10);
		System.out.println(Arrays.toString(txt1));
		
		User[] ulist = Data.getUserArray();
		System.out.println(Arrays.toString(ulist));
		
		Item[] ilist = Data.getItemArray();
		System.out.println(Arrays.toString(ilist));
		
		// 사용자 라이브러리 만들기 (*.jar)
		// - 내가 만든 기능을 다른 사람에게 전달할 때 사용
		// - 배포의 단위: *.jar
		// jar 파일을 프로젝트의 폴더 안으로 집어넣고
		// Eclipse에서 폴더 우클릭-Build Path-Configure Build Path
		// Classpath를 클릭하고 Add JARs로 방금 집어넣은 jar 파일을 프로젝트에 import
		
		MyUtil myutil = new MyUtil(); // wow!
		
	}
} // class
