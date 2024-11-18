package com.test.jpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.test.jpa.entity.Address;
import com.test.jpa.entity.AddressNameAgeMapping;
import com.test.jpa.repository.AddressRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AddressController {

	private final AddressRepository addressRepository;
	
	/*
	@GetMapping("/m.do")
	public String m(Model model) {
		return "result";
	}
	*/
	
	@GetMapping("/m01.do")
	public String m01(Model model) {
		
		/*
		  
		  JPA 사용법
		  1. Query Method
				- 정해진 키워드 + 메서드명 > 호출 > SQL 실행
				- 메서드 이름으로 쿼리를 생성하는 방식 > 정해진 키워드 + 조합 > JPA가 해당 메서드 이름을 분석해서 JPQL(SQL)을 생성하고 실행한다.
				- 장점
					- 별도의 쿼리를 작성하지 않고 쿼리를 자동으로 생성
					- 단순한 CRUD에 효율적
					- 메서드 이름 > 가독성 향상
				- 단점
					- 복잡한 업무 쿼리에는 부적합
					- 복잡한 업무의 메서드명 매우 길어짐				
				
			2. JPQL, Java Persistence Query Language
				- 직접 SQL 작성 > 실행
				- JPA에서 사용하는 전용 SQL
				- SQL은 테이블을 대상 실행 > JPQL은 엔티티를 대상 실행
				- 장점
					- SQL과 구문이 유사
					- 객체지향 관점의 SQL
					- @Query 사용
				- 단점
					- 문자열로 작성 > 컴파일때 오류 확인 불가 > 런타임 때 오류 확인 가능
					- 동적 쿼리 작성이 불편
				
			3. Query DSL
				- SQL 관련 각각의 조작을 자바 메소드를 통해서 실행
				- SQL을 자바 코드로 만드는 프레임워크
				- 장점
					- 컴파일때 오류 확인 가능
					- IDE > 자동 완성 기능 활용 가능
					- 동적 쿼리 작성 수월
					- 복잡한 쿼리도 수월
				- 단점
					- 초기 설정 복잡
					- 학습 곡선 높음
					
			
			정리
			1. 단순한 CRUD > Query Method > 40%
			2. 정적이면서 복잡한 쿼리 > JPQL > 25%
			3. 동적이거나 복잡한 쿼리 > QuerySQL > 35%
			
			
		  
		 */
		
		//[C]RUD
		//- 레코드 추가하기 > insert
		//1. 추가할 레코드 정보 확보
		//2. 엔티티 객체 생성
		//3. 리포지토리 객체 + 엔티티 객체 전달 > insert 요청
		
		Address address= new Address(null, "깡총이", 5, "서울특별시 강남구 역삼동 한독빌딩", "m");
		
		Address result = addressRepository.save(address);
		
		//Controller > (전달) > View
		model.addAttribute("dto", result.toDTO());
		
		return "result_dto";
	}
	
	@GetMapping("/m02.do")
	public String m02(Model model) {
		
		//C[R]UD
		//- 1개의 레코드를 가져오기 + 기본키 조건
		Optional<Address> address = addressRepository.findById(2L);
		
//		if(address.isPresent()) { //address != null 랑 동일한 의미
//			model.addAttribute("dto", address.get().toDTO());
//		}
		
//		address.ifPresent(value -> model.addAttribute("dto", Address.toDTO(value)));
		
		address.ifPresent(value -> model.addAttribute("dto", value.toDTO()));

		return "result_dto";
	}
	
	@GetMapping("/m03.do")
	public String m03(Model model) {
		
		//CR[U]D
		//- 레코드 수정하기
		//1. 엔티티 새로 만든 뒤 > 이 값으로 기존 레코드를 수정
		//1. 기존 엔티티 검색 > 기존 엔티티 수정 > 레코드 수정
		
		//53 깡총이 5 서울특별시 강남구 역삼동 한독빌딩 m > address 바꾸기!
		
		//방법 1
//		Address address = Address.builder()
//							.seq(53L)
//							.name("깡총이")
//							.age(5)
//							.address("서울특별시 강남구 역삼동 한독빌딩 8층")
//							.gender("m")
//							.build();
		
		//방법 2
		Address address = addressRepository.findById(53L).get();
		address.updateAddress("서울특별시 강동구 천호동");
		
		//Update 진행
		Address result = addressRepository.save(address);
		
		model.addAttribute("dto", result.toDTO());
		
		return "result_dto";
	}
	
	@GetMapping("/m04.do")
	public String m04(Model model) {
		//CRU[D]
		//- 레코드 삭제하기
		
		//방법 1
//		Address address = Address.builder()
//							.seq(52L)
//							.build();
		
		//방법 2
		Address address = addressRepository.findById(53L).get();
		
		addressRepository.delete(address);
		
		return "result_dto";
	}
	
	
	@GetMapping("/m05.do")
	public String m05(Model model) {
		
		//Query Method
		//- 키워드 조합 > 메서드 이름 만들기
		//- 메서드명 = Subject Part + Predicate Part
		//				= 가져올 컬럼 + 조건
		
		
		//Select문
		//- fingBt, getBy, readBy, queryBy, serachBy, streamBy 등,,,
		//- findBy(***)
		
		//메서드명 = findBy + Subject Part [+ Predicate Part]
		
		//'다람쥐' 검색
		//- select * from tblAddress where name = '다람쥐'
		
//		Optional<Address> address = addressRepository.findByName("다람쥐");
	
		
//		Optional<Address> address = addressRepository.findByAddress("서울특별시 강북구 삼양동 305-7 삼양프라자 2층");
	
		//레코드 하나만 가져와야하는데 m으로 조회시 1개 이상의 컬럼이 나옴! 그래서 오류
		Optional<Address> address = addressRepository.findByGender("m");
		
		model.addAttribute("dto", address.get().toDTO());
		
		return "result_dto";
	}
	
	@GetMapping("/m06.do")
	public String m06(Model model) {
		
		//카운트
		
		
		long count = addressRepository.count();
		
		//레코드 존재 유무: count(*) > 0
		boolean result = addressRepository.existsById(1L);
		
		model.addAttribute("count", count);
		model.addAttribute("result", result);
		
		
		return "result_item";
	}
	
	
	@GetMapping("/m07.do")
	public String m07(Model model) {
		
		//전체 select
		
		List<Address> list = addressRepository.findAll();
		
		//List<Entity> > (변환) > List<DTO>
		
		model.addAttribute("list", list);
		
		
		return "result_list";
	}
	
	@GetMapping("/m08.do")
	public String m08(Model model) {
		
		//First, TOP
		//- 가져올 레코드의 개수를 지정한다.
		//- 키워드 뒤에 숫자 지정. 생략(1)
		//- 1이면 단수 반환, N이면 복수 반환
		Address address = addressRepository.findFirst3ByAge(3);
		
		model.addAttribute("dto", address.toDTO());
		return "result_list";
		
	}
	
	@GetMapping("/m09.do")
	public String m09(Model model) {
		
		//And, Or
		
		//- where name = '강아지' and gender = 'm'
//		Address address = addressRepository.findByNameAndGender("강아지","m");
//		
//		model.addAttribute("dto", address.toDTO());
//		
//		return "result_dto";
		
		
		//- where gender = 'm' and age = 3
		//- where gender = 'm' or age = 3
		// 행이 여러개이기 때문에 List
		
		List<Address> list = addressRepository.findByGenderOrAge("m", 3);
		model.addAttribute("list", list);
		
		return"result_list";
		
	}
	
	@GetMapping("/m10.do")
	public String m10(Model model) {
		
		//After Before GeraterThan(GreaterThanEqual), LessThan(LessThanEqual) Between
		//- After, GreaterThan(GreaterThanEqual) > 크다
		//- Before, LessThan(LessThanEqual) > 작다
		//- Between > 범위
		//- After/Before > 날짜 시간 비교
		//- GreaterThan/LessThan > 크기 비교
		
//		List<Address> list = addressRepository.findByAgeGreaterThan(5);
//		List<Address> list = addressRepository.findByAgeLessThan(5);
//		List<Address> list = addressRepository.findByAgeLessThanEqual(5);
		List<Address> list = addressRepository.findByAgeBetween(3, 5);
		
		
		model.addAttribute("list", list);
		
		return "result_list";
	}

	
	@GetMapping("/m11.do")
	public String m11(Model model) {
		
		//isEmpty, isNull
		//isNotEmpty, isNotNull
		//- isNull > null 체크
		//- isEmpty > 빈문자열, 컬렉션 size(0) 등을 체크
		
		List<Address> list = addressRepository.findByAddressIsNull();
//		List<Address> list = addressRepository.findByAddressIsNotNull();
		
		model.addAttribute("list", list);
		
		
		return "result_list";
	}
	
	
	@GetMapping("/m12.do")
	public String m12(Model model) {
		
		//In, NotIn
		//- 열거형 조건
		//- 조건을 List로 넘긴다.
		
		List<Integer> age = List.of(3, 5, 7);
		
//		List<Address> list = addressRepository.findByAgeIn(age);
		List<Address> list = addressRepository.findByAgeNotIn(age);
		
		model.addAttribute("list", list);
		
		return "result_list";
	}

	@GetMapping("/m13.do")
	public String m13(Model model) {
		
		//StartingWith(StartsWith), EndingWith(EndsWith), Contains, Like
		
		//address like '% % %'
		
//		List<Address> list = addressRepository.findByAddressStartingWith("서울특별시 강남구");
//		List<Address> list = addressRepository.findByAddressEndingWith("층");
//		List<Address> list = addressRepository.findByAddressContains("역삼");
//		List<Address> list = addressRepository.findByAddressLike("%타워%");
		List<Address> list = addressRepository.findByAddressNotLike("%타워%");
		
		model.addAttribute("list", list);
		
		return "result_list";
	}
	
	@GetMapping("/m14.do")
	public String m14(Model model) {
		
		//Is, Equals
//		Optional<Address> address = addressRepository.findByName("강아지");
//		Optional<Address> address = addressRepository.findByNameIs("강아지");
		Optional<Address> address = addressRepository.findByNameEquals("강아지");
		
		address.ifPresent(value -> model.addAttribute("dto", value.toDTO()));
		
		
		return "result_dto";
	}
	
	
	@GetMapping("/m15.do")
	public String m15(Model model) {
		
		//정렬
		//- OrderBy 컬럼명 Asc
		//- OrderBy 컬럼명 Desc
		
		//다중 정렬
		//- OrderBy 컬럼명 Asc 컬럼명 Desc 컬럼명 Asc
		
		
//		List<Address> list = addressRepository.findAllByOrderByNameAsc();
//		List<Address> list = addressRepository.findAllByGenderOrderByNameDesc("f");
//		List<Address> list = addressRepository.findAllByGenderOrderByAgeAscNameDesc("f");
		
		
//		List<Address> list = addressRepository.findAll(Sort.by(Sort.Order.desc("age"), Sort.Order.asc("name")));
	
		List<Address> list = addressRepository.findByGender("m", Sort.by("age"));
		
		model.addAttribute("list", list);
		
		return "result_list";
	}
	
	//가변 인자 리스트
	public void test(String...args) {
		
		for(int i=0; i<args.length; i++) {
			System.out.println(i+":"+args[i]);
		}
	}
	
	@GetMapping("/m16.do")
	public String m16(Model model) {
		
		//여태껏 Select
		//- select * from tblAddress
		
		//일부 컬럼만 가져오기 Select
		//- select name, age from tblAddress
		//- 인터페이스 사용
		//-com.test.jpa.entity > "AddressNameAgeMapping.java(I)"
		
		List<AddressNameAgeMapping> list = addressRepository.findAllByGender("f");
		
		model.addAttribute("list2", list);
		
		return "result_list";
	}
	
}

/*
@GetMapping("/m.do")
public String m(Model model) {
	return "result";
}
*/





